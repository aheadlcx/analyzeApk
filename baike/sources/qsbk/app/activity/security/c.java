package qsbk.app.activity.security;

import android.os.Handler;
import android.os.Message;

class c extends Handler {
    final /* synthetic */ ActionBarSecurityBindActivity a;

    c(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
        this.a = actionBarSecurityBindActivity;
    }

    public void handleMessage(Message message) {
        this.a.setTitle("设定密保");
        if (message.what == 1) {
            this.a.f.setText((String) message.obj);
        } else {
            this.a.e.setText((String) message.obj);
        }
        this.a.u = true;
    }
}
