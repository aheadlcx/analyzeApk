package cn.xiaochuankeji.tieba.background.post;

import android.content.SharedPreferences;
import cn.xiaochuankeji.tieba.background.a;

public class b {
    private static b a;
    private SharedPreferences b = a.a();
    private boolean c;

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public boolean a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.b.getLong(str + "_refresh_time", currentTimeMillis);
        if (cn.xiaochuankeji.tieba.d.a.c() == cn.xiaochuankeji.tieba.d.a.e) {
            return b(j, currentTimeMillis);
        }
        return a(j, currentTimeMillis);
    }

    public boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        return b(this.b.getLong("key_refresh_tale_time", currentTimeMillis), currentTimeMillis);
    }

    private boolean a(long j, long j2) {
        if (j2 >= j && (j2 - j) / com.umeng.analytics.a.j >= 24) {
            return true;
        }
        return false;
    }

    private boolean b(long j, long j2) {
        if (j2 >= j && (j2 - j) / 1000 >= ((long) cn.xiaochuankeji.tieba.background.utils.c.a.c().y())) {
            return true;
        }
        return false;
    }

    public void a(final long j, final String str) {
        a.p().b().execute(new Runnable(this) {
            final /* synthetic */ b c;

            public void run() {
                this.c.b.edit().putLong(str, j).apply();
            }
        });
    }

    public void a(final long j) {
        a.p().b().execute(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                this.b.b.edit().putLong("key_refresh_ugcvideo_time", j).apply();
            }
        });
    }

    public void b(final long j) {
        a.p().b().execute(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                this.b.b.edit().putLong("key_refresh_tale_time", j).apply();
            }
        });
    }

    public boolean c() {
        long currentTimeMillis = System.currentTimeMillis();
        return a(this.b.getLong("key_refresh_ugcvideo_time", currentTimeMillis), currentTimeMillis);
    }

    public void c(final long j) {
        a.p().b().execute(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                this.b.b.edit().putLong("key_refresh_attri_time", j).apply();
            }
        });
    }

    public boolean d() {
        long currentTimeMillis = System.currentTimeMillis();
        return a(this.b.getLong("key_refresh_attri_time", currentTimeMillis), currentTimeMillis);
    }

    public boolean e() {
        long currentTimeMillis = System.currentTimeMillis();
        return a(this.b.getLong("key_refresh_follow_time", currentTimeMillis), currentTimeMillis);
    }

    public boolean f() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
