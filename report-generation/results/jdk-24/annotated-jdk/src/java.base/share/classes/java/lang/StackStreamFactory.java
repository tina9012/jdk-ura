/*
 * Copyright (c) 2015, 2024, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import jdk.internal.reflect.MethodAccessor;
import jdk.internal.reflect.ConstructorAccessor;
import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.lang.annotation.Native;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;
import static java.lang.StackStreamFactory.WalkerState.*;

final class StackStreamFactory {

    static <T> StackFrameTraverser<T> makeStackTraverser(StackWalker walker, Function<? super Stream<StackFrame>, ? extends T> function);

    static CallerClassFinder makeCallerFinder(StackWalker walker);

    abstract static class AbstractStackWalker<R, T> {

        protected final StackWalker walker;

        protected final Thread thread;

        protected final int maxDepth;

        protected final int mode;

        protected int depth;

        protected FrameBuffer<? extends T> frameBuffer;

        protected long anchor;

        protected final ContinuationScope contScope;

        protected Continuation continuation;

        protected AbstractStackWalker(StackWalker walker, int mode) {
        }

        protected AbstractStackWalker(StackWalker walker, int mode, int maxDepth) {
        }

        protected abstract R consumeFrames();

        protected abstract void initFrameBuffer();

        protected abstract int batchSize(int lastBatchSize);

        protected int getNextBatchSize();

        final void checkState(WalkerState state);

        final R walk();

        final Class<?> peekFrame();

        @SideEffectsOnly("this")
        final Class<?> nextFrame();

        @Pure
        final boolean hasNext();
    }

    static class StackFrameTraverser<T> extends AbstractStackWalker<T, StackFrame> implements Spliterator<StackFrame> {

        StackFrame nextStackFrame();

        @Override
        protected T consumeFrames();

        @Override
        protected void initFrameBuffer();

        @Override
        protected int batchSize(int lastBatchSize);

        @Override
        public Spliterator<StackFrame> trySplit();

        @Override
        public long estimateSize();

        @Override
        public int characteristics();

        @Override
        public void forEachRemaining(Consumer<? super StackFrame> action);

        @Override
        public boolean tryAdvance(Consumer<? super StackFrame> action);
    }

    static class StackFrameBuffer<T extends ClassFrameInfo> extends FrameBuffer<T> {

        @Override
        T[] frames();

        @SuppressWarnings("unchecked")
        T[] allocateArray(int size);

        T[] fill(T[] array, int startIndex, int size);

        @Override
        void resize(int size);

        @Override
        T nextStackFrame();

        @Override
        final Class<?> at(int index);
    }

    static class ClassFrameBuffer extends StackFrameBuffer<ClassFrameInfo> {

        @Override
        ClassFrameInfo[] allocateArray(int size);

        @Override
        ClassFrameInfo[] fill(ClassFrameInfo[] array, int startIndex, int size);
    }

    static final class CallerClassFinder extends AbstractStackWalker<Integer, ClassFrameInfo> {

        Class<?> findCaller();

        @Override
        protected Integer consumeFrames();

        @Override
        protected void initFrameBuffer();

        @Override
        protected int batchSize(int lastBatchSize);

        @Override
        protected int getNextBatchSize();
    }

    static final class LiveStackInfoTraverser<T> extends StackFrameTraverser<T> {

        @Override
        protected void initFrameBuffer();
    }

    abstract static class FrameBuffer<F> {

        abstract F[] frames();

        abstract void resize(int size);

        abstract Class<?> at(int index);

        int startIndex();

        F nextStackFrame();

        final int currentBatchSize();

        @EnsuresNonEmptyIf(result = false, expression = "this")
        final boolean isEmpty();

        final int numFrames();

        final void freeze();

        final boolean isActive();

        final boolean isFull();

        final boolean isAtBottom();

        final Class<?> next();

        final Class<?> get();

        final int getIndex();

        final void setBatch(int depth, int startIndex, int numFrames);

        final void check(int skipFrames);
    }
}
