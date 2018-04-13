package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.CommendDetailOld.b;
import com.budejie.www.bean.CommentItem;

class CommendDetailOld$b$3 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ View b;
    final /* synthetic */ b c;

    CommendDetailOld$b$3(b bVar, int i, View view) {
        this.c = bVar;
        this.a = i;
        this.b = view;
    }

    public void onClick(View view) {
        if (this.c.a != null && !CommendDetailOld.Y(this.c.d)) {
            CommendDetailOld.a(this.c.d, this.a, (CommentItem) CommendDetailOld.c(this.c.d).get(this.a), this.b);
        }
    }
}
