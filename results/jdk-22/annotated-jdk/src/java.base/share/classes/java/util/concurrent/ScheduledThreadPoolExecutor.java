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
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor("nullness")
public class ScheduledThreadPoolExecutor extends ThreadPoolExecutor implements ScheduledExecutorService {

    private class ScheduledFutureTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {

        public long getDelay(TimeUnit unit);

        public int compareTo(Delayed other);

        public boolean isPeriodic();

        public boolean cancel(boolean mayInterruptIfRunning);

        public void run();
    }

    boolean canRunInCurrentRunState(RunnableScheduledFuture<?> task);

    void reExecutePeriodic(RunnableScheduledFuture<?> task);

    @Override
    void onShutdown();

    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task);

    protected <V> RunnableScheduledFuture<V> decorateTask(Callable<V> callable, RunnableScheduledFuture<V> task);

    public ScheduledThreadPoolExecutor(int corePoolSize) {
    }

    public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
    }

    public ScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
    }

    public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
    }

    long triggerTime(long delay);

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit);

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit);

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);

    public void execute(Runnable command);

    public Future<?> submit(Runnable task);

    public <T> Future<T> submit(Runnable task, T result);

    public <T> Future<T> submit(Callable<T> task);

    public void setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean value);

    public boolean getContinueExistingPeriodicTasksAfterShutdownPolicy();

    public void setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean value);

    public boolean getExecuteExistingDelayedTasksAfterShutdownPolicy();

    public void setRemoveOnCancelPolicy(boolean value);

    public boolean getRemoveOnCancelPolicy();

    public void shutdown();

    public List<Runnable> shutdownNow();

    public BlockingQueue<Runnable> getQueue();

    static class DelayedWorkQueue extends AbstractQueue<Runnable> implements BlockingQueue<Runnable> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object x);

        public boolean remove(@UnknownSignedness Object x);

        @Pure
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        public int remainingCapacity();

        @Pure
        public RunnableScheduledFuture<?> peek();

        public boolean offer(Runnable x);

        public void put(Runnable e);

        @EnsuresNonEmpty("this")
        public boolean add(Runnable e);

        public boolean offer(Runnable e, long timeout, TimeUnit unit);

        public RunnableScheduledFuture<?> poll();

        public RunnableScheduledFuture<?> take() throws InterruptedException;

        public RunnableScheduledFuture<?> poll(long timeout, TimeUnit unit) throws InterruptedException;

        public void clear();

        public int drainTo(Collection<? super Runnable> c);

        public int drainTo(Collection<? super Runnable> c, int maxElements);

        public Object[] toArray();

        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public Iterator<Runnable> iterator();

        private class Itr implements Iterator<Runnable> {

            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public boolean hasNext();

            @SideEffectsOnly("this")
            public Runnable next(@NonEmpty Itr this);

            public void remove();
        }
    }
}
