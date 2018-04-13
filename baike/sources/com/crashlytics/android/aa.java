package com.crashlytics.android;

import com.crashlytics.android.internal.C0003ab;
import com.crashlytics.android.internal.C0011av;
import com.crashlytics.android.internal.C0012ax;
import com.crashlytics.android.internal.C0013ay;
import com.crashlytics.android.internal.Z;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.io.Closeable;
import java.io.InputStream;

abstract class aa extends Z {
    public aa(String str, String str2, C0011av c0011av, C0012ax c0012ax) {
        super(str, str2, c0011av, c0012ax);
    }

    public final boolean a(ag agVar) {
        C0013ay a = a(b().a("X-CRASHLYTICS-API-KEY", agVar.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()), agVar);
        v.a().b().a(Crashlytics.TAG, "Sending app info to " + a());
        if (agVar.j != null) {
            v.a().b().a(Crashlytics.TAG, "App icon hash is " + agVar.j.a);
            v.a().b().a(Crashlytics.TAG, "App icon size is " + agVar.j.c + "x" + agVar.j.d);
        }
        int b = a.b();
        v.a().b().a(Crashlytics.TAG, ("POST".equals(a.d()) ? "Create" : "Update") + " app request ID: " + a.a("X-REQUEST-ID"));
        v.a().b().a(Crashlytics.TAG, "Result was " + b);
        if (r.a(b) == 0) {
            return true;
        }
        return false;
    }

    private static C0013ay a(C0013ay c0013ay, ag agVar) {
        C0013ay b = c0013ay.b("app[identifier]", agVar.b).b("app[name]", agVar.f).b("app[display_version]", agVar.c).b("app[build_version]", agVar.d).a("app[source]", Integer.valueOf(agVar.g)).b("app[minimum_sdk_version]", agVar.h).b("app[built_sdk_version]", agVar.i);
        if (!C0003ab.e(agVar.e)) {
            b.b("app[instance_identifier]", agVar.e);
        }
        if (agVar.j != null) {
            Closeable closeable = null;
            try {
                closeable = v.a().getContext().getResources().openRawResource(agVar.j.b);
                b.b("app[icon][hash]", agVar.j.a).a("app[icon][data]", "icon.png", "application/octet-stream", (InputStream) closeable).a("app[icon][width]", Integer.valueOf(agVar.j.c)).a("app[icon][height]", Integer.valueOf(agVar.j.d));
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, "Failed to find app icon with resource ID: " + agVar.j.b, e);
            } finally {
                String str = "Failed to close app icon InputStream.";
                C0003ab.a(closeable, str);
            }
        }
        return b;
    }
}
