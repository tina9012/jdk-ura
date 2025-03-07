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
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InvalidObjectException;
import jdk.internal.access.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {

    public HashSet() {
    }

    @PolyNonEmpty
    public HashSet(@PolyNonEmpty Collection<? extends E> c) {
    }

    public HashSet(@NonNegative int initialCapacity, float loadFactor) {
    }

    public HashSet(@NonNegative int initialCapacity) {
    }

    @SideEffectFree
    @PolyNonEmpty
    public Iterator<E> iterator(@PolyNonEmpty HashSet<E> this);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied HashSet<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty(@GuardSatisfied HashSet<E> this);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied HashSet<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @SideEffectsOnly("this")
    @EnsuresNonEmpty("this")
    public boolean add(@GuardSatisfied HashSet<E> this, E e);

    @SideEffectsOnly("this")
    public boolean remove(@GuardSatisfied HashSet<E> this, @GuardSatisfied @Nullable @UnknownSignedness Object o);

    @SideEffectsOnly("this")
    public void clear(@GuardSatisfied HashSet<E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    public Object clone(@GuardSatisfied HashSet<E> this);

    public Spliterator<E> spliterator();

    @Override
    public Object[] toArray();

    @Override
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public static <T> HashSet<T> newHashSet(int numElements);
}
