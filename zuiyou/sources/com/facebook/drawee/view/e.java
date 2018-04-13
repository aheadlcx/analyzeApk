package com.facebook.drawee.view;

import android.graphics.drawable.Drawable;
import com.facebook.common.internal.g;
import com.facebook.drawee.d.b;
import java.util.ArrayList;

public class e<DH extends b> {
    boolean a = false;
    ArrayList<b<DH>> b = new ArrayList();

    public void a() {
        if (!this.a) {
            this.a = true;
            for (int i = 0; i < this.b.size(); i++) {
                ((b) this.b.get(i)).b();
            }
        }
    }

    public void b() {
        if (this.a) {
            this.a = false;
            for (int i = 0; i < this.b.size(); i++) {
                ((b) this.b.get(i)).c();
            }
        }
    }

    public void a(b<DH> bVar) {
        a(this.b.size(), bVar);
    }

    public void a(int i, b<DH> bVar) {
        g.a((Object) bVar);
        g.a(i, this.b.size() + 1);
        this.b.add(i, bVar);
        if (this.a) {
            bVar.b();
        }
    }

    public b<DH> a(int i) {
        return (b) this.b.get(i);
    }

    public int c() {
        return this.b.size();
    }

    public boolean a(Drawable drawable) {
        for (int i = 0; i < this.b.size(); i++) {
            if (drawable == a(i).f()) {
                return true;
            }
        }
        return false;
    }
}
