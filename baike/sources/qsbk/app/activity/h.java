package qsbk.app.activity;

import android.os.Message;
import qsbk.app.utils.VersionUtil;

class h extends Thread {
    final /* synthetic */ AboutSettingActivity a;

    h(AboutSettingActivity aboutSettingActivity, String str) {
        this.a = aboutSettingActivity;
        super(str);
    }

    public void run() {
        if (this.a.g != null) {
            boolean manualCheck = VersionUtil.manualCheck(this.a);
            if (this.a.g != null) {
                Message obtainMessage = this.a.g.obtainMessage();
                obtainMessage.obj = Boolean.valueOf(manualCheck);
                obtainMessage.sendToTarget();
            }
        }
    }
}
