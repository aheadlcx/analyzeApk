package cn.shuzilm.core;

import android.content.Context;
import com.umeng.analytics.pro.b;
import org.json.JSONObject;

public class Main {
    private static Main a = new Main();
    private static JSONObject b = null;
    private static Object c = null;

    static {
        try {
            System.loadLibrary(b.R);
        } catch (UnsatisfiedLinkError e) {
        }
    }

    Main() {
        b = new JSONObject();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void go(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
        r1 = cn.shuzilm.core.Main.class;
        monitor-enter(r1);
        r0 = b;	 Catch:{ all -> 0x0018 }
        if (r0 != 0) goto L_0x001b;
    L_0x0007:
        r0 = new org.json.JSONObject;	 Catch:{ all -> 0x0018 }
        r0.<init>();	 Catch:{ all -> 0x0018 }
        b = r0;	 Catch:{ all -> 0x0018 }
        r0 = b;	 Catch:{ all -> 0x0018 }
        if (r0 != 0) goto L_0x001b;
    L_0x0012:
        r0 = new java.lang.NullPointerException;	 Catch:{ all -> 0x0018 }
        r0.<init>();	 Catch:{ all -> 0x0018 }
        throw r0;	 Catch:{ all -> 0x0018 }
    L_0x0018:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x001b:
        r0 = b;	 Catch:{ UnsatisfiedLinkError -> 0x0037, Exception -> 0x0035 }
        r2 = "custom";
        r0.put(r2, r5);	 Catch:{ UnsatisfiedLinkError -> 0x0037, Exception -> 0x0035 }
        r0 = "du";
        java.lang.System.loadLibrary(r0);	 Catch:{ UnsatisfiedLinkError -> 0x0039, Exception -> 0x0035 }
    L_0x0027:
        setContext(r3);	 Catch:{ UnsatisfiedLinkError -> 0x0037, Exception -> 0x0035 }
        r0 = b;	 Catch:{ UnsatisfiedLinkError -> 0x0037, Exception -> 0x0035 }
        r0 = r0.toString();	 Catch:{ UnsatisfiedLinkError -> 0x0037, Exception -> 0x0035 }
        run(r3, r4, r0);	 Catch:{ UnsatisfiedLinkError -> 0x0037, Exception -> 0x0035 }
    L_0x0033:
        monitor-exit(r1);
        return;
    L_0x0035:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0037:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0039:
        r0 = move-exception;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.shuzilm.core.Main.go(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static native void run(Context context, String str, String str2);

    private static native void setContext(Context context);

    public static synchronized int setData(String str, String str2) {
        int i;
        synchronized (Main.class) {
            i = 0;
            if (b == null) {
                b = new JSONObject();
                if (b == null) {
                    throw new NullPointerException();
                }
            }
            try {
                b.put(str, str2);
            } catch (Exception e) {
                i = -1;
            }
        }
        return i;
    }
}
