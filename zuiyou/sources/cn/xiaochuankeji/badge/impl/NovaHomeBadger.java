package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import com.iflytek.aiui.AIUIConstant;
import java.util.Arrays;
import java.util.List;

public class NovaHomeBadger implements a {
    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AIUIConstant.KEY_TAG, componentName.getPackageName() + "/" + componentName.getClassName());
        contentValues.put("count", Integer.valueOf(i));
        context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"com.teslacoilsw.launcher"});
    }
}
