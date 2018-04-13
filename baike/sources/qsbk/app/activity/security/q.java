package qsbk.app.activity.security;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ToastAndDialog;

class q implements OnClickListener {
    final /* synthetic */ PhoneVerifyActivity a;

    q(PhoneVerifyActivity phoneVerifyActivity) {
        this.a = phoneVerifyActivity;
    }

    public void onClick(View view) {
        if (!HttpUtils.isNetworkConnected(this.a)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, this.a.getResources().getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        } else if (!this.a.a(this.a.c.getText().toString())) {
            ToastAndDialog.makeText(this.a, "请输入的电话号码").show();
        } else if (this.a.b(this.a.d.getText().toString())) {
            this.a.submit();
        } else {
            ToastAndDialog.makeText(this.a, "请输入登录密码").show();
        }
    }
}
