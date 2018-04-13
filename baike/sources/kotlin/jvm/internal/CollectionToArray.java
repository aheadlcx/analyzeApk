package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionToArray {
    private static final Object[] a = new Object[0];

    public static Object[] toArray(Collection<?> collection) {
        int size = collection.size();
        if (size == 0) {
            return a;
        }
        Object[] objArr = new Object[size];
        Iterator it = collection.iterator();
        for (int i = 0; i < size; i++) {
            if (!it.hasNext()) {
                return Arrays.copyOf(objArr, i);
            }
            objArr[i] = it.next();
        }
        return it.hasNext() ? a(objArr, it) : objArr;
    }

    public static <T, E> T[] toArray(Collection<E> collection, T[] tArr) {
        T[] tArr2;
        int size = collection.size();
        if (tArr.length >= size) {
            tArr2 = tArr;
        } else {
            Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        }
        Iterator it = collection.iterator();
        int i = 0;
        while (i < tArr2.length) {
            if (it.hasNext()) {
                tArr2[i] = it.next();
                i++;
            } else if (tArr != tArr2) {
                return Arrays.copyOf(tArr2, i);
            } else {
                tArr2[i] = null;
                return tArr2;
            }
        }
        return it.hasNext() ? a(tArr2, it) : tArr2;
    }

    private static <T> T[] a(T[] tArr, Iterator<?> it) {
        int length = tArr.length;
        while (it.hasNext()) {
            int i;
            int length2 = tArr.length;
            if (length == length2) {
                i = ((length2 / 2) + 1) * 3;
                if (i <= length2) {
                    if (length2 == Integer.MAX_VALUE) {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                    i = Integer.MAX_VALUE;
                }
                tArr = Arrays.copyOf(tArr, i);
            }
            i = length + 1;
            tArr[length] = it.next();
            length = i;
        }
        return length == tArr.length ? tArr : Arrays.copyOf(tArr, length);
    }

    private CollectionToArray() {
    }
}
