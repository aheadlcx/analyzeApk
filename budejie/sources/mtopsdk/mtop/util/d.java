package mtopsdk.mtop.util;

import java.util.Arrays;
import java.util.List;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a;
import mtopsdk.mtop.common.e;
import mtopsdk.mtop.common.f;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.common.o;

public class d {
    private static final List a = Arrays.asList(new String[]{"mtop.common.gettimestamp$*"});

    public static List a() {
        return a;
    }

    public static o a(a aVar) {
        Throwable th;
        o oVar = null;
        if (aVar == null || aVar.f() == null) {
            return null;
        }
        try {
            o oVar2 = new o(aVar);
            try {
                k f = aVar.f();
                if (f instanceof e) {
                    oVar2.a = (e) f;
                }
                if (f instanceof f) {
                    oVar2.b = (f) f;
                }
                return oVar2;
            } catch (Throwable th2) {
                th = th2;
                oVar = oVar2;
                m.a("mtopsdk.MtopProxyUtils", "[convertCallbackListener] convert NetworkListenerAdapter error. apiKey=" + aVar.d().getKey(), th);
                return oVar;
            }
        } catch (Throwable th3) {
            th = th3;
            m.a("mtopsdk.MtopProxyUtils", "[convertCallbackListener] convert NetworkListenerAdapter error. apiKey=" + aVar.d().getKey(), th);
            return oVar;
        }
    }
}
