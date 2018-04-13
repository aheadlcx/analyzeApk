package cn.v6.sixrooms.login.interfaces;

public interface LoginClientCallback {
    void error(int i);

    void handleErrorInfo(String str, String str2);

    void loginClientSuccess(String str, String str2);

    void loginOtherPlace(String str);
}
