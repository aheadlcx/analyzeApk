package cn.v6.sixrooms.room.interfaces;

public interface LaunchNotificationViewable {
    public static final byte ALREADY_SUBSCRIBE = (byte) 1;
    public static final byte SUBSCRIBE_OK = (byte) 0;

    void error(int i);

    void handleErrorInfo(String str, String str2);

    void refreshNotificationUI(boolean z);

    void showMessage(byte b);
}
