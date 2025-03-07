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
package java.nio.channels.spi;

import org.checkerframework.checker.calledmethods.qual.EnsuresCalledMethodsIf;
import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.Channel;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.InterruptibleChannel;
import jdk.internal.access.SharedSecrets;
import sun.nio.ch.Interruptible;

@AnnotatedFor({ "calledmethods", "interning" })
@UsesObjectEquals
public abstract class AbstractInterruptibleChannel implements Channel, InterruptibleChannel {

    protected AbstractInterruptibleChannel() {
    }

    public final void close() throws IOException;

    protected abstract void implCloseChannel() throws IOException;

    @EnsuresCalledMethodsIf(expression = "this", result = false, methods = { "close" })
    public final boolean isOpen();

    protected final void begin();

    protected final void end(boolean completed) throws AsynchronousCloseException;

    static void blockedOn(Interruptible intr);
}
