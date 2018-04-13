package cn.xiaochuankeji.tieba.ui.homepage;

import android.content.SharedPreferences.Editor;
import cn.xiaochuankeji.tieba.push.c.i;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class c {
    private boolean a;
    private long b;
    private a c;

    public interface a {
        void a(boolean z);
    }

    private boolean a() {
        return System.currentTimeMillis() - this.b > com.umeng.analytics.a.j;
    }

    private void b(boolean z) {
        if (this.c != null) {
            this.c.a(z);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void message(i iVar) {
        if (this.a) {
            b(false);
            a(false);
        } else if (a()) {
            b(true);
        }
    }

    public static void a(boolean z) {
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putBoolean("TOPIC_UPDATE_FLAG_KEY", z);
        edit.apply();
    }
}
