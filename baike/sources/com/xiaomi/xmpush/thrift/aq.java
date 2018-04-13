package com.xiaomi.xmpush.thrift;

import android.content.Context;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.misc.e;
import com.xiaomi.push.service.ay;
import org.apache.thrift.a;
import org.apache.thrift.f;
import org.apache.thrift.g;
import org.apache.thrift.protocol.k;

public class aq {
    public static short a(Context context, ab abVar) {
        int i = 0;
        int a = (e.a(context) ? 8 : 0) + ((0 + b.d(context, abVar.f).a()) + (e.b(context) ? 4 : 0));
        if (ay.a(context, abVar)) {
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

    public static <T extends a<T, ?>> void a(T t, byte[] bArr) {
        if (bArr == null) {
            throw new f("the message byte is empty.");
        }
        new org.apache.thrift.e(new k.a(true, true, bArr.length)).a(t, bArr);
    }

    public static <T extends a<T, ?>> byte[] a(T t) {
        byte[] bArr = null;
        if (t != null) {
            try {
                bArr = new g(new org.apache.thrift.protocol.a.a()).a(t);
            } catch (Throwable e) {
                com.xiaomi.channel.commonutils.logger.b.a("convertThriftObjectToBytes catch TException.", e);
            }
        }
        return bArr;
    }
}
