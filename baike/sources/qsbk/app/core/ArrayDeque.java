package qsbk.app.core;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> extends AbstractCollection<E> implements Serializable, Cloneable, Deque<E> {
    static final /* synthetic */ boolean a = (!ArrayDeque.class.desiredAssertionStatus());
    private transient E[] b;
    private transient int c;
    private transient int d;

    private class a implements Iterator<E> {
        final /* synthetic */ ArrayDeque a;
        private int b;
        private int c;
        private int d;

        private a(ArrayDeque arrayDeque) {
            this.a = arrayDeque;
            this.b = this.a.c;
            this.c = this.a.d;
            this.d = -1;
        }

        public boolean hasNext() {
            return this.b != this.c;
        }

        public E next() {
            if (this.b == this.c) {
                throw new NoSuchElementException();
            }
            E e = this.a.b[this.b];
            if (this.a.d != this.c || e == null) {
                throw new ConcurrentModificationException();
            }
            this.d = this.b;
            this.b = (this.b + 1) & (this.a.b.length - 1);
            return e;
        }

        public void remove() {
            if (this.d < 0) {
                throw new IllegalStateException();
            }
            if (this.a.b(this.d)) {
                this.b = (this.b - 1) & (this.a.b.length - 1);
                this.c = this.a.d;
            }
            this.d = -1;
        }
    }

    private class b implements Iterator<E> {
        final /* synthetic */ ArrayDeque a;
        private int b;
        private int c;
        private int d;

        private b(ArrayDeque arrayDeque) {
            this.a = arrayDeque;
            this.b = this.a.d;
            this.c = this.a.c;
            this.d = -1;
        }

        public boolean hasNext() {
            return this.b != this.c;
        }

        public E next() {
            if (this.b == this.c) {
                throw new NoSuchElementException();
            }
            this.b = (this.b - 1) & (this.a.b.length - 1);
            E e = this.a.b[this.b];
            if (this.a.c != this.c || e == null) {
                throw new ConcurrentModificationException();
            }
            this.d = this.b;
            return e;
        }

        public void remove() {
            if (this.d < 0) {
                throw new IllegalStateException();
            }
            if (!this.a.b(this.d)) {
                this.b = (this.b + 1) & (this.a.b.length - 1);
                this.c = this.a.c;
            }
            this.d = -1;
        }
    }

    public ArrayDeque() {
        this.b = new Object[16];
    }

    public ArrayDeque(int i) {
        a(i);
    }

    public ArrayDeque(Collection<? extends E> collection) {
        a(collection.size());
        addAll(collection);
    }

    private void a(int i) {
        int i2 = 8;
        if (i >= 8) {
            i2 = (i >>> 1) | i;
            i2 |= i2 >>> 2;
            i2 |= i2 >>> 4;
            i2 |= i2 >>> 8;
            i2 = (i2 | (i2 >>> 16)) + 1;
            if (i2 < 0) {
                i2 >>>= 1;
            }
        }
        this.b = new Object[i2];
    }

    private void a() {
        if (a || this.c == this.d) {
            int i = this.c;
            int length = this.b.length;
            int i2 = length - i;
            int i3 = length << 1;
            if (i3 < 0) {
                throw new IllegalStateException("Sorry, deque too big");
            }
            Object obj = new Object[i3];
            System.arraycopy(this.b, i, obj, 0, i2);
            System.arraycopy(this.b, 0, obj, i2, i);
            this.b = (Object[]) obj;
            this.c = 0;
            this.d = length;
            return;
        }
        throw new AssertionError();
    }

    private <T> T[] a(T[] tArr) {
        if (this.c < this.d) {
            System.arraycopy(this.b, this.c, tArr, 0, size());
        } else if (this.c > this.d) {
            int length = this.b.length - this.c;
            System.arraycopy(this.b, this.c, tArr, 0, length);
            System.arraycopy(this.b, 0, tArr, length, this.d);
        }
        return tArr;
    }

    public void addFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Object[] objArr = this.b;
        int length = (this.c - 1) & (this.b.length - 1);
        this.c = length;
        objArr[length] = e;
        if (this.c == this.d) {
            a();
        }
    }

    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        this.b[this.d] = e;
        int length = (this.d + 1) & (this.b.length - 1);
        this.d = length;
        if (length == this.c) {
            a();
        }
    }

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    public E removeFirst() {
        E pollFirst = pollFirst();
        if (pollFirst != null) {
            return pollFirst;
        }
        throw new NoSuchElementException();
    }

    public E removeLast() {
        E pollLast = pollLast();
        if (pollLast != null) {
            return pollLast;
        }
        throw new NoSuchElementException();
    }

    public E pollFirst() {
        int i = this.c;
        E e = this.b[i];
        if (e == null) {
            return null;
        }
        this.b[i] = null;
        this.c = (i + 1) & (this.b.length - 1);
        return e;
    }

    public E pollLast() {
        int length = (this.b.length - 1) & (this.d - 1);
        E e = this.b[length];
        if (e == null) {
            return null;
        }
        this.b[length] = null;
        this.d = length;
        return e;
    }

    public E getFirst() {
        E e = this.b[this.c];
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        E e = this.b[(this.d - 1) & (this.b.length - 1)];
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public E peekFirst() {
        return this.b[this.c];
    }

    public E peekLast() {
        return this.b[(this.d - 1) & (this.b.length - 1)];
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.b.length - 1;
        int i = this.c;
        while (true) {
            Object obj2 = this.b[i];
            if (obj2 == null) {
                return false;
            }
            if (obj.equals(obj2)) {
                b(i);
                return true;
            }
            i = (i + 1) & length;
        }
    }

    public boolean removeLastOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.b.length - 1;
        int i = (this.d - 1) & length;
        while (true) {
            Object obj2 = this.b[i];
            if (obj2 == null) {
                return false;
            }
            if (obj.equals(obj2)) {
                b(i);
                return true;
            }
            i = (i - 1) & length;
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public E remove() {
        return removeFirst();
    }

    public E poll() {
        return pollFirst();
    }

    public E element() {
        return getFirst();
    }

    public E peek() {
        return peekFirst();
    }

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        return removeFirst();
    }

    private void b() {
        if (!a && this.b[this.d] != null) {
            throw new AssertionError();
        } else if (!a && (!this.c != this.d ? this.b[this.c] != null : this.b[this.c] == null || this.b[(this.d - 1) & (this.b.length - 1)] == null)) {
            throw new AssertionError();
        } else if (!a && this.b[(this.c - 1) & (this.b.length - 1)] != null) {
            throw new AssertionError();
        }
    }

    private boolean b(int i) {
        b();
        Object obj = this.b;
        int length = obj.length - 1;
        int i2 = this.c;
        int i3 = this.d;
        int i4 = (i - i2) & length;
        int i5 = (i3 - i) & length;
        if (i4 >= ((i3 - i2) & length)) {
            throw new ConcurrentModificationException();
        } else if (i4 < i5) {
            if (i2 <= i) {
                System.arraycopy(obj, i2, obj, i2 + 1, i4);
            } else {
                System.arraycopy(obj, 0, obj, 1, i);
                obj[0] = obj[length];
                System.arraycopy(obj, i2, obj, i2 + 1, length - i2);
            }
            obj[i2] = null;
            this.c = (i2 + 1) & length;
            return false;
        } else {
            if (i < i3) {
                System.arraycopy(obj, i + 1, obj, i, i5);
                this.d = i3 - 1;
            } else {
                System.arraycopy(obj, i + 1, obj, i, length - i);
                obj[length] = obj[0];
                System.arraycopy(obj, 1, obj, 0, i3);
                this.d = (i3 - 1) & length;
            }
            return true;
        }
    }

    public int size() {
        return (this.d - this.c) & (this.b.length - 1);
    }

    public boolean isEmpty() {
        return this.c == this.d;
    }

    public Iterator<E> iterator() {
        return new a();
    }

    public Iterator<E> descendingIterator() {
        return new b();
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        int length = this.b.length - 1;
        int i = this.c;
        while (true) {
            Object obj2 = this.b[i];
            if (obj2 == null) {
                return false;
            }
            if (obj.equals(obj2)) {
                return true;
            }
            i = (i + 1) & length;
        }
    }

    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    public void clear() {
        int i = this.c;
        int i2 = this.d;
        if (i != i2) {
            this.d = 0;
            this.c = 0;
            int length = this.b.length - 1;
            do {
                this.b[i] = null;
                i = (i + 1) & length;
            } while (i != i2);
        }
    }

    public Object[] toArray() {
        return a(new Object[size()]);
    }

    public <T> T[] toArray(T[] tArr) {
        Object[] objArr;
        int size = size();
        if (tArr.length < size) {
            objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else {
            T[] tArr2 = tArr;
        }
        a(objArr);
        if (objArr.length > size) {
            objArr[size] = null;
        }
        return objArr;
    }

    public ArrayDeque<E> clone() {
        try {
            ArrayDeque<E> arrayDeque = (ArrayDeque) super.clone();
            arrayDeque.b = Arrays.copyOf(this.b, this.b.length);
            return arrayDeque;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
