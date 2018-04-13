package com.budejie.www.activity.video;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.util.a;
import com.budejie.www.wallpaper.b.b;

class f$4 implements OnClickListener {
    final /* synthetic */ f a;

    f$4(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        a.b(this.a.getContext(), f.o(this.a));
        if (f.a(this.a) == 0) {
            b.a(f.o(this.a), "动态桌面", "悬浮");
        }
    }
}
