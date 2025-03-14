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

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
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
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Set<E> extends Collection<E> {

    @Pure
    @NonNegative
    int size(@GuardSatisfied Set<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    boolean isEmpty(@GuardSatisfied Set<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    boolean contains(@GuardSatisfied Set<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @SideEffectFree
    @PolyNonEmpty
    Iterator<E> iterator(@PolyNonEmpty Set<E> this);

    @SideEffectFree
    @PolyNull
    @PolySigned
    Object[] toArray(Set<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @Nullable
    <T> T[] toArray(@PolyNull T[] a);

    @EnsuresNonEmpty("this")
    boolean add(@GuardSatisfied Set<E> this, E e);

    boolean remove(@GuardSatisfied Set<E> this, @UnknownSignedness Object o);

    @Pure
    boolean containsAll(@GuardSatisfied Set<E> this, @GuardSatisfied Collection<? extends @UnknownSignedness Object> c);

    @EnsuresNonEmptyIf(result = true, expression = "this")
    boolean addAll(@GuardSatisfied Set<E> this, Collection<? extends E> c);

    boolean retainAll(@GuardSatisfied Set<E> this, Collection<? extends @UnknownSignedness Object> c);

    boolean removeAll(@GuardSatisfied Set<E> this, Collection<? extends @UnknownSignedness Object> c);

    void clear(@GuardSatisfied Set<E> this);

    @Pure
    boolean equals(@GuardSatisfied Set<E> this, @GuardSatisfied @Nullable Object o);

    @Pure
    int hashCode(@GuardSatisfied Set<E> this);

    @Override
    default Spliterator<E> spliterator();

    @SuppressWarnings("unchecked")
    static <E> Set<E> of();

    @NonEmpty
    static <E extends Object> Set<E> of(E e1);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4, E e5);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);

    @NonEmpty
    static <E extends Object> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10);

    @SafeVarargs
    @SuppressWarnings("varargs")
    static <E extends Object> Set<E> of(E... elements);

    @SuppressWarnings("unchecked")
    @PolyNonEmpty
    static <E extends Object> Set<E> copyOf(@PolyNonEmpty Collection<? extends E> coll);
}
