package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.d.j;
import com.umeng.analytics.d.m;
import java.io.Serializable;

public class b {
    public String a;
    public String b;
    private Context c;
    private final String d = "um_g_cache";
    private final String e = "single_level";
    private final String f = "stat_player_level";
    private final String g = "stat_game_level";
    private a h = null;

    static class a implements Serializable {
        private String b;
        private long c;
        private long d;

        public a(String str) {
            this.b = str;
        }

        public boolean a(String str) {
            return this.b.equals(str);
        }

        public void a() {
            this.d = System.currentTimeMillis();
        }

        public void b() {
            this.c += System.currentTimeMillis() - this.d;
            this.d = 0;
        }

        public void c() {
            a();
        }

        public void d() {
            b();
        }

        public long e() {
            return this.c;
        }

        public String f() {
            return this.b;
        }
    }

    public b(Context context) {
        this.c = context;
    }

    public a a(String str) {
        this.h = new a(str);
        this.h.a();
        return this.h;
    }

    public void a() {
        if (this.h != null) {
            this.h.b();
            Editor edit = this.c.getSharedPreferences("um_g_cache", 0).edit();
            edit.putString("single_level", j.a(this.h));
            edit.putString("stat_player_level", this.b);
            edit.putString("stat_game_level", this.a);
            edit.commit();
        }
    }

    public void b() {
        SharedPreferences a = m.a(this.c, "um_g_cache");
        String string = a.getString("single_level", null);
        if (!TextUtils.isEmpty(string)) {
            this.h = (a) j.a(string);
            if (this.h != null) {
                this.h.c();
            }
        }
        if (TextUtils.isEmpty(this.b)) {
            this.b = a.getString("stat_player_level", null);
            if (this.b == null) {
                SharedPreferences a2 = m.a(this.c);
                if (a2 != null) {
                    this.b = a2.getString("userlevel", null);
                } else {
                    return;
                }
            }
        }
        if (this.a == null) {
            this.a = a.getString("stat_game_level", null);
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
