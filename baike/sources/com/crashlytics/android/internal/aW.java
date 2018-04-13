package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import org.json.JSONException;
import org.json.JSONObject;

public class aW {
    private aZ a;
    private final aY b;
    private final ah c;
    private final aN d;
    private final ba e;

    public aW(aZ aZVar, ah ahVar, aY aYVar, aN aNVar, ba baVar) {
        this.a = aZVar;
        this.c = ahVar;
        this.b = aYVar;
        this.d = aNVar;
        this.e = baVar;
    }

    public aX a() {
        return a(aV.USE_CACHE);
    }

    public aX a(aV aVVar) {
        Throwable th;
        aX aXVar;
        Throwable th2;
        aX aXVar2 = null;
        try {
            if (!v.a().f()) {
                aXVar2 = b(aVVar);
            }
            if (aXVar2 == null) {
                try {
                    JSONObject a = this.e.a(this.a);
                    if (a != null) {
                        aXVar2 = this.b.a(this.c, a);
                        this.d.a(aXVar2.f, a);
                        a(a, "Loaded settings: ");
                    }
                } catch (Throwable e) {
                    th = e;
                    aXVar = aXVar2;
                    th2 = th;
                    v.a().b().a(Crashlytics.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return aXVar;
                }
            }
            aXVar = aXVar2;
            if (aXVar == null) {
                try {
                    aXVar = b(aV.IGNORE_CACHE_EXPIRATION);
                } catch (Exception e2) {
                    th2 = e2;
                    v.a().b().a(Crashlytics.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
                    return aXVar;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            aXVar = null;
            th2 = th;
            v.a().b().a(Crashlytics.TAG, "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", th2);
            return aXVar;
        }
        return aXVar;
    }

    private aX b(aV aVVar) {
        Throwable e;
        aX a;
        try {
            if (!aV.SKIP_CACHE_LOOKUP.equals(aVVar)) {
                JSONObject a2 = this.d.a();
                if (a2 != null) {
                    a = this.b.a(this.c, a2);
                    if (a != null) {
                        a(a2, "Loaded cached settings: ");
                        long a3 = this.c.a();
                        if (!aV.IGNORE_CACHE_EXPIRATION.equals(aVVar)) {
                            if ((a.f < a3 ? 1 : null) != null) {
                                v.a().b().a(Crashlytics.TAG, "Cached settings have expired.");
                            }
                        }
                        try {
                            v.a().b().a(Crashlytics.TAG, "Returning cached settings.");
                            return a;
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } else {
                        v.a().b().a(Crashlytics.TAG, "Failed to transform cached settings data.", null);
                        return null;
                    }
                }
                v.a().b().a(Crashlytics.TAG, "No cached settings data found.");
            }
            return null;
        } catch (Throwable e3) {
            Throwable th = e3;
            a = null;
            e = th;
            v.a().b().a(Crashlytics.TAG, "Failed to get cached settings", e);
            return a;
        }
    }

    private void a(JSONObject jSONObject, String str) throws JSONException {
        if (!C0003ab.e(v.a().getContext())) {
            jSONObject = this.b.a(jSONObject);
        }
        v.a().b().a(Crashlytics.TAG, str + jSONObject.toString());
    }
}
