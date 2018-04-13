package com.budejie.www.goddubbing;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ProgressBar;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.d;
import com.zxt.download2.b;
import com.zxt.download2.f;
import com.zxt.download2.g;

public class a implements b {
    private ProgressBar a;
    private Context b;
    private ListItemObject c;
    private a d;
    private f e;

    public interface a {
        void a();
    }

    public a(Context context, ProgressBar progressBar, ListItemObject listItemObject) {
        this.b = context;
        this.a = progressBar;
        this.c = listItemObject;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(String str) {
        if (this.d != null) {
            this.d.a();
        }
    }

    public void a() {
        this.d = null;
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void a(int i, int i2, int i3) {
        this.a.setProgress((int) ((((long) i) * 100) / ((long) i2)));
    }

    public void f() {
        String str = "DownloadVideoName.mp4";
        String f = d.f();
        String str2 = "";
        Object a = com.lt.a.a(this.b).a(this.c.getVideouri());
        if (TextUtils.isEmpty(a)) {
            a = com.lt.a.a(this.b).a(this.c.getDownloadVideoUri());
        }
        if (!TextUtils.isEmpty(a)) {
            this.e = new f(a, f, str, str2, null);
            g.a(this.b).a(this.e, (b) this);
            g.a(this.b).a(this.e);
        }
    }

    public void g() {
        if (this.e != null) {
            g.a(this.b).a(this.e, true);
            g.a(this.b).h(this.e);
            g.a(this.b).e(this.e);
            d.c(d.g());
        }
    }
}
