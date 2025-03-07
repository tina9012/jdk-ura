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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@CFComment({ "lock/nullness: This class permits null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

    public LinkedList() {
    }

    @SuppressWarnings("this-escape")
    @PolyNonEmpty
    public LinkedList(@PolyNonEmpty Collection<? extends E> c) {
    }

    void linkLast(E e);

    void linkBefore(E e, Node<E> succ);

    E unlink(Node<E> x);

    public E getFirst(@GuardSatisfied @NonEmpty LinkedList<E> this);

    public E getLast(@GuardSatisfied @NonEmpty LinkedList<E> this);

    public E removeFirst(@GuardSatisfied @NonEmpty LinkedList<E> this);

    public E removeLast(@GuardSatisfied @NonEmpty LinkedList<E> this);

    public void addFirst(@GuardSatisfied LinkedList<E> this, E e);

    public void addLast(@GuardSatisfied LinkedList<E> this, E e);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied LinkedList<E> this);

    @ReleasesNoLocks
    @EnsuresNonEmpty("this")
    public boolean add(@GuardSatisfied LinkedList<E> this, E e);

    @ReleasesNoLocks
    public boolean remove(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    public boolean addAll(@GuardSatisfied LinkedList<E> this, Collection<? extends E> c);

    public boolean addAll(@GuardSatisfied LinkedList<E> this, @NonNegative int index, Collection<? extends E> c);

    public void clear(@GuardSatisfied LinkedList<E> this);

    @Pure
    public E get(@GuardSatisfied LinkedList<E> this, @NonNegative int index);

    public E set(@GuardSatisfied LinkedList<E> this, @NonNegative int index, E element);

    public void add(@GuardSatisfied LinkedList<E> this, @NonNegative int index, E element);

    public E remove(@GuardSatisfied LinkedList<E> this, @NonNegative int index);

    Node<E> node(@NonNegative int index);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied LinkedList<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    @Nullable
    public E peek();

    public E element(@GuardSatisfied @NonEmpty LinkedList<E> this);

    @Nullable
    public E poll(@GuardSatisfied LinkedList<E> this);

    public E remove(@GuardSatisfied @NonEmpty LinkedList<E> this);

    public boolean offer(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Pure
    @Nullable
    public E peekFirst();

    @Pure
    @Nullable
    public E peekLast();

    @Nullable
    public E pollFirst(@GuardSatisfied LinkedList<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied LinkedList<E> this);

    public void push(@GuardSatisfied LinkedList<E> this, E e);

    public E pop(@GuardSatisfied @NonEmpty LinkedList<E> this);

    public boolean removeFirstOccurrence(@GuardSatisfied LinkedList<E> this, @Nullable Object o);

    public boolean removeLastOccurrence(@GuardSatisfied LinkedList<E> this, @Nullable Object o);

    public ListIterator<E> listIterator(@NonNegative int index);

    private class ListItr implements ListIterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty ListItr this);

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void remove();

        public void set(E e);

        public void add(E e);

        public void forEachRemaining(Consumer<? super E> action);

        final void checkForComodification();
    }

    private static class Node<E> {
    }

    public Iterator<E> descendingIterator();

    private class DescendingIterator implements Iterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty DescendingIterator this);

        public void remove();
    }

    @SideEffectFree
    public Object clone(@GuardSatisfied LinkedList<E> this);

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(LinkedList<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SideEffectFree
    @Override
    public Spliterator<E> spliterator();

    static final class LLSpliterator<E> implements Spliterator<E> {

        final int getEst();

        public long estimateSize();

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public int characteristics();
    }

    public LinkedList<E> reversed();

    @SuppressWarnings("serial")
    static class ReverseOrderLinkedListView<E> extends LinkedList<E> implements java.io.Externalizable {

        public String toString();

        public boolean retainAll(Collection<?> c);

        public boolean removeAll(Collection<?> c);

        public boolean containsAll(Collection<?> c);

        public boolean isEmpty();

        public Stream<E> parallelStream();

        public Stream<E> stream();

        public boolean removeIf(Predicate<? super E> filter);

        public <T> T[] toArray(IntFunction<T[]> generator);

        public void forEach(Consumer<? super E> action);

        public Iterator<E> iterator();

        public int hashCode();

        public boolean equals(Object o);

        public List<E> subList(int fromIndex, int toIndex);

        public ListIterator<E> listIterator();

        public void sort(Comparator<? super E> c);

        public void replaceAll(UnaryOperator<E> operator);

        public LinkedList<E> reversed();

        public Spliterator<E> spliterator();

        public <T> T[] toArray(T[] a);

        public Object[] toArray();

        public Iterator<E> descendingIterator();

        public ListIterator<E> listIterator(int index);

        public boolean removeLastOccurrence(Object o);

        public boolean removeFirstOccurrence(Object o);

        public E pop();

        public void push(E e);

        public E pollLast();

        public E pollFirst();

        public E peekLast();

        public E peekFirst();

        public boolean offerLast(E e);

        public boolean offerFirst(E e);

        public boolean offer(E e);

        public E remove();

        public E poll();

        public E element();

        public E peek();

        public int lastIndexOf(Object o);

        public int indexOf(Object o);

        public E remove(int index);

        public void add(int index, E element);

        public E set(int index, E element);

        public E get(int index);

        public void clear();

        public boolean addAll(int index, Collection<? extends E> c);

        public boolean addAll(Collection<? extends E> c);

        public boolean remove(Object o);

        public boolean add(E e);

        public int size();

        public boolean contains(Object o);

        public void addLast(E e);

        public void addFirst(E e);

        public E removeLast();

        public E removeFirst();

        public E getLast();

        public E getFirst();

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;

        public void writeExternal(ObjectOutput out) throws IOException;
    }
}
