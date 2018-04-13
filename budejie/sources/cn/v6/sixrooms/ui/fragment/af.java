package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class af implements DialogListener {
    final /* synthetic */ MsgVerifyFragment a;

    af(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void positive(int i) {
        MsgVerifyFragment.d(this.a).showMenu();
    }

    public final void negative(int i) {
    }
}
