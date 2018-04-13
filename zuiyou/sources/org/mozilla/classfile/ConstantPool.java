package org.mozilla.classfile;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.UintMap;

final class ConstantPool {
    static final byte CONSTANT_Class = (byte) 7;
    static final byte CONSTANT_Double = (byte) 6;
    static final byte CONSTANT_Fieldref = (byte) 9;
    static final byte CONSTANT_Float = (byte) 4;
    static final byte CONSTANT_Integer = (byte) 3;
    static final byte CONSTANT_InterfaceMethodref = (byte) 11;
    static final byte CONSTANT_Long = (byte) 5;
    static final byte CONSTANT_Methodref = (byte) 10;
    static final byte CONSTANT_NameAndType = (byte) 12;
    static final byte CONSTANT_String = (byte) 8;
    static final byte CONSTANT_Utf8 = (byte) 1;
    private static final int ConstantPoolSize = 256;
    private static final int MAX_UTF_ENCODING_SIZE = 65535;
    private ClassFileWriter cfw;
    private ObjToIntMap itsClassHash = new ObjToIntMap();
    private UintMap itsConstantData = new UintMap();
    private ObjToIntMap itsFieldRefHash = new ObjToIntMap();
    private ObjToIntMap itsMethodRefHash = new ObjToIntMap();
    private byte[] itsPool;
    private UintMap itsPoolTypes = new UintMap();
    private UintMap itsStringConstHash = new UintMap();
    private int itsTop;
    private int itsTopIndex;
    private ObjToIntMap itsUtf8Hash = new ObjToIntMap();

    ConstantPool(ClassFileWriter classFileWriter) {
        this.cfw = classFileWriter;
        this.itsTopIndex = 1;
        this.itsPool = new byte[256];
        this.itsTop = 0;
    }

    int write(byte[] bArr, int i) {
        int putInt16 = ClassFileWriter.putInt16((short) this.itsTopIndex, bArr, i);
        System.arraycopy(this.itsPool, 0, bArr, putInt16, this.itsTop);
        return putInt16 + this.itsTop;
    }

    int getWriteSize() {
        return this.itsTop + 2;
    }

    int addConstant(int i) {
        ensure(5);
        byte[] bArr = this.itsPool;
        int i2 = this.itsTop;
        this.itsTop = i2 + 1;
        bArr[i2] = (byte) 3;
        this.itsTop = ClassFileWriter.putInt32(i, this.itsPool, this.itsTop);
        this.itsPoolTypes.put(this.itsTopIndex, 3);
        int i3 = this.itsTopIndex;
        this.itsTopIndex = i3 + 1;
        return (short) i3;
    }

    int addConstant(long j) {
        ensure(9);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = (byte) 5;
        this.itsTop = ClassFileWriter.putInt64(j, this.itsPool, this.itsTop);
        int i2 = this.itsTopIndex;
        this.itsTopIndex += 2;
        this.itsPoolTypes.put(i2, 5);
        return i2;
    }

    int addConstant(float f) {
        ensure(5);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = (byte) 4;
        this.itsTop = ClassFileWriter.putInt32(Float.floatToIntBits(f), this.itsPool, this.itsTop);
        this.itsPoolTypes.put(this.itsTopIndex, 4);
        int i2 = this.itsTopIndex;
        this.itsTopIndex = i2 + 1;
        return i2;
    }

    int addConstant(double d) {
        ensure(9);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = (byte) 6;
        this.itsTop = ClassFileWriter.putInt64(Double.doubleToLongBits(d), this.itsPool, this.itsTop);
        int i2 = this.itsTopIndex;
        this.itsTopIndex += 2;
        this.itsPoolTypes.put(i2, 6);
        return i2;
    }

    int addConstant(String str) {
        int addUtf8 = addUtf8(str) & 65535;
        int i = this.itsStringConstHash.getInt(addUtf8, -1);
        if (i == -1) {
            i = this.itsTopIndex;
            this.itsTopIndex = i + 1;
            ensure(3);
            byte[] bArr = this.itsPool;
            int i2 = this.itsTop;
            this.itsTop = i2 + 1;
            bArr[i2] = (byte) 8;
            this.itsTop = ClassFileWriter.putInt16(addUtf8, this.itsPool, this.itsTop);
            this.itsStringConstHash.put(addUtf8, i);
        }
        this.itsPoolTypes.put(i, 8);
        return i;
    }

