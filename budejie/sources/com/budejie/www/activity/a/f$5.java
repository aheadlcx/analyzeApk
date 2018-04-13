package com.budejie.www.activity.a;

import com.budejie.www.R;
import com.budejie.www.activity.mycomment.f;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.util.an;
import java.util.Collection;
import net.tsz.afinal.a.a;

class f$5 extends a<String> {
    final /* synthetic */ f a;

    f$5(f fVar) {
        this.a = fVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void a(String str) {
        f.h(this.a).c();
        ListInfo a = com.budejie.www.j.a.a(str);
        if (a.np == 0) {
            if (f.g(this.a).b() != 6) {
                f.h(this.a).setPullLoadEnable(false);
            }
            f.g(this.a).a(false);
            if (f.d(this.a).f()) {
                an.a(f.d(this.a), f.d(this.a).getString(R.string.mycomment_list_no_moredata), -1).show();
                return;
            } else {
                an.a(f.d(this.a), f.d(this.a).getString(R.string.no_more_data), -1).show();
                return;
            }
        }
        f.c(this.a, String.valueOf(a.np));
        f f = com.budejie.www.j.a.f(f.d(this.a), str);
        if (f != null) {
            Collection collection = f.a;
            if (!(collection == null || collection.isEmpty())) {
                f.a(this.a, collection);
                an.b(collection, f.b(this.a), f.c(this.a));
                f.f(this.a).b(collection);
                this.a.a.addAll(collection);
                if (f.g(this.a).b() != 6) {
                    f.h(this.a).setPullLoadEnable(true);
                }
                f.g(this.a).a(true);
            }
        }
        f.g(this.a).b(2);
    }

    public void onFailure(Throwable th, int i, String str) {
        f.h(this.a).c();
        f.g(this.a).b(2);
        an.a(f.d(this.a), f.d(this.a).getString(R.string.parse_failed), -1).show();
    }
}
