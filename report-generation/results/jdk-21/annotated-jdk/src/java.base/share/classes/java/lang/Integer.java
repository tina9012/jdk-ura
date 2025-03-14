/*
 * Copyright (c) 1994, 2023, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.PolyIndex;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.checker.lock.qual.NewObject;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.checker.signedness.qual.SignednessGlb;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.checker.signedness.qual.Unsigned;
import org.checkerframework.common.value.qual.ArrayLenRange;
import org.checkerframework.common.value.qual.IntRange;
import org.checkerframework.common.value.qual.IntVal;
import org.checkerframework.common.value.qual.PolyValue;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.misc.CDS;
import jdk.internal.misc.VM;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import java.lang.annotation.Native;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.Optional;
import static java.lang.String.COMPACT_STRINGS;
import static java.lang.String.LATIN1;
import static java.lang.String.UTF16;

@AnnotatedFor({ "index", "initialization", "nullness", "lock", "signedness", "value" })
@jdk.internal.ValueBased
public final class Integer extends Number implements Comparable<Integer>, Constable, ConstantDesc {

    @Native
    @SignednessGlb
    @IntVal(0x80000000)
    public static final int MIN_VALUE;

    @Native
    @SignedPositive
    @IntVal(0x7fffffff)
    public static final int MAX_VALUE;

    @SuppressWarnings("unchecked")
    public static final Class<Integer> TYPE;

    @CFComment("@IntRange(2, 36) int radix: the method uses 10 if radix is outside the valid range, but that is still probably an error, and other methods (like many methods in Integer, and Byte.toString) do throw an exception if the radix is outside the valid range")
    @SideEffectFree
    @StaticallyExecutable
    @ArrayLenRange(from = 1)
    public static String toString(int i, @Positive @IntRange(from = 2, to = 36) int radix);

    @CFComment("@IntRange(2, 36) int radix: see CFComment on toString")
    @SideEffectFree
    @StaticallyExecutable
    public static String toUnsignedString(@Unsigned int i, @Positive @IntRange(from = 2, to = 36) int radix);

    @SideEffectFree
    @StaticallyExecutable
    @ArrayLenRange(from = 1, to = 8)
    public static String toHexString(@UnknownSignedness int i);

    @SideEffectFree
    @StaticallyExecutable
    @ArrayLenRange(from = 1, to = 11)
    public static String toOctalString(@Unsigned int i);

    @SideEffectFree
    @StaticallyExecutable
    @ArrayLenRange(from = 1, to = 32)
    public static String toBinaryString(@Unsigned int i);

    @SideEffectFree
    @StaticallyExecutable
    @IntrinsicCandidate
    @ArrayLenRange(from = 1, to = 11)
    public static String toString(int i);

    @SideEffectFree
    @StaticallyExecutable
    public static String toUnsignedString(@Unsigned int i);

    static int getChars(int i, int index, byte[] buf);

    static int stringSize(int x);

    @Pure
    @StaticallyExecutable
    public static int parseInt(String s, @Positive @IntRange(from = 2, to = 36) int radix) throws NumberFormatException;

    @Pure
    @StaticallyExecutable
    public static int parseInt(CharSequence s, int beginIndex, int endIndex, @IntRange(from = 2, to = 36) int radix) throws NumberFormatException;

    @Pure
    @StaticallyExecutable
    public static int parseInt(String s) throws NumberFormatException;

    @Pure
    @StaticallyExecutable
    @Unsigned
    public static int parseUnsignedInt(String s, @Positive @IntRange(from = 2, to = 36) int radix) throws NumberFormatException;

    @Pure
    @StaticallyExecutable
    @Unsigned
    public static int parseUnsignedInt(CharSequence s, int beginIndex, int endIndex, @IntRange(from = 2, to = 36) int radix) throws NumberFormatException;

    @Pure
    @StaticallyExecutable
    @Unsigned
    public static int parseUnsignedInt(String s) throws NumberFormatException;

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    public static Integer valueOf(String s, @Positive @IntRange(from = 2, to = 36) int radix) throws NumberFormatException;

    @SideEffectFree
    @StaticallyExecutable
    @NewObject
    public static Integer valueOf(String s) throws NumberFormatException;

    private static class IntegerCache {
    }

    @SideEffectFree
    @StaticallyExecutable
    @IntrinsicCandidate
    @NewObject
    @PolyIndex
    @PolySigned
    @PolyValue
    public static Integer valueOf(@PolyIndex @PolySigned @PolyValue int i);

    @SideEffectFree
    @StaticallyExecutable
    @Deprecated()
    @PolyIndex
    @PolySigned
    @PolyValue
    public Integer(@PolyIndex @PolySigned @PolyValue int value) {
    }

    @SideEffectFree
    @StaticallyExecutable
    @Deprecated()
    public Integer(String s) throws NumberFormatException {
    }

    @Pure
    @StaticallyExecutable
    @PolyIndex
    @PolyValue
    public byte byteValue(@PolyIndex @PolyValue Integer this);

    @Pure
    @StaticallyExecutable
    @PolyIndex
    @PolyValue
    public short shortValue(@PolyIndex @PolyValue Integer this);

    @Pure
    @StaticallyExecutable
    @IntrinsicCandidate
    @PolyIndex
    @PolySigned
    @PolyValue
    public int intValue(@PolyIndex @PolySigned @PolyValue Integer this);

    @Pure
    @StaticallyExecutable
    @PolyIndex
    @PolySigned
    @PolyValue
    public long longValue(@PolyIndex @PolySigned @PolyValue Integer this);

    @Pure
    @StaticallyExecutable
    @PolyValue
    public float floatValue(@PolyValue Integer this);

    @Pure
    @StaticallyExecutable
    @PolyValue
    public double doubleValue(@PolyValue Integer this);

    @SideEffectFree
    @StaticallyExecutable
    @ArrayLenRange(from = 1, to = 11)
    public String toString();

    @Pure
    @StaticallyExecutable
    @Override
    public int hashCode();

    @Pure
    @StaticallyExecutable
    public static int hashCode(int value);

    @Pure
    @StaticallyExecutable
    public boolean equals(@Nullable Object obj);

    @SideEffectFree
    @StaticallyExecutable
    @Nullable
    public static Integer getInteger(@Nullable String nm);

    @SideEffectFree
    @StaticallyExecutable
    public static Integer getInteger(@Nullable String nm, int val);

    @SideEffectFree
    @StaticallyExecutable
    @PolyNull
    public static Integer getInteger(@Nullable String nm, @PolyNull Integer val);

    @SideEffectFree
    @StaticallyExecutable
    public static Integer decode(String nm) throws NumberFormatException;

    @Pure
    @StaticallyExecutable
    public int compareTo(Integer anotherInteger);

    @Pure
    @StaticallyExecutable
    public static int compare(int x, int y);

    @IntrinsicCandidate
    @Pure
    @StaticallyExecutable
    public static int compareUnsigned(@Unsigned int x, @Unsigned int y);

    @Pure
    @StaticallyExecutable
    @SignedPositive
    public static long toUnsignedLong(@UnknownSignedness int x);

    @IntrinsicCandidate
    @Pure
    @StaticallyExecutable
    @Unsigned
    public static int divideUnsigned(@Unsigned int dividend, @Unsigned int divisor);

    @IntrinsicCandidate
    @Pure
    @StaticallyExecutable
    @Unsigned
    public static int remainderUnsigned(@Unsigned int dividend, @Unsigned int divisor);

    @Native
    @SignedPositive
    @IntVal(32)
    public static final int SIZE;

    @SignedPositive
    @IntVal(4)
    public static final int BYTES;

    @Pure
    @StaticallyExecutable
    public static int highestOneBit(@UnknownSignedness int i);

    @Pure
    @StaticallyExecutable
    public static int lowestOneBit(@UnknownSignedness int i);

    @Pure
    @StaticallyExecutable
    @IntrinsicCandidate
    @NonNegative
    @IntRange(from = 0, to = 32)
    public static int numberOfLeadingZeros(@UnknownSignedness int i);

    @Pure
    @StaticallyExecutable
    @IntrinsicCandidate
    @NonNegative
    @IntRange(from = 0, to = 32)
    public static int numberOfTrailingZeros(@UnknownSignedness int i);

    @Pure
    @StaticallyExecutable
    @IntrinsicCandidate
    @NonNegative
    public static int bitCount(@UnknownSignedness int i);

    @Pure
    @StaticallyExecutable
    @PolySigned
    public static int rotateLeft(@PolySigned int i, int distance);

    @Pure
    @StaticallyExecutable
    @PolySigned
    public static int rotateRight(@PolySigned int i, int distance);

    @IntrinsicCandidate
    @Pure
    @StaticallyExecutable
    @SignednessGlb
    public static int reverse(@PolySigned int i);

    @IntrinsicCandidate
    public static int compress(int i, int mask);

    @IntrinsicCandidate
    public static int expand(int i, int mask);

    @Pure
    @StaticallyExecutable
    @GTENegativeOne
    public static int signum(int i);

    @Pure
    @StaticallyExecutable
    @IntrinsicCandidate
    @SignednessGlb
    public static int reverseBytes(@PolySigned int i);

    @Pure
    @StaticallyExecutable
    public static int sum(int a, int b);

    @Pure
    @StaticallyExecutable
    public static int max(int a, int b);

    @Pure
    @StaticallyExecutable
    public static int min(int a, int b);

    @Override
    public Optional<Integer> describeConstable();

    @Override
    public Integer resolveConstantDesc(MethodHandles.Lookup lookup);
}
