package cn.v6.sixrooms.mvp.interfaces;

public interface IResetPasswordRunnable {
    void error(int i);

    void fail();

    String getAuthCode();

    String getConfirmPassword();

    String getMobileNumber();

    String getPassword();

    String getUid();

    String getUserName();

    void hideLoading();

    void resetPwdSucceed();

    void showErrorDialog(String str);

    void showLoading();
}