    boolean isUnderUtfEncodingLimit(String str) {
        int length = str.length();
        if (length * 3 <= 65535) {
            return true;
        }
        if (length > 65535) {
            return false;
        }
        if (length != getUtfEncodingLimit(str, 0, length)) {
            return false;
        }
        return true;
    }

    int getUtfEncodingLimit(String str, int i, int i2) {
        int i3 = 65535;
        if ((i2 - i) * 3 <= 65535) {
            return i2;
        }
        for (int i4 = i; i4 != i2; i4++) {
            char charAt = str.charAt(i4);
            if (charAt != '\u0000' && charAt <= '') {
                i3--;
            } else if (charAt < '߿') {
                i3 -= 2;
            } else {
                i3 -= 3;
            }
            if (i3 < 0) {
                return i4;
            }
        }
        return i2;
    }

    short addUtf8(String str) {
        int i;
        int i2 = this.itsUtf8Hash.get(str, -1);
        if (i2 == -1) {
            byte b;
            int length = str.length();
            if (length > 65535) {
                i = i2;
                b = (byte) 1;
            } else {
                ensure((length * 3) + 3);
                i = this.itsTop;
                int i3 = i + 1;
                this.itsPool[i] = (byte) 1;
                i = i3 + 2;
                char[] charBuffer = this.cfw.getCharBuffer(length);
                str.getChars(0, length, charBuffer, 0);
                int i4 = 0;
                i3 = i;
                while (i4 != length) {
                    char c = charBuffer[i4];
                    if (c != '\u0000' && c <= '') {
                        i = i3 + 1;
                        this.itsPool[i3] = (byte) c;
                    } else if (c > '߿') {
                        r9 = i3 + 1;
                        this.itsPool[i3] = (byte) ((c >> 12) | Opcodes.SHL_INT_LIT8);
                        i3 = r9 + 1;
                        this.itsPool[r9] = (byte) (((c >> 6) & 63) | 128);
                        i = i3 + 1;
                        this.itsPool[i3] = (byte) ((c & 63) | 128);
                    } else {
                        r9 = i3 + 1;
                        this.itsPool[i3] = (byte) ((c >> 6) | 192);
                        i = r9 + 1;
                        this.itsPool[r9] = (byte) ((c & 63) | 128);
                    }
                    i4++;
                    i3 = i;
                }
                i = i3 - ((this.itsTop + 1) + 2);
                if (i > 65535) {
                    i = i2;
                    b = (byte) 1;
                } else {
                    this.itsPool[this.itsTop + 1] = (byte) (i >>> 8);
                    this.itsPool[this.itsTop + 2] = (byte) i;
                    this.itsTop = i3;
                    i2 = this.itsTopIndex;
                    this.itsTopIndex = i2 + 1;
                    this.itsUtf8Hash.put(str, i2);
                    i = i2;
                    b = (byte) 0;
                }
            }
            if (b != (byte) 0) {
                throw new IllegalArgumentException("Too big string");
            }
        }
        i = i2;
        setConstantData(i, str);
        this.itsPoolTypes.put(i, 1);
        return (short) i;
    }

    private short addNameAndType(String str, String str2) {
        short addUtf8 = addUtf8(str);
        short addUtf82 = addUtf8(str2);
        ensure(5);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = CONSTANT_NameAndType;
        this.itsTop = ClassFileWriter.putInt16(addUtf8, this.itsPool, this.itsTop);
        this.itsTop = ClassFileWriter.putInt16(addUtf82, this.itsPool, this.itsTop);
        this.itsPoolTypes.put(this.itsTopIndex, 12);
        int i2 = this.itsTopIndex;
        this.itsTopIndex = i2 + 1;
        return (short) i2;
    }

