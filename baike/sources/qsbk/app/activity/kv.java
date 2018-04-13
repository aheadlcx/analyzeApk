package qsbk.app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.security.ActionBarSecurityBindActivity;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.ToastAndDialog;

class kv extends Handler {
    final /* synthetic */ FillUserDataActivity a;

    kv(FillUserDataActivity fillUserDataActivity) {
        this.a = fillUserDataActivity;
    }

    public void handleMessage(Message message) {
        Bundle data = message.getData();
        switch (message.what) {
            case 0:
                CharSequence string = data.getString("name");
                this.a.h = data.getString("avatar");
                if ("qzuser".equalsIgnoreCase(string) && ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(this.a.j)) {
                    this.a.c.setText(string);
                } else if (TextUtils.isEmpty(string)) {
                    this.a.c.setText("");
                } else {
                    this.a.c.setText(string);
                }
                if (!this.a.f) {
                    FrescoImageloader.displayAvatar(this.a.b, this.a.h);
                    return;
                }
                return;
            case 1:
                ToastAndDialog.makeNeutralToast(this.a, data.getString("msg"), Integer.valueOf(0)).show();
                this.a.c.setHighlightColor(Color.parseColor("#f86f45"));
                this.a.c.setCursorVisible(true);
                this.a.c.setSelected(true);
                this.a.l.cancel();
                return;
            case 2:
                try {
                    this.a.l.cancel();
                    JSONObject jSONObject = new JSONObject(data.getString(ActionBarSecurityBindActivity.KEY_RESPONSE));
                    int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
                    if (i == 0) {
                        this.a.a(jSONObject);
                        this.a.a(this.a.i, this.a.k, this.a.j.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token");
                        this.a.g();
                        ToastAndDialog.makePositiveToast(this.a, "欢迎来到糗事百科哦~，" + this.a.c.getText().toString(), Integer.valueOf(0)).show();
                    } else {
                        ToastAndDialog.makeNegativeToast(this.a, jSONObject.getString("err_msg"), Integer.valueOf(0)).show();
                    }
                    if (i == 0) {
                        BindPhoneActivity.launchCanSkip(this.a, this.a.j, this.a.i);
                        this.a.setResult(-1);
                        this.a.finish();
                        return;
                    }
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 3:
                if (this.a.l.isShowing()) {
                    this.a.l.cancel();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
