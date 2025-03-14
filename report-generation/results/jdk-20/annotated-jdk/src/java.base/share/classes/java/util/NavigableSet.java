/*
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
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness" })
public interface NavigableSet<E> extends SortedSet<E> {

    @Nullable
    E lower(E e);

    @Nullable
    E floor(E e);

    @Nullable
    E ceiling(E e);

    @Nullable
    E higher(E e);

    @Nullable
    E pollFirst(@GuardSatisfied NavigableSet<E> this);

    @Nullable
    E pollLast(@GuardSatisfied NavigableSet<E> this);

    @SideEffectFree
    Iterator<E> iterator();

    NavigableSet<E> descendingSet();

    Iterator<E> descendingIterator();

    @SideEffectFree
    NavigableSet<E> subSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E fromElement, boolean fromInclusive, @GuardSatisfied E toElement, boolean toInclusive);

    @SideEffectFree
    NavigableSet<E> headSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E toElement, boolean inclusive);

    @SideEffectFree
    NavigableSet<E> tailSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E fromElement, boolean inclusive);

    @SideEffectFree
    SortedSet<E> subSet(@GuardSatisfied NavigableSet<E> this, @GuardSatisfied E fromElement, @GuardSatisfied E toElement);

    @SideEffectFree
    SortedSet<E> headSet(@GuardSatisfied NavigableSet<E> this, E toElement);

    @SideEffectFree
    SortedSet<E> tailSet(@GuardSatisfied NavigableSet<E> this, E fromElement);
}
