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
package sun.security.util;

import org.checkerframework.dataflow.qual.Pure;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import sun.security.action.GetIntegerAction;
import sun.security.jca.Providers;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

public class SignatureFileVerifier {

    public static final int MAX_SIG_FILE_SIZE;

    public SignatureFileVerifier(ArrayList<CodeSigner[]> signerCache, ManifestDigester md, String name, byte[] rawBytes) throws IOException, CertificateException {
    }

    public boolean needSignatureFileBytes();

    public boolean needSignatureFile(String name);

    public void setSignatureFile(byte[] sfBytes);

    public static boolean isInMetaInf(String name);

    public static boolean isBlockOrSF(String s);

    public static String getBlockExtension(PrivateKey key);

    public static boolean isSigningRelated(String name);

    public void process(Hashtable<String, CodeSigner[]> signers, List<Object> manifestDigests, String manifestName) throws IOException, SignatureException, NoSuchAlgorithmException, CertificateException;

    String getWeakAlgorithms(String header);

    @Pure
    static boolean contains(CodeSigner[] set, CodeSigner signer);

    static boolean isSubSet(CodeSigner[] subset, CodeSigner[] set);

    static boolean matches(CodeSigner[] signers, CodeSigner[] oldSigners, CodeSigner[] newSigners);

    void updateSigners(CodeSigner[] newSigners, Hashtable<String, CodeSigner[]> signers, String name);
}
