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
package java.util.jar;

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.zip.ZipEntry;
import jdk.internal.util.jar.JarIndex;
import sun.security.util.ManifestDigester;
import sun.security.util.ManifestEntryVerifier;
import sun.security.util.SignatureFileVerifier;
import sun.security.util.Debug;

class JarVerifier {

    public JarVerifier(String name, byte[] rawBytes) {
    }

    public void beginEntry(JarEntry je, ManifestEntryVerifier mev) throws IOException;

    public void update(int b, ManifestEntryVerifier mev) throws IOException;

    public void update(int n, byte[] b, int off, int len, ManifestEntryVerifier mev) throws IOException;

    @Deprecated
    public java.security.cert.Certificate[] getCerts(String name);

    public java.security.cert.Certificate[] getCerts(JarFile jar, JarEntry entry);

    public CodeSigner[] getCodeSigners(String name);

    public CodeSigner[] getCodeSigners(JarFile jar, JarEntry entry);

    boolean nothingToVerify();

    void doneWithMeta();

    static class VerifierStream extends java.io.InputStream {

        public int read() throws IOException;

        public int read(byte[] b, int off, int len) throws IOException;

        public void close() throws IOException;

        public int available() throws IOException;
    }

    private static class VerifierCodeSource extends CodeSource {

        public boolean equals(Object obj);

        boolean isSameDomain(Object csdomain);
    }

    public synchronized Enumeration<String> entryNames(JarFile jar, final CodeSource[] cs);

    public Enumeration<JarEntry> entries2(final JarFile jar, Enumeration<JarEntry> e);

    static boolean isSigningRelated(String name);

    public synchronized CodeSource[] getCodeSources(JarFile jar, URL url);

    public CodeSource getCodeSource(URL url, String name);

    public CodeSource getCodeSource(URL url, JarFile jar, JarEntry je);

    public void setEagerValidation(boolean eager);

    public synchronized List<Object> getManifestDigests();

    static CodeSource getUnsignedCS(URL url);

    boolean isTrustedManifestEntry(String name);
}
