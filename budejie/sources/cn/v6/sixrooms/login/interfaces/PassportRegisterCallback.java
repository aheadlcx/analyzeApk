package cn.v6.sixrooms.login.interfaces;

public interface PassportRegisterCallback {
    void error(int i);

    void getTicketError(int i);

    void getTicketSuccess(String str);

    void perRegisterError(int i);

    void perRegisterSuccess(boolean z);
}
