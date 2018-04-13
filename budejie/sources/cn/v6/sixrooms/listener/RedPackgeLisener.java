package cn.v6.sixrooms.listener;

public interface RedPackgeLisener {
    void clickPackage(String str);

    void onGetFailResult(String str, String str2, String str3);

    void onGetSuccResult(String str, String str2);

    void onReceiveBigFireworks(String[] strArr);

    void onReceiveFireworksTimeEnd(String[] strArr, String[] strArr2);

    void onReceiveSuperFireworks(String[] strArr, String[] strArr2);

    void onRedPackageNone(String str);
}
