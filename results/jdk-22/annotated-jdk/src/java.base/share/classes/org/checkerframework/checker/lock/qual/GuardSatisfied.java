package org.checkerframework.checker.lock.qual;

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
@Target(ElementType.TYPE_USE)
@TargetLocations({ TypeUseLocation.RECEIVER, TypeUseLocation.PARAMETER, TypeUseLocation.RETURN, TypeUseLocation.FIELD, TypeUseLocation.LOCAL_VARIABLE, TypeUseLocation.CONSTRUCTOR_RESULT })
@SubtypeOf(GuardedByUnknown.class)
public @interface GuardSatisfied {

    int value() default -1;
}
