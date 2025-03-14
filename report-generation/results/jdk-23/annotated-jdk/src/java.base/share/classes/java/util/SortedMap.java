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

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface SortedMap<K, V> extends SequencedMap<K, V> {

    @Pure
    @Nullable
    Comparator<? super K> comparator(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    SortedMap<K, V> subMap(@GuardSatisfied SortedMap<K, V> this, @GuardSatisfied K fromKey, @GuardSatisfied K toKey);

    @SideEffectFree
    SortedMap<K, V> headMap(@GuardSatisfied SortedMap<K, V> this, K toKey);

    @SideEffectFree
    SortedMap<K, V> tailMap(@GuardSatisfied SortedMap<K, V> this, K fromKey);

    @SideEffectFree
    @KeyFor("this")
    K firstKey(@GuardSatisfied @NonEmpty SortedMap<K, V> this);

    @SideEffectFree
    @KeyFor("this")
    K lastKey(@GuardSatisfied @NonEmpty SortedMap<K, V> this);

    @SideEffectFree
    Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    Collection<V> values(@GuardSatisfied SortedMap<K, V> this);

    @SideEffectFree
    Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied SortedMap<K, V> this);

    default V putFirst(K k, V v);

    default V putLast(K k, V v);

    default SortedMap<K, V> reversed();
}
