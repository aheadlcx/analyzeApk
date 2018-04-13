package org.mozilla.classfile;

final class ClassFileMethod {
    private byte[] itsCodeAttribute;
    private short itsFlags;
    private String itsName;
    private short itsNameIndex;
    private String itsType;
    private short itsTypeIndex;

    ClassFileMethod(String str, short s, String str2, short s2, short s3) {
        this.itsName = str;
        this.itsNameIndex = s;
        this.itsType = str2;
        this.itsTypeIndex = s2;
        this.itsFlags = s3;
    }

    void setCodeAttribute(byte[] bArr) {
        this.itsCodeAttribute = bArr;
    }

    int write(byte[] bArr, int i) {
        int putInt16 = ClassFileWriter.putInt16(1, bArr, ClassFileWriter.putInt16(this.itsTypeIndex, bArr, ClassFileWriter.putInt16(this.itsNameIndex, bArr, ClassFileWriter.putInt16(this.itsFlags, bArr, i))));
        System.arraycopy(this.itsCodeAttribute, 0, bArr, putInt16, this.itsCodeAttribute.length);
        return putInt16 + this.itsCodeAttribute.length;
    }

    int getWriteSize() {
        return this.itsCodeAttribute.length + 8;
    }

    String getName() {
        return this.itsName;
    }

    String getType() {
        return this.itsType;
    }

    short getFlags() {
        return this.itsFlags;
    }
}
