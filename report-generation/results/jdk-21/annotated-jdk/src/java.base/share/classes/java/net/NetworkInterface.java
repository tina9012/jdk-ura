/*
 * Copyright (c) 2000, 2022, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@AnnotatedFor("nullness")
public final class NetworkInterface {

    public String getName();

    public Enumeration<InetAddress> getInetAddresses();

    public Stream<InetAddress> inetAddresses();

    public java.util.List<InterfaceAddress> getInterfaceAddresses();

    public Enumeration<NetworkInterface> getSubInterfaces();

    public Stream<NetworkInterface> subInterfaces();

    @Nullable
    public NetworkInterface getParent();

    public int getIndex();

    @Nullable
    public String getDisplayName();

    @Nullable
    public static NetworkInterface getByName(String name) throws SocketException;

    @Nullable
    public static NetworkInterface getByIndex(int index) throws SocketException;

    @Nullable
    public static NetworkInterface getByInetAddress(InetAddress addr) throws SocketException;

    public static Enumeration<NetworkInterface> getNetworkInterfaces() throws SocketException;

    public static Stream<NetworkInterface> networkInterfaces() throws SocketException;

    static boolean isBoundInetAddress(InetAddress addr) throws SocketException;

    public boolean isUp() throws SocketException;

    public boolean isLoopback() throws SocketException;

    public boolean isPointToPoint() throws SocketException;

    public boolean supportsMulticast() throws SocketException;

    public byte @Nullable [] getHardwareAddress() throws SocketException;

    public int getMTU() throws SocketException;

    public boolean isVirtual();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    public int hashCode();

    public String toString();

    static NetworkInterface getDefault();
}
