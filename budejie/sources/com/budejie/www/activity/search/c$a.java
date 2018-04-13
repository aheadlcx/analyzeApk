package com.budejie.www.activity.search;

import com.budejie.www.R;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import java.util.List;
import net.tsz.afinal.a.a;

class c$a extends a<String> {
    final /* synthetic */ c a;

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    private c$a(c cVar, int i) {
        this.a = cVar;
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        if (this.a.isAdded()) {
            this.a.c();
            this.a.a(this.a.getString(R.string.search_error));
        }
    }

    public void a(String str) {
        if (this.a.isAdded()) {
            List a;
            this.a.c();
            this.a.c.c();
            this.a.c.b();
            ListInfo a2 = com.budejie.www.j.a.a(str);
            c.a(this.a, a2.count);
            c.a(this.a, a2.np);
            try {
                a = com.budejie.www.j.a.a(this.a.getActivity(), str);
            } catch (Exception e) {
                e.printStackTrace();
                a = null;
            }
            if (a != null) {
                int count = c.h(this.a).getCount();
                if (c.f(this.a) == 0 || count < c.i(this.a) || !a(a)) {
                    if (c.i(this.a) <= 20 || c.f(this.a) == 0) {
                        this.a.c.setPullLoadEnable(false);
                    } else {
                        this.a.c.setPullLoadEnable(true);
                    }
                    c.h(this.a).a(this.a.f);
                    if (c.f(this.a) == 20) {
                        c.j(this.a).clear();
                        c.h(this.a).b(a);
                        return;
                    }
                    c.j(this.a).addAll(a);
                    c.h(this.a).a(a);
                    return;
                }
                this.a.c.setPullLoadEnable(false);
                return;
            }
            this.a.c.setVisibility(8);
            this.a.a(this.a.getString(R.string.search_error));
        }
    }

    public boolean a(List<ListItemObject> list) {
        return list == null || list.isEmpty();
    }
}
