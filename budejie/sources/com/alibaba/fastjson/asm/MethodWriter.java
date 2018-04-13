package com.alibaba.fastjson.asm;

import com.budejie.www.R$styleable;

public class MethodWriter implements MethodVisitor {
    private int access;
    private ByteVector code = new ByteVector();
    final ClassWriter cw;
    private final int desc;
    int exceptionCount;
    int[] exceptions;
    private int maxLocals;
    private int maxStack;
    private final int name;
    MethodWriter next;

    public MethodWriter(ClassWriter classWriter, int i, String str, String str2, String str3, String[] strArr) {
        if (classWriter.firstMethod == null) {
            classWriter.firstMethod = this;
        } else {
            classWriter.lastMethod.next = this;
        }
        classWriter.lastMethod = this;
        this.cw = classWriter;
        this.access = i;
        this.name = classWriter.newUTF8(str);
        this.desc = classWriter.newUTF8(str2);
        if (strArr != null && strArr.length > 0) {
            this.exceptionCount = strArr.length;
            this.exceptions = new int[this.exceptionCount];
            for (int i2 = 0; i2 < this.exceptionCount; i2++) {
                this.exceptions[i2] = classWriter.newClassItem(strArr[i2]).index;
            }
        }
    }

    public void visitInsn(int i) {
        this.code.putByte(i);
    }

    public void visitIntInsn(int i, int i2) {
        this.code.put11(i, i2);
    }

    public void visitVarInsn(int i, int i2) {
        if (i2 < 4 && i != 169) {
            int i3;
            if (i < 54) {
                i3 = (((i - 21) << 2) + 26) + i2;
            } else {
                i3 = (((i - 54) << 2) + 59) + i2;
            }
            this.code.putByte(i3);
        } else if (i2 >= 256) {
            this.code.putByte(R$styleable.Theme_Custom_bt2_color_state).put12(i, i2);
        } else {
            this.code.put11(i, i2);
        }
    }

    public void visitTypeInsn(int i, String str) {
        this.code.put12(i, this.cw.newClassItem(str).index);
    }

    public void visitFieldInsn(int i, String str, String str2, String str3) {
        this.code.put12(i, this.cw.newFieldItem(str, str2, str3).index);
    }

    public void visitMethodInsn(int i, String str, String str2, String str3) {
        boolean z = i == 185;
        Item newMethodItem = this.cw.newMethodItem(str, str2, str3, z);
        int i2 = newMethodItem.intVal;
        if (z) {
            int argumentsAndReturnSizes;
            if (i2 == 0) {
                argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
                newMethodItem.intVal = argumentsAndReturnSizes;
            } else {
                argumentsAndReturnSizes = i2;
            }
            this.code.put12(185, newMethodItem.index).put11(argumentsAndReturnSizes >> 2, 0);
            return;
        }
        this.code.put12(i, newMethodItem.index);
    }

    public void visitJumpInsn(int i, Label label) {
        if ((label.status & 2) == 0 || label.position - this.code.length >= -32768) {
            this.code.putByte(i);
            label.put(this, this.code, this.code.length - 1);
            return;
        }
        throw new UnsupportedOperationException();
    }

    public void visitLabel(Label label) {
        label.resolve(this, this.code.length, this.code.data);
    }

    public void visitLdcInsn(Object obj) {
        Item newConstItem = this.cw.newConstItem(obj);
        int i = newConstItem.index;
        if (newConstItem.type == 5 || newConstItem.type == 6) {
            this.code.put12(20, i);
        } else if (i >= 256) {
            this.code.put12(19, i);
        } else {
            this.code.put11(18, i);
        }
    }

    public void visitIincInsn(int i, int i2) {
        this.code.putByte(R$styleable.Theme_Custom_new_item_login_tencent_bg).put11(i, i2);
    }

    public void visitMaxs(int i, int i2) {
        this.maxStack = i;
        this.maxLocals = i2;
    }

    public void visitEnd() {
    }

    final int getSize() {
        int i = 8;
        if (this.code.length > 0) {
            this.cw.newUTF8("Code");
            i = 8 + ((this.code.length + 18) + 0);
        }
        if (this.exceptionCount <= 0) {
            return i;
        }
        this.cw.newUTF8("Exceptions");
        return i + ((this.exceptionCount * 2) + 8);
    }

    final void put(ByteVector byteVector) {
        int i;
        int i2 = 0;
        byteVector.putShort(this.access & -393217).putShort(this.name).putShort(this.desc);
        if (this.code.length > 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (this.exceptionCount > 0) {
            i++;
        }
        byteVector.putShort(i);
        if (this.code.length > 0) {
            byteVector.putShort(this.cw.newUTF8("Code")).putInt((this.code.length + 12) + 0);
            byteVector.putShort(this.maxStack).putShort(this.maxLocals);
            byteVector.putInt(this.code.length).putByteArray(this.code.data, 0, this.code.length);
            byteVector.putShort(0);
            byteVector.putShort(0);
        }
        if (this.exceptionCount > 0) {
            byteVector.putShort(this.cw.newUTF8("Exceptions")).putInt((this.exceptionCount * 2) + 2);
            byteVector.putShort(this.exceptionCount);
            while (i2 < this.exceptionCount) {
                byteVector.putShort(this.exceptions[i2]);
                i2++;
            }
        }
    }
}
