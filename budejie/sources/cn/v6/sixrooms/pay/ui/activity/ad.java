package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class ad implements OnClickListener {
    final /* synthetic */ CoopPayAcitvity a;

    ad(CoopPayAcitvity coopPayAcitvity) {
        this.a = coopPayAcitvity;
    }

    public final void onClick(View view) {
        this.a.showMenu();
    }
}
