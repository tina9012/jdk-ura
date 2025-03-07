/*
 * Copyright (c) 2000, 2023, Oracle and/or its affiliates. All rights reserved.
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
package java.nio.channels;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.mustcall.qual.NotOwning;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import jdk.internal.javac.PreviewFeature;

@AnnotatedFor({ "index", "mustcall", "nullness" })
public abstract class FileChannel extends AbstractInterruptibleChannel implements SeekableByteChannel, GatheringByteChannel, ScatteringByteChannel {

    protected FileChannel() {
    }

    public static FileChannel open(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException;

    public static FileChannel open(Path path, OpenOption... options) throws IOException;

    @GTENegativeOne
    public abstract int read(ByteBuffer dst) throws IOException;

    @GTENegativeOne
    public abstract long read(ByteBuffer[] dsts, @IndexOrHigh({ "#1" }) int offset, @IndexOrHigh({ "#1" }) int length) throws IOException;

    @GTENegativeOne
    public final long read(ByteBuffer[] dsts) throws IOException;

    @NonNegative
    public abstract int write(ByteBuffer src) throws IOException;

    @NonNegative
    public abstract long write(ByteBuffer[] srcs, @IndexOrHigh({ "#1" }) int offset, @IndexOrHigh({ "#1" }) int length) throws IOException;

    @NonNegative
    public final long write(ByteBuffer[] srcs) throws IOException;

    @NonNegative
    public abstract long position() throws IOException;

    @NotOwning
    public abstract FileChannel position(@NonNegative long newPosition) throws IOException;

    @NonNegative
    public abstract long size() throws IOException;

    @NotOwning
    public abstract FileChannel truncate(@NonNegative long size) throws IOException;

    public abstract void force(boolean metaData) throws IOException;

    @NonNegative
    public abstract long transferTo(@NonNegative long position, @NonNegative long count, WritableByteChannel target) throws IOException;

    @NonNegative
    public abstract long transferFrom(ReadableByteChannel src, @NonNegative long position, @NonNegative long count) throws IOException;

    @GTENegativeOne
    public abstract int read(ByteBuffer dst, @NonNegative long position) throws IOException;

    @NonNegative
    public abstract int write(ByteBuffer src, @NonNegative long position) throws IOException;

    public static class MapMode {

        public static final MapMode READ_ONLY;

        public static final MapMode READ_WRITE;

        public static final MapMode PRIVATE;

        public String toString();
    }

    public abstract MappedByteBuffer map(MapMode mode, @NonNegative long position, @NonNegative long size) throws IOException;

    public MemorySegment map(MapMode mode, long offset, long size, Arena arena) throws IOException;

    public abstract FileLock lock(@NonNegative long position, @NonNegative long size, boolean shared) throws IOException;

    public final FileLock lock() throws IOException;

    @Nullable
    public abstract FileLock tryLock(@NonNegative long position, @NonNegative long size, boolean shared) throws IOException;

    @Nullable
    public final FileLock tryLock() throws IOException;
}
