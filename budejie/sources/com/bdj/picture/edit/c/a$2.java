package com.bdj.picture.edit.c;

import com.bdj.picture.edit.bean.f;
import com.bdj.picture.edit.e.b;
import com.bdj.picture.edit.widget.d;
import com.bdj.picture.edit.widget.d.a;

class a$2 implements b {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void a(f fVar, String str) {
        a.a(this.a, fVar);
        if (a.a(this.a) == null) {
            a.a(this.a, new d(this.a.getActivity()));
            a.a(this.a).a(new a(this) {
                final /* synthetic */ a$2 a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    this.a.a.a(a.b(this.a.a), str);
                }
            });
        }
        a.a(this.a).a(str);
    }
}
