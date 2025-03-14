/*
 * Copyright (c) 1996, 2023, Oracle and/or its affiliates. All rights reserved.
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
package java.io;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTEqLengthOf;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.mustcall.qual.MustCallAlias;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.SignedPositive;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectInputFilter.Config;
import java.io.ObjectStreamClass.RecordSupport;
import java.lang.System.Logger;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import jdk.internal.access.SharedSecrets;
import jdk.internal.event.DeserializationEvent;
import jdk.internal.misc.Unsafe;
import jdk.internal.util.ByteArray;
import sun.reflect.misc.ReflectUtil;
import sun.security.action.GetBooleanAction;
import sun.security.action.GetIntegerAction;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {

    private static class Caches {
    }

    private static class Logging {
    }

    @MustCallAlias
    public ObjectInputStream(@MustCallAlias InputStream in) throws IOException {
    }

    protected ObjectInputStream() throws IOException, SecurityException {
    }

    @Nullable
    public final Object readObject() throws IOException, ClassNotFoundException;

    @Nullable
    protected Object readObjectOverride() throws IOException, ClassNotFoundException;

    @Nullable
    public Object readUnshared() throws IOException, ClassNotFoundException;

    public void defaultReadObject() throws IOException, ClassNotFoundException;

    public ObjectInputStream.GetField readFields() throws IOException, ClassNotFoundException;

    public void registerValidation(ObjectInputValidation obj, int prio) throws NotActiveException, InvalidObjectException;

    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException;

    protected Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException;

    protected Object resolveObject(Object obj) throws IOException;

    protected boolean enableResolveObject(boolean enable) throws SecurityException;

    protected void readStreamHeader() throws IOException, StreamCorruptedException;

    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException;

    @Override
    public int read() throws IOException;

    @Override
    @GTENegativeOne
    @LTEqLengthOf({ "#1" })
    public int read(byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Override
    @NonNegative
    public int available() throws IOException;

    @Override
    public void close() throws IOException;

    public boolean readBoolean() throws IOException;

    public byte readByte() throws IOException;

    @SignedPositive
    @NonNegative
    public int readUnsignedByte() throws IOException;

    public char readChar() throws IOException;

    public short readShort() throws IOException;

    @SignedPositive
    @NonNegative
    public int readUnsignedShort() throws IOException;

    public int readInt() throws IOException;

    public long readLong() throws IOException;

    public float readFloat() throws IOException;

    public double readDouble() throws IOException;

    public void readFully(byte[] buf) throws IOException;

    public void readFully(byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    @Override
    @NonNegative
    public int skipBytes(@NonNegative int len) throws IOException;

    @Deprecated
    @Nullable
    public String readLine() throws IOException;

    public String readUTF() throws IOException;

    @Nullable
    public final ObjectInputFilter getObjectInputFilter();

    public final void setObjectInputFilter(@Nullable ObjectInputFilter filter);

    public abstract static class GetField {

        public GetField() {
        }

        public abstract ObjectStreamClass getObjectStreamClass();

        public abstract boolean defaulted(String name) throws IOException;

        public abstract boolean get(String name, boolean val) throws IOException;

        public abstract byte get(String name, byte val) throws IOException;

        public abstract char get(String name, char val) throws IOException;

        public abstract short get(String name, short val) throws IOException;

        public abstract int get(String name, int val) throws IOException;

        public abstract long get(String name, long val) throws IOException;

        public abstract float get(String name, float val) throws IOException;

        public abstract double get(String name, double val) throws IOException;

        @Nullable
        public abstract Object get(String name, @Nullable Object val) throws IOException, ClassNotFoundException;
    }

    String readTypeString() throws IOException;

    private final class FieldValues extends GetField {

        public ObjectStreamClass getObjectStreamClass();

        public boolean defaulted(String name);

        public boolean get(String name, boolean val);

        public byte get(String name, byte val);

        public char get(String name, char val);

        public short get(String name, short val);

        public int get(String name, int val);

        public float get(String name, float val);

        public long get(String name, long val);

        public double get(String name, double val);

        public Object get(String name, Object val) throws ClassNotFoundException;

        void defaultCheckFieldValues(Object obj);
    }

    private static class ValidationList {

        private static class Callback {
        }

        void register(ObjectInputValidation obj, int priority) throws InvalidObjectException;

        @SuppressWarnings("removal")
        void doCallbacks() throws InvalidObjectException;

        public void clear();
    }

    static class FilterValues implements ObjectInputFilter.FilterInfo {

        public FilterValues(Class<?> clazz, long arrayLength, long totalObjectRefs, long depth, long streamBytes) {
        }

        @Override
        public Class<?> serialClass();

        @Override
        public long arrayLength();

        @Override
        public long references();

        @Override
        public long depth();

        @Override
        public long streamBytes();
    }

    private static class PeekInputStream extends InputStream {

        @Pure
        int peek() throws IOException;

        public int read() throws IOException;

        public int read(byte[] b, int off, int len) throws IOException;

        void readFully(byte[] b, int off, int len) throws IOException;

        public long skip(long n) throws IOException;

        public int available() throws IOException;

        public void close() throws IOException;

        public long getBytesRead();
    }

    private class BlockDataInputStream extends InputStream implements DataInput {

        boolean setBlockDataMode(boolean newmode) throws IOException;

        boolean getBlockDataMode();

        void skipBlockData() throws IOException;

        int currentBlockRemaining();

        @Pure
        int peek() throws IOException;

        @Pure
        byte peekByte() throws IOException;

        public int read() throws IOException;

        public int read(byte[] b, int off, int len) throws IOException;

        public long skip(long len) throws IOException;

        public int available() throws IOException;

        public void close() throws IOException;

        int read(byte[] b, int off, int len, boolean copy) throws IOException;

        public void readFully(byte[] b) throws IOException;

        public void readFully(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

        public void readFully(byte[] b, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len, boolean copy) throws IOException;

        public int skipBytes(int n) throws IOException;

        public boolean readBoolean() throws IOException;

        public byte readByte() throws IOException;

        @SignedPositive
        @NonNegative
        public int readUnsignedByte() throws IOException;

        public char readChar() throws IOException;

        public short readShort() throws IOException;

        @SignedPositive
        @NonNegative
        public int readUnsignedShort() throws IOException;

        public int readInt() throws IOException;

        public float readFloat() throws IOException;

        public long readLong() throws IOException;

        public double readDouble() throws IOException;

        public String readUTF() throws IOException;

        @SuppressWarnings("deprecation")
        public String readLine() throws IOException;

        void readBooleans(boolean[] v, int off, int len) throws IOException;

        void readChars(char[] v, int off, int len) throws IOException;

        void readShorts(short[] v, int off, int len) throws IOException;

        void readInts(int[] v, int off, int len) throws IOException;

        void readFloats(float[] v, int off, int len) throws IOException;

        void readLongs(long[] v, int off, int len) throws IOException;

        void readDoubles(double[] v, int off, int len) throws IOException;

        String readLongUTF() throws IOException;

        long getBytesRead();
    }

    private static final class HandleTable {

        int assign(Object obj);

        void markDependency(int dependent, int target);

        void markException(int handle, ClassNotFoundException ex);

        void finish(int handle);

        void setObject(int handle, Object obj);

        Object lookupObject(int handle);

        ClassNotFoundException lookupException(int handle);

        void clear();

        int size();

        private static class HandleList {

            public HandleList() {
            }

            public void add(int handle);

            public int get(int index);

            public int size();
        }
    }
}
