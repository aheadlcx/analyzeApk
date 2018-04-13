package cn.v6.sixrooms.mvp.interfaces;

import android.app.Activity;

public interface IFindUserOrPwdRunnable {
    void error(int i);

    Activity getActivity();

    String getAuthCode();

    String getPhoneNumber();

    String getUserName();

    void handleErrorInfo(String str, String str2);

    void hideLoading();

    void hideSystemInputManager();

    boolean isRetrieveName();

    void showLoading();
}
