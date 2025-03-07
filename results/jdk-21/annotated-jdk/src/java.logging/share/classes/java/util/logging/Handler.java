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
package java.util.logging;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.util.Objects;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.locks.ReentrantLock;

@AnnotatedFor({ "interning", "nullness" })
@UsesObjectEquals
public abstract class Handler {

    protected Handler() {
    }

    boolean tryUseLock();

    void unlock();

    @CFComment({ "nullness: The doc says null is permitted, but JDK11 has a bug in isLoggable that " + "rejects it, so Handler implementations that call isLoggable would reject null" })
    public abstract void publish(LogRecord record);

    public abstract void flush();

    public abstract void close() throws SecurityException;

    public void setFormatter(Formatter newFormatter) throws SecurityException;

    @Nullable
    public Formatter getFormatter();

    public void setEncoding(@Nullable String encoding) throws SecurityException, java.io.UnsupportedEncodingException;

    @Nullable
    public String getEncoding();

    public void setFilter(@Nullable Filter newFilter) throws SecurityException;

    @Nullable
    public Filter getFilter();

    public void setErrorManager(ErrorManager em);

    public ErrorManager getErrorManager();

    protected void reportError(@Nullable String msg, @Nullable Exception ex, int code);

    public void setLevel(Level newLevel) throws SecurityException;

    public Level getLevel();

    @CFComment({ "nullness: The doc says null is permitted, but JDK11 has a bug that rejects it" })
    public boolean isLoggable(LogRecord record);

    void checkPermission() throws SecurityException;
}
