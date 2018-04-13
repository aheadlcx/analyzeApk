package com.budejie.www.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.CommendDetailOld.b;

class CommendDetailOld$b$1 implements OnClickListener {
    final /* synthetic */ CommendDetailOld$b$b a;
    final /* synthetic */ b b;

    CommendDetailOld$b$1(b bVar, CommendDetailOld$b$b commendDetailOld$b$b) {
        this.b = bVar;
        this.a = commendDetailOld$b$b;
    }

    public void onClick(View view) {
        this.a.u.setVisibility(8);
        this.a.v.setVisibility(0);
        CommendDetailOld.c(this.b.d, 1);
        CommendDetailOld.V(this.b.d).a(CommendDetailOld.l(this.b.d), CommendDetailOld.S(this.b.d), CommendDetailOld.T(this.b.d), "1", CommendDetailOld.U(this.b.d));
    }
}
