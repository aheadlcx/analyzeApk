package qsbk.app.activity.group;

import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.QsbkApp;

public class SplashAdStatUtil {
    public static void loadingGDT() {
        StatService.onEvent(QsbkApp.mContext, "SplashAd", "loading_GDTSplashAd");
        StatSDK.onEvent(QsbkApp.mContext, "SplashAd", "loading_GDTSplashAd");
    }

    public static void showGDT() {
        StatService.onEvent(QsbkApp.mContext, "SplashAd", "show_GDTSplashAd");
        StatSDK.onEvent(QsbkApp.mContext, "SplashAd", "show_GDTSplashAd");
    }

    public static void loadingBaidu() {
        StatService.onEvent(QsbkApp.mContext, "SplashAd", "loading_BDSplashAd");
        StatSDK.onEvent(QsbkApp.mContext, "SplashAd", "loading_BDSplashAd");
    }

    public static void showBaidu() {
        StatService.onEvent(QsbkApp.mContext, "SplashAd", "show_BDSplashAd");
        StatSDK.onEvent(QsbkApp.mContext, "SplashAd", "show_BDSplashAd");
    }

    public static void loadSelfFail() {
        StatService.onEvent(QsbkApp.mContext, "SplashAdFailed", "1.5sRequestFail");
        StatSDK.onEvent(QsbkApp.mContext, "SplashAdFailed", "1.5sRequestFail");
    }

    public static void loadSelfTimeout() {
        StatService.onEvent(QsbkApp.mContext, "SplashAdFailed", "TimeOut_close");
        StatSDK.onEvent(QsbkApp.mContext, "SplashAdFailed", "TimeOut_close");
    }
}
