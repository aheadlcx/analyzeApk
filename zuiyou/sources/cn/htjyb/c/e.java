package cn.htjyb.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.izuiyou.a.a.b;
import java.io.File;
import java.io.IOException;

public class e {
    public static String a(Context context) {
        String str = "unknown";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (d.d(packageInfo.versionName)) {
                return str;
            }
            return packageInfo.versionName;
        } catch (Exception e) {
            b.e(e.toString());
            return str;
        }
    }

    public static void a(String str) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        File file = new File(str + ".nomedia");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
    }
}
