package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cw implements OnClickListener {
    final /* synthetic */ ShopActivity a;

    cw(ShopActivity shopActivity) {
        this.a = shopActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
