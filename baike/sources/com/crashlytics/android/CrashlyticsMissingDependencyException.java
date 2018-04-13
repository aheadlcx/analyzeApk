package com.crashlytics.android;

import com.crashlytics.android.internal.v;
import java.net.URLEncoder;

public class CrashlyticsMissingDependencyException extends RuntimeException {
    CrashlyticsMissingDependencyException(String str, String str2) {
        super(a(str, str2));
    }

    private static String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\nThis app relies on Crashlytics. Configure your build environment here: \n");
            stringBuilder.append(String.format("https://crashlytics.com/register/%s/android/%s", new Object[]{URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8")}) + "\n");
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Could not find UTF-8 encoding.", e);
        }
        return stringBuilder.toString();
    }
}
