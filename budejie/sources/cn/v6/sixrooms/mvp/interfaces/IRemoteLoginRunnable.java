package cn.v6.sixrooms.mvp.interfaces;

import android.app.Activity;

public interface IRemoteLoginRunnable {
    void error(int i);

    Activity getActivity();

    String getCode();

    String getTicket();

    void handleErrorInfo(String str, String str2);

    void handleVerifySucceed(String str);

    void hideLoading();

    void logOut();

    void remoteLoginSucceed(String str);

    void showLoading(boolean z);
}
