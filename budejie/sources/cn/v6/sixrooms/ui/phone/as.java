package cn.v6.sixrooms.ui.phone;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final class as implements OnDismissListener {
    final /* synthetic */ ExchangeBean6ToCoin6Activity a;

    as(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = exchangeBean6ToCoin6Activity;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.showLoadingScreen(false);
    }
}
