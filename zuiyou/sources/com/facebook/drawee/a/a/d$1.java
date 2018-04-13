package com.facebook.drawee.a.a;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.i;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.d;

class d$1 implements a {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public boolean a(c cVar) {
        return true;
    }

    public Drawable b(c cVar) {
        if (cVar instanceof d) {
            d dVar = (d) cVar;
            Drawable bitmapDrawable = new BitmapDrawable(d.a(this.a), dVar.f());
            if (dVar.h() == 0 || dVar.h() == -1) {
                return bitmapDrawable;
            }
            return new i(bitmapDrawable, dVar.h());
        } else if (d.b(this.a) != null) {
            return d.b(this.a).a(cVar);
        } else {
            return null;
        }
    }
}
