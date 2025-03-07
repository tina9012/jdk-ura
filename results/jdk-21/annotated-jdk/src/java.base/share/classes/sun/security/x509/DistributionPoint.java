/*
 * Copyright (c) 2002, 2022, Oracle and/or its affiliates. All rights reserved.
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
import java.util.*;
import sun.security.util.BitArray;
import sun.security.util.DerEncoder;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class DistributionPoint implements DerEncoder {

    public static final int KEY_COMPROMISE;

    public static final int CA_COMPROMISE;

    public static final int AFFILIATION_CHANGED;

    public static final int SUPERSEDED;

    public static final int CESSATION_OF_OPERATION;

    public static final int CERTIFICATE_HOLD;

    public static final int PRIVILEGE_WITHDRAWN;

    public static final int AA_COMPROMISE;

    public DistributionPoint(GeneralNames fullName, boolean[] reasonFlags, GeneralNames crlIssuer) {
    }

    public DistributionPoint(RDN relativeName, boolean[] reasonFlags, GeneralNames crlIssuer) {
    }

    public DistributionPoint(DerValue val) throws IOException {
    }

    public GeneralNames getFullName();

    public RDN getRelativeName();

    public boolean[] getReasonFlags();

    public GeneralNames getCRLIssuer();

    @Override
    public void encode(DerOutputStream out);

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    public int hashCode();

    public String toString();
}
