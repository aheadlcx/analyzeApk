package com.budejie.www.activity.video;

import android.view.View;
import android.view.View.OnClickListener;

class f$3 implements OnClickListener {
    final /* synthetic */ f a;

    f$3(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        if (f.o(this.a) != null && f.o(this.a).getAdItem() != null) {
            f.o(this.a).getAdItem().onClicked(new View(f.e(this.a)));
        }
    }
}
