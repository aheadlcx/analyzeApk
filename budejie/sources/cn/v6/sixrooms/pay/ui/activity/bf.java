package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class bf implements OnClickListener {
    final /* synthetic */ WeixinPayActivity a;

    bf(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void onClick(View view) {
        this.a.showMenu();
    }
}
