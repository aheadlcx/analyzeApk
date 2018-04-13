package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjToIntMap implements Serializable {
    private static final int A = -1640531527;
    private static final Object DELETED = new Object();
    private static final boolean check = false;
    static final long serialVersionUID = -1542220580748809402L;
    private int keyCount;
    private transient Object[] keys;
    private transient int occupiedCount;
    private int power;
    private transient int[] values;

    public static class Iterator {
        private int cursor;
        private Object[] keys;
        ObjToIntMap master;
        private int remaining;
        private int[] values;

        Iterator(ObjToIntMap objToIntMap) {
            this.master = objToIntMap;
        }

        final void init(Object[] objArr, int[] iArr, int i) {
            this.keys = objArr;
            this.values = iArr;
            this.cursor = -1;
            this.remaining = i;
        }

        public void start() {
            this.master.initIterator(this);
            next();
        }

        public boolean done() {
            return this.remaining < 0;
        }

        public void next() {
            if (this.remaining == -1) {
                Kit.codeBug();
            }
            if (this.remaining == 0) {
                this.remaining = -1;
                this.cursor = -1;
                return;
            }
            this.cursor++;
            while (true) {
                Object obj = this.keys[this.cursor];
                if (obj == null || obj == ObjToIntMap.DELETED) {
                    this.cursor++;
                } else {
                    this.remaining--;
                    return;
                }
            }
        }

        public Object getKey() {
            UniqueTag uniqueTag = this.keys[this.cursor];
            if (uniqueTag == UniqueTag.NULL_VALUE) {
                return null;
            }
            return uniqueTag;
        }

        public int getValue() {
            return this.values[this.cursor];
        }

        public void setValue(int i) {
            this.values[this.cursor] = i;
        }
    }

    public ObjToIntMap() {
        this(4);
    }

    public ObjToIntMap(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        int i2 = 2;
        while ((1 << i2) < (i * 4) / 3) {
            i2++;
        }
        this.power = i2;
    }

    public boolean isEmpty() {
        return this.keyCount == 0;
    }

    public int size() {
        return this.keyCount;
    }

    public boolean has(Object obj) {
        if (obj == null) {
            obj = UniqueTag.NULL_VALUE;
        }
        return findIndex(obj) >= 0;
    }

    public int get(Object obj, int i) {
        if (obj == null) {
            obj = UniqueTag.NULL_VALUE;
        }
        int findIndex = findIndex(obj);
        if (findIndex >= 0) {
            return this.values[findIndex];
        }
        return i;
    }

    public int getExisting(Object obj) {
        if (obj == null) {
            obj = UniqueTag.NULL_VALUE;
        }
        int findIndex = findIndex(obj);
        if (findIndex >= 0) {
            return this.values[findIndex];
        }
        Kit.codeBug();
        return 0;
    }

    public void put(Object obj, int i) {
        if (obj == null) {
            obj = UniqueTag.NULL_VALUE;
        }
        this.values[ensureIndex(obj)] = i;
    }

    public Object intern(Object obj) {
        Object obj2;
        if (obj == null) {
            obj2 = 1;
            obj = UniqueTag.NULL_VALUE;
        } else {
            obj2 = null;
        }
        int ensureIndex = ensureIndex(obj);
        this.values[ensureIndex] = 0;
        return obj2 != null ? null : this.keys[ensureIndex];
    }

    public void remove(Object obj) {
        if (obj == null) {
            obj = UniqueTag.NULL_VALUE;
        }
        int findIndex = findIndex(obj);
        if (findIndex >= 0) {
            this.keys[findIndex] = DELETED;
            this.keyCount--;
        }
    }

    public void clear() {
        int length = this.keys.length;
        while (length != 0) {
            length--;
            this.keys[length] = null;
        }
        this.keyCount = 0;
        this.occupiedCount = 0;
    }

    public Iterator newIterator() {
        return new Iterator(this);
    }

    final void initIterator(Iterator iterator) {
        iterator.init(this.keys, this.values, this.keyCount);
    }

    public Object[] getKeys() {
        Object[] objArr = new Object[this.keyCount];
        getKeys(objArr, 0);
        return objArr;
    }

    public void getKeys(Object[] objArr, int i) {
        int i2 = this.keyCount;
        int i3 = 0;
        int i4 = i;
        while (i2 != 0) {
            int i5;
            UniqueTag uniqueTag = this.keys[i3];
            if (uniqueTag == null || uniqueTag == DELETED) {
                i5 = i2;
                i2 = i4;
            } else {
                if (uniqueTag == UniqueTag.NULL_VALUE) {
                    uniqueTag = null;
                }
                objArr[i4] = uniqueTag;
                i5 = i2 - 1;
                i2 = i4 + 1;
            }
            i3++;
            i4 = i2;
            i2 = i5;
        }
    }

    private static int tableLookupStep(int i, int i2, int i3) {
        int i4 = 32 - (i3 * 2);
        if (i4 >= 0) {
            return ((i >>> i4) & i2) | 1;
        }
        return ((i2 >>> (-i4)) & i) | 1;
    }

    private int findIndex(Object obj) {
        if (this.keys != null) {
            int hashCode = obj.hashCode();
            int i = hashCode * A;
            int i2 = i >>> (32 - this.power);
            Object obj2 = this.keys[i2];
            if (obj2 != null) {
                int i3 = 1 << this.power;
                if (obj2 == obj) {
                    return i2;
                }
                if (this.values[i3 + i2] == hashCode && obj2.equals(obj)) {
                    return i2;
                }
                int i4 = i3 - 1;
                i = tableLookupStep(i, i4, this.power);
                while (true) {
                    i2 = (i2 + i) & i4;
                    Object obj3 = this.keys[i2];
                    if (obj3 == null) {
                        break;
                    } else if (obj3 == obj) {
                        return i2;
                    } else {
                        if (this.values[i3 + i2] == hashCode && obj3.equals(obj)) {
                            return i2;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int insertNewKey(Object obj, int i) {
        int i2 = i * A;
        int i3 = i2 >>> (32 - this.power);
        int i4 = 1 << this.power;
        if (this.keys[i3] != null) {
            int i5 = i4 - 1;
            i2 = tableLookupStep(i2, i5, this.power);
            do {
                i3 = (i3 + i2) & i5;
            } while (this.keys[i3] != null);
        }
        this.keys[i3] = obj;
        this.values[i4 + i3] = i;
        this.occupiedCount++;
        this.keyCount++;
        return i3;
    }

    private void rehashTable() {
        if (this.keys == null) {
            int i = 1 << this.power;
            this.keys = new Object[i];
            this.values = new int[(i * 2)];
            return;
        }
        if (this.keyCount * 2 >= this.occupiedCount) {
            this.power++;
        }
        int i2 = 1 << this.power;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int length = objArr.length;
        this.keys = new Object[i2];
        this.values = new int[(i2 * 2)];
        i2 = this.keyCount;
        this.keyCount = 0;
        this.occupiedCount = 0;
        i = i2;
        i2 = 0;
        while (i != 0) {
            Object obj = objArr[i2];
            if (!(obj == null || obj == DELETED)) {
                this.values[insertNewKey(obj, iArr[length + i2])] = iArr[i2];
                i--;
            }
            i2++;
        }
    }

    private int ensureIndex(Object obj) {
        int i;
        int i2 = -1;
        int hashCode = obj.hashCode();
        if (this.keys != null) {
            int i3 = hashCode * A;
            i = i3 >>> (32 - this.power);
            Object obj2 = this.keys[i];
            if (obj2 != null) {
                int i4 = 1 << this.power;
                if (obj2 == obj) {
                    return i;
                }
                if (this.values[i4 + i] == hashCode && obj2.equals(obj)) {
                    return i;
                }
                if (obj2 == DELETED) {
                    i2 = i;
                }
                int i5 = i4 - 1;
                i3 = tableLookupStep(i3, i5, this.power);
                while (true) {
                    i = (i + i3) & i5;
                    Object obj3 = this.keys[i];
                    if (obj3 == null) {
                        break;
                    } else if (obj3 == obj) {
                        return i;
                    } else {
                        if (this.values[i4 + i] == hashCode && obj3.equals(obj)) {
                            return i;
                        }
                        if (obj3 == DELETED && r0 < 0) {
                            i2 = i;
                        }
                    }
                }
            }
        } else {
            i = -1;
        }
        if (i2 < 0) {
            if (this.keys == null || this.occupiedCount * 4 >= (1 << this.power) * 3) {
                rehashTable();
                return insertNewKey(obj, hashCode);
            }
            this.occupiedCount++;
            i2 = i;
        }
        this.keys[i2] = obj;
        this.values[(1 << this.power) + i2] = hashCode;
        this.keyCount++;
        return i2;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int i = this.keyCount;
        int i2 = 0;
        while (i != 0) {
            Object obj = this.keys[i2];
            if (!(obj == null || obj == DELETED)) {
                i--;
                objectOutputStream.writeObject(obj);
                objectOutputStream.writeInt(this.values[i2]);
            }
            i2++;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int i = 0;
        objectInputStream.defaultReadObject();
        int i2 = this.keyCount;
        if (i2 != 0) {
            this.keyCount = 0;
            int i3 = 1 << this.power;
            this.keys = new Object[i3];
            this.values = new int[(i3 * 2)];
            while (i != i2) {
                Object readObject = objectInputStream.readObject();
                this.values[insertNewKey(readObject, readObject.hashCode())] = objectInputStream.readInt();
                i++;
            }
        }
    }
}
