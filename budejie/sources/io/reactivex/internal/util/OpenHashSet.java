package io.reactivex.internal.util;

public final class OpenHashSet<T> {
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public OpenHashSet(int i) {
        this(i, 0.75f);
    }

    public OpenHashSet(int i, float f) {
        this.loadFactor = f;
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        this.mask = roundToPowerOfTwo - 1;
        this.maxSize = (int) (((float) roundToPowerOfTwo) * f);
        this.keys = new Object[roundToPowerOfTwo];
    }

    public boolean add(T t) {
        Object[] objArr = this.keys;
        int i = this.mask;
        int mix = mix(t.hashCode()) & i;
        Object obj = objArr[mix];
        if (obj != null) {
            if (obj.equals(t)) {
                return false;
            }
            do {
                mix = (mix + 1) & i;
                obj = objArr[mix];
                if (obj == null) {
                }
            } while (!obj.equals(t));
            return false;
        }
        objArr[mix] = t;
        mix = this.size + 1;
        this.size = mix;
        if (mix >= this.maxSize) {
            rehash();
        }
        return true;
    }

    public boolean remove(T t) {
        Object[] objArr = this.keys;
        int i = this.mask;
        int mix = mix(t.hashCode()) & i;
        Object obj = objArr[mix];
        if (obj == null) {
            return false;
        }
        if (obj.equals(t)) {
            return removeEntry(mix, objArr, i);
        }
        do {
            mix = (mix + 1) & i;
            obj = objArr[mix];
            if (obj == null) {
                return false;
            }
        } while (!obj.equals(t));
        return removeEntry(mix, objArr, i);
    }

    boolean removeEntry(int i, T[] tArr, int i2) {
        this.size--;
        while (true) {
            Object obj;
            int i3 = (i + 1) & i2;
            while (true) {
                obj = tArr[i3];
                if (obj == null) {
                    tArr[i] = null;
                    return true;
                }
                int mix = mix(obj.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= mix || mix > i3) {
                        break;
                    }
                    i3 = (i3 + 1) & i2;
                } else {
                    if (i >= mix && mix > i3) {
                        break;
                    }
                    i3 = (i3 + 1) & i2;
                }
            }
            tArr[i] = obj;
            i = i3;
        }
    }

    void rehash() {
        Object[] objArr = this.keys;
        int length = objArr.length;
        int i = length << 1;
        int i2 = i - 1;
        Object[] objArr2 = new Object[i];
        int i3 = length;
        length = this.size;
        while (true) {
            int i4 = length - 1;
            if (length != 0) {
                do {
                    i3--;
                } while (objArr[i3] == null);
                length = mix(objArr[i3].hashCode()) & i2;
                if (objArr2[length] != null) {
                    do {
                        length = (length + 1) & i2;
                    } while (objArr2[length] != null);
                }
                objArr2[length] = objArr[i3];
                length = i4;
            } else {
                this.mask = i2;
                this.maxSize = (int) (((float) i) * this.loadFactor);
                this.keys = objArr2;
                return;
            }
        }
    }

    static int mix(int i) {
        int i2 = INT_PHI * i;
        return i2 ^ (i2 >>> 16);
    }

    public Object[] keys() {
        return this.keys;
    }

    public int size() {
        return this.size;
    }
}
