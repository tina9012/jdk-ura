/*
 * Copyright (c) 1998, 2023, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable {

    public TreeSet() {
    }

    public TreeSet(@Nullable Comparator<? super E> comparator) {
    }

    @PolyNonEmpty
    public TreeSet(@PolyNonEmpty Collection<? extends E> c) {
    }

    @PolyNonEmpty
    public TreeSet(@PolyNonEmpty SortedSet<E> s) {
    }

    @SideEffectFree
    @PolyNonEmpty
    public Iterator<E> iterator(@PolyNonEmpty TreeSet<E> this);

    @PolyNonEmpty
    public Iterator<E> descendingIterator(@PolyNonEmpty TreeSet<E> this);

    public NavigableSet<E> descendingSet();

    @Pure
    @NonNegative
    public int size(@GuardSatisfied TreeSet<E> this);

    @EnsuresNonNullIf(expression = { "pollFirst()", "pollLast()" }, result = false)
    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty(@GuardSatisfied TreeSet<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied TreeSet<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @EnsuresNonEmpty("this")
    public boolean add(@GuardSatisfied TreeSet<E> this, E e);

    public boolean remove(@GuardSatisfied TreeSet<E> this, @GuardSatisfied @UnknownSignedness Object o);

    public void clear(@GuardSatisfied TreeSet<E> this);

    public boolean addAll(@GuardSatisfied TreeSet<E> this, Collection<? extends E> c);

    @SideEffectFree
    public NavigableSet<E> subSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E fromElement, boolean fromInclusive, @GuardSatisfied E toElement, boolean toInclusive);

    @SideEffectFree
    public NavigableSet<E> headSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E toElement, boolean inclusive);

    @SideEffectFree
    public NavigableSet<E> tailSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E fromElement, boolean inclusive);

    @SideEffectFree
    public SortedSet<E> subSet(@GuardSatisfied TreeSet<E> this, @GuardSatisfied E fromElement, @GuardSatisfied E toElement);

    @SideEffectFree
    public SortedSet<E> headSet(@GuardSatisfied TreeSet<E> this, E toElement);

    @SideEffectFree
    public SortedSet<E> tailSet(@GuardSatisfied TreeSet<E> this, E fromElement);

    @Pure
    @Nullable
    public Comparator<? super E> comparator(@GuardSatisfied TreeSet<E> this);

    @SideEffectFree
    public E first(@GuardSatisfied @NonEmpty TreeSet<E> this);

    @SideEffectFree
    public E last(@GuardSatisfied @NonEmpty TreeSet<E> this);

    @Nullable
    public E lower(E e);

    @Nullable
    public E floor(E e);

    @Nullable
    public E ceiling(E e);

    @Nullable
    public E higher(E e);

    @Nullable
    public E pollFirst(@GuardSatisfied TreeSet<E> this);

    @Nullable
    public E pollLast(@GuardSatisfied TreeSet<E> this);

    public void addFirst(E e);

    public void addLast(E e);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public Object clone(@GuardSatisfied TreeSet<E> this);

    public Spliterator<E> spliterator();
}
