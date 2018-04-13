package qsbk.app.activity.security;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class d extends Handler {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    d(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void handleMessage(Message message) {
        this.a.setTitle("设定密保");
        if (message.what != 0) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(0)).show();
            return;
        }
        try {
            if (this.a.n.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                this.a.e.setText(this.a.s.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA));
                QsbkApp.currentUser.wb = this.a.s.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
            } else {
                this.a.f.setText(this.a.s.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ));
                QsbkApp.currentUser.wb = this.a.s.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        if (!TextUtils.isEmpty(this.a.v)) {
            this.a.finish();
        }
    }
}
