package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.c;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.xmpush.thrift.aa;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.am;
import com.xiaomi.xmpush.thrift.an;
import com.xiaomi.xmpush.thrift.ap;
import com.xiaomi.xmpush.thrift.ar;
import com.xiaomi.xmpush.thrift.at;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.x;
import com.xiaomi.xmpush.thrift.z;
import java.nio.ByteBuffer;
import org.apache.thrift.a;

public class aq {
    protected static <T extends a<T, ?>> af a(Context context, T t, com.xiaomi.xmpush.thrift.a aVar) {
        return a(context, t, aVar, !aVar.equals(com.xiaomi.xmpush.thrift.a.Registration), context.getPackageName(), c.a(context).c());
    }

    protected static <T extends a<T, ?>> af a(Context context, T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, String str, String str2) {
        byte[] a = au.a(t);
        if (a == null) {
            b.a("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        af afVar = new af();
        if (z) {
            String f = c.a(context).f();
            if (TextUtils.isEmpty(f)) {
                b.a("regSecret is empty, return null");
                return null;
            }
            try {
                a = c.b(com.xiaomi.channel.commonutils.string.a.a(f), a);
            } catch (Exception e) {
                b.d("encryption error. ");
            }
        }
        x xVar = new x();
        xVar.a = 5;
        xVar.b = "fakeid";
        afVar.a(xVar);
        afVar.a(ByteBuffer.wrap(a));
        afVar.a(aVar);
        afVar.c(true);
        afVar.b(str);
        afVar.a(z);
        afVar.a(str2);
        return afVar;
    }

    public static a a(Context context, af afVar) {
        if (afVar.c()) {
            try {
                byte[] a = c.a(com.xiaomi.channel.commonutils.string.a.a(c.a(context).f()), afVar.f());
            } catch (Throwable e) {
                throw new f("the aes decrypt failed.", e);
            }
        }
        a = afVar.f();
        a a2 = a(afVar.a(), afVar.c);
        if (a2 != null) {
            au.a(a2, a);
        }
        return a2;
    }

    private static a a(com.xiaomi.xmpush.thrift.a aVar, boolean z) {
        switch (y.a[aVar.ordinal()]) {
            case 1:
                return new ak();
            case 2:
                return new ar();
            case 3:
                return new ap();
            case 4:
                return new at();
            case 5:
                return new an();
            case 6:
                return new z();
            case 7:
                return new ae();
            case 8:
                return new am();
            case 9:
                if (z) {
                    return new ai();
                }
                a aaVar = new aa();
                aaVar.a(true);
                return aaVar;
            case 10:
                return new ae();
            default:
                return null;
        }
    }
}
