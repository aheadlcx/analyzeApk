package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.verify.VerifyEngine$CallBack;
import cn.v6.sixrooms.utils.ToastUtils;

final class s implements VerifyEngine$CallBack {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    public final void result(String str) {
        ToastUtils.showToast(str);
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.d.a.a.handleErrorResult(str, str2, this.a.d.a.a);
    }

    public final void error(int i) {
        this.a.d.a.a.showErrorToast(i);
    }
}
