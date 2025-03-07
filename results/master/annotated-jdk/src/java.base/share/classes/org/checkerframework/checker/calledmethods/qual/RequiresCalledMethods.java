package org.checkerframework.checker.calledmethods.qual;

import org.checkerframework.framework.qual.PreconditionAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@PreconditionAnnotation(qualifier = CalledMethods.class)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
@Repeatable(RequiresCalledMethods.List.class)
public @interface RequiresCalledMethods {

    String[] value();

    @QualifierArgument("value")
    String[] methods();

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @PreconditionAnnotation(qualifier = CalledMethods.class)
    @Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
    public static @interface List {

        RequiresCalledMethods[] value();
    }
}
