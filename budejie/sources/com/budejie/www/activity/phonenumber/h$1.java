package com.budejie.www.activity.phonenumber;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.type.RegisterResult;
import com.budejie.www.util.z;
import com.tencent.connect.common.Constants;
import net.tsz.afinal.a.a;

class h$1 extends a<String> {
    final /* synthetic */ h a;
    private ProgressDialog b;

    h$1(h hVar) {
        this.a = hVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        super.onStart();
        this.b = ProgressDialog.show(h.a(this.a), "正在验证", "正在进行验证码验证，请稍等...");
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (this.b != null && this.b.isShowing()) {
            this.b.cancel();
        }
        Toast.makeText(h.a(this.a), "验证码验证失败。", 1000).show();
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            CharSequence string;
            Log.e("wuzhenlin", str);
            if (this.b != null && this.b.isShowing()) {
                this.b.cancel();
            }
            RegisterResult registerResult = (RegisterResult) z.a(str, RegisterResult.class);
            String msg = registerResult.getMsg();
            if (Constants.DEFAULT_UIN.equals(registerResult.getCode())) {
                string = this.a.getString(R.string.retrieve_pswd_success_msg);
                h.b(this.a).a();
            } else {
                Object obj = msg;
            }
            Toast.makeText(h.a(this.a), string, 1000).show();
        }
    }
}
