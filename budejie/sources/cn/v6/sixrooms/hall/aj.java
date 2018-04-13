package cn.v6.sixrooms.hall;

import android.view.View;
import android.view.View.OnClickListener;

final class aj implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ai b;

    aj(ai aiVar, int i) {
        this.b = aiVar;
        this.a = i;
    }

    public final void onClick(View view) {
        HotFragment.b(this.b.a).setCurrentItem(this.a);
    }
}
