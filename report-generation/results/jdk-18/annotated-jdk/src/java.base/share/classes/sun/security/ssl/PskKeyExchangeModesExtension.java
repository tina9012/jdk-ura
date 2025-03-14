/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
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
package sun.security.ssl;

import org.checkerframework.dataflow.qual.Pure;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.*;
import javax.net.ssl.SSLProtocolException;
import sun.security.ssl.SSLExtension.ExtensionConsumer;
import sun.security.ssl.SSLExtension.SSLExtensionSpec;
import sun.security.ssl.SSLHandshake.HandshakeMessage;

final class PskKeyExchangeModesExtension {

    static final class PskKeyExchangeModesSpec implements SSLExtensionSpec {

        @Pure
        boolean contains(PskKeyExchangeMode mode);

        @Override
        public String toString();
    }

    private static final class PskKeyExchangeModesStringizer implements SSLStringizer {

        @Override
        public String toString(HandshakeContext hc, ByteBuffer buffer);
    }

    private static final class PskKeyExchangeModesConsumer implements ExtensionConsumer {

        @Override
        public void consume(ConnectionContext context, HandshakeMessage message, ByteBuffer buffer) throws IOException;
    }

    private static final class PskKeyExchangeModesProducer implements HandshakeProducer {

        @Override
        public byte[] produce(ConnectionContext context, HandshakeMessage message) throws IOException;
    }

    private static final class PskKeyExchangeModesOnLoadAbsence implements HandshakeAbsence {

        @Override
        public void absent(ConnectionContext context, HandshakeMessage message) throws IOException;
    }

    private static final class PskKeyExchangeModesOnTradeAbsence implements HandshakeAbsence {

        @Override
        public void absent(ConnectionContext context, HandshakeMessage message) throws IOException;
    }
}
