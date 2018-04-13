package cn.v6.sixrooms.widgets.phone;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class p implements DialogListener {
    final /* synthetic */ int a;
    final /* synthetic */ GuardPropDetailBean b;
    final /* synthetic */ FullScreenOpenGuardDialog c;

    p(FullScreenOpenGuardDialog fullScreenOpenGuardDialog, int i, GuardPropDetailBean guardPropDetailBean) {
        this.c = fullScreenOpenGuardDialog;
        this.a = i;
        this.b = guardPropDetailBean;
    }

    public final void positive(int i) {
        this.c.l.buyProp(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), LoginUtils.getLoginUserBean().getId(), this.c.g.getId(), ((GuardPropBean) this.c.h.get(this.a)).getPropId(), this.b.getTid(), "");
    }

    public final void negative(int i) {
    }
}
