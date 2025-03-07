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
import java.util.*;
import java.security.CodeSigner;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
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

    public Certificate[] getCerts(JarEntry entry);

    public CodeSigner[] getCodeSigners(JarEntry entry);

    boolean nothingToVerify();

    void doneWithMeta();

    static class VerifierStream extends java.io.InputStream {

        public int read() throws IOException;

        public int read(byte[] b, int off, int len) throws IOException;

        public void close() throws IOException;

        public int available() throws IOException;
    }

    boolean isTrustedManifestEntry(String name);
}
