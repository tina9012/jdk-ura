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

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nonempty.qual.PolyNonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.common.value.qual.ArrayLen;
import org.checkerframework.common.value.qual.MinLen;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.access.SharedSecrets;

@AnnotatedFor({ "lock", "index", "nonempty", "nullness" })
public class Collections {

    public static <T extends Comparable<? super T>> void sort(List<T> list);

    public static <T> void sort(List<T> list, @Nullable Comparator<? super T> c);

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key);

    @SuppressWarnings("unchecked")
    public static <T> int binarySearch(List<? extends T> list, T key, @Nullable Comparator<? super T> c);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void reverse(@GuardSatisfied List<?> list);

    public static void shuffle(@GuardSatisfied List<?> list);

    public static void shuffle(List<?> list, Random rnd);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void shuffle(@GuardSatisfied List<?> list, RandomGenerator rnd);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void swap(@GuardSatisfied List<?> list, int i, int j);

    public static <T> void fill(@GuardSatisfied List<? super T> list, T obj);

    public static <T> void copy(List<? super T> dest, List<? extends T> src);

    @Pure
    @StaticallyExecutable
    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll);

    @SuppressWarnings({ "unchecked" })
    @Pure
    @StaticallyExecutable
    public static <T> T min(Collection<? extends T> coll, @Nullable Comparator<? super T> comp);

    @Pure
    @StaticallyExecutable
    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll);

    @SuppressWarnings({ "unchecked" })
    @Pure
    @StaticallyExecutable
    public static <T> T max(Collection<? extends T> coll, @Nullable Comparator<? super T> comp);

    public static void rotate(@GuardSatisfied List<?> list, int distance);

    public static <T> boolean replaceAll(List<T> list, @Nullable T oldVal, T newVal);

    @Pure
    @GTENegativeOne
    public static int indexOfSubList(@GuardSatisfied List<?> source, @GuardSatisfied List<?> target);

    @Pure
    @GTENegativeOne
    public static int lastIndexOfSubList(@GuardSatisfied List<?> source, @GuardSatisfied List<?> target);

    @SuppressWarnings("unchecked")
    @SideEffectFree
    @PolyNonEmpty
    public static <T> Collection<T> unmodifiableCollection(@PolyNonEmpty Collection<? extends T> c);

    static class UnmodifiableCollection<E> implements Collection<E>, Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.UnmodifiableCollection<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        public String toString();

        @SideEffectFree
        @PolyNonEmpty
        public Iterator<E> iterator(@PolyNonEmpty UnmodifiableCollection<E> this);

        @EnsuresNonEmpty("this")
        public boolean add(E e);

        public boolean remove(@UnknownSignedness Object o);

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> coll);

        public boolean addAll(Collection<? extends E> coll);

        public boolean removeAll(Collection<? extends @UnknownSignedness Object> coll);

        public boolean retainAll(Collection<? extends @UnknownSignedness Object> coll);

        public void clear();

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @SuppressWarnings("unchecked")
        @Override
        public Spliterator<E> spliterator();

        @SuppressWarnings("unchecked")
        @Override
        public Stream<E> stream();

        @SuppressWarnings("unchecked")
        @Override
        public Stream<E> parallelStream();
    }

    @SuppressWarnings("unchecked")
    public static <T> SequencedCollection<T> unmodifiableSequencedCollection(SequencedCollection<? extends T> c);

    static class UnmodifiableSequencedCollection<E> extends UnmodifiableCollection<E> implements SequencedCollection<E>, Serializable {

        public SequencedCollection<E> reversed();

        public void addFirst(E e);

        public void addLast(E e);

        public E getFirst();

        public E getLast();

        public E removeFirst();

        public E removeLast();
    }

    @SuppressWarnings("unchecked")
    @SideEffectFree
    @PolyNonEmpty
    public static <T> Set<T> unmodifiableSet(@PolyNonEmpty Set<? extends T> s);

    static class UnmodifiableSet<E> extends UnmodifiableCollection<E> implements Set<E>, Serializable {

        public boolean equals(Object o);

        public int hashCode();
    }

    @SuppressWarnings("unchecked")
    public static <T> SequencedSet<T> unmodifiableSequencedSet(SequencedSet<? extends T> s);

    static class UnmodifiableSequencedSet<E> extends UnmodifiableSequencedCollection<E> implements SequencedSet<E>, Serializable {

        public boolean equals(Object o);

        public int hashCode();

        public SequencedSet<E> reversed();
    }

    @PolyNonEmpty
    public static <T> SortedSet<T> unmodifiableSortedSet(@PolyNonEmpty SortedSet<T> s);

    static class UnmodifiableSortedSet<E> extends UnmodifiableSet<E> implements SortedSet<E>, Serializable {

        public Comparator<? super E> comparator();

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);

        public E first();

        public E last();
    }

    @PolyNonEmpty
    public static <T> NavigableSet<T> unmodifiableNavigableSet(@PolyNonEmpty NavigableSet<T> s);

    static class UnmodifiableNavigableSet<E> extends UnmodifiableSortedSet<E> implements NavigableSet<E>, Serializable {

        private static class EmptyNavigableSet<E> extends UnmodifiableNavigableSet<E> implements Serializable {

            @SideEffectFree
            public EmptyNavigableSet() {
            }
        }

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E pollFirst();

        public E pollLast();

        public NavigableSet<E> descendingSet();

        public Iterator<E> descendingIterator();

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);
    }

    @SuppressWarnings("unchecked")
    @PolyNonEmpty
    public static <T> List<T> unmodifiableList(@PolyNonEmpty List<? extends T> list);

    static class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {

        public boolean equals(Object o);

        public int hashCode();

        public E get(int index);

        public E set(int index, E element);

        public void add(int index, E element);

        public E remove(int index);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public boolean addAll(int index, Collection<? extends E> c);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @PolyNonEmpty
        public ListIterator<E> listIterator(@PolyNonEmpty UnmodifiableList<E> this);

        public ListIterator<E> listIterator(final int index);

        public List<E> subList(int fromIndex, int toIndex);
    }

    static class UnmodifiableRandomAccessList<E> extends UnmodifiableList<E> implements RandomAccess {

        public List<E> subList(int fromIndex, int toIndex);
    }

    @SuppressWarnings("unchecked")
    @PolyNonEmpty
    public static <K, V> Map<K, V> unmodifiableMap(@PolyNonEmpty Map<? extends K, ? extends V> m);

    private static class UnmodifiableMap<K, V> implements Map<K, V>, Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(@UnknownSignedness Object key);

        @Pure
        public boolean containsValue(@UnknownSignedness Object val);

        public V get(Object key);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public V put(K key, V value);

        public V remove(Object key);

        public void putAll(Map<? extends K, ? extends V> m);

        public void clear();

        public Set<K> keySet();

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        public Collection<V> values();

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        @Override
        @SuppressWarnings("unchecked")
        @Pure
        public V getOrDefault(Object k, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        @PolyNull
        public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

        @Override
        @PolyNull
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

        static class UnmodifiableEntrySet<K, V> extends UnmodifiableSet<Map.Entry<K, V>> {

            static <K, V> Consumer<Map.Entry<? extends K, ? extends V>> entryConsumer(Consumer<? super Entry<K, V>> action);

            public void forEach(Consumer<? super Entry<K, V>> action);

            static final class UnmodifiableEntrySetSpliterator<K, V> implements Spliterator<Entry<K, V>> {

                @Override
                public boolean tryAdvance(Consumer<? super Entry<K, V>> action);

                @Override
                public void forEachRemaining(Consumer<? super Entry<K, V>> action);

                @Override
                public Spliterator<Entry<K, V>> trySplit();

                @Override
                public long estimateSize();

                @Override
                public long getExactSizeIfKnown();

                @Override
                public int characteristics();

                @Override
                public boolean hasCharacteristics(int characteristics);

                @Override
                public Comparator<? super Entry<K, V>> getComparator();
            }

            @SuppressWarnings("unchecked")
            public Spliterator<Entry<K, V>> spliterator();

            @Override
            public Stream<Entry<K, V>> stream();

            @Override
            public Stream<Entry<K, V>> parallelStream();

            public Iterator<Map.Entry<K, V>> iterator();

            @SuppressWarnings("unchecked")
            public Object[] toArray();

            @SuppressWarnings("unchecked")
            @Nullable
            public <T> T[] toArray(@PolyNull T[] a);

            @EnsuresNonEmptyIf(result = true, expression = "this")
            public boolean contains(@UnknownSignedness Object o);

            @Pure
            public boolean containsAll(Collection<? extends @UnknownSignedness Object> coll);

            public boolean equals(Object o);

            private static class UnmodifiableEntry<K, V> implements Map.Entry<K, V> {

                public K getKey();

                public V getValue();

                public V setValue(V value);

                public int hashCode();

                public boolean equals(Object o);

                public String toString();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> SequencedMap<K, V> unmodifiableSequencedMap(SequencedMap<? extends K, ? extends V> m);

    private static class UnmodifiableSequencedMap<K, V> extends UnmodifiableMap<K, V> implements SequencedMap<K, V>, Serializable {

        public SequencedMap<K, V> reversed();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        public V putFirst(K k, V v);

        public V putLast(K k, V v);
    }

    @SuppressWarnings("unchecked")
    @PolyNonEmpty
    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(@PolyNonEmpty SortedMap<K, ? extends V> m);

    static class UnmodifiableSortedMap<K, V> extends UnmodifiableMap<K, V> implements SortedMap<K, V>, Serializable {

        public Comparator<? super K> comparator();

        @SideEffectFree
        public SortedMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public SortedMap<K, V> headMap(K toKey);

        @SideEffectFree
        public SortedMap<K, V> tailMap(K fromKey);

        public K firstKey();

        public K lastKey();
    }

    @SuppressWarnings("unchecked")
    @PolyNonEmpty
    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(@PolyNonEmpty NavigableMap<K, ? extends V> m);

    static class UnmodifiableNavigableMap<K, V> extends UnmodifiableSortedMap<K, V> implements NavigableMap<K, V>, Serializable {

        private static class EmptyNavigableMap<K, V> extends UnmodifiableNavigableMap<K, V> implements Serializable {

            @Override
            @SideEffectFree
            public NavigableSet<K> navigableKeySet();
        }

        public K lowerKey(K key);

        public K floorKey(K key);

        public K ceilingKey(K key);

        public K higherKey(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> lowerEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> floorEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> ceilingEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> higherEntry(K key);

        @SuppressWarnings("unchecked")
        public Entry<K, V> firstEntry();

        @SuppressWarnings("unchecked")
        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        @SideEffectFree
        public NavigableMap<K, V> descendingMap();

        @SideEffectFree
        public NavigableSet<K> navigableKeySet();

        @SideEffectFree
        public NavigableSet<K> descendingKeySet();

        @SideEffectFree
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);
    }

    public static <T> Collection<T> synchronizedCollection(Collection<T> c);

    static <T> Collection<T> synchronizedCollection(Collection<T> c, Object mutex);

    static class SynchronizedCollection<E> implements Collection<E>, Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.SynchronizedCollection<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        @SideEffectFree
        public Iterator<E> iterator();

        @EnsuresNonEmpty("this")
        public boolean add(E e);

        public boolean remove(@UnknownSignedness Object o);

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> coll);

        public boolean addAll(Collection<? extends E> coll);

        public boolean removeAll(Collection<? extends @UnknownSignedness Object> coll);

        public boolean retainAll(Collection<? extends @UnknownSignedness Object> coll);

        public void clear();

        public String toString();

        @Override
        public void forEach(Consumer<? super E> consumer);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();
    }

    public static <T> Set<T> synchronizedSet(Set<T> s);

    static <T> Set<T> synchronizedSet(Set<T> s, Object mutex);

    static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {

        public boolean equals(Object o);

        public int hashCode();
    }

    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s);

    static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {

        public Comparator<? super E> comparator();

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);

        public E first();

        public E last();
    }

    public static <T> NavigableSet<T> synchronizedNavigableSet(NavigableSet<T> s);

    static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E pollFirst();

        public E pollLast();

        public NavigableSet<E> descendingSet();

        public Iterator<E> descendingIterator();

        public NavigableSet<E> subSet(E fromElement, E toElement);

        public NavigableSet<E> headSet(E toElement);

        public NavigableSet<E> tailSet(E fromElement);

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);
    }

    public static <T> List<T> synchronizedList(List<T> list);

    static <T> List<T> synchronizedList(List<T> list, Object mutex);

    static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {

        public boolean equals(Object o);

        public int hashCode();

        public E get(int index);

        public E set(int index, E element);

        public void add(int index, E element);

        public E remove(int index);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public boolean addAll(int index, Collection<? extends E> c);

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(int index);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);
    }

    static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {

        public List<E> subList(int fromIndex, int toIndex);
    }

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> m);

    private static class SynchronizedMap<K, V> implements Map<K, V>, Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(@UnknownSignedness Object key);

        @Pure
        public boolean containsValue(@UnknownSignedness Object value);

        public V get(Object key);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public V put(K key, V value);

        public V remove(Object key);

        public void putAll(Map<? extends K, ? extends V> map);

        public void clear();

        public Set<K> keySet();

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        public Collection<V> values();

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        @Override
        @Pure
        public V getOrDefault(Object k, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        @PolyNull
        public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

        @Override
        @PolyNull
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m);

    static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {

        public Comparator<? super K> comparator();

        @SideEffectFree
        public SortedMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public SortedMap<K, V> headMap(K toKey);

        @SideEffectFree
        public SortedMap<K, V> tailMap(K fromKey);

        public K firstKey();

        public K lastKey();
    }

    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> m);

    static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {

        public Entry<K, V> lowerEntry(K key);

        public K lowerKey(K key);

        public Entry<K, V> floorEntry(K key);

        public K floorKey(K key);

        public Entry<K, V> ceilingEntry(K key);

        public K ceilingKey(K key);

        public Entry<K, V> higherEntry(K key);

        public K higherKey(K key);

        public Entry<K, V> firstEntry();

        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        @SideEffectFree
        public NavigableMap<K, V> descendingMap();

        public NavigableSet<K> keySet();

        @SideEffectFree
        public NavigableSet<K> navigableKeySet();

        @SideEffectFree
        public NavigableSet<K> descendingKeySet();

        @SideEffectFree
        public SortedMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public SortedMap<K, V> headMap(K toKey);

        @SideEffectFree
        public SortedMap<K, V> tailMap(K fromKey);

        @SideEffectFree
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);
    }

    public static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type);

    @SuppressWarnings("unchecked")
    static <T> T[] zeroLengthArray(Class<T> type);

    static class CheckedCollection<E> implements Collection<E>, Serializable {

        @SuppressWarnings("unchecked")
        E typeCheck(Object o);

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.CheckedCollection<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        public String toString();

        public boolean remove(@UnknownSignedness Object o);

        public void clear();

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> coll);

        public boolean removeAll(Collection<? extends @UnknownSignedness Object> coll);

        public boolean retainAll(Collection<? extends @UnknownSignedness Object> coll);

        @SideEffectFree
        public Iterator<E> iterator();

        @EnsuresNonEmpty("this")
        public boolean add(E e);

        @SuppressWarnings("unchecked")
        Collection<E> checkedCopyOf(Collection<? extends E> coll);

        public boolean addAll(Collection<? extends E> coll);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();
    }

    public static <E> Queue<E> checkedQueue(Queue<E> queue, Class<E> type);

    static class CheckedQueue<E> extends CheckedCollection<E> implements Queue<E>, Serializable {

        public E element();

        @Pure
        public boolean equals(Object o);

        @Pure
        public int hashCode();

        @Pure
        public E peek();

        public E poll();

        public E remove();

        public boolean offer(E e);
    }

    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type);

    static class CheckedSet<E> extends CheckedCollection<E> implements Set<E>, Serializable {

        public boolean equals(Object o);

        public int hashCode();
    }

    public static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type);

    static class CheckedSortedSet<E> extends CheckedSet<E> implements SortedSet<E>, Serializable {

        public Comparator<? super E> comparator();

        public E first();

        public E last();

        public SortedSet<E> subSet(E fromElement, E toElement);

        public SortedSet<E> headSet(E toElement);

        public SortedSet<E> tailSet(E fromElement);
    }

    public static <E> NavigableSet<E> checkedNavigableSet(NavigableSet<E> s, Class<E> type);

    static class CheckedNavigableSet<E> extends CheckedSortedSet<E> implements NavigableSet<E>, Serializable {

        public E lower(E e);

        public E floor(E e);

        public E ceiling(E e);

        public E higher(E e);

        public E pollFirst();

        public E pollLast();

        public NavigableSet<E> descendingSet();

        public Iterator<E> descendingIterator();

        public NavigableSet<E> subSet(E fromElement, E toElement);

        public NavigableSet<E> headSet(E toElement);

        public NavigableSet<E> tailSet(E fromElement);

        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive);

        public NavigableSet<E> headSet(E toElement, boolean inclusive);

        public NavigableSet<E> tailSet(E fromElement, boolean inclusive);
    }

    public static <E> List<E> checkedList(List<E> list, Class<E> type);

    static class CheckedList<E> extends CheckedCollection<E> implements List<E> {

        public boolean equals(Object o);

        public int hashCode();

        public E get(int index);

        public E remove(int index);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public E set(int index, E element);

        public void add(int index, E element);

        public boolean addAll(int index, Collection<? extends E> c);

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(final int index);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);
    }

    static class CheckedRandomAccessList<E> extends CheckedList<E> implements RandomAccess {

        public List<E> subList(int fromIndex, int toIndex);
    }

    public static <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType);

    private static class CheckedMap<K, V> implements Map<K, V>, Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(@UnknownSignedness Object key);

        @Pure
        public boolean containsValue(@UnknownSignedness Object v);

        public V get(Object key);

        public V remove(Object key);

        public void clear();

        public Set<K> keySet();

        public Collection<V> values();

        public boolean equals(Object o);

        public int hashCode();

        public String toString();

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        public V put(K key, V value);

        @SuppressWarnings("unchecked")
        public void putAll(Map<? extends K, ? extends V> t);

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        @PolyNull
        public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

        @Override
        @PolyNull
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

        static class CheckedEntrySet<K, V> implements Set<Map.Entry<K, V>> {

            @Pure
            public int size();

            @Pure
            @EnsuresNonEmptyIf(result = false, expression = "this")
            public boolean isEmpty();

            public String toString();

            public int hashCode();

            public void clear();

            @EnsuresNonEmpty("this")
            public boolean add(Map.Entry<K, V> e);

            public boolean addAll(Collection<? extends Map.Entry<K, V>> coll);

            public Iterator<Map.Entry<K, V>> iterator();

            @SuppressWarnings("unchecked")
            public Object[] toArray();

            @SuppressWarnings("unchecked")
            @Nullable
            public <T> T[] toArray(@PolyNull T[] a);

            @Pure
            @EnsuresNonEmptyIf(result = true, expression = "this")
            public boolean contains(@UnknownSignedness Object o);

            @Pure
            public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

            public boolean remove(@UnknownSignedness Object o);

            public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

            public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

            public boolean equals(Object o);

            static <K, V, T> CheckedEntry<K, V, T> checkedEntry(Map.Entry<K, V> e, Class<T> valueType);

            private static class CheckedEntry<K, V, T> implements Map.Entry<K, V> {

                public K getKey();

                public V getValue();

                public int hashCode();

                public String toString();

                public V setValue(V value);

                public boolean equals(Object o);
            }
        }
    }

    public static <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType);

    static class CheckedSortedMap<K, V> extends CheckedMap<K, V> implements SortedMap<K, V>, Serializable {

        public Comparator<? super K> comparator();

        public K firstKey();

        public K lastKey();

        @SideEffectFree
        public SortedMap<K, V> subMap(K fromKey, K toKey);

        @SideEffectFree
        public SortedMap<K, V> headMap(K toKey);

        @SideEffectFree
        public SortedMap<K, V> tailMap(K fromKey);
    }

    public static <K, V> NavigableMap<K, V> checkedNavigableMap(NavigableMap<K, V> m, Class<K> keyType, Class<V> valueType);

    static class CheckedNavigableMap<K, V> extends CheckedSortedMap<K, V> implements NavigableMap<K, V>, Serializable {

        public Comparator<? super K> comparator();

        public K firstKey();

        public K lastKey();

        public Entry<K, V> lowerEntry(K key);

        public K lowerKey(K key);

        public Entry<K, V> floorEntry(K key);

        public K floorKey(K key);

        public Entry<K, V> ceilingEntry(K key);

        public K ceilingKey(K key);

        public Entry<K, V> higherEntry(K key);

        public K higherKey(K key);

        public Entry<K, V> firstEntry();

        public Entry<K, V> lastEntry();

        public Entry<K, V> pollFirstEntry();

        public Entry<K, V> pollLastEntry();

        @SideEffectFree
        public NavigableMap<K, V> descendingMap();

        public NavigableSet<K> keySet();

        @SideEffectFree
        public NavigableSet<K> navigableKeySet();

        @SideEffectFree
        public NavigableSet<K> descendingKeySet();

        @Override
        @SideEffectFree
        public NavigableMap<K, V> subMap(K fromKey, K toKey);

        @Override
        @SideEffectFree
        public NavigableMap<K, V> headMap(K toKey);

        @Override
        @SideEffectFree
        public NavigableMap<K, V> tailMap(K fromKey);

        @SideEffectFree
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @SideEffectFree
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive);

        @SideEffectFree
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive);
    }

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static <T> Iterator<T> emptyIterator();

    private static class EmptyIterator<E> implements Iterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty EmptyIterator<E> this);

        public void remove(@NonEmpty EmptyIterator<E> this);

        @Override
        public void forEachRemaining(Consumer<? super E> action);
    }

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static <T> ListIterator<T> emptyListIterator();

    private static class EmptyListIterator<E> extends EmptyIterator<E> implements ListIterator<E> {

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void set(E e);

        public void add(E e);
    }

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static <T> Enumeration<T> emptyEnumeration();

    private static class EmptyEnumeration<E> implements Enumeration<E> {

        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasMoreElements();

        public E nextElement(@NonEmpty EmptyEnumeration<E> this);

        public Iterator<E> asIterator();
    }

    @SuppressWarnings("rawtypes")
    public static final Set EMPTY_SET;

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static final <T> Set<T> emptySet();

    private static class EmptySet<E> extends AbstractSet<E> implements Serializable {

        @SideEffectFree
        public Iterator<E> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        public void clear();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object obj);

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.EmptySet<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public int hashCode();
    }

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static <E> SortedSet<E> emptySortedSet();

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static <E> NavigableSet<E> emptyNavigableSet();

    @SuppressWarnings("rawtypes")
    public static final List EMPTY_LIST;

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static final <T> List<T> emptyList();

    private static class EmptyList<E> extends AbstractList<E> implements RandomAccess, Serializable {

        @SideEffectFree
        public Iterator<E> iterator();

        public ListIterator<E> listIterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        public void clear();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object obj);

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.EmptyList<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public E get(int index);

        public boolean equals(Object o);

        public int hashCode();

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @Override
        public void forEach(Consumer<? super E> action);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();
    }

    @SuppressWarnings("rawtypes")
    public static final Map EMPTY_MAP;

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static final <K, V> Map<K, V> emptyMap();

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static final <K, V> SortedMap<K, V> emptySortedMap();

    @SuppressWarnings("unchecked")
    @SideEffectFree
    public static final <K, V> NavigableMap<K, V> emptyNavigableMap();

    private static class EmptyMap<K, V> extends AbstractMap<K, V> implements Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        public void clear();

        @Pure
        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(@UnknownSignedness Object key);

        @Pure
        public boolean containsValue(@UnknownSignedness Object value);

        public V get(Object key);

        public Set<K> keySet();

        public Collection<V> values();

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        public boolean equals(Object o);

        public int hashCode();

        @Override
        @Pure
        public V getOrDefault(Object k, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        @PolyNull
        public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

        @Override
        @PolyNull
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);
    }

    public static <T> Set<T> singleton(T o);

    static <E> Iterator<E> singletonIterator(final E e);

    static <T> Spliterator<T> singletonSpliterator(final T element);

    private static class SingletonSet<E> extends AbstractSet<E> implements Serializable {

        @SideEffectFree
        public Iterator<E> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        @Override
        public void forEach(Consumer<? super E> action);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public int hashCode();
    }

    @ArrayLen(1)
    public static <T> List<T> singletonList(T o);

    @ArrayLen(1)
    private static class SingletonList<E> extends AbstractList<E> implements RandomAccess, Serializable {

        @SideEffectFree
        public Iterator<E> iterator();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object obj);

        public E get(int index);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @Override
        public void replaceAll(UnaryOperator<E> operator);

        @Override
        public void sort(Comparator<? super E> c);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public int hashCode();
    }

    public static <K, V> Map<K, V> singletonMap(K key, V value);

    private static class SingletonMap<K, V> extends AbstractMap<K, V> implements Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
        public boolean containsKey(@UnknownSignedness Object key);

        @Pure
        public boolean containsValue(@UnknownSignedness Object value);

        public V get(Object key);

        public Set<K> keySet();

        @SideEffectFree
        public Set<Map.Entry<K, V>> entrySet();

        public Collection<V> values();

        @Override
        @Pure
        public V getOrDefault(Object key, V defaultValue);

        @Override
        public void forEach(BiConsumer<? super K, ? super V> action);

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

        @EnsuresKeyFor(value = { "#1" }, map = { "this" })
        @Override
        public V putIfAbsent(K key, V value);

        @Override
        public boolean remove(@UnknownSignedness Object key, @UnknownSignedness Object value);

        @Override
        public boolean replace(K key, V oldValue, V newValue);

        @Override
        public V replace(K key, V value);

        @Override
        @PolyNull
        public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

        @Override
        @PolyNull
        public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V compute(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

        @Override
        @PolyNull
        public V merge(K key, @NonNull V value, BiFunction<? super @NonNull V, ? super @NonNull V, ? extends @PolyNull V> remappingFunction);

        @Override
        public int hashCode();
    }

    public static <T> List<T> nCopies(@NonNegative int n, T o);

    private static class CopiesList<E> extends AbstractList<E> implements RandomAccess, Serializable {

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object obj);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public E get(int index);

        @Override
        public void forEach(Consumer<? super E> action);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.CopiesList<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public List<E> subList(int fromIndex, int toIndex);

        @Override
        public int hashCode();

        @Override
        public boolean equals(Object o);

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();
    }

    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> reverseOrder();

    private static class ReverseComparator implements Comparator<Comparable<Object>>, Serializable {

        public int compare(Comparable<Object> c1, Comparable<Object> c2);

        @Override
        public Comparator<Comparable<Object>> reversed();
    }

    @SuppressWarnings("unchecked")
    public static <T> Comparator<T> reverseOrder(@Nullable Comparator<T> cmp);

    private static class ReverseComparator2<T> implements Comparator<T>, Serializable {

        public int compare(T t1, T t2);

        public boolean equals(Object o);

        public int hashCode();

        @Override
        public Comparator<T> reversed();
    }

    public static <T> Enumeration<T> enumeration(final Collection<T> c);

    public static <T> ArrayList<T> list(Enumeration<T> e);

    static boolean eq(Object o1, Object o2);

    @NonNegative
    public static int frequency(Collection<?> c, @Nullable Object o);

    public static boolean disjoint(Collection<?> c1, Collection<?> c2);

    @SafeVarargs
    public static <T> boolean addAll(@GuardSatisfied Collection<? super T> c, T... elements);

    @SideEffectFree
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map);

    private static class SetFromMap<E> extends AbstractSet<E> implements Set<E>, Serializable {

        public void clear();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @EnsuresNonEmpty("this")
        public boolean add(E e);

        @SideEffectFree
        public Iterator<E> iterator();

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.SetFromMap<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public String toString();

        public int hashCode();

        public boolean equals(Object o);

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

        public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

        public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();
    }

    public static <E> SequencedSet<E> newSequencedSetFromMap(SequencedMap<E, Boolean> map);

    private static class SequencedSetFromMap<E> extends SetFromMap<E> implements SequencedSet<E> {

        public SequencedSet<E> reversed();

        public void addFirst(E e);

        public void addLast(E e);

        public E getFirst();

        public E getLast();

        public E removeFirst();

        public E removeLast();
    }

    public static <T> Queue<T> asLifoQueue(Deque<T> deque);

    static class AsLIFOQueue<E> extends AbstractQueue<E> implements Queue<E>, Serializable {

        @EnsuresNonEmpty("this")
        public boolean add(E e);

        public boolean offer(E e);

        public E poll();

        public E remove();

        @Pure
        public E peek();

        public E element();

        public void clear();

        @Pure
        @NonNegative
        public int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @SideEffectFree
        public Iterator<E> iterator();

        @SideEffectFree
        @PolyNull
        @PolySigned
        public Object[] toArray(Collections.AsLIFOQueue<@PolyNull @PolySigned E> this);

        @SideEffectFree
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public <T> T[] toArray(IntFunction<T[]> f);

        public String toString();

        @Pure
        public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

        public boolean removeAll(Collection<? extends @UnknownSignedness Object> c);

        public boolean retainAll(Collection<? extends @UnknownSignedness Object> c);

        @Override
        public void forEach(Consumer<? super E> action);

        @Override
        public boolean removeIf(Predicate<? super E> filter);

        @SideEffectFree
        @Override
        public Spliterator<E> spliterator();

        @Override
        public Stream<E> stream();

        @Override
        public Stream<E> parallelStream();
    }
}
