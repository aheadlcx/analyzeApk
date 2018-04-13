package qsbk.app.utils;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import qsbk.app.QsbkApp;
import qsbk.app.utils.comm.SharedPreferencesCache;

public class AppContext {
    private static Application a;
    private static SharedPreferencesCache b;

    public static Application getContext() {
        return a;
    }

    public static void setAppContext(Application application) {
        a = application;
    }

    public static SharedPreferencesCache getSharedPrefernce() {
        if (b == null) {
            b = new SharedPreferencesCache(a, a.getPackageName());
        }
        return b;
    }

    public static LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getContext().getSystemService("layout_inflater");
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public void updateChannelFromConfig() {
    }

    public PackageInfo getPackageInfo() {
        PackageInfo packageInfo;
        try {
            packageInfo = QsbkApp.getInstance().getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
            packageInfo = null;
        }
        if (packageInfo == null) {
            return new PackageInfo();
        }
        return packageInfo;
    }
}
