package com.budejie.www.activity.video;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.WindowManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public class a {
    public static String a(Context context) {
        try {
            String formatIpAddress = Formatter.formatIpAddress(((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getDhcpInfo().dns1);
            if (formatIpAddress == null) {
                return "";
            }
            return formatIpAddress;
        } catch (Exception e) {
            return "";
        }
    }

    public static int a(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    public static boolean a() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static int a(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static int b(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
