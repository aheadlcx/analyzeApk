package io.agora.rtc.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;

public class ConnectionChangeBroadcastReceiver extends BroadcastReceiver {
    private WeakReference<RtcEngineImpl> mEngine;

    public ConnectionChangeBroadcastReceiver(RtcEngineImpl rtcEngineImpl) {
        this.mEngine = new WeakReference(rtcEngineImpl);
    }

    public void onReceive(Context context, Intent intent) {
        RtcEngineImpl rtcEngineImpl = (RtcEngineImpl) this.mEngine.get();
        if (rtcEngineImpl == null) {
            Logging.w("rtc engine is not ready");
        } else {
            rtcEngineImpl.notifyNetworkChange();
        }
    }
}
