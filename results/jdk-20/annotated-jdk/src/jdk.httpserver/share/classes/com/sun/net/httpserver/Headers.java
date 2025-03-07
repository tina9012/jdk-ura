/*
 * Copyright (c) 2005, 2021, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.net.httpserver;

import org.checkerframework.dataflow.qual.Pure;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import sun.net.httpserver.UnmodifiableHeaders;

public class Headers implements Map<String, List<String>> {

    public Headers() {
    }

    public Headers(Map<String, List<String>> headers) {
    }

    @Override
    public int size();

    @Override
    public boolean isEmpty();

    @Override
    public boolean containsKey(Object key);

    @Override
    public boolean containsValue(Object value);

    @Override
    public List<String> get(Object key);

    public String getFirst(String key);

    @Override
    public List<String> put(String key, List<String> value);

    public void add(String key, String value);

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
    public void replaceAll(BiFunction<? super String, ? super List<String>, ? extends List<String>> function);

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    @Override
    public String toString();

    public static Headers of(String... headers);

    public static Headers of(Map<String, List<String>> headers);
}
