package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressLint({"DefaultLocale"})
public class e {
    public static Boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return Boolean.valueOf(false);
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
}
