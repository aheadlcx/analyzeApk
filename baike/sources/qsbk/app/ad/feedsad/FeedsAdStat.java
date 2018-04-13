package qsbk.app.ad.feedsad;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import java.lang.ref.WeakReference;
import qsbk.app.QsbkApp;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Util;

public class FeedsAdStat {
    private static Handler handler = new Handler(handlerThread.getLooper());
    private static HandlerThread handlerThread = new HandlerThread("FeedsAdStat");

    private static class a implements Runnable {
        WeakReference<View> a;
        String b;

        public a(View view, String str) {
            this.a = new WeakReference(view);
            this.b = str;
        }

        public void run() {
            View view = (View) this.a.get();
            if (view != null && view.getParent() != null && view.hasWindowFocus() && view.getWindowVisibility() == 0 && view.getVisibility() == 0) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                if (iArr[1] >= 0 && iArr[1] + view.getHeight() <= Util.displaySize.y) {
                    FeedsAdStat.stat(view.getContext(), "feed_ads_post_show", this.b);
                }
            }
        }
    }

    static {
        handlerThread.start();
    }

    public static void onGDTClick(Context context) {
        handler.post(new b(context));
    }

    public static void onMobisageClick(Context context) {
        onClick(context, "mobisage");
    }

    public static void onClick(Context context, String str) {
        if (context != null && str != null) {
            StatSDK.onEvent(context, "feed_ads_click_" + HttpUtils.getNetwork(QsbkApp.mContext), str);
            StatService.onEvent(context, "feed_ads_click_" + HttpUtils.getNetwork(QsbkApp.mContext), str);
        }
    }

    public static void stat(View view, String str) {
        if (view != null && str != null) {
            stat(view.getContext(), "feed_ads_show_" + HttpUtils.getNetwork(QsbkApp.mContext), str);
            postStat(view, str);
        }
    }

    private static void stat(Context context, String str, String str2) {
        StatSDK.onEvent(context, str, str2);
        StatService.onEvent(context, str, str2);
    }

    private static void postStat(View view, String str) {
        handler.postDelayed(new a(view, str), 500);
    }
}
