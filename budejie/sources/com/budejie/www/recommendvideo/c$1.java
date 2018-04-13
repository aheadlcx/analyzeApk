package com.budejie.www.recommendvideo;

import com.budejie.www.activity.video.f$e;
import com.budejie.www.activity.video.k;

class c$1 implements f$e {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void a() {
        k.a(this.a.b).l();
    }

    public void b() {
        if (this.a.e < this.a.f - 1 && c.a(this.a) != null) {
            c.a(this.a).a(this.a.e + 1);
        }
    }
}
