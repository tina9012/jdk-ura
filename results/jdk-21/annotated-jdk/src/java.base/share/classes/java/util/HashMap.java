/*
 * Copyright (c) 1997, 2023, Oracle and/or its affiliates. All rights reserved.
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

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import jdk.internal.access.SharedSecrets;

@AnnotatedFor({ "lock", "nullness", "index" })
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    static class Node<K, V> implements Map.Entry<K, V> {

        public final K getKey();

        public final V getValue();

        public final String toString();

        public final int hashCode();

        public final V setValue(V newValue);

        public final boolean equals(Object o);
    }

    static final int hash(@Nullable Object key);

    static Class<?> comparableClassFor(Object x);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static int compareComparables(Class<?> kc, Object k, Object x);

    static final int tableSizeFor(int cap);

    public HashMap(@NonNegative int initialCapacity, float loadFactor) {
    }

    public HashMap(@NonNegative int initialCapacity) {
    }

    public HashMap() {
    }

    @PolyNonEmpty
    public HashMap(@PolyNonEmpty Map<? extends K, ? extends V> m) {
    }

    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict);

    @Pure
    @NonNegative
    public int size(@GuardSatisfied HashMap<K, V> this);

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty(@GuardSatisfied HashMap<K, V> this);

    @Pure
    @Nullable
    public V get(@GuardSatisfied HashMap<K, V> this, @UnknownSignedness @GuardSatisfied @Nullable Object key);

    final Node<K, V> getNode(@Nullable Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable @UnknownSignedness Object key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied HashMap<K, V> this, K key, V value);

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict);

    @SuppressWarnings("cast.unsafe")
    final Node<K, V>[] resize();

    final void treeifyBin(Node<K, V>[] tab, int hash);

    public void putAll(@GuardSatisfied HashMap<K, V> this, Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable @UnknownSignedness Object key);

    final Node<K, V> removeNode(int hash, @Nullable Object key, @Nullable Object value, boolean matchValue, boolean movable);

    public void clear(@GuardSatisfied HashMap<K, V> this);

    @Pure
    public boolean containsValue(@GuardSatisfied HashMap<K, V> this, @GuardSatisfied @Nullable @UnknownSignedness Object value);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied HashMap<K, V> this);

    @SuppressWarnings("unchecked")
    final <T> T[] prepareArray(T[] a);

    <T> T[] keysToArray(T[] a);

    <T> T[] valuesToArray(T[] a);

    final class KeySet extends AbstractSet<K> {

        @Pure
        @NonNegative
        public final int size();

        public final void clear();

        @SideEffectFree
        public final Iterator<K> iterator();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean contains(@Nullable @UnknownSignedness Object o);

        public final boolean remove(@Nullable @UnknownSignedness Object key);

        @SideEffectFree
        public final Spliterator<K> spliterator();

        public Object[] toArray();

        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public final void forEach(Consumer<? super K> action);
    }

    @SideEffectFree
    public Collection<V> values(@GuardSatisfied HashMap<K, V> this);

    final class Values extends AbstractCollection<V> {

        @Pure
        @NonNegative
        public final int size();

        public final void clear();

        @SideEffectFree
        public final Iterator<V> iterator();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean contains(@Nullable @UnknownSignedness Object o);

        @SideEffectFree
        public final Spliterator<V> spliterator();

        public Object[] toArray();

        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public final void forEach(Consumer<? super V> action);
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied HashMap<K, V> this);

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Pure
        @NonNegative
        public final int size();

        public final void clear();

        @SideEffectFree
        public final Iterator<Map.Entry<K, V>> iterator();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean contains(@Nullable @UnknownSignedness Object o);

        public final boolean remove(@Nullable @UnknownSignedness Object o);

        @SideEffectFree
        public final Spliterator<Map.Entry<K, V>> spliterator();

        public final void forEach(Consumer<? super Map.Entry<K, V>> action);
    }

    @Override
    @Pure
    public V getOrDefault(@GuardSatisfied @Nullable @UnknownSignedness Object key, V defaultValue);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Override
    @Nullable
    public V putIfAbsent(K key, V value);

    @Override
    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object key, @GuardSatisfied @Nullable @UnknownSignedness Object value);

    @Override
    public boolean replace(K key, V oldValue, V newValue);

    @Override
    @Nullable
    public V replace(K key, V value);

    @Override
    @PolyNull
    public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @Override
    @PolyNull
    public V computeIfPresent(K key, BiFunction<? super K, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

    @Override
    @PolyNull
    public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @Override
    @PolyNull
    public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    @SideEffectFree
    @SuppressWarnings("unchecked")
    @Override
    public Object clone(@GuardSatisfied HashMap<K, V> this);

    final float loadFactor();

    final int capacity();

    private static final class UnsafeHolder {

        static void putLoadFactor(HashMap<?, ?> map, float lf);
    }

    abstract class HashIterator {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean hasNext();

        @SideEffectsOnly("this")
        final Node<K, V> nextNode(@NonEmpty HashIterator this);

        public final void remove();
    }

    final class KeyIterator extends HashIterator implements Iterator<K> {

        public final K next(@NonEmpty KeyIterator this);
    }

    final class ValueIterator extends HashIterator implements Iterator<V> {

        public final V next(@NonEmpty ValueIterator this);
    }

    final class EntryIterator extends HashIterator implements Iterator<Map.Entry<K, V>> {

        public final Map.Entry<K, V> next(@NonEmpty EntryIterator this);
    }

    static class HashMapSpliterator<K, V> {

        final int getFence();

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<K> {

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<V> {

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();
    }

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next);

    Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next);

    TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next);

    TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next);

    void reinitialize();

    void afterNodeAccess(Node<K, V> p);

    void afterNodeInsertion(boolean evict);

    void afterNodeRemoval(Node<K, V> p);

    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException;

    static final class TreeNode<K, V> extends LinkedHashMap.Entry<K, V> {

        final TreeNode<K, V> root();

        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root);

        final TreeNode<K, V> find(int h, Object k, Class<?> kc);

        final TreeNode<K, V> getTreeNode(int h, Object k);

        static int tieBreakOrder(Object a, Object b);

        final void treeify(Node<K, V>[] tab);

        final Node<K, V> untreeify(HashMap<K, V> map);

        final TreeNode<K, V> putTreeVal(HashMap<K, V> map, Node<K, V>[] tab, int h, K k, V v);

        final void removeTreeNode(HashMap<K, V> map, Node<K, V>[] tab, boolean movable);

        final void split(HashMap<K, V> map, Node<K, V>[] tab, int index, int bit);

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> boolean checkInvariants(TreeNode<K, V> t);
    }

    static int calculateHashMapCapacity(int numMappings);

    public static <K, V> HashMap<K, V> newHashMap(int numMappings);
}
