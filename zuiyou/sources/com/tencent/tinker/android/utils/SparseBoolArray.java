package com.tencent.tinker.android.utils;

public class SparseBoolArray implements Cloneable {
    private static final boolean[] EMPTY_BOOL_ARRAY = new boolean[0];
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private int[] mKeys;
    private int mSize;
    private boolean[] mValues;

    public static class KeyNotFoundException extends Exception {
        public KeyNotFoundException(String str) {
            super(str);
        }
    }

    public SparseBoolArray() {
        this(10);
    }

    public SparseBoolArray(int i) {
        if (i == 0) {
            this.mKeys = EMPTY_INT_ARRAY;
            this.mValues = EMPTY_BOOL_ARRAY;
        } else {
            this.mKeys = new int[i];
            this.mValues = new boolean[i];
        }
        this.mSize = 0;
    }

    private static int growSize(int i) {
        return i <= 4 ? 8 : (i >> 1) + i;
    }

    public SparseBoolArray clone() {
        try {
            SparseBoolArray sparseBoolArray = (SparseBoolArray) super.clone();
            try {
                sparseBoolArray.mKeys = (int[]) this.mKeys.clone();
                sparseBoolArray.mValues = (boolean[]) this.mValues.clone();
                return sparseBoolArray;
            } catch (CloneNotSupportedException e) {
                return sparseBoolArray;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public boolean get(int i) throws KeyNotFoundException {
        int binarySearch = binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            return this.mValues[binarySearch];
        }
        throw new KeyNotFoundException("" + i);
    }

    public void delete(int i) {
        int binarySearch = binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            removeAt(binarySearch);
        }
    }

    public void removeAt(int i) {
        System.arraycopy(this.mKeys, i + 1, this.mKeys, i, this.mSize - (i + 1));
        System.arraycopy(this.mValues, i + 1, this.mValues, i, this.mSize - (i + 1));
        this.mSize--;
    }

    public void put(int i, boolean z) {
        int binarySearch = binarySearch(this.mKeys, this.mSize, i);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = z;
            return;
        }
        binarySearch ^= -1;
        this.mKeys = insertElementIntoIntArray(this.mKeys, this.mSize, binarySearch, i);
        this.mValues = insertElementIntoBoolArray(this.mValues, this.mSize, binarySearch, z);
        this.mSize++;
    }

    public int size() {
        return this.mSize;
    }

    public int keyAt(int i) {
        return this.mKeys[i];
    }

    public boolean valueAt(int i) {
        return this.mValues[i];
    }

    public int indexOfKey(int i) {
        return binarySearch(this.mKeys, this.mSize, i);
    }

    public boolean containsKey(int i) {
        return indexOfKey(i) >= 0;
    }

    public int indexOfValue(boolean z) {
        for (int i = 0; i < this.mSize; i++) {
            if (this.mValues[i] == z) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        this.mSize = 0;
    }

    public void append(int i, boolean z) {
        if (this.mSize == 0 || i > this.mKeys[this.mSize - 1]) {
            this.mKeys = appendElementIntoIntArray(this.mKeys, this.mSize, i);
            this.mValues = appendElementIntoBoolArray(this.mValues, this.mSize, z);
            this.mSize++;
            return;
        }
        put(i, z);
    }

    private int binarySearch(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = i - 1;
        while (i3 <= i4) {
            int i5 = (i3 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i3 = i5 + 1;
            } else if (i6 <= i2) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return i3 ^ -1;
    }

    private int[] appendElementIntoIntArray(int[] iArr, int i, int i2) {
        if (i > iArr.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + iArr.length + " currentSize: " + i);
        }
        if (i + 1 > iArr.length) {
            Object obj = new int[growSize(i)];
            System.arraycopy(iArr, 0, obj, 0, i);
            iArr = obj;
        }
        iArr[i] = i2;
        return iArr;
    }

    private boolean[] appendElementIntoBoolArray(boolean[] zArr, int i, boolean z) {
        if (i > zArr.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + zArr.length + " currentSize: " + i);
        }
        if (i + 1 > zArr.length) {
            Object obj = new boolean[growSize(i)];
            System.arraycopy(zArr, 0, obj, 0, i);
            zArr = obj;
        }
        zArr[i] = z;
        return zArr;
    }

    private int[] insertElementIntoIntArray(int[] iArr, int i, int i2, int i3) {
        if (i > iArr.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + iArr.length + " currentSize: " + i);
        } else if (i + 1 <= iArr.length) {
            System.arraycopy(iArr, i2, iArr, i2 + 1, i - i2);
            iArr[i2] = i3;
            return iArr;
        } else {
            Object obj = new int[growSize(i)];
            System.arraycopy(iArr, 0, obj, 0, i2);
            obj[i2] = i3;
            System.arraycopy(iArr, i2, obj, i2 + 1, iArr.length - i2);
            return obj;
        }
    }

    private boolean[] insertElementIntoBoolArray(boolean[] zArr, int i, int i2, boolean z) {
        if (i > zArr.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + zArr.length + " currentSize: " + i);
        } else if (i + 1 <= zArr.length) {
            System.arraycopy(zArr, i2, zArr, i2 + 1, i - i2);
            zArr[i2] = z;
            return zArr;
        } else {
            Object obj = new boolean[growSize(i)];
            System.arraycopy(zArr, 0, obj, 0, i2);
            obj[i2] = z;
            System.arraycopy(zArr, i2, obj, i2 + 1, zArr.length - i2);
            return obj;
        }
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(keyAt(i));
            stringBuilder.append('=');
            stringBuilder.append(valueAt(i));
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
