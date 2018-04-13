package com.budejie.www.activity.posts;

import com.budejie.www.R;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;

class a$14 implements a {
    final /* synthetic */ a a;

    a$14(a aVar) {
        this.a = aVar;
    }

    public void a() {
        if (an.a(a.b(this.a))) {
            if (!XListView.a) {
                XListView.a = true;
            }
            a.l(this.a);
            return;
        }
        a.m(this.a).postDelayed(new Runnable(this) {
            final /* synthetic */ a$14 a;

            {
                this.a = r1;
            }

            public void run() {
                if (XListView.a) {
                    XListView.a = false;
                }
                an.a(a.b(this.a.a), a.b(this.a.a).getString(R.string.nonet), -1).show();
                a.i(this.a.a).b();
            }
        }, 200);
    }

    public void b() {
        if (an.a(a.b(this.a))) {
            if (!XListView.a) {
                XListView.a = true;
            }
            this.a.e();
            return;
        }
        a.m(this.a).postDelayed(new Runnable(this) {
            final /* synthetic */ a$14 a;

            {
                this.a = r1;
            }

            public void run() {
                if (XListView.a) {
                    XListView.a = false;
                }
                an.a(a.b(this.a.a), a.b(this.a.a).getString(R.string.nonet), -1).show();
                a.i(this.a.a).c();
            }
        }, 200);
    }
}
