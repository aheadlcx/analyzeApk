package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.engine.PurchaseGuardEngine.CallBack;

final class aa implements CallBack {
    final /* synthetic */ OpenGuardPage a;

    aa(OpenGuardPage openGuardPage) {
        this.a = openGuardPage;
    }

    public final void success() {
        if (this.a.u != null) {
            this.a.u.onPurchaseSuccess();
        }
        if (this.a.s != null) {
            this.a.s.dismiss();
            this.a.s = null;
        }
        this.a.s = this.a.r.createDiaglog("购买成功");
        this.a.s.show();
    }

    public final void handleErrorInfo(String str, String str2) {
        if ("105".equals(str)) {
            OpenGuardPage.a(this.a, str2);
        } else if (this.a.u != null) {
            this.a.u.onHandleErrorInfo(str, str2);
        }
    }

    public final void error(int i) {
        if (this.a.u != null) {
            this.a.u.onShowErrorCode(i);
        }
    }
}
