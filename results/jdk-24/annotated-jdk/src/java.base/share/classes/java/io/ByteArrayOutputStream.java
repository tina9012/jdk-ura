/*
 * Copyright (c) 1994, 2024, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.mustcall.qual.InheritableMustCall;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import jdk.internal.util.ArraysSupport;

@AnnotatedFor({ "index", "initialization", "lock", "mustcall", "nullness", "signedness" })
@InheritableMustCall({})
public class ByteArrayOutputStream extends OutputStream {

    protected byte[] buf;

    @IndexOrHigh({ "this.buf" })
    protected int count;

    public ByteArrayOutputStream() {
    }

    public ByteArrayOutputStream(@NonNegative int size) {
    }

    @Override
    public synchronized void write(@PolySigned int b);

    @Override
    public synchronized void write(@PolySigned byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len);

    public void writeBytes(byte[] b);

    public synchronized void writeTo(OutputStream out) throws IOException;

    public synchronized void reset();

    @PolySigned
    public synchronized byte[] toByteArray();

    @Pure
    @IndexOrHigh({ "this.buf" })
    public synchronized int size(@GuardSatisfied ByteArrayOutputStream this);

    @Override
    @SideEffectFree
    public synchronized String toString(@GuardSatisfied ByteArrayOutputStream this);

    @SideEffectFree
    public synchronized String toString(@GuardSatisfied ByteArrayOutputStream this, String charsetName) throws UnsupportedEncodingException;

    public synchronized String toString(Charset charset);

    @SideEffectFree
    @Deprecated
    public synchronized String toString(@GuardSatisfied ByteArrayOutputStream this, int hibyte);

    @Override
    public void close() throws IOException;
}
