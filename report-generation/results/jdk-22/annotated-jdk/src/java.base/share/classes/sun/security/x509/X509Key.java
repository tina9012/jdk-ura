/*
 * Copyright (c) 1996, 2023, Oracle and/or its affiliates. All rights reserved.
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
package sun.security.x509;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.*;
import java.util.Arrays;
import java.security.Key;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.Security;
import java.security.Provider;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import sun.security.util.HexDumpEncoder;
import sun.security.util.*;

public class X509Key implements PublicKey, DerEncoder {

    protected AlgorithmId algid;

    @Deprecated
    protected byte[] key;

    protected byte[] encodedKey;

    public X509Key() {
    }

    protected void setKey(BitArray key);

    protected BitArray getKey();

    public static PublicKey parse(DerValue in) throws IOException;

    protected void parseKeyBits() throws InvalidKeyException;

    static PublicKey buildX509Key(AlgorithmId algid, BitArray key) throws IOException, InvalidKeyException;

    public String getAlgorithm();

    public AlgorithmId getAlgorithmId();

    @Override
    public final void encode(DerOutputStream out);

    public byte[] getEncoded();

    public byte[] getEncodedInternal();

    public String getFormat();

    public byte[] encode();

    public String toString();

    void decode(DerValue val) throws InvalidKeyException;

    public void decode(byte[] encodedKey) throws InvalidKeyException;

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    @Override
    public int hashCode();

    static void encode(DerOutputStream out, AlgorithmId algid, BitArray key);
}
