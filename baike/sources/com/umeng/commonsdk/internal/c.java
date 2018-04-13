package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.common.e;
import org.json.JSONObject;

public class c implements UMLogDataProtocol {
    private Context a;

    public c(Context context) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
    }

    public void workEvent(Object obj, int i) {
        e.a("walle", "[internal] workEvent");
        Class cls;
        String str;
        switch (i) {
            case a.e /*32769*/:
                try {
                    e.a("walle", "[internal] workEvent send envelope");
                    try {
                        cls = Class.forName("com.umeng.commonsdk.internal.UMInternalManagerAgent");
                        if (cls != null) {
                            cls.getMethod("sendInternalEnvelopeByStateful2", new Class[]{Context.class}).invoke(cls, new Object[]{this.a});
                            return;
                        }
                        return;
                    } catch (ClassNotFoundException e) {
                        return;
                    } catch (Throwable th) {
                        return;
                    }
                } catch (Exception e2) {
                    return;
                }
            case a.g /*32771*/:
                e.a("walle", "[internal] workEvent cache battery, event is " + obj.toString());
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (cls != null) {
                        str = (String) obj;
                        cls.getMethod("saveBattery", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{this.a, str});
                        return;
                    }
                    return;
                } catch (ClassNotFoundException e3) {
                    return;
                } catch (Throwable th2) {
                    return;
                }
            case a.h /*32772*/:
                e.a("walle", "[internal] workEvent cache station, event is " + obj.toString());
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                    if (cls != null) {
                        str = (String) obj;
                        cls.getMethod("saveBaseStationStrength", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{this.a, str});
                        return;
                    }
                    return;
                } catch (ClassNotFoundException e4) {
                    return;
                } catch (Throwable th3) {
                    return;
                }
            case a.i /*32773*/:
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (cls != null) {
                        cls.getMethod("saveBluetoothInfo", new Class[]{Context.class, Object.class}).invoke(cls, new Object[]{this.a, obj});
                        return;
                    }
                    return;
                } catch (ClassNotFoundException e5) {
                    return;
                } catch (Throwable th4) {
                    return;
                }
            case a.j /*32774*/:
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent");
                    if (cls != null) {
                        cls.getMethod("wifiChange", new Class[]{Context.class}).invoke(cls, new Object[]{this.a});
                        return;
                    }
                    return;
                } catch (ClassNotFoundException e6) {
                    return;
                } catch (Throwable th5) {
                    return;
                }
            case a.k /*32775*/:
                try {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                    if (cls != null) {
                        str = (String) obj;
                        cls.getMethod("saveUA", new Class[]{Context.class, String.class}).invoke(cls, new Object[]{this.a, str});
                        return;
                    }
                    return;
                } catch (ClassNotFoundException e7) {
                    return;
                } catch (Throwable th6) {
                    return;
                }
            case a.l /*32776*/:
                SharedPreferences sharedPreferences = this.a.getApplicationContext().getSharedPreferences("info", 0);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString("stat", (String) obj).commit();
                    return;
                }
                return;
            case a.m /*32777*/:
                try {
                    e.a("walle", "[internal] workEvent send envelope");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(g.au, a.d);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(g.ak, new JSONObject());
                    jSONObject = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.a, jSONObject, jSONObject2);
                    if (jSONObject != null && !jSONObject.has(b.ao)) {
                        e.a("walle", "[internal] workEvent send envelope back, result is ok");
                        return;
                    }
                    return;
                } catch (Exception e8) {
                    return;
                }
            default:
                return;
        }
    }

    public void removeCacheData(Object obj) {
    }

    public JSONObject setupReportData(long j) {
        return null;
    }
}
