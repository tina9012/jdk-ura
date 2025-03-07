/*
 * Copyright (c) 1997, 2022, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.security.util.Debug;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@AnnotatedFor("nullness")
public class SecureClassLoader extends ClassLoader {

    protected SecureClassLoader(@Nullable ClassLoader parent) {
    }

    protected SecureClassLoader() {
    }

    protected SecureClassLoader(@Nullable String name, @Nullable ClassLoader parent) {
    }

    protected final Class<?> defineClass(String name, byte[] b, int off, int len, @Nullable CodeSource cs);

    protected final Class<?> defineClass(String name, java.nio.ByteBuffer b, @Nullable CodeSource cs);

    protected PermissionCollection getPermissions(CodeSource codesource);

    private static class DebugHolder {
    }

    private record CodeSourceKey(CodeSource cs) {

        @Override
        public int hashCode();

        @Override
        public boolean equals(Object obj);
    }
}
