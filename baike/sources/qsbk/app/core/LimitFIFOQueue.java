package qsbk.app.core;

import android.support.annotation.NonNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LimitFIFOQueue<E> implements Queue<E> {
    LinkedList<E> a = new LinkedList();
    private int b;
    private Object c = new Object();

    public LimitFIFOQueue(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("limit must > 0");
        }
        this.b = i;
    }

    public boolean offer(E e) {
        boolean z;
        synchronized (this.c) {
            if (e == null) {
                z = false;
            } else {
                while (this.a.size() >= this.b && this.a.size() > 0) {
                    this.a.removeLast();
                }
                this.a.addFirst(e);
                z = true;
            }
        }
        return z;
    }

    public boolean add(E e) {
        boolean z = true;
        synchronized (this.c) {
            if (e == null) {
                z = false;
            } else if (this.a.contains(e)) {
                this.a.remove(e);
                this.a.addFirst(e);
            } else {
                while (this.a.size() >= this.b && this.a.size() > 0) {
                    this.a.removeLast();
                }
                this.a.addFirst(e);
            }
        }
        return z;
    }

    public boolean addAll(Collection<? extends E> collection) {
        boolean z;
        synchronized (this.c) {
            if (collection != null) {
                if (collection.size() > 0) {
                    Object[] toArray = collection.toArray();
                    for (int length = toArray.length - 1; length >= 0; length--) {
                        add(toArray[length]);
                    }
                    while (this.a.size() > this.b && this.a.size() > 0) {
                        this.a.removeLast();
                    }
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public void clear() {
        synchronized (this.c) {
            this.a.clear();
        }
    }

    public boolean contains(Object obj) {
        boolean contains;
        synchronized (this.c) {
            contains = this.a.contains(obj);
        }
        return contains;
    }

    public boolean containsAll(Collection<?> collection) {
        boolean containsAll;
        synchronized (this.c) {
            containsAll = this.a.containsAll(collection);
        }
        return containsAll;
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.c) {
            isEmpty = this.a.isEmpty();
        }
        return isEmpty;
    }

    @NonNull
    public Iterator<E> iterator() {
        Iterator<E> it;
        synchronized (this.c) {
            it = this.a.iterator();
        }
        return it;
    }

    public boolean remove(Object obj) {
        boolean remove;
        synchronized (this.c) {
            remove = this.a.remove(obj);
        }
        return remove;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean removeAll;
        synchronized (this.c) {
            removeAll = this.a.removeAll(collection);
        }
        return removeAll;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean retainAll;
        synchronized (this.c) {
            retainAll = this.a.retainAll(collection);
        }
        return retainAll;
    }

    public int size() {
        int size;
        synchronized (this.c) {
            size = this.a.size();
        }
        return size;
    }

    @NonNull
    public Object[] toArray() {
        Object[] toArray;
        synchronized (this.c) {
            toArray = this.a.toArray();
        }
        return toArray;
    }

    @NonNull
    public <T> T[] toArray(T[] tArr) {
        T[] toArray;
        synchronized (this.c) {
            toArray = this.a.toArray(tArr);
        }
        return toArray;
    }

    public E remove() {
        E removeLast;
        synchronized (this.c) {
            removeLast = this.a.removeLast();
        }
        return removeLast;
    }

    public E poll() {
        E removeLast;
        synchronized (this.c) {
            removeLast = this.a.removeLast();
        }
        return removeLast;
    }

    public E element() {
        E element;
        synchronized (this.c) {
            element = this.a.element();
        }
        return element;
    }

    public E peek() {
        E peek;
        synchronized (this.c) {
            peek = this.a.peek();
        }
        return peek;
    }

    public E get(int i) {
        E e;
        synchronized (this.c) {
            e = this.a.get(i);
        }
        return e;
    }
}
