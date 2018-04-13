package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class a implements OnClickListener {
    final /* synthetic */ AlipayActivity a;

    a(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void onClick(View view) {
        this.a.showMenu();
    }
}
