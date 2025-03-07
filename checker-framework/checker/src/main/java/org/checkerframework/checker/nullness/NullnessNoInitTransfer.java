package org.checkerframework.checker.nullness;

import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.dataflow.analysis.ConditionalTransferResult;
import org.checkerframework.dataflow.analysis.TransferInput;
import org.checkerframework.dataflow.analysis.TransferResult;
import org.checkerframework.dataflow.cfg.node.ArrayAccessNode;
import org.checkerframework.dataflow.cfg.node.FieldAccessNode;
import org.checkerframework.dataflow.cfg.node.InstanceOfNode;
import org.checkerframework.dataflow.cfg.node.MethodAccessNode;
import org.checkerframework.dataflow.cfg.node.MethodInvocationNode;
import org.checkerframework.dataflow.cfg.node.Node;
import org.checkerframework.dataflow.cfg.node.NullLiteralNode;
import org.checkerframework.dataflow.cfg.node.ReturnNode;
import org.checkerframework.dataflow.cfg.node.ThrowNode;
import org.checkerframework.dataflow.expression.JavaExpression;
import org.checkerframework.dataflow.expression.LocalVariable;
import org.checkerframework.dataflow.util.PurityUtils;
import org.checkerframework.framework.flow.CFAbstractStore;
import org.checkerframework.framework.flow.CFAbstractTransfer;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.framework.type.AnnotatedTypeMirror.AnnotatedDeclaredType;
import org.checkerframework.framework.type.AnnotatedTypeMirror.AnnotatedExecutableType;
import org.checkerframework.framework.type.visitor.SimpleAnnotatedTypeScanner;
import org.checkerframework.framework.util.AnnotatedTypes;
import org.checkerframework.javacutil.AnnotationBuilder;
import org.checkerframework.javacutil.AnnotationMirrorSet;
import org.checkerframework.javacutil.AnnotationUtils;
import org.checkerframework.javacutil.TreeUtils;
import org.checkerframework.javacutil.TypeSystemError;
import org.checkerframework.javacutil.TypesUtils;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

/**
 * Transfer function for the non-null type system. Performs the following refinements:
 *
 * <ol>
 *   <li>After an expression is compared with the {@code null} literal, then that expression can
 *       safely be considered {@link NonNull} if the result of the comparison is false or {@link
 *       Nullable} if the result is true.
 *   <li>If an expression is dereferenced, then it can safely be assumed to non-null in the future.
 *       If it would not be, then the dereference would have raised a {@link NullPointerException}.
 *   <li>Tracks whether {@link PolyNull} is known to be {@link NonNull} or {@link Nullable} (or not
 *       known to be either).
 * </ol>
 */
