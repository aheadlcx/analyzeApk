package qsbk.app.live.ui.bag;

import android.content.Context;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public class MarketPaySuccessDialog extends BaseDialog {
    public MarketPaySuccessDialog(Context context) {
        super(context);
    }

    protected int c() {
        return R.layout.dialog_market_pay_success;
    }

    protected void d() {
        findViewById(R.id.ll_pay_success).setOnClickListener(new ac(this));
    }

    protected void e() {
    }

    protected int a() {
        return 17;
    }

    protected float f() {
        return 0.5f;
    }
}
