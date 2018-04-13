package com.nostra13.universalimageloader.core.assist.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

abstract class LinkedBlockingDeque$a implements Iterator<E> {
    LinkedBlockingDeque$d<E> a;
    E b;
    final /* synthetic */ LinkedBlockingDeque c;
    private LinkedBlockingDeque$d<E> d;

    abstract LinkedBlockingDeque$d<E> a();

    abstract LinkedBlockingDeque$d<E> a(LinkedBlockingDeque$d<E> linkedBlockingDeque$d);

    LinkedBlockingDeque$a(LinkedBlockingDeque linkedBlockingDeque) {
        this.c = linkedBlockingDeque;
        ReentrantLock reentrantLock = linkedBlockingDeque.lock;
        reentrantLock.lock();
        try {
            this.a = a();
            this.b = this.a == null ? null : this.a.a;
        } finally {
            reentrantLock.unlock();
        }
    }

    private LinkedBlockingDeque$d<E> b(LinkedBlockingDeque$d<E> linkedBlockingDeque$d) {
        while (true) {
            LinkedBlockingDeque$d<E> a = a(linkedBlockingDeque$d);
            if (a == null) {
                return null;
            }
            if (a.a != null) {
                return a;
            }
            if (a == linkedBlockingDeque$d) {
                return a();
            }
            linkedBlockingDeque$d = a;
        }
    }

    void b() {
        ReentrantLock reentrantLock = this.c.lock;
        reentrantLock.lock();
        try {
            this.a = b(this.a);
            this.b = this.a == null ? null : this.a.a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean hasNext() {
        return this.a != null;
    }

    public E next() {
        if (this.a == null) {
            throw new NoSuchElementException();
        }
        this.d = this.a;
        E e = this.b;
        b();
        return e;
    }

    public void remove() {
        LinkedBlockingDeque$d linkedBlockingDeque$d = this.d;
        if (linkedBlockingDeque$d == null) {
            throw new IllegalStateException();
        }
        this.d = null;
        ReentrantLock reentrantLock = this.c.lock;
        reentrantLock.lock();
        try {
            if (linkedBlockingDeque$d.a != null) {
                this.c.unlink(linkedBlockingDeque$d);
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }
}
