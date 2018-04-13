package qsbk.app.live.ui.bag;

import android.content.Intent;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.widget.RefreshRecyclerView;
import qsbk.app.live.R;

public class BagFragment extends BaseFragment {
    public static final int REQUEST_MARKET = 1101;
    private RefreshRecyclerView a;

    protected int getLayoutId() {
        return R.layout.fragment_bag;
    }

    protected void initView() {
        this.a = (RefreshRecyclerView) $(R.id.refresher);
        Map hashMap = new HashMap();
        hashMap.put("count", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        this.a.setRefreshListener(new f(this));
        BaseRecyclerViewAdapter bagAdapter = new BagAdapter(getContext(), new ArrayList());
        bagAdapter.setClickItem(new h(this));
        this.a.init(bagAdapter, UrlConstants.LIVE_BAG, hashMap, "ujse", new i(this));
        if (this.a.getmGridLayoutManager() != null) {
            this.a.getmGridLayoutManager().setSpanSizeLookup(new j(this, bagAdapter));
        }
    }

    protected void initData() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1101) {
            this.a.forceRefresh();
        }
    }

    public void refresh() {
        this.a.forceRefresh();
    }
}
