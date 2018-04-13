package cn.xiaochuankeji.badge.impl;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import java.util.Collections;
import java.util.List;

public class OPPOHomeBader implements a {
    private int a = -1;

    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        if (this.a != i) {
            this.a = i;
            if (VERSION.SDK_INT >= 11) {
                a(context, i);
            } else {
                b(context, componentName, i);
            }
        }
    }

    public List<String> a() {
        return Collections.singletonList("com.oppo.launcher");
    }

    private void b(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        if (i == 0) {
            i = -1;
        }
        Intent intent = new Intent("com.oppo.unsettledevent");
        intent.putExtra("pakeageName", componentName.getPackageName());
        intent.putExtra("number", i);
        intent.putExtra("upgradeNumber", i);
        if (cn.xiaochuankeji.badge.a.a.a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }

    @TargetApi(11)
    private void a(Context context, int i) throws ShortcutBadgeException {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("app_badge_count", i);
            context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, bundle);
        } catch (Throwable th) {
            ShortcutBadgeException shortcutBadgeException = new ShortcutBadgeException("Unable to execute Badge By Content Provider");
        }
    }
}
