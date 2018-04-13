package com.sina.weibo.sdk.a;

import android.content.Context;
import com.umeng.update.UpdateConfig;

public class f {
    public static boolean a(Context context) {
        if (context == null || context.checkCallingOrSelfPermission(UpdateConfig.h) == 0) {
            return true;
        }
        return false;
    }
}
