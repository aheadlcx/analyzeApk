package com.budejie.www.activity.video;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.budejie.www.activity.video.f.d;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.budejie.www.http.j;

class k$3 implements d {
    final /* synthetic */ f a;
    final /* synthetic */ int b;
    final /* synthetic */ ListItemObject c;
    final /* synthetic */ k d;

    k$3(k kVar, f fVar, int i, ListItemObject listItemObject) {
        this.d = kVar;
        this.a = fVar;
        this.b = i;
        this.c = listItemObject;
    }

    public void a() {
        try {
            if (this.d.f != null) {
                this.d.f.e();
            }
            if (this.d.f != null && this.d.f.n && this.d.f.o) {
                k.a(this.d, 0);
                k.b(this.d).a(k.e(this.d));
                this.d.n();
                this.d.f.n();
            } else if (k.e(this.d) != 0) {
                k.b(this.d).a(k.e(this.d));
                this.d.n();
                this.a.h();
            } else {
                String str;
                if (this.b == 0 && this.d.f != null && !this.d.f.a.isShown() && k.f(this.d).getBoolean("barrage_status", true)) {
                    this.a.v();
                }
                if (TextUtils.isEmpty(this.c.getPlaycount())) {
                    str = "0";
                } else {
                    str = this.c.getPlaycount();
                }
                this.c.setPlaycount(String.valueOf(Integer.parseInt(str) + 1));
                this.a.a(Integer.valueOf(0));
                long currentTimeMillis = System.currentTimeMillis() - k.g(this.d);
                this.a.a(this.c.getVideouri(), currentTimeMillis + "");
                i.a(k.a(this.d, 1, this.c.getVideouri()), j.a(this.c), j.b(k.a(this.d), this.c), currentTimeMillis);
                if (!this.c.isIs_ad()) {
                    return;
                }
                if (this.c.getAdItem() != null) {
                    this.c.getAdItem().onPlay(this.a);
                } else {
                    i.b(k.a(this.d), this.c.getAd_id());
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void a(int i) {
        if (this.d.f != null) {
            this.d.f.a(i);
        }
    }

    @SuppressLint({"NewApi"})
    public void b() {
        if (!(this.d.b == null || !this.d.b.a || this.d.f == null)) {
            this.d.f.j();
        }
        if (k.b(this.d) != null) {
            k.b(this.d).setKeepScreenOn(false);
        }
        if (this.b > 0) {
            if (this.b != 4 || k.h(this.d) == null) {
                this.d.r();
            } else {
                k.h(this.d).b();
            }
        } else if (this.d.f != null) {
            this.d.f.n();
        }
    }

    public void a(boolean z) {
        if (z) {
            this.d.t();
        } else {
            k.a(this.d, false);
        }
    }

    public void c() {
        if (this.d.b != null && this.d.b.a) {
            this.d.h();
        }
    }
}
