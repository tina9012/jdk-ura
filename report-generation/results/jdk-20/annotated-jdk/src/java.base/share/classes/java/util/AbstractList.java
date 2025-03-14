/*
 * Copyright (c) 1997, 2022, Oracle and/or its affiliates. All rights reserved.
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
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.function.Consumer;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    @SideEffectFree
    protected AbstractList() {
    }

    @EnsuresNonEmpty("this")
    public boolean add(@GuardSatisfied AbstractList<E> this, E e);

    @Pure
    public abstract E get(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index);

    public E set(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index, E element);

    public void add(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int index, E element);

    public E remove(@GuardSatisfied AbstractList<E> this, @IndexFor({ "this" }) int index);

    @Pure
    @GTENegativeOne
    public int indexOf(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @Pure
    @GTENegativeOne
    public int lastIndexOf(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @UnknownSignedness Object o);

    public void clear(@GuardSatisfied AbstractList<E> this);

    public boolean addAll(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int index, Collection<? extends E> c);

    @SideEffectFree
    public Iterator<E> iterator();

    public ListIterator<E> listIterator();

    public ListIterator<E> listIterator(@IndexOrHigh({ "this" }) final int index);

    private class Itr implements Iterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty Itr this);

        public void remove();

        final void checkForComodification();
    }

    private class ListItr extends Itr implements ListIterator<E> {

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void set(E e);

        public void add(E e);
    }

    @SideEffectFree
    public List<E> subList(@GuardSatisfied AbstractList<E> this, @IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    static void subListRangeCheck(int fromIndex, int toIndex, int size);

    @Pure
    public boolean equals(@GuardSatisfied AbstractList<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    public int hashCode(@GuardSatisfied AbstractList<E> this);

    protected void removeRange(@IndexOrHigh({ "this" }) int fromIndex, @IndexOrHigh({ "this" }) int toIndex);

    protected transient int modCount;

    static final class RandomAccessSpliterator<E> implements Spliterator<E> {

        public Spliterator<E> trySplit();

        public boolean tryAdvance(Consumer<? super E> action);

        public void forEachRemaining(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();

        static void checkAbstractListModCount(AbstractList<?> alist, int expectedModCount);
    }

    private static class SubList<E> extends AbstractList<E> {

        protected int size;

        public SubList(AbstractList<E> root, int fromIndex, int toIndex) {
        }

        protected SubList(SubList<E> parent, int fromIndex, int toIndex) {
        }

        public E set(int index, E element);

        public E get(int index);

        @Pure
        public int size();

        public void add(int index, E element);

        public E remove(int index);

        protected void removeRange(int fromIndex, int toIndex);

        public boolean addAll(Collection<? extends E> c);

        public boolean addAll(int index, Collection<? extends E> c);

        public Iterator<E> iterator();

        public ListIterator<E> listIterator(int index);

        public List<E> subList(int fromIndex, int toIndex);
    }

    private static class RandomAccessSubList<E> extends SubList<E> implements RandomAccess {

        public List<E> subList(int fromIndex, int toIndex);
    }
}
