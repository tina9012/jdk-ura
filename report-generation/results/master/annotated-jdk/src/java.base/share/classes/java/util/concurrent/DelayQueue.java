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

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "nullness" })
public class DelayQueue<E extends @NonNull Delayed> extends AbstractQueue<E> implements BlockingQueue<E> {

    public DelayQueue() {
    }

    public DelayQueue(Collection<? extends E> c) {
    }

    @EnsuresNonEmpty("this")
    public boolean add(E e);

    public boolean offer(E e);

    public void put(E e);

    public boolean offer(E e, long timeout, TimeUnit unit);

    @Nullable
    public E poll();

    public E take() throws InterruptedException;

    @Nullable
    public E poll(long timeout, TimeUnit unit) throws InterruptedException;

    @Pure
    @Nullable
    public E peek();

    @Pure
    public int size();

    public int drainTo(Collection<? super E> c);

    public int drainTo(Collection<? super E> c, int maxElements);

    public void clear();

    public int remainingCapacity();

    @SideEffectFree
    @PolyNull
    @PolySigned
    public Object[] toArray(DelayQueue<@PolyNull @PolySigned E> this);

    @SideEffectFree
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    public boolean remove(@UnknownSignedness Object o);

    void removeEQ(Object o);

    @SideEffectFree
    public Iterator<E> iterator();

    private class Itr implements Iterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SuppressWarnings("unchecked")
        @SideEffectsOnly("this")
        public E next(@NonEmpty Itr this);

        public void remove();
    }
}
