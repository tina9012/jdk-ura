/*
 * Copyright (c) 2017, 2023, Oracle and/or its affiliates. All rights reserved.
 */
package com.sun.org.apache.bcel.internal.generic;

import org.checkerframework.dataflow.qual.Pure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.AccessFlags;
import com.sun.org.apache.bcel.internal.classfile.Annotations;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.RuntimeInvisibleAnnotations;
import com.sun.org.apache.bcel.internal.classfile.RuntimeVisibleAnnotations;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.BCELComparator;

public class ClassGen extends AccessFlags implements Cloneable {

    public static BCELComparator getComparator();

    public static void setComparator(final BCELComparator comparator);

    public ClassGen(final JavaClass clazz) {
    }

    public ClassGen(final String className, final String superClassName, final String fileName, final int accessFlags, final String[] interfaces) {
    }

    public ClassGen(final String className, final String superClassName, final String fileName, final int accessFlags, final String[] interfaces, final ConstantPoolGen cp) {
    }

    public void addAnnotationEntry(final AnnotationEntryGen a);

    public void addAttribute(final Attribute a);

    public void addEmptyConstructor(final int accessFlags);

    public void addField(final Field f);

    public void addInterface(final String name);

    public void addMethod(final Method m);

    public void addObserver(final ClassObserver o);

    @Override
    public Object clone();

    @Pure
    public boolean containsField(final Field f);

    @Pure
    public Field containsField(final String name);

    public Method containsMethod(final String name, final String signature);

    @Override
    public boolean equals(final Object obj);

    public AnnotationEntryGen[] getAnnotationEntries();

    public Attribute[] getAttributes();

    public String getClassName();

    public int getClassNameIndex();

    public ConstantPoolGen getConstantPool();

    public Field[] getFields();

    public String getFileName();

    public String[] getInterfaceNames();

    public int[] getInterfaces();

    public JavaClass getJavaClass();

    public int getMajor();

    public Method getMethodAt(final int pos);

    public Method[] getMethods();

    public int getMinor();

    public String getSuperclassName();

    public int getSuperclassNameIndex();

    @Override
    public int hashCode();

    public void removeAttribute(final Attribute a);

    public void removeField(final Field f);

    public void removeInterface(final String name);

    public void removeMethod(final Method m);

    public void removeObserver(final ClassObserver o);

    public void replaceField(final Field old, final Field newField);

    public void replaceMethod(final Method old, final Method newMethod);

    public void setClassName(final String name);

    public void setClassNameIndex(final int classNameIndex);

    public void setConstantPool(final ConstantPoolGen constantPool);

    public void setMajor(final int major);

    public void setMethodAt(final Method method, final int pos);

    public void setMethods(final Method[] methods);

    public void setMinor(final int minor);

    public void setSuperclassName(final String name);

    public void setSuperclassNameIndex(final int superclassNameIndex);

    public void update();
}
