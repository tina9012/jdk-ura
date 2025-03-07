package org.checkerframework.checker.initialization.qual;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.SubtypeOf;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
@SubtypeOf(UnknownInitialization.class)
public @interface UnderInitialization {

    Class<?> value() default Object.class;
}
