/*
 * Copyright (c) 1995, 2022, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MulticastChannel;

@AnnotatedFor({ "nullness" })
public class MulticastSocket extends DatagramSocket {

    @Override
    final MulticastSocket delegate();

    public MulticastSocket() throws IOException {
    }

    public MulticastSocket(int port) throws IOException {
    }

    public MulticastSocket(@Nullable SocketAddress bindaddr) throws IOException {
    }

    @Deprecated
    public void setTTL(byte ttl) throws IOException;

    public void setTimeToLive(int ttl) throws IOException;

    @Deprecated
    public byte getTTL() throws IOException;

    public int getTimeToLive() throws IOException;

    @Deprecated()
    public void joinGroup(InetAddress mcastaddr) throws IOException;

    @Deprecated()
    public void leaveGroup(InetAddress mcastaddr) throws IOException;

    @Override
    public void joinGroup(SocketAddress mcastaddr, @Nullable NetworkInterface netIf) throws IOException;

    @Override
    public void leaveGroup(SocketAddress mcastaddr, @Nullable NetworkInterface netIf) throws IOException;

    @Deprecated()
    @CFComment("nullness: TODO: @Nullable parameter or not?")
    public void setInterface(InetAddress inf) throws SocketException;

    @Deprecated()
    public InetAddress getInterface() throws SocketException;

    @CFComment("nullness: TODO: @Nullable parameter or not?")
    public void setNetworkInterface(NetworkInterface netIf) throws SocketException;

    public NetworkInterface getNetworkInterface() throws SocketException;

    @Deprecated()
    public void setLoopbackMode(boolean disable) throws SocketException;

    @Deprecated()
    public boolean getLoopbackMode() throws SocketException;

    @Deprecated
    public void send(DatagramPacket p, byte ttl) throws IOException;
}
