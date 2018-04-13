package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class x implements DialogListener {
    final /* synthetic */ DialogActivity a;

    x(DialogActivity dialogActivity) {
        this.a = dialogActivity;
    }

    public final void positive(int i) {
        this.a.finish();
    }

    public final void negative(int i) {
    }
}
