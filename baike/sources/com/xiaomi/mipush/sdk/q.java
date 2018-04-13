package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.xmpush.thrift.aa;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.ag;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.aj;
import com.xiaomi.xmpush.thrift.al;
import com.xiaomi.xmpush.thrift.an;
import com.xiaomi.xmpush.thrift.ap;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.u;
import com.xiaomi.xmpush.thrift.w;
import com.xiaomi.xmpush.thrift.x;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.a;

public class q {
    private static final byte[] a = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};

    protected static <T extends a<T, ?>> ab a(Context context, T t, com.xiaomi.xmpush.thrift.a aVar) {
        return a(context, t, aVar, !aVar.equals(com.xiaomi.xmpush.thrift.a.Registration), context.getPackageName(), a.a(context).c());
    }

    protected static <T extends a<T, ?>> ab a(Context context, T t, com.xiaomi.xmpush.thrift.a aVar, boolean z, String str, String str2) {
        byte[] a = aq.a(t);
        if (a == null) {
            b.a("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        ab abVar = new ab();
        if (z) {
            String f = a.a(context).f();
            if (TextUtils.isEmpty(f)) {
                b.a("regSecret is empty, return null");
                return null;
            }
            try {
                a = b(com.xiaomi.channel.commonutils.string.a.a(f), a);
            } catch (Exception e) {
                b.d("encryption error. ");
            }
        }
        u uVar = new u();
        uVar.a = 5;
        uVar.b = "fakeid";
        abVar.a(uVar);
        abVar.a(ByteBuffer.wrap(a));
        abVar.a(aVar);
        abVar.c(true);
        abVar.b(str);
        abVar.a(z);
        abVar.a(str2);
        return abVar;
    }

    private static Cipher a(byte[] bArr, int i) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance;
    }

    public static a a(Context context, ab abVar) {
        if (abVar.c()) {
            try {
                byte[] a = a(com.xiaomi.channel.commonutils.string.a.a(a.a(context).f()), abVar.f());
            } catch (Throwable e) {
                throw new c("the aes decrypt failed.", e);
            }
        }
        a = abVar.f();
        a a2 = a(abVar.a(), abVar.c);
        if (a2 != null) {
            aq.a(a2, a);
        }
        return a2;
    }

    private static a a(com.xiaomi.xmpush.thrift.a aVar, boolean z) {
        switch (aVar) {
            case Registration:
                return new ag();
            case UnRegistration:
                return new an();
            case Subscription:
                return new al();
            case UnSubscription:
                return new ap();
            case SendMessage:
                return new aj();
            case AckMessage:
                return new w();
            case SetConfig:
                return new aa();
            case ReportFeedback:
                return new ai();
            case Notification:
                if (z) {
                    return new ae();
                }
                a xVar = new x();
                xVar.a(true);
                return xVar;
            case Command:
                return new aa();
            default:
                return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return a(bArr, 2).doFinal(bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, 1).doFinal(bArr2);
    }
}
