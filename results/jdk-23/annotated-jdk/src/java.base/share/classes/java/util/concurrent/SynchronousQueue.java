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
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

@AnnotatedFor({ "nullness" })
public class SynchronousQueue<E extends @NonNull Object> extends AbstractQueue<E> implements BlockingQueue<E>, java.io.Serializable {

    @SuppressWarnings("serial")
    static final class Transferer<E> extends LinkedTransferQueue<E> {

        final Object xferLifo(Object e, long ns);
    }

    public SynchronousQueue() {
    }

    public SynchronousQueue(boolean fair) {
    }

    public void put(E e) throws InterruptedException;

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

    public boolean offer(E e);

    @SuppressWarnings("unchecked")
    public E take() throws InterruptedException;

    @SuppressWarnings("unchecked")
    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    @SuppressWarnings("unchecked")
    @Nullable
    public E poll();

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Pure
    public int size();

    public int remainingCapacity();

    public void clear();

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    @Pure
    public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

    public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

    @Pure
    @Nullable
    public E peek();

    @SideEffectFree
    public Iterator<E> iterator();

    @SideEffectFree
    public Spliterator<E> spliterator();

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(SynchronousQueue<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public String toString();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    @SuppressWarnings("serial")
    static class WaitQueue implements java.io.Serializable {
    }

    static class LifoWaitQueue extends WaitQueue {
    }

    static class FifoWaitQueue extends WaitQueue {
    }
}
