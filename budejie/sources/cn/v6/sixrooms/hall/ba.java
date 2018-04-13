package cn.v6.sixrooms.hall;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.BundleInfoEngine.CallBack;
import cn.v6.sixrooms.utils.ToastUtils;

final class ba implements CallBack {
    final /* synthetic */ MineFragment a;

    ba(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void handleErrorInfo(String str, String str2) {
        MineFragment.f(this.a).handleErrorResult(str, str2, MineFragment.f(this.a));
    }

    public final void error(int i) {
        MineFragment.f(this.a).showErrorToast(i);
    }

    public final void bundleInfo(String str, String str2, String str3) {
        MineFragment.a(this.a, true);
        if ("0".equals(str)) {
            MineFragment.b(this.a, false);
            MineFragment.g(this.a).setText("");
            MineFragment.h(this.a).setText(this.a.isAdded() ? this.a.getResources().getString(R.string.bundle) : MineFragment.f(this.a).getResources().getString(R.string.bundle));
        } else if ("1".equals(str)) {
            MineFragment.b(this.a, true);
            MineFragment.b(this.a, str2);
            MineFragment.g(this.a).setText(str2);
            MineFragment.h(this.a).setText(this.a.isAdded() ? this.a.getResources().getString(R.string.unbundle_mobile) : MineFragment.f(this.a).getResources().getString(R.string.unbundle_mobile));
        } else {
            MineFragment.a(this.a, false);
            ToastUtils.showToast(this.a.isAdded() ? this.a.getResources().getString(R.string.data_error) : MineFragment.f(this.a).getResources().getString(R.string.data_error));
        }
        MineFragment.c(this.a, str3);
    }
}
