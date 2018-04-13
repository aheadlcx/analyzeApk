package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.CommendDetailOld.b;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.util.an;

class CommendDetailOld$b$5 implements OnClickListener {
    final /* synthetic */ CommentItem a;
    final /* synthetic */ View b;
    final /* synthetic */ b c;

    CommendDetailOld$b$5(b bVar, CommentItem commentItem, View view) {
        this.c = bVar;
        this.a = commentItem;
        this.b = view;
    }

    public void onClick(View view) {
        if (!an.b()) {
            CommendDetailOld.a(this.c.d, this.a, this.b);
        }
    }
}
