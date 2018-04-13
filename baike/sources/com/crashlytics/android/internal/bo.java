package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import com.qiniu.android.http.Client;
import cz.msebera.android.httpclient.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

final class bo extends Z implements ba {
    public bo(String str, String str2, C0011av c0011av) {
        this(str, str2, c0011av, C0012ax.GET);
    }

    private bo(String str, String str2, C0011av c0011av, C0012ax c0012ax) {
        super(str, str2, c0011av, c0012ax);
    }

    public final JSONObject a(aZ aZVar) {
        Throwable e;
        C0013ay a;
        try {
            Map hashMap = new HashMap();
            hashMap.put("build_version", aZVar.e);
            hashMap.put("display_version", aZVar.d);
            hashMap.put("source", Integer.toString(aZVar.f));
            if (aZVar.g != null) {
                hashMap.put("icon_hash", aZVar.g);
            }
            String str = aZVar.c;
            if (!C0003ab.e(str)) {
                hashMap.put("instance", str);
            }
            try {
                a = a(hashMap).a("X-CRASHLYTICS-API-KEY", aZVar.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-D", aZVar.b).a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()).a(HttpHeaders.ACCEPT, Client.JsonMime);
                v.a().b().a(Crashlytics.TAG, "Requesting settings from " + a());
                v.a().b().a(Crashlytics.TAG, "Settings query params were: " + hashMap);
                JSONObject jSONObject = new JSONObject(a.c());
                if (a == null) {
                    return jSONObject;
                }
                v.a().b().a(Crashlytics.TAG, "Settings request ID: " + a.a("X-REQUEST-ID"));
                return jSONObject;
            } catch (Exception e2) {
                e = e2;
                try {
                    v.a().b().a(Crashlytics.TAG, "Failed to retrieve settings from " + a(), e);
                    if (a != null) {
                        return null;
                    }
                    v.a().b().a(Crashlytics.TAG, "Settings request ID: " + a.a("X-REQUEST-ID"));
                    return null;
                } catch (Throwable th) {
                    e = th;
                    if (a != null) {
                        v.a().b().a(Crashlytics.TAG, "Settings request ID: " + a.a("X-REQUEST-ID"));
                    }
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            a = null;
            v.a().b().a(Crashlytics.TAG, "Failed to retrieve settings from " + a(), e);
            if (a != null) {
                return null;
            }
            v.a().b().a(Crashlytics.TAG, "Settings request ID: " + a.a("X-REQUEST-ID"));
            return null;
        } catch (Throwable th2) {
            e = th2;
            a = null;
            if (a != null) {
                v.a().b().a(Crashlytics.TAG, "Settings request ID: " + a.a("X-REQUEST-ID"));
            }
            throw e;
        }
    }
}
