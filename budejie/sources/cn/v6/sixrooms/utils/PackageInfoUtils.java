package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.List;

public class PackageInfoUtils {
    public static ArrayList<String> getAllAppInfo(Context context) {
        ArrayList<String> arrayList = new ArrayList();
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            arrayList.add(((PackageInfo) installedPackages.get(i)).packageName);
        }
        return arrayList;
    }

    public static boolean isAppInstalled(Context context, String str) {
        if (context != null && getAllAppInfo(context).contains(str)) {
            return true;
        }
        return false;
    }
}
