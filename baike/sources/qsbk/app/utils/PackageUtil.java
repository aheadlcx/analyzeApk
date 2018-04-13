package qsbk.app.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import qsbk.app.QsbkApp;

public class PackageUtil {
    public static PackageInfo getPacageInfo(String str) {
        try {
            return QsbkApp.mContext.getPackageManager().getPackageInfo(str, 64);
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}
