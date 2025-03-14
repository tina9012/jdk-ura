/*
 * Copyright (c) 2013, 2024, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Azul Systems, Inc. All rights reserved.
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
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.common.reflection.qual.ForName;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.InputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.loader.BootLoader;
import jdk.internal.loader.BuiltinClassLoader;
import jdk.internal.loader.ClassLoaders;
import jdk.internal.loader.NativeLibrary;
import jdk.internal.loader.NativeLibraries;
import jdk.internal.perf.PerfCounter;
import jdk.internal.misc.Unsafe;
import jdk.internal.misc.VM;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.CallerSensitiveAdapter;
import jdk.internal.reflect.Reflection;
import jdk.internal.util.StaticProperty;

@AnnotatedFor({ "interning", "lock", "nullness", "signature" })
@UsesObjectEquals
public abstract class ClassLoader {

    private static class ParallelLoaders {

        static boolean register(Class<? extends ClassLoader> c);

        static boolean isRegistered(Class<? extends ClassLoader> c);
    }

    void addClass(Class<?> c);

    String nameAndId();

    @SuppressWarnings("this-escape")
    protected ClassLoader(@Nullable String name, @Nullable ClassLoader parent) {
    }

    @SuppressWarnings("this-escape")
    protected ClassLoader(@Nullable ClassLoader parent) {
    }

    @SuppressWarnings("this-escape")
    protected ClassLoader() {
    }

    @Nullable
    public String getName();

    final String name();

    @ForName
    public Class<?> loadClass(@BinaryName String name) throws ClassNotFoundException;

    @ForName
    protected Class<?> loadClass(@BinaryName String name, boolean resolve) throws ClassNotFoundException;

    @ForName
    final Class<?> loadClass(Module module, @BinaryName String name);

    protected Object getClassLoadingLock(String className);

    protected Class<?> findClass(@BinaryName String name) throws ClassNotFoundException;

    protected Class<?> findClass(String moduleName, String name);

    @Deprecated()
    @SuppressWarnings("signature")
    protected final Class<?> defineClass(byte[] b, int off, int len) throws ClassFormatError;

    protected final Class<?> defineClass(@Nullable @BinaryName String name, byte[] b, int off, int len) throws ClassFormatError;

    protected final Class<?> defineClass(@Nullable @BinaryName String name, byte[] b, int off, int len, @Nullable ProtectionDomain protectionDomain) throws ClassFormatError;

    protected final Class<?> defineClass(@Nullable @BinaryName String name, java.nio.ByteBuffer b, @Nullable ProtectionDomain protectionDomain) throws ClassFormatError;

    static native Class<?> defineClass1(ClassLoader loader, @BinaryName String name, byte[] b, int off, int len, ProtectionDomain pd, String source);

    static native Class<?> defineClass2(ClassLoader loader, @BinaryName String name, java.nio.ByteBuffer b, int off, int len, ProtectionDomain pd, String source);

    static native Class<?> defineClass0(ClassLoader loader, Class<?> lookup, String name, byte[] b, int off, int len, ProtectionDomain pd, boolean initialize, int flags, Object classData);

    protected final void resolveClass(Class<?> c);

    protected final Class<?> findSystemClass(@BinaryName String name) throws ClassNotFoundException;

    @Nullable
    static Class<?> findBootstrapClassOrNull(String name);

    @Nullable
    protected final Class<?> findLoadedClass(@BinaryName String name);

    protected final void setSigners(Class<?> c, Object[] signers);

    protected URL findResource(String moduleName, String name) throws IOException;

    @Nullable
    public URL getResource(String name);

    public Enumeration<URL> getResources(String name) throws IOException;

    public Stream<URL> resources(String name);

    @Nullable
    protected URL findResource(String name);

    protected Enumeration<URL> findResources(String name) throws IOException;

    @CallerSensitive
    protected static boolean registerAsParallelCapable();

    public final boolean isRegisteredAsParallelCapable();

    @Nullable
    public static URL getSystemResource(String name);

    public static Enumeration<URL> getSystemResources(String name) throws IOException;

    @Nullable
    public InputStream getResourceAsStream(String name);

    @Nullable
    public static InputStream getSystemResourceAsStream(String name);

    @Nullable
    public final ClassLoader getParent();

    public final Module getUnnamedModule();

    public static ClassLoader getPlatformClassLoader();

    public static ClassLoader getSystemClassLoader();

    static ClassLoader getBuiltinPlatformClassLoader();

    static ClassLoader getBuiltinAppClassLoader();

    static synchronized ClassLoader initSystemClassLoader();

    @Nullable
    static ClassLoader getClassLoader(Class<?> caller);

    Package definePackage(Class<?> c);

    Package definePackage(String name, Module m);

    protected Package definePackage(@FullyQualifiedName String name, @Nullable String specTitle, @Nullable String specVersion, @Nullable String specVendor, @Nullable String implTitle, @Nullable String implVersion, @Nullable String implVendor, @Nullable URL sealBase);

    public final Package getDefinedPackage(String name);

    public final Package[] getDefinedPackages();

    @Deprecated()
    @Nullable
    protected Package getPackage(String name);

    @CFComment({ "nullness: The size of array passed to toArray", "method is of exact same size as of the map for which toArray method is invoked" })
    @SuppressWarnings({ "nullness:return" })
    protected Package[] getPackages();

    Stream<Package> packages();

    @Nullable
    protected String findLibrary(String libname);

    @CFComment({ "nulness: usr_paths and sys_paths are initialized", "by intializePath method if they are null" })
    @SuppressWarnings({ "nullness:dereference.of.nullable" })
    static NativeLibrary loadLibrary(Class<?> fromClass, File file);

    static NativeLibrary loadLibrary(Class<?> fromClass, String name);

    static long findNative(@Nullable ClassLoader loader, Class<?> clazz, String entryName, String javaName);

    static NativeLibraries nativeLibrariesFor(@Nullable ClassLoader loader);

    public void setDefaultAssertionStatus(boolean enabled);

    public void setPackageAssertionStatus(@Nullable String packageName, boolean enabled);

    public void setClassAssertionStatus(String className, boolean enabled);

    public void clearAssertionStatus();

    @RequiresNonNull({ "classAssertionStatus", "packageAssertionStatus" })
    boolean desiredAssertionStatus(String className);

    ConcurrentHashMap<?, ?> createOrGetClassLoaderValueMap();
}

final class CompoundEnumeration<E> implements Enumeration<E> {

    public CompoundEnumeration(Enumeration<E>[] enums) {
    }

    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean hasMoreElements();

    public E nextElement(@NonEmpty CompoundEnumeration<E> this);
}
