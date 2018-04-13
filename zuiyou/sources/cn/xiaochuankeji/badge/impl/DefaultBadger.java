package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import java.util.Arrays;
import java.util.List;

public class DefaultBadger implements a {
    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i);
        intent.putExtra("badge_count_package_name", componentName.getPackageName());
        intent.putExtra("badge_count_class_name", componentName.getClassName());
        if (cn.xiaochuankeji.badge.a.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"fr.neamar.kiss", "com.quaap.launchtime", "com.quaap.launchtime_official"});
    }

    boolean a(Context context) {
        return cn.xiaochuankeji.badge.a.a.a(context, new Intent("android.intent.action.BADGE_COUNT_UPDATE"));
    }
}
