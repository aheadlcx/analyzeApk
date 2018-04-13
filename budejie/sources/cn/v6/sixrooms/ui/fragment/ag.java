package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class ag implements DialogListener {
    final /* synthetic */ String a;
    final /* synthetic */ MsgVerifyFragment b;

    ag(MsgVerifyFragment msgVerifyFragment, String str) {
        this.b = msgVerifyFragment;
        this.a = str;
    }

    public final void positive(int i) {
        if ("105".equals(this.a) && 2 == MsgVerifyFragment.b(this.b)) {
            MsgVerifyFragment.d(this.b).showMenu();
        }
    }

    public final void negative(int i) {
    }
}
