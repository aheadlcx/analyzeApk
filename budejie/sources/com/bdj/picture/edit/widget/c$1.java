package com.bdj.picture.edit.widget;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.bdj.picture.edit.bean.d;

class c$1 implements OnClickListener {
    final /* synthetic */ RelativeLayout a;
    final /* synthetic */ d b;
    final /* synthetic */ c c;

    c$1(c cVar, RelativeLayout relativeLayout, d dVar) {
        this.c = cVar;
        this.a = relativeLayout;
        this.b = dVar;
    }

    public void onClick(View view) {
        if (c.a(this.c) != null) {
            c.a(this.c).setSelected(false);
            ((d) c.a(this.c).getTag()).a(false);
        }
        this.a.setSelected(true);
        c.a(this.c, this.a);
        this.b.a(true);
        c.a(this.c).setTag(this.b);
        this.c.a(this.b);
    }
}
