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

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "lock/nullness: Subclasses of this interface/class may opt to prohibit null elements" })
@AnnotatedFor({ "lock", "nullness", "index" })
public interface Deque<E> extends Queue<E>, SequencedCollection<E> {

    @EnsuresNonEmpty("this")
    void addFirst(@GuardSatisfied Deque<E> this, E e);

    @EnsuresNonEmpty("this")
    void addLast(@GuardSatisfied Deque<E> this, E e);

    boolean offerFirst(E e);

    boolean offerLast(E e);

    E removeFirst(@GuardSatisfied @NonEmpty Deque<E> this);

    E removeLast(@GuardSatisfied @NonEmpty Deque<E> this);

    @Nullable
    E pollFirst(@GuardSatisfied Deque<E> this);

    @Nullable
    E pollLast(@GuardSatisfied Deque<E> this);

    @EnsuresNonEmpty("this")
    E getFirst(@GuardSatisfied @NonEmpty Deque<E> this);

    @EnsuresNonEmpty("this")
    E getLast(@GuardSatisfied @NonEmpty Deque<E> this);

    @Nullable
    E peekFirst();

    @Nullable
    E peekLast();

    boolean removeFirstOccurrence(@GuardSatisfied Deque<E> this, Object o);

    boolean removeLastOccurrence(@GuardSatisfied Deque<E> this, Object o);

    @EnsuresNonEmpty("this")
    boolean add(@GuardSatisfied Deque<E> this, E e);

    boolean offer(E e);

    E remove(@GuardSatisfied @NonEmpty Deque<E> this);

    @Nullable
    E poll(@GuardSatisfied Deque<E> this);

    E element(@GuardSatisfied @NonEmpty Deque<E> this);

    @Nullable
    E peek();

    boolean addAll(Collection<? extends E> c);

    void push(@GuardSatisfied Deque<E> this, E e);

    E pop(@GuardSatisfied @NonEmpty Deque<E> this);

    boolean remove(@GuardSatisfied Deque<E> this, @UnknownSignedness Object o);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    boolean contains(@GuardSatisfied Deque<E> this, @UnknownSignedness Object o);

    @Pure
    @NonNegative
    int size(@GuardSatisfied Deque<E> this);

    @SideEffectFree
    Iterator<E> iterator();

    Iterator<E> descendingIterator();

    default Deque<E> reversed();
}
