package org.checkerframework.checker.nullness.qual;

import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
@PostconditionAnnotation(qualifier = NonNull.class)
@InheritedAnnotation
@Repeatable(EnsuresNonNull.List.class)
public @interface EnsuresNonNull {

    String[] value();

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
    @PostconditionAnnotation(qualifier = NonNull.class)
    @InheritedAnnotation
    public static @interface List {

        EnsuresNonNull[] value();
    }
}
