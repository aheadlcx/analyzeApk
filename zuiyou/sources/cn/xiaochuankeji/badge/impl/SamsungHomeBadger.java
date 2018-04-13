package cn.xiaochuankeji.badge.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import cn.xiaochuankeji.badge.ShortcutBadgeException;
import cn.xiaochuankeji.badge.a;
import cn.xiaochuankeji.badge.a.b;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import java.util.Arrays;
import java.util.List;

public class SamsungHomeBadger implements a {
    private static final String[] a = new String[]{"_id", "class"};
    private DefaultBadger b;

    public SamsungHomeBadger() {
        if (VERSION.SDK_INT >= 21) {
            this.b = new DefaultBadger();
        }
    }

    public void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Throwable th;
        Cursor cursor;
        if (this.b == null || !this.b.a(context)) {
            Uri parse = Uri.parse("content://com.sec.badge/apps?notify=true");
            ContentResolver contentResolver = context.getContentResolver();
            try {
                Cursor query = contentResolver.query(parse, a, "package=?", new String[]{componentName.getPackageName()}, null);
                if (query != null) {
                    try {
                        String className = componentName.getClassName();
                        Object obj = null;
                        while (query.moveToNext()) {
                            int i2 = query.getInt(0);
                            contentResolver.update(parse, a(componentName, i, false), "_id=?", new String[]{String.valueOf(i2)});
                            if (className.equals(query.getString(query.getColumnIndex("class")))) {
                                obj = 1;
                            }
                        }
                        if (obj == null) {
                            contentResolver.insert(parse, a(componentName, i, true));
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        b.a(cursor);
                        throw th;
                    }
                }
                b.a(query);
                return;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                b.a(cursor);
                throw th;
            }
        }
        this.b.a(context, componentName, i);
    }

    private ContentValues a(ComponentName componentName, int i, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(EnvConsts.PACKAGE_MANAGER_SRVNAME, componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(i));
        return contentValues;
    }

    public List<String> a() {
        return Arrays.asList(new String[]{"com.sec.android.app.launcher", "com.sec.android.app.twlauncher"});
    }
}
