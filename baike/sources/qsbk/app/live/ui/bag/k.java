package qsbk.app.live.ui.bag;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveMarketDataRecord;

class k implements OnClickListener {
    final /* synthetic */ Object a;
    final /* synthetic */ MarketAdapter b;

    k(MarketAdapter marketAdapter, Object obj) {
        this.b = marketAdapter;
        this.a = obj;
    }

    public void onClick(View view) {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            MarketPayDialog marketPayDialog = new MarketPayDialog(this.b.a, ((LiveMarketDataRecord) this.a).i);
            marketPayDialog.setParent((FragmentActivity) this.b.a);
            marketPayDialog.show();
            return;
        }
        AppUtils.getInstance().getUserInfoProvider().toLogin((FragmentActivity) this.b.a, 1000);
    }
}
