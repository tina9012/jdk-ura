/*
 * Copyright (c) 1994, 2021, Oracle and/or its affiliates. All rights reserved.
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
package java.io;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.lock.qual.ReleasesNoLocks;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.regex.qual.Regex;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import sun.security.action.GetPropertyAction;

@CFComment({ "nullness:", "This @EnsuresNonNullIfTrue is not true, since the list methods also", "return null in the case of an IO error (instead of throwing IOException).", "EnsuresNonNullIf(expression={\"list()\",\"list(FilenameFilter)\",\"listFiles()\",\"listFiles(FilenameFilter)\",\"listFiles(FileFilter)\"}, result=true)\"" })
@AnnotatedFor({ "index", "initialization", "interning", "lock", "nullness" })
public class File implements Serializable, Comparable<File> {

    @Pure
    final boolean isInvalid();

    @Pure
    int getPrefixLength();

    public static final char separatorChar;

    @Interned
    public static final String separator;

    public static final char pathSeparatorChar;

    @Interned
    @Regex
    public static final String pathSeparator;

    @SideEffectFree
    public File(String pathname) {
    }

    @SideEffectFree
    public File(@Nullable String parent, String child) {
    }

    @SideEffectFree
    public File(@Nullable File parent, String child) {
    }

    @SideEffectFree
    public File(URI uri) {
    }

    @SideEffectFree
    public String getName();

    @Pure
    @SideEffectFree
    @Nullable
    public String getParent(@GuardSatisfied File this);

    @Pure
    @SideEffectFree
    @Nullable
    public File getParentFile(@GuardSatisfied File this);

    @SideEffectFree
    public String getPath();

    @Pure
    public boolean isAbsolute(@GuardSatisfied File this);

    @SideEffectFree
    public String getAbsolutePath();

    @SideEffectFree
    public File getAbsoluteFile();

    @SideEffectFree
    @ReleasesNoLocks
    public String getCanonicalPath() throws IOException;

    @SideEffectFree
    public File getCanonicalFile() throws IOException;

    @SideEffectFree
    @Deprecated
    public URL toURL() throws MalformedURLException;

    @SideEffectFree
    public URI toURI();

    @SideEffectFree
    public boolean canRead();

    @SideEffectFree
    public boolean canWrite();

    @SideEffectFree
    public boolean exists();

    @SideEffectFree
    public boolean isDirectory(@GuardSatisfied File this);

    @SideEffectFree
    public boolean isFile(@GuardSatisfied File this);

    @SideEffectFree
    public boolean isHidden(@GuardSatisfied File this);

    @SideEffectFree
    public long lastModified();

    @SideEffectFree
    @NonNegative
    public long length();

    public boolean createNewFile() throws IOException;

    public boolean delete();

    public void deleteOnExit();

    @SideEffectFree
    public String @Nullable [] list();

    @SideEffectFree
    public String @Nullable [] list(@Nullable FilenameFilter filter);

    @SideEffectFree
    public File @Nullable [] listFiles();

    @SideEffectFree
    public File @Nullable [] listFiles(@Nullable FilenameFilter filter);

    @SideEffectFree
    public File @Nullable [] listFiles(@Nullable FileFilter filter);

    public boolean mkdir();

    public boolean mkdirs();

    public boolean renameTo(File dest);

    public boolean setLastModified(long time);

    public boolean setReadOnly();

    public boolean setWritable(boolean writable, boolean ownerOnly);

    public boolean setWritable(boolean writable);

    public boolean setReadable(boolean readable, boolean ownerOnly);

    public boolean setReadable(boolean readable);

    public boolean setExecutable(boolean executable, boolean ownerOnly);

    public boolean setExecutable(boolean executable);

    @SideEffectFree
    public boolean canExecute();

    @SideEffectFree
    public static File @Nullable [] listRoots();

    @SideEffectFree
    @NonNegative
    public long getTotalSpace();

    @SideEffectFree
    @NonNegative
    public long getFreeSpace();

    @SideEffectFree
    @NonNegative
    public long getUsableSpace();

    private static class TempDirectory {

        static File location();

        @SuppressWarnings("removal")
        static File generateFile(String prefix, String suffix, File dir) throws IOException;
    }

    public static File createTempFile(String prefix, @Nullable String suffix, @Nullable File directory) throws IOException;

    public static File createTempFile(String prefix, @Nullable String suffix) throws IOException;

    @Pure
    public int compareTo(@GuardSatisfied File this, @GuardSatisfied File pathname);

    @Pure
    public boolean equals(@GuardSatisfied File this, @GuardSatisfied @Nullable Object obj);

    @Pure
    public int hashCode(@GuardSatisfied File this);

    @SideEffectFree
    public String toString(@GuardSatisfied File this);

    @SideEffectFree
    public Path toPath();
}
