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
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmptyIf;
import org.checkerframework.checker.nonempty.qual.NonEmpty;
import org.checkerframework.checker.nullness.qual.EnsuresKeyFor;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.KeyFor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;
import jdk.internal.misc.Unsafe;

@AnnotatedFor({ "nullness" })
public class ConcurrentHashMap<K extends @NonNull Object, V extends @NonNull Object> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {

    static class Node<K, V> implements Map.Entry<K, V> {

        public final K getKey();

        public final V getValue();

        public final int hashCode();

        public final String toString();

        public final V setValue(V value);

        public final boolean equals(Object o);

        Node<K, V> find(int h, Object k);
    }

    static final int spread(int h);

    static Class<?> comparableClassFor(Object x);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static int compareComparables(Class<?> kc, Object k, Object x);

    @SuppressWarnings("unchecked")
    static final <K, V> Node<K, V> tabAt(Node<K, V>[] tab, int i);

    static final <K, V> boolean casTabAt(Node<K, V>[] tab, int i, Node<K, V> c, Node<K, V> v);

    static final <K, V> void setTabAt(Node<K, V>[] tab, int i, Node<K, V> v);

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int initialCapacity) {
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor) {
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
    }

    @Pure
    public int size();

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Pure
    @Nullable
    public V get(@UnknownSignedness @GuardSatisfied Object key);

    @EnsuresKeyForIf(expression = { "#1" }, result = true, map = { "this" })
    @Pure
    public boolean containsKey(@GuardSatisfied @UnknownSignedness Object key);

    @Pure
    public boolean containsValue(@GuardSatisfied @UnknownSignedness Object value);

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V put(K key, V value);

    final V putVal(K key, V value, boolean onlyIfAbsent);

    public void putAll(Map<? extends K, ? extends V> m);

    @Nullable
    public V remove(@GuardSatisfied @UnknownSignedness Object key);

    final V replaceNode(Object key, V value, Object cv);

    public void clear();

    @SideEffectFree
    public KeySetView<@KeyFor({ "this" }) K, V> keySet();

    @SideEffectFree
    public Collection<V> values();

    @SideEffectFree
    public Set<Map.Entry<@KeyFor({ "this" }) K, V>> entrySet();

    public int hashCode();

    public String toString();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    static class Segment<K, V> extends ReentrantLock implements Serializable {
    }

    @EnsuresKeyFor(value = { "#1" }, map = { "this" })
    @Nullable
    public V putIfAbsent(K key, V value);

    public boolean remove(@GuardSatisfied @UnknownSignedness Object key, @GuardSatisfied @UnknownSignedness Object value);

    public boolean replace(K key, V oldValue, V newValue);

    @Nullable
    public V replace(K key, V value);

    @Pure
    @PolyNull
    public V getOrDefault(@GuardSatisfied @UnknownSignedness Object key, @PolyNull V defaultValue);

    public void forEach(BiConsumer<? super K, ? super V> action);

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);

    boolean removeEntryIf(Predicate<? super Entry<K, V>> function);

    boolean removeValueIf(Predicate<? super V> function);

    @PolyNull
    public V computeIfAbsent(K key, Function<? super K, ? extends @PolyNull V> mappingFunction);

    @PolyNull
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends @PolyNull V> remappingFunction);

    @PolyNull
    public V compute(K key, BiFunction<? super K, ? super @Nullable V, ? extends @PolyNull V> remappingFunction);

    @PolyNull
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends @PolyNull V> remappingFunction);

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @UnknownSignedness Object value);

    @SideEffectFree
    public Enumeration<@KeyFor({ "this" }) K> keys();

    @SideEffectFree
    public Enumeration<V> elements();

    public long mappingCount();

    public static <K> KeySetView<K, Boolean> newKeySet();

    public static <K> KeySetView<K, Boolean> newKeySet(int initialCapacity);

    public KeySetView<K, V> keySet(V mappedValue);

    static final class ForwardingNode<K, V> extends Node<K, V> {

        Node<K, V> find(int h, Object k);
    }

    static final class ReservationNode<K, V> extends Node<K, V> {

        Node<K, V> find(int h, Object k);
    }

    static final int resizeStamp(int n);

    final Node<K, V>[] helpTransfer(Node<K, V>[] tab, Node<K, V> f);

    @jdk.internal.vm.annotation.Contended
    static final class CounterCell {
    }

    final long sumCount();

    static <K, V> Node<K, V> untreeify(Node<K, V> b);

    static final class TreeNode<K, V> extends Node<K, V> {

        Node<K, V> find(int h, Object k);

        final TreeNode<K, V> findTreeNode(int h, Object k, Class<?> kc);
    }

    static final class TreeBin<K, V> extends Node<K, V> {

        static int tieBreakOrder(Object a, Object b);

        final Node<K, V> find(int h, Object k);

        final TreeNode<K, V> putTreeVal(int h, K k, V v);

        final boolean removeTreeNode(TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p);

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x);

        static <K, V> boolean checkInvariants(TreeNode<K, V> t);
    }

    static final class TableStack<K, V> {
    }

    static class Traverser<K, V> {

        final Node<K, V> advance();
    }

    static class BaseIterator<K, V> extends Traverser<K, V> {

        @Pure
        public final boolean hasNext();

        @Pure
        public final boolean hasMoreElements();

        public final void remove();
    }

    static final class KeyIterator<K, V> extends BaseIterator<K, V> implements Iterator<K>, Enumeration<K> {

        public final K next(@NonEmpty KeyIterator<K, V> this);

        public final K nextElement(@NonEmpty KeyIterator<K, V> this);
    }

    static final class ValueIterator<K, V> extends BaseIterator<K, V> implements Iterator<V>, Enumeration<V> {

        public final V next(@NonEmpty ValueIterator<K, V> this);

        public final V nextElement(@NonEmpty ValueIterator<K, V> this);
    }

    static final class EntryIterator<K, V> extends BaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        public final Map.Entry<K, V> next(@NonEmpty EntryIterator<K, V> this);
    }

    static final class MapEntry<K, V> implements Map.Entry<K, V> {

        public K getKey();

        public V getValue();

        public int hashCode();

        public String toString();

        public boolean equals(Object o);

        public V setValue(V value);
    }

    static final class KeySpliterator<K, V> extends Traverser<K, V> implements Spliterator<K> {

        public KeySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super K> action);

        public boolean tryAdvance(Consumer<? super K> action);

        public long estimateSize();

        public int characteristics();
    }

    static final class ValueSpliterator<K, V> extends Traverser<K, V> implements Spliterator<V> {

        public ValueSpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super V> action);

        public boolean tryAdvance(Consumer<? super V> action);

        public long estimateSize();

        public int characteristics();
    }

    static final class EntrySpliterator<K, V> extends Traverser<K, V> implements Spliterator<Map.Entry<K, V>> {

        public EntrySpliterator<K, V> trySplit();

        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action);

        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action);

        public long estimateSize();

        public int characteristics();
    }

    final int batchFor(long b);

    public void forEach(long parallelismThreshold, BiConsumer<? super K, ? super V> action);

    public <U> void forEach(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> transformer, Consumer<? super U> action);

    public <U> U search(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> searchFunction);

    public <U> U reduce(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceToDouble(long parallelismThreshold, ToDoubleBiFunction<? super K, ? super V> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceToLong(long parallelismThreshold, ToLongBiFunction<? super K, ? super V> transformer, long basis, LongBinaryOperator reducer);

    public int reduceToInt(long parallelismThreshold, ToIntBiFunction<? super K, ? super V> transformer, int basis, IntBinaryOperator reducer);

    public void forEachKey(long parallelismThreshold, Consumer<? super K> action);

    public <U> void forEachKey(long parallelismThreshold, Function<? super K, ? extends U> transformer, Consumer<? super U> action);

    public <U> U searchKeys(long parallelismThreshold, Function<? super K, ? extends U> searchFunction);

    public K reduceKeys(long parallelismThreshold, BiFunction<? super K, ? super K, ? extends K> reducer);

    public <U> U reduceKeys(long parallelismThreshold, Function<? super K, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceKeysToDouble(long parallelismThreshold, ToDoubleFunction<? super K> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceKeysToLong(long parallelismThreshold, ToLongFunction<? super K> transformer, long basis, LongBinaryOperator reducer);

    public int reduceKeysToInt(long parallelismThreshold, ToIntFunction<? super K> transformer, int basis, IntBinaryOperator reducer);

    public void forEachValue(long parallelismThreshold, Consumer<? super V> action);

    public <U> void forEachValue(long parallelismThreshold, Function<? super V, ? extends U> transformer, Consumer<? super U> action);

    public <U> U searchValues(long parallelismThreshold, Function<? super V, ? extends U> searchFunction);

    public V reduceValues(long parallelismThreshold, BiFunction<? super V, ? super V, ? extends V> reducer);

    public <U> U reduceValues(long parallelismThreshold, Function<? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceValuesToDouble(long parallelismThreshold, ToDoubleFunction<? super V> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceValuesToLong(long parallelismThreshold, ToLongFunction<? super V> transformer, long basis, LongBinaryOperator reducer);

    public int reduceValuesToInt(long parallelismThreshold, ToIntFunction<? super V> transformer, int basis, IntBinaryOperator reducer);

    public void forEachEntry(long parallelismThreshold, Consumer<? super Map.Entry<K, V>> action);

    public <U> void forEachEntry(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> transformer, Consumer<? super U> action);

    public <U> U searchEntries(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> searchFunction);

    public Map.Entry<K, V> reduceEntries(long parallelismThreshold, BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer);

    public <U> U reduceEntries(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer);

    public double reduceEntriesToDouble(long parallelismThreshold, ToDoubleFunction<Map.Entry<K, V>> transformer, double basis, DoubleBinaryOperator reducer);

    public long reduceEntriesToLong(long parallelismThreshold, ToLongFunction<Map.Entry<K, V>> transformer, long basis, LongBinaryOperator reducer);

    public int reduceEntriesToInt(long parallelismThreshold, ToIntFunction<Map.Entry<K, V>> transformer, int basis, IntBinaryOperator reducer);

    abstract static sealed class CollectionView<K, V, E> implements Collection<E>, java.io.Serializable permits EntrySetView, KeySetView, ValuesView {

        public ConcurrentHashMap<K, V> getMap();

        public final void clear();

        @Pure
        public final int size();

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public final boolean isEmpty();

        @SideEffectFree
        public abstract Iterator<E> iterator();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public abstract boolean contains(@UnknownSignedness Object o);

        public abstract boolean remove(@UnknownSignedness Object o);

        @SideEffectFree
        @PolyNull
        @PolySigned
        public final Object[] toArray(CollectionView<K, V, @PolyNull @PolySigned E> this);

        @SideEffectFree
        @SuppressWarnings("unchecked")
        @Nullable
        public final <T> T[] toArray(@PolyNull T[] a);

        public final String toString();

        @Pure
        public final boolean containsAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

        public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

        public final boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);
    }

    public static final class KeySetView<K, V> extends CollectionView<K, V, K> implements Set<K>, java.io.Serializable {

        public V getMappedValue();

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @SideEffectFree
        public Iterator<K> iterator();

        @EnsuresNonEmpty("this")
        public boolean add(K e);

        public boolean addAll(Collection<? extends K> c);

        public int hashCode();

        public boolean equals(Object o);

        @SideEffectFree
        public Spliterator<K> spliterator();

        public void forEach(Consumer<? super K> action);
    }

    static final class ValuesView<K, V> extends CollectionView<K, V, V> implements Collection<V>, java.io.Serializable {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public final boolean contains(@UnknownSignedness Object o);

        public final boolean remove(@UnknownSignedness Object o);

        @SideEffectFree
        public final Iterator<V> iterator();

        @EnsuresNonEmpty("this")
        public final boolean add(V e);

        public final boolean addAll(Collection<? extends V> c);

        @Override
        public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

        public boolean removeIf(Predicate<? super V> filter);

        @SideEffectFree
        public Spliterator<V> spliterator();

        public void forEach(Consumer<? super V> action);
    }

    static final class EntrySetView<K, V> extends CollectionView<K, V, Map.Entry<K, V>> implements Set<Map.Entry<K, V>>, java.io.Serializable {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@UnknownSignedness Object o);

        public boolean remove(@UnknownSignedness Object o);

        @SideEffectFree
        public Iterator<Map.Entry<K, V>> iterator();

        @EnsuresNonEmpty("this")
        public boolean add(Entry<K, V> e);

        public boolean addAll(Collection<? extends Entry<K, V>> c);

        public boolean removeIf(Predicate<? super Entry<K, V>> filter);

        public final int hashCode();

        public final boolean equals(Object o);

        @SideEffectFree
        public Spliterator<Map.Entry<K, V>> spliterator();

        public void forEach(Consumer<? super Map.Entry<K, V>> action);
    }

    @SuppressWarnings("serial")
    abstract static class BulkTask<K, V, R> extends CountedCompleter<R> {

        final Node<K, V> advance();
    }

    @SuppressWarnings("serial")
    static final class ForEachKeyTask<K, V> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachValueTask<K, V> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachEntryTask<K, V> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachMappingTask<K, V> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedKeyTask<K, V, U> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedValueTask<K, V, U> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedEntryTask<K, V, U> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ForEachTransformedMappingTask<K, V, U> extends BulkTask<K, V, Void> {

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchKeysTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchValuesTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchEntriesTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class SearchMappingsTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ReduceKeysTask<K, V> extends BulkTask<K, V, K> {

        public final K getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ReduceValuesTask<K, V> extends BulkTask<K, V, V> {

        public final V getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class ReduceEntriesTask<K, V> extends BulkTask<K, V, Map.Entry<K, V>> {

        public final Map.Entry<K, V> getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsTask<K, V, U> extends BulkTask<K, V, U> {

        public final U getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsToDoubleTask<K, V> extends BulkTask<K, V, Double> {

        public final Double getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysToLongTask<K, V> extends BulkTask<K, V, Long> {

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesToLongTask<K, V> extends BulkTask<K, V, Long> {

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesToLongTask<K, V> extends BulkTask<K, V, Long> {

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsToLongTask<K, V> extends BulkTask<K, V, Long> {

        public final Long getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceKeysToIntTask<K, V> extends BulkTask<K, V, Integer> {

        public final Integer getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceValuesToIntTask<K, V> extends BulkTask<K, V, Integer> {

        public final Integer getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceEntriesToIntTask<K, V> extends BulkTask<K, V, Integer> {

        public final Integer getRawResult();

        public final void compute();
    }

    @SuppressWarnings("serial")
    static final class MapReduceMappingsToIntTask<K, V> extends BulkTask<K, V, Integer> {

        public final Integer getRawResult();

        public final void compute();
    }
}
