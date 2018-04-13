package mtopsdk.xstate;

import android.content.Context;
import com.ali.auth.third.login.LoginConstants;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;

public final class c {
    private static ConcurrentHashMap a = null;
    private static Context b;
    private static volatile boolean c = false;
    private static Lock d = new ReentrantLock();

    public static String a(String str) {
        return (a == null || str == null) ? null : (String) a.get(str);
    }

    public static void a() {
        if (c) {
            d.lock();
            try {
                if (c) {
                    if (a != null) {
                        a.clear();
                        a = null;
                    }
                    if (b == null) {
                        m.d("mtopsdk.XStateDelegate", "[unInit]static field context in Class XState is null.");
                        return;
                    }
                    c = false;
                    if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                        m.b("mtopsdk.XStateDelegate", "[unInit] uninit XState OK,isInit=" + c);
                    }
                }
                d.unlock();
            } catch (Exception e) {
                m.d("mtopsdk.XStateDelegate", "[unInit] unInit error --" + e.toString());
            } finally {
                d.unlock();
            }
        }
    }

    public static void a(Context context) {
        if (!c) {
            d.lock();
            try {
                if (!c) {
                    if (context == null) {
                        m.d("mtopsdk.XStateDelegate", "[checkInit]parameter context for init(Context context) is null.");
                        return;
                    }
                    if (a == null) {
                        a = new ConcurrentHashMap();
                    }
                    b = context;
                    c = true;
                    if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                        m.b("mtopsdk.XStateDelegate", "[checkInit] init XState OK,isInit=" + c);
                    }
                }
                d.unlock();
            } catch (Throwable th) {
                m.d("mtopsdk.XStateDelegate", "[checkInit] checkInit error --" + th.toString());
            } finally {
                d.unlock();
            }
        }
    }

    public static void a(String str, String str2) {
        if (a != null && str != null && str2 != null) {
            a.put(str, str2);
            if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                m.a("mtopsdk.XStateDelegate", "[setValue]set  XstateID succeed," + str + LoginConstants.EQUAL + str2);
            }
        } else if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a("mtopsdk.XStateDelegate", "[setValue]set  XstateID failed,key=" + str + ",value=" + str2);
        }
    }

    public static String b(String str) {
        if (a == null || str == null) {
            return null;
        }
        if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a("mtopsdk.XStateDelegate", "remove Xstate key=" + str);
        }
        return (String) a.remove(str);
    }
}
