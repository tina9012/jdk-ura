package org.checkerframework.checker.interning.qual;

import org.checkerframework.framework.qual.InheritedAnnotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@InheritedAnnotation
public @interface InternMethod {
}
