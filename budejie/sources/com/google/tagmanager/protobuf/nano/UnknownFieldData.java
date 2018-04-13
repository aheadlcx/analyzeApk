package com.google.tagmanager.protobuf.nano;

import java.util.Arrays;

public final class UnknownFieldData {
    final byte[] bytes;
    final int tag;

    UnknownFieldData(int i, byte[] bArr) {
        this.tag = i;
        this.bytes = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UnknownFieldData)) {
            return false;
        }
        UnknownFieldData unknownFieldData = (UnknownFieldData) obj;
        if (this.tag == unknownFieldData.tag && Arrays.equals(this.bytes, unknownFieldData.bytes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.tag + 527;
        for (byte b : this.bytes) {
            i = (i * 31) + b;
        }
        return i;
    }
}
