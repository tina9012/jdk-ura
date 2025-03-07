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

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.dataflow.qual.Pure;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.AccessControlContext;
import java.security.Permission;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import jdk.internal.access.JavaUtilConcurrentFJPAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.misc.Unsafe;
import jdk.internal.vm.SharedThreadContainer;

public class ForkJoinPool extends AbstractExecutorService {

    public static interface ForkJoinWorkerThreadFactory {

        public ForkJoinWorkerThread newThread(ForkJoinPool pool);
    }

    static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

        public final ForkJoinWorkerThread newThread(ForkJoinPool pool);

        @SuppressWarnings("removal")
        static ForkJoinWorkerThread newRegularWithACC(ForkJoinPool pool);

        @SuppressWarnings("removal")
        static ForkJoinWorkerThread newCommonWithACC(ForkJoinPool pool);
    }

    static final class WorkQueue {

        static ForkJoinTask<?> getAndClearSlot(ForkJoinTask<?>[] a, int i);

        static boolean casSlotToNull(ForkJoinTask<?>[] a, int i, ForkJoinTask<?> c);

        final void forcePhaseActive();

        final int getAndSetAccess(int v);

        final void releaseAccess();

        final int getPoolIndex();

        final int queueSize();

        final void push(ForkJoinTask<?> task, ForkJoinPool pool, boolean signalIfEmpty);

        final ForkJoinTask<?> nextLocalTask(int fifo);

        final ForkJoinTask<?> nextLocalTask();

        final boolean tryUnpush(ForkJoinTask<?> task, boolean owned);

        @Pure
        final ForkJoinTask<?> peek();

        final ForkJoinTask<?> poll(ForkJoinPool pool);

        final ForkJoinTask<?> tryPoll();

        final void topLevelExec(ForkJoinTask<?> task, WorkQueue src);

        final int tryRemoveAndExec(ForkJoinTask<?> task, boolean owned);

        final int helpComplete(ForkJoinTask<?> task, boolean owned, int limit);

        final void helpAsyncBlocker(ManagedBlocker blocker);

        final boolean isApparentlyUnblocked();

        final void setClearThreadLocals();
    }

    public static final ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory;

    final String nextWorkerThreadName();

    final void registerWorker(WorkQueue w);

    final void deregisterWorker(ForkJoinWorkerThread wt, Throwable ex);

    final void signalWork();

    final void runWorker(WorkQueue w);

    final void uncompensate();

    final int helpJoin(ForkJoinTask<?> task, WorkQueue w, boolean timed);

    final int helpComplete(ForkJoinTask<?> task, WorkQueue w, boolean owned, boolean timed);

    static final int helpQuiescePool(ForkJoinPool pool, long nanos, boolean interruptible);

    final ForkJoinTask<?> nextTaskFor(WorkQueue w);

    final WorkQueue submissionQueue(boolean isSubmit);

    static WorkQueue commonQueue();

    final WorkQueue externalQueue();

    static void helpAsyncBlocker(Executor e, ManagedBlocker blocker);

    static int getSurplusQueuedTaskCount();

    public ForkJoinPool() {
    }

    public ForkJoinPool(int parallelism) {
    }

    public ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, UncaughtExceptionHandler handler, boolean asyncMode) {
    }

    public ForkJoinPool(int parallelism, ForkJoinWorkerThreadFactory factory, UncaughtExceptionHandler handler, boolean asyncMode, int corePoolSize, int maximumPoolSize, int minimumRunnable, Predicate<? super ForkJoinPool> saturate, long keepAliveTime, TimeUnit unit) {
    }

    public static ForkJoinPool commonPool();

    public <T> T invoke(ForkJoinTask<T> task);

    public void execute(ForkJoinTask<?> task);

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Runnable task);

    public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task);

    @Override
    public <T> ForkJoinTask<T> submit(Callable<T> task);

    @Override
    public <T> ForkJoinTask<T> submit(Runnable task, T result);

    @Override
    @SuppressWarnings("unchecked")
    public ForkJoinTask<?> submit(Runnable task);

    public <T> ForkJoinTask<T> externalSubmit(ForkJoinTask<T> task);

    public <T> ForkJoinTask<T> lazySubmit(ForkJoinTask<T> task);

    public int setParallelism(int size);

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks);

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;

    static final class InvokeAnyRoot<E> extends ForkJoinTask<E> {

        final void tryComplete(Callable<E> c);

        public final boolean exec();

        public final E getRawResult();

        public final void setRawResult(E v);
    }

    static final class InvokeAnyTask<E> extends ForkJoinTask<E> {

        public final boolean exec();

        public final boolean cancel(boolean mayInterruptIfRunning);

        public final void setRawResult(E v);

        public final E getRawResult();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    public ForkJoinWorkerThreadFactory getFactory();

    public UncaughtExceptionHandler getUncaughtExceptionHandler();

    public int getParallelism();

    public static int getCommonPoolParallelism();

    public int getPoolSize();

    public boolean getAsyncMode();

    public int getRunningThreadCount();

    public int getActiveThreadCount();

    public boolean isQuiescent();

    public long getStealCount();

    public long getQueuedTaskCount();

    public int getQueuedSubmissionCount();

    public boolean hasQueuedSubmissions();

    protected ForkJoinTask<?> pollSubmission();

    protected int drainTasksTo(Collection<? super ForkJoinTask<?>> c);

    public String toString();

    public void shutdown();

    public List<Runnable> shutdownNow();

    public boolean isTerminated();

    public boolean isTerminating();

    public boolean isShutdown();

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;

    public boolean awaitQuiescence(long timeout, TimeUnit unit);

    @Override
    public void close();

    public static interface ManagedBlocker {

        boolean block() throws InterruptedException;

        boolean isReleasable();
    }

    public static void managedBlock(ManagedBlocker blocker) throws InterruptedException;

    void endCompensatedBlock(long post);

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value);

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable);
}
