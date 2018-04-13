package qsbk.app.live.receiver;

import android.telephony.PhoneStateListener;
import qsbk.app.core.utils.LogUtils;

class a extends PhoneStateListener {
    final /* synthetic */ PhoneStateReceiver a;
    private int b = 0;

    a(PhoneStateReceiver phoneStateReceiver) {
        this.a = phoneStateReceiver;
    }

    public void onCallStateChanged(int i, String str) {
        String str2 = "PhoneStateReceiver";
        switch (i) {
            case 0:
                LogUtils.d(str2, "live phone state 挂掉");
                if (this.b == 2 && this.a.b != null) {
                    this.a.b.onCallIdle();
                    break;
                }
            case 1:
                LogUtils.d(str2, "live phone state 有来电，号码是：" + str);
                break;
            case 2:
                LogUtils.d(str2, "live phone state 通話中");
                if (this.a.b != null) {
                    this.a.b.onCallOffHook();
                    break;
                }
                break;
        }
        this.b = i;
        super.onCallStateChanged(i, str);
    }
}
