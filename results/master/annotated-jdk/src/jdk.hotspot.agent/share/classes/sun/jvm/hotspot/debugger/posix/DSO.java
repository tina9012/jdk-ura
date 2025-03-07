/*
 * Copyright (c) 2001, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
 *
 */
package sun.jvm.hotspot.debugger.posix;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.cdbg.*;
import sun.jvm.hotspot.utilities.memo.*;

public abstract class DSO implements LoadObject {

    public DSO(String filename, long size, Address relocation) {
    }

    public String getName();

    public Address getBase();

    public long getSize();

    public CDebugInfoDataBase getDebugInfoDataBase() throws DebuggerException;

    public BlockSym debugInfoForPC(Address pc) throws DebuggerException;

    public LineNumberInfo lineNumberForPC(Address pc) throws DebuggerException;

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    public int hashCode();

    protected abstract Address newAddress(long addr);

    protected abstract long getAddressValue(Address addr);
}
