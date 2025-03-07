package org.checkerframework.framework.testchecker.variablenamedefault.quals;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** VariableNameDefault bottom qualifier. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(VariableNameDefaultMiddle.class)
@DefaultFor(names = ".*bottom.*", namesExceptions = ".*notbottom.*")
public @interface VariableNameDefaultBottom {}
