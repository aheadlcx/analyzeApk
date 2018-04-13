package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjArray implements Serializable {
    private static final int FIELDS_STORE_SIZE = 5;
    static final long serialVersionUID = 4174889037736658296L;
    private transient Object[] data;
    private transient Object f0;
    private transient Object f1;
    private transient Object f2;
    private transient Object f3;
    private transient Object f4;
    private boolean sealed;
    private int size;

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal() {
        this.sealed = true;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final int size() {
        return this.size;
    }

    public final void setSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (this.sealed) {
            throw onSeledMutation();
        } else {
            int i2 = this.size;
            if (i < i2) {
                for (int i3 = i; i3 != i2; i3++) {
                    setImpl(i3, null);
                }
            } else if (i > i2 && i > 5) {
                ensureCapacity(i);
            }
            this.size = i;
        }
    }

    public final Object get(int i) {
        if (i >= 0 && i < this.size) {
            return getImpl(i);
        }
        throw onInvalidIndex(i, this.size);
    }

    public final void set(int i, Object obj) {
        if (i < 0 || i >= this.size) {
            throw onInvalidIndex(i, this.size);
        } else if (this.sealed) {
            throw onSeledMutation();
        } else {
            setImpl(i, obj);
        }
    }

    private Object getImpl(int i) {
        switch (i) {
            case 0:
                return this.f0;
            case 1:
                return this.f1;
            case 2:
                return this.f2;
            case 3:
                return this.f3;
            case 4:
                return this.f4;
            default:
                return this.data[i - 5];
        }
    }

    private void setImpl(int i, Object obj) {
        switch (i) {
            case 0:
                this.f0 = obj;
                return;
            case 1:
                this.f1 = obj;
                return;
            case 2:
                this.f2 = obj;
                return;
            case 3:
                this.f3 = obj;
                return;
            case 4:
                this.f4 = obj;
                return;
            default:
                this.data[i - 5] = obj;
                return;
        }
    }

    public int indexOf(Object obj) {
        int i = this.size;
        for (int i2 = 0; i2 != i; i2++) {
            Object impl = getImpl(i2);
            if (impl == obj) {
                return i2;
            }
            if (impl != null && impl.equals(obj)) {
                return i2;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int i = this.size;
        while (i != 0) {
            i--;
            Object impl = getImpl(i);
            if (impl == obj) {
                return i;
            }
            if (impl != null && impl.equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public final Object peek() {
        int i = this.size;
        if (i != 0) {
            return getImpl(i - 1);
        }
        throw onEmptyStackTopRead();
    }

    public final Object pop() {
        if (this.sealed) {
            throw onSeledMutation();
        }
        Object obj;
        int i = this.size - 1;
        switch (i) {
            case -1:
                throw onEmptyStackTopRead();
            case 0:
                obj = this.f0;
                this.f0 = null;
                break;
            case 1:
                obj = this.f1;
                this.f1 = null;
                break;
            case 2:
                obj = this.f2;
                this.f2 = null;
                break;
            case 3:
                obj = this.f3;
                this.f3 = null;
                break;
            case 4:
                obj = this.f4;
                this.f4 = null;
                break;
            default:
                obj = this.data[i - 5];
                this.data[i - 5] = null;
                break;
        }
        this.size = i;
        return obj;
    }

    public final void push(Object obj) {
        add(obj);
    }

    public final void add(Object obj) {
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i = this.size;
        if (i >= 5) {
            ensureCapacity(i + 1);
        }
        this.size = i + 1;
        setImpl(i, obj);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void add(int r7, java.lang.Object r8) {
        /*
        r6 = this;
        r1 = r6.size;
        if (r7 < 0) goto L_0x0006;
    L_0x0004:
        if (r7 <= r1) goto L_0x000d;
    L_0x0006:
        r0 = r1 + 1;
        r0 = onInvalidIndex(r7, r0);
        throw r0;
    L_0x000d:
        r0 = r6.sealed;
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = onSeledMutation();
        throw r0;
    L_0x0016:
        switch(r7) {
            case 0: goto L_0x003a;
            case 1: goto L_0x0076;
            case 2: goto L_0x0074;
            case 3: goto L_0x0072;
            case 4: goto L_0x0070;
            default: goto L_0x0019;
        };
    L_0x0019:
        r0 = r1 + 1;
        r6.ensureCapacity(r0);
        if (r7 == r1) goto L_0x002f;
    L_0x0020:
        r0 = r6.data;
        r2 = r7 + -5;
        r3 = r6.data;
        r4 = r7 + -5;
        r4 = r4 + 1;
        r5 = r1 - r7;
        java.lang.System.arraycopy(r0, r2, r3, r4, r5);
    L_0x002f:
        r0 = r6.data;
        r2 = r7 + -5;
        r0[r2] = r8;
    L_0x0035:
        r0 = r1 + 1;
        r6.size = r0;
        return;
    L_0x003a:
        if (r1 != 0) goto L_0x003f;
    L_0x003c:
        r6.f0 = r8;
        goto L_0x0035;
    L_0x003f:
        r0 = r6.f0;
        r6.f0 = r8;
    L_0x0043:
        r2 = 1;
        if (r1 != r2) goto L_0x0049;
    L_0x0046:
        r6.f1 = r0;
        goto L_0x0035;
    L_0x0049:
        r8 = r6.f1;
        r6.f1 = r0;
        r0 = r8;
    L_0x004e:
        r2 = 2;
        if (r1 != r2) goto L_0x0054;
    L_0x0051:
        r6.f2 = r0;
        goto L_0x0035;
    L_0x0054:
        r8 = r6.f2;
        r6.f2 = r0;
        r0 = r8;
    L_0x0059:
        r2 = 3;
        if (r1 != r2) goto L_0x005f;
    L_0x005c:
        r6.f3 = r0;
        goto L_0x0035;
    L_0x005f:
        r8 = r6.f3;
        r6.f3 = r0;
        r0 = r8;
    L_0x0064:
        r2 = 4;
        if (r1 != r2) goto L_0x006a;
    L_0x0067:
        r6.f4 = r0;
        goto L_0x0035;
    L_0x006a:
        r8 = r6.f4;
        r6.f4 = r0;
        r7 = 5;
        goto L_0x0019;
    L_0x0070:
        r0 = r8;
        goto L_0x0064;
    L_0x0072:
        r0 = r8;
        goto L_0x0059;
    L_0x0074:
        r0 = r8;
        goto L_0x004e;
    L_0x0076:
        r0 = r8;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ObjArray.add(int, java.lang.Object):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void remove(int r8) {
        /*
        r7 = this;
        r6 = 0;
        r0 = r7.size;
        if (r8 < 0) goto L_0x0007;
    L_0x0005:
        if (r8 < r0) goto L_0x000c;
    L_0x0007:
        r0 = onInvalidIndex(r8, r0);
        throw r0;
    L_0x000c:
        r1 = r7.sealed;
        if (r1 == 0) goto L_0x0015;
    L_0x0010:
        r0 = onSeledMutation();
        throw r0;
    L_0x0015:
        r0 = r0 + -1;
        switch(r8) {
            case 0: goto L_0x0034;
            case 1: goto L_0x003d;
            case 2: goto L_0x0047;
            case 3: goto L_0x0051;
            case 4: goto L_0x005b;
            default: goto L_0x001a;
        };
    L_0x001a:
        if (r8 == r0) goto L_0x002b;
    L_0x001c:
        r1 = r7.data;
        r2 = r8 + -5;
        r2 = r2 + 1;
        r3 = r7.data;
        r4 = r8 + -5;
        r5 = r0 - r8;
        java.lang.System.arraycopy(r1, r2, r3, r4, r5);
    L_0x002b:
        r1 = r7.data;
        r2 = r0 + -5;
        r1[r2] = r6;
    L_0x0031:
        r7.size = r0;
        return;
    L_0x0034:
        if (r0 != 0) goto L_0x0039;
    L_0x0036:
        r7.f0 = r6;
        goto L_0x0031;
    L_0x0039:
        r1 = r7.f1;
        r7.f0 = r1;
    L_0x003d:
        r1 = 1;
        if (r0 != r1) goto L_0x0043;
    L_0x0040:
        r7.f1 = r6;
        goto L_0x0031;
    L_0x0043:
        r1 = r7.f2;
        r7.f1 = r1;
    L_0x0047:
        r1 = 2;
        if (r0 != r1) goto L_0x004d;
    L_0x004a:
        r7.f2 = r6;
        goto L_0x0031;
    L_0x004d:
        r1 = r7.f3;
        r7.f2 = r1;
    L_0x0051:
        r1 = 3;
        if (r0 != r1) goto L_0x0057;
    L_0x0054:
        r7.f3 = r6;
        goto L_0x0031;
    L_0x0057:
        r1 = r7.f4;
        r7.f3 = r1;
    L_0x005b:
        r1 = 4;
        if (r0 != r1) goto L_0x0061;
    L_0x005e:
        r7.f4 = r6;
        goto L_0x0031;
    L_0x0061:
        r1 = r7.data;
        r2 = 0;
        r1 = r1[r2];
        r7.f4 = r1;
        r8 = 5;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ObjArray.remove(int):void");
    }

    public final void clear() {
        if (this.sealed) {
            throw onSeledMutation();
        }
        int i = this.size;
        for (int i2 = 0; i2 != i; i2++) {
            setImpl(i2, null);
        }
        this.size = 0;
    }

    public final Object[] toArray() {
        Object[] objArr = new Object[this.size];
        toArray(objArr, 0);
        return objArr;
    }

    public final void toArray(Object[] objArr) {
        toArray(objArr, 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void toArray(java.lang.Object[] r5, int r6) {
        /*
        r4 = this;
        r0 = r4.size;
        switch(r0) {
            case 0: goto L_0x002d;
            case 1: goto L_0x0027;
            case 2: goto L_0x0021;
            case 3: goto L_0x001b;
            case 4: goto L_0x0015;
            case 5: goto L_0x000f;
            default: goto L_0x0005;
        };
    L_0x0005:
        r1 = r4.data;
        r2 = 0;
        r3 = r6 + 5;
        r0 = r0 + -5;
        java.lang.System.arraycopy(r1, r2, r5, r3, r0);
    L_0x000f:
        r0 = r6 + 4;
        r1 = r4.f4;
        r5[r0] = r1;
    L_0x0015:
        r0 = r6 + 3;
        r1 = r4.f3;
        r5[r0] = r1;
    L_0x001b:
        r0 = r6 + 2;
        r1 = r4.f2;
        r5[r0] = r1;
    L_0x0021:
        r0 = r6 + 1;
        r1 = r4.f1;
        r5[r0] = r1;
    L_0x0027:
        r0 = r6 + 0;
        r1 = r4.f0;
        r5[r0] = r1;
    L_0x002d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ObjArray.toArray(java.lang.Object[], int):void");
    }

    private void ensureCapacity(int i) {
        int i2 = 10;
        int i3 = i - 5;
        if (i3 <= 0) {
            throw new IllegalArgumentException();
        } else if (this.data == null) {
            if (10 >= i3) {
                i3 = 10;
            }
            this.data = new Object[i3];
        } else {
            int length = this.data.length;
            if (length < i3) {
                if (length > 5) {
                    i2 = length * 2;
                }
                if (i2 >= i3) {
                    i3 = i2;
                }
                Object obj = new Object[i3];
                if (this.size > 5) {
                    System.arraycopy(this.data, 0, obj, 0, this.size - 5);
                }
                this.data = obj;
            }
        }
    }

    private static RuntimeException onInvalidIndex(int i, int i2) {
        throw new IndexOutOfBoundsException(i + " ∉ [0, " + i2 + ')');
    }

    private static RuntimeException onEmptyStackTopRead() {
        throw new RuntimeException("Empty stack");
    }

    private static RuntimeException onSeledMutation() {
        throw new IllegalStateException("Attempt to modify sealed array");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int i = this.size;
        for (int i2 = 0; i2 != i; i2++) {
            objectOutputStream.writeObject(getImpl(i2));
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int i = this.size;
        if (i > 5) {
            this.data = new Object[(i - 5)];
        }
        for (int i2 = 0; i2 != i; i2++) {
            setImpl(i2, objectInputStream.readObject());
        }
    }
}
