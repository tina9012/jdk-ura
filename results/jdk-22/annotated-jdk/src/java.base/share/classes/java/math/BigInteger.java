/*
 * Copyright (c) 1996, 2023, Oracle and/or its affiliates. All rights reserved.
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
package java.math;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;
import org.checkerframework.common.value.qual.PolyValue;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.ObjectStreamException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import jdk.internal.math.DoubleConsts;
import jdk.internal.math.FloatConsts;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import jdk.internal.vm.annotation.Stable;

@AnnotatedFor({ "initialization", "nullness", "value" })
public class BigInteger extends Number implements Comparable<BigInteger> {

    public BigInteger(byte[] val, int off, int len) {
    }

    public BigInteger(byte[] val) {
    }

    public BigInteger(@IntRange(from = -1, to = 1) int signum, byte[] magnitude, int off, int len) {
    }

    public BigInteger(@IntRange(from = -1, to = 1) int signum, byte[] magnitude) {
    }

    public BigInteger(String val, @IntRange(from = 2, to = 36) int radix) {
    }

    public BigInteger(String val) {
    }

    public BigInteger(int numBits, Random rnd) {
    }

    public BigInteger(int bitLength, int certainty, Random rnd) {
    }

    public static BigInteger probablePrime(int bitLength, Random rnd);

    public BigInteger nextProbablePrime();

    boolean primeToCertainty(int certainty, Random random);

    public static BigInteger valueOf(long val);

    public static final BigInteger ZERO;

    public static final BigInteger ONE;

    public static final BigInteger TWO;

    public static final BigInteger TEN;

    public BigInteger add(BigInteger val);

    BigInteger add(long val);

    public BigInteger subtract(BigInteger val);

    public BigInteger multiply(BigInteger val);

    public BigInteger parallelMultiply(BigInteger val);

    BigInteger multiply(long v);

    @SuppressWarnings("serial")
    private abstract static sealed class RecursiveOp extends RecursiveTask<BigInteger> {

        protected RecursiveTask<BigInteger> forkOrInvoke();

        @SuppressWarnings("serial")
        private static final class RecursiveMultiply extends RecursiveOp {

            public RecursiveMultiply(BigInteger a, BigInteger b, boolean parallel, int depth) {
            }

            @Override
            public BigInteger compute();
        }

        @SuppressWarnings("serial")
        private static final class RecursiveSquare extends RecursiveOp {

            public RecursiveSquare(BigInteger a, boolean parallel, int depth) {
            }

            @Override
            public BigInteger compute();
        }
    }

    public BigInteger divide(BigInteger val);

    public BigInteger[] divideAndRemainder(BigInteger val);

    public BigInteger remainder(BigInteger val);

    public BigInteger pow(int exponent);

    public BigInteger sqrt();

    public BigInteger[] sqrtAndRemainder();

    public BigInteger gcd(BigInteger val);

    static int bitLengthForInt(int n);

    static void primitiveRightShift(int[] a, int len, int n);

    static void primitiveLeftShift(int[] a, int len, int n);

    public BigInteger abs();

    public BigInteger negate();

    @IntRange(from = -1, to = 1)
    public int signum();

    public BigInteger mod(BigInteger m);

    public BigInteger modPow(BigInteger exponent, BigInteger m);

    static int mulAdd(int[] out, int[] in, int offset, int len, int k);

    static int addOne(int[] a, int offset, int mlen, int carry);

    public BigInteger modInverse(BigInteger m);

    public BigInteger shiftLeft(int n);

    public BigInteger shiftRight(int n);

    int[] javaIncrement(int[] val);

    public BigInteger and(BigInteger val);

    public BigInteger or(BigInteger val);

    public BigInteger xor(BigInteger val);

    public BigInteger not();

    public BigInteger andNot(BigInteger val);

    public boolean testBit(int n);

    public BigInteger setBit(int n);

    public BigInteger clearBit(int n);

    public BigInteger flipBit(int n);

    public int getLowestSetBit();

    public int bitLength();

    public int bitCount();

    public boolean isProbablePrime(int certainty);

    @IntRange(from = -1, to = 1)
    public int compareTo(BigInteger val);

    @IntRange(from = -1, to = 1)
    final int compareMagnitude(BigInteger val);

    @IntRange(from = -1, to = 1)
    final int compareMagnitude(long val);

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object x);

    @Pure
    @StaticallyExecutable
    public BigInteger min(BigInteger val);

    @Pure
    @StaticallyExecutable
    public BigInteger max(BigInteger val);

    public int hashCode();

    public String toString(@IntRange(from = 2, to = 36) int radix);

    public String toString();

    public byte[] toByteArray();

    @PolyValue
    public int intValue(@PolyValue BigInteger this);

    @PolyValue
    public long longValue(@PolyValue BigInteger this);

    @PolyValue
    public float floatValue(@PolyValue BigInteger this);

    @PolyValue
    public double doubleValue(@PolyValue BigInteger this);

    private static class UnsafeHolder {

        static void putSignAndMag(BigInteger bi, int sign, int[] magnitude);
    }

    public long longValueExact();

    public int intValueExact();

    public short shortValueExact();

    public byte byteValueExact();
}
