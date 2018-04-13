package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class au implements DialogListener {
    final /* synthetic */ MobilePayActivity a;

    au(MobilePayActivity mobilePayActivity) {
        this.a = mobilePayActivity;
    }

    public final void positive(int i) {
        this.a.finish();
    }

    public final void negative(int i) {
    }
}
