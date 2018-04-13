package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.view.IUpdateView;

class TtgMainFragment$11 extends IUpdateView<Boolean> {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$11(TtgMainFragment ttgMainFragment) {
        this.a = ttgMainFragment;
    }

    public void updateView(Boolean bool) {
        super.updateView(bool);
        this.a.initBcSDK();
    }
}
