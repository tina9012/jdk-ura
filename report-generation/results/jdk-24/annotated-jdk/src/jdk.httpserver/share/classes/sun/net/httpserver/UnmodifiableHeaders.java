/*
 * Copyright (c) 2005, 2024, Oracle and/or its affiliates. All rights reserved.
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
package sun.net.httpserver;

import org.checkerframework.dataflow.qual.Pure;
import java.util.*;
import java.util.function.BiFunction;
import com.sun.net.httpserver.*;

public class UnmodifiableHeaders extends Headers {

    public UnmodifiableHeaders(Headers headers) {
    }

    @Override
    public int size();

    @Override
    public boolean isEmpty();

    @Override
    @Pure
    public boolean containsKey(Object key);

    @Override
    @Pure
    public boolean containsValue(Object value);

    @Override
    public List<String> get(Object key);

    @Override
    public String getFirst(String key);

    @Override
    public List<String> put(String key, List<String> value);

    @Override
    public void add(String key, String value);

    @Override
    public void set(String key, String value);

    @Override
    public List<String> remove(Object key);

    @Override
    public void putAll(Map<? extends String, ? extends List<String>> t);

    @Override
    public void clear();

    @Override
    public Set<String> keySet();

    @Override
    public Collection<List<String>> values();

    @Override
    public Set<Map.Entry<String, List<String>>> entrySet();

    @Override
    public List<String> replace(String key, List<String> value);

    @Override
    public boolean replace(String key, List<String> oldValue, List<String> newValue);

    @Override
    public void replaceAll(BiFunction<? super String, ? super List<String>, ? extends List<String>> function);

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
