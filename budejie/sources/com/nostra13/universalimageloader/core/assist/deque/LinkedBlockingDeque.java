package com.nostra13.universalimageloader.core.assist.deque;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingDeque<E> extends AbstractQueue<E> implements a<E>, Serializable {
    private static final long serialVersionUID = -387911632671998426L;
    private final int capacity;
    private transient int count;
    transient LinkedBlockingDeque$d<E> first;
    transient LinkedBlockingDeque$d<E> last;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int i) {
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = i;
    }

    public LinkedBlockingDeque(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (!linkLast(new LinkedBlockingDeque$d(next))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean linkFirst(LinkedBlockingDeque$d<E> linkedBlockingDeque$d) {
        if (this.count >= this.capacity) {
            return false;
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d2 = this.first;
        linkedBlockingDeque$d.c = linkedBlockingDeque$d2;
        this.first = linkedBlockingDeque$d;
        if (this.last == null) {
            this.last = linkedBlockingDeque$d;
        } else {
            linkedBlockingDeque$d2.b = linkedBlockingDeque$d;
        }
        this.count++;
        this.notEmpty.signal();
        return true;
    }

    private boolean linkLast(LinkedBlockingDeque$d<E> linkedBlockingDeque$d) {
        if (this.count >= this.capacity) {
            return false;
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d2 = this.last;
        linkedBlockingDeque$d.b = linkedBlockingDeque$d2;
        this.last = linkedBlockingDeque$d;
        if (this.first == null) {
            this.first = linkedBlockingDeque$d;
        } else {
            linkedBlockingDeque$d2.c = linkedBlockingDeque$d;
        }
        this.count++;
        this.notEmpty.signal();
        return true;
    }

    private E unlinkFirst() {
        LinkedBlockingDeque$d linkedBlockingDeque$d = this.first;
        if (linkedBlockingDeque$d == null) {
            return null;
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d2 = linkedBlockingDeque$d.c;
        E e = linkedBlockingDeque$d.a;
        linkedBlockingDeque$d.a = null;
        linkedBlockingDeque$d.c = linkedBlockingDeque$d;
        this.first = linkedBlockingDeque$d2;
        if (linkedBlockingDeque$d2 == null) {
            this.last = null;
        } else {
            linkedBlockingDeque$d2.b = null;
        }
        this.count--;
        this.notFull.signal();
        return e;
    }

    private E unlinkLast() {
        LinkedBlockingDeque$d linkedBlockingDeque$d = this.last;
        if (linkedBlockingDeque$d == null) {
            return null;
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d2 = linkedBlockingDeque$d.b;
        E e = linkedBlockingDeque$d.a;
        linkedBlockingDeque$d.a = null;
        linkedBlockingDeque$d.b = linkedBlockingDeque$d;
        this.last = linkedBlockingDeque$d2;
        if (linkedBlockingDeque$d2 == null) {
            this.first = null;
        } else {
            linkedBlockingDeque$d2.c = null;
        }
        this.count--;
        this.notFull.signal();
        return e;
    }

    void unlink(LinkedBlockingDeque$d<E> linkedBlockingDeque$d) {
        LinkedBlockingDeque$d linkedBlockingDeque$d2 = linkedBlockingDeque$d.b;
        LinkedBlockingDeque$d linkedBlockingDeque$d3 = linkedBlockingDeque$d.c;
        if (linkedBlockingDeque$d2 == null) {
            unlinkFirst();
        } else if (linkedBlockingDeque$d3 == null) {
            unlinkLast();
        } else {
            linkedBlockingDeque$d2.c = linkedBlockingDeque$d3;
            linkedBlockingDeque$d3.b = linkedBlockingDeque$d2;
            linkedBlockingDeque$d.a = null;
            this.count--;
            this.notFull.signal();
        }
    }

    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d = new LinkedBlockingDeque$d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean linkFirst = linkFirst(linkedBlockingDeque$d);
            return linkFirst;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d = new LinkedBlockingDeque$d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean linkLast = linkLast(linkedBlockingDeque$d);
            return linkLast;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void putFirst(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d = new LinkedBlockingDeque$d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (!linkFirst(linkedBlockingDeque$d)) {
            try {
                this.notFull.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void putLast(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d = new LinkedBlockingDeque$d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (!linkLast(linkedBlockingDeque$d)) {
            try {
                this.notFull.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean offerFirst(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d = new LinkedBlockingDeque$d(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (!linkFirst(linkedBlockingDeque$d)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.notFull.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public boolean offerLast(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        LinkedBlockingDeque$d linkedBlockingDeque$d = new LinkedBlockingDeque$d(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (!linkLast(linkedBlockingDeque$d)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.notFull.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
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
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E unlinkFirst = unlinkFirst();
            return unlinkFirst;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E unlinkLast = unlinkLast();
            return unlinkLast;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E takeFirst() throws InterruptedException {
        E unlinkFirst;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                unlinkFirst = unlinkFirst();
                if (unlinkFirst != null) {
                    break;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return unlinkFirst;
    }

    public E takeLast() throws InterruptedException {
        E unlinkLast;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                unlinkLast = unlinkLast();
                if (unlinkLast != null) {
                    break;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return unlinkLast;
    }

    public E pollFirst(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E unlinkFirst = unlinkFirst();
                if (unlinkFirst != null) {
                    reentrantLock.unlock();
                    return unlinkFirst;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.notEmpty.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E pollLast(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E unlinkLast = unlinkLast();
                if (unlinkLast != null) {
                    reentrantLock.unlock();
                    return unlinkLast;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.notEmpty.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E getFirst() {
        E peekFirst = peekFirst();
        if (peekFirst != null) {
            return peekFirst;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        E peekLast = peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        throw new NoSuchElementException();
    }

    public E peekFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = this.first == null ? null : this.first.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public E peekLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = this.last == null ? null : this.last.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (LinkedBlockingDeque$d linkedBlockingDeque$d = this.first; linkedBlockingDeque$d != null; linkedBlockingDeque$d = linkedBlockingDeque$d.c) {
                if (obj.equals(linkedBlockingDeque$d.a)) {
                    unlink(linkedBlockingDeque$d);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean removeLastOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (LinkedBlockingDeque$d linkedBlockingDeque$d = this.last; linkedBlockingDeque$d != null; linkedBlockingDeque$d = linkedBlockingDeque$d.b) {
                if (obj.equals(linkedBlockingDeque$d.a)) {
                    unlink(linkedBlockingDeque$d);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public void put(E e) throws InterruptedException {
        putLast(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        return offerLast(e, j, timeUnit);
    }

    public E remove() {
        return removeFirst();
    }

    public E poll() {
        return pollFirst();
    }

    public E take() throws InterruptedException {
        return takeFirst();
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return pollFirst(j, timeUnit);
    }

    public E element() {
        return getFirst();
    }

    public E peek() {
        return peekFirst();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.capacity - this.count;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.count);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.first.a);
                    unlinkFirst();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        return removeFirst();
    }

    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.count;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (LinkedBlockingDeque$d linkedBlockingDeque$d = this.first; linkedBlockingDeque$d != null; linkedBlockingDeque$d = linkedBlockingDeque$d.c) {
                if (obj.equals(linkedBlockingDeque$d.a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.count];
            int i = 0;
            LinkedBlockingDeque$d linkedBlockingDeque$d = this.first;
            while (linkedBlockingDeque$d != null) {
                int i2 = i + 1;
                objArr[i] = linkedBlockingDeque$d.a;
                linkedBlockingDeque$d = linkedBlockingDeque$d.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (tArr.length < this.count) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.count);
            }
            int i = 0;
            LinkedBlockingDeque$d linkedBlockingDeque$d = this.first;
            while (linkedBlockingDeque$d != null) {
                int i2 = i + 1;
                tArr[i] = linkedBlockingDeque$d.a;
                linkedBlockingDeque$d = linkedBlockingDeque$d.c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            String str;
            LinkedBlockingDeque$d linkedBlockingDeque$d = this.first;
            if (linkedBlockingDeque$d == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                LinkedBlockingDeque$d linkedBlockingDeque$d2 = linkedBlockingDeque$d;
                while (true) {
                    Object obj = linkedBlockingDeque$d2.a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    linkedBlockingDeque$d = linkedBlockingDeque$d2.c;
                    if (linkedBlockingDeque$d == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    linkedBlockingDeque$d2 = linkedBlockingDeque$d;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            LinkedBlockingDeque$d linkedBlockingDeque$d = this.first;
            while (linkedBlockingDeque$d != null) {
                linkedBlockingDeque$d.a = null;
                LinkedBlockingDeque$d linkedBlockingDeque$d2 = linkedBlockingDeque$d.c;
                linkedBlockingDeque$d.b = null;
                linkedBlockingDeque$d.c = null;
                linkedBlockingDeque$d = linkedBlockingDeque$d2;
            }
            this.last = null;
            this.first = null;
            this.count = 0;
            this.notFull.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new LinkedBlockingDeque$c(this, null);
    }

    public Iterator<E> descendingIterator() {
        return new LinkedBlockingDeque$b(this, null);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (LinkedBlockingDeque$d linkedBlockingDeque$d = this.first; linkedBlockingDeque$d != null; linkedBlockingDeque$d = linkedBlockingDeque$d.c) {
                objectOutputStream.writeObject(linkedBlockingDeque$d.a);
            }
            objectOutputStream.writeObject(null);
        } finally {
            reentrantLock.unlock();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count = 0;
        this.first = null;
        this.last = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}
