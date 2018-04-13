package cn.tatagou.sdk.fragment;

class TtgSearchGoodsFragment$12 implements Runnable {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$12(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void run() {
        TtgSearchGoodsFragment.k(this.a).requestFocusFromTouch();
        TtgSearchGoodsFragment.k(this.a).setSelection(0);
    }
}
