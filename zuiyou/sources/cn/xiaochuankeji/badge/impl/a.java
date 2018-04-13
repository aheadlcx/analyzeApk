package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import java.util.Arrays;
import java.util.List;

public class a implements cn.xiaochuankeji.badge.a {
    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i);
        intent.putExtra("badge_count_package_name", componentName.getPackageName());
        intent.putExtra("badge_count_class_name", componentName.getClassName());
        intent.putExtra("badge_vip_count", 0);
        if (cn.xiaochuankeji.badge.a.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"com.asus.launcher"});
    }
}
