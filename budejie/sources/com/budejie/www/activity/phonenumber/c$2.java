package com.budejie.www.activity.phonenumber;

import android.text.TextUtils;
import com.budejie.www.type.SendSMSVerifyResult;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import net.tsz.afinal.a.a;

class c$2 extends a<String> {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
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
                c.a(this.a, an.a(c.f(this.a), "获取验证码失败", -1));
                c.g(this.a).show();
            } else if (Constants.DEFAULT_UIN.equals(sendSMSVerifyResult.getCode())) {
                c.a(this.a, sendSMSVerifyResult.getSeq());
                c.b(this.a, sendSMSVerifyResult.getExpirTime());
                i.a().a(c.c(this.a));
                i.a().b(c.d(this.a));
                c.e(this.a).a(c.c(this.a), c.d(this.a));
            } else {
                String msg = sendSMSVerifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                c.a(this.a, an.a(c.f(this.a), msg, -1));
                c.g(this.a).show();
            }
        }
    }
}
