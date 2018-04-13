package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import java.util.Arrays;
import java.util.List;

public class AdwHomeBadger implements a {
    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Intent intent = new Intent("org.adw.launcher.counter.SEND");
        intent.putExtra("PNAME", componentName.getPackageName());
        intent.putExtra("CNAME", componentName.getClassName());
        intent.putExtra("COUNT", i);
        if (cn.xiaochuankeji.badge.a.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"org.adw.launcher", "org.adwfreak.launcher"});
    }
}
