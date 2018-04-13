package cn.v6.sixrooms.room.verify;

public interface VerifyEngine$CallBack {
    void error(int i);

    void handleErrorInfo(String str, String str2);

    void result(String str);
}
