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
package java.util.concurrent;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.Field;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;

@AnnotatedFor({ "nullness" })
public class ConcurrentSkipListSet<E extends @NonNull Object> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, java.io.Serializable {

    public ConcurrentSkipListSet() {
    }

    public ConcurrentSkipListSet(@Nullable Comparator<? super E> comparator) {
    }

    @SuppressWarnings("this-escape")
    public ConcurrentSkipListSet(Collection<? extends E> c) {
    }

    @SuppressWarnings("this-escape")
    public ConcurrentSkipListSet(SortedSet<E> s) {
    }

    @SideEffectFree
    public ConcurrentSkipListSet<E> clone();

    @Pure
    public int size();

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @UnknownSignedness Object o);

    @EnsuresNonEmpty("this")
    public boolean add(E e);

    public boolean remove(@GuardSatisfied @UnknownSignedness Object o);

    public void clear();

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public E lower(E e);

    public E floor(E e);

    public E ceiling(E e);

    public E higher(E e);

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    @Pure
    @Nullable
    public Comparator<? super E> comparator();

    public E first(@NonEmpty ConcurrentSkipListSet<E> this);

    public E last(@NonEmpty ConcurrentSkipListSet<E> this);

    public void addFirst(E e);

    public void addLast(E e);

    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

    public NavigableSet<E> headSet(E toElement, boolean inclusive);

    public NavigableSet<E> tailSet(E fromElement, boolean inclusive);

    public NavigableSet<E> subSet(E fromElement, E toElement);

    public NavigableSet<E> headSet(E toElement);

    public NavigableSet<E> tailSet(E fromElement);

    public NavigableSet<E> descendingSet();

    @SuppressWarnings({ "unchecked" })
    @SideEffectFree
    public Spliterator<E> spliterator();
}
