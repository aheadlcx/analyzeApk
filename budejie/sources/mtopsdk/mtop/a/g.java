package mtopsdk.mtop.a;

import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.util.e;
import mtopsdk.xstate.a;

public class g {
    private static f a = f.a();

    public static long a() {
        return b() + (System.currentTimeMillis() / 1000);
    }

    public static void a(String str) {
        if (str != null) {
            a.b(str);
        }
    }

    public static void a(String str, String str2) {
        a.a("sid", str);
        a.a(HistoryOpenHelper.COLUMN_UID, str2);
        e.a(new h());
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            StringBuilder stringBuilder = new StringBuilder("[registerSessionInfo]register sessionInfo succeed");
            stringBuilder.append(",sid=").append(str);
            stringBuilder.append(",uid=").append(str2);
            m.b("mtopsdk.SDKUtils", stringBuilder.toString());
        }
    }

    public static long b() {
        String c = a.c();
        long j = 0;
        if (l.a(c)) {
            try {
                j = Long.parseLong(c);
            } catch (NumberFormatException e) {
                m.d("mtopsdk.SDKUtils", "[getTimeOffset]parse t_offset failed");
            }
        } else {
            a.a("t_offset", "0");
        }
        return j;
    }

    public static void c() {
        a.b("sid");
        a.b(HistoryOpenHelper.COLUMN_UID);
        m.b("mtopsdk.SDKUtils", "[logOut] remove sessionInfo succeed.");
    }
}
