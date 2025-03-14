/*
 * Copyright (c) 1997, 2024, Oracle and/or its affiliates. All rights reserved.
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
package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@Deprecated()
@UsesObjectEquals
public final class AccessController {

    public static <T> T doPrivileged(PrivilegedAction<T> action);

    public static <T> T doPrivilegedWithCombiner(PrivilegedAction<T> action);

    public static <T> T doPrivileged(PrivilegedAction<T> action, @SuppressWarnings("removal") AccessControlContext context);

    public static <T> T doPrivileged(PrivilegedAction<T> action, @SuppressWarnings("removal") AccessControlContext context, Permission... perms);

    public static <T> T doPrivilegedWithCombiner(PrivilegedAction<T> action, @SuppressWarnings("removal") AccessControlContext context, Permission... perms);

    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action) throws PrivilegedActionException;

    public static <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> action) throws PrivilegedActionException;

    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action, @SuppressWarnings("removal") AccessControlContext context) throws PrivilegedActionException;

    public static <T> T doPrivileged(PrivilegedExceptionAction<T> action, @SuppressWarnings("removal") AccessControlContext context, Permission... perms) throws PrivilegedActionException;

    public static <T> T doPrivilegedWithCombiner(PrivilegedExceptionAction<T> action, @SuppressWarnings("removal") AccessControlContext context, Permission... perms) throws PrivilegedActionException;

    @SuppressWarnings("removal")
    public static AccessControlContext getContext();

    @SuppressWarnings("removal")
    public static void checkPermission(Permission perm) throws AccessControlException;
}
