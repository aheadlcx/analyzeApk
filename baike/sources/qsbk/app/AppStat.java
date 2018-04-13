package qsbk.app;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.utils.LogUtil;

public class AppStat {
    static FrameCallback a = null;
    private static long b = 0;
    private static String c = null;
    public static boolean canSend = true;
    public static long main_on_create_end_delta = 0;
    public static long main_on_create_start_delta = 0;
    public static long main_on_resume_delta = 0;

    public interface OnFpsResultListener {
        void onFpsResult(int[] iArr);
    }

    public static void setAppStartTime() {
        b = SystemClock.uptimeMillis();
    }

    public static boolean canSend() {
        return canSend;
    }

    public static void sendStat() {
        LogUtil.d("main oncreate_start:" + main_on_create_start_delta);
        LogUtil.d("main oncreate_end:" + main_on_create_end_delta);
        LogUtil.d("main onresume :" + main_on_resume_delta);
        canSend = false;
        if (main_on_create_start_delta <= 10000 && QsbkApp.isInConfigRatio("m_start_du_ratio", 0)) {
            StatService.onEventDuration(QsbkApp.mContext, "stat", "m_on_create_start_delta", main_on_create_start_delta * 1000);
            StatService.onEventDuration(QsbkApp.mContext, "stat", "m_on_create_end_delta", main_on_create_end_delta * 1000);
            StatService.onEventDuration(QsbkApp.mContext, "stat", "m_on_resume_delta", main_on_resume_delta * 1000);
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "m_on_create_start_delta", main_on_create_start_delta);
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "m_on_create_end_delta", main_on_create_end_delta);
            StatSDK.onEventDuration(QsbkApp.mContext, "stat", "m_on_resume_delta", main_on_resume_delta);
        }
    }

    public static void startChoreographerCallback(int i, OnFpsResultListener onFpsResultListener) {
        long uptimeMillis = SystemClock.uptimeMillis();
        int[] iArr = new int[i];
        if (VERSION.SDK_INT >= 16) {
            if (a == null) {
                a = new a(uptimeMillis, i, iArr, onFpsResultListener);
            }
            Choreographer.getInstance().postFrameCallback(a);
        }
    }

    public static void reportAppStart(String str) {
        if (TextUtils.isEmpty(c)) {
            c = str;
            LogUtil.d("app start with:" + str + " and system start on:" + Process.getElapsedCpuTime());
        }
    }

    public static void startANRDetect(Handler handler) {
        handler.getLooper().getThread().getStackTrace();
        handler.post(new b());
    }
}
