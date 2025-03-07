/*
 * Copyright (c) 2008, 2024, Oracle and/or its affiliates. All rights reserved.
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
package sun.nio.fs;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.CharacterCodingException;
import java.nio.file.DirectoryStream;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.ProviderMismatchException;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.Objects;
import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.util.ArraysSupport;
import static sun.nio.fs.UnixConstants.*;
import static sun.nio.fs.UnixNativeDispatcher.*;

class UnixPath implements Path {

    static String normalizeAndCheck(String input);

    byte[] asByteArray();

    byte[] getByteArrayForSysCalls();

    String getPathForExceptionMessage();

    String getPathForPermissionCheck();

    static UnixPath toUnixPath(Path obj);

    boolean isEmpty();

    @Override
    public UnixFileSystem getFileSystem();

    @Override
    public UnixPath getRoot();

    @Override
    public UnixPath getFileName();

    @Override
    public UnixPath getParent();

    @Override
    public int getNameCount();

    @Override
    public UnixPath getName(int index);

    @Override
    public UnixPath subpath(int beginIndex, int endIndex);

    @Override
    public boolean isAbsolute();

    @Override
    public UnixPath resolve(Path obj);

    UnixPath resolve(byte[] other);

    @Override
    public UnixPath resolve(Path first, Path... more);

    @Override
    public UnixPath relativize(Path obj);

    @Override
    public UnixPath normalize();

    @Override
    public boolean startsWith(Path other);

    @Override
    public boolean endsWith(Path other);

    @Override
    public int compareTo(Path other);

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object ob);

    @Override
    public int hashCode();

    @Override
    public String toString();

    int openForAttributeAccess(boolean followLinks) throws UnixException;

    void checkRead();

    void checkWrite();

    void checkDelete();

    @Override
    public UnixPath toAbsolutePath();

    @Override
    public Path toRealPath(LinkOption... options) throws IOException;

    @Override
    public URI toUri();

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException;
}
