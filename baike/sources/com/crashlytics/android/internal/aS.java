package com.crashlytics.android.internal;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.umeng.commonsdk.proguard.g;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import org.json.JSONObject;

public final class aS {
    private final AtomicReference<aX> a;
    private aW b;
    private boolean c;

    public static aS a() {
        return bp.a;
    }

    private aS() {
        this.a = new AtomicReference();
        this.c = false;
    }

    public final synchronized aS a(Context context, C0011av c0011av, String str, String str2, String str3) {
        aS aSVar;
        if (this.c) {
            aSVar = this;
        } else {
            if (this.b == null) {
                String a = r.a(context, false);
                String packageName = context.getPackageName();
                String installerPackageName = context.getPackageManager().getInstallerPackageName(packageName);
                ah ahVar = new ah();
                aY aYVar = new aY();
                aN aNVar = new aN();
                String g = C0003ab.g(context);
                String str4 = str3;
                installerPackageName = str2;
                String str5 = str;
                this.b = new aW(new aZ(a, a(a, packageName, context), C0003ab.a(C0003ab.i(context)), installerPackageName, str5, ai.a(installerPackageName).a(), g), ahVar, aYVar, aNVar, new bo(str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{packageName}), c0011av));
            }
            this.c = true;
            aSVar = this;
        }
        return aSVar;
    }

    public final aX b() {
        return (aX) this.a.get();
    }

    public final <T> T a(aU<T> aUVar, T t) {
        aX aXVar = (aX) this.a.get();
        return aXVar == null ? t : aUVar.a(aXVar);
    }

    public final synchronized boolean c() {
        aX a;
        a = this.b.a();
        this.a.set(a);
        return a != null;
    }

    public final synchronized boolean d() {
        aX a;
        a = this.b.a(aV.SKIP_CACHE_LOOKUP);
        this.a.set(a);
        if (a == null) {
            v.a().b().a(Crashlytics.TAG, "Failed to force reload of settings from Crashlytics.", null);
        }
        return a != null;
    }

    private static String a(String str, String str2, Context context) {
        try {
            Cipher b = C0003ab.b(1, C0003ab.a(str + str2.replaceAll("\\.", new StringBuffer(new String(new char[]{'s', 'l', 'c'})).reverse().toString())));
            JSONObject jSONObject = new JSONObject();
            ao aoVar = new ao(context);
            try {
                jSONObject.put("APPLICATION_INSTALLATION_UUID".toLowerCase(Locale.US), aoVar.b());
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Could not write application id to JSON", e);
            }
            for (Entry entry : aoVar.f().entrySet()) {
                try {
                    jSONObject.put(((C0009ap) entry.getKey()).name().toLowerCase(Locale.US), entry.getValue());
                } catch (Throwable e2) {
                    v.a().b().a(Crashlytics.TAG, "Could not write value to JSON: " + ((C0009ap) entry.getKey()).name(), e2);
                }
            }
            try {
                jSONObject.put(g.x, aoVar.c());
            } catch (Throwable e3) {
                v.a().b().a(Crashlytics.TAG, "Could not write OS version to JSON", e3);
            }
            try {
                jSONObject.put("model", aoVar.d());
            } catch (Throwable e32) {
                v.a().b().a(Crashlytics.TAG, "Could not write model to JSON", e32);
            }
            String str3 = "";
            if (jSONObject.length() <= 0) {
                return str3;
            }
            try {
                return C0003ab.a(b.doFinal(jSONObject.toString().getBytes()));
            } catch (Throwable e22) {
                v.a().b().a(Crashlytics.TAG, "Could not encrypt IDs", e22);
                return str3;
            }
        } catch (Throwable e322) {
            v.a().b().a(Crashlytics.TAG, "Could not create cipher to encrypt headers.", e322);
            return "";
        }
    }
}
