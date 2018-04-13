package cn.v6.sixrooms.room.gift;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.engine.WebGiftEngine;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class GiftWebview extends WebView {
    private static final int DONE = 3;
    private static final String JS_OBJ = "appAndroid";
    private static final int LOAD_RES = 1;
    private static final int PLAYING = 2;
    private static final String URL_PREFIX = "http://m.v.6.cn/gift-animation";
    private Callback callback;
    private Gift mCurrentGift;
    private int mCurrentState = 3;
    private int mGiftNum;
    private Map<String, GiftSlowMessage> mGiftStatisticsMap = new HashMap();
    private Handler mHandler = new Handler();
    private int mLoadResTimeCount;

    public interface Callback {
        void animComplete();

        void animCount(int i, int i2);

        void animError(String str);

        void animReStart();

        void animStart();

        void animTimeout();
    }

    public class AndroidJavaScript {
        @JavascriptInterface
        public void animComplete(String str, long j, long j2) {
            GiftWebview.this.resetLoad();
            GiftWebview.this.mGiftNum = GiftWebview.this.mGiftNum - 1;
            if (j2 != 0) {
                GiftWebview.this.saveGiftStatistics(str, j, j2);
            }
            if (GiftWebview.this.mGiftNum > 0) {
                GiftWebview.this.runLoadUrl();
                if (GiftWebview.this.callback != null) {
                    GiftWebview.this.callback.animCount(GiftWebview.this.mCurrentGift.getNum(), GiftWebview.this.mGiftNum);
                }
            } else if (GiftWebview.this.mHandler != null) {
                GiftWebview.this.mHandler.post(new GiftWebview$AndroidJavaScript$1(this));
            }
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public GiftWebview(Context context) {
        super(context);
        init();
    }

    public GiftWebview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public GiftWebview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void init() {
        setLayerType(1, null);
        setBackgroundColor(0);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        addJavascriptInterface(new AndroidJavaScript(), JS_OBJ);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(1);
        getSettings().setUseWideViewPort(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUserAgentString(SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setWebViewClient(new GiftWebview$GiftWebViewClient(this));
    }

    public void loadGift(Gift gift) {
        if (gift != null) {
            checkGiftState(gift);
        }
    }

    private void checkGiftState(Gift gift) {
        if (this.mCurrentState != 3) {
            if (this.callback != null) {
                this.callback.animReStart();
            }
        } else if ("3".equals(gift.getGtype()) || "4".equals(gift.getGtype())) {
            this.mCurrentGift = gift;
            this.mGiftNum = this.mCurrentGift.getNum();
            runLoadUrl();
        } else if (this.callback != null) {
            this.callback.animError("要播放的礼物动画，不是H5礼物!");
        }
    }

    private void runLoadUrl() {
        this.mHandler.post(new GiftWebview$1(this));
    }

    private void saveGiftStatistics(String str, long j, long j2) {
        Object obj = (GiftSlowMessage) this.mGiftStatisticsMap.get(str);
        if (obj == null) {
            obj = new GiftSlowMessage();
            obj.gid = str;
            obj.duration = String.valueOf(j);
            obj.sumDuration = String.valueOf(j2);
            obj.min = String.valueOf(j2);
            obj.max = String.valueOf(j2);
            obj.count = "1";
            obj.average = String.valueOf(j2);
        } else {
            obj.count = String.valueOf(Integer.valueOf(obj.count).intValue() + 1);
            obj.sumDuration = String.valueOf(Long.parseLong(obj.sumDuration) + j2);
            if (Long.parseLong(obj.min) > j2) {
                obj.min = String.valueOf(j2);
            }
            if (Long.parseLong(obj.max) < j2) {
                obj.max = String.valueOf(j2);
            }
            obj.average = String.valueOf(Float.parseFloat(obj.sumDuration) / Float.parseFloat(obj.count));
        }
        this.mGiftStatisticsMap.put(str, obj);
    }

    private void checkLoadResTime() {
        if (this.mCurrentState != 2) {
            this.mHandler.postDelayed(new GiftWebview$2(this), 1000);
        }
    }

    private void resetLoad() {
        this.mHandler.post(new GiftWebview$3(this));
    }

    public void cleanLoadGiftAnimation() {
        resetLoad();
        if (this.callback != null) {
            this.callback.animComplete();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        destroy();
    }

    public static void clearWebViewCache() {
        V6Coop.getInstance().getContext().deleteDatabase("webview.db");
        V6Coop.getInstance().getContext().deleteDatabase("webviewCache.db");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void uploadService() {
        if (this.mGiftStatisticsMap != null && this.mGiftStatisticsMap.size() > 0) {
            WebGiftEngine.uploadGiftSlowData(new Gson().toJson((GiftSlowMessage[]) this.mGiftStatisticsMap.values().toArray(new GiftSlowMessage[0])));
        }
    }
}
