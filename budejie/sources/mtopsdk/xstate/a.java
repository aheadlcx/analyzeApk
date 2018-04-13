package mtopsdk.xstate;

import android.content.Context;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.ut.device.UTDevice;
import java.util.HashMap;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;

public final class a {
    private static final HashMap a = new HashMap();
    private static mtopsdk.common.util.a b;

    public static String a() {
        return a("sid");
    }

    public static String a(String str) {
        String str2;
        if (b == null || b.b() == null) {
            if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                m.c("mtopsdk.XState", "[getValue]Attention :User XState Local Mode : key=" + str);
            }
            synchronized (a) {
                str2 = (String) a.get(str);
            }
        } else {
            try {
                str2 = ((mtopsdk.xstate.a.a) b.b()).a(str);
            } catch (Exception e) {
                if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                    m.d("mtopsdk.XState", "[getValue] getValue by key=" + str + " error ---" + e.toString());
                    m.c("mtopsdk.XState", "[getValue]Attention :User XState Local Mode : key=" + str);
                }
                synchronized (a) {
                    str2 = (String) a.get(str);
                }
            }
        }
        return str2;
    }

    public static void a(Context context) {
        if (context == null) {
            m.d("mtopsdk.XState", "[init]init() error,context is null");
            return;
        }
        try {
            a.put(Constants.UA, mtopsdk.xstate.b.a.a(context));
            a.put("pv", "1.0");
            a.put("t_offset", "0");
            a.put("utdid", UTDevice.getUtdid(context));
        } catch (Throwable th) {
            m.b("mtopsdk.XState", "[initPhoneInfo]initPhoneInfo error", th);
        }
        if (b == null) {
            mtopsdk.common.util.a bVar = new b(mtopsdk.xstate.a.a.class, d.class);
            b = bVar;
            bVar.a(context);
            return;
        }
        d();
    }

    public static void a(String str, String str2) {
        if (b == null || b.b() == null) {
            if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                m.b("mtopsdk.XState", "[setValue]Attention :User XState Local Mode: key:" + str + " value:" + str2);
            }
            synchronized (a) {
                a.put(str, str2);
            }
            return;
        }
        try {
            ((mtopsdk.xstate.a.a) b.b()).a(str, str2);
        } catch (Exception e) {
            if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                m.d("mtopsdk.XState", "[setValue] setValue failed ,key=" + str + ",value=" + str2 + "; ---" + e.toString());
                m.c("mtopsdk.XState", "[setValue]Attention :User XState Local Mode: key:" + str + " value:" + str2);
            }
            synchronized (a) {
                a.put(str, str2);
            }
        }
    }

    public static String b() {
        return a(HistoryOpenHelper.COLUMN_UID);
    }

    public static String b(String str) {
        if (b == null || b.b() == null) {
            if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                m.c("mtopsdk.XState", "[removeKey]Attention :User XState Local Mode : key=" + str);
            }
            synchronized (a) {
                a.remove(str);
            }
        } else {
            try {
                return ((mtopsdk.xstate.a.a) b.b()).b(str);
            } catch (Exception e) {
                if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                    m.d("mtopsdk.XState", "[removeKey] removeKey by key=" + str + " error ---" + e.toString());
                    m.c("mtopsdk.XState", "[removeKey]Attention :User XState Local Mode : key=" + str);
                }
                synchronized (a) {
                    a.remove(str);
                }
            }
        }
        return null;
    }

    public static String c() {
        return a("t_offset");
    }

    protected static void d() {
        if (b != null && b.b() != null) {
            try {
                ((mtopsdk.xstate.a.a) b.b()).a();
                synchronized (a) {
                    for (String str : a.keySet()) {
                        a(str, (String) a.get(str));
                    }
                    a.clear();
                }
            } catch (Throwable th) {
                m.b("mtopsdk.XState", "[syncToRemote]service.init() error", th);
            }
        }
    }
}
