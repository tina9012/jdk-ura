package org.checkerframework.framework.testchecker.h1h2checker;

import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;

import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H1Bot;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H1Invalid;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H1Poly;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H1S1;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H1S2;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H1Top;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H2Bot;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H2OnlyOnLB;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H2Poly;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H2S1;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H2S2;
import org.checkerframework.framework.testchecker.h1h2checker.quals.H2Top;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.javacutil.AnnotationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;

public class H1H2AnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

    AnnotationMirror H1S2;

    /**
     * Creates a new H1H2AnnotatedTypeFactory.
     *
     * @param checker the checker
     */
    public H1H2AnnotatedTypeFactory(BaseTypeChecker checker) {
        super(checker);
        this.postInit();
        H1S2 = AnnotationBuilder.fromClass(elements, H1S2.class);
    }

    @Override
    protected Set<Class<? extends Annotation>> createSupportedTypeQualifiers() {
        return getBundledTypeQualifiers(
                H1Top.class,
                H1S1.class,
                H1S2.class,
                H1Bot.class,
                H2Top.class,
                H2S1.class,
                H2S2.class,
                H2Bot.class,
                H1Poly.class,
                H2Poly.class,
                H2OnlyOnLB.class,
                H1Invalid.class);
    }

    @Override
    protected void addComputedTypeAnnotations(Tree tree, AnnotatedTypeMirror type) {
        super.addComputedTypeAnnotations(tree, type);
        if (tree.getKind() == Tree.Kind.VARIABLE
                && ((VariableTree) tree).getName().toString().contains("addH1S2")) {
            type.replaceAnnotation(H1S2);
        }
    }
}
