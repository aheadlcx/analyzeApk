package qsbk.app.live.ui;

import java.util.List;
import qsbk.app.core.model.BarrageData;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.live.adapter.BarrageListAdapter.BarrageItemClickListener;

class t implements BarrageItemClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ LiveBaseActivity b;

    t(LiveBaseActivity liveBaseActivity, List list) {
        this.b = liveBaseActivity;
        this.a = list;
    }

    public void onClick(int i) {
        String str = ((BarrageData) this.a.get(i)).t;
        LiveBaseActivity.a(this.b, str);
        PreferenceUtils.instance().putString("live_barrage_switch", str);
        LiveBaseActivity.g(this.b);
    }
}
