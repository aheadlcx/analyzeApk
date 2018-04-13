package qsbk.app.live.ui.bag;

import com.r0adkll.slidr.Slidr;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

public class MarketActivity extends BaseActivity {
    protected int getLayoutId() {
        return R.layout.activity_market;
    }

    protected void initView() {
        setTitle(getString(R.string.market));
        setUp();
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected void initData() {
    }
}
