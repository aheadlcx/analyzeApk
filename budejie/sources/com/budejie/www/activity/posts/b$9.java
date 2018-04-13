package com.budejie.www.activity.posts;

import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.ap;
import io.reactivex.functions.Function;
import java.util.List;

class b$9 implements Function<Integer, List<ListItemObject>> {
    final /* synthetic */ b a;

    b$9(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ Object apply(Object obj) throws Exception {
        return a((Integer) obj);
    }

    public List<ListItemObject> a(Integer num) {
        if (b.q(this.a) == 1) {
            b.b(this.a, b.r(this.a).a(b.l(this.a).getKey(), null, "essence"));
        } else if (b.q(this.a) == 2) {
            b.b(this.a, b.r(this.a).a(b.l(this.a).getKey(), null, "new"));
        }
        ap.a(b.m(this.a));
        return b.m(this.a);
    }
}
