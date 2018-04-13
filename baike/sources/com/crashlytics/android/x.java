package com.crashlytics.android;

import android.content.Context;
import com.crashlytics.android.internal.C0003ab;
import com.crashlytics.android.internal.aQ;

final class x {
    private final Context a;
    private final aQ b;

    public x(Context context, aQ aQVar) {
        this.a = context;
        this.b = aQVar;
    }

    public final String a() {
        return a("com.crashlytics.CrashSubmissionPromptTitle", this.b.a);
    }

    public final String b() {
        return a("com.crashlytics.CrashSubmissionPromptMessage", this.b.b);
    }

    public final String c() {
        return a("com.crashlytics.CrashSubmissionSendTitle", this.b.c);
    }

    public final String d() {
        return a("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.b.g);
    }

    public final String e() {
        return a("com.crashlytics.CrashSubmissionCancelTitle", this.b.e);
    }

    private String a(String str, String str2) {
        String a = C0003ab.a(this.a, str);
        Object obj = (a == null || a.length() == 0) ? 1 : null;
        return obj != null ? str2 : a;
    }
}
