package com.bdj.picture.edit.widget;

import android.app.Activity;
import android.widget.HorizontalScrollView;
import com.bdj.picture.edit.a.k;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.bean.d;
import com.bdj.picture.edit.util.j;
import java.util.List;

public class a extends c {
    private List<d> d;

    public a(Activity activity) {
        super(activity);
    }

    public void a(HorizontalScrollView horizontalScrollView, BVType bVType) {
        this.c.a(bVType);
        if (this.d == null || this.d.isEmpty()) {
            this.d = j.a(this.a, k.plist_advanced_text_material);
        }
        a(horizontalScrollView, this.d);
    }

    public void a(d dVar) {
        this.c.a(dVar);
        com.bdj.picture.edit.bean.a aVar = new com.bdj.picture.edit.bean.a();
        aVar.a(this.c.a());
        aVar.a(dVar);
        this.b.a(aVar);
    }
}
