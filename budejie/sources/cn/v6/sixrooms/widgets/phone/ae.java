package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class ae implements DialogListener {
    final /* synthetic */ GuardPropDetailBean a;
    final /* synthetic */ OpenGuardPage b;

    ae(OpenGuardPage openGuardPage, GuardPropDetailBean guardPropDetailBean) {
        this.b = openGuardPage;
        this.a = guardPropDetailBean;
    }

    public final void positive(int i) {
        this.b.o.buyProp(SaveUserInfoUtils.getEncpass(this.b.a), GlobleValue.getUserBean().getId(), this.b.t.getId(), ((GuardPropBean) this.b.j.get(this.b.p)).getPropId(), this.a.getTid(), "");
    }

    public final void negative(int i) {
    }
}
