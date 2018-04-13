package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.SetUserVisibleEngine.CallBack;

final class r implements CallBack {
    final /* synthetic */ ChangeUserVisibilityActivity a;

    r(ChangeUserVisibilityActivity changeUserVisibilityActivity) {
        this.a = changeUserVisibilityActivity;
    }

    public final void result(String str) {
        this.a.setTheMark();
        this.a.hideLoadingScreen();
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.showErrorToast(i);
    }
}
