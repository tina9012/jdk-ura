/*
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
package java.util.concurrent;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.concurrent.atomic.LongAdder;

@AnnotatedFor({ "nullness" })
public class ConcurrentSkipListMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V>, Cloneable, Serializable {

    static final class Node<K, V> {
    }

    static final class Index<K, V> {
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static int cpr(Comparator c, Object x, Object y);

    final Node<K, V> baseHead();

    static <K, V> void unlinkNode(Node<K, V> b, Node<K, V> n);

    final long getAdderCount();

    static <K, V> boolean addIndices(Index<K, V> q, int skips, Index<K, V> x, Comparator<? super K> cmp);

    final V doRemove(Object key, Object value);

    final Node<K, V> findFirst();

    final AbstractMap.SimpleImmutableEntry<K, V> findFirstEntry();

    final Node<K, V> findLast();

    final AbstractMap.SimpleImmutableEntry<K, V> findLastEntry();

    final Node<K, V> findNear(K key, int rel, Comparator<? super K> cmp);

    final AbstractMap.SimpleImmutableEntry<K, V> findNearEntry(K key, int rel, Comparator<? super K> cmp);

    public ConcurrentSkipListMap() {
    }

    public ConcurrentSkipListMap(@Nullable Comparator<? super K> comparator) {
    }

    @SuppressWarnings("this-escape")
    public ConcurrentSkipListMap(Map<? extends K, ? extends V> m) {
    }

    @SuppressWarnings("this-escape")
    public ConcurrentSkipListMap(SortedMap<K, ? extends V> m) {
    }

    public ConcurrentSkipListMap<K, V> clone();

    @Pure
    public boolean containsKey(@GuardSatisfied @UnknownSignedness Object key);

    @Nullable
    public V get(Object key);

    @Pure
    @PolyNull
    public V getOrDefault(@GuardSatisfied @UnknownSignedness Object key, @PolyNull V defaultValue);

    @Nullable
    public V put(K key, V value);

    @Nullable
    public V remove(@GuardSatisfied @UnknownSignedness Object key);

    @Pure
    public boolean containsValue(@GuardSatisfied @UnknownSignedness Object value);

    @Pure
    public int size();

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    public void clear();

    @PolyNull
    public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @PolyNull
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @PolyNull
    public V compute(K key, BiFunction<? super K, ? super @Nullable V, ? extends @PolyNull V> remappingFunction);

    @PolyNull
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends @PolyNull V> remappingFunction);

    public NavigableSet<K> keySet();

    @SideEffectFree
    public NavigableSet<K> navigableKeySet();

    public Collection<V> values();

    @SideEffectFree
    public Set<Map.Entry<K, V>> entrySet();

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> descendingMap();

    @SideEffectFree
    public NavigableSet<K> descendingKeySet();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    @Nullable
    public V putIfAbsent(K key, V value);

    public boolean remove(@GuardSatisfied @UnknownSignedness Object key, @GuardSatisfied @UnknownSignedness Object value);

    public boolean replace(K key, V oldValue, V newValue);

    @Nullable
    public V replace(K key, V value);

    @Nullable
    public Comparator<? super K> comparator();

    public K firstKey(@NonEmpty ConcurrentSkipListMap<K, V> this);

    public K lastKey(@NonEmpty ConcurrentSkipListMap<K, V> this);

    public V putFirst(K k, V v);

    public V putLast(K k, V v);

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> headMap(K toKey, boolean inclusive);

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> tailMap(K fromKey, boolean inclusive);

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> subMap(K fromKey, K toKey);

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> headMap(K toKey);

    @SideEffectFree
    public ConcurrentNavigableMap<K, V> tailMap(K fromKey);

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

    public Map.@Nullable Entry<K, V> firstEntry();

    public Map.@Nullable Entry<K, V> lastEntry();

    public Map.@Nullable Entry<K, V> pollFirstEntry();

    public Map.@Nullable Entry<K, V> pollLastEntry();

    abstract class Iter<T> implements Iterator<T> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean hasNext();

        final void advance(Node<K, V> b);

        public final void remove();
    }

    final class ValueIterator extends Iter<V> {

        public V next(@NonEmpty ValueIterator this);
    }

    final class KeyIterator extends Iter<K> {

        public K next(@NonEmpty KeyIterator this);
    }

    final class EntryIterator extends Iter<Map.Entry<K, V>> {

        public Map.Entry<K, V> next(@NonEmpty EntryIterator this);
    }

    static final <E> List<E> toList(Collection<E> c);

    static final class KeySet<K, V> extends AbstractSet<K> implements NavigableSet<K> {

        @Pure
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        public void clear();

        public K lower(K e);

        public K floor(K e);

        public K ceiling(K e);

        public K higher(K e);

        public Comparator<? super K> comparator();

        public K first();

        public K last();

        public K pollFirst();

        public K pollLast();

        public Iterator<K> iterator();

        public boolean equals(Object o);

        @PolyNull
        @PolySigned
        public Object[] toArray(KeySet<@PolyNull @PolySigned K, V> this);

        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public Iterator<K> descendingIterator();

        public NavigableSet<K> subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive);

        public NavigableSet<K> headSet(K toElement, boolean inclusive);

        public NavigableSet<K> tailSet(K fromElement, boolean inclusive);

        public NavigableSet<K> subSet(K fromElement, K toElement);

        public NavigableSet<K> headSet(K toElement);

        public NavigableSet<K> tailSet(K fromElement);

        public NavigableSet<K> descendingSet();

        public Spliterator<K> spliterator();
    }

    static final class Values<K, V> extends AbstractCollection<V> {

        public Iterator<V> iterator();

        @Pure
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public void clear();

        public Object[] toArray();

        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public Spliterator<V> spliterator();

        public boolean removeIf(Predicate<? super V> filter);
    }

    static final class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {

        public Iterator<Map.Entry<K, V>> iterator();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        public int size();

        public void clear();

        public boolean equals(Object o);

        public Object[] toArray();

        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public Spliterator<Map.Entry<K, V>> spliterator();

        public boolean removeIf(Predicate<? super Entry<K, V>> filter);
    }

    static final class SubMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V>, Serializable {

        boolean tooLow(Object key, Comparator<? super K> cmp);

        boolean tooHigh(Object key, Comparator<? super K> cmp);

        boolean inBounds(Object key, Comparator<? super K> cmp);

        void checkKeyBounds(K key, Comparator<? super K> cmp);

        boolean isBeforeEnd(ConcurrentSkipListMap.Node<K, V> n, Comparator<? super K> cmp);

        ConcurrentSkipListMap.Node<K, V> loNode(Comparator<? super K> cmp);

        ConcurrentSkipListMap.Node<K, V> hiNode(Comparator<? super K> cmp);

        K lowestKey(@NonEmpty SubMap<K, V> this);

        K highestKey(@NonEmpty SubMap<K, V> this);

        Map.Entry<K, V> lowestEntry();

        Map.Entry<K, V> highestEntry();

        Map.Entry<K, V> removeLowest();

        Map.Entry<K, V> removeHighest();

        Map.Entry<K, V> getNearEntry(K key, int rel);

        K getNearKey(K key, int rel);

        @Pure
        public boolean containsKey(@UnknownSignedness Object key);

        public V get(Object key);

        public V put(K key, V value);

        public V remove(Object key);

        @Pure
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        public boolean containsValue(@UnknownSignedness Object value);

        public void clear();

        public V putIfAbsent(K key, V value);

        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        public boolean replace(K key, V oldValue, V newValue);

        public V replace(K key, V value);

        public Comparator<? super K> comparator();

        SubMap<K, V> newSubMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public SubMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public SubMap<K, V> headMap(K toKey, boolean inclusive);

        @SideEffectFree
        public SubMap<K, V> tailMap(K fromKey, boolean inclusive);

        @SideEffectFree
        public SubMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public SubMap<K, V> headMap(K toKey);

        @SideEffectFree
        public SubMap<K, V> tailMap(K fromKey);

        @SideEffectFree
        public SubMap<K, V> descendingMap();

        public Map.Entry<K, V> ceilingEntry(K key);

        public K ceilingKey(K key);

        public Map.Entry<K, V> lowerEntry(K key);

        public K lowerKey(K key);

        public Map.Entry<K, V> floorEntry(K key);

        public K floorKey(K key);

        public Map.Entry<K, V> higherEntry(K key);

        public K higherKey(K key);

        public K firstKey();

        public K lastKey();

        public Map.Entry<K, V> firstEntry();

        public Map.Entry<K, V> lastEntry();

        public Map.Entry<K, V> pollFirstEntry();

        public Map.Entry<K, V> pollLastEntry();

        public NavigableSet<K> keySet();

        @SideEffectFree
        public NavigableSet<K> navigableKeySet();

        public Collection<V> values();

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        @SideEffectFree
        public NavigableSet<K> descendingKeySet();

        abstract class SubMapIter<T> implements Iterator<T>, Spliterator<T> {

            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public final boolean hasNext();

            final void advance(@NonEmpty SubMapIter<T> this);

            public void remove();

            public Spliterator<T> trySplit();

            public boolean tryAdvance(Consumer<? super T> action);

            public void forEachRemaining(Consumer<? super T> action);

            public long estimateSize();
        }

        final class SubMapValueIterator extends SubMapIter<V> {

            public V next(@NonEmpty SubMapValueIterator this);

            public int characteristics();
        }

        final class SubMapKeyIterator extends SubMapIter<K> {

            public K next(@NonEmpty SubMapKeyIterator this);

            public int characteristics();

            public final Comparator<? super K> getComparator();
        }

        final class SubMapEntryIterator extends SubMapIter<Map.Entry<K, V>> {

            public Map.Entry<K, V> next(@NonEmpty SubMapEntryIterator this);

            public int characteristics();
        }
    }

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    boolean removeEntryIf(Predicate<? super Entry<K, V>> function);

    boolean removeValueIf(Predicate<? super V> function);

    abstract static class CSLMSpliterator<K, V> {

        public final long estimateSize();
    }

    static final class KeySpliterator<K, V> extends CSLMSpliterator<K, V> implements Spliterator<K> {

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public int characteristics();

        public final Comparator<? super K> getComparator();
    }

    final KeySpliterator<K, V> keySpliterator();

    static final class ValueSpliterator<K, V> extends CSLMSpliterator<K, V> implements Spliterator<V> {

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public int characteristics();
    }

    final ValueSpliterator<K, V> valueSpliterator();

    static final class EntrySpliterator<K, V> extends CSLMSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public int characteristics();

        public final Comparator<Map.Entry<K, V>> getComparator();
    }

    final EntrySpliterator<K, V> entrySpliterator();
}
