package qsbk.app.core.utils.stat;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.webkit.WebView;
import com.baidu.mobstat.SendStrategyEnum;
import com.baidu.mobstat.StatService;
import qsbk.app.core.utils.AppUtils;

public class BaiduStatistics {
    public static void init(String str) {
        init("b87defb52b", str);
    }

    public static void init(String str, String str2) {
        a(str);
        b(str2);
        a(30);
        a();
        a(false);
    }

    private static void a(String str) {
        StatService.setAppKey(str);
    }

    private static void b(String str) {
        StatService.setAppChannel(AppUtils.getInstance().getAppContext(), str, true);
    }

    private static void a(int i) {
        StatService.setLogSenderDelayed(i);
    }

    private static void a() {
        StatService.setSendLogStrategy(AppUtils.getInstance().getAppContext(), SendStrategyEnum.SET_TIME_INTERVAL, 1, false);
    }

    private static void a(boolean z) {
        StatService.setDebugOn(z);
    }

    public static void activityPageOnResume(Activity activity) {
        StatService.onResume(activity);
    }

    public static void acticityPageOnPause(Activity activity) {
        StatService.onPause(activity);
    }

    public static void fragmentPageOnResume(Fragment fragment) {
        StatService.onResume(fragment);
    }

    public static void fragmentPageOnPause(Fragment fragment) {
        StatService.onPause(fragment);
    }

    public static void onPageStart(String str) {
        StatService.onPageStart(AppUtils.getInstance().getAppContext(), str);
    }

    public static void onPageEnd(String str) {
        StatService.onPageEnd(AppUtils.getInstance().getAppContext(), str);
    }

    public static void onEvent(String str, String str2) {
        StatService.onEvent(AppUtils.getInstance().getAppContext(), str, str2);
    }

    public static void onEventStart(String str, String str2) {
        StatService.onEventStart(AppUtils.getInstance().getAppContext(), str, str2);
    }

    public static void onEventEnd(String str, String str2) {
        StatService.onEventEnd(AppUtils.getInstance().getAppContext(), str, str2);
    }

    public static void bindJSInterface(Context context, WebView webView) {
        StatService.bindJSInterface(context, webView);
    }
}
