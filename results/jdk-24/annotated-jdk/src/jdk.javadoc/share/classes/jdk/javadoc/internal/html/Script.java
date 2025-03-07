/*
 * Copyright (c) 2003, 2022, Oracle and/or its affiliates. All rights reserved.
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
package jdk.javadoc.internal.html;

import org.checkerframework.dataflow.qual.Pure;
import java.io.IOException;
import java.io.Writer;

public class Script {

    public Script() {
    }

    public Script(String code) {
    }

    public Script append(CharSequence code);

    public Script appendStringLiteral(CharSequence text);

    public Script appendStringLiteral(CharSequence text, char quoteChar);

    public Content asContent();

    public static String stringLiteral(CharSequence s);

    public static String stringLiteral(CharSequence s, char quoteChar);

    private static class ScriptContent extends Content {

        @Override
        public ScriptContent add(CharSequence code);

        @Override
        public boolean write(Writer writer, String newline, boolean atNewline) throws IOException;

        @Override
        @Pure
        public boolean isEmpty();
    }
}
