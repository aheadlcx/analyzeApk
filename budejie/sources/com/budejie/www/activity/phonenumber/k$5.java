package com.budejie.www.activity.phonenumber;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;
import com.budejie.www.type.VerifyResult;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import net.tsz.afinal.a.a;

class k$5 extends a<String> {
    final /* synthetic */ k a;
    private ProgressDialog b;

    k$5(k kVar) {
        this.a = kVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        super.onStart();
        this.b = ProgressDialog.show(k.e(this.a), "正在验证", "正在进行验证码验证，请稍等...");
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (this.b != null && this.b.isShowing()) {
            this.b.cancel();
        }
        k.a(this.a, an.a(k.e(this.a), "验证码验证失败。", -1));
        k.f(this.a).show();
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            if (this.b != null && this.b.isShowing()) {
                this.b.cancel();
            }
            VerifyResult verifyResult = (VerifyResult) z.a(str, VerifyResult.class);
            if (verifyResult == null) {
                k.a(this.a, an.a(k.e(this.a), "获取验证码失败", -1));
                k.f(this.a).show();
            } else if (Constants.DEFAULT_UIN.equals(verifyResult.getCode())) {
                k.c(this.a, verifyResult.getReq());
                k.d(this.a, verifyResult.getExpirTime());
                Log.i("SMSVerifyFragment", "mReq = " + k.i(this.a) + ", mReqExpirTime = " + k.j(this.a));
                i.a().i();
                if (k.k(this.a)) {
                    k.e(this.a, k.i(this.a));
                    return;
                }
                r0 = k.l(this.a).substring(0, 3);
                k.a(this.a, "用户" + r0 + "XX" + k.l(this.a).substring(7, 11), k.m(this.a), k.i(this.a));
            } else {
                r0 = verifyResult.getMsg();
                if (TextUtils.isEmpty(r0)) {
                    r0 = "获取验证码失败";
                }
                k.a(this.a, an.a(k.e(this.a), r0, -1));
                k.f(this.a).show();
            }
        }
    }
}
