package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.CommendDetail.b;

class CommendDetail$b$1 implements OnClickListener {
    final /* synthetic */ CommendDetail$b$b a;
    final /* synthetic */ b b;

    CommendDetail$b$1(b bVar, CommendDetail$b$b commendDetail$b$b) {
        this.b = bVar;
        this.a = commendDetail$b$b;
    }

    public void onClick(View view) {
        this.a.u.setVisibility(8);
        this.a.v.setVisibility(0);
        CommendDetail.c(this.b.d, 1);
        CommendDetail.U(this.b.d).a(CommendDetail.l(this.b.d), CommendDetail.R(this.b.d), CommendDetail.S(this.b.d), "1", CommendDetail.T(this.b.d));
    }
}
