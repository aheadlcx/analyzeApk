package com.google.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

class InstallReferrerUtil {
    static final String INTENT_EXTRA_REFERRER = "referrer";
    static final String PREF_KEY_REFERRER = "referrer";
    static final String PREF_NAME_CLICK_REFERRERS = "gtm_click_referrers";
    static final String PREF_NAME_INSTALL_REFERRER = "gtm_install_referrer";
    @VisibleForTesting
    static Map<String, String> clickReferrers = new HashMap();
    private static String installReferrer;

    InstallReferrerUtil() {
    }

    static void cacheInstallReferrer(String str) {
        synchronized (InstallReferrerUtil.class) {
            installReferrer = str;
        }
    }

    static void saveInstallReferrer(Context context, String str) {
        SharedPreferencesUtil.saveAsync(context, PREF_NAME_INSTALL_REFERRER, "referrer", str);
        addClickReferrer(context, str);
    }

    static String getInstallReferrer(Context context, String str) {
        if (installReferrer == null) {
            synchronized (InstallReferrerUtil.class) {
                if (installReferrer == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_INSTALL_REFERRER, 0);
                    if (sharedPreferences != null) {
                        installReferrer = sharedPreferences.getString("referrer", "");
                    } else {
                        installReferrer = "";
                    }
                }
            }
        }
        return extractComponent(installReferrer, str);
    }

    static void addClickReferrer(Context context, String str) {
        String extractComponent = extractComponent(str, "conv");
        if (extractComponent != null && extractComponent.length() > 0) {
            clickReferrers.put(extractComponent, str);
            SharedPreferencesUtil.saveAsync(context, PREF_NAME_CLICK_REFERRERS, extractComponent, str);
        }
    }

    static String extractComponent(String str, String str2) {
        if (str2 != null) {
            return Uri.parse("http://hostname/?" + str).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    static String getClickReferrer(Context context, String str, String str2) {
        String str3 = (String) clickReferrers.get(str);
        if (str3 == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_CLICK_REFERRERS, 0);
            if (sharedPreferences != null) {
                str3 = sharedPreferences.getString(str, "");
            } else {
                str3 = "";
            }
            clickReferrers.put(str, str3);
        }
        return extractComponent(str3, str2);
    }
}
