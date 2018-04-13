package qsbk.app.core.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import qsbk.app.utils.RemixUtil;

public class PackageUtils {
    public static boolean isPackageInstalled(String str) {
        try {
            if (AppUtils.getInstance().getAppContext().getPackageManager().getPackageInfo(str, 64) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isPackageRemix(Context context) {
        return RemixUtil.REMIX_PACKAGE_NAME.equalsIgnoreCase(context.getPackageName());
    }

    public static boolean isPackageDoll(Context context) {
        return "qsbk.app.doll".equalsIgnoreCase(context.getPackageName());
    }

    public static boolean isPackageLSJXC(Context context) {
        return "qsbk.app.remix.lsjxc".equalsIgnoreCase(context.getPackageName());
    }

    public static boolean isPackageYGBH(Context context) {
        return "qsbk.app.remix.ygbh".equalsIgnoreCase(context.getPackageName());
    }

    public static boolean isPackageFSS(Context context) {
        return "qsbk.app.remix.fss".equalsIgnoreCase(context.getPackageName());
    }
}
