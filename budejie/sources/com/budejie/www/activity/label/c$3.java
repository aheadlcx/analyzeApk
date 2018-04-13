package com.budejie.www.activity.label;

import com.budejie.www.util.an;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class c$3 extends a<String> {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        if (!c.b(this.a).isFinishing()) {
            c.f(this.a).show();
        }
    }

    public void a(String str) {
        try {
            if ("0".equals(z.s(str).getCode())) {
                c.a(this.a, an.a(c.b(this.a), "操作成功", -1));
                c.g(this.a).show();
                c.a(this.a, !c.d(this.a));
                c.c(this.a).setIs_sub(c.d(this.a) ? "1" : "0");
                if (c.c(this.a).getSub_number().matches("[0-9]+")) {
                    if (c.d(this.a)) {
                        c.c(this.a).setSub_number((Integer.parseInt(c.c(this.a).getSub_number()) + 1) + "");
                    } else {
                        c.c(this.a).setSub_number((Integer.parseInt(c.c(this.a).getSub_number()) - 1) + "");
                    }
                }
                if (c.d(this.a)) {
                    c.h(this.a).setText((Integer.parseInt(c.h(this.a).getText().toString()) + 1) + "");
                } else {
                    c.h(this.a).setText((Integer.parseInt(c.h(this.a).getText().toString()) - 1) + "");
                }
                c.i(this.a);
            } else {
                c.a(this.a, an.a(c.b(this.a), "操作失败", -1));
                c.g(this.a).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            c.a(this.a, an.a(c.b(this.a), "操作失败", -1));
            c.g(this.a).show();
        }
        if (c.f(this.a).isShowing()) {
            c.f(this.a).cancel();
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (this.a.isAdded()) {
            if (c.f(this.a).isShowing()) {
                c.f(this.a).cancel();
            }
            c.a(this.a, an.a(c.b(this.a), "操作失败", -1));
            c.g(this.a).show();
        }
    }
}
