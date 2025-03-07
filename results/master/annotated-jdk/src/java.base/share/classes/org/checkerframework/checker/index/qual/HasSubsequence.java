package org.checkerframework.checker.index.qual;

import org.checkerframework.framework.qual.JavaExpression;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface HasSubsequence {

    @JavaExpression
    String subsequence();

    @JavaExpression
    String from();

    @JavaExpression
    String to();
}
