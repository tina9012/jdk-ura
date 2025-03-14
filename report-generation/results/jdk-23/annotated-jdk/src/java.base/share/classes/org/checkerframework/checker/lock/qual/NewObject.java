package org.checkerframework.checker.lock.qual;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.QualifierForLiterals;
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
@TargetLocations({ TypeUseLocation.LOWER_BOUND, TypeUseLocation.UPPER_BOUND, TypeUseLocation.CONSTRUCTOR_RESULT, TypeUseLocation.RETURN })
@SubtypeOf({ GuardedBy.class, GuardSatisfied.class })
@DefaultFor(TypeUseLocation.CONSTRUCTOR_RESULT)
@QualifierForLiterals({ LiteralKind.STRING, LiteralKind.PRIMITIVE })
public @interface NewObject {
}
