package net.vidageek.a.f;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class a<T> implements net.vidageek.a.f.a.a<T> {
    private final List<T> a;

    public a(List<T> list) {
        this.a = Collections.unmodifiableList(list);
    }

    public final void add(int i, T t) {
        this.a.add(i, t);
    }

    public final boolean add(T t) {
        return this.a.add(t);
    }

    public final boolean addAll(int i, Collection<? extends T> collection) {
        return this.a.addAll(i, collection);
    }

    public final boolean addAll(Collection<? extends T> collection) {
        return this.a.addAll(collection);
    }

    public final void clear() {
        this.a.clear();
    }

    public final boolean contains(Object obj) {
        return this.a.contains(obj);
    }

    public final boolean containsAll(Collection<?> collection) {
        return this.a.containsAll(collection);
    }

    public final boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    public final T get(int i) {
        return this.a.get(i);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final int indexOf(Object obj) {
        return this.a.indexOf(obj);
    }

    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    public final Iterator<T> iterator() {
        return this.a.iterator();
    }

    public final int lastIndexOf(Object obj) {
        return this.a.lastIndexOf(obj);
    }

    public final ListIterator<T> listIterator() {
        return this.a.listIterator();
    }

    public final ListIterator<T> listIterator(int i) {
        return this.a.listIterator(i);
    }

    public final T remove(int i) {
        return this.a.remove(i);
    }

    public final boolean remove(Object obj) {
        return this.a.remove(obj);
    }

    public final boolean removeAll(Collection<?> collection) {
        return this.a.removeAll(collection);
    }

    public final boolean retainAll(Collection<?> collection) {
        return this.a.retainAll(collection);
    }

    public final T set(int i, T t) {
        return this.a.set(i, t);
    }

    public final int size() {
        return this.a.size();
    }

    public final List<T> subList(int i, int i2) {
        return this.a.subList(i, i2);
    }

    public final Object[] toArray() {
        return this.a.toArray();
    }

    public final <E> E[] toArray(E[] eArr) {
        return this.a.toArray(eArr);
    }

    public final String toString() {
        return this.a.toString();
    }
}
