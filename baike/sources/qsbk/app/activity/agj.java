package qsbk.app.activity;

import android.view.View;
import qsbk.app.model.TradeRecord;
import qsbk.app.utils.ToastUtil;
import qsbk.app.widget.NoUnderlineClickableSpan;

class agj extends NoUnderlineClickableSpan {
    final /* synthetic */ TradeRecord a;
    final /* synthetic */ a b;

    agj(a aVar, TradeRecord tradeRecord) {
        this.b = aVar;
        this.a = tradeRecord;
    }

    public void onClick(View view) {
        ToastUtil.Short(this.a.toast);
    }
}
