package com.budejie.www.activity.video;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.util.ao;

class f$6 implements OnClickListener {
    final /* synthetic */ f a;

    f$6(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        try {
            if (k.a(null).f.getParentView().getKeyBoardState()) {
                ao.a(f.e(this.a));
            } else if (!k.a(null).f.c() && !k.a(null).f.d()) {
                if (k.a(f.e(this.a)).f == null || !k.a(f.e(this.a)).f.isShown()) {
                    f.e(this.a).finish();
                } else {
                    k.a(f.e(this.a)).f.l();
                }
            }
        } catch (Exception e) {
            f.e(this.a).finish();
            e.printStackTrace();
        }
    }
}
