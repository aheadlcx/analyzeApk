package com.budejie.www.activity.phonenumber;

import android.widget.Toast;
import com.budejie.www.adapter.a.j;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.util.z;
import java.util.List;
import net.tsz.afinal.a.a;

class e$1 extends a {
    final /* synthetic */ e a;

    e$1(e eVar) {
        this.a = eVar;
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        this.a.c();
        Toast.makeText(e.a(this.a), "请求数据失败，请重试", 0).show();
    }

    public void onSuccess(Object obj) {
        this.a.c();
        if (obj == null) {
            Toast.makeText(e.a(this.a), "数据为空", 0).show();
            return;
        }
        List h = z.h(obj.toString());
        if (h == null || h.size() <= 0) {
            Toast.makeText(e.a(this.a), "无数据", 0).show();
            return;
        }
        for (int size = h.size() - 1; size >= 0; size--) {
            if ("1".equals(((RecommendSubscription) h.get(size)).getIs_sub())) {
                h.remove(size);
            }
        }
        e.a(this.a, new j(e.a(this.a), h, e.b(this.a)));
        e.b(this.a).setAdapter(e.c(this.a));
        e.d(this.a).b("recommend_Label");
        e.d(this.a).b(h);
    }
}
