package com.budejie.www.recommendvideo;

import android.app.Activity;
import com.budejie.www.adapter.c;
import com.budejie.www.adapter.d;
import com.budejie.www.bean.ListItemObject;
import java.util.List;

public class a extends c<ListItemObject> {
    private Activity a;
    private com.budejie.www.adapter.e.a b;
    private int c;
    private com.budejie.www.recommendvideo.c.a e;

    public a(Activity activity, com.budejie.www.adapter.e.a aVar, com.budejie.www.recommendvideo.c.a aVar2) {
        this.a = activity;
        this.b = aVar;
        this.e = aVar2;
    }

    public void a(List<ListItemObject> list) {
        this.c = list.size();
        super.a(list);
    }

    public void b(List<ListItemObject> list) {
        this.c += list.size();
        super.b(list);
    }

    protected d a(ListItemObject listItemObject, int i) {
        return new c(this.a, this.b, this.e, listItemObject, i, this.c);
    }
}
