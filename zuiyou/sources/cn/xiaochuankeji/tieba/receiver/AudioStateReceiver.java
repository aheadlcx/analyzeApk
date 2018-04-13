package cn.xiaochuankeji.tieba.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import cn.xiaochuankeji.tieba.background.d.a;
import org.greenrobot.eventbus.c;

public class AudioStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        a aVar = new a();
        aVar.a = 0;
        a aVar2 = new a();
        aVar2.a = 1;
        if (intent.getAction().equals("com.android.music.playstatechanged")) {
            if (((AudioManager) context.getSystemService("audio")).isMusicActive()) {
                c.a().d(aVar);
            }
        } else if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            c.a().d(aVar);
        } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
            aVar.b = 0;
            c.a().d(aVar);
        } else if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
            aVar2.b = 0;
            c.a().d(aVar2);
        } else {
            switch (((TelephonyManager) context.getSystemService("phone")).getCallState()) {
                case 0:
                    c.a().d(aVar2);
                    return;
                case 1:
                    c.a().d(aVar);
                    return;
                case 2:
                    c.a().d(aVar);
                    return;
                default:
                    return;
            }
        }
    }
}
