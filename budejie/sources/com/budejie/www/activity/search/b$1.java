package com.budejie.www.activity.search;

import android.text.TextUtils;
import com.budejie.www.activity.plate.bean.PlateResponseData;
import com.budejie.www.util.ah;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class b$1 extends a<String> {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        this.a.b();
    }

    public void a(String str) {
        if (this.a.isAdded()) {
            this.a.c();
            this.a.c.setVisibility(0);
            if (TextUtils.isEmpty(str)) {
                this.a.c.setVisibility(8);
                this.a.a("数据为空");
                return;
            }
            PlateResponseData plateResponseData = (PlateResponseData) z.a(str, PlateResponseData.class);
            if (plateResponseData != null) {
                b.a(this.a, plateResponseData.list);
                if (com.budejie.www.goddubbing.c.a.a(b.a(this.a))) {
                    this.a.c.setVisibility(8);
                    this.a.a("抱歉，没有该标签！");
                    return;
                }
                ah.a(this.a.getContext(), b.a(this.a));
                if (b.b(this.a) == null) {
                    b.a(this.a, new b$a(this.a));
                    this.a.c.setAdapter(b.b(this.a));
                    return;
                }
                b.b(this.a).notifyDataSetChanged();
            }
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (this.a.isAdded()) {
            this.a.c();
        }
    }
}
