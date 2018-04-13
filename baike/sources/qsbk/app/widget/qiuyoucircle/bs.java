package qsbk.app.widget.qiuyoucircle;

import android.os.Message;
import qsbk.app.utils.VersionUtil;

class bs extends Thread {
    final /* synthetic */ a a;
    final /* synthetic */ UnsupportCell b;

    bs(UnsupportCell unsupportCell, String str, a aVar) {
        this.b = unsupportCell;
        this.a = aVar;
        super(str);
    }

    public void run() {
        if (this.a != null) {
            boolean manualCheck = VersionUtil.manualCheck(this.b.getContext());
            if (this.a != null) {
                Message obtainMessage = this.a.obtainMessage();
                obtainMessage.obj = Boolean.valueOf(manualCheck);
                obtainMessage.sendToTarget();
            }
        }
    }
}
