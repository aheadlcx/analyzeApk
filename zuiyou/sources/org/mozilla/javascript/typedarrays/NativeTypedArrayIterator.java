package org.mozilla.javascript.typedarrays;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class NativeTypedArrayIterator<T> implements ListIterator<T> {
    private int lastPosition = -1;
    private int position;
    private final NativeTypedArrayView<T> view;

    NativeTypedArrayIterator(NativeTypedArrayView<T> nativeTypedArrayView, int i) {
        this.view = nativeTypedArrayView;
        this.position = i;
    }

    public boolean hasNext() {
        return this.position < this.view.length;
    }

    public boolean hasPrevious() {
        return this.position > 0;
    }

    public int nextIndex() {
        return this.position;
    }

    public int previousIndex() {
        return this.position - 1;
    }

    public T next() {
        if (hasNext()) {
            T t = this.view.get(this.position);
            this.lastPosition = this.position;
            this.position++;
            return t;
        }
        throw new NoSuchElementException();
    }

    public T previous() {
        if (hasPrevious()) {
            this.position--;
            this.lastPosition = this.position;
            return this.view.get(this.position);
        }
        throw new NoSuchElementException();
    }

    public void set(T t) {
        if (this.lastPosition < 0) {
            throw new IllegalStateException();
        }
        this.view.js_set(this.lastPosition, t);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void add(T t) {
        throw new UnsupportedOperationException();
    }
}
