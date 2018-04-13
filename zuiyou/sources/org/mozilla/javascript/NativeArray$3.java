package org.mozilla.javascript;

import java.util.ListIterator;
import java.util.NoSuchElementException;

class NativeArray$3 implements ListIterator {
    int cursor = this.val$start;
    final /* synthetic */ NativeArray this$0;
    final /* synthetic */ int val$len;
    final /* synthetic */ int val$start;

    NativeArray$3(NativeArray nativeArray, int i, int i2) {
        this.this$0 = nativeArray;
        this.val$start = i;
        this.val$len = i2;
    }

    public boolean hasNext() {
        return this.cursor < this.val$len;
    }

    public Object next() {
        if (this.cursor == this.val$len) {
            throw new NoSuchElementException();
        }
        NativeArray nativeArray = this.this$0;
        int i = this.cursor;
        this.cursor = i + 1;
        return nativeArray.get(i);
    }

    public boolean hasPrevious() {
        return this.cursor > 0;
    }

    public Object previous() {
        if (this.cursor == 0) {
            throw new NoSuchElementException();
        }
        NativeArray nativeArray = this.this$0;
        int i = this.cursor - 1;
        this.cursor = i;
        return nativeArray.get(i);
    }

    public int nextIndex() {
        return this.cursor;
    }

    public int previousIndex() {
        return this.cursor - 1;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