    short addClass(String str) {
        int i = this.itsClassHash.get(str, -1);
        if (i == -1) {
            String str2;
            if (str.indexOf(46) > 0) {
                String slashedForm = ClassFileWriter.getSlashedForm(str);
                int i2 = this.itsClassHash.get(slashedForm, -1);
                if (i2 != -1) {
                    this.itsClassHash.put(str, i2);
                }
                String str3 = slashedForm;
                i = i2;
                str2 = str3;
            } else {
                str2 = str;
            }
            if (i == -1) {
                short addUtf8 = addUtf8(str2);
                ensure(3);
                byte[] bArr = this.itsPool;
                int i3 = this.itsTop;
                this.itsTop = i3 + 1;
                bArr[i3] = (byte) 7;
                this.itsTop = ClassFileWriter.putInt16(addUtf8, this.itsPool, this.itsTop);
                i = this.itsTopIndex;
                this.itsTopIndex = i + 1;
                this.itsClassHash.put(str2, i);
                if (str != str2) {
                    this.itsClassHash.put(str, i);
                }
            }
        }
        setConstantData(i, str);
        this.itsPoolTypes.put(i, 7);
        return (short) i;
    }

    short addFieldRef(String str, String str2, String str3) {
        FieldOrMethodRef fieldOrMethodRef = new FieldOrMethodRef(str, str2, str3);
        int i = this.itsFieldRefHash.get(fieldOrMethodRef, -1);
        if (i == -1) {
            short addNameAndType = addNameAndType(str2, str3);
            short addClass = addClass(str);
            ensure(5);
            byte[] bArr = this.itsPool;
            int i2 = this.itsTop;
            this.itsTop = i2 + 1;
            bArr[i2] = (byte) 9;
            this.itsTop = ClassFileWriter.putInt16(addClass, this.itsPool, this.itsTop);
            this.itsTop = ClassFileWriter.putInt16(addNameAndType, this.itsPool, this.itsTop);
            i = this.itsTopIndex;
            this.itsTopIndex = i + 1;
            this.itsFieldRefHash.put(fieldOrMethodRef, i);
        }
        setConstantData(i, fieldOrMethodRef);
        this.itsPoolTypes.put(i, 9);
        return (short) i;
    }

    short addMethodRef(String str, String str2, String str3) {
        FieldOrMethodRef fieldOrMethodRef = new FieldOrMethodRef(str, str2, str3);
        int i = this.itsMethodRefHash.get(fieldOrMethodRef, -1);
        if (i == -1) {
            short addNameAndType = addNameAndType(str2, str3);
            short addClass = addClass(str);
            ensure(5);
            byte[] bArr = this.itsPool;
            int i2 = this.itsTop;
            this.itsTop = i2 + 1;
            bArr[i2] = (byte) 10;
            this.itsTop = ClassFileWriter.putInt16(addClass, this.itsPool, this.itsTop);
            this.itsTop = ClassFileWriter.putInt16(addNameAndType, this.itsPool, this.itsTop);
            i = this.itsTopIndex;
            this.itsTopIndex = i + 1;
            this.itsMethodRefHash.put(fieldOrMethodRef, i);
        }
        setConstantData(i, fieldOrMethodRef);
        this.itsPoolTypes.put(i, 10);
        return (short) i;
    }

    short addInterfaceMethodRef(String str, String str2, String str3) {
        short addNameAndType = addNameAndType(str2, str3);
        short addClass = addClass(str);
        ensure(5);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = (byte) 11;
        this.itsTop = ClassFileWriter.putInt16(addClass, this.itsPool, this.itsTop);
        this.itsTop = ClassFileWriter.putInt16(addNameAndType, this.itsPool, this.itsTop);
        setConstantData(this.itsTopIndex, new FieldOrMethodRef(str, str2, str3));
        this.itsPoolTypes.put(this.itsTopIndex, 11);
        int i2 = this.itsTopIndex;
        this.itsTopIndex = i2 + 1;
        return (short) i2;
    }

    Object getConstantData(int i) {
        return this.itsConstantData.getObject(i);
    }

    void setConstantData(int i, Object obj) {
        this.itsConstantData.put(i, obj);
    }

    byte getConstantType(int i) {
        return (byte) this.itsPoolTypes.getInt(i, 0);
    }

    void ensure(int i) {
        if (this.itsTop + i > this.itsPool.length) {
            int length = this.itsPool.length * 2;
            if (this.itsTop + i > length) {
                length = this.itsTop + i;
            }
            Object obj = new byte[length];
            System.arraycopy(this.itsPool, 0, obj, 0, this.itsTop);
            this.itsPool = obj;
        }
    }
}
