package cn.xiaochuan.push.b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationManagerCompat;
import cn.xiaochuan.base.BaseApplication;
import com.tencent.wcdb.database.SQLiteDatabase;

public class b {
    private static boolean b(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static boolean a(Context context) {
        return b(context);
    }

    public static boolean a() {
        try {
            Context appContext = BaseApplication.getAppContext();
            String packageName = appContext.getPackageName();
            Intent intent = new Intent();
            if (VERSION.SDK_INT < 19) {
                intent.setAction("android.settings.SETTINGS");
            } else if (VERSION.SDK_INT == 19) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("package:" + packageName));
            } else if (VERSION.SDK_INT > 19 && VERSION.SDK_INT < 26) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", packageName);
                intent.putExtra("app_uid", appContext.getApplicationInfo().uid);
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
            } else if (VERSION.SDK_INT >= 26) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
            }
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            appContext.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                BaseApplication.getAppContext().startActivity(new Intent("android.settings.SETTINGS"));
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
    }
}
