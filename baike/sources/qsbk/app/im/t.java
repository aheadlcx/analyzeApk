package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.Util;

final class t implements OnClickListener {
    final /* synthetic */ h a;
    final /* synthetic */ View b;

    t(h hVar, View view) {
        this.a = hVar;
        this.b = view;
    }

    public void onClick(View view) {
        this.a.a(Util.getActivityOrContext(this.b));
    }
}
