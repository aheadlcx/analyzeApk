package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import qsbk.app.utils.ToastAndDialog;

class ca extends Handler {
    final /* synthetic */ AuditNativeActivity a;

    ca(AuditNativeActivity auditNativeActivity, Looper looper) {
        this.a = auditNativeActivity;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case -1:
                postDelayed(AuditNativeActivity.g(this.a), 1500);
                break;
            case 0:
                AuditNativeActivity.h(this.a);
                break;
            case 1:
                AuditNativeActivity.a(this.a, AuditNativeActivity.i(this.a).size());
                AuditNativeActivity.b(this.a, -1);
                break;
            case 11:
                new Builder(this.a).setTitle("温馨提示").setMessage((String) message.obj).setPositiveButton("确定", new cb(this)).show();
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
