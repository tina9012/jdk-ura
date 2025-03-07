/*
 * Copyright (c) 1995, 2024, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.AccessController;
import java.security.Permission;
import java.util.Set;

@AnnotatedFor({ "interning", "nullness" })
@Deprecated()
@UsesObjectEquals
public class SecurityManager {

    public SecurityManager() {
    }

    protected Class<?>[] getClassContext();

    private static class StackWalkerHolder {
    }

    @SuppressWarnings("removal")
    public Object getSecurityContext();

    public void checkPermission(Permission perm);

    public void checkPermission(Permission perm, Object context);

    public void checkCreateClassLoader();

    public void checkAccess(Thread t);

    public void checkAccess(ThreadGroup g);

    public void checkExit(int status);

    public void checkExec(String cmd);

    public void checkLink(String lib);

    public void checkRead(FileDescriptor fd);

    public void checkRead(String file);

    public void checkRead(String file, Object context);

    public void checkWrite(FileDescriptor fd);

    public void checkWrite(String file);

    public void checkDelete(String file);

    public void checkConnect(String host, int port);

    public void checkConnect(String host, int port, Object context);

    public void checkListen(int port);

    public void checkAccept(String host, int port);

    public void checkMulticast(InetAddress maddr);

    public void checkMulticast(InetAddress maddr, byte ttl);

    public void checkPropertiesAccess();

    public void checkPropertyAccess(String key);

    public void checkPrintJobAccess();

    public void checkPackageAccess(String pkg);

    public void checkPackageDefinition(String pkg);

    public void checkSetFactory();

    public void checkSecurityAccess(String target);

    public ThreadGroup getThreadGroup();
}
