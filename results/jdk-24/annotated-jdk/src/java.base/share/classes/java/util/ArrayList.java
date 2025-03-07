/*
 * Copyright (c) 1997, 2024, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import jdk.internal.access.SharedSecrets;
import jdk.internal.util.ArraysSupport;

@CFComment("lock/nullness: Permit null elements")
@AnnotatedFor({ "index", "initialization", "lock", "nullness" })
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    @SideEffectFree
    public ArrayList(@NonNegative int initialCapacity) {
    }

    @SideEffectFree
    public ArrayList() {
    }

    @SideEffectFree
    @PolyNonEmpty
    public ArrayList(@PolyNonEmpty Collection<? extends E> c) {
    }

    public void trimToSize(@GuardSatisfied ArrayList<E> this);

    public void ensureCapacity(@GuardSatisfied ArrayList<E> this, int minCapacity);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied ArrayList<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty(@GuardSatisfied ArrayList<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    int indexOfRange(@GuardSatisfied @Nullable @UnknownSignedness Object o, int start, int end);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    int lastIndexOfRange(@GuardSatisfied @Nullable @UnknownSignedness Object o, int start, int end);

    @SideEffectFree
    public Object clone(@GuardSatisfied ArrayList<E> this);

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(ArrayList<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SuppressWarnings("unchecked")
    E elementData(@NonNegative int index);

    @SuppressWarnings("unchecked")
    static <E> E elementAt(Object[] es, int index);

    @Pure
    public E get(@GuardSatisfied ArrayList<E> this, @NonNegative int index);

    public E getFirst();

    public E getLast();

    @SideEffectsOnly("this")
    public E set(@GuardSatisfied ArrayList<E> this, @NonNegative int index, E element);

    @SideEffectsOnly("this")
    @EnsuresNonEmpty("this")
    public boolean add(@GuardSatisfied ArrayList<E> this, E e);

    @SideEffectsOnly("this")
    public void add(@GuardSatisfied ArrayList<E> this, @NonNegative int index, E element);

    public void addFirst(E element);

    public void addLast(E element);

    public E remove(@GuardSatisfied ArrayList<E> this, @NonNegative int index);

    public E removeFirst();

    public E removeLast();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    boolean equalsRange(List<?> other, int from, int to);

    public int hashCode();

    int hashCodeRange(int from, int to);

    public boolean remove(@GuardSatisfied ArrayList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    public void clear(@GuardSatisfied ArrayList<E> this);

    @SideEffectsOnly("this")
    public boolean addAll(@GuardSatisfied ArrayList<E> this, Collection<? extends E> c);

    @SideEffectsOnly("this")
    public boolean addAll(@GuardSatisfied ArrayList<E> this, @NonNegative int index, Collection<? extends E> c);

    protected void removeRange(int fromIndex, int toIndex);

    public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

    boolean batchRemove(Collection<?> c, boolean complement, final int from, final int end);

    public ListIterator<E> listIterator(@NonNegative int index);

    public ListIterator<E> listIterator();

    @SideEffectFree
    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SuppressWarnings("unchecked")
        @SideEffectsOnly("this")
        public E next(@NonEmpty Itr this);

        public void remove();

        @SuppressWarnings({ "unchecked" })
        @Override
        public void forEachRemaining(Consumer<? super E> action);

        final void checkForComodification();
    }

    private class ListItr extends Itr implements ListIterator<E> {

        public boolean hasPrevious();

        public int nextIndex();

        public int previousIndex();

        @SuppressWarnings("unchecked")
        public E previous();

        public void set(E e);

        public void add(E e);
    }

    public List<E> subList(@NonNegative int fromIndex, @NonNegative int toIndex);

    private static class SubList<E> extends AbstractList<E> implements RandomAccess {

        public SubList(ArrayList<E> root, int fromIndex, int toIndex) {
        }

        public E set(@NonNegative int index, E element);

        public E get(@NonNegative int index);

        @Pure
        @NonNegative
        public int size();

        public void add(@NonNegative int index, E element);

        public E remove(@NonNegative int index);

        protected void removeRange(int fromIndex, int toIndex);

        public boolean addAll(Collection<? extends E> c);

        public boolean addAll(@NonNegative int index, Collection<? extends E> c);

        public void replaceAll(UnaryOperator<E> operator);

        public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

        public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

        public boolean removeIf(Predicate<? super E> filter);

        @PolyNull
        @PolySigned
        public Object[] toArray(SubList<@PolyNull @PolySigned E> this);

        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(T[] a);

        public boolean equals(@Nullable Object o);

        public int hashCode();

        public int indexOf(@GuardSatisfied @Nullable @UnknownSignedness Object o);

        public int lastIndexOf(@GuardSatisfied @Nullable @UnknownSignedness Object o);

        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@Nullable @UnknownSignedness Object o);

        @SideEffectFree
        public Iterator<E> iterator();

        public ListIterator<E> listIterator(@NonNegative int index);

        public List<E> subList(int fromIndex, int toIndex);

        @SideEffectFree
        public Spliterator<E> spliterator();

        @Override
        public void sort(Comparator<? super E> c);
    }

    @Override
    public void forEach(Consumer<? super E> action);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    final class ArrayListSpliterator implements Spliterator<E> {

        public ArrayListSpliterator trySplit();

        public boolean tryAdvance(Consumer<? super E> action);

        public void forEachRemaining(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter);

    boolean removeIf(Predicate<? super E> filter, int i, final int end);

    @SuppressWarnings({ "unchecked" })
    @Override
    public void replaceAll(UnaryOperator<E> operator);

    @Override
    public void sort(@Nullable Comparator<? super E> c);

    void checkInvariants();
}
