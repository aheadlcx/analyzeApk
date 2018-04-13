package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class am implements OnClickListener {
    final /* synthetic */ ExchangeBean6ToCoin6Activity a;

    am(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = exchangeBean6ToCoin6Activity;
    }

    public final void onClick(View view) {
        this.a.finishWithAnimation();
    }
}
