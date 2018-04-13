package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView$a;

class FootprintFragment$2 implements AutoPullAbleListView$a {
    final /* synthetic */ FootprintFragment a;

    FootprintFragment$2(FootprintFragment footprintFragment) {
        this.a = footprintFragment;
    }

    public void onLoad(AutoPullAbleListView autoPullAbleListView) {
        if (!p.isEmpty(FootprintFragment.g(this.a))) {
            FootprintFragment.a(this.a, FootprintFragment.g(this.a));
        }
    }
}
