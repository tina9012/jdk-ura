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

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.concurrent.locks.LockSupport;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class ForkJoinTask<V> implements Future<V>, Serializable {

    static final class Aux {

        final boolean casNext(Aux c, Aux v);
    }

    final int trySetCancelled();

    final boolean trySetThrown(Throwable ex);

    void onAuxExceptionSet(Throwable ex);

    final void trySetException(Throwable ex);

    final void doExec();

    static void rethrow(Throwable ex);

    @SuppressWarnings("unchecked")
    static <T extends Throwable> void uncheckedThrow(Throwable t) throws T;

    final int setForkJoinTaskStatusMarkerBit();

    final int getForkJoinTaskStatusMarkerBit();

    public ForkJoinTask() {
    }

    public final ForkJoinTask<V> fork();

    public final V join();

    public final V invoke();

    public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2);

    public static void invokeAll(ForkJoinTask<?>... tasks);

    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks);

    public boolean cancel(boolean mayInterruptIfRunning);

    public final boolean isDone();

    public final boolean isCancelled();

    public final boolean isCompletedAbnormally();

    public final boolean isCompletedNormally();

    @Override
    public State state();

    @Override
    public V resultNow();

    @Override
    public Throwable exceptionNow();

    public final Throwable getException();

    public void completeExceptionally(Throwable ex);

    public void complete(V value);

    public final void quietlyComplete();

    public final V get() throws InterruptedException, ExecutionException;

    public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    public final void quietlyJoin();

    public final void quietlyInvoke();

    public final boolean quietlyJoin(long timeout, TimeUnit unit) throws InterruptedException;

    public final boolean quietlyJoinUninterruptibly(long timeout, TimeUnit unit);

    final void quietlyJoinPoolInvokeAllTask(long deadline) throws InterruptedException;

    public static void helpQuiesce();

    public void reinitialize();

    public static ForkJoinPool getPool();

    public static boolean inForkJoinPool();

    public boolean tryUnfork();

    public static int getQueuedTaskCount();

    public static int getSurplusQueuedTaskCount();

    public abstract V getRawResult();

    protected abstract void setRawResult(V value);

    protected abstract boolean exec();

    protected static ForkJoinTask<?> peekNextLocalTask();

    protected static ForkJoinTask<?> pollNextLocalTask();

    protected static ForkJoinTask<?> pollTask();

    protected static ForkJoinTask<?> pollSubmission();

    public final short getForkJoinTaskTag();

    public final short setForkJoinTaskTag(short newValue);

    public final boolean compareAndSetForkJoinTaskTag(short expect, short update);

    public static ForkJoinTask<?> adapt(Runnable runnable);

    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result);

    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable);

    public static <T> ForkJoinTask<T> adaptInterruptible(Callable<? extends T> callable);

    public static <T> ForkJoinTask<T> adaptInterruptible(Runnable runnable, T result);

    public static ForkJoinTask<?> adaptInterruptible(Runnable runnable);

    static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        public final T getRawResult();

        public final void setRawResult(T v);

        public final boolean exec();

        public final void run();

        public String toString();
    }

    static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {

        public final Void getRawResult();

        public final void setRawResult(Void v);

        public final boolean exec();

        public final void run();

        public String toString();
    }

    static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        public final T getRawResult();

        public final void setRawResult(T v);

        public final boolean exec();

        public final void run();

        public String toString();
    }

    abstract static class InterruptibleTask<T> extends ForkJoinTask<T> implements RunnableFuture<T> {

        abstract T compute() throws Exception;

        public final boolean exec();

        public boolean cancel(boolean mayInterruptIfRunning);

        public final void run();

        Object adaptee();

        public String toString();
    }

    static final class AdaptedInterruptibleCallable<T> extends InterruptibleTask<T> {

        public final T getRawResult();

        public final void setRawResult(T v);

        final T compute() throws Exception;

        final Object adaptee();
    }

    static final class AdaptedInterruptibleRunnable<T> extends InterruptibleTask<T> {

        public final T getRawResult();

        public final void setRawResult(T v);

        final T compute();

        final Object adaptee();
    }

    static final class RunnableExecuteAction extends InterruptibleTask<Void> {

        public final Void getRawResult();

        public final void setRawResult(Void v);

        final Void compute();

        final Object adaptee();

        void onAuxExceptionSet(Throwable ex);
    }

    @SuppressWarnings("serial")
    static final class InvokeAnyRoot<T> extends InterruptibleTask<T> {

        final void tryComplete(InvokeAnyTask<T> f, T v, Throwable ex, boolean completed);

        public final T compute();

        public final T getRawResult();

        public final void setRawResult(T v);

        final T invokeAny(Collection<? extends Callable<T>> tasks, ForkJoinPool pool, boolean timed, long nanos) throws InterruptedException, ExecutionException, TimeoutException;
    }

    @SuppressWarnings("serial")
    static final class InvokeAnyTask<T> extends InterruptibleTask<Void> {

        final Void compute() throws Exception;

        public final boolean cancel(boolean mayInterruptIfRunning);

        final void onRootCompletion();

        public final Void getRawResult();

        public final void setRawResult(Void v);

        final Object adaptee();
    }
}
