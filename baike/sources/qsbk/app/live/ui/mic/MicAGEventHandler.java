package qsbk.app.live.ui.mic;

public interface MicAGEventHandler {
    void onFirstRemoteVideoDecoded(int i, int i2, int i3, int i4);

    void onJoinChannelSuccess(String str, int i, int i2);

    void onUserJoined(int i, int i2);

    void onUserOffline(int i, int i2);
}
