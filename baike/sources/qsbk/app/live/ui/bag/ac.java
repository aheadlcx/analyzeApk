package qsbk.app.live.ui.bag;

import android.view.View;
import android.view.View.OnClickListener;

class ac implements OnClickListener {
    final /* synthetic */ MarketPaySuccessDialog a;

    ac(MarketPaySuccessDialog marketPaySuccessDialog) {
        this.a = marketPaySuccessDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
