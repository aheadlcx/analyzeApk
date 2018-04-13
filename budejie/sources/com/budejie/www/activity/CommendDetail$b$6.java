package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.CommendDetail.b;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.util.an;

class CommendDetail$b$6 implements OnClickListener {
    final /* synthetic */ CommentItem a;
    final /* synthetic */ View b;
    final /* synthetic */ b c;

    CommendDetail$b$6(b bVar, CommentItem commentItem, View view) {
        this.c = bVar;
        this.a = commentItem;
        this.b = view;
    }

    public void onClick(View view) {
        if (!an.b()) {
            CommendDetail.b(this.c.d, this.a, this.b);
        }
    }
}
