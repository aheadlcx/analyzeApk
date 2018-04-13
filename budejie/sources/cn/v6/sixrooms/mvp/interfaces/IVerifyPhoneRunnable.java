package cn.v6.sixrooms.mvp.interfaces;

public interface IVerifyPhoneRunnable {
    void error(int i);

    String getPassword();

    String getPhoneNumber();

    void handleErrorResult(String str, String str2);

    void hideLoading();

    void showLoading();

    void verifyPhoneSucceed();
}
