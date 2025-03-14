/*
 * Copyright (c) 1999, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package jdk.javadoc.internal.doclets.toolkit.util;

import org.checkerframework.dataflow.qual.Pure;
import java.lang.annotation.Documented;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.text.CollationKey;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.lang.model.AnnotatedConstruct;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.ModuleElement.RequiresDirective;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.lang.model.util.SimpleElementVisitor14;
import javax.lang.model.util.SimpleTypeVisitor14;
import javax.lang.model.util.TypeKindVisitor9;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileManager.Location;
import javax.tools.StandardLocation;
import com.sun.source.doctree.BlockTagTree;
import com.sun.source.doctree.DeprecatedTree;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.DocTree.Kind;
import com.sun.source.doctree.EndElementTree;
import com.sun.source.doctree.ParamTree;
import com.sun.source.doctree.ProvidesTree;
import com.sun.source.doctree.ReturnTree;
import com.sun.source.doctree.SeeTree;
import com.sun.source.doctree.SerialDataTree;
import com.sun.source.doctree.SerialFieldTree;
import com.sun.source.doctree.SerialTree;
import com.sun.source.doctree.SpecTree;
import com.sun.source.doctree.StartElementTree;
import com.sun.source.doctree.TextTree;
import com.sun.source.doctree.ThrowsTree;
import com.sun.source.doctree.UsesTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.LineMap;
import com.sun.source.util.DocSourcePositions;
import com.sun.source.util.DocTrees;
import com.sun.source.util.TreePath;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.BaseOptions;
import jdk.javadoc.internal.doclets.toolkit.CommentUtils;
import jdk.javadoc.internal.doclets.toolkit.CommentUtils.DocCommentInfo;
import jdk.javadoc.internal.doclets.toolkit.Resources;
import static javax.lang.model.element.ElementKind.*;
import static javax.lang.model.type.TypeKind.*;
import static com.sun.source.doctree.DocTree.Kind.*;

public class Utils {

    public final BaseConfiguration configuration;

    public final DocTrees docTrees;

    public final Elements elementUtils;

    public final Types typeUtils;

    public final Comparators comparators;

    public Utils(BaseConfiguration c) {
    }

    public TypeMirror getSymbol(String signature);

    public TypeMirror getObjectType();

    public TypeMirror getThrowableType();

    public TypeMirror getSerializableType();

    public TypeMirror getExternalizableType();

    public TypeMirror getDeprecatedType();

    public TypeMirror getFunctionalInterface();

    @Pure
    public boolean isCoreClass(TypeElement e);

    public Location getLocationForPackage(PackageElement pd);

    public Location getLocationForModule(ModuleElement mdle);

    @Pure
    public boolean isAnnotated(TypeMirror e);

    @Pure
    public boolean isAnnotationInterface(Element e);

    @Pure
    public boolean isClass(Element e);

    @Pure
    public boolean isInterface(Element e);

    @Pure
    public boolean isConstructor(Element e);

    @Pure
    public boolean isEnum(Element e);

    @Pure
    public boolean isField(Element e);

    @Pure
    public boolean isPlainInterface(Element e);

    @Pure
    public boolean isMethod(Element e);

    @Pure
    public boolean isModule(Element e);

    @Pure
    public boolean isPackage(Element e);

    @Pure
    public boolean isAbstract(Element e);

    @Pure
    public boolean isDefault(Element e);

    @Pure
    public boolean isFinal(Element e);

    @Pure
    public boolean isPackagePrivate(Element e);

    @Pure
    public boolean isPrivate(Element e);

    @Pure
    public boolean isProtected(Element e);

    @Pure
    public boolean isPublic(Element e);

    @Pure
    public boolean isProperty(String name);

    @Pure
    public String getPropertyName(String name);

    @Pure
    public String getPropertyLabel(String name);

    @Pure
    public boolean isOverviewElement(Element e);

    @Pure
    public boolean isStatic(Element e);

    @Pure
    public boolean isSerializable(TypeElement e);

    @Pure
    public boolean isExternalizable(TypeElement e);

    @Pure
    public boolean isRecord(TypeElement e);

    @Pure
    public boolean isCanonicalRecordConstructor(ExecutableElement ee);

    public SortedSet<VariableElement> serializableFields(TypeElement aclass);

    public SortedSet<ExecutableElement> serializationMethods(TypeElement aclass);

    public boolean definesSerializableFields(TypeElement aclass);

    @Pure
    public boolean isFunctionalInterface(AnnotationMirror amirror);

    @Pure
    public boolean isFunctionalInterface(TypeElement typeElement);

    @Pure
    public boolean isUndocumentedEnclosure(TypeElement enclosingTypeElement);

    @Pure
    public boolean isNonThrowableClass(TypeElement te);

    @Pure
    public boolean isThrowable(TypeElement te);

    @Pure
    public boolean isExecutableElement(Element e);

    @Pure
    public boolean isVariableElement(Element e);

    @Pure
    public boolean isTypeElement(Element e);

    public String signature(ExecutableElement e, TypeElement site);

    public String flatSignature(ExecutableElement e, TypeElement site);

    public String makeSignature(ExecutableElement e, TypeElement site, boolean full);

    public String makeSignature(ExecutableElement e, TypeElement site, boolean full, boolean ignoreTypeParameters);

    public String getTypeSignature(TypeMirror t, boolean qualifiedName, boolean noTypeParameters);

    @Pure
    public boolean isArrayType(TypeMirror t);

    @Pure
    public boolean isDeclaredType(TypeMirror t);

    @Pure
    public boolean isTypeParameterElement(Element e);

    @Pure
    public boolean isTypeVariable(TypeMirror t);

    @Pure
    public boolean isVoid(TypeMirror t);

    @Pure
    public boolean ignoreBounds(TypeMirror bound);

    public List<? extends TypeMirror> getBounds(TypeParameterElement tpe);

    public TypeMirror getReturnType(TypeElement site, ExecutableElement ee);

    public ExecutableType asInstantiatedMethodType(TypeElement site, ExecutableElement ee);

    public TypeMirror asInstantiatedFieldType(TypeElement site, VariableElement ve);

    public record OverrideInfo(ExecutableElement overriddenMethod, DeclaredType overriddenMethodOwner) {
    }

    public OverrideInfo overriddenMethod(ExecutableElement method);

    public SortedSet<TypeElement> getTypeElementsAsSortedSet(Iterable<TypeElement> typeElements);

    public List<? extends SerialDataTree> getSerialDataTrees(ExecutableElement member);

    public FileObject getFileObject(TypeElement te);

    public TypeMirror getDeclaredType(TypeElement enclosing, TypeMirror target);

    public TypeMirror getDeclaredType(Collection<TypeMirror> values, TypeElement enclosing, TypeMirror target);

    public Set<TypeMirror> getAllInterfaces(TypeElement te);

    @Pure
    public boolean isGenericType(TypeMirror type);

    @Pure
    public boolean isDocumentedAnnotation(TypeElement annotation);

    @Pure
    public boolean isLinkable(TypeElement typeElem);

    @Pure
    public boolean isLinkable(TypeElement typeElem, Element elem);

    public TypeElement asTypeElement(TypeMirror t);

    public TypeMirror getComponentType(TypeMirror t);

    public String getDimension(TypeMirror t);

    public TypeElement getFirstVisibleSuperClassAsTypeElement(TypeElement te);

    public TypeMirror getFirstVisibleSuperClass(TypeMirror type);

    public TypeMirror getFirstVisibleSuperClass(TypeElement te);

    public String getTypeElementKindName(TypeElement te, boolean lowerCaseOnly);

    public String getTypeName(TypeMirror t, boolean fullyQualified);

    public String replaceTabs(String text);

    public static String toLowerCase(String s);

    @Pure
    public boolean isDeprecated(Element e);

    @Pure
    public boolean isDeprecatedForRemoval(Element e);

    public String getDeprecatedSince(Element e);

    public Object getPreviewFeature(Element e);

    public String propertyName(ExecutableElement e);

    @Pure
    public boolean hasHiddenTag(Element e);

    @Pure
    public boolean isSimpleOverride(ExecutableElement m);

    public SortedSet<TypeElement> filterOutPrivateClasses(Iterable<TypeElement> classlist, boolean javafx);

    public int compareStrings(String s1, String s2);

    int compareStrings(boolean caseSensitive, String s1, String s2);

    public String getHTMLTitle(Element element);

    private static class DocCollator {

        CollationKey getKey(String s);

        public int compare(String s1, String s2);
    }

    public String getQualifiedTypeName(TypeMirror t);

    public String getFullyQualifiedName(Element e);

    public String getFullyQualifiedName(Element e, final boolean outer);

    public Iterable<TypeElement> getEnclosedTypeElements(PackageElement pkg);

    public List<Element> getAnnotationMembers(TypeElement te);

    public List<VariableElement> getFields(TypeElement te);

    public List<VariableElement> getFieldsUnfiltered(TypeElement te);

    public List<ExecutableElement> getConstructors(TypeElement te);

    public List<ExecutableElement> getMethods(TypeElement te);

    public Map<ModuleElement, Set<PackageElement>> getModulePackageMap();

    public Map<ModuleElement, String> getDependentModules(ModuleElement mdle);

    public String getModifiers(RequiresDirective rd);

    public long getLineNumber(Element e);

    public List<VariableElement> getEnumConstants(TypeElement te);

    public SortedSet<TypeElement> getAllClassesUnfiltered(PackageElement pkg);

    public SortedSet<TypeElement> getAllClasses(PackageElement pkg);

    public boolean shouldDocument(Element e);

    public String getSimpleName(Element e);

    public TypeElement getEnclosingTypeElement(Element e);

    public String constantValueExpression(VariableElement ve);

    private static class ConstantValueExpression extends TypeKindVisitor9<String, Object> {

        @Override
        public String visitPrimitiveAsBoolean(PrimitiveType t, Object val);

        @Override
        public String visitPrimitiveAsByte(PrimitiveType t, Object val);

        @Override
        public String visitPrimitiveAsChar(PrimitiveType t, Object val);

        @Override
        public String visitPrimitiveAsDouble(PrimitiveType t, Object val);

        @Override
        public String visitPrimitiveAsFloat(PrimitiveType t, Object val);

        @Override
        public String visitPrimitiveAsLong(PrimitiveType t, Object val);

        @Override
        protected String defaultAction(TypeMirror e, Object val);
    }

    @Pure
    public boolean isEnclosingPackageIncluded(TypeElement te);

    @Pure
    public boolean isIncluded(Element e);

    @Pure
    public boolean isSpecified(Element e);

    public String getPackageName(PackageElement pkg);

    public String getModuleName(ModuleElement mdle);

    public CommentHelper getCommentHelper(Element element);

    public void removeCommentHelper(Element element);

    public List<? extends DocTree> getBlockTags(Element element);

    public List<? extends DocTree> getBlockTags(DocCommentTree dcTree);

    public List<? extends BlockTagTree> getBlockTags(Element element, Predicate<? super BlockTagTree> filter);

    public <T extends BlockTagTree> List<T> getBlockTags(Element element, Predicate<? super BlockTagTree> filter, Class<T> tClass);

    public List<? extends BlockTagTree> getBlockTags(Element element, DocTree.Kind kind);

    public <T extends BlockTagTree> List<? extends T> getBlockTags(Element element, DocTree.Kind kind, Class<T> tClass);

    public List<? extends BlockTagTree> getBlockTags(Element element, String tagName);

    @Pure
    public boolean hasBlockTag(Element element, DocTree.Kind kind);

    @Pure
    public boolean hasBlockTag(Element element, DocTree.Kind kind, final String tagName);

    boolean hasBlockTagUnchecked(Element element, DocTree.Kind kind);

    public TreePath getTreePath(Element e);

    @Pure
    public boolean hasDocCommentTree(Element element);

    public DocCommentTree getDocCommentTree0(Element element);

    public void checkJavaScriptInOption(String name, String value);

    public DocCommentTree getDocCommentTree(Element element);

    public List<? extends DocTree> getPreamble(Element element);

    public List<? extends DocTree> getFullBody(Element element);

    public List<? extends DocTree> getBody(Element element);

    public List<? extends DeprecatedTree> getDeprecatedTrees(Element element);

    public List<? extends ProvidesTree> getProvidesTrees(Element element);

    public List<? extends SeeTree> getSeeTrees(Element element);

    public List<? extends SerialTree> getSerialTrees(Element element);

    public List<? extends SerialFieldTree> getSerialFieldTrees(VariableElement field);

    public List<? extends SpecTree> getSpecTrees(Element element);

    public List<ThrowsTree> getThrowsTrees(Element element);

    public List<ParamTree> getTypeParamTrees(Element element);

    public List<ParamTree> getParamTrees(Element element);

    public List<? extends ReturnTree> getReturnTrees(Element element);

    public List<? extends UsesTree> getUsesTrees(Element element);

    public List<? extends DocTree> getFirstSentenceTrees(Element element);

    public ModuleElement containingModule(Element e);

    public PackageElement containingPackage(Element e);

    private static class CommentHelperCache {

        public CommentHelperCache(Utils utils) {
        }

        public CommentHelper remove(Element key);

        public CommentHelper put(Element key, CommentHelper value);

        public CommentHelper get(Element key);

        public CommentHelper computeIfAbsent(Element key);
    }

    public static class Pair<K, L> {

        public final K first;

        public final L second;

        public Pair(K first, L second) {
        }

        @Override
        public String toString();
    }

    public Set<DeclarationPreviewLanguageFeatures> previewLanguageFeaturesUsed(Element e);

    public enum DeclarationPreviewLanguageFeatures {

        NONE(List.of(""));

        public final List<String> features;
    }

    public PreviewSummary declaredUsingPreviewAPIs(Element el);

    public static final class PreviewSummary {

        public final Set<TypeElement> previewAPI;

        public final Set<TypeElement> reflectivePreviewAPI;

        public final Set<TypeElement> declaredUsingPreviewFeature;

        public PreviewSummary(Set<TypeElement> previewAPI, Set<TypeElement> reflectivePreviewAPI, Set<TypeElement> declaredUsingPreviewFeature) {
        }

        @Override
        public String toString();
    }

    @Pure
    public boolean isPreviewAPI(Element el);

    @Pure
    public boolean isReflectivePreviewAPI(Element el);

    public boolean isRestrictedAPI(Element el);

    public Set<ElementFlag> elementFlags(Element el);

    public enum ElementFlag {

        DEPRECATED, PREVIEW, RESTRICTED
    }

    public PreviewFlagProvider setPreviewFlagProvider(PreviewFlagProvider provider);

    public interface PreviewFlagProvider {

        boolean isPreview(Element el);
    }

    public DocFinder docFinder();

    private class Overrides implements Iterator<ExecutableElement> {

        public Overrides(ExecutableElement method) {
        }

        @Override
        public boolean hasNext();

        @Override
        public ExecutableElement next();
    }

    public static String diagnosticDescriptionOf(Element e);
}
