/*
 * Copyright (c) 1996, 2022, Oracle and/or its affiliates. All rights reserved.
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
package java.util.zip;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.mustcall.qual.MustCallAlias;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import sun.nio.cs.UTF_8;
import static java.util.zip.ZipConstants64.*;
import static java.util.zip.ZipUtils.*;

@AnnotatedFor({ "index", "nullness" })
public class ZipInputStream extends InflaterInputStream implements ZipConstants {

    @MustCallAlias
    public ZipInputStream(@MustCallAlias InputStream in) {
    }

    @MustCallAlias
    public ZipInputStream(@MustCallAlias InputStream in, Charset charset) {
    }

    @Nullable
    public ZipEntry getNextEntry() throws IOException;

    public void closeEntry() throws IOException;

    public int available() throws IOException;

    @Override
    public int read() throws IOException;

    @Override
    public byte[] readAllBytes() throws IOException;

    @Override
    public byte[] readNBytes(int len) throws IOException;

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException;

    @Override
    public void skipNBytes(long n) throws IOException;

    @Override
    public long transferTo(OutputStream out) throws IOException;

    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] b, @IndexOrHigh({ "#1" }) int off, @IndexOrHigh({ "#1" }) int len) throws IOException;

    public long skip(long n) throws IOException;

    public void close() throws IOException;

    protected ZipEntry createZipEntry(String name);
}
