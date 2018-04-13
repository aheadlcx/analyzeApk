package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType;
import com.umeng.commonsdk.proguard.b;
import java.util.Iterator;
import org.json.JSONObject;

public class e {
    private static HandlerThread a = null;
    private static Handler b = null;
    private static g c = null;
    private static Object d = new Object();

    public static void a(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        if (context == null || uMLogDataProtocol == null) {
            com.umeng.commonsdk.statistics.common.e.b("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        c.a(context.getApplicationContext());
        if (c.a(i, uMLogDataProtocol)) {
            if (a == null || b == null) {
                e();
            }
            try {
                if (b != null) {
                    if (c == null) {
                        synchronized (d) {
                            b.f(context);
                            c = new g(context, b);
                        }
                    }
                    Message obtainMessage = b.obtainMessage();
                    obtainMessage.what = 768;
                    obtainMessage.arg1 = i;
                    obtainMessage.obj = obj;
                    b.sendMessage(obtainMessage);
                }
            } catch (Throwable th) {
                b.a(c.a(), th);
            }
        }
    }

    private e() {
    }

    private static void d() {
        com.umeng.commonsdk.statistics.common.e.b("--->>> autoProcess Enter...");
        Context a = c.a();
        if (a != null) {
            long maxDataSpace = UMEnvelopeBuild.maxDataSpace(a);
            UMLogDataProtocol a2 = c.a("analytics");
            try {
                JSONObject jSONObject;
                JSONObject jSONObject2;
                int i;
                if (!UMEnvelopeBuild.isReadyBuild(a, UMBusinessType.U_DPLUS) || a2 == null) {
                    i = 0;
                    jSONObject = null;
                } else {
                    jSONObject2 = a2.setupReportData(maxDataSpace);
                    if (jSONObject2 != null) {
                        i = jSONObject2.toString().getBytes().length;
                        jSONObject = jSONObject2;
                    } else {
                        i = 0;
                        jSONObject = jSONObject2;
                    }
                }
                if (jSONObject != null) {
                    JSONObject jSONObject3;
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("header", new JSONObject());
                    jSONObject2.put("content", new JSONObject());
                    if (jSONObject == null || r2 <= 0) {
                        jSONObject3 = jSONObject2;
                    } else {
                        jSONObject3 = a(a(jSONObject2, jSONObject.optJSONObject("header"), "header"), jSONObject.optJSONObject("content"), "content");
                    }
                    if (jSONObject3 != null && UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, jSONObject3.optJSONObject("header"), jSONObject3.optJSONObject("content")) != null && jSONObject != null) {
                        a2.removeCacheData(jSONObject);
                    }
                }
            } catch (Throwable th) {
                b.a(a, th);
            }
        }
    }

    private static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        Context a = c.a();
        if (!(jSONObject == null || jSONObject2 == null)) {
            try {
                if (jSONObject.opt(str) != null && (jSONObject.opt(str) instanceof JSONObject)) {
                    JSONObject jSONObject3 = (JSONObject) jSONObject.opt(str);
                    Iterator keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        Object next = keys.next();
                        if (next != null && (next instanceof String)) {
                            String str2 = (String) next;
                            if (!(str2 == null || jSONObject2.opt(str2) == null)) {
                                jSONObject3.put(str2, jSONObject2.opt(str2));
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                b.a(a, e);
            } catch (Throwable th) {
                b.a(a, th);
            }
        }
        return jSONObject;
    }

    private static synchronized void e() {
        synchronized (e.class) {
            com.umeng.commonsdk.statistics.common.e.b("--->>> Dispatch: init Enter...");
            try {
                if (a == null) {
                    a = new HandlerThread("work_thread");
                    a.start();
                    if (b == null) {
                        b = new l(a.getLooper());
                    }
                }
            } catch (Throwable th) {
                b.a(c.a(), th);
            }
            com.umeng.commonsdk.statistics.common.e.b("--->>> Dispatch: init Exit...");
        }
    }

    private static void b(Message message) {
        int i = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol a = c.a(c.a(i));
        if (a != null) {
            com.umeng.commonsdk.statistics.common.e.b("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i) + "]");
            a.workEvent(obj, i);
        }
    }

    private static void f() {
        if (a != null) {
            a = null;
        }
        if (b != null) {
            b = null;
        }
        if (c != null) {
            c = null;
        }
    }

    private static void g() {
        if (c != null && a != null) {
            g.a();
            com.umeng.commonsdk.statistics.common.e.b("--->>> handleQuit: Quit dispatch thread.");
            a.quit();
            f();
        }
    }

    public static void a() {
        if (b != null) {
            Message obtainMessage = b.obtainMessage();
            obtainMessage.what = 770;
            b.sendMessage(obtainMessage);
        }
    }
}
