/*
 * Copyright (c) 1994, 2021, Oracle and/or its affiliates. All rights reserved.
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
package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.mustcall.qual.MustCallAlias;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Objects;

@AnnotatedFor({ "index", "initialization", "mustcall", "nullness", "signedness" })
public class DataInputStream extends FilterInputStream implements DataInput {

    @MustCallAlias
    public DataInputStream(@MustCallAlias InputStream in) {
    }

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public final int read(@PolySigned byte @Nullable [] b) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public final int read(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public final void readFully(@PolySigned byte[] b) throws IOException;

    public final void readFully(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @NonNegative
    public final int skipBytes(int n) throws IOException;

    public final boolean readBoolean() throws IOException;

    public final byte readByte() throws IOException;

    @SignedPositive
    @NonNegative
    public final int readUnsignedByte() throws IOException;

    public final short readShort() throws IOException;

    @SignedPositive
    @NonNegative
    public final int readUnsignedShort() throws IOException;

    public final char readChar() throws IOException;

    public final int readInt() throws IOException;

    public final long readLong() throws IOException;

    public final float readFloat() throws IOException;

    public final double readDouble() throws IOException;

    @Deprecated
    @Nullable
    public final String readLine() throws IOException;

    public final String readUTF() throws IOException;

    public static final String readUTF(DataInput in) throws IOException;
}
