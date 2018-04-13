package cn.v6.sixrooms.login.interfaces;

public interface RegisterCallback extends LoginClientCallback, PassportRegisterCallback {
    void getAuthCodeSuccess(String str);
}
