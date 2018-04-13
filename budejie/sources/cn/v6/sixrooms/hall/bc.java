package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.engine.GetUserVisibleEngine.CallBack;

final class bc implements CallBack {
    final /* synthetic */ MineFragment a;

    bc(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void result(String str) {
        MineFragment.d(this.a, str);
        if ("0".equals(MineFragment.k(this.a))) {
            MineFragment.l(this.a).setText("显身");
        } else if ("1".equals(MineFragment.k(this.a))) {
            MineFragment.l(this.a).setText("隐身");
        } else if ("2".equals(MineFragment.k(this.a))) {
            MineFragment.l(this.a).setText("显身");
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        MineFragment.f(this.a).handleErrorResult(str, str2, MineFragment.f(this.a));
    }

    public final void error(int i) {
        MineFragment.f(this.a).showErrorToast(i);
    }
}
