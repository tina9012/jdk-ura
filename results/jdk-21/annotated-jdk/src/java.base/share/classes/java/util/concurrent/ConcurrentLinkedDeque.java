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
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class ConcurrentLinkedDeque<E extends @NonNull Object> extends AbstractCollection<E> implements Deque<E>, java.io.Serializable {

    @SuppressWarnings("unchecked")
    Node<E> prevTerminator();

    @SuppressWarnings("unchecked")
    Node<E> nextTerminator();

    static final class Node<E> {
    }

    static <E> Node<E> newNode(E item);

    void unlink(Node<E> x);

    final Node<E> succ(Node<E> p);

    final Node<E> pred(Node<E> p);

    Node<E> first();

    Node<E> last();

    public ConcurrentLinkedDeque() {
    }

    public ConcurrentLinkedDeque(Collection<? extends E> c) {
    }

    public void addFirst(E e);

    public void addLast(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    @Pure
    @Nullable
    public E peekFirst();

    @Pure
    @Nullable
    public E peekLast();

    public E getFirst(@NonEmpty ConcurrentLinkedDeque<E> this);

    public E getLast(@NonEmpty ConcurrentLinkedDeque<E> this);

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    public E removeFirst(@NonEmpty ConcurrentLinkedDeque<E> this);

    public E removeLast(@NonEmpty ConcurrentLinkedDeque<E> this);

    public boolean offer(E e);

    @EnsuresNonEmpty("this")
    public boolean add(E e);

    @Nullable
    public E poll();

    @Pure
    @Nullable
    public E peek();

    public E remove(@NonEmpty ConcurrentLinkedDeque<E> this);

    public E pop(@NonEmpty ConcurrentLinkedDeque<E> this);

    public E element(@NonEmpty ConcurrentLinkedDeque<E> this);

    public void push(E e);

    public boolean removeFirstOccurrence(Object o);

    public boolean removeLastOccurrence(Object o);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @UnknownSignedness Object o);

    @EnsuresNonNullIf(expression = { "peek()", "peekFirst()", "peekLast()", "poll()", "pollFirst()", "pollLast()" }, result = false)
    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Pure
    public int size();

    public boolean remove(@GuardSatisfied @UnknownSignedness Object o);

    public boolean addAll(Collection<? extends E> c);

    public void clear();

    public String toString();

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(ConcurrentLinkedDeque<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SideEffectFree
    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    private abstract class AbstractItr implements Iterator<E> {

        abstract Node<E> startNode();

        abstract Node<E> nextNode(Node<E> p);

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty AbstractItr this);

        public void remove();
    }

    private class Itr extends AbstractItr {

        Node<E> startNode();

        Node<E> nextNode(Node<E> p);
    }

    private class DescendingItr extends AbstractItr {

        Node<E> startNode();

        Node<E> nextNode(Node<E> p);
    }

    final class CLDSpliterator implements Spliterator<E> {

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    @SideEffectFree
    public Spliterator<E> spliterator();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public void forEach(Consumer<? super E> action);
}
