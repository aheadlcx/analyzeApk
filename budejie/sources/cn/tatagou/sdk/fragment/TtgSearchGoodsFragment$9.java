package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.view.IUpdateView;

class TtgSearchGoodsFragment$9 extends IUpdateView<Boolean> {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$9(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void updateView(Boolean bool) {
        if (bool.booleanValue() && this.a.isAdded()) {
            this.a.getActivity().getWindow().setSoftInputMode(48);
            this.a.getActivity().getWindow().setSoftInputMode(2);
        }
    }
}
