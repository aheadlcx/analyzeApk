package io.agora.rtc.internal;

import android.content.Context;
import android.view.OrientationEventListener;

class RtcEngineImpl$1 extends OrientationEventListener {
    final /* synthetic */ RtcEngineImpl this$0;

    RtcEngineImpl$1(RtcEngineImpl rtcEngineImpl, Context context, int i) {
        this.this$0 = rtcEngineImpl;
        super(context, i);
    }

    public void onOrientationChanged(int i) {
        if (RtcEngineImpl.access$200(this.this$0) && i != -1) {
            RtcEngineImpl.access$300(this.this$0, i);
        }
    }
}
