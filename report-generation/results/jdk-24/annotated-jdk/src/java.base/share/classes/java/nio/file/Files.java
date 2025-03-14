/*
 * Copyright (c) 2007, 2024, Oracle and/or its affiliates. All rights reserved.
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
package java.nio.file;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.mustcall.qual.MustCall;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.spi.FileSystemProvider;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.util.ArraysSupport;
import sun.nio.ch.FileChannelImpl;
import sun.nio.cs.UTF_8;
import sun.nio.fs.AbstractFileSystemProvider;

@AnnotatedFor({ "interning", "mustcall", "nullness", "signedness" })
@UsesObjectEquals
public final class Files {

    @ReleasesNoLocks
    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static SeekableByteChannel newByteChannel(Path path, OpenOption... options) throws IOException;

    private static class AcceptAllFilter implements DirectoryStream.Filter<Path> {

        @Override
        @ReleasesNoLocks
        public boolean accept(Path entry);
    }

    @ReleasesNoLocks
    @MustCall("close")
    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException;

    @ReleasesNoLocks
    @MustCall("close")
    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) throws IOException;

    @ReleasesNoLocks
    @MustCall("close")
    public static DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException;

    @ReleasesNoLocks
    public static Path createFile(Path path, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createTempFile(Path dir, @Nullable String prefix, @Nullable String suffix, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createTempFile(@Nullable String prefix, @Nullable String suffix, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createTempDirectory(Path dir, @Nullable String prefix, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createTempDirectory(@Nullable String prefix, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs) throws IOException;

    @ReleasesNoLocks
    public static Path createLink(Path link, Path existing) throws IOException;

    @ReleasesNoLocks
    public static void delete(Path path) throws IOException;

    @ReleasesNoLocks
    public static boolean deleteIfExists(Path path) throws IOException;

    @ReleasesNoLocks
    public static Path copy(Path source, Path target, CopyOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path move(Path source, Path target, CopyOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path readSymbolicLink(Path link) throws IOException;

    @ReleasesNoLocks
    public static FileStore getFileStore(Path path) throws IOException;

    @SideEffectFree
    public static boolean isSameFile(Path path, Path path2) throws IOException;

    @ReleasesNoLocks
    public static long mismatch(Path path, Path path2) throws IOException;

    @SideEffectFree
    public static boolean isHidden(Path path) throws IOException;

    private static class FileTypeDetectors {
    }

    @ReleasesNoLocks
    @Nullable
    public static String probeContentType(Path path) throws IOException;

    @ReleasesNoLocks
    @Nullable
    public static <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options);

    @ReleasesNoLocks
    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException;

    @CFComment({ "nullness: The nullness of the return type is unclear" })
    @ReleasesNoLocks
    public static Object getAttribute(Path path, String attribute, LinkOption... options) throws IOException;

    @CFComment({ "nullness: The nullness of the returned map's values is unclear" })
    @ReleasesNoLocks
    public static Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException;

    @ReleasesNoLocks
    public static Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path setPosixFilePermissions(Path path, Set<PosixFilePermission> perms) throws IOException;

    @ReleasesNoLocks
    public static UserPrincipal getOwner(Path path, LinkOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path setOwner(Path path, UserPrincipal owner) throws IOException;

    @SideEffectFree
    public static boolean isSymbolicLink(Path path);

    @SideEffectFree
    public static boolean isDirectory(Path path, LinkOption... options);

    @SideEffectFree
    public static boolean isRegularFile(Path path, LinkOption... options);

    @ReleasesNoLocks
    public static FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path setLastModifiedTime(Path path, FileTime time) throws IOException;

    @ReleasesNoLocks
    public static long size(Path path) throws IOException;

    @SideEffectFree
    public static boolean exists(Path path, LinkOption... options);

    @SideEffectFree
    public static boolean notExists(Path path, LinkOption... options);

    @SideEffectFree
    public static boolean isReadable(Path path);

    @SideEffectFree
    public static boolean isWritable(Path path);

    @SideEffectFree
    public static boolean isExecutable(Path path);

    @ReleasesNoLocks
    public static Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) throws IOException;

    @ReleasesNoLocks
    public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor) throws IOException;

    @ReleasesNoLocks
    public static BufferedReader newBufferedReader(Path path, Charset cs) throws IOException;

    @ReleasesNoLocks
    public static BufferedReader newBufferedReader(Path path) throws IOException;

    @ReleasesNoLocks
    public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static long copy(InputStream in, Path target, CopyOption... options) throws IOException;

    @ReleasesNoLocks
    public static long copy(Path source, OutputStream out) throws IOException;

    @SideEffectFree
    public static byte[] readAllBytes(Path path) throws IOException;

    @SideEffectFree
    public static String readString(Path path) throws IOException;

    @SideEffectFree
    public static String readString(Path path, Charset cs) throws IOException;

    @SideEffectFree
    public static List<String> readAllLines(Path path, Charset cs) throws IOException;

    @SideEffectFree
    public static List<String> readAllLines(Path path) throws IOException;

    @ReleasesNoLocks
    public static Path write(Path path, @PolySigned byte[] bytes, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path write(Path path, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path writeString(Path path, CharSequence csq, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    public static Path writeString(Path path, CharSequence csq, Charset cs, OpenOption... options) throws IOException;

    @ReleasesNoLocks
    @MustCall("close")
    public static Stream<Path> list(Path dir) throws IOException;

    @ReleasesNoLocks
    @MustCall("close")
    public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) throws IOException;

    @ReleasesNoLocks
    @MustCall("close")
    public static Stream<Path> walk(Path start, FileVisitOption... options) throws IOException;

    @ReleasesNoLocks
    @MustCall("close")
    public static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException;

    @SideEffectFree
    @MustCall("close")
    public static Stream<String> lines(Path path, Charset cs) throws IOException;

    @SideEffectFree
    @MustCall("close")
    public static Stream<String> lines(Path path) throws IOException;
}
