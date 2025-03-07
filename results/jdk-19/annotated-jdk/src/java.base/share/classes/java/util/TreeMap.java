/*
 * Copyright (c) 1997, 2022, Oracle and/or its affiliates. All rights reserved.
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
import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@CFComment({ "lock/nullness: This permits null element when using a custom comparator that allows null" })
@AnnotatedFor({ "lock", "nullness", "index" })
public class TreeMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, java.io.Serializable {

    public TreeMap() {
    }

    public TreeMap(@Nullable Comparator<? super K> comparator) {
    }

    @PolyNonEmpty
    public TreeMap(@PolyNonEmpty Map<? extends K, ? extends V> m) {
    }

    @PolyNonEmpty
    public TreeMap(@PolyNonEmpty SortedMap<K, ? extends V> m) {
    }

    @Pure
    @NonNegative
    public int size(@GuardSatisfied TreeMap<K, V> this);

    @Pure
    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    public boolean containsKey(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied @UnknownSignedness Object key);

    @Pure
    public boolean containsValue(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied @UnknownSignedness Object value);

    @Nullable
    public V get(@GuardSatisfied TreeMap<K, V> this, @UnknownSignedness @GuardSatisfied Object key);

    @Pure
    @Nullable
    public Comparator<? super K> comparator(@GuardSatisfied TreeMap<K, V> this);

    @KeyFor("this")
    public K firstKey(@NonEmpty TreeMap<K, V> this);

    @KeyFor("this")
    public K lastKey(@NonEmpty TreeMap<K, V> this);

    public void putAll(@GuardSatisfied TreeMap<K, V> this, Map<? extends K, ? extends V> map);

    final Entry<K, V> getEntry(Object key);

    final Entry<K, V> getEntryUsingComparator(Object key);

    final Entry<K, V> getCeilingEntry(K key);

    final Entry<K, V> getFloorEntry(K key);

    final Entry<K, V> getHigherEntry(K key);

    final Entry<K, V> getLowerEntry(K key);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(@GuardSatisfied TreeMap<K, V> this, K key, V value);

    @Override
    public V putIfAbsent(K key, V value);

    @Override
    @PolyNull
    public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @Override
    @PolyNull
    public V computeIfPresent(K key, BiFunction<? super K, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

    @Override
    @PolyNull
    public V compute(K key, BiFunction<? super K, ? super @Nullable V, ? extends @PolyNull V> remappingFunction);

    @Override
    @PolyNull
    public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

    @Nullable
    public V remove(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied @UnknownSignedness Object key);

    public void clear(@GuardSatisfied TreeMap<K, V> this);

    public Object clone(@GuardSatisfied TreeMap<K, V> this);

    public Map.@Nullable Entry<K, V> firstEntry();

    public Map.@Nullable Entry<K, V> lastEntry();

    public Map.@Nullable Entry<K, V> pollFirstEntry(@GuardSatisfied TreeMap<K, V> this);

    public Map.@Nullable Entry<K, V> pollLastEntry(@GuardSatisfied TreeMap<K, V> this);

    public Map.@Nullable Entry<K, V> lowerEntry(K key);

    @Nullable
    public K lowerKey(K key);

    public Map.@Nullable Entry<K, V> floorEntry(K key);

    @Nullable
    public K floorKey(K key);

    public Map.@Nullable Entry<K, V> ceilingEntry(K key);

    @Nullable
    public K ceilingKey(K key);

    public Map.@Nullable Entry<K, V> higherEntry(K key);

    @Nullable
    public K higherKey(K key);

    public Set<@KeyFor({ "this" }) K> keySet(@GuardSatisfied TreeMap<K, V> this);

    @SideEffectFree
    public NavigableSet<@KeyFor({ "this" }) K> navigableKeySet(@GuardSatisfied TreeMap<K, V> this);

    @SideEffectFree
    public NavigableSet<@KeyFor({ "this" }) K> descendingKeySet(@GuardSatisfied TreeMap<K, V> this);

    public Collection<V> values(@GuardSatisfied TreeMap<K, V> this);

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet(@GuardSatisfied TreeMap<K, V> this);

    @SideEffectFree
    public NavigableMap<K, V> descendingMap(@GuardSatisfied TreeMap<K, V> this);

    @SideEffectFree
    public NavigableMap<K, V> subMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K fromKey, boolean fromInclusive, @GuardSatisfied K toKey, boolean toInclusive);

    @SideEffectFree
    public NavigableMap<K, V> headMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K toKey, boolean inclusive);

    @SideEffectFree
    public NavigableMap<K, V> tailMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K fromKey, boolean inclusive);

    @SideEffectFree
    public SortedMap<K, V> subMap(@GuardSatisfied TreeMap<K, V> this, @GuardSatisfied K fromKey, @GuardSatisfied K toKey);

    @SideEffectFree
    public SortedMap<K, V> headMap(@GuardSatisfied TreeMap<K, V> this, K toKey);

    @SideEffectFree
    public SortedMap<K, V> tailMap(@GuardSatisfied TreeMap<K, V> this, K fromKey);

    @Override
    public boolean replace(K key, V oldValue, V newValue);

    @Override
    public V replace(K key, V value);

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action);

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    class Values extends AbstractCollection<V> {

        @SideEffectFree
        public Iterator<V> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        public void clear();

        @SideEffectFree
        public Spliterator<V> spliterator();
    }

    class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @Pure
        @NonNegative
        public int size();

        public void clear();

        @SideEffectFree
        public Spliterator<Map.Entry<K, V>> spliterator();
    }

    Iterator<K> keyIterator();

    Iterator<K> descendingKeyIterator();

    static final class KeySet<E> extends AbstractSet<E> implements NavigableSet<E> {

        @SideEffectFree
        public Iterator<E> iterator();

        public Iterator<E> descendingIterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public void clear();

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E first();

        public E last();

        public Comparator<? super E> comparator();

        public E pollFirst();

        public E pollLast();

        public boolean remove(@UnknownSignedness Object o);

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);

        public NavigableSet<E> descendingSet();

        @SideEffectFree
        public Spliterator<E> spliterator();
    }

    abstract class PrivateEntryIterator<T> implements Iterator<T> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean hasNext();

        @SideEffectsOnly("this")
        final Entry<K, V> nextEntry();

        @SideEffectsOnly("this")
        final Entry<K, V> prevEntry();

        public void remove();
    }

    final class EntryIterator extends PrivateEntryIterator<Map.Entry<K, V>> {

        public Map.Entry<K, V> next(@NonEmpty EntryIterator this);
    }

    final class ValueIterator extends PrivateEntryIterator<V> {

        public V next(@NonEmpty ValueIterator this);
    }

    final class KeyIterator extends PrivateEntryIterator<K> {

        public K next(@NonEmpty KeyIterator this);
    }

    final class DescendingKeyIterator extends PrivateEntryIterator<K> {

        public K next(@NonEmpty DescendingKeyIterator this);

        public void remove();
    }

    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2);

    static final boolean valEquals(Object o1, Object o2);

    static <K, V> Map.Entry<K, V> exportEntry(TreeMap.Entry<K, V> e);

    static <K, V> K keyOrNull(TreeMap.Entry<K, V> e);

    static <K> K key(@NonNull Entry<K, ?> e);

    abstract static class NavigableSubMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, java.io.Serializable {

        final boolean tooLow(Object key);

        final boolean tooHigh(Object key);

        final boolean inRange(Object key);

        final boolean inClosedRange(Object key);

        final boolean inRange(Object key, boolean inclusive);

        final TreeMap.Entry<K, V> absLowest();

        final TreeMap.Entry<K, V> absHighest();

        final TreeMap.Entry<K, V> absCeiling(K key);

        final TreeMap.Entry<K, V> absHigher(K key);

        final TreeMap.Entry<K, V> absFloor(K key);

        final TreeMap.Entry<K, V> absLower(K key);

        final TreeMap.Entry<K, V> absHighFence();

        final TreeMap.Entry<K, V> absLowFence();

        abstract TreeMap.Entry<K, V> subLowest();

        abstract TreeMap.Entry<K, V> subHighest();

        abstract TreeMap.Entry<K, V> subCeiling(K key);

        abstract TreeMap.Entry<K, V> subHigher(K key);

        abstract TreeMap.Entry<K, V> subFloor(K key);

        abstract TreeMap.Entry<K, V> subLower(K key);

        abstract Iterator<K> keyIterator();

        abstract Spliterator<K> keySpliterator();

        abstract Iterator<K> descendingKeyIterator();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public final boolean containsKey(@UnknownSignedness Object key);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public final V put(K key, V value);

        public V putIfAbsent(K key, V value);

        public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

        public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

        public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

        public final V get(Object key);

        public final V remove(Object key);

        public final Map.Entry<K, V> ceilingEntry(K key);

        public final K ceilingKey(K key);

        public final Map.Entry<K, V> higherEntry(K key);

        public final K higherKey(K key);

        public final Map.Entry<K, V> floorEntry(K key);

        public final K floorKey(K key);

        public final Map.Entry<K, V> lowerEntry(K key);

        public final K lowerKey(K key);

        public final K firstKey();

        public final K lastKey();

        public final Map.Entry<K, V> firstEntry();

        public final Map.Entry<K, V> lastEntry();

        public final Map.Entry<K, V> pollFirstEntry();

        public final Map.Entry<K, V> pollLastEntry();

        @SideEffectFree
        public final NavigableSet<K> navigableKeySet();

        public final Set<K> keySet();

        @SideEffectFree
        public NavigableSet<K> descendingKeySet();

        @SideEffectFree
        public final SortedMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public final SortedMap<K, V> headMap(K toKey);

        @SideEffectFree
        public final SortedMap<K, V> tailMap(K fromKey);

        abstract class EntrySetView extends AbstractSet<Map.Entry<K, V>> {

            @Pure
            public int size();

            @Pure
            @EnsuresNonEmptyIf(result = false, expression = "this")
            public boolean isEmpty();

            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public boolean contains(@UnknownSignedness Object o);

            public boolean remove(@UnknownSignedness Object o);
        }

        abstract class SubMapIterator<T> implements Iterator<T> {

            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public final boolean hasNext();

            @SideEffectsOnly("this")
            final TreeMap.Entry<K, V> nextEntry();

            @SideEffectsOnly("this")
            final TreeMap.Entry<K, V> prevEntry();

            final void removeAscending();

            final void removeDescending();
        }

        final class SubMapEntryIterator extends SubMapIterator<Map.Entry<K, V>> {

            public Map.Entry<K, V> next(@NonEmpty SubMapEntryIterator this);

            public void remove();
        }

        final class DescendingSubMapEntryIterator extends SubMapIterator<Map.Entry<K, V>> {

            public Map.Entry<K, V> next(@NonEmpty DescendingSubMapEntryIterator this);

            public void remove();
        }

        final class SubMapKeyIterator extends SubMapIterator<K> implements Spliterator<K> {

            public K next(@NonEmpty SubMapKeyIterator this);

            public void remove();

            public Spliterator<K> trySplit();

            public void forEachRemaining(Consumer<? super K> action);

            public boolean tryAdvance(Consumer<? super K> action);

            public long estimateSize();

            public int characteristics();

            public final Comparator<? super K> getComparator();
        }

        final class DescendingSubMapKeyIterator extends SubMapIterator<K> implements Spliterator<K> {

            public K next(@NonEmpty DescendingSubMapKeyIterator this);

            public void remove();

            public Spliterator<K> trySplit();

            public void forEachRemaining(Consumer<? super K> action);

            public boolean tryAdvance(Consumer<? super K> action);

            public long estimateSize();

            public int characteristics();
        }
    }

    static final class AscendingSubMap<K, V> extends NavigableSubMap<K, V> {

        public Comparator<? super K> comparator();

        @SideEffectFree
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> descendingMap();

        Iterator<K> keyIterator();

        Spliterator<K> keySpliterator();

        Iterator<K> descendingKeyIterator();

        final class AscendingEntrySetView extends EntrySetView {

            public Iterator<Map.Entry<K, V>> iterator();
        }

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        TreeMap.Entry<K, V> subLowest();

        TreeMap.Entry<K, V> subHighest();

        TreeMap.Entry<K, V> subCeiling(K key);

        TreeMap.Entry<K, V> subHigher(K key);

        TreeMap.Entry<K, V> subFloor(K key);

        TreeMap.Entry<K, V> subLower(K key);
    }

    static final class DescendingSubMap<K, V> extends NavigableSubMap<K, V> {

        public Comparator<? super K> comparator();

        @SideEffectFree
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> descendingMap();

        Iterator<K> keyIterator();

        Spliterator<K> keySpliterator();

        Iterator<K> descendingKeyIterator();

        final class DescendingEntrySetView extends EntrySetView {

            public Iterator<Map.Entry<K, V>> iterator();
        }

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        TreeMap.Entry<K, V> subLowest();

        TreeMap.Entry<K, V> subHighest();

        TreeMap.Entry<K, V> subCeiling(K key);

        TreeMap.Entry<K, V> subHigher(K key);

        TreeMap.Entry<K, V> subFloor(K key);

        TreeMap.Entry<K, V> subLower(K key);
    }

    private class SubMap extends AbstractMap<K, V> implements SortedMap<K, V>, java.io.Serializable {

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        public K lastKey();

        public K firstKey();

        @SideEffectFree
        public SortedMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public SortedMap<K, V> headMap(K toKey);

        @SideEffectFree
        public SortedMap<K, V> tailMap(K fromKey);

        public Comparator<? super K> comparator();
    }

    static final class Entry<K, V> implements Map.Entry<K, V> {

        public K getKey();

        public V getValue();

        public V setValue(V value);

        public boolean equals(Object o);

        public int hashCode();

        public String toString();
    }

    final Entry<K, V> getFirstEntry();

    final Entry<K, V> getLastEntry();

    static <K, V> TreeMap.Entry<K, V> successor(Entry<K, V> t);

    static <K, V> Entry<K, V> predecessor(Entry<K, V> t);

    void readTreeSet(int size, java.io.ObjectInputStream s, V defaultVal) throws java.io.IOException, ClassNotFoundException;

    void addAllForTreeSet(SortedSet<? extends K> set, V defaultVal);

    static <K> Spliterator<K> keySpliteratorFor(NavigableMap<K, ?> m);

    final Spliterator<K> keySpliterator();

    final Spliterator<K> descendingKeySpliterator();

    static class TreeMapSpliterator<K, V> {

        final int getEstimate();

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<K> {

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();

        public final Comparator<? super K> getComparator();
    }

    static final class DescendingKeySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<K> {

        public DescendingKeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<V> {

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();

        @Override
        public Comparator<Map.Entry<K, V>> getComparator();
    }
}
