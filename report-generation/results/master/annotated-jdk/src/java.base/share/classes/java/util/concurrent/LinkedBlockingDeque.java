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
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor({ "nullness" })
public class LinkedBlockingDeque<E extends Object> extends AbstractQueue<E> implements BlockingDeque<E>, java.io.Serializable {

    static final class Node<E> {
    }

    public LinkedBlockingDeque() {
    }

    public LinkedBlockingDeque(int capacity) {
    }

    public LinkedBlockingDeque(Collection<? extends E> c) {
    }

    void unlink(Node<E> x);

    public void addFirst(E e);

    public void addLast(E e);

    public boolean offerFirst(E e);

    public boolean offerLast(E e);

    public void putFirst(E e) throws InterruptedException;

    public void putLast(E e) throws InterruptedException;

    public boolean offerFirst(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public boolean offerLast(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public E removeFirst(@NonEmpty LinkedBlockingDeque<E> this);

    public E removeLast(@NonEmpty LinkedBlockingDeque<E> this);

    @Nullable
    public E pollFirst();

    @Nullable
    public E pollLast();

    public E takeFirst() throws InterruptedException;

    public E takeLast() throws InterruptedException;

    @Nullable
    public E pollFirst(long timeout, TimeUnit unit) throws InterruptedException;

    @Nullable
    public E pollLast(long timeout, TimeUnit unit) throws InterruptedException;

    public E getFirst(@NonEmpty LinkedBlockingDeque<E> this);

    public E getLast(@NonEmpty LinkedBlockingDeque<E> this);

    @Pure
    @Nullable
    public E peekFirst();

    @Pure
    @Nullable
    public E peekLast();

    public boolean removeFirstOccurrence(Object o);

    public boolean removeLastOccurrence(Object o);

    @EnsuresNonEmpty("this")
    public boolean add(E e);

    public boolean offer(E e);

    public void put(E e) throws InterruptedException;

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public E remove(@NonEmpty LinkedBlockingDeque<E> this);

    @Nullable
    public E poll();

    public E take() throws InterruptedException;

    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    public E element(@NonEmpty LinkedBlockingDeque<E> this);

    @Pure
    @Nullable
    public E peek();

    public int remainingCapacity();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    public void push(E e);

    public E pop(@NonEmpty LinkedBlockingDeque<E> this);

    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    public int size();

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public boolean addAll(Collection<? extends E> c);

    @SuppressWarnings("unchecked")
    @PolyNull
    @PolySigned
    public Object[] toArray(LinkedBlockingDeque<@PolyNull @PolySigned E> this);

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public String toString();

    public void clear();

    Node<E> succ(Node<E> p);

    public Iterator<E> iterator();

    public Iterator<E> descendingIterator();

    private abstract class AbstractItr implements Iterator<E> {

        abstract Node<E> firstNode();

        abstract Node<E> nextNode(Node<E> n);

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty AbstractItr this);

        public void forEachRemaining(Consumer<? super E> action);

        public void remove();
    }

    private class Itr extends AbstractItr {

        Node<E> firstNode();

        Node<E> nextNode(Node<E> n);
    }

    private class DescendingItr extends AbstractItr {

        Node<E> firstNode();

        Node<E> nextNode(Node<E> n);
    }

    private final class LBDSpliterator implements Spliterator<E> {

        public long estimateSize();

        public Spliterator<E> trySplit();

        public boolean tryAdvance(Consumer<? super E> action);

        public void forEachRemaining(Consumer<? super E> action);

        public int characteristics();
    }

    public Spliterator<E> spliterator();

    public void forEach(Consumer<? super E> action);

    void forEachFrom(Consumer<? super E> action, Node<E> p);

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    void checkInvariants();
}
