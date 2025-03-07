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
package sun.rmi.transport;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ObjID;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.util.Arrays;
import sun.rmi.transport.tcp.TCPEndpoint;

public class LiveRef implements Cloneable {

    public LiveRef(ObjID objID, Endpoint endpoint, boolean isLocal) {
    }

    public LiveRef(int port) {
    }

    public LiveRef(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) {
    }

    public LiveRef(ObjID objID, int port) {
    }

    public LiveRef(ObjID objID, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) {
    }

    public Object clone();

    public int getPort();

    public RMIClientSocketFactory getClientSocketFactory();

    public RMIServerSocketFactory getServerSocketFactory();

    public void exportObject(Target target) throws RemoteException;

    public Channel getChannel() throws RemoteException;

    public ObjID getObjID();

    Endpoint getEndpoint();

    public String toString();

    public int hashCode();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object obj);

    public boolean remoteEquals(Object obj);

    public void write(ObjectOutput out, boolean useNewFormat) throws IOException;

    public static LiveRef read(ObjectInput in, boolean useNewFormat) throws IOException, ClassNotFoundException;
}
