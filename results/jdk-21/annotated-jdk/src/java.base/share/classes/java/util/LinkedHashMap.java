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
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.io.IOException;
import java.util.function.Function;

@AnnotatedFor({ "lock", "nullness", "index" })
public class LinkedHashMap<K, V> extends HashMap<K, V> implements SequencedMap<K, V> {

    static class Entry<K, V> extends HashMap.Node<K, V> {
    }

    void reinitialize();

    Node<K, V> newNode(int hash, K key, V value, Node<K, V> e);

    Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next);

    TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next);

    TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next);

    void afterNodeRemoval(Node<K, V> e);

    void afterNodeInsertion(boolean evict);

    void afterNodeAccess(Node<K, V> e);

    public V putFirst(K k, V v);

    public V putLast(K k, V v);

    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException;

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor) {
    }

    public LinkedHashMap(@NonNegative int initialCapacity) {
    }

    public LinkedHashMap() {
    }

    @PolyNonEmpty
    public LinkedHashMap(@PolyNonEmpty Map<? extends K, ? extends V> m) {
    }

    public LinkedHashMap(@NonNegative int initialCapacity, float loadFactor, boolean accessOrder) {
    }

    @Pure
    public boolean containsValue(@GuardSatisfied LinkedHashMap<K, V> this, @GuardSatisfied @Nullable @UnknownSignedness Object value);

    @Pure
    @Nullable
    public V get(@GuardSatisfied LinkedHashMap<K, V> this, @UnknownSignedness @GuardSatisfied @Nullable Object key);

    @Pure
    public V getOrDefault(@Nullable Object key, V defaultValue);

    public void clear(@GuardSatisfied LinkedHashMap<K, V> this);

    protected boolean removeEldestEntry(@GuardSatisfied LinkedHashMap<K, V> this, Map.Entry<K, V> eldest);

    @SideEffectFree
    public Set<@KeyFor({ "this" }) K> keySet();

    public SequencedSet<K> sequencedKeySet();

    static <K1, V1> Node<K1, V1> nsee(Node<K1, V1> node);

    final <T> T[] keysToArray(T[] a);

    final <T> T[] keysToArray(T[] a, boolean reversed);

    final <T> T[] valuesToArray(T[] a, boolean reversed);

    final class LinkedKeySet extends AbstractSet<K> implements SequencedSet<K> {

        @Pure
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

        public final void addFirst(K k);

        public final void addLast(K k);

        public final K getFirst();

        public final K getLast();

        public final K removeFirst();

        public final K removeLast();

        public SequencedSet<K> reversed();
    }

    public Collection<V> values();

    public SequencedCollection<V> sequencedValues();

    final class LinkedValues extends AbstractCollection<V> implements SequencedCollection<V> {

        @Pure
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

        public final void addFirst(V v);

        public final void addLast(V v);

        public final V getFirst();

        public final V getLast();

        public final V removeFirst();

        public final V removeLast();

        public SequencedCollection<V> reversed();
    }

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied LinkedHashMap<K, V> this);

    public SequencedSet<Map.Entry<K, V>> sequencedEntrySet();

    final class LinkedEntrySet extends AbstractSet<Map.Entry<K, V>> implements SequencedSet<Map.Entry<K, V>> {

        @Pure
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

        final Node<K, V> nsee(Node<K, V> e);

        public final void addFirst(Map.Entry<K, V> e);

        public final void addLast(Map.Entry<K, V> e);

        public final Map.Entry<K, V> getFirst();

        public final Map.Entry<K, V> getLast();

        public final Map.Entry<K, V> removeFirst();

        public final Map.Entry<K, V> removeLast();

        public SequencedSet<Map.Entry<K, V>> reversed();
    }

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    abstract class LinkedHashIterator {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean hasNext();

        @SideEffectsOnly("this")
        final LinkedHashMap.Entry<K, V> nextNode(@NonEmpty LinkedHashIterator this);

        public final void remove();
    }

    final class LinkedKeyIterator extends LinkedHashIterator implements Iterator<K> {

        public final K next(@NonEmpty LinkedKeyIterator this);
    }

    final class LinkedValueIterator extends LinkedHashIterator implements Iterator<V> {

        public final V next(@NonEmpty LinkedValueIterator this);
    }

    final class LinkedEntryIterator extends LinkedHashIterator implements Iterator<Map.Entry<K, V>> {

        public final Map.Entry<K, V> next(@NonEmpty LinkedEntryIterator this);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int numMappings);

    public SequencedMap<K, V> reversed();

    static class ReversedLinkedHashMapView<K, V> extends AbstractMap<K, V> implements SequencedMap<K, V> {

        public boolean equals(Object o);

        public int hashCode();

        public int size();

        public boolean isEmpty();

        public boolean containsKey(Object key);

        public boolean containsValue(Object value);

        public V get(Object key);

        public V put(K key, V value);

        public V remove(Object key);

        public void putAll(Map<? extends K, ? extends V> m);

        public void clear();

        public Set<K> keySet();

        public Collection<V> values();

        public Set<Entry<K, V>> entrySet();

        public V getOrDefault(Object key, V defaultValue);

        public void forEach(BiConsumer<? super K, ? super V> action);

        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        public V putIfAbsent(K key, V value);

        public boolean remove(Object key, Object value);

        public boolean replace(K key, V oldValue, V newValue);

        public V replace(K key, V value);

        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        public SequencedMap<K, V> reversed();

        public Entry<K, V> firstEntry();

        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        public V putFirst(K k, V v);

        public V putLast(K k, V v);
    }
}
