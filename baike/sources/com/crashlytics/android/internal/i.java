package com.crashlytics.android.internal;

import java.io.File;
import java.util.List;

final class i extends Z implements y {
    public i(String str, String str2, C0011av c0011av) {
        super(str, str2, c0011av, C0012ax.POST);
    }

    public final boolean a(String str, List<File> list) {
        C0013ay a = b().a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", v.a().getVersion()).a("X-CRASHLYTICS-API-KEY", str);
        int i = 0;
        for (File file : list) {
            C0003ab.c("Adding analytics session file " + file.getName() + " to multipart POST");
            a.a("session_analytics_file_" + i, file.getName(), "application/vnd.crashlytics.android.events", file);
            i++;
        }
        C0003ab.c("Sending " + list.size() + " analytics files to " + a());
        int b = a.b();
        C0003ab.c("Response code for analytics file send is " + b);
        if (r.a(b) == 0) {
            return true;
        }
        return false;
    }
}
