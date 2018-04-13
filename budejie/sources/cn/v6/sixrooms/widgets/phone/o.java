package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.engine.PurchaseGuardEngine.CallBack;

final class o implements CallBack {
    final /* synthetic */ FullScreenOpenGuardDialog a;

    o(FullScreenOpenGuardDialog fullScreenOpenGuardDialog) {
        this.a = fullScreenOpenGuardDialog;
    }

    public final void success() {
        if (this.a.o != null) {
            this.a.o.onPurchaseSuccess();
        }
        this.a.cancel();
        if (this.a.m != null) {
            this.a.m.dismiss();
            this.a.m = null;
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.k.handleErrorResult(str, str2, this.a.k);
    }

    public final void error(int i) {
        this.a.k.showErrorToast(i);
    }
}
