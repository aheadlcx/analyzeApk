package mtopsdk.mtop.antiattack;

import java.util.concurrent.ConcurrentHashMap;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.i;

public class c {
    private static ConcurrentHashMap a = new ConcurrentHashMap();

    private static long a(String str) {
        long a = i.a().a(str);
        if (a > 0) {
            return a;
        }
        a = i.a().c();
        return a <= 0 ? 10 : a;
    }

    private static String a(long j, f fVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", currentTime=").append(j);
        stringBuilder.append(", lockentity=" + fVar.toString());
        return stringBuilder.toString();
    }

    public static boolean a(String str, long j) {
        boolean z = false;
        if (!l.b(str)) {
            f fVar = (f) a.get(str);
            if (fVar != null) {
                if (Math.abs(j - fVar.b) < fVar.c) {
                    z = true;
                } else {
                    a.remove(str);
                    if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                        m.c("mtopsdk.ApiLockHelper", "[unLock]apiKey=" + str);
                    }
                }
                if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                    m.c("mtopsdk.ApiLockHelper", "[iSApiLocked] isLocked=" + z + ", " + a(j, fVar));
                }
            }
        }
        return z;
    }

    public static void b(String str, long j) {
        if (!l.b(str)) {
            f fVar = (f) a.get(str);
            if (fVar == null) {
                fVar = new f(str, j, a(str));
            } else {
                fVar.b = j;
                fVar.c = a(str);
            }
            a.put(str, fVar);
            if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                m.c("mtopsdk.ApiLockHelper", "[lock]" + a(j, fVar));
            }
        }
    }
}
