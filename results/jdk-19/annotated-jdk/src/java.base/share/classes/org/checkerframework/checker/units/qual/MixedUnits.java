package org.checkerframework.checker.units.qual;

import org.checkerframework.framework.qual.SubtypeOf;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
@SubtypeOf(UnknownUnits.class)
public @interface MixedUnits {
}
