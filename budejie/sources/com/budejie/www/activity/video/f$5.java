package com.budejie.www.activity.video;

import android.view.View;
import android.view.View.OnClickListener;

class f$5 implements OnClickListener {
    final /* synthetic */ f a;

    f$5(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        this.a.a(false, true);
        if (!(f.b(this.a) instanceof FullScreenVideoActivity) && f.a(this.a) != 6 && f.a(this.a) != 5) {
            k.a(f.e(this.a)).t();
        }
    }
}
