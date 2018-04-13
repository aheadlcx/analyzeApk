package cn.v6.sixrooms.mvp.interfaces;

public interface IVerifyMessageRunnable {
    void bindSucceed();

    void cleanPromat();

    void error(int i);

    String getCode();

    String getPassword();

    String getPhoneNumber();

    void handleErrorInfo(String str, String str2);

    void handleVerifySucceed(String str);

    void hideLoading();

    void showLoading(boolean z);
}
