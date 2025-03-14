package org.checkerframework.checker.nullness.qual;

import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
@ConditionalPostconditionAnnotation(qualifier = NonNull.class)
@InheritedAnnotation
@Repeatable(EnsuresNonNullIf.List.class)
public @interface EnsuresNonNullIf {

    boolean result();

    String[] expression();

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
    @ConditionalPostconditionAnnotation(qualifier = NonNull.class)
    @InheritedAnnotation
    public static @interface List {

        EnsuresNonNullIf[] value();
    }
}
