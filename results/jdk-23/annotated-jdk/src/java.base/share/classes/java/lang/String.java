/*
 * Copyright (c) 1994, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.lang;

import org.checkerframework.checker.formatter.qual.FormatMethod;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.IndexOrLow;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.index.qual.SameLen;
import org.checkerframework.checker.index.qual.SubstringIndexFor;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.NewObject;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.PolyRegex;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.checker.signature.qual.PolySignature;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.common.aliasing.qual.Unique;
import org.checkerframework.common.value.qual.ArrayLen;
import org.checkerframework.common.value.qual.ArrayLenRange;
import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.common.value.qual.PolyValue;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.checkerframework.common.value.qual.StringVal;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.ObjectStreamField;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Native;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandles;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.util.ArraysSupport;
import jdk.internal.util.Preconditions;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import jdk.internal.vm.annotation.Stable;
import sun.nio.cs.ArrayDecoder;
import sun.nio.cs.ArrayEncoder;
import sun.nio.cs.ISO_8859_1;
import sun.nio.cs.US_ASCII;
import sun.nio.cs.UTF_8;

@AnnotatedFor({ "aliasing", "formatter", "index", "initialization", "interning", "lock", "nullness", "regex", "signature", "signedness" })
public final class String implements java.io.Serializable, Comparable<String>, CharSequence, Constable, ConstantDesc {

    @SideEffectFree
    @StaticallyExecutable
    @StringVal("")
    @Unique
    public String() {
    }

    @SideEffectFree
    @StaticallyExecutable
    @IntrinsicCandidate
    @PolyValue
    @Unique
    public String(@PolyValue String original) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @PolyValue
    @Unique
    public String(char @GuardSatisfied @PolyValue [] value) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(char @GuardSatisfied [] value, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(int @GuardSatisfied [] codePoints, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Deprecated()
    @Unique
    public String(byte @GuardSatisfied [] ascii, int hibyte, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Deprecated()
    @Unique
    public String(byte @GuardSatisfied [] ascii, int hibyte) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length, String charsetName) throws UnsupportedEncodingException {
    }

    @SideEffectFree
    @StaticallyExecutable
    @SuppressWarnings("removal")
    @Unique
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length, Charset charset) {
    }

    static String newStringUTF8NoRepl(byte[] bytes, int offset, int length, boolean noShare);

    static String newStringNoRepl(byte[] src, Charset cs) throws CharacterCodingException;

    static byte[] getBytesUTF8NoRepl(String s);

    static byte[] getBytesNoRepl(String s, Charset cs) throws CharacterCodingException;

    static int decodeASCII(byte[] sa, int sp, char[] da, int dp, int len);

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@PolySigned byte @GuardSatisfied [] bytes, String charsetName) throws UnsupportedEncodingException {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@PolySigned byte @GuardSatisfied [] bytes, Charset charset) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@PolySigned byte @GuardSatisfied [] bytes, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int length) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@PolySigned byte @GuardSatisfied [] bytes) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@GuardSatisfied StringBuffer buffer) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Unique
    public String(@GuardSatisfied StringBuilder builder) {
    }

    @Pure
    @StaticallyExecutable
    @LengthOf({ "this" })
    public int length();

    @SuppressWarnings("contracts.conditional.postcondition.not.satisfied")
    @CFComment("index: The postcondition is EnsuresMinLenIf.  It's true because: values.length != 0 => this is @MinLen(1), as values.length is @LengthOf(this).")
    @Pure
    @StaticallyExecutable
    @EnsuresMinLenIf(expression = "this", result = false, targetValue = 1)
    @Override
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Pure
    @StaticallyExecutable
    public char charAt(@IndexFor({ "this" }) int index);

    @Pure
    @StaticallyExecutable
    public int codePointAt(@IndexFor({ "this" }) int index);

    @Pure
    @StaticallyExecutable
    public int codePointBefore(@LTEqLengthOf({ "this" }) @Positive int index);

    @Pure
    @StaticallyExecutable
    @NonNegative
    public int codePointCount(@IndexOrHigh({ "this" }) int beginIndex, @IndexOrHigh({ "this" }) int endIndex);

    @Pure
    @StaticallyExecutable
    @IndexOrHigh({ "this" })
    public int offsetByCodePoints(@IndexOrHigh({ "this" }) int index, int codePointOffset);

    public void getChars(@IndexOrHigh({ "this" }) int srcBegin, @IndexOrHigh({ "this" }) int srcEnd, char @GuardSatisfied [] dst, @IndexOrHigh({ "#3" }) int dstBegin);

    @Deprecated()
    public void getBytes(@IndexOrHigh({ "this" }) int srcBegin, @IndexOrHigh({ "this" }) int srcEnd, byte @GuardSatisfied [] dst, @IndexOrHigh({ "#3" }) int dstBegin);

    @SideEffectFree
    @StaticallyExecutable
    @PolySigned
    public byte[] getBytes(String charsetName) throws UnsupportedEncodingException;

    @SideEffectFree
    @StaticallyExecutable
    @PolySigned
    public byte[] getBytes(Charset charset);

    @SideEffectFree
    @StaticallyExecutable
    @PolySigned
    public byte[] getBytes();

    boolean bytesCompatible(Charset charset);

    void copyToSegmentRaw(MemorySegment segment, long offset);

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    @StaticallyExecutable
    public boolean equals(@GuardSatisfied @Nullable Object anObject);

    @Pure
    @StaticallyExecutable
    public boolean contentEquals(@GuardSatisfied StringBuffer sb);

    @Pure
    @StaticallyExecutable
    public boolean contentEquals(@GuardSatisfied CharSequence cs);

    @EnsuresNonNullIf(expression = { "#1" }, result = true)
    @Pure
    @StaticallyExecutable
    public boolean equalsIgnoreCase(@Nullable String anotherString);

    @Pure
    @StaticallyExecutable
    public int compareTo(String anotherString);

    public static final Comparator<String> CASE_INSENSITIVE_ORDER;

    private static class CaseInsensitiveComparator implements Comparator<String>, java.io.Serializable {

        public int compare(String s1, String s2);
    }

    @Pure
    @StaticallyExecutable
    public int compareToIgnoreCase(String str);

    @Pure
    @StaticallyExecutable
    public boolean regionMatches(int toffset, String other, int ooffset, int len);

    @Pure
    @StaticallyExecutable
    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len);

    @Pure
    @StaticallyExecutable
    public boolean startsWith(String prefix, int toffset);

    @Pure
    @StaticallyExecutable
    public boolean startsWith(String prefix);

    @Pure
    @StaticallyExecutable
    public boolean endsWith(String suffix);

    @Pure
    @StaticallyExecutable
    public int hashCode();

    @Pure
    @StaticallyExecutable
    @IndexOrLow({ "this" })
    public int indexOf(int ch);

    @Pure
    @StaticallyExecutable
    @IndexOrLow({ "this" })
    public int indexOf(int ch, int fromIndex);

    @IndexOrLow({ "this" })
    public int indexOf(int ch, int beginIndex, int endIndex);

    @Pure
    @StaticallyExecutable
    @IndexOrLow({ "this" })
    public int lastIndexOf(int ch);

    @Pure
    @StaticallyExecutable
    @IndexOrLow({ "this" })
    public int lastIndexOf(int ch, int fromIndex);

    @Pure
    @StaticallyExecutable
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int indexOf(String str);

    @Pure
    @StaticallyExecutable
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int indexOf(String str, int fromIndex);

    public int indexOf(String str, int beginIndex, int endIndex);

    static int indexOf(byte[] src, byte srcCoder, int srcCount, String tgtStr, int fromIndex);

    @Pure
    @StaticallyExecutable
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int lastIndexOf(String str);

    @Pure
    @StaticallyExecutable
    @LTEqLengthOf({ "this" })
    @SubstringIndexFor(value = { "this" }, offset = { "#1.length()-1" })
    public int lastIndexOf(String str, int fromIndex);

    static int lastIndexOf(byte[] src, byte srcCoder, int srcCount, String tgtStr, int fromIndex);

    @SideEffectFree
    @StaticallyExecutable
    public String substring(@IndexOrHigh({ "this" }) int beginIndex);

    @SideEffectFree
    @StaticallyExecutable
    public String substring(@IndexOrHigh({ "this" }) int beginIndex, @IndexOrHigh({ "this" }) int endIndex);

    @SideEffectFree
    @StaticallyExecutable
    public CharSequence subSequence(@IndexOrHigh({ "this" }) int beginIndex, @IndexOrHigh({ "this" }) int endIndex);

    @SideEffectFree
    @StaticallyExecutable
    public String concat(String str);

    @SideEffectFree
    @StaticallyExecutable
    public String replace(char oldChar, char newChar);

    @SideEffectFree
    @StaticallyExecutable
    public boolean matches(@Regex String regex);

    @Pure
    @StaticallyExecutable
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(CharSequence s);

    @SideEffectFree
    @StaticallyExecutable
    public String replaceFirst(@Regex String regex, String replacement);

    @SideEffectFree
    @StaticallyExecutable
    public String replaceAll(@Regex String regex, String replacement);

    @SideEffectFree
    @StaticallyExecutable
    public String replace(@GuardSatisfied CharSequence target, @GuardSatisfied CharSequence replacement);

    @SideEffectFree
    @StaticallyExecutable
    public String @MinLen(1) [] split(@Regex String regex, int limit);

    public String @MinLen(1) [] splitWithDelimiters(@Regex String regex, int limit);

    @SideEffectFree
    @StaticallyExecutable
    public String @MinLen(1) [] split(@Regex String regex);

    @SideEffectFree
    @StaticallyExecutable
    public static String join(CharSequence delimiter, CharSequence... elements);

    @ForceInline
    static String join(String prefix, String suffix, String delimiter, String[] elements, int size);

    @SideEffectFree
    @StaticallyExecutable
    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements);

    @SideEffectFree
    @StaticallyExecutable
    public String toLowerCase(@GuardSatisfied Locale locale);

    @SideEffectFree
    @StaticallyExecutable
    public String toLowerCase();

    @SideEffectFree
    @StaticallyExecutable
    public String toUpperCase(@GuardSatisfied Locale locale);

    @SideEffectFree
    @StaticallyExecutable
    public String toUpperCase();

    @SideEffectFree
    @StaticallyExecutable
    public String trim();

    @SideEffectFree
    @StaticallyExecutable
    public String strip();

    @SideEffectFree
    @StaticallyExecutable
    public String stripLeading();

    @SideEffectFree
    @StaticallyExecutable
    public String stripTrailing();

    @Pure
    @StaticallyExecutable
    public boolean isBlank();

    public Stream<String> lines();

    @CFComment("n may be negative")
    @SideEffectFree
    public String indent(int n);

    @SideEffectFree
    public String stripIndent();

    @SideEffectFree
    public String translateEscapes();

    public <R> R transform(Function<? super String, ? extends R> f);

    @Pure
    @StaticallyExecutable
    @SameLen({ "this" })
    @PolyRegex
    @PolyValue
    public String toString(@PolyRegex @PolyValue String this);

    @Override
    public IntStream chars();

    @Override
    public IntStream codePoints();

    @SideEffectFree
    @StaticallyExecutable
    @PolySigned
    public char @SameLen({ "this" }) @PolyValue [] toCharArray(@PolyValue String this);

    @SideEffectFree
    @StaticallyExecutable
    @FormatMethod
    public static String format(String format, @GuardSatisfied @Nullable Object@GuardSatisfied ... args);

    @SideEffectFree
    @StaticallyExecutable
    @FormatMethod
    public static String format(@GuardSatisfied @Nullable Locale l, String format, @GuardSatisfied @Nullable Object@GuardSatisfied ... args);

    @SideEffectFree
    public String formatted(Object... args);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    public static String valueOf(@GuardSatisfied @Nullable Object obj);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    @SameLen({ "#1" })
    @PolyValue
    public static String valueOf(char @GuardSatisfied @PolyValue [] data);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    public static String valueOf(char @GuardSatisfied [] data, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count);

    @SideEffectFree
    @StaticallyExecutable
    public static String copyValueOf(char @GuardSatisfied [] data, @IndexOrHigh({ "#1" }) int offset, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int count);

    @SideEffectFree
    @StaticallyExecutable
    @SameLen({ "#1" })
    @PolyValue
    public static String copyValueOf(char @GuardSatisfied @PolyValue [] data);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    @StringVal({ "true", "false" })
    public static String valueOf(boolean b);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    @ArrayLen(1)
    public static String valueOf(char c);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    @ArrayLenRange(from = 1, to = 11)
    public static String valueOf(int i);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    @ArrayLenRange(from = 1, to = 20)
    public static String valueOf(long l);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    public static String valueOf(float f);

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    public static String valueOf(double d);

    @Pure
    @StaticallyExecutable
    @Interned
    @SameLen({ "this" })
    @PolyRegex
    @PolySignature
    @PolyValue
    public native String intern(@PolyRegex @PolySignature @PolyValue String this);

    @SideEffectFree
    @StaticallyExecutable
    public String repeat(int count);

    static void repeatCopyRest(byte[] buffer, int offset, int limit, int copied);

    void getBytes(byte[] dst, int dstBegin, byte coder);

    void getBytes(byte[] dst, int srcPos, int dstBegin, byte coder, int length);

    byte coder();

    byte[] value();

    boolean isLatin1();

    static void checkIndex(int index, int length);

    static void checkOffset(int offset, int length);

    static int checkBoundsOffCount(int offset, int count, int length);

    static void checkBoundsBeginEnd(int begin, int end, int length);

    static String valueOfCodePoint(int codePoint);

    @Override
    public Optional<String> describeConstable();

    @Override
    public String resolveConstantDesc(MethodHandles.Lookup lookup);
}
