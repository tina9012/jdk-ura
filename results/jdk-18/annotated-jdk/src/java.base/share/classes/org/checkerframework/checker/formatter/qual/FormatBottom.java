package org.checkerframework.checker.formatter.qual;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
@TargetLocations({ TypeUseLocation.LOWER_BOUND, TypeUseLocation.UPPER_BOUND })
@SubtypeOf({ Format.class, InvalidFormat.class })
@DefaultFor(value = { TypeUseLocation.LOWER_BOUND })
public @interface FormatBottom {
}
