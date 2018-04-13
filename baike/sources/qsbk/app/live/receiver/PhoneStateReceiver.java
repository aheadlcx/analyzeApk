package qsbk.app.live.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStateReceiver extends BroadcastReceiver {
    private TelephonyManager a;
    private OnPhoneStateListener b;
    private PhoneStateListener c = new a(this);

    public interface OnPhoneStateListener {
        void onCallIdle();

        void onCallOffHook();
    }

    public PhoneStateReceiver(OnPhoneStateListener onPhoneStateListener) {
        this.b = onPhoneStateListener;
    }

    public void onReceive(Context context, Intent intent) {
        this.a = (TelephonyManager) context.getSystemService("phone");
        this.a.listen(this.c, 32);
    }

    public void onDestroy() {
        if (this.a != null) {
            this.a.listen(this.c, 0);
            this.a = null;
        }
    }
}
