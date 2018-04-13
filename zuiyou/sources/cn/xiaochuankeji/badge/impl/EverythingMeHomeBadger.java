package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import com.umeng.analytics.b.g;
import java.util.Arrays;
import java.util.List;

public class EverythingMeHomeBadger implements a {
    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(g.e, componentName.getPackageName());
        contentValues.put("activity_name", componentName.getClassName());
        contentValues.put("count", Integer.valueOf(i));
        context.getContentResolver().insert(Uri.parse("content://me.everything.badger/apps"), contentValues);
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"me.everything.launcher"});
    }
}
