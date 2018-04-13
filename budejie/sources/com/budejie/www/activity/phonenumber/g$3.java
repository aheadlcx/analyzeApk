package com.budejie.www.activity.phonenumber;

import android.app.ProgressDialog;
import android.text.TextUtils;
import com.budejie.www.type.SendSMSVerifyResult;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import net.tsz.afinal.a.a;

class g$3 extends a<String> {
    final /* synthetic */ g a;
    private ProgressDialog b;

    g$3(g gVar) {
        this.a = gVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        super.onStart();
        this.b = ProgressDialog.show(g.g(this.a), "正在验证", "正在进行验证，请稍等...");
    }

    public void onFailure(Throwable th, int i, String str) {
        if (this.b != null && this.b.isShowing()) {
            this.b.cancel();
        }
        super.onFailure(th, i, str);
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            if (this.b != null && this.b.isShowing()) {
                this.b.cancel();
            }
            SendSMSVerifyResult sendSMSVerifyResult = (SendSMSVerifyResult) z.a(str, SendSMSVerifyResult.class);
            if (sendSMSVerifyResult == null) {
                g.a(this.a, an.a(g.g(this.a), "获取验证码失败", -1));
                g.l(this.a).show();
            } else if (Constants.DEFAULT_UIN.equals(sendSMSVerifyResult.getCode())) {
                g.a(this.a, sendSMSVerifyResult.getSeq());
                g.b(this.a, sendSMSVerifyResult.getExpirTime());
                i.a().a(g.h(this.a));
                i.a().b(g.i(this.a));
                i.a().c(g.j(this.a));
                g.k(this.a).a(g.h(this.a));
            } else {
                String msg = sendSMSVerifyResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "获取验证码失败";
                }
                g.a(this.a, an.a(g.g(this.a), msg, -1));
                g.l(this.a).show();
            }
        }
    }
}
