package com.budejie.www.goddubbing.a;

import com.budejie.www.goddubbing.c.h;
import com.spriteapp.videoedit.spritefilters.SpriteFiltersClass.ProgressListener;

class a$1 implements ProgressListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void sendMessage(String str) {
        a.a(this.a, h.a(str, ((float) (a.a(this.a) * a.b(this.a))) / 1000.0f));
        a.a(this.a, new Void[0]);
    }
}
