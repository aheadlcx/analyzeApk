package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class ac implements OnClickListener {
    final /* synthetic */ MsgVerifyFragment a;

    ac(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void onClick(View view) {
        this.a.clearCode();
        MsgVerifyFragment.h(this.a);
    }
}
