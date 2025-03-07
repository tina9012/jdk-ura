/*
 * Copyright (c) 2013, 2023, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public final class Spliterators {

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static <T> Spliterator<T> emptySpliterator();

    @SideEffectFree
    public static Spliterator.OfInt emptyIntSpliterator();

    @SideEffectFree
    public static Spliterator.OfLong emptyLongSpliterator();

    @SideEffectFree
    public static Spliterator.OfDouble emptyDoubleSpliterator();

    public static <T> Spliterator<@PolyNull T> spliterator(@PolyNull Object[] array, int additionalCharacteristics);

    public static <T> Spliterator<@PolyNull T> spliterator(@PolyNull Object[] array, int fromIndex, int toIndex, int additionalCharacteristics);

    public static Spliterator.OfInt spliterator(int[] array, int additionalCharacteristics);

    public static Spliterator.OfInt spliterator(int[] array, int fromIndex, int toIndex, int additionalCharacteristics);

    public static Spliterator.OfLong spliterator(long[] array, int additionalCharacteristics);

    public static Spliterator.OfLong spliterator(long[] array, int fromIndex, int toIndex, int additionalCharacteristics);

    public static Spliterator.OfDouble spliterator(double[] array, int additionalCharacteristics);

    public static Spliterator.OfDouble spliterator(double[] array, int fromIndex, int toIndex, int additionalCharacteristics);

    public static <T> Spliterator<T> spliterator(Collection<? extends T> c, int characteristics);

    public static <T> Spliterator<T> spliterator(Iterator<? extends T> iterator, long size, int characteristics);

    public static <T> Spliterator<T> spliteratorUnknownSize(Iterator<? extends T> iterator, int characteristics);

    public static Spliterator.OfInt spliterator(PrimitiveIterator.OfInt iterator, long size, int characteristics);

    public static Spliterator.OfInt spliteratorUnknownSize(PrimitiveIterator.OfInt iterator, int characteristics);

    public static Spliterator.OfLong spliterator(PrimitiveIterator.OfLong iterator, long size, int characteristics);

    public static Spliterator.OfLong spliteratorUnknownSize(PrimitiveIterator.OfLong iterator, int characteristics);

    public static Spliterator.OfDouble spliterator(PrimitiveIterator.OfDouble iterator, long size, int characteristics);

    public static Spliterator.OfDouble spliteratorUnknownSize(PrimitiveIterator.OfDouble iterator, int characteristics);

    public static <T> Iterator<T> iterator(Spliterator<? extends T> spliterator);

    public static PrimitiveIterator.OfInt iterator(Spliterator.OfInt spliterator);

    public static PrimitiveIterator.OfLong iterator(Spliterator.OfLong spliterator);

    public static PrimitiveIterator.OfDouble iterator(Spliterator.OfDouble spliterator);

    private abstract static class EmptySpliterator<T, S extends Spliterator<T>, C> {

        public S trySplit();

        public boolean tryAdvance(C consumer);

        public void forEachRemaining(C consumer);

        public long estimateSize();

        public int characteristics();

        private static final class OfRef<T> extends EmptySpliterator<T, Spliterator<T>, Consumer<? super T>> implements Spliterator<T> {
        }

        @SuppressWarnings("overloads")
        private static final class OfInt extends EmptySpliterator<Integer, Spliterator.OfInt, IntConsumer> implements Spliterator.OfInt {
        }

        @SuppressWarnings("overloads")
        private static final class OfLong extends EmptySpliterator<Long, Spliterator.OfLong, LongConsumer> implements Spliterator.OfLong {
        }

        @SuppressWarnings("overloads")
        private static final class OfDouble extends EmptySpliterator<Double, Spliterator.OfDouble, DoubleConsumer> implements Spliterator.OfDouble {
        }
    }

    static final class ArraySpliterator<T> implements Spliterator<T> {

        public ArraySpliterator(Object[] array, int additionalCharacteristics) {
        }

        public ArraySpliterator(Object[] array, int origin, int fence, int additionalCharacteristics) {
        }

        @Override
        public Spliterator<T> trySplit();

        @SuppressWarnings("unchecked")
        @Override
        public void forEachRemaining(Consumer<? super T> action);

        @Override
        public boolean tryAdvance(Consumer<? super T> action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super T> getComparator();
    }

    static final class IntArraySpliterator implements Spliterator.OfInt {

        public IntArraySpliterator(int[] array, int additionalCharacteristics) {
        }

        public IntArraySpliterator(int[] array, int origin, int fence, int additionalCharacteristics) {
        }

        @Override
        public OfInt trySplit();

        @Override
        public void forEachRemaining(IntConsumer action);

        @Override
        public boolean tryAdvance(IntConsumer action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super Integer> getComparator();
    }

    static final class LongArraySpliterator implements Spliterator.OfLong {

        public LongArraySpliterator(long[] array, int additionalCharacteristics) {
        }

        public LongArraySpliterator(long[] array, int origin, int fence, int additionalCharacteristics) {
        }

        @Override
        public OfLong trySplit();

        @Override
        public void forEachRemaining(LongConsumer action);

        @Override
        public boolean tryAdvance(LongConsumer action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super Long> getComparator();
    }

    static final class DoubleArraySpliterator implements Spliterator.OfDouble {

        public DoubleArraySpliterator(double[] array, int additionalCharacteristics) {
        }

        public DoubleArraySpliterator(double[] array, int origin, int fence, int additionalCharacteristics) {
        }

        @Override
        public OfDouble trySplit();

        @Override
        public void forEachRemaining(DoubleConsumer action);

        @Override
        public boolean tryAdvance(DoubleConsumer action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super Double> getComparator();
    }

    public abstract static class AbstractSpliterator<T> implements Spliterator<T> {

        protected AbstractSpliterator(long est, int additionalCharacteristics) {
        }

        static final class HoldingConsumer<T> implements Consumer<T> {

            @Override
            public void accept(T value);
        }

        @Override
        @Nullable
        public Spliterator<T> trySplit();

        @Override
        public long estimateSize();

        @Override
        public int characteristics();
    }

    public abstract static class AbstractIntSpliterator implements Spliterator.OfInt {

        protected AbstractIntSpliterator(long est, int additionalCharacteristics) {
        }

        static final class HoldingIntConsumer implements IntConsumer {

            @Override
            public void accept(int value);
        }

        @Override
        public Spliterator.@Nullable OfInt trySplit();

        @Override
        public long estimateSize();

        @Override
        public int characteristics();
    }

    public abstract static class AbstractLongSpliterator implements Spliterator.OfLong {

        protected AbstractLongSpliterator(long est, int additionalCharacteristics) {
        }

        static final class HoldingLongConsumer implements LongConsumer {

            @Override
            public void accept(long value);
        }

        @Override
        public Spliterator.@Nullable OfLong trySplit();

        @Override
        public long estimateSize();

        @Override
        public int characteristics();
    }

    public abstract static class AbstractDoubleSpliterator implements Spliterator.OfDouble {

        protected AbstractDoubleSpliterator(long est, int additionalCharacteristics) {
        }

        static final class HoldingDoubleConsumer implements DoubleConsumer {

            @Override
            public void accept(double value);
        }

        @Override
        public Spliterator.@Nullable OfDouble trySplit();

        @Override
        public long estimateSize();

        @Override
        public int characteristics();
    }

    static class IteratorSpliterator<T> implements Spliterator<T> {

        public IteratorSpliterator(Collection<? extends T> collection, int characteristics) {
        }

        public IteratorSpliterator(Iterator<? extends T> iterator, long size, int characteristics) {
        }

        public IteratorSpliterator(Iterator<? extends T> iterator, int characteristics) {
        }

        @Override
        public Spliterator<T> trySplit();

        @Override
        public void forEachRemaining(Consumer<? super T> action);

        @Override
        public boolean tryAdvance(Consumer<? super T> action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super T> getComparator();
    }

    static final class IntIteratorSpliterator implements Spliterator.OfInt {

        public IntIteratorSpliterator(PrimitiveIterator.OfInt iterator, long size, int characteristics) {
        }

        public IntIteratorSpliterator(PrimitiveIterator.OfInt iterator, int characteristics) {
        }

        @Override
        public OfInt trySplit();

        @Override
        public void forEachRemaining(IntConsumer action);

        @Override
        public boolean tryAdvance(IntConsumer action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super Integer> getComparator();
    }

    static final class LongIteratorSpliterator implements Spliterator.OfLong {

        public LongIteratorSpliterator(PrimitiveIterator.OfLong iterator, long size, int characteristics) {
        }

        public LongIteratorSpliterator(PrimitiveIterator.OfLong iterator, int characteristics) {
        }

        @Override
        public OfLong trySplit();

        @Override
        public void forEachRemaining(LongConsumer action);

        @Override
        public boolean tryAdvance(LongConsumer action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super Long> getComparator();
    }

    static final class DoubleIteratorSpliterator implements Spliterator.OfDouble {

        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble iterator, long size, int characteristics) {
        }

        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble iterator, int characteristics) {
        }

        @Override
        public OfDouble trySplit();

        @Override
        public void forEachRemaining(DoubleConsumer action);

        @Override
        public boolean tryAdvance(DoubleConsumer action);

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public Comparator<? super Double> getComparator();
    }
}
