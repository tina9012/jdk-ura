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
package java.util.jar;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.StringVal;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.access.SharedSecrets;
import jdk.internal.access.JavaUtilZipFileAccess;
import jdk.internal.misc.ThreadTracker;
import sun.security.action.GetPropertyAction;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@AnnotatedFor({ "nullness" })
public class JarFile extends ZipFile {

    @Interned
    @StringVal("META-INF/MANIFEST.MF")
    public static final String MANIFEST_NAME;

    public static Runtime.Version baseVersion();

    public static Runtime.Version runtimeVersion();

    public JarFile(String name) throws IOException {
    }

    public JarFile(String name, boolean verify) throws IOException {
    }

    public JarFile(File file) throws IOException {
    }

    public JarFile(File file, boolean verify) throws IOException {
    }

    public JarFile(File file, boolean verify, int mode) throws IOException {
    }

    public JarFile(File file, boolean verify, int mode, Runtime.Version version) throws IOException {
    }

    public final Runtime.Version getVersion();

    public final boolean isMultiRelease();

    @Nullable
    public Manifest getManifest() throws IOException;

    @Nullable
    public JarEntry getJarEntry(String name);

    @Nullable
    public ZipEntry getEntry(String name);

    public Enumeration<JarEntry> entries();

    public Stream<JarEntry> stream();

    public Stream<JarEntry> versionedStream();

    JarEntry entryFor(String name);

    String getRealName(JarEntry entry);

    private class JarFileEntry extends JarEntry {

        @Override
        @Nullable
        public Attributes getAttributes() throws IOException;

        @Override
        public Certificate @Nullable [] getCertificates();

        @Override
        public CodeSigner @Nullable [] getCodeSigners();

        @Override
        public String getRealName();

        @Override
        public String getName();

        JarFileEntry realEntry();

        JarFileEntry withBasename(String name);
    }

    public synchronized InputStream getInputStream(ZipEntry ze) throws IOException;

    boolean hasClassPathAttribute() throws IOException;

    private static class ThreadTrackHolder {
    }

    synchronized void ensureInitialization();

    static boolean isInitializing();
}
