package com.budejie.www.activity.phonenumber;

import android.text.TextUtils;
import android.util.Log;
import com.budejie.www.type.SendSMSVerifyResult;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import net.tsz.afinal.a.a;

class j$1 extends a<String> {
    final /* synthetic */ j a;

    j$1(j jVar) {
        this.a = jVar;
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
                j.a(this.a, an.a(j.d(this.a), "获取验证码失败", -1));
                j.e(this.a).show();
            } else if (Constants.DEFAULT_UIN.equals(sendSMSVerifyResult.getCode())) {
                j.a(this.a, sendSMSVerifyResult.getSeq());
                j.b(this.a, sendSMSVerifyResult.getExpirTime());
                i.a().a(j.a(this.a));
                i.a().b(j.b(this.a));
                Log.i("SMSVerifyFragment", "mSeq =" + j.a(this.a) + ", expirTime = " + j.c(this.a));
            } else {
                String msg = sendSMSVerifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                j.a(this.a, an.a(j.d(this.a), msg, -1));
                j.e(this.a).show();
            }
        }
    }
}
