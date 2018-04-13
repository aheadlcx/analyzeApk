package qsbk.app.live.ui.bag;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMarketPayRecord;

class w implements OnClickListener {
    final /* synthetic */ MarketPayDialog a;

    w(MarketPayDialog marketPayDialog) {
        this.a = marketPayDialog;
    }

    public void onClick(View view) {
        if (this.a.p == null || this.a.r >= this.a.p.size() || ((LiveMarketPayRecord) this.a.p.get(this.a.r)).n <= AppUtils.getInstance().getUserInfoProvider().getBalance()) {
            Builder yVar = new y(this, R.style.SimpleDialog);
            yVar.message("确定要购买该特效吗？").positiveAction(this.a.getContext().getString(R.string.setting_confirm)).negativeAction(this.a.getContext().getString(R.string.setting_cancel));
            AppUtils.showDialogFragment(this.a.s, yVar);
            return;
        }
        yVar = new x(this, R.style.SimpleDialog);
        yVar.message(this.a.s.getString(R.string.live_balance_not_sufficient_hint)).positiveAction(this.a.s.getString(R.string.live_charge)).negativeAction(this.a.s.getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this.a.s, yVar);
    }
}
