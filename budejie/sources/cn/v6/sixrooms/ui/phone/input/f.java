package cn.v6.sixrooms.ui.phone.input;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class f implements DialogListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void positive(int i) {
        this.a.a.mActivity.showOpenGuardPage();
    }

    public final void negative(int i) {
    }
}
