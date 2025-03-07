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
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.checker.signedness.qual.UnknownSignedness;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.dataflow.qual.SideEffectsOnly;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import jdk.internal.access.SharedSecrets;
import jdk.internal.util.ArraysSupport;

public class CopyOnWriteArrayList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    final Object[] getArray();

    final void setArray(Object[] a);

    public CopyOnWriteArrayList() {
    }

    public CopyOnWriteArrayList(Collection<? extends E> c) {
    }

    public CopyOnWriteArrayList(E[] toCopyIn) {
    }

    @Pure
    public int size();

    @Pure
    @EnsuresNonEmptyIf(result = false, expression = "this")
    public boolean isEmpty();

    @Pure
    @EnsuresNonEmptyIf(result = true, expression = "this")
    public boolean contains(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public int indexOf(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public int indexOf(E e, int index);

    public int lastIndexOf(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    public int lastIndexOf(E e, int index);

    public Object clone();

    @PolyNull
    @PolySigned
    public Object[] toArray(CopyOnWriteArrayList<@PolyNull @PolySigned E> this);

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T[] toArray(@PolyNull T[] a);

    @SuppressWarnings("unchecked")
    static <E> E elementAt(Object[] a, int index);

    static String outOfBounds(int index, int size);

    public E get(int index);

    public E getFirst();

    public E getLast();

    public E set(int index, E element);

    @EnsuresNonEmpty("this")
    public boolean add(E e);

    public void add(int index, E element);

    public void addFirst(E e);

    public void addLast(E e);

    public E remove(int index);

    public E removeFirst();

    public E removeLast();

    public boolean remove(@GuardSatisfied @Nullable @UnknownSignedness Object o);

    void removeRange(int fromIndex, int toIndex);

    public boolean addIfAbsent(E e);

    @Pure
    public boolean containsAll(Collection<? extends @UnknownSignedness Object> c);

    public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

    public int addAllAbsent(Collection<? extends E> c);

    public void clear();

    public boolean addAll(Collection<? extends E> c);

    public boolean addAll(int index, Collection<? extends E> c);

    public void forEach(Consumer<? super E> action);

    public boolean removeIf(Predicate<? super E> filter);

    boolean bulkRemove(Predicate<? super E> filter, int i, int end);

    public void replaceAll(UnaryOperator<E> operator);

    void replaceAllRange(UnaryOperator<E> operator, int i, int end);

    public void sort(@Nullable Comparator<? super E> c);

    @SuppressWarnings("unchecked")
    void sortRange(Comparator<? super E> c, int i, int end);

    public String toString();

    @Pure
    @EnsuresNonNullIf(expression = "#1", result = true)
    public boolean equals(@Nullable Object o);

    public int hashCode();

    public Iterator<E> iterator();

    public ListIterator<E> listIterator();

    public ListIterator<E> listIterator(int index);

    public Spliterator<E> spliterator();

    static final class COWIterator<E> implements ListIterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @Pure
        public boolean hasPrevious();

        @SuppressWarnings("unchecked")
        @SideEffectsOnly("this")
        public E next(@NonEmpty COWIterator<E> this);

        @SuppressWarnings("unchecked")
        @SideEffectsOnly("this")
        public E previous();

        @Pure
        public int nextIndex();

        @Pure
        public int previousIndex();

        public void remove();

        public void set(E e);

        public void add(E e);

        @Override
        public void forEachRemaining(Consumer<? super E> action);
    }

    public List<E> subList(int fromIndex, int toIndex);

    private class COWSubList implements List<E>, RandomAccess {

        public Object[] toArray();

        @SuppressWarnings("unchecked")
        @Nullable
        public <T> T[] toArray(@PolyNull T[] a);

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean contains(@Nullable @UnknownSignedness Object o);

        @Pure
        public boolean containsAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

        @Pure
        @EnsuresNonEmptyIf(result = false, expression = "this")
        public boolean isEmpty();

        public String toString();

        public int hashCode();

        public boolean equals(Object o);

        public E set(int index, E element);

        public E get(int index);

        public E getFirst();

        public E getLast();

        @Pure
        public int size();

        @EnsuresNonEmpty("this")
        public boolean add(E element);

        public void add(int index, E element);

        public void addFirst(E e);

        public void addLast(E e);

        public boolean addAll(Collection<? extends E> c);

        public boolean addAll(int index, Collection<? extends E> c);

        public void clear();

        public E remove(int index);

        public E removeFirst();

        public E removeLast();

        public boolean remove(@Nullable @UnknownSignedness Object o);

        public Iterator<E> iterator();

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(int index);

        public List<E> subList(int fromIndex, int toIndex);

        public void forEach(Consumer<? super E> action);

        public void replaceAll(UnaryOperator<E> operator);

        public void sort(Comparator<? super E> c);

        public boolean removeAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

        public boolean retainAll(Collection<? extends @NonNull @UnknownSignedness Object> c);

        public boolean removeIf(Predicate<? super E> filter);

        public Spliterator<E> spliterator();

        public List<E> reversed();
    }

    private static class COWSubListIterator<E> implements ListIterator<E> {

        @Pure
        @EnsuresNonEmptyIf(result = true, expression = "this")
        public boolean hasNext();

        @SideEffectsOnly("this")
        public E next(@NonEmpty COWSubListIterator<E> this);

        public boolean hasPrevious();

        public E previous();

        public int nextIndex();

        public int previousIndex();

        public void remove();

        public void set(E e);

        public void add(E e);

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> action);
    }

    public List<E> reversed();

    private static class Reversed<E> implements List<E>, RandomAccess {

        class DescendingIterator implements Iterator<E> {

            public boolean hasNext();

            public E next();

            public void remove();
        }

        class DescendingListIterator implements ListIterator<E> {

            public boolean hasNext();

            public E next();

            public boolean hasPrevious();

            public E previous();

            public int nextIndex();

            public int previousIndex();

            public void remove();

            public void set(E e);

            public void add(E e);
        }

        public void forEach(Consumer<? super E> action);

        public Iterator<E> iterator();

        public Spliterator<E> spliterator();

        public boolean add(E e);

        public boolean addAll(Collection<? extends E> c);

        public void clear();

        public boolean contains(Object o);

        public boolean containsAll(Collection<?> c);

        public boolean equals(Object o);

        public int hashCode();

        public boolean isEmpty();

        public Stream<E> parallelStream();

        public boolean remove(Object o);

        public boolean removeAll(Collection<?> c);

        public boolean retainAll(Collection<?> c);

        public int size();

        public Stream<E> stream();

        public Object[] toArray();

        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a);

        public <T> T[] toArray(IntFunction<T[]> generator);

        public String toString();

        public void add(int index, E element);

        public void addFirst(E e);

        public void addLast(E e);

        public boolean addAll(int index, Collection<? extends E> c);

        public E get(int i);

        public E getFirst();

        public E getLast();

        public int indexOf(Object o);

        public int lastIndexOf(Object o);

        public ListIterator<E> listIterator();

        public ListIterator<E> listIterator(int index);

        public E remove(int index);

        public E removeFirst();

        public E removeLast();

        public boolean removeIf(Predicate<? super E> filter);

        public void replaceAll(UnaryOperator<E> operator);

        public void sort(Comparator<? super E> c);

        public E set(int index, E element);

        public List<E> subList(int fromIndex, int toIndex);

        public List<E> reversed();
    }
}