public class NullnessNoInitTransfer
        extends CFAbstractTransfer<
                NullnessNoInitValue, NullnessNoInitStore, NullnessNoInitTransfer> {

    /** The @{@link NonNull} annotation. */
    protected final AnnotationMirror NONNULL;

    /** The @{@link Nullable} annotation. */
    protected final AnnotationMirror NULLABLE;

    /** The @{@link PolyNull} annotation. */
    protected final AnnotationMirror POLYNULL;

    /**
     * Java's Map interface.
     *
     * <p>The qualifiers in this type don't matter -- it is not used as a fully-annotated
     * AnnotatedDeclaredType, but just passed to asSuper().
     */
    protected final AnnotatedDeclaredType MAP_TYPE;

    /** The type factory for the nullness analysis that was passed to the constructor. */
    protected final NullnessNoInitAnnotatedTypeFactory nullnessTypeFactory;

    /**
     * The type factory for the map key analysis, or null if the Map Key Checker should not be run.
     */
    protected final @Nullable KeyForAnnotatedTypeFactory keyForTypeFactory;

    /** True if -AassumeKeyFor was provided on the command line. */
    private final boolean assumeKeyFor;

    /**
     * True if conservativeArgumentNullnessAfterInvocation flag is turned off, meaning that after a
     * method call or constructor invocation, arguments of the invocation (including the receiver)
     * are assumed to be non-null.
     */
    private final boolean nonNullAssumptionAfterInvocation;

    /**
     * Create a new NullnessTransfer for the given analysis.
     *
     * @param analysis nullness analysis
     */
    public NullnessNoInitTransfer(NullnessNoInitAnalysis analysis) {
        super(analysis);
        this.nullnessTypeFactory = (NullnessNoInitAnnotatedTypeFactory) analysis.getTypeFactory();
        Elements elements = nullnessTypeFactory.getElementUtils();
        BaseTypeChecker checker = nullnessTypeFactory.getChecker();
        assumeKeyFor = checker.hasOption("assumeKeyFor");
        if (assumeKeyFor) {
            this.keyForTypeFactory = null;
        } else {
            // It is error-prone to put a type factory in a field.  It is OK here because
            // keyForTypeFactory is used only to call methods isMapGet() and isKeyForMap().
            this.keyForTypeFactory =
                    nullnessTypeFactory.getTypeFactoryOfSubchecker(KeyForSubchecker.class);
        }

        NONNULL = AnnotationBuilder.fromClass(elements, NonNull.class);
        NULLABLE = AnnotationBuilder.fromClass(elements, Nullable.class);
        POLYNULL = AnnotationBuilder.fromClass(elements, PolyNull.class);

        MAP_TYPE =
                (AnnotatedDeclaredType)
                        AnnotatedTypeMirror.createType(
                                TypesUtils.typeFromClass(Map.class, analysis.getTypes(), elements),
                                nullnessTypeFactory,
                                false);

        nonNullAssumptionAfterInvocation =
                !analysis.getTypeFactory()
                        .getChecker()
                        .getUltimateParentChecker()
                        .getBooleanOption("conservativeArgumentNullnessAfterInvocation", false);
    }

    /**
     * Sets a given {@link Node} to non-null in the given {@code store}. Calls to this method
     * implement case 2.
     *
     * @param store the store to update
     * @param node the node that should be non-null
     */
    protected void makeNonNull(NullnessNoInitStore store, Node node) {
        JavaExpression internalRepr = JavaExpression.fromNode(node);
        store.insertValue(internalRepr, NONNULL);
    }

    /**
     * Sets a given node to non-null in the given transfer result.
     *
     * @param result the transfer result
     * @param node the node to make non-null
     */
    protected void makeNonNull(
            TransferResult<NullnessNoInitValue, NullnessNoInitStore> result, Node node) {
        if (result.containsTwoStores()) {
            makeNonNull(result.getThenStore(), node);
            makeNonNull(result.getElseStore(), node);
        } else {
            makeNonNull(result.getRegularStore(), node);
        }
    }

    /**
     * Refine the given result to @NonNull.
     *
     * @param result the result to refine
     */
    protected void refineToNonNull(
            TransferResult<NullnessNoInitValue, NullnessNoInitStore> result) {
        NullnessNoInitValue oldResultValue = result.getResultValue();
        NullnessNoInitValue refinedResultValue =
                analysis.createSingleAnnotationValue(NONNULL, oldResultValue.getUnderlyingType());
        NullnessNoInitValue newResultValue = refinedResultValue.mostSpecific(oldResultValue, null);
        result.setResultValue(newResultValue);
    }

    @Override
    protected @Nullable NullnessNoInitValue finishValue(
            @Nullable NullnessNoInitValue value, NullnessNoInitStore store) {
        value = super.finishValue(value, store);
        if (value != null) {
            value.isPolyNullNonNull = store.isPolyNullNonNull();
            value.isPolyNullNull = store.isPolyNullNull();
        }
        return value;
    }

    @Override
    protected @Nullable NullnessNoInitValue finishValue(
            @Nullable NullnessNoInitValue value,
            NullnessNoInitStore thenStore,
            NullnessNoInitStore elseStore) {
        value = super.finishValue(value, thenStore, elseStore);
        if (value != null) {
            value.isPolyNullNonNull =
                    thenStore.isPolyNullNonNull() && elseStore.isPolyNullNonNull();
            value.isPolyNullNull = thenStore.isPolyNullNull() && elseStore.isPolyNullNull();
        }
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Furthermore, this method refines the type to {@code NonNull} for the appropriate branch if
     * an expression is compared to the {@code null} literal (listed as case 1 in the class
     * description).
     */
    @Override
    protected TransferResult<NullnessNoInitValue, NullnessNoInitStore>
            strengthenAnnotationOfEqualTo(
                    TransferResult<NullnessNoInitValue, NullnessNoInitStore> res,
                    Node firstNode,
                    Node secondNode,
                    NullnessNoInitValue firstValue,
                    NullnessNoInitValue secondValue,
                    boolean notEqualTo) {
        res =
                super.strengthenAnnotationOfEqualTo(
                        res, firstNode, secondNode, firstValue, secondValue, notEqualTo);
        if (firstNode instanceof NullLiteralNode) {
            NullnessNoInitStore thenStore = res.getThenStore();
            NullnessNoInitStore elseStore = res.getElseStore();

            List<Node> secondParts = splitAssignments(secondNode);
            for (Node secondPart : secondParts) {
                JavaExpression secondInternal = JavaExpression.fromNode(secondPart);
                if (CFAbstractStore.canInsertJavaExpression(secondInternal)) {
                    thenStore = thenStore == null ? res.getThenStore() : thenStore;
                    elseStore = elseStore == null ? res.getElseStore() : elseStore;
                    if (notEqualTo) {
                        thenStore.insertValue(secondInternal, NONNULL);
                    } else {
                        elseStore.insertValue(secondInternal, NONNULL);
                    }
                }
            }

            AnnotationMirrorSet secondAnnos =
                    secondValue != null ? secondValue.getAnnotations() : new AnnotationMirrorSet();
            if (nullnessTypeFactory.containsSameByClass(secondAnnos, PolyNull.class)) {
                thenStore = thenStore == null ? res.getThenStore() : thenStore;
                elseStore = elseStore == null ? res.getElseStore() : elseStore;
                // TODO: methodTree is null for lambdas.  Handle that case.  See Issue3850.java.
                MethodTree methodTree = analysis.getEnclosingMethod(secondNode.getTree());
                ExecutableElement methodElem =
                        methodTree == null ? null : TreeUtils.elementFromDeclaration(methodTree);
                if (notEqualTo) {
                    elseStore.setPolyNullNull(true);
                    if (methodElem != null && polyNullIsNonNull(methodElem, thenStore)) {
                        thenStore.setPolyNullNonNull(true);
                    }
                } else {
                    thenStore.setPolyNullNull(true);
                    if (methodElem != null && polyNullIsNonNull(methodElem, elseStore)) {
                        elseStore.setPolyNullNonNull(true);
                    }
                }
            }

            if (thenStore != null) {
                return new ConditionalTransferResult<>(res.getResultValue(), thenStore, elseStore);
            }
        }
        return res;
    }

    /**
     * Returns true if every formal parameter that is declared as @PolyNull is currently known to be
     * non-null.
     *
     * @param method a method
     * @param s a store
     * @return true if every formal parameter declared as @PolyNull is non-null
     */
    private boolean polyNullIsNonNull(ExecutableElement method, NullnessNoInitStore s) {
        // No need to check the receiver, which is always non-null.
        for (VariableElement var : method.getParameters()) {
            AnnotatedTypeMirror varType = nullnessTypeFactory.fromElement(var);

            if (containsPolyNullNotAtTopLevel(varType)) {
                return false;
            }

            if (varType.hasAnnotation(POLYNULL)) {
                NullnessNoInitValue v = s.getValue(new LocalVariable(var));
                if (!AnnotationUtils.containsSameByName(v.getAnnotations(), NONNULL)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A scanner that returns true if there is an occurrence of @PolyNull that is not at the top
     * level.
     */
    // Not static so it can access field POLYNULL.
    private class ContainsPolyNullNotAtTopLevelScanner
            extends SimpleAnnotatedTypeScanner<Boolean, Void> {
        /**
         * True if the top-level type has not yet been processed (by the first call to
         * defaultAction).
         */
        private boolean isTopLevel = true;

        /** Create a ContainsPolyNullNotAtTopLevelScanner. */
        ContainsPolyNullNotAtTopLevelScanner() {
            super(Boolean::logicalOr, false);
        }

        @Override
        protected Boolean defaultAction(AnnotatedTypeMirror type, Void p) {
            if (isTopLevel) {
                isTopLevel = false;
                return false;
            } else {
                return type.hasAnnotation(POLYNULL);
            }
        }
    }

    /**
     * Returns true if there is an occurrence of @PolyNull that is not at the top level.
     *
     * @param t a type
     * @return true if there is an occurrence of @PolyNull that is not at the top level
     */
    private boolean containsPolyNullNotAtTopLevel(AnnotatedTypeMirror t) {
        return new ContainsPolyNullNotAtTopLevelScanner().visit(t);
    }

    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitArrayAccess(
            ArrayAccessNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> p) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result =
                super.visitArrayAccess(n, p);
        makeNonNull(result, n.getArray());
        return result;
    }

    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitInstanceOf(
            InstanceOfNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> p) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result =
                super.visitInstanceOf(n, p);
        NullnessNoInitStore thenStore = result.getThenStore();
        NullnessNoInitStore elseStore = result.getElseStore();
        makeNonNull(thenStore, n.getOperand());
        return new ConditionalTransferResult<>(result.getResultValue(), thenStore, elseStore);
    }

    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitMethodAccess(
            MethodAccessNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> p) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result =
                super.visitMethodAccess(n, p);
        // The receiver of an instance method access is non-null. A static method access does not
        // ensure that the receiver is non-null.
        if (!n.isStatic()) {
            makeNonNull(result, n.getReceiver());
        }
        return result;
    }

    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitFieldAccess(
            FieldAccessNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> p) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result =
                super.visitFieldAccess(n, p);
        // The receiver of an instance field access is non-null. A static field access does not
        // ensure that the receiver is non-null.
        if (!n.isStatic()) {
            makeNonNull(result, n.getReceiver());
        }
        return result;
    }

    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitThrow(
            ThrowNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> p) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result = super.visitThrow(n, p);
        makeNonNull(result, n.getExpression());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * <p>When the conservativeArgumentNullnessAfterInvocation flag is turned off, the receiver and
     * arguments that are passed to non-null parameters in a method call or constructor invocation
     * are unsoundly assumed to be non-null after the invocation.
     *
     * <p>When the flag is turned on, the analysis is more conservative by checking the method is
     * SideEffectFree or the receiver is unassignable. Only if either one of the two is true, is the
     * receiver made non-null. Similar logic is applied to the arguments of the invocation.
     *
     * <p>Provided that m is of a type that implements interface java.util.Map:
     *
     * <ul>
     *   <li>Given a call m.get(k), if k is @KeyFor("m") and m's value type is @NonNull, then the
     *       result is @NonNull in the thenStore and elseStore of the transfer result.
     * </ul>
     */
    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitMethodInvocation(
            MethodInvocationNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> in) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result =
                super.visitMethodInvocation(n, in);

        MethodInvocationTree tree = n.getTree();
        ExecutableElement method = TreeUtils.elementFromUse(tree);

        boolean isMethodSideEffectFree =
                nullnessTypeFactory.isSideEffectFree(method)
                        || PurityUtils.isSideEffectFree(nullnessTypeFactory, method);
        Node receiver = n.getTarget().getReceiver();
        if (nonNullAssumptionAfterInvocation
                || isMethodSideEffectFree
                || !JavaExpression.fromNode(receiver).isAssignableByOtherCode()) {
            // Make receiver non-null.
            makeNonNull(result, receiver);
        }

        // For all formal parameters with a non-null annotation, make the actual argument non-null.
        // The point of this is to prevent cascaded errors -- the Nullness Checker will issue a
        // warning for the method invocation, but not for subsequent uses of the argument.  See test
        // case FlowNullness.java.
        AnnotatedExecutableType methodType = nullnessTypeFactory.getAnnotatedType(method);
        List<AnnotatedTypeMirror> methodParams = methodType.getParameterTypes();
        List<? extends ExpressionTree> methodArgs = tree.getArguments();
        for (int i = 0; i < methodParams.size() && i < methodArgs.size(); ++i) {
            if (methodParams.get(i).hasAnnotation(NONNULL)
                    && (nonNullAssumptionAfterInvocation
                            || isMethodSideEffectFree
                            || !JavaExpression.fromTree(methodArgs.get(i))
                                    .isAssignableByOtherCode())) {
                makeNonNull(result, n.getArgument(i));
            }
        }

        // Refine result to @NonNull if n is an invocation of Map.get, the argument is a key for
        // the map, and the map's value type is not @Nullable.
        if (nullnessTypeFactory.isMapGet(n)) {
            boolean isKeyFor;
            if (keyForTypeFactory != null) {
                String mapName = JavaExpression.fromNode(receiver).toString();
                isKeyFor = keyForTypeFactory.isKeyForMap(mapName, methodArgs.get(0));
            } else {
                isKeyFor = assumeKeyFor;
            }
            if (isKeyFor) {
                AnnotatedTypeMirror receiverType = nullnessTypeFactory.getReceiverType(n.getTree());
                if (!hasNullableValueType(receiverType)) {
                    makeNonNull(result, n);
                    refineToNonNull(result);
                }
            }
        }

        return result;
    }

    /**
     * Returns true if mapType's value type (the V type argument to Map) is @Nullable.
     *
     * @param mapOrSubtype the Map type, or a subtype
     * @return true if mapType's value type is @Nullable
     */
    private boolean hasNullableValueType(AnnotatedTypeMirror mapOrSubtype) {
        AnnotatedDeclaredType mapType =
                AnnotatedTypes.asSuper(nullnessTypeFactory, mapOrSubtype, MAP_TYPE);
        int numTypeArguments = mapType.getTypeArguments().size();
        if (numTypeArguments != 2) {
            throw new TypeSystemError(
                    "Wrong number %d of type arguments: %s", numTypeArguments, mapType);
        }
        AnnotatedTypeMirror valueType = mapType.getTypeArguments().get(1);
        return valueType.hasAnnotation(NULLABLE);
    }

    @Override
    public TransferResult<NullnessNoInitValue, NullnessNoInitStore> visitReturn(
            ReturnNode n, TransferInput<NullnessNoInitValue, NullnessNoInitStore> in) {
        TransferResult<NullnessNoInitValue, NullnessNoInitStore> result = super.visitReturn(n, in);

        if (result.getResultValue() == null) {
            // Make sure there is a value for return statements, to record (at this return
            // statement) the values of isPolyNullNotNull and isPolyNullNull.
            return recreateTransferResult(createDummyValue(), result);
        } else {
            return result;
        }
    }

    /**
     * Creates a dummy abstract value (whose type is not supposed to be looked at).
     *
     * @return a dummy abstract value
     */
    private NullnessNoInitValue createDummyValue() {
        TypeMirror dummy = analysis.getEnv().getTypeUtils().getPrimitiveType(TypeKind.BOOLEAN);
        AnnotationMirrorSet annos = new AnnotationMirrorSet();
        annos.addAll(nullnessTypeFactory.getQualifierHierarchy().getBottomAnnotations());
        return new NullnessNoInitValue(analysis, annos, dummy);
    }
}
