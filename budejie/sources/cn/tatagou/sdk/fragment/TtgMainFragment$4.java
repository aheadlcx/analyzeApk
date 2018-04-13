package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.TtgUrl;
import cn.tatagou.sdk.view.IUpdateView;

class TtgMainFragment$4 extends IUpdateView<TtgUrl> {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$4(TtgMainFragment ttgMainFragment) {
        this.a = ttgMainFragment;
    }

    public void updateView(TtgUrl ttgUrl) {
        if (!(!this.a.isAdded() || ttgUrl == null || this.a.mScrollCatViewPage == null || TtgMainFragment.access$000(this.a) == null || TtgMainFragment.access$000(this.a).size() <= 0)) {
            AppHomeData.getInstance().setTtgUrl(ttgUrl);
            TtgMainFragment.access$1200(this.a);
        }
        Log.d(TtgMainFragment.access$400(), "TTG_HOME updateView: onResume");
    }
}
