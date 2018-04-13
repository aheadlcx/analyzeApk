package cn.v6.sixrooms.avsolution.common;

public interface PlayerCallBack {
    void onBufferEmpty();

    void onBufferLoad();

    void onError(int i);

    void onVideoEnd();

    void onVideoSizeChange(int i, int i2);
}
