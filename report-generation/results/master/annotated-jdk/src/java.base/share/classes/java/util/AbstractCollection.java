/*
 * Copyright (c) 1997, 2019, Oracle and/or its affiliates. All rights reserved.
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
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import jdk.internal.util.ArraysSupport;

@CFComment("lock/nullness: Subclasses of this interface/class may opt to prohibit null elements")
@AnnotatedFor({ "lock", "nullness", "index" })
public abstract class AbstractCollection<E> implements Collection<E> {

    @SideEffectFree
    protected AbstractCollection() {
    }

    @SideEffectFree
    @PolyNonEmpty
    public abstract Iterator<E> iterator(@PolyNonEmpty AbstractCollection<E> this);

    @Pure
    @NonNegative
    public abstract int size(@GuardSatisfied AbstractCollection<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty(@GuardSatisfied AbstractCollection<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied AbstractCollection<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(AbstractCollection<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public boolean add(@GuardSatisfied AbstractCollection<E> this, E e);

    public boolean remove(@GuardSatisfied AbstractCollection<E> this, @GuardSatisfied @UnknownSignedness Object o);

    @Pure
    public boolean containsAll(@GuardSatisfied AbstractCollection<E> this, @GuardSatisfied Collection<? extends @UnknownSignedness Object> c);

    public boolean addAll(@GuardSatisfied AbstractCollection<E> this, Collection<? extends E> c);

    public boolean removeAll(@GuardSatisfied AbstractCollection<E> this, Collection<? extends @UnknownSignedness Object> c);

    public boolean retainAll(@GuardSatisfied AbstractCollection<E> this, Collection<? extends @UnknownSignedness Object> c);

    public void clear(@GuardSatisfied AbstractCollection<E> this);

    @SideEffectFree
    public String toString(@GuardSatisfied AbstractCollection<E> this);
}
