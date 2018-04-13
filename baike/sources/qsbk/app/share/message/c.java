package qsbk.app.share.message;

import android.os.Handler;
import android.os.Message;

class c extends Handler {
    final /* synthetic */ MyDialog a;

    c(MyDialog myDialog) {
        this.a = myDialog;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == this.a.a) {
            this.a.dismiss();
        }
    }
}
