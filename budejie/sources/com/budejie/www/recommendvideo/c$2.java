package com.budejie.www.recommendvideo;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.video.k;
import com.budejie.www.util.an;

class c$2 implements OnClickListener {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        AutoPlayVideoListActivity.a(this.a.e);
        an.E(this.a.b);
        k.a(this.a.b).a(this.a.e, this.a.a, c.b(this.a), null, c.c(this.a), this.a.d, 4);
    }
}
