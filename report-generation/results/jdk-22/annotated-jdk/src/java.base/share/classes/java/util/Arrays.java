/*
 * Copyright (c) 1997, 2023, Oracle and/or its affiliates. All rights reserved.
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
package java.util;

import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.SearchIndexFor;
import org.checkerframework.checker.interning.qual.PolyInterned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.mustcall.qual.MustCallUnknown;
import org.checkerframework.checker.mustcall.qual.PolyMustCall;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.checker.signedness.qual.Unsigned;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.util.ArraysSupport;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor({ "index", "initialization", "interning", "lock", "nullness", "signedness" })
public final class Arrays {

    public static void sort(int[] a);

    public static void sort(int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(long[] a);

    public static void sort(long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(short[] a);

    public static void sort(short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(char[] a);

    public static void sort(char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(byte[] a);

    public static void sort(byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(float[] a);

    public static void sort(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void sort(double[] a);

    public static void sort(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(byte[] a);

    public static void parallelSort(byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(char[] a);

    public static void parallelSort(char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(short[] a);

    public static void parallelSort(short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(int[] a);

    public static void parallelSort(int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(long[] a);

    public static void parallelSort(long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(float[] a);

    public static void parallelSort(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    public static void parallelSort(double[] a);

    public static void parallelSort(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    static void rangeCheck(int arrayLength, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    static final class NaturalOrder implements Comparator<Object> {

        @SuppressWarnings("unchecked")
        public int compare(Object first, Object second);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void parallelSort(T[] a);

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void parallelSort(T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    @SuppressWarnings("unchecked")
    @CFComment("A comparator that can handle the array elements needs to be provided. Otherwise, use method without comparator.")
    public static <T> void parallelSort(@UnknownSignedness T[] a, Comparator<? super T> cmp);

    @SuppressWarnings("unchecked")
    @CFComment("A comparator that can handle the array elements needs to be provided. Otherwise, use method without comparator.")
    public static <T> void parallelSort(@UnknownSignedness T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, Comparator<? super T> cmp);

    static final class LegacyMergeSort {
    }

    public static void sort(@PolyInterned Object[] a);

    public static void sort(@PolyInterned Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex);

    @CFComment("A comparator that can handle the array elements needs to be provided. Otherwise, use method without comparator.")
    public static <T> void sort(@UnknownSignedness T[] a, Comparator<? super T> c);

    @CFComment("A comparator that can handle the array elements needs to be provided. Otherwise, use method without comparator.")
    public static <T> void sort(@UnknownSignedness T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, Comparator<? super T> c);

    public static <T> void parallelPrefix(T[] array, BinaryOperator<T> op);

    public static <T> void parallelPrefix(T[] array, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, BinaryOperator<T> op);

    public static void parallelPrefix(long[] array, LongBinaryOperator op);

    public static void parallelPrefix(long[] array, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, LongBinaryOperator op);

    public static void parallelPrefix(double[] array, DoubleBinaryOperator op);

    public static void parallelPrefix(double[] array, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, DoubleBinaryOperator op);

    public static void parallelPrefix(int[] array, IntBinaryOperator op);

    public static void parallelPrefix(int[] array, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, IntBinaryOperator op);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(long[] a, long key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, long key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(int[] a, int key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, int key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(short[] a, short key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, short key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(char[] a, char key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, char key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(byte[] a, byte key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, byte key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(double[] a, double key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, double key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(float[] a, float key);

    @SearchIndexFor({ "#1" })
    public static int binarySearch(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, float key);

    @CFComment("nullness: array components and key need to be non-null.")
    @SearchIndexFor({ "#1" })
    public static int binarySearch(@PolyInterned Object[] a, @PolyInterned Object key);

    @CFComment("nullness: array components and key need to be non-null.")
    @SearchIndexFor({ "#1" })
    public static int binarySearch(@PolyInterned Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolyInterned Object key);

    @CFComment("A comparator that can handle the array elements needs to be provided. Otherwise, use method without comparator.")
    @SearchIndexFor({ "#1" })
    public static <T> int binarySearch(@UnknownSignedness T[] a, T key, Comparator<? super T> c);

    @CFComment("A comparator that can handle the array elements needs to be provided. Otherwise, use method without comparator.")
    @SearchIndexFor({ "#1" })
    public static <T> int binarySearch(@UnknownSignedness T[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, T key, Comparator<? super T> c);

    @Pure
    public static boolean equals(@PolySigned long @Nullable [] a, @PolySigned long @Nullable [] a2);

    public static boolean equals(@PolySigned long[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, @PolySigned long[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    public static boolean equals(@PolySigned int @Nullable [] a, @PolySigned int @Nullable [] a2);

    public static boolean equals(@PolySigned int[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, @PolySigned int[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    public static boolean equals(@PolySigned short @Nullable [] a, @PolySigned short @Nullable [] a2);

    public static boolean equals(@PolySigned short[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, @PolySigned short[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    @IntrinsicCandidate
    public static boolean equals(@PolySigned char @Nullable [] a, @PolySigned char @Nullable [] a2);

    public static boolean equals(@PolySigned char[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, @PolySigned char[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    @IntrinsicCandidate
    public static boolean equals(@PolySigned byte @Nullable [] a, @PolySigned byte @Nullable [] a2);

    public static boolean equals(@PolySigned byte[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, @PolySigned byte[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    public static boolean equals(boolean @Nullable [] a, boolean @Nullable [] a2);

    public static boolean equals(boolean[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, boolean[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    public static boolean equals(double @Nullable [] a, double @Nullable [] a2);

    public static boolean equals(double[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, double[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    public static boolean equals(float @Nullable [] a, float @Nullable [] a2);

    public static boolean equals(float[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, float[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    @Pure
    public static boolean equals(@PolyInterned @PolyNull @PolySigned Object @GuardSatisfied @Nullable [] a, @PolyInterned @PolyNull @PolySigned Object @GuardSatisfied @Nullable [] a2);

    public static boolean equals(@PolyNull Object[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, @PolyNull Object[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex);

    public static <T> boolean equals(T @Nullable [] a, T @Nullable [] a2, Comparator<? super T> cmp);

    public static <T> boolean equals(T[] a, @IndexOrHigh({ "#1" }) int aFromIndex, @IndexOrHigh({ "#1" }) int aToIndex, T[] b, @IndexOrHigh({ "#4" }) int bFromIndex, @IndexOrHigh({ "#4" }) int bToIndex, Comparator<? super T> cmp);

    public static void fill(@PolySigned long[] a, @PolySigned long val);

    public static void fill(@PolySigned long[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned long val);

    public static void fill(@PolySigned int[] a, @PolySigned int val);

    public static void fill(@PolySigned int[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned int val);

    public static void fill(@PolySigned short[] a, @PolySigned short val);

    public static void fill(@PolySigned short[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned short val);

    public static void fill(@PolySigned char[] a, @PolySigned char val);

    public static void fill(@PolySigned char[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned char val);

    public static void fill(@PolySigned byte[] a, @PolySigned byte val);

    public static void fill(@PolySigned byte[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolySigned byte val);

    public static void fill(boolean[] a, boolean val);

    public static void fill(boolean[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, boolean val);

    public static void fill(double[] a, double val);

    public static void fill(double[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, double val);

    public static void fill(float[] a, float val);

    public static void fill(float[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, float val);

    @CFComment("array covariance: polymorphism could e.g. allow filling an array of non-null types with null.")
    public static void fill(@PolyInterned @PolyNull @PolySigned Object[] a, @PolyInterned @PolyNull @PolySigned Object val);

    @CFComment("array covariance: polymorphism could e.g. allow filling an array of non-null types with null.")
    public static void fill(@PolyInterned @PolyNull @PolySigned Object[] a, @IndexOrHigh({ "#1" }) int fromIndex, @IndexOrHigh({ "#1" }) int toIndex, @PolyInterned @PolyNull @PolySigned Object val);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T[] copyOf(T[] original, @NonNegative int newLength);

    @SideEffectFree
    @IntrinsicCandidate
    @Nullable
    public static <T, U> T[] copyOf(U[] original, @NonNegative int newLength, Class<? extends T[]> newType);

    @SideEffectFree
    @PolySigned
    public static byte[] copyOf(@PolySigned byte[] original, @NonNegative int newLength);

    @SideEffectFree
    @PolySigned
    public static short[] copyOf(@PolySigned short[] original, @NonNegative int newLength);

    @SideEffectFree
    @PolySigned
    public static int[] copyOf(@PolySigned int[] original, @NonNegative int newLength);

    @SideEffectFree
    @PolySigned
    public static long[] copyOf(@PolySigned long[] original, @NonNegative int newLength);

    @SideEffectFree
    @PolySigned
    public static char[] copyOf(@PolySigned char[] original, @NonNegative int newLength);

    @SideEffectFree
    public static float[] copyOf(float[] original, @NonNegative int newLength);

    @SideEffectFree
    public static double[] copyOf(double[] original, @NonNegative int newLength);

    @SideEffectFree
    public static boolean[] copyOf(boolean[] original, @NonNegative int newLength);

    @SuppressWarnings("unchecked")
    @SideEffectFree
    @Nullable
    public static <T> T[] copyOfRange(T[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @IntrinsicCandidate
    @SideEffectFree
    @Nullable
    public static <T, U> T[] copyOfRange(U[] original, @IndexOrHigh({ "#1" }) int from, int to, Class<? extends T[]> newType);

    @SideEffectFree
    @PolySigned
    public static byte[] copyOfRange(@PolySigned byte[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    @PolySigned
    public static short[] copyOfRange(@PolySigned short[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    @PolySigned
    public static int[] copyOfRange(@PolySigned int[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    @PolySigned
    public static long[] copyOfRange(@PolySigned long[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    @PolySigned
    public static char[] copyOfRange(@PolySigned char[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    public static float[] copyOfRange(float[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    public static double[] copyOfRange(double[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SideEffectFree
    public static boolean[] copyOfRange(boolean[] original, @IndexOrHigh({ "#1" }) int from, int to);

    @SafeVarargs
    @SideEffectFree
    @SuppressWarnings("varargs")
    @CFComment({ "array covariance: if a reference to the argument is retained, array covariance could allow", "adding a null value to an array of non-null types." })
    @PolyNonEmpty
    public static <T> List<T> asList(T@PolyNonEmpty ... a);

    private static class ArrayList<E> extends AbstractList<E> implements RandomAccess, java.io.Serializable {

        @Override
        @Pure
        @NonNegative
        public int size();

        @SideEffectFree
        @Override
        @PolyNull
        @PolySigned
        public Object[] toArray(Arrays.ArrayList<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Override
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        @Override
        public E get(int index);

        @Override
        public E set(int index, E element);

        @Override
        public int indexOf(Object o);

        @Override
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @Override
        public Iterator<E> iterator();
    }

    private static class ArrayItr<E> implements Iterator<E> {

        @Override
        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @Override
        @SideEffectsOnly("this")
        public E next(@NonEmpty ArrayItr<E> this);
    }

    @Pure
    public static int hashCode(@PolySigned long @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned int @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned short @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned char @Nullable [] a);

    @Pure
    public static int hashCode(@PolySigned byte @Nullable [] a);

    @Pure
    public static int hashCode(boolean @Nullable [] a);

    @Pure
    public static int hashCode(float @Nullable [] a);

    @Pure
    public static int hashCode(double @Nullable [] a);

    @Pure
    public static int hashCode(@PolyInterned @PolyNull @PolySigned Object @GuardSatisfied @Nullable [] a);

    @Pure
    public static int deepHashCode(@PolyInterned @PolyNull @PolySigned Object @GuardSatisfied @Nullable [] a);

    @Pure
    public static boolean deepEquals(@PolyInterned @PolyNull @PolySigned Object @GuardSatisfied @Nullable [] a1, @PolyInterned @PolyNull @PolySigned Object @GuardSatisfied @Nullable [] a2);

    static boolean deepEquals0(Object e1, Object e2);

    @SideEffectFree
    @MinLen(2)
    public static String toString(long @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(int @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(short @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(char @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(byte @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(boolean @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(float @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String toString(double @Nullable [] a);

    @SideEffectFree
    @CFComment({ "The @PolyMustCall annotations don't make sense, because toString", "shouldn't care about MustCall types, especially of the array.  However,", "without these annotations, calls to Arrays.toString yield a MustCall error." })
    @MinLen(2)
    public static String toString(@PolyInterned @PolyMustCall @PolyNull @PolySigned Object @PolyMustCall @Nullable [] a);

    @SideEffectFree
    @MinLen(2)
    public static String deepToString(@PolyInterned @PolyMustCall @PolyNull @PolySigned Object @PolyMustCall @Nullable [] a);

    public static <T> void setAll(T[] array, IntFunction<? extends T> generator);

    public static <T> void parallelSetAll(T[] array, IntFunction<? extends T> generator);

    public static void setAll(int[] array, IntUnaryOperator generator);

    public static void parallelSetAll(int[] array, IntUnaryOperator generator);

    public static void setAll(long[] array, IntToLongFunction generator);

    public static void parallelSetAll(long[] array, IntToLongFunction generator);

    public static void setAll(double[] array, IntToDoubleFunction generator);

    public static void parallelSetAll(double[] array, IntToDoubleFunction generator);

    @SideEffectFree
    public static <T> Spliterator<T> spliterator(T[] array);

    @SideEffectFree
    public static <T> Spliterator<T> spliterator(T[] array, int startInclusive, int endExclusive);

    @SideEffectFree
    public static Spliterator.OfInt spliterator(int[] array);

    @SideEffectFree
    public static Spliterator.OfInt spliterator(int[] array, int startInclusive, int endExclusive);

    @SideEffectFree
    public static Spliterator.OfLong spliterator(long[] array);

    @SideEffectFree
    public static Spliterator.OfLong spliterator(long[] array, int startInclusive, int endExclusive);

    @SideEffectFree
    public static Spliterator.OfDouble spliterator(double[] array);

    @SideEffectFree
    public static Spliterator.OfDouble spliterator(double[] array, int startInclusive, int endExclusive);

    public static <T> Stream<T> stream(T[] array);

    public static <T> Stream<T> stream(T[] array, int startInclusive, int endExclusive);

    public static IntStream stream(int[] array);

    public static IntStream stream(int[] array, int startInclusive, int endExclusive);

    public static LongStream stream(long[] array);

    public static LongStream stream(long[] array, int startInclusive, int endExclusive);

    public static DoubleStream stream(double[] array);

    public static DoubleStream stream(double[] array, int startInclusive, int endExclusive);

    public static int compare(boolean @Nullable [] a, boolean @Nullable [] b);

    public static int compare(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex);

    public static int compare(byte @Nullable [] a, byte @Nullable [] b);

    public static int compare(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(@Unsigned byte @Nullable [] a, @Unsigned byte @Nullable [] b);

    public static int compareUnsigned(@Unsigned byte[] a, @IndexFor("#1") int aFromIndex, @IndexFor("#1") int aToIndex, @Unsigned byte[] b, @IndexFor("#3") int bFromIndex, @IndexFor("#3") int bToIndex);

    public static int compare(short @Nullable [] a, short @Nullable [] b);

    public static int compare(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(@Unsigned short @Nullable [] a, @Unsigned short @Nullable [] b);

    public static int compareUnsigned(@Unsigned short[] a, @IndexFor("#1") int aFromIndex, @IndexFor("#1") int aToIndex, @Unsigned short[] b, @IndexFor("#3") int bFromIndex, @IndexFor("#3") int bToIndex);

    public static int compare(char @Nullable [] a, char @Nullable [] b);

    public static int compare(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex);

    public static int compare(int @Nullable [] a, int @Nullable [] b);

    public static int compare(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(@Unsigned int @Nullable [] a, @Unsigned int @Nullable [] b);

    public static int compareUnsigned(@Unsigned int[] a, @IndexFor("#1") int aFromIndex, @IndexFor("#1") int aToIndex, @Unsigned int[] b, @IndexFor("#3") int bFromIndex, @IndexFor("#3") int bToIndex);

    public static int compare(long @Nullable [] a, long @Nullable [] b);

    public static int compare(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex);

    public static int compareUnsigned(@Unsigned long @Nullable [] a, @Unsigned long @Nullable [] b);

    public static int compareUnsigned(@Unsigned long[] a, @IndexFor("#1") int aFromIndex, @IndexFor("#1") int aToIndex, @Unsigned long[] b, @IndexFor("#3") int bFromIndex, @IndexFor("#3") int bToIndex);

    public static int compare(float @Nullable [] a, float @Nullable [] b);

    public static int compare(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex);

    public static int compare(double @Nullable [] a, double @Nullable [] b);

    public static int compare(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex);

    public static <T extends Comparable<? super T>> int compare(@PolyNull T @Nullable [] a, @PolyNull T @Nullable [] b);

    public static <T extends Comparable<? super T>> int compare(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex);

    public static <T> int compare(T @Nullable [] a, T @Nullable [] b, Comparator<? super T> cmp);

    public static <T> int compare(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex, Comparator<? super T> cmp);

    public static int mismatch(boolean[] a, boolean[] b);

    public static int mismatch(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex);

    public static int mismatch(byte[] a, byte[] b);

    public static int mismatch(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex);

    public static int mismatch(char[] a, char[] b);

    public static int mismatch(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex);

    public static int mismatch(short[] a, short[] b);

    public static int mismatch(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex);

    public static int mismatch(int[] a, int[] b);

    public static int mismatch(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex);

    public static int mismatch(long[] a, long[] b);

    public static int mismatch(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex);

    public static int mismatch(float[] a, float[] b);

    public static int mismatch(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex);

    public static int mismatch(double[] a, double[] b);

    public static int mismatch(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex);

    public static int mismatch(@PolyNull Object[] a, @PolyNull Object[] b);

    public static int mismatch(@PolyNull Object[] a, int aFromIndex, int aToIndex, @PolyNull Object[] b, int bFromIndex, int bToIndex);

    public static <T> int mismatch(T[] a, T[] b, Comparator<? super T> cmp);

    public static <T> int mismatch(T[] a, int aFromIndex, int aToIndex, T[] b, int bFromIndex, int bToIndex, Comparator<? super T> cmp);
}
