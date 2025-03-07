/*
 * Copyright (c) 2000, 2021, Oracle and/or its affiliates. All rights reserved.
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
package javax.print.attribute;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class HashAttributeSet implements AttributeSet, Serializable {

    public HashAttributeSet() {
    }

    public HashAttributeSet(Attribute attribute) {
    }

    public HashAttributeSet(Attribute[] attributes) {
    }

    public HashAttributeSet(AttributeSet attributes) {
    }

    protected HashAttributeSet(Class<?> interfaceName) {
    }

    protected HashAttributeSet(Attribute attribute, Class<?> interfaceName) {
    }

    protected HashAttributeSet(Attribute[] attributes, Class<?> interfaceName) {
    }

    protected HashAttributeSet(AttributeSet attributes, Class<?> interfaceName) {
    }

    public Attribute get(Class<?> category);

    public boolean add(Attribute attribute);

    public boolean remove(Class<?> category);

    public boolean remove(Attribute attribute);

    public boolean containsKey(Class<?> category);

    public boolean containsValue(Attribute attribute);

    public boolean addAll(AttributeSet attributes);

    public int size();

    public Attribute[] toArray();

    public void clear();

    public boolean isEmpty();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object object);

    public int hashCode();
}
