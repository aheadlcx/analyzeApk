package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class aa implements OnClickListener {
    final /* synthetic */ MsgVerifyFragment a;

    aa(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void onClick(View view) {
        if (MsgVerifyFragment.b(this.a) == 1) {
            MsgVerifyFragment.c(this.a).bundleAgain();
        } else {
            MsgVerifyFragment.d(this.a).showMenu();
        }
    }
}
