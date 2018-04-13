package com.budejie.www.activity.phonenumber;

import android.text.TextUtils;
import com.budejie.www.type.SendSMSVerifyResult;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import net.tsz.afinal.a.a;

class k$3 extends a<String> {
    final /* synthetic */ k a;

    k$3(k kVar) {
        this.a = kVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            SendSMSVerifyResult sendSMSVerifyResult = (SendSMSVerifyResult) z.a(str, SendSMSVerifyResult.class);
            if (sendSMSVerifyResult == null) {
                k.a(this.a, an.a(k.e(this.a), "获取验证码失败", -1));
                k.f(this.a).show();
            } else if (Constants.DEFAULT_UIN.equals(sendSMSVerifyResult.getCode())) {
                k.a(this.a, sendSMSVerifyResult.getSeq());
                k.b(this.a, sendSMSVerifyResult.getExpirTime());
                i.a().a(k.d(this.a));
            } else {
                String msg = sendSMSVerifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                k.a(this.a, an.a(k.e(this.a), msg, -1));
                k.f(this.a).show();
            }
        }
    }
}
