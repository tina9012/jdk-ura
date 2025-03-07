/*
 * Copyright (c) 2016, 2021, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import jdk.internal.access.JavaUtilCollectionAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.misc.CDS;
import jdk.internal.vm.annotation.Stable;

@SuppressWarnings("serial")
class ImmutableCollections {

    static class Access {
    }

    static UnsupportedOperationException uoe();

    @jdk.internal.ValueBased
    abstract static class AbstractImmutableCollection<E> extends AbstractCollection<E> {

        @Override
        @EnsuresNonEmpty("this")
        public boolean add(E e);

        @Override
        public boolean addAll(Collection<? extends E> c);

        @Override
        public void clear();

        @Override
        public boolean remove(@UnknownSignedness Object o);

        @Override
        public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);
    }

    @SuppressWarnings("unchecked")
    static <E> List<E> listCopy(Collection<? extends E> coll);

    @SafeVarargs
    static <E> List<E> listFromArray(E... input);

    @SuppressWarnings("unchecked")
    static <E> List<E> listFromTrustedArray(Object... input);

    @SuppressWarnings("unchecked")
    static <E> List<E> listFromTrustedArrayNullsAllowed(Object... input);

    @jdk.internal.ValueBased
    abstract static class AbstractImmutableList<E> extends AbstractImmutableCollection<E> implements List<E>, RandomAccess {

        @Override
        public void add(int index, E element);

        @Override
        public boolean addAll(int index, Collection<? extends E> c);

        @Override
        public E remove(int index);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public E set(int index, E element);

        @Override
        public void sort(Comparator<? super E> c);

        @Override
        public List<E> subList(int fromIndex, int toIndex);

        static void subListRangeCheck(int fromIndex, int toIndex, int size);

        @Override
        public Iterator<E> iterator();

        @Override
        public ListIterator<E> listIterator();

        @Override
        public ListIterator<E> listIterator(final int index);

        @Override
        public boolean equals(Object o);

        @Override
        public int hashCode();

        @Override
        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        IndexOutOfBoundsException outOfBounds(int index);
    }

    static final class ListItr<E> implements ListIterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty ListItr<E> this);

        public void remove();

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void set(E e);

        public void add(E e);
    }

    static final class SubList<E> extends AbstractImmutableList<E> implements RandomAccess {

        static <E> SubList<E> fromSubList(SubList<E> parent, int fromIndex, int toIndex);

        static <E> SubList<E> fromList(AbstractImmutableList<E> list, int fromIndex, int toIndex);

        public E get(int index);

        @Pure
        public int size();

        public Iterator<E> iterator();

        public ListIterator<E> listIterator(int index);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public int indexOf(Object o);

        @Override
        public int lastIndexOf(Object o);

        @Override
        public Object[] toArray();

        @Override
        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);
    }

    @jdk.internal.ValueBased
    static final class List12<E> extends AbstractImmutableList<E> implements Serializable {

        @Override
        @Pure
        public int size();

        @Override
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Override
        @SuppressWarnings("unchecked")
        public E get(int index);

        @Override
        public int indexOf(Object o);

        @Override
        public int lastIndexOf(Object o);

        @Override
        public Object[] toArray();

        @Override
        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);
    }

    @jdk.internal.ValueBased
    static final class ListN<E> extends AbstractImmutableList<E> implements Serializable {

        @Pure
        @Override
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Override
        @Pure
        public int size();

        @Override
        public E get(int index);

        @Override
        public Object[] toArray();

        @Override
        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        @Override
        public int indexOf(Object o);

        @Override
        public int lastIndexOf(Object o);
    }

    @jdk.internal.ValueBased
    abstract static class AbstractImmutableSet<E> extends AbstractImmutableCollection<E> implements Set<E> {

        @Override
        public boolean equals(Object o);

        @Override
        public abstract int hashCode();
    }

    @jdk.internal.ValueBased
    static final class Set12<E> extends AbstractImmutableSet<E> implements Serializable {

        @Override
        @Pure
        public int size();

        @Override
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Override
        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        @Override
        public int hashCode();

        @Override
        public Iterator<E> iterator();

        @Override
        public Object[] toArray();

        @Override
        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);
    }

    @jdk.internal.ValueBased
    static final class SetN<E> extends AbstractImmutableSet<E> implements Serializable {

        @Override
        @Pure
        public int size();

        @Override
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Override
        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        private final class SetNIterator implements Iterator<E> {

            @Override
            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public boolean hasNext();

            @Override
            @SideEffectsOnly("this")
            public E next(@NonEmpty SetNIterator this);
        }

        @Override
        public Iterator<E> iterator();

        @Override
        public int hashCode();

        @Override
        public Object[] toArray();

        @Override
        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);
    }

    abstract static class AbstractImmutableMap<K, V> extends AbstractMap<K, V> implements Serializable {

        @Override
        public void clear();

        @Override
        @PolyNull
        public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> rf);

        @Override
        @PolyNull
        public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mf);

        @Override
        @PolyNull
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> rf);

        @Override
        @PolyNull
        public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> rf);

        @Override
        public V put(K key, V value);

        @Override
        public void putAll(Map<? extends K, ? extends V> m);

        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public V remove(Object key);

        @Override
        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        @Override
        public V replace(K key, V value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> f);

        @Override
        public V getOrDefault(Object key, V defaultValue);
    }

    static final class Map1<K, V> extends AbstractImmutableMap<K, V> {

        @Override
        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        @Override
        public V get(Object o);

        @Pure
        @Override
        public boolean containsKey(@UnknownSignedness Object o);

        @Override
        @Pure
        public boolean containsValue(@UnknownSignedness Object o);

        @Override
        public int size();

        @Override
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Override
        public int hashCode();
    }

    static final class MapN<K, V> extends AbstractImmutableMap<K, V> {

        @Override
        @Pure
        public boolean containsKey(@UnknownSignedness Object o);

        @Override
        @Pure
        public boolean containsValue(@UnknownSignedness Object o);

        @Override
        public int hashCode();

        @Override
        @SuppressWarnings("unchecked")
        public V get(Object o);

        @Override
        @Pure
        public int size();

        @Override
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        class MapNIterator implements Iterator<Map.Entry<K, V>> {

            @Override
            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public boolean hasNext();

            @Override
            public Map.Entry<K, V> next(@NonEmpty MapNIterator this);
        }

        @Override
        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();
    }
}

final class CollSer implements Serializable {
}
