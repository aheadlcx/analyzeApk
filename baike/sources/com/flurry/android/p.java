package com.flurry.android;

import com.xiaomi.mipush.sdk.Constants;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

final class p {
    final String a;
    v b;
    long c;
    List d = new ArrayList();
    private byte e;

    p(String str, byte b, long j) {
        this.a = str;
        this.e = b;
        this.d.add(new f((byte) 1, j));
    }

    final void a(f fVar) {
        this.d.add(fVar);
    }

    final long a() {
        return ((f) this.d.get(0)).b;
    }

    final void a(DataOutput dataOutput) {
        dataOutput.writeUTF(this.a);
        dataOutput.writeByte(this.e);
        if (this.b == null) {
            dataOutput.writeLong(0);
            dataOutput.writeLong(0);
            dataOutput.writeByte(0);
        } else {
            dataOutput.writeLong(this.b.a);
            dataOutput.writeLong(this.b.e);
            byte[] bArr = this.b.g;
            dataOutput.writeByte(bArr.length);
            dataOutput.write(bArr);
        }
        dataOutput.writeShort(this.d.size());
        for (f fVar : this.d) {
            dataOutput.writeByte(fVar.a);
            dataOutput.writeLong(fVar.b);
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{hook: " + this.a + ", ad: " + this.b.d + ", transitions: [");
        for (f append : this.d) {
            stringBuilder.append(append);
            stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
