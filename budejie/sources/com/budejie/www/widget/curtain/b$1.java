package com.budejie.www.widget.curtain;

import android.text.TextUtils;
import android.util.Log;
import com.budejie.www.R;
import com.budejie.www.activity.video.barrage.a.f$a;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import com.budejie.www.activity.video.barrage.danmaku.model.k;

class b$1 implements f$a {
    final /* synthetic */ b a;
    private int b = b.a(this.a).getResources().getDimensionPixelSize(R.dimen.navigation_height);

    b$1(b bVar) {
        this.a = bVar;
    }

    public void a(c cVar, float f, float f2) {
        Log.d("OnDanmakuClickListener", "onDanmakuClick ");
        if (!cVar.a.f) {
            b.b(this.a).add(cVar.a.a);
            cVar.a.f = true;
            cVar.e = -1823210;
            if (TextUtils.isEmpty(cVar.a.c)) {
                cVar.a.c = "0";
            }
            cVar.a.c = (Integer.parseInt(cVar.a.c) + 1) + "";
            b.c(this.a).a(f, ((float) this.b) + f2);
        }
    }

    public void a(k kVar) {
    }

    public void a(boolean z) {
        Log.d("OnDanmakuClickListener", "onDanmakuEmptyClick");
    }
}
