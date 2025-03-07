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
package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.mustcall.qual.MustCallAlias;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import jdk.internal.misc.InternalLock;
import sun.nio.cs.StreamEncoder;

@AnnotatedFor({ "index", "mustcall", "nullness" })
public class OutputStreamWriter extends Writer {

    @SuppressWarnings("this-escape")
    @MustCallAlias
    public OutputStreamWriter(@MustCallAlias OutputStream out, String charsetName) throws UnsupportedEncodingException {
    }

    @SuppressWarnings("this-escape")
    @MustCallAlias
    public OutputStreamWriter(@MustCallAlias OutputStream out) {
    }

    @SuppressWarnings("this-escape")
    @MustCallAlias
    public OutputStreamWriter(@MustCallAlias OutputStream out, Charset cs) {
    }

    @SuppressWarnings("this-escape")
    @MustCallAlias
    public OutputStreamWriter(@MustCallAlias OutputStream out, CharsetEncoder enc) {
    }

    @Nullable
    public String getEncoding();

    void flushBuffer() throws IOException;

    public void write(int c) throws IOException;

    public void write(char[] cbuf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void write(String str, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Override
    @MustCallAlias
    public Writer append(@MustCallAlias OutputStreamWriter this, CharSequence csq, int start, int end) throws IOException;

    @Override
    @MustCallAlias
    public Writer append(@MustCallAlias OutputStreamWriter this, CharSequence csq) throws IOException;

    public void flush() throws IOException;

    public void close() throws IOException;
}
