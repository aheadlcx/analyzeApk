package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;

final class bb implements CallBack {
    final /* synthetic */ MineFragment a;

    bb(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void handleInfo(UserBean userBean) {
        MineFragment.a(this.a, userBean);
        if (MineFragment.i(this.a)) {
            GlobleValue.setUserBean(userBean);
        }
        MineFragment.e(this.a).sendEmptyMessage(0);
    }

    public final void error(int i) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).showErrorToast(i);
    }

    public final void handleErrorInfo(String str, String str2) {
        MineFragment.j(this.a);
        MineFragment.f(this.a).handleErrorResult(str, str2, MineFragment.f(this.a));
    }
}
