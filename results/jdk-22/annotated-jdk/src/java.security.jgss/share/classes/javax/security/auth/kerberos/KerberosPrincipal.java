/*
 * Copyright (c) 2000, 2023, Oracle and/or its affiliates. All rights reserved.
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
package javax.security.auth.kerberos;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.*;
import sun.security.krb5.KrbException;
import sun.security.krb5.PrincipalName;
import sun.security.krb5.Realm;
import sun.security.util.*;

public final class KerberosPrincipal implements java.security.Principal, java.io.Serializable {

    public static final int KRB_NT_UNKNOWN;

    public static final int KRB_NT_PRINCIPAL;

    public static final int KRB_NT_SRV_INST;

    public static final int KRB_NT_SRV_HST;

    public static final int KRB_NT_SRV_XHST;

    public static final int KRB_NT_UID;

    public static final int KRB_NT_ENTERPRISE;

    public KerberosPrincipal(String name) {
    }

    public KerberosPrincipal(String name, int nameType) {
    }

    public String getRealm();

    @Override
    public int hashCode();

    @Override
    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    public String getName();

    public int getNameType();

    public String toString();
}
