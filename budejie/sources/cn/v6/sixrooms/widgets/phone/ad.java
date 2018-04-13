package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class ad implements DialogListener {
    final /* synthetic */ GuardPropDetailBean a;
    final /* synthetic */ ac b;

    ad(ac acVar, GuardPropDetailBean guardPropDetailBean) {
        this.b = acVar;
        this.a = guardPropDetailBean;
    }

    public final void positive(int i) {
        OpenGuardPage.a(this.b.a, this.a);
    }

    public final void negative(int i) {
    }
}
