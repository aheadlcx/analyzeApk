package cn.xiaochuankeji.badge.a;

import android.content.Context;
import android.content.Intent;
import java.util.List;

public class a {
    public static boolean a(Context context, Intent intent) {
        List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return false;
        }
        return true;
    }
}
