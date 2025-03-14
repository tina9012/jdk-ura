package org.checkerframework.checker.mustcall.qual;

import org.checkerframework.checker.calledmethods.qual.CalledMethods;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@InheritedAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CreatesMustCallFor.List.class)
public @interface CreatesMustCallFor {

    @JavaExpression
    String value() default "this";

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD })
    @InheritedAnnotation
    public static @interface List {

        CreatesMustCallFor[] value();
    }
}
