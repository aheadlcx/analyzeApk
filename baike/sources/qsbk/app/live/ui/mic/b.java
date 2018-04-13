package qsbk.app.live.ui.mic;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.IRtcEngineEventHandler$RtcStats;
import qsbk.app.core.utils.LogUtils;

class b extends IRtcEngineEventHandler {
    final /* synthetic */ MicEngineEventHandler a;

    b(MicEngineEventHandler micEngineEventHandler) {
        this.a = micEngineEventHandler;
    }

    public void onFirstRemoteVideoDecoded(int i, int i2, int i3, int i4) {
        LogUtils.d("MicEngineEventHandler", "onFirstRemoteVideoDecoded " + (((long) i) & 4294967295L) + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i3 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i4);
        for (MicAGEventHandler onFirstRemoteVideoDecoded : this.a.d.keySet()) {
            onFirstRemoteVideoDecoded.onFirstRemoteVideoDecoded(i, i2, i3, i4);
        }
    }

    public void onFirstLocalVideoFrame(int i, int i2, int i3) {
        LogUtils.d("MicEngineEventHandler", "onFirstLocalVideoFrame " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i3);
    }

    public void onUserJoined(int i, int i2) {
        LogUtils.d("MicEngineEventHandler", "onUserJoined uid:" + i + " elapsed:" + i2);
        for (MicAGEventHandler onUserJoined : this.a.d.keySet()) {
            onUserJoined.onUserJoined(i, i2);
        }
    }

    public void onUserOffline(int i, int i2) {
        for (MicAGEventHandler onUserOffline : this.a.d.keySet()) {
            onUserOffline.onUserOffline(i, i2);
        }
    }

    public void onUserMuteVideo(int i, boolean z) {
    }

    public void onRtcStats(IRtcEngineEventHandler$RtcStats iRtcEngineEventHandler$RtcStats) {
    }

    public void onLeaveChannel(IRtcEngineEventHandler$RtcStats iRtcEngineEventHandler$RtcStats) {
    }

    public void onLastmileQuality(int i) {
        LogUtils.d("MicEngineEventHandler", "onLastmileQuality " + i);
    }

    public void onError(int i) {
        super.onError(i);
        LogUtils.d("MicEngineEventHandler", "onError " + i);
    }

    public void onJoinChannelSuccess(String str, int i, int i2) {
        LogUtils.d("MicEngineEventHandler", "onJoinChannelSuccess " + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (((long) i) & 4294967295L) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2);
        for (MicAGEventHandler onJoinChannelSuccess : this.a.d.keySet()) {
            onJoinChannelSuccess.onJoinChannelSuccess(str, i, i2);
        }
    }

    public void onRejoinChannelSuccess(String str, int i, int i2) {
        LogUtils.d("MicEngineEventHandler", "onRejoinChannelSuccess " + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2);
    }

    public void onWarning(int i) {
        LogUtils.d("MicEngineEventHandler", "onWarning " + i);
    }
}
