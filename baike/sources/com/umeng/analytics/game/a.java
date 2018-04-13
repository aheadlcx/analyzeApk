package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.pro.s;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.Serializable;

public class a {
    public String a;
    public String b;
    private Context c;
    private final String d = "um_g_cache";
    private final String e = "single_level";
    private final String f = "stat_player_level";
    private final String g = "stat_game_level";
    private a h = null;

    static class a implements Serializable {
        private String a;
        private long b;
        private long c;

        public a(String str) {
            this.a = str;
        }

        public boolean a(String str) {
            return this.a.equals(str);
        }

        public void a() {
            this.c = System.currentTimeMillis();
        }

        public void b() {
            this.b += System.currentTimeMillis() - this.c;
            this.c = 0;
        }

        public void c() {
            a();
        }

        public void d() {
            b();
        }

        public long e() {
            return this.b;
        }

        public String f() {
            return this.a;
        }
    }

    public a(Context context) {
        this.c = context;
    }

    public a a(String str) {
        this.h = new a(str);
        this.h.a();
        return this.h;
    }

    public void a() {
        try {
            if (this.h != null) {
                this.h.b();
                Editor edit = this.c.getSharedPreferences("um_g_cache", 0).edit();
                edit.putString("single_level", s.a(this.h));
                edit.putString("stat_player_level", this.b);
                edit.putString("stat_game_level", this.a);
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public void b() {
        try {
            SharedPreferences instance = PreferenceWrapper.getInstance(this.c, "um_g_cache");
            String string = instance.getString("single_level", null);
            if (!TextUtils.isEmpty(string)) {
                this.h = (a) s.a(string);
                if (this.h != null) {
                    this.h.c();
                }
            }
            if (TextUtils.isEmpty(this.b)) {
                this.b = instance.getString("stat_player_level", null);
                if (this.b == null) {
                    SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.c);
                    if (sharedPreferences != null) {
                        this.b = sharedPreferences.getString("userlevel", null);
                    }
                }
            }
            if (this.a == null) {
                this.a = instance.getString("stat_game_level", null);
            }
        } catch (Throwable th) {
        }
    }

    public a b(String str) {
        if (this.h != null) {
            this.h.d();
            if (this.h.a(str)) {
                a aVar = this.h;
                this.h = null;
                return aVar;
            }
        }
        return null;
    }
}
