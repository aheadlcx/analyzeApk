package com.crashlytics.android;

import com.crashlytics.android.internal.C0011av;
import com.crashlytics.android.internal.C0012ax;
import com.crashlytics.android.internal.C0013ay;
import com.crashlytics.android.internal.Z;
import com.crashlytics.android.internal.r;
import com.crashlytics.android.internal.v;
import java.util.Map.Entry;

final class w extends Z implements v {
    public w(String str, String str2, C0011av c0011av) {
        super(str, str2, c0011av, C0012ax.POST);
    }

    public final boolean a(u uVar) {
        C0013ay a = b().a("X-CRASHLYTICS-API-KEY", uVar.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", Crashlytics.getInstance().getVersion());
        C0013ay c0013ay = a;
        for (Entry a2 : uVar.b.e().entrySet()) {
            c0013ay = c0013ay.a(a2);
        }
        z zVar = uVar.b;
        a = c0013ay.a("report[file]", zVar.b(), "application/octet-stream", zVar.d()).b("report[identifier]", zVar.c());
        v.a().b().a(Crashlytics.TAG, "Sending report to: " + a());
        int b = a.b();
        v.a().b().a(Crashlytics.TAG, "Create report request ID: " + a.a("X-REQUEST-ID"));
        v.a().b().a(Crashlytics.TAG, "Result was: " + b);
        return r.a(b) == 0;
    }
}
