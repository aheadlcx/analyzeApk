package com.budejie.www.activity.posts;

import com.budejie.www.bean.ListItemObject;
import io.reactivex.functions.Function;

class b$11 implements Function<Integer, Integer> {
    final /* synthetic */ b a;

    b$11(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ Object apply(Object obj) throws Exception {
        return a((Integer) obj);
    }

    public Integer a(Integer num) {
        int i;
        int size = b.m(this.a).size();
        for (int i2 = 0; i2 < size; i2++) {
            ListItemObject listItemObject = (ListItemObject) b.m(this.a).get(i2);
            if (listItemObject != null && b.s(this.a).equals(listItemObject.getWid())) {
                i = i2;
                break;
            }
        }
        i = 0;
        return Integer.valueOf(i);
    }
}
