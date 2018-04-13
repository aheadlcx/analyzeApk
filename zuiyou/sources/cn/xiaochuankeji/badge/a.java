package cn.xiaochuankeji.badge;

import android.content.ComponentName;
import android.content.Context;
import java.util.List;

public interface a {
    List<String> a();

    void a(Context context, ComponentName componentName, int i) throws ShortcutBadgeException;
}
