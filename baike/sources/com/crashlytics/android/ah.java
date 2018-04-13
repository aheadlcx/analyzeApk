package com.crashlytics.android;

import android.util.Log;
import com.crashlytics.android.internal.C0003ab;
import com.crashlytics.android.internal.v;

final class ah {
    private String a;
    private boolean b;

    public ah(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public final void a(String str, String str2) {
        if (C0003ab.e(this.a) && this.b) {
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, ".     |  | ");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".   \\ |  | /");
            Log.e(Crashlytics.TAG, ".    \\    /");
            Log.e(Crashlytics.TAG, ".     \\  /");
            Log.e(Crashlytics.TAG, ".      \\/");
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, "This app relies on Crashlytics. Configure your build environment here: ");
            Log.e(Crashlytics.TAG, String.format("https://crashlytics.com/register/%s/android/%s", new Object[]{str, str2}));
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, ".      /\\");
            Log.e(Crashlytics.TAG, ".     /  \\");
            Log.e(Crashlytics.TAG, ".    /    \\");
            Log.e(Crashlytics.TAG, ".   / |  | \\");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".");
            throw new CrashlyticsMissingDependencyException(str, str2);
        } else if (!this.b) {
            v.a().b().a(Crashlytics.TAG, "Configured not to require a build ID.");
        }
    }
}
