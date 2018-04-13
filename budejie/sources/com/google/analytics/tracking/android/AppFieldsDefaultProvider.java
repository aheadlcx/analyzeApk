package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.util.VisibleForTesting;

class AppFieldsDefaultProvider implements DefaultProvider {
    private static AppFieldsDefaultProvider sInstance;
    private static Object sInstanceLock = new Object();
    protected String mAppId;
    protected String mAppInstallerId;
    protected String mAppName;
    protected String mAppVersion;

    public static void initializeProvider(Context context) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new AppFieldsDefaultProvider(context);
            }
        }
    }

    @VisibleForTesting
    static void dropInstance() {
        synchronized (sInstanceLock) {
            sInstance = null;
        }
    }

    public static AppFieldsDefaultProvider getProvider() {
        return sInstance;
    }

    private AppFieldsDefaultProvider(Context context) {
        PackageManager packageManager = context.getPackageManager();
        this.mAppId = context.getPackageName();
        this.mAppInstallerId = packageManager.getInstallerPackageName(this.mAppId);
        String str = this.mAppId;
        String str2 = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                str = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                str2 = packageInfo.versionName;
            }
        } catch (NameNotFoundException e) {
            Log.e("Error retrieving package info: appName set to " + str);
        }
        this.mAppName = str;
        this.mAppVersion = str2;
    }

    @VisibleForTesting
    protected AppFieldsDefaultProvider() {
    }

    public boolean providesField(String str) {
        return Fields.APP_NAME.equals(str) || Fields.APP_VERSION.equals(str) || Fields.APP_ID.equals(str) || Fields.APP_INSTALLER_ID.equals(str);
    }

    public String getValue(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals(Fields.APP_NAME)) {
            return this.mAppName;
        }
        if (str.equals(Fields.APP_VERSION)) {
            return this.mAppVersion;
        }
        if (str.equals(Fields.APP_ID)) {
            return this.mAppId;
        }
        if (str.equals(Fields.APP_INSTALLER_ID)) {
            return this.mAppInstallerId;
        }
        return null;
    }
}
