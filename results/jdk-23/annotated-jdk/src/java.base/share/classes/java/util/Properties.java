/*
 * Copyright (c) 1995, 2024, Oracle and/or its affiliates. All rights reserved.
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
package java.util;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.propkey.qual.PropertyKey;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import jdk.internal.util.StaticProperty;
import sun.nio.cs.ISO_8859_1;
import sun.nio.cs.UTF_8;
import jdk.internal.access.SharedSecrets;
import jdk.internal.misc.Unsafe;
import jdk.internal.util.ArraysSupport;
import jdk.internal.util.xml.PropertiesDefaultHandler;

@AnnotatedFor({ "index", "lock", "nullness", "propkey" })
public class Properties extends Hashtable<Object, Object> {

    @Nullable
    protected volatile Properties defaults;

    public Properties() {
    }

    public Properties(int initialCapacity) {
    }

    public Properties(@Nullable Properties defaults) {
    }

    @Nullable
    public synchronized Object setProperty(@GuardSatisfied Properties this, @PropertyKey String key, String value);

    public synchronized void load(Reader reader) throws IOException;

    public synchronized void load(InputStream inStream) throws IOException;

    private static class LineReader {

        int readLine() throws IOException;
    }

    @Deprecated
    public void save(OutputStream out, @Nullable String comments);

    public void store(Writer writer, @Nullable String comments) throws IOException;

    public void store(OutputStream out, @Nullable String comments) throws IOException;

    public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException;

    public void storeToXML(OutputStream os, @Nullable String comment) throws IOException;

    public void storeToXML(OutputStream os, @Nullable String comment, String encoding) throws IOException;

    public void storeToXML(OutputStream os, String comment, Charset charset) throws IOException;

    @Pure
    @Nullable
    public String getProperty(@GuardSatisfied Properties this, @PropertyKey String key);

    @Pure
    @PolyNull
    public String getProperty(@GuardSatisfied Properties this, @PropertyKey String key, @PolyNull String defaultValue);

    public Enumeration<?> propertyNames();

    public Set<String> stringPropertyNames();

    public void list(PrintStream out);

    public void list(PrintWriter out);

    @Override
    @Pure
    public int size();

    @Override
    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Override
    public Enumeration<Object> keys();

    @Override
    public Enumeration<Object> elements();

    @Override
    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object value);

    @Override
    @Pure
    public boolean containsValue(@GuardSatisfied @Nullable @UnknownSignedness Object value);

    @Override
    @Pure
    public boolean containsKey(@GuardSatisfied @Nullable @UnknownSignedness Object key);

    @Override
    @Nullable
    public Object get(Object key);

    @Override
    @Nullable
    public synchronized Object put(Object key, Object value);

    @Override
    @Nullable
    public synchronized Object remove(@GuardSatisfied @UnknownSignedness Object key);

    @Override
    public synchronized void putAll(Map<? extends Object, ? extends Object> t);

    @Override
    public synchronized void clear();

    @Override
    public synchronized String toString();

    @Override
    public Set<@KeyFor("this") Object> keySet();

    @Override
    public Collection<Object> values();

    @Override
    @SideEffectFree
    public Set<Map.Entry<@KeyFor("this") Object, Object>> entrySet();

    private static class EntrySet implements Set<Map.Entry<Object, Object>> {

        @Pure
        @Override
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        @Override
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        @Override
        public boolean contains(@UnknownSignedness Object o);

        @Override
        public Object[] toArray();

        @Override
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        @Override
        public void clear();

        @Override
        public boolean remove(@UnknownSignedness Object o);

        @Override
        public boolean add(Map.Entry<Object, Object> e);

        @Override
        public boolean addAll(Collection<? extends Map.Entry<Object, Object>> c);

        @Override
        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

        @Override
        public boolean equals(Object o);

        @Override
        public int hashCode();

        @Override
        public String toString();

        @Override
        public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

        @Override
        public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

        @Override
        public Iterator<Map.Entry<Object, Object>> iterator();
    }

    @Override
    public synchronized boolean equals(Object o);

    @Override
    public synchronized int hashCode();

    @Override
    @Pure
    @PolyNull
    public Object getOrDefault(@GuardSatisfied @UnknownSignedness Object key, @PolyNull Object defaultValue);

    @Override
    public synchronized void forEach(BiConsumer<? super Object, ? super Object> action);

    @Override
    public synchronized void replaceAll(BiFunction<? super Object, ? super Object, ? extends Object> function);

    @Override
    @Nullable
    public synchronized Object putIfAbsent(Object key, Object value);

    @Override
    public synchronized boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object key, @GuardSatisfied @Nullable @UnknownSignedness Object value);

    @Override
    public synchronized boolean replace(Object key, Object oldValue, Object newValue);

    @Override
    @Nullable
    public synchronized Object replace(Object key, Object value);

    @Override
    @PolyNull
    public synchronized Object computeIfAbsent(Object key, Function<? super Object, ? extends @PolyNull Object> mappingFunction);

    @Override
    @PolyNull
    public synchronized Object computeIfPresent(Object key, BiFunction<? super Object, ? super Object, ? extends @PolyNull Object> remappingFunction);

    @Override
    @PolyNull
    public synchronized Object compute(Object key, BiFunction<? super Object, ? super Object, ? extends @PolyNull Object> remappingFunction);

    @Override
    @Nullable
    public synchronized Object merge(Object key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction);

    @Override
    protected void rehash();

    @Override
    public synchronized Object clone();

    @Override
    void writeHashtable(ObjectOutputStream s) throws IOException;

    @Override
    void readHashtable(ObjectInputStream s) throws IOException, ClassNotFoundException;
}
