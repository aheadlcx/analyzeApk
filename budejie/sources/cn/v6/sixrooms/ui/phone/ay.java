package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class ay implements DialogListener {
    final /* synthetic */ FindUsernameActivity a;

    ay(FindUsernameActivity findUsernameActivity) {
        this.a = findUsernameActivity;
    }

    public final void positive(int i) {
        this.a.finishWithAnimation();
    }

    public final void negative(int i) {
    }
}
