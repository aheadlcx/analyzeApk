package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.CommendDetail.b;
import com.budejie.www.bean.CommentItem;

class CommendDetail$b$3 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ View b;
    final /* synthetic */ b c;

    CommendDetail$b$3(b bVar, int i, View view) {
        this.c = bVar;
        this.a = i;
        this.b = view;
    }

    public void onClick(View view) {
        if (this.c.a != null && !CommendDetail.X(this.c.d)) {
            CommendDetail.a(this.c.d, this.a, (CommentItem) CommendDetail.c(this.c.d).get(this.a), this.b);
        }
    }
}
