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
package sun.security.x509;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
import sun.security.util.*;

public class Extension implements java.security.cert.Extension, DerEncoder {

    protected ObjectIdentifier extensionId;

    protected boolean critical;

    protected byte[] extensionValue;

    public Extension() {
    }

    public Extension(DerValue derVal) throws IOException {
    }

    public Extension(ObjectIdentifier extensionId, boolean critical, byte[] extensionValue) throws IOException {
    }

    public Extension(Extension ext) {
    }

    public static Extension newExtension(ObjectIdentifier extensionId, boolean critical, byte[] rawExtensionValue) throws IOException;

    @Override
    public final void encode(OutputStream out) throws IOException;

    @Override
    public void encode(DerOutputStream out);

    public boolean isCritical();

    public ObjectIdentifier getExtensionId();

    public byte[] getValue();

    public byte[] getExtensionValue();

    public String getName();

    public String getId();

    public String toString();

    public int hashCode();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object other);
}
