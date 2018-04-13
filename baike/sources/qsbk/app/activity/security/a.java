package qsbk.app.activity.security;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.utils.ToastAndDialog;

class a extends Handler {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    a(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void handleMessage(Message message) {
        if (message.what != 0) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(0)).show();
            return;
        }
        this.a.handleToken(this.a.s);
        this.a.g();
        Intent intent = new Intent(this.a, MainActivity.class);
        intent.addFlags(67108864);
        this.a.startActivity(intent);
        this.a.finish();
        if (QsbkApp.register != null) {
            QsbkApp.register.finish();
        }
    }
}
