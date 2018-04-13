package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.NameModifyEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SendBroadcastUtils;

final class aq implements CallBack {
    final /* synthetic */ MineFragment a;

    aq(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void result(String str, String str2) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).handleErrorResult("", str, MineFragment.f(this.a));
        MineFragment.o(this.a).setText(MineFragment.n(this.a));
        SendBroadcastUtils.sendUserNameBroadcast(MineFragment.p(this.a), MineFragment.n(this.a));
        UserBean userBean = GlobleValue.getUserBean();
        userBean.setAlias(MineFragment.n(this.a));
        GlobleValue.setUserBean(userBean);
    }

    public final void error(int i) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).showErrorToast(i);
        MineFragment.o(this.a).setText(MineFragment.q(this.a));
    }

    public final void handleErrorInfo(String str, String str2) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).handleErrorResult(str, str2, MineFragment.f(this.a));
        MineFragment.o(this.a).setText(MineFragment.q(this.a));
    }
}
