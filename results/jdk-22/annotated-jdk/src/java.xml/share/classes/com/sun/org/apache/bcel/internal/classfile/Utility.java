/*
 * Copyright (c) 2017, 2023, Oracle and/or its affiliates. All rights reserved.
 */
package com.sun.org.apache.bcel.internal.classfile;

import org.checkerframework.checker.signedness.qual.PolySigned;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

public abstract class Utility {

    private static class JavaReader extends FilterReader {

        public JavaReader(final Reader in) {
        }

        @Override
        public int read() throws IOException;

        @Override
        public int read(final char[] cbuf, final int off, final int len) throws IOException;
    }

    private static class JavaWriter extends FilterWriter {

        public JavaWriter(final Writer out) {
        }

        @Override
        public void write(final char[] cbuf, final int off, final int len) throws IOException;

        @Override
        public void write(final int b) throws IOException;

        @Override
        public void write(final String str, final int off, final int len) throws IOException;
    }

    public static String accessToString(final int accessFlags);

    public static String accessToString(final int accessFlags, final boolean forClass);

    public static String classOrInterface(final int accessFlags);

    public static int clearBit(final int flag, final int i);

    public static String codeToString(final byte[] code, final ConstantPool constantPool, final int index, final int length);

    public static String codeToString(final byte[] code, final ConstantPool constantPool, final int index, final int length, final boolean verbose);

    public static String codeToString(final ByteSequence bytes, final ConstantPool constantPool) throws IOException;

    @SuppressWarnings("fallthrough")
    public static String codeToString(final ByteSequence bytes, final ConstantPool constantPool, final boolean verbose) throws IOException;

    public static String compactClassName(final String str);

    public static String compactClassName(final String str, final boolean chopit);

    public static String compactClassName(String str, final String prefix, final boolean chopit);

    public static String convertString(final String label);

    public static byte[] decode(final String s, final boolean uncompress) throws IOException;

    public static String encode(byte[] bytes, final boolean compress) throws IOException;

    public static String fillup(final String str, final int length, final boolean leftJustify, final char fill);

    public static String format(final int i, final int length, final boolean leftJustify, final char fill);

    public static String getSignature(String type);

    public static boolean isJavaIdentifierPart(final char ch);

    public static boolean isSet(final int flag, final int i);

    public static String[] methodSignatureArgumentTypes(final String signature) throws ClassFormatException;

    public static String[] methodSignatureArgumentTypes(final String signature, final boolean chopit) throws ClassFormatException;

    public static String methodSignatureReturnType(final String signature) throws ClassFormatException;

    public static String methodSignatureReturnType(final String signature, final boolean chopit) throws ClassFormatException;

    public static String methodSignatureToString(final String signature, final String name, final String access);

    public static String methodSignatureToString(final String signature, final String name, final String access, final boolean chopit);

    public static String methodSignatureToString(final String signature, final String name, final String access, final boolean chopit, final LocalVariableTable vars) throws ClassFormatException;

    public static String methodTypeToSignature(final String ret, final String[] argv) throws ClassFormatException;

    public static String packageToPath(final String name);

    public static String pathToPackage(final String str);

    public static String printArray(final Object[] obj);

    public static String printArray(final Object[] obj, final boolean braces);

    public static String printArray(final Object[] obj, final boolean braces, final boolean quote);

    public static void printArray(final PrintStream out, final Object[] obj);

    public static void printArray(final PrintWriter out, final Object[] obj);

    public static String replace(String str, final String old, final String new_);

    public static short searchOpcode(String name);

    public static int setBit(final int flag, final int i);

    public static String signatureToString(final String signature);

    public static String signatureToString(final String signature, final boolean chopit);

    public static String toHexString(final byte[] bytes);

    public static byte typeOfMethodSignature(final String signature) throws ClassFormatException;

    public static byte typeOfSignature(final String signature) throws ClassFormatException;

    public static String typeSignatureToString(final String signature, final boolean chopit) throws ClassFormatException;
}
