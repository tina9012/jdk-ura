package org.checkerframework.checker.index.qual;

import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
@PostconditionAnnotation(qualifier = LTLengthOf.class)
@InheritedAnnotation
@Repeatable(EnsuresLTLengthOf.List.class)
public @interface EnsuresLTLengthOf {

    @JavaExpression
    String[] value();

    @JavaExpression
    @QualifierArgument("value")
    String[] targetValue();

    @JavaExpression
    @QualifierArgument("offset")
    String[] offset() default {};

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
    @PostconditionAnnotation(qualifier = LTLengthOf.class)
    @InheritedAnnotation
    public static @interface List {

        EnsuresLTLengthOf[] value();
    }
}
