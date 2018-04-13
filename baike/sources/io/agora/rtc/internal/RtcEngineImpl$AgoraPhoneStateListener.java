package io.agora.rtc.internal;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import java.lang.reflect.Method;

class RtcEngineImpl$AgoraPhoneStateListener extends PhoneStateListener {
    private SignalStrength mSignalStrenth;
    final /* synthetic */ RtcEngineImpl this$0;

    private RtcEngineImpl$AgoraPhoneStateListener(RtcEngineImpl rtcEngineImpl) {
        this.this$0 = rtcEngineImpl;
    }

    public int getRssi() {
        return invokeMethod("getDbm");
    }

    public int getLevel() {
        return invokeMethod("getLevel");
    }

    public int getAsuLevel() {
        return invokeMethod("getAsuLevel");
    }

    private int invokeMethod(String str) {
        try {
            if (this.mSignalStrenth != null) {
                Method declaredMethod = this.mSignalStrenth.getClass().getDeclaredMethod(str, new Class[0]);
                if (declaredMethod != null) {
                    return ((Integer) declaredMethod.invoke(this.mSignalStrenth, new Object[0])).intValue();
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        this.mSignalStrenth = signalStrength;
    }

    public void onCallStateChanged(int i, String str) {
        super.onCallStateChanged(i, str);
        if (RtcEngineImpl.access$000(this.this$0) != null) {
            switch (i) {
                case 0:
                    Logging.i("RtcEngine", "system phone call end");
                    this.this$0.enableAudio();
                    RtcEngineImpl.access$000(this.this$0).sendEvent(22, 0);
                    return;
                case 1:
                    Logging.i("RtcEngine", "system phone call ring");
                    this.this$0.disableAudio();
                    RtcEngineImpl.access$000(this.this$0).sendEvent(22, 1);
                    return;
                case 2:
                    Logging.i("RtcEngine", "system phone call start");
                    RtcEngineImpl.access$000(this.this$0).sendEvent(22, 2);
                    return;
                default:
                    return;
            }
        }
    }
}
