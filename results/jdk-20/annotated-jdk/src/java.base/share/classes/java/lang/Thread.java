/*
 * Copyright (c) 1994, 2022, Oracle and/or its affiliates. All rights reserved.
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
package java.lang;

import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.EnsuresLockHeldIf;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.AccessControlContext;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Predicate;
import java.util.stream.Stream;
import jdk.internal.event.ThreadSleepEvent;
import jdk.internal.javac.PreviewFeature;
import jdk.internal.misc.PreviewFeatures;
import jdk.internal.misc.StructureViolationExceptions;
import jdk.internal.misc.TerminatingThreadLocal;
import jdk.internal.misc.Unsafe;
import jdk.internal.misc.VM;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.Continuation;
import jdk.internal.vm.ScopedValueContainer;
import jdk.internal.vm.StackableScope;
import jdk.internal.vm.ThreadContainer;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.Hidden;
import jdk.internal.vm.annotation.IntrinsicCandidate;
import sun.nio.ch.Interruptible;
import sun.security.util.SecurityConstants;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

@AnnotatedFor({ "interning", "lock", "nullness" })
@UsesObjectEquals
public class Thread implements Runnable {

    private static class FieldHolder {
    }

    static Object scopedValueBindings();

    static void setScopedValueBindings(Object bindings);

    @IntrinsicCandidate
    static native Object findScopedValueBindings();

    void inheritScopedValueBindings(ThreadContainer container);

    static void blockedOn(Interruptible b);

    public static final int MIN_PRIORITY;

    public static final int NORM_PRIORITY;

    public static final int MAX_PRIORITY;

    Continuation getContinuation();

    void setContinuation(Continuation cont);

    @IntrinsicCandidate
    static native Thread currentCarrierThread();

    @IntrinsicCandidate
    public static native Thread currentThread();

    @IntrinsicCandidate
    native void setCurrentThread(Thread thread);

    @IntrinsicCandidate
    static native Object[] scopedValueCache();

    @IntrinsicCandidate
    static native void setScopedValueCache(Object[] cache);

    @IntrinsicCandidate
    static native void ensureMaterializedForStackWalk(Object o);

    public static void yield();

    public static void sleep(long millis) throws InterruptedException;

    public static void sleep(long millis, int nanos) throws InterruptedException;

    public static void sleep(Duration duration) throws InterruptedException;

    @IntrinsicCandidate
    public static void onSpinWait();

    private static class ThreadIdentifiers {

        static long next();
    }

    @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
    public static Builder.OfPlatform ofPlatform();

    @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
    public static Builder.OfVirtual ofVirtual();

    @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
    public sealed interface Builder permits Builder.OfPlatform, Builder.OfVirtual, ThreadBuilders.BaseThreadBuilder {

        Builder name(String name);

        Builder name(String prefix, long start);

        Builder allowSetThreadLocals(boolean allow);

        Builder inheritInheritableThreadLocals(boolean inherit);

        Builder uncaughtExceptionHandler(UncaughtExceptionHandler ueh);

        Thread unstarted(Runnable task);

        Thread start(Runnable task);

        ThreadFactory factory();

        @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
        sealed interface OfPlatform extends Builder permits ThreadBuilders.PlatformThreadBuilder {

            @Override
            OfPlatform name(String name);

            @Override
            OfPlatform name(String prefix, long start);

            @Override
            OfPlatform allowSetThreadLocals(boolean allow);

            @Override
            OfPlatform inheritInheritableThreadLocals(boolean inherit);

            @Override
            OfPlatform uncaughtExceptionHandler(UncaughtExceptionHandler ueh);

            OfPlatform group(ThreadGroup group);

            OfPlatform daemon(boolean on);

            default OfPlatform daemon();

            OfPlatform priority(int priority);

            OfPlatform stackSize(long stackSize);
        }

        @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
        sealed interface OfVirtual extends Builder permits ThreadBuilders.VirtualThreadBuilder {

            @Override
            OfVirtual name(String name);

            @Override
            OfVirtual name(String prefix, long start);

            @Override
            OfVirtual allowSetThreadLocals(boolean allow);

            @Override
            OfVirtual inheritInheritableThreadLocals(boolean inherit);

            @Override
            OfVirtual uncaughtExceptionHandler(UncaughtExceptionHandler ueh);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException;

    private static class ThreadNumbering {

        static int next();
    }

    static String genThreadName();

    public Thread() {
    }

    public Thread(@Nullable Runnable task) {
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable task) {
    }

    public Thread(String name) {
    }

    public Thread(@Nullable ThreadGroup group, String name) {
    }

    public Thread(@Nullable Runnable task, String name) {
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable task, String name) {
    }

    public Thread(@Nullable ThreadGroup group, @Nullable Runnable task, String name, long stackSize) {
    }

    public Thread(ThreadGroup group, Runnable task, String name, long stackSize, boolean inheritInheritableThreadLocals) {
    }

    @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
    public static Thread startVirtualThread(Runnable task);

    @PreviewFeature(feature = PreviewFeature.Feature.VIRTUAL_THREADS)
    public final boolean isVirtual();

    public void start();

    void start(ThreadContainer container);

    @Override
    public void run();

    void clearReferences();

    @Deprecated()
    public final void stop();

    public void interrupt();

    public static boolean interrupted();

    @Pure
    public boolean isInterrupted(@GuardSatisfied Thread this);

    final void setInterrupt();

    final void clearInterrupt();

    boolean getAndClearInterrupt();

    @Pure
    public final boolean isAlive(@GuardSatisfied Thread this);

    @Pure
    boolean alive();

    @Deprecated()
    public final void suspend();

    @Deprecated()
    public final void resume();

    public final void setPriority(@UnknownInitialization(java.lang.Thread.class) Thread this, int newPriority);

    void priority(int newPriority);

    public final int getPriority();

    public final synchronized void setName(String name);

    public final String getName();

    @Nullable
    public final ThreadGroup getThreadGroup();

    public static int activeCount();

    public static int enumerate(@PolyNull Thread[] tarray);

    @Deprecated()
    public int countStackFrames();

    public final void join(long millis) throws InterruptedException;

    public final void join(long millis, int nanos) throws InterruptedException;

    public final void join() throws InterruptedException;

    public final boolean join(Duration duration) throws InterruptedException;

    public static void dumpStack();

    public final void setDaemon(@UnknownInitialization Thread this, boolean on);

    void daemon(boolean on);

    @Pure
    public final boolean isDaemon(@GuardSatisfied Thread this);

    @Deprecated()
    public final void checkAccess();

    @SideEffectFree
    public String toString(@GuardSatisfied Thread this);

    @CallerSensitive
    @Nullable
    public ClassLoader getContextClassLoader();

    public void setContextClassLoader(@Nullable ClassLoader cl);

    @EnsuresLockHeldIf(expression = { "#1" }, result = true)
    @ReleasesNoLocks
    public static native boolean holdsLock(Object obj);

    public StackTraceElement[] getStackTrace();

    StackTraceElement[] asyncGetStackTrace();

    public static Map<Thread, StackTraceElement[]> getAllStackTraces();

    private static class Caches {
    }

    static Thread[] getAllThreads();

    @Deprecated()
    public long getId();

    public final long threadId();

    public enum State {

        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED
    }

    public State getState();

    State threadState();

    boolean isTerminated();

    @FunctionalInterface
    public interface UncaughtExceptionHandler {

        void uncaughtException(Thread t, Throwable e);
    }

    public static void setDefaultUncaughtExceptionHandler(@Nullable UncaughtExceptionHandler ueh);

    @Nullable
    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler();

    @Nullable
    public UncaughtExceptionHandler getUncaughtExceptionHandler();

    public void setUncaughtExceptionHandler(@Nullable UncaughtExceptionHandler ueh);

    void uncaughtExceptionHandler(UncaughtExceptionHandler ueh);

    void dispatchUncaughtException(Throwable e);

    @SuppressWarnings("removal")
    private static class Constants {
    }

    static ThreadGroup virtualThreadGroup();

    ThreadContainer threadContainer();

    void setThreadContainer(ThreadContainer container);

    StackableScope headStackableScopes();

    static void setHeadStackableScope(StackableScope scope);
}
