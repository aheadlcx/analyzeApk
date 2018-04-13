package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UintMap implements Serializable {
    private static final int A = -1640531527;
    private static final int DELETED = -2;
    private static final int EMPTY = -1;
    private static final boolean check = false;
    static final long serialVersionUID = 4242698212885848444L;
    private transient int ivaluesShift;
    private int keyCount;
    private transient int[] keys;
    private transient int occupiedCount;
    private int power;
    private transient Object[] values;

    public UintMap() {
        this(4);
    }

    public UintMap(int i) {
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

    public boolean has(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        return findIndex(i) >= 0;
    }

    public Object getObject(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        if (this.values != null) {
            int findIndex = findIndex(i);
            if (findIndex >= 0) {
                return this.values[findIndex];
            }
        }
        return null;
    }

    public int getInt(int i, int i2) {
        if (i < 0) {
            Kit.codeBug();
        }
        int findIndex = findIndex(i);
        if (findIndex < 0) {
            return i2;
        }
        if (this.ivaluesShift != 0) {
            return this.keys[findIndex + this.ivaluesShift];
        }
        return 0;
    }

    public int getExistingInt(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        int findIndex = findIndex(i);
        if (findIndex < 0) {
            Kit.codeBug();
            return 0;
        } else if (this.ivaluesShift != 0) {
            return this.keys[findIndex + this.ivaluesShift];
        } else {
            return 0;
        }
    }

    public void put(int i, Object obj) {
        if (i < 0) {
            Kit.codeBug();
        }
        int ensureIndex = ensureIndex(i, false);
        if (this.values == null) {
            this.values = new Object[(1 << this.power)];
        }
        this.values[ensureIndex] = obj;
    }

    public void put(int i, int i2) {
        if (i < 0) {
            Kit.codeBug();
        }
        int ensureIndex = ensureIndex(i, true);
        if (this.ivaluesShift == 0) {
            int i3 = 1 << this.power;
            if (this.keys.length != i3 * 2) {
                Object obj = new int[(i3 * 2)];
                System.arraycopy(this.keys, 0, obj, 0, i3);
                this.keys = obj;
            }
            this.ivaluesShift = i3;
        }
        this.keys[ensureIndex + this.ivaluesShift] = i2;
    }

    public void remove(int i) {
        if (i < 0) {
            Kit.codeBug();
        }
        int findIndex = findIndex(i);
        if (findIndex >= 0) {
            this.keys[findIndex] = -2;
            this.keyCount--;
            if (this.values != null) {
                this.values[findIndex] = null;
            }
            if (this.ivaluesShift != 0) {
                this.keys[findIndex + this.ivaluesShift] = 0;
            }
        }
    }

    public void clear() {
        int i = 1 << this.power;
        if (this.keys != null) {
            int i2;
            for (i2 = 0; i2 != i; i2++) {
                this.keys[i2] = -1;
            }
            if (this.values != null) {
                for (i2 = 0; i2 != i; i2++) {
                    this.values[i2] = null;
                }
            }
        }
        this.ivaluesShift = 0;
        this.keyCount = 0;
        this.occupiedCount = 0;
    }

    public int[] getKeys() {
        int[] iArr = this.keys;
        int i = this.keyCount;
        int[] iArr2 = new int[i];
        int i2 = i;
        i = 0;
        while (i2 != 0) {
            int i3 = iArr[i];
            if (!(i3 == -1 || i3 == -2)) {
                i2--;
                iArr2[i2] = i3;
            }
            i++;
        }
        return iArr2;
    }

    private static int tableLookupStep(int i, int i2, int i3) {
        int i4 = 32 - (i3 * 2);
        if (i4 >= 0) {
            return ((i >>> i4) & i2) | 1;
        }
        return ((i2 >>> (-i4)) & i) | 1;
    }

    private int findIndex(int i) {
        int[] iArr = this.keys;
        if (iArr != null) {
            int i2 = i * A;
            int i3 = i2 >>> (32 - this.power);
            int i4 = iArr[i3];
            if (i4 == i) {
                return i3;
            }
            if (i4 != -1) {
                i4 = (1 << this.power) - 1;
                i2 = tableLookupStep(i2, i4, this.power);
                int i5;
                do {
                    i3 = (i3 + i2) & i4;
                    i5 = iArr[i3];
                    if (i5 == i) {
                        return i3;
                    }
                } while (i5 != -1);
            }
        }
        return -1;
    }

    private int insertNewKey(int i) {
        int[] iArr = this.keys;
        int i2 = i * A;
        int i3 = i2 >>> (32 - this.power);
        if (iArr[i3] != -1) {
            int i4 = (1 << this.power) - 1;
            i2 = tableLookupStep(i2, i4, this.power);
            do {
                i3 = (i3 + i2) & i4;
            } while (iArr[i3] != -1);
        }
        iArr[i3] = i;
        this.occupiedCount++;
        this.keyCount++;
        return i3;
    }

    private void rehashTable(boolean z) {
        int i;
        int i2 = 0;
        if (this.keys != null && this.keyCount * 2 >= this.occupiedCount) {
            this.power++;
        }
        int i3 = 1 << this.power;
        int[] iArr = this.keys;
        int i4 = this.ivaluesShift;
        if (i4 != 0 || z) {
            this.ivaluesShift = i3;
            this.keys = new int[(i3 * 2)];
        } else {
            this.keys = new int[i3];
        }
        for (i = 0; i != i3; i++) {
            this.keys[i] = -1;
        }
        Object[] objArr = this.values;
        if (objArr != null) {
            this.values = new Object[i3];
        }
        i = this.keyCount;
        this.occupiedCount = 0;
        if (i != 0) {
            this.keyCount = 0;
            while (i != 0) {
                i3 = iArr[i2];
                if (!(i3 == -1 || i3 == -2)) {
                    i3 = insertNewKey(i3);
                    if (objArr != null) {
                        this.values[i3] = objArr[i2];
                    }
                    if (i4 != 0) {
                        this.keys[i3 + this.ivaluesShift] = iArr[i4 + i2];
                    }
                    i--;
                }
                i2++;
            }
        }
    }

    private int ensureIndex(int i, boolean z) {
        int i2;
        int i3;
        int[] iArr = this.keys;
        if (iArr != null) {
            int i4 = i * A;
            i2 = i4 >>> (32 - this.power);
            i3 = iArr[i2];
            if (i3 == i) {
                return i2;
            }
            if (i3 != -1) {
                if (i3 == -2) {
                    i3 = i2;
                } else {
                    i3 = -1;
                }
                int i5 = (1 << this.power) - 1;
                i4 = tableLookupStep(i4, i5, this.power);
                int i6;
                do {
                    i2 = (i2 + i4) & i5;
                    i6 = iArr[i2];
                    if (i6 == i) {
                        return i2;
                    }
                    if (i6 == -2 && r0 < 0) {
                        i3 = i2;
                        continue;
                    }
                } while (i6 != -1);
            } else {
                i3 = -1;
            }
        } else {
            i3 = -1;
            i2 = -1;
        }
        if (i3 < 0) {
            if (iArr == null || this.occupiedCount * 4 >= (1 << this.power) * 3) {
                rehashTable(z);
                return insertNewKey(i);
            }
            this.occupiedCount++;
            i3 = i2;
        }
        iArr[i3] = i;
        this.keyCount++;
        return i3;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        boolean z = true;
        objectOutputStream.defaultWriteObject();
        int i = this.keyCount;
        if (i != 0) {
            boolean z2 = this.ivaluesShift != 0;
            if (this.values == null) {
                z = false;
            }
            objectOutputStream.writeBoolean(z2);
            objectOutputStream.writeBoolean(z);
            int i2 = i;
            i = 0;
            while (i2 != 0) {
                int i3 = this.keys[i];
                if (!(i3 == -1 || i3 == -2)) {
                    i2--;
                    objectOutputStream.writeInt(i3);
                    if (z2) {
                        objectOutputStream.writeInt(this.keys[this.ivaluesShift + i]);
                    }
                    if (z) {
                        objectOutputStream.writeObject(this.values[i]);
                    }
                }
                i++;
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int i = 0;
        objectInputStream.defaultReadObject();
        int i2 = this.keyCount;
        if (i2 != 0) {
            int i3;
            this.keyCount = 0;
            boolean readBoolean = objectInputStream.readBoolean();
            boolean readBoolean2 = objectInputStream.readBoolean();
            int i4 = 1 << this.power;
            if (readBoolean) {
                this.keys = new int[(i4 * 2)];
                this.ivaluesShift = i4;
            } else {
                this.keys = new int[i4];
            }
            for (i3 = 0; i3 != i4; i3++) {
                this.keys[i3] = -1;
            }
            if (readBoolean2) {
                this.values = new Object[i4];
            }
            while (i != i2) {
                i3 = insertNewKey(objectInputStream.readInt());
                if (readBoolean) {
                    this.keys[this.ivaluesShift + i3] = objectInputStream.readInt();
                }
                if (readBoolean2) {
                    this.values[i3] = objectInputStream.readObject();
                }
                i++;
            }
        }
    }
}
