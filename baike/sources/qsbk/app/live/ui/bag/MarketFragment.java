package qsbk.app.live.ui.bag;

import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.widget.RefreshRecyclerView;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMarketDataRecord;
import qsbk.app.live.widget.SuperUserEnterAnimLayout;

public class MarketFragment extends BaseFragment {
    MarketAdapter a;
    SuperUserEnterAnimLayout b;
    private RefreshRecyclerView c;
    private List<LiveMarketDataRecord> d = new ArrayList();

    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    protected void initView() {
        this.c = (RefreshRecyclerView) $(R.id.refresher);
        this.a = new MarketAdapter(getContext(), this.d);
        this.b = (SuperUserEnterAnimLayout) $(R.id.enter_anim);
    }

    protected void initData() {
        a();
    }

    private void a() {
        ConfigInfoUtil.instance().setUpdateConfigCallback(new n(this));
        ConfigInfoUtil.instance().updateConfigInfo(true);
    }
}
