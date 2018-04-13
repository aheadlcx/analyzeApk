package io.reactivex.internal.util;

import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class MergerBiFunction<T> implements BiFunction<List<T>, List<T>, List<T>> {
    final Comparator<? super T> comparator;

    public MergerBiFunction(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public List<T> apply(List<T> list, List<T> list2) throws Exception {
        int size = list.size() + list2.size();
        if (size == 0) {
            return new ArrayList();
        }
        Object next;
        List<T> arrayList = new ArrayList(size);
        Iterator it = list.iterator();
        Iterator it2 = list2.iterator();
        Object next2 = it.hasNext() ? it.next() : null;
        if (it2.hasNext()) {
            next = it2.next();
        } else {
            next = null;
        }
        Object obj = next2;
        while (obj != null && next != null) {
            if (this.comparator.compare(obj, next) < 0) {
                arrayList.add(obj);
                if (it.hasNext()) {
                    next2 = it.next();
                } else {
                    next2 = null;
                }
                obj = next2;
            } else {
                arrayList.add(next);
                next = it2.hasNext() ? it2.next() : null;
            }
        }
        if (obj != null) {
            arrayList.add(obj);
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        } else if (next != null) {
            arrayList.add(next);
            while (it2.hasNext()) {
                arrayList.add(it2.next());
            }
        }
        return arrayList;
    }
}
