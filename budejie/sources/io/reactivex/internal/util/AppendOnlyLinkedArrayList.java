package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Predicate;
import org.a.c;

public class AppendOnlyLinkedArrayList<T> {
    final int capacity;
    final Object[] head;
    int offset;
    Object[] tail = this.head;

    public interface NonThrowingPredicate<T> extends Predicate<T> {
        boolean test(T t);
    }

    public AppendOnlyLinkedArrayList(int i) {
        this.capacity = i;
        this.head = new Object[(i + 1)];
    }

    public void add(T t) {
        int i = this.capacity;
        int i2 = this.offset;
        if (i2 == i) {
            Object[] objArr = new Object[(i + 1)];
            this.tail[i] = objArr;
            this.tail = objArr;
            i2 = 0;
        }
        this.tail[i2] = t;
        this.offset = i2 + 1;
    }

    public void setFirst(T t) {
        this.head[0] = t;
    }

    public void forEachWhile(NonThrowingPredicate<? super T> nonThrowingPredicate) {
        Object[] objArr = this.head;
        int i = this.capacity;
        for (Object[] objArr2 = objArr; objArr2 != null; objArr2 = (Object[]) objArr2[i]) {
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr2[i2];
                if (obj == null || nonThrowingPredicate.test(obj)) {
                    break;
                }
            }
        }
    }

    public <U> boolean accept(c<? super U> cVar) {
        Object[] objArr = this.head;
        int i = this.capacity;
        for (Object[] objArr2 = objArr; objArr2 != null; objArr2 = (Object[]) objArr2[i]) {
            int i2 = 0;
            while (i2 < i) {
                Object obj = objArr2[i2];
                if (obj == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull(obj, (c) cVar)) {
                    return true;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public <U> boolean accept(Observer<? super U> observer) {
        Object[] objArr = this.head;
        int i = this.capacity;
        for (Object[] objArr2 = objArr; objArr2 != null; objArr2 = (Object[]) objArr2[i]) {
            int i2 = 0;
            while (i2 < i) {
                Object obj = objArr2[i2];
                if (obj == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull(obj, (Observer) observer)) {
                    return true;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public <S> void forEachWhile(S s, BiPredicate<? super S, ? super T> biPredicate) throws Exception {
        Object[] objArr = this.head;
        int i = this.capacity;
        while (true) {
            int i2 = 0;
            while (i2 < i) {
                Object obj = objArr[i2];
                if (obj != null && !biPredicate.test(s, obj)) {
                    i2++;
                } else {
                    return;
                }
            }
            objArr = (Object[]) objArr[i];
        }
    }
}
