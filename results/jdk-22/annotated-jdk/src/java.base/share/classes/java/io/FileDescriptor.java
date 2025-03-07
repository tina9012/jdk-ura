/*
 * Copyright (c) 2003, 2023, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jdk.internal.access.JavaIOFileDescriptorAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.misc.Blocker;
import jdk.internal.ref.PhantomCleanable;

@AnnotatedFor({ "index", "initialization", "nullness" })
public final class FileDescriptor {

    public FileDescriptor() {
    }

    public static final FileDescriptor in;

    public static final FileDescriptor out;

    public static final FileDescriptor err;

    public boolean valid();

    public void sync() throws SyncFailedException;

    synchronized void set(int fd);

    void setHandle(long handle);

    synchronized void registerCleanup(PhantomCleanable<FileDescriptor> cleanable);

    synchronized void unregisterCleanup();

    synchronized void close() throws IOException;

    synchronized void attach(Closeable c);

    @SuppressWarnings("try")
    synchronized void closeAll(Closeable releaser) throws IOException;
}
