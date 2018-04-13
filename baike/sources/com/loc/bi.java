package com.loc;

import android.text.TextUtils;
import java.net.URLConnection;
import java.util.Map;

public final class bi {
    private static bi a;

    public interface a {
        URLConnection a();
    }

    public static bi a() {
        if (a == null) {
            a = new bi();
        }
        return a;
    }

    public static bo a(bn bnVar, boolean z) throws j {
        j e;
        if (bnVar == null) {
            try {
                throw new j("requeust is null");
            } catch (j e2) {
                throw e2;
            } catch (Throwable th) {
                th.printStackTrace();
                e2 = new j("未知的错误");
            }
        } else if (bnVar.c() == null || "".equals(bnVar.c())) {
            throw new j("request url is empty");
        } else {
            String c;
            bl blVar = new bl(bnVar.c, bnVar.d, bnVar.e == null ? null : bnVar.e, z);
            byte[] d = bnVar.d();
            if (d == null || d.length == 0) {
                c = bnVar.c();
            } else {
                Map b_ = bnVar.b_();
                if (b_ == null) {
                    c = bnVar.c();
                } else {
                    String a = bl.a(b_);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(bnVar.c()).append("?").append(a);
                    c = stringBuffer.toString();
                }
            }
            Map b = bnVar.b();
            d = bnVar.d();
            if (d == null || d.length == 0) {
                String a2 = bl.a(bnVar.b_());
                if (!TextUtils.isEmpty(a2)) {
                    d = t.a(a2);
                }
            }
            return blVar.a(c, b, d);
        }
    }

    public static byte[] a(bn bnVar) throws j {
        j e;
        try {
            bo a = a(bnVar, false);
            return a != null ? a.a : null;
        } catch (j e2) {
            throw e2;
        } catch (Throwable th) {
            w.a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new j("未知的错误");
        }
    }
}
