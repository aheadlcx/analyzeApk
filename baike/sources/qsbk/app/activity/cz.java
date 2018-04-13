package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import qsbk.app.utils.ToastAndDialog;

class cz extends Handler {
    final /* synthetic */ AuditNativeActivity2 a;

    cz(AuditNativeActivity2 auditNativeActivity2, Looper looper) {
        this.a = auditNativeActivity2;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case -1:
                postDelayed(this.a.S, 1500);
                break;
            case 0:
                this.a.k();
                break;
            case 1:
                this.a.a(this.a.K.size());
                this.a.b(-1);
                break;
            case 11:
                new Builder(this.a).setTitle("温馨提示").setMessage((String) message.obj).setPositiveButton("确定", new da(this)).show();
                break;
            case 12:
                ToastAndDialog.makeNeutralToast(this.a, (String) message.obj).show();
                break;
            case 108:
                ToastAndDialog.makeNeutralToast(this.a, "即将跳转登录...").show();
                break;
        }
        super.handleMessage(message);
    }
}
