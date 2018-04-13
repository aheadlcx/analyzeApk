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

class j$2 extends a<String> {
    final /* synthetic */ j a;
    private ProgressDialog b;

    j$2(j jVar) {
        this.a = jVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        super.onStart();
        this.b = ProgressDialog.show(j.d(this.a), "正在验证", "正在进行验证码验证，请稍等...");
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (this.b != null && this.b.isShowing()) {
            this.b.cancel();
        }
        j.a(this.a, an.a(j.d(this.a), "验证码验证失败。", -1));
        j.e(this.a).show();
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            if (this.b != null && this.b.isShowing()) {
                this.b.cancel();
            }
            VerifyResult verifyResult = (VerifyResult) z.a(str, VerifyResult.class);
            if (verifyResult == null) {
                j.a(this.a, an.a(j.d(this.a), "获取验证码失败", -1));
            } else if (Constants.DEFAULT_UIN.equals(verifyResult.getCode())) {
                j.a(this.a, an.a(j.d(this.a), "成功", -1));
                j.c(this.a, verifyResult.getReq());
                j.d(this.a, verifyResult.getExpirTime());
                Log.i("SMSVerifyFragment", "mReq = " + j.f(this.a) + ", mReqExpirTime = " + j.g(this.a));
                i.a().i();
                j.h(this.a).a(j.f(this.a));
            } else {
                String msg = verifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                j.a(this.a, an.a(j.d(this.a), msg, -1));
            }
            if (j.e(this.a) != null) {
                j.e(this.a).show();
            }
        }
    }
}
