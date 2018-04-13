package com.budejie.www.activity.video;

import com.budejie.www.activity.video.f.d;
import com.budejie.www.bean.ListItemObject;

class k$4 implements d {
    final /* synthetic */ ListItemObject a;
    final /* synthetic */ f b;
    final /* synthetic */ k c;

    k$4(k kVar, ListItemObject listItemObject, f fVar) {
        this.c = kVar;
        this.a = listItemObject;
        this.b = fVar;
    }

    public void a() {
        if (this.a.isIs_ad() && this.a.getAdItem() != null) {
            this.a.getAdItem().onPlay(this.b);
        }
    }

    public void a(int i) {
    }

    public void b() {
    }

    public void a(boolean z) {
    }

    public void c() {
    }
}
