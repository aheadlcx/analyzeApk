package org.mozilla.classfile;

final class SuperBlock {
    private int end;
    private int index;
    private boolean isInQueue = false;
    private boolean isInitialized = false;
    private int[] locals;
    private int[] stack = new int[0];
    private int start;

    SuperBlock(int i, int i2, int i3, int[] iArr) {
        this.index = i;
        this.start = i2;
        this.end = i3;
        this.locals = new int[iArr.length];
        System.arraycopy(iArr, 0, this.locals, 0, iArr.length);
    }

    int getIndex() {
        return this.index;
    }

    int[] getLocals() {
        Object obj = new int[this.locals.length];
        System.arraycopy(this.locals, 0, obj, 0, this.locals.length);
        return obj;
    }

    int[] getTrimmedLocals() {
        int i;
        int i2 = 0;
        int length = this.locals.length - 1;
        while (length >= 0 && this.locals[length] == 0 && !TypeInfo.isTwoWords(this.locals[length - 1])) {
            length--;
        }
        int i3 = length + 1;
        length = i3;
        for (i = 0; i < i3; i++) {
            if (TypeInfo.isTwoWords(this.locals[i])) {
                length--;
            }
        }
        int[] iArr = new int[length];
        i = 0;
        while (i < length) {
            iArr[i] = this.locals[i2];
            if (TypeInfo.isTwoWords(this.locals[i2])) {
                i2++;
            }
            i++;
            i2++;
        }
        return iArr;
    }

    int[] getStack() {
        Object obj = new int[this.stack.length];
        System.arraycopy(this.stack, 0, obj, 0, this.stack.length);
        return obj;
    }

    boolean merge(int[] iArr, int i, int[] iArr2, int i2, ConstantPool constantPool) {
        boolean z = false;
        if (!this.isInitialized) {
            System.arraycopy(iArr, 0, this.locals, 0, i);
            this.stack = new int[i2];
            System.arraycopy(iArr2, 0, this.stack, 0, i2);
            this.isInitialized = true;
            return true;
        } else if (this.locals.length == i && this.stack.length == i2) {
            boolean mergeState = mergeState(this.locals, iArr, i, constantPool);
            boolean mergeState2 = mergeState(this.stack, iArr2, i2, constantPool);
            if (mergeState || mergeState2) {
                z = true;
            }
            return z;
        } else {
            throw new IllegalArgumentException("bad merge attempt");
        }
    }

    private boolean mergeState(int[] iArr, int[] iArr2, int i, ConstantPool constantPool) {
        boolean z = false;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            iArr[i2] = TypeInfo.merge(iArr[i2], iArr2[i2], constantPool);
            if (i3 != iArr[i2]) {
                z = true;
            }
        }
        return z;
    }

    int getStart() {
        return this.start;
    }

    int getEnd() {
        return this.end;
    }

    public String toString() {
        return "sb " + this.index;
    }

    boolean isInitialized() {
        return this.isInitialized;
    }

    void setInitialized(boolean z) {
        this.isInitialized = z;
    }

    boolean isInQueue() {
        return this.isInQueue;
    }

    void setInQueue(boolean z) {
        this.isInQueue = z;
    }
}
