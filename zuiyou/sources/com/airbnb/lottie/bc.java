package com.airbnb.lottie;

import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class bc extends d {
    @Nullable
    private bb c;
    @Nullable
    private bb d;

    bc(bd bdVar, @Nullable ay ayVar, @Nullable ShapeStroke shapeStroke, @Nullable be beVar, j jVar, Callback callback) {
        super(callback);
        a(jVar.f());
        if (ayVar != null) {
            this.c = new bb(getCallback());
            this.c.d(bdVar.a().b());
            this.c.c(ayVar.a().a());
            this.c.d(ayVar.b().a());
            this.c.e(jVar.e().a());
            this.c.g(jVar.c().a());
            if (beVar != null) {
                this.c.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(this.c);
        }
        if (shapeStroke != null) {
            this.d = new bb(getCallback());
            this.d.d();
            this.d.d(bdVar.a().b());
            this.d.c(shapeStroke.a().a());
            this.d.d(shapeStroke.b().a());
            this.d.e(jVar.e().a());
            this.d.f(shapeStroke.c().a());
            if (!shapeStroke.d().isEmpty()) {
                List arrayList = new ArrayList(shapeStroke.d().size());
                for (b a : shapeStroke.d()) {
                    arrayList.add(a.a());
                }
                this.d.a(arrayList, shapeStroke.e().a());
            }
            this.d.a(shapeStroke.f());
            this.d.a(shapeStroke.g());
            this.d.g(jVar.c().a());
            if (beVar != null) {
                this.d.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(this.d);
        }
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        if (this.c != null) {
            this.c.setAlpha(i);
        }
        if (this.d != null) {
            this.d.setAlpha(i);
        }
    }
}
