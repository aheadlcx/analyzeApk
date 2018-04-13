package com.xiaomi.xmpush.thrift;

import android.content.Context;
import com.xiaomi.channel.commonutils.android.a;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.g;
import com.xiaomi.push.service.bg;
import org.apache.thrift.e;
import org.apache.thrift.f;
import org.apache.thrift.protocol.k;

public class au {
    public static short a(Context context, af afVar) {
        int i = 0;
        int a = (g.a(context) ? 8 : 0) + ((0 + a.c(context, afVar.f).a()) + (g.b(context) ? 4 : 0));
        if (bg.a(context, afVar)) {
            i = 16;
        }
        return (short) (a + i);
    }

    public static short a(boolean z, boolean z2, boolean z3) {
        int i = 0;
        int i2 = (z2 ? 2 : 0) + (0 + (z ? 4 : 0));
        if (z3) {
            i = 1;
        }
        return (short) (i + i2);
    }

    public static <T extends org.apache.thrift.a<T, ?>> void a(T t, byte[] bArr) {
        if (bArr == null) {
            throw new f("the message byte is empty.");
        }
        new e(new k.a(true, true, bArr.length)).a(t, bArr);
    }

    public static <T extends org.apache.thrift.a<T, ?>> byte[] a(T t) {
        byte[] bArr = null;
        if (t != null) {
            try {
                bArr = new org.apache.thrift.g(new org.apache.thrift.protocol.a.a()).a(t);
            } catch (Throwable e) {
                b.a("convertThriftObjectToBytes catch TException.", e);
            }
        }
        return bArr;
    }
}
