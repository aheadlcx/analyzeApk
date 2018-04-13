package cn.tatagou.sdk.fragment;

import android.view.View;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.pojo.AppCate;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.util.c;

class TtgMainFragment$1 extends c {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$1(TtgMainFragment ttgMainFragment) {
        this.a = ttgMainFragment;
    }

    public void setPageSelected(int i) {
        super.setPageSelected(i);
        this.a.mScrollCatViewPage.setCurrentItem(i, false);
        if (TtgMainFragment.access$000(this.a).size() > 0) {
            AppCate appCate = (AppCate) TtgMainFragment.access$000(this.a).get(i);
            if (this.a.mAppCate != null) {
                this.a.mAppCate.setId(appCate.getId());
                this.a.mAppCate.setPosition(i);
                b.cateStatEvent(appCate.getId());
            }
        }
    }

    public void onCheckIdentityResult(String str) {
        super.onCheckIdentityResult(str);
        TtgMainFragment.access$100(this.a, str);
        if (TtgMainFragment.access$200(this.a).a != null && AppHomeData.getInstance().getHomeData() != null) {
            TtgMainFragment.access$200(this.a).a.onRcmmDataReady(false, AppHomeData.getInstance().getHomeData().getNormalSpecialList(), AppHomeData.getInstance().getHomeData().getTimestamp(), TtgMainFragment.access$300(this.a), "registerSaveInfoListener");
        }
    }

    public void onTitle(View view, TitleBar titleBar) {
        super.onTitle(view, titleBar);
        this.a.setBarTitle(view, titleBar);
    }
}
