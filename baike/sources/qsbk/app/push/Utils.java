package qsbk.app.push;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class Utils {
    public static final String ACTION_RESPONSE = "bccsclient.action.RESPONSE";
    public static final String ACTION_SHOW_MESSAGE = "bccsclient.action.SHOW_MESSAGE";
    public static final String EXTRA_MESSAGE = "message";
    public static final String RESPONSE_CONTENT = "content";
    public static final String RESPONSE_ERRCODE = "errcode";
    public static final String RESPONSE_METHOD = "method";

    public static String getMetaValue(Context context, String str) {
        String str2 = null;
        if (!(context == null || str == null)) {
            try {
                Bundle bundle;
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null) {
                    bundle = applicationInfo.metaData;
                } else {
                    bundle = null;
                }
                if (bundle != null) {
                    str2 = bundle.getString(str);
                }
            } catch (NameNotFoundException e) {
            }
        }
        return str2;
    }
}
