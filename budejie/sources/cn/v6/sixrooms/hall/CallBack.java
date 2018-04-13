package cn.v6.sixrooms.hall;

public interface CallBack<T> {
    void error(int i);

    void handleErrorInfo(String str, String str2);

    void handleInfo(T t);
}
