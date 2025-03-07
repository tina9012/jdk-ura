/*
 * Copyright (c) 2005, 2023, Oracle and/or its affiliates. All rights reserved.
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
package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.InputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import jdk.internal.icu.impl.Punycode;
import jdk.internal.icu.text.StringPrep;
import jdk.internal.icu.text.UCharacterIterator;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class IDN {

    @SignedPositive
    public static final int ALLOW_UNASSIGNED;

    @SignedPositive
    public static final int USE_STD3_ASCII_RULES;

    public static String toASCII(String input, int flag);

    public static String toASCII(String input);

    public static String toUnicode(String input, int flag);

    public static String toUnicode(String input);
}
