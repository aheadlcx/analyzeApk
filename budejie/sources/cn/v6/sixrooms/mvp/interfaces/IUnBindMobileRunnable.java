package cn.v6.sixrooms.mvp.interfaces;

public interface IUnBindMobileRunnable {
    void error(int i);

    String getCode();

    void handleErrorInfo(String str, String str2);

    void handleVerifySucceed(String str);

    void hideLoading();

    void showLoading(boolean z);

    void unbundleSucceed();
}
