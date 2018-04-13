package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.utils.FastDoubleClickUtil;

final class bl implements OnClickListener {
    final /* synthetic */ WeixinPayActivity a;

    bl(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void onClick(View view) {
        if (!FastDoubleClickUtil.isFastDoubleClick()) {
            WeixinPayActivity.a(this.a);
        }
    }
}
