package com.budejie.www.activity.video;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.video.f.c;

class f$2 implements OnClickListener {
    final /* synthetic */ f a;

    f$2(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        boolean z = true;
        view.setSelected(!view.isSelected());
        if (!view.isSelected()) {
            k.a(this.a.getContext()).k();
        }
        if (f.d(this.a) != null) {
            c d = f.d(this.a);
            if (view.isSelected()) {
                z = false;
            }
            d.a(z);
        }
    }
}
