package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class g implements OnClickListener {
    final /* synthetic */ AlipayActivity a;

    g(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void onClick(View view) {
        AlipayActivity.a(this.a);
    }
}
