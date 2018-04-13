package com.budejie.www.activity.video;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

class f$21 implements OnClickListener {
    final /* synthetic */ f a;

    f$21(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        Log.d("MicroMediaController", "mPlaySimpleClose onclick");
        if (f.l(this.a) != null) {
            f.l(this.a).c();
        }
    }
}
