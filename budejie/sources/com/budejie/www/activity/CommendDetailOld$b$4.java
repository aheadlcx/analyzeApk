package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.budejie.www.activity.CommendDetailOld.b;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;

class CommendDetailOld$b$4 implements OnClickListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ProgressBar b;
    final /* synthetic */ int c;
    final /* synthetic */ CommentItem d;
    final /* synthetic */ b e;

    CommendDetailOld$b$4(b bVar, ImageView imageView, ProgressBar progressBar, int i, CommentItem commentItem) {
        this.e = bVar;
        this.a = imageView;
        this.b = progressBar;
        this.c = i;
        this.d = commentItem;
    }

    public void onClick(View view) {
        this.a.setVisibility(8);
        this.b.setVisibility(0);
        CommendDetailOld.d(this.e.d, this.c);
        BudejieApplication.a.a(RequstMethod.GET, j.b(CommendDetailOld.S(this.e.d), this.d.getId()), CommendDetailOld.Z(this.e.d), CommendDetailOld.aa(this.e.d));
    }
}
