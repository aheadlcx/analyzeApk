package qsbk.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;

public class TipsManager {
    public static final String SHOW_SECURITY_BIND = "show_security_bind";
    private static HashMap<String, Integer> a = new HashMap();

    private TipsManager() {
    }

    public static boolean isBindOneOfQQWXWB() {
        if (QsbkApp.currentUser != null && a(QsbkApp.currentUser.qq) && a(QsbkApp.currentUser.wx) && a(QsbkApp.currentUser.wb)) {
            return false;
        }
        return true;
    }

    public static boolean shouldShowSecurityBind(Context context) {
        if (context == null || QsbkApp.currentUser == null) {
            return false;
        }
        String str = QsbkApp.currentUser.userId;
        Integer num = (Integer) a.get(str);
        if (num != null) {
            boolean z = num.intValue() == 2 || !QsbkApp.currentUser.hasPhone();
            return z;
        }
        num = Integer.valueOf(b(context).getInt(a(), 0));
        a.put(str, num);
        if (num.intValue() == 2 || !QsbkApp.currentUser.hasPhone()) {
            return true;
        }
        return false;
    }

    private static SharedPreferences b(Context context) {
        return context.getSharedPreferences("tips_manager", 0);
    }

    private static String a() {
        return "show_bind_tips" + QsbkApp.currentUser.userId;
    }

    public static void setShowedSecurityBind(Context context) {
        b(context, false);
    }

    private static void b(Context context, boolean z) {
        if (context != null && QsbkApp.currentUser != null) {
            Integer valueOf = Integer.valueOf(z ? 2 : 1);
            a.put(QsbkApp.currentUser.userId, valueOf);
            b(context).edit().putInt(a(), valueOf.intValue()).apply();
        }
    }

    public static void checkMyInfo(Context context) {
        if (context != null && QsbkApp.currentUser != null) {
            shouldShowSecurityBind(context);
            Integer num = (Integer) a.get(QsbkApp.currentUser.userId);
            if ((num.intValue() == 1 ? 1 : 0) == 0) {
                int i;
                if (num.intValue() == 2) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i != 0) {
                    c(context);
                } else {
                    new SimpleHttpTask(Constants.MY_INFO, new be(context)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
            }
        }
    }

    private static void c(Context context) {
        if (context != null) {
            Intent intent = new Intent(SHOW_SECURITY_BIND);
            intent.putExtra(SHOW_SECURITY_BIND, true);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    private static boolean a(String str) {
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }

    public static void onDestroy() {
        a.clear();
    }
}
