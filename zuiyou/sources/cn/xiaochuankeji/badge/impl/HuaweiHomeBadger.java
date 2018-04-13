package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import java.util.Arrays;
import java.util.List;

public class HuaweiHomeBadger implements a {
    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Bundle bundle = new Bundle();
        bundle.putString(EnvConsts.PACKAGE_MANAGER_SRVNAME, context.getPackageName());
        bundle.putString("class", componentName.getClassName());
        bundle.putInt("badgenumber", i);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"com.huawei.android.launcher"});
    }
}
