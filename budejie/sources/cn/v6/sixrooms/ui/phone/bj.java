package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.PropActionEngine.CallBack;

final class bj implements CallBack {
    final /* synthetic */ MyPropActivity a;

    bj(MyPropActivity myPropActivity) {
        this.a = myPropActivity;
    }

    public final void result(boolean z) {
        this.a.a(8, "");
        if (z) {
            this.a.a();
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a(8, "");
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.a(8, "");
        this.a.showErrorToast(i);
    }

    public final void start(boolean z) {
        if (z) {
            this.a.a(0, this.a.getResources().getString(R.string.prop_car_opening));
        } else {
            this.a.a(0, this.a.getResources().getString(R.string.prop_car_closing));
        }
    }
}
