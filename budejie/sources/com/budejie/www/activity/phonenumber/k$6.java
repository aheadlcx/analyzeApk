package com.budejie.www.activity.phonenumber;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.a;

class k$6 extends a<String> {
    final /* synthetic */ k a;
    private ProgressDialog b;

    k$6(k kVar) {
        this.a = kVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        super.onStart();
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (this.b != null && this.b.isShowing()) {
            this.b.cancel();
        }
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            Log.e("wuzhenlin", str);
            if (this.b != null && this.b.isShowing()) {
                this.b.cancel();
            }
            if (TextUtils.isEmpty(str)) {
                an.a(k.e(this.a), "注册失败", -1).show();
                return;
            }
            Map d = z.d(str);
            if (d != null && !d.isEmpty()) {
                String str2 = (String) d.get("msg");
                if (Constants.DEFAULT_UIN.equals((String) d.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                    String str3 = (String) d.get("id");
                    k.g(this.a).a(str3, d);
                    ai.a(k.e(this.a), str3, com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE);
                    if ("MyAccountActivity".equals(k.b(this.a))) {
                        an.a(k.e(this.a), "绑定成功", -1).show();
                    } else if ("phone_register".equals(k.b(this.a))) {
                        an.a(k.e(this.a), "注册成功", -1).show();
                    }
                    k.n(this.a).a();
                    return;
                }
                an.a(k.e(this.a), str2, -1).show();
            }
        }
    }
}
