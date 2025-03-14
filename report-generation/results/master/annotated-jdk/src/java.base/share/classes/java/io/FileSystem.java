/*
 * Copyright (c) 1998, 2019, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Native;

@AnnotatedFor({ "index", "interning" })
@UsesObjectEquals
abstract class FileSystem {

    public abstract char getSeparator();

    public abstract char getPathSeparator();

    public abstract String normalize(String path);

    @IndexOrHigh({ "#1" })
    public abstract int prefixLength(String path);

    public abstract String resolve(String parent, String child);

    public abstract String getDefaultParent();

    public abstract String fromURIPath(String path);

    public abstract boolean isAbsolute(File f);

    public abstract String resolve(File f);

    public abstract String canonicalize(String path) throws IOException;

    @Native
    @SignedPositive
    public static final int BA_EXISTS;

    @Native
    @SignedPositive
    public static final int BA_REGULAR;

    @Native
    @SignedPositive
    public static final int BA_DIRECTORY;

    @Native
    @SignedPositive
    public static final int BA_HIDDEN;

    public abstract int getBooleanAttributes(File f);

    public boolean hasBooleanAttributes(File f, int attributes);

    @Native
    @SignedPositive
    public static final int ACCESS_READ;

    @Native
    @SignedPositive
    public static final int ACCESS_WRITE;

    @Native
    @SignedPositive
    public static final int ACCESS_EXECUTE;

    public abstract boolean checkAccess(File f, int access);

    public abstract boolean setPermission(File f, int access, boolean enable, boolean owneronly);

    public abstract long getLastModifiedTime(File f);

    public abstract long getLength(File f);

    public abstract boolean createFileExclusively(String pathname) throws IOException;

    public abstract boolean delete(File f);

    public abstract String[] list(File f);

    public abstract boolean createDirectory(File f);

    public abstract boolean rename(File f1, File f2);

    public abstract boolean setLastModifiedTime(File f, long time);

    public abstract boolean setReadOnly(File f);

    public abstract File[] listRoots();

    @Native
    public static final int SPACE_TOTAL;

    @Native
    public static final int SPACE_FREE;

    @Native
    public static final int SPACE_USABLE;

    public abstract long getSpace(File f, int t);

    public abstract int getNameMax(String path);

    public abstract int compare(File f1, File f2);

    public abstract int hashCode(File f);
}
