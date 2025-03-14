/*
 * Copyright (c) 1997, 2023, Oracle and/or its affiliates. All rights reserved.
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
package java.net;

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Closeable;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import jdk.internal.loader.Resource;
import jdk.internal.loader.URLClassPath;
import jdk.internal.access.SharedSecrets;
import jdk.internal.perf.PerfCounter;
import sun.net.www.ParseUtil;
import sun.security.util.SecurityConstants;

@AnnotatedFor("nullness")
public class URLClassLoader extends SecureClassLoader implements Closeable {

    @SuppressWarnings("removal")
    public URLClassLoader(URL[] urls, @Nullable ClassLoader parent) {
    }

    @SuppressWarnings("removal")
    public URLClassLoader(URL[] urls) {
    }

    @SuppressWarnings("removal")
    public URLClassLoader(URL[] urls, @Nullable ClassLoader parent, @Nullable URLStreamHandlerFactory factory) {
    }

    @SuppressWarnings("removal")
    public URLClassLoader(@Nullable String name, URL[] urls, @Nullable ClassLoader parent) {
    }

    @SuppressWarnings("removal")
    public URLClassLoader(@Nullable String name, URL[] urls, @Nullable ClassLoader parent, @Nullable URLStreamHandlerFactory factory) {
    }

    @Nullable
    public InputStream getResourceAsStream(String name);

    public void close() throws IOException;

    protected void addURL(@Nullable URL url);

    public URL[] getURLs();

    @SuppressWarnings("removal")
    protected Class<?> findClass(final String name) throws ClassNotFoundException;

    protected Package definePackage(String name, Manifest man, @Nullable URL url);

    @Nullable
    public URL findResource(final String name);

    public Enumeration<URL> findResources(final String name) throws IOException;

    @SuppressWarnings("removal")
    protected PermissionCollection getPermissions(CodeSource codesource);

    public static URLClassLoader newInstance(final URL[] urls, @Nullable final ClassLoader parent);

    public static URLClassLoader newInstance(final URL[] urls);
}

final class FactoryURLClassLoader extends URLClassLoader {

    public final Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException;
}
