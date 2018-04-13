package cn.tatagou.sdk.fragment;

class TtgMainFragment$3 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ TtgMainFragment b;

    TtgMainFragment$3(TtgMainFragment ttgMainFragment, String str) {
        this.b = ttgMainFragment;
        this.a = str;
    }

    public void run() {
        TtgMainFragment.access$908(this.b);
        if (TtgMainFragment.access$000(this.b) != null && TtgMainFragment.access$900(this.b) == TtgMainFragment.access$000(this.b).size() * 2) {
            TtgMainFragment.access$1000(this.b);
            TtgMainFragment.access$1100(this.b);
            this.b.onDateError(this.a);
        }
    }
}
