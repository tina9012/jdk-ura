package org.checkerframework.checker.signedness.qual;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeKind;
import org.checkerframework.framework.qual.TypeUseLocation;
import org.checkerframework.framework.qual.UpperBoundFor;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
@SubtypeOf({ UnknownSignedness.class })
@DefaultQualifierInHierarchy
@DefaultFor(typeKinds = { TypeKind.BYTE, TypeKind.INT, TypeKind.LONG, TypeKind.SHORT, TypeKind.FLOAT, TypeKind.DOUBLE }, types = { java.lang.Byte.class, java.lang.Integer.class, java.lang.Long.class, java.lang.Short.class, java.lang.Float.class, java.lang.Double.class }, value = TypeUseLocation.EXCEPTION_PARAMETER)
@UpperBoundFor(typeKinds = { TypeKind.FLOAT, TypeKind.DOUBLE }, types = { java.lang.Float.class, java.lang.Double.class })
public @interface Signed {
}
