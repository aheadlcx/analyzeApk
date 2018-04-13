package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.crashlytics.android.Crashlytics;

public class r implements q {
    private static boolean b(int i) {
        return cl.a.g() <= i;
    }

    public static String a(Context context, boolean z) {
        String string;
        int a;
        try {
            Context applicationContext = context.getApplicationContext();
            Bundle bundle = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData;
            if (bundle != null) {
                string = bundle.getString("com.crashlytics.ApiKey");
                if (C0003ab.e(string)) {
                    a = C0003ab.a(context, "com.crashlytics.ApiKey", "string");
                    if (a != 0) {
                        string = context.getResources().getString(a);
                    }
                }
                if (C0003ab.e(string)) {
                    if (!z || C0003ab.f(context)) {
                        throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
                    }
                    cl.a.b().a(Crashlytics.TAG, "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>", null);
                }
                return string;
            }
        } catch (Exception e) {
            cl.a.b().a(Crashlytics.TAG, "Caught non-fatal exception while retrieving apiKey: " + e);
        }
        string = null;
        if (C0003ab.e(string)) {
            a = C0003ab.a(context, "com.crashlytics.ApiKey", "string");
            if (a != 0) {
                string = context.getResources().getString(a);
            }
        }
        if (C0003ab.e(string)) {
            if (z) {
            }
            throw new IllegalArgumentException("Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>");
        }
        return string;
    }

    public static int a(int i) {
        if (i >= 200 && i <= 299) {
            return 0;
        }
        if (i >= 300 && i <= 399) {
            return 1;
        }
        if (i >= 400 && i <= 499) {
            return 0;
        }
        if (i >= 500) {
            return 1;
        }
        return 1;
    }

    public final void a(String str, String str2, Throwable th) {
        if (b(6)) {
            Log.e(str, str2, th);
        }
    }

    public final void a(String str, String str2) {
        if (b(3)) {
            Log.d(str, str2, null);
        }
    }

    public final void b(String str, String str2) {
        if (b(4)) {
            Log.i(str, str2, null);
        }
    }

    public final void c(String str, String str2) {
        if (b(5)) {
            Log.w(str, str2, null);
        }
    }

    public final void d(String str, String str2) {
        a(str, str2, null);
    }

    public final void a(int i, String str, String str2) {
        a(i, str, str2, false);
    }

    public final void a(int i, String str, String str2, boolean z) {
        if (z || b(i)) {
            Log.println(i, str, str2);
        }
    }

    static /* synthetic */ Activity a(Context context) {
        return context instanceof Activity ? (Activity) context : null;
    }

    static /* synthetic */ Application b(Context context) {
        if (context instanceof Application) {
            return (Application) context;
        }
        if (context instanceof Activity) {
            return ((Activity) context).getApplication();
        }
        if (context instanceof Service) {
            return ((Service) context).getApplication();
        }
        return context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
    }
}
