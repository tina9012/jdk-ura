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
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.function.Consumer;
import java.util.function.Predicate;

@AnnotatedFor("nullness")
public class LinkedTransferQueue<E extends Object> extends AbstractQueue<E> implements TransferQueue<E>, java.io.Serializable {

    static final class DualNode implements ForkJoinPool.ManagedBlocker {

        final Object cmpExItem(Object cmp, Object val);

        final DualNode cmpExNext(DualNode cmp, DualNode val);

        final boolean matched();

        final void selfLinkItem();

        final Object await(Object e, long ns, Object blocker, boolean spin);

        public final boolean isReleasable();

        public final boolean block();
    }

    final DualNode cmpExTail(DualNode cmp, DualNode val);

    final DualNode cmpExHead(DualNode cmp, DualNode val);

    final boolean sweepNow();

    final Object xfer(Object e, long ns);

    final boolean tryCasSuccessor(DualNode pred, DualNode c, DualNode p);

    final DualNode skipDeadNodes(DualNode pred, DualNode c, DualNode p, DualNode q);

    final boolean tryMatchData(DualNode p, Object x);

    final DualNode firstDataNode();

    final int countOfMode(boolean data);

    public String toString();

    @PolyNull
    @PolySigned
    public Object[] toArray(LinkedTransferQueue<@PolyNull @PolySigned E> this);

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    final class Itr implements Iterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean hasNext();

        @SideEffectsOnly("this")
        public final E next(@NonEmpty Itr this);

        public void forEachRemaining(Consumer<? super E> action);

        public final void remove();
    }

    final class LTQSpliterator implements Spliterator<E> {

        public Spliterator<E> trySplit();

        public void forEachRemaining(Consumer<? super E> action);

        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super E> action);

        public long estimateSize();

        public int characteristics();
    }

    public Spliterator<E> spliterator();

    public LinkedTransferQueue() {
    }

    public LinkedTransferQueue(Collection<? extends E> c) {
    }

    public void put(E e);

    public boolean offer(E e, long timeout, TimeUnit unit);

    public boolean offer(E e);

    @EnsuresNonEmpty("this")
    public boolean add(E e);

    public boolean tryTransfer(E e);

    public void transfer(E e) throws InterruptedException;

    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    @SuppressWarnings("unchecked")
    public E take() throws InterruptedException;

    @SuppressWarnings("unchecked")
    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    @SuppressWarnings("unchecked")
    @Nullable
    public E poll();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    public Iterator<E> iterator();

    @Pure
    @Nullable
    public E peek();

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    public boolean hasWaitingConsumer();

    @Pure
    public int size();

    public int getWaitingConsumerCount();

    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public int remainingCapacity();

    public boolean removeIf(Predicate<? super E> filter);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public void clear();

    @SuppressWarnings("unchecked")
    void forEachFrom(Consumer<? super E> action, DualNode p);

    public void forEach(Consumer<? super E> action);
}
