package cn.v6.sixrooms.mvp.interfaces;

import android.app.Activity;

public interface ILoginRunnable {
    void clearUsernamePassword();

    Activity getActivity();

    String getPassword();

    String getUsername();

    void gtError(String str);

    void hideLoading();

    void hideSystemInput();

    void loginError(int i);

    void loginSuccess(String str);

    void noNetwork();

    void remoteLogin(boolean z, String str, String str2);

    void requestCode(int i);

    void showLoading();
}
