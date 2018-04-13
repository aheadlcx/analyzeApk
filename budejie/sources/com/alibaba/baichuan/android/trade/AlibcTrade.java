package com.alibaba.baichuan.android.trade;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alibaba.baichuan.android.trade.adapter.applink.AlibcApplink;
import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.adapter.ut.UserTrackerCompoment;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.component.AlibcComponent;
import com.alibaba.baichuan.android.trade.config.AlibcConfig;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.InitResult;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.utils.AlibcTradeHelper;
import com.alibaba.baichuan.android.trade.utils.a;
import com.alibaba.baichuan.android.trade.utils.d;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AlibcTrade {
    public static final String ALI_URL = "^(?:https?):\\/\\/(([^/\\?#]+\\.)*((taobao|tmall|juhuasuan|xiami|amap|taobaocdn|alipay|etao|alibaba|aliyun|alimama|weibo|tanx|laiwang|alicdn|mmstat|yunos|alibaba-inc|alitrip|aliloan|kanbox|wirlesshare|dingtalk|alimei|cnzz|kuaidadi|autonavi|m\\.yintai|polyinno|spdyidea|h5util|h5tool|5945i|miaostreet|1688|cainiao|taohua|m\\.dfkhgj|m\\.edcdfg|liangxinyao|taopiaopiao)\\.com|(tb|tbcdn|weibo|mashort|mybank|ba\\.ugame\\.uc|game\\.uc)\\.cn|(fastidea|juzone)\\.(me|cc)|lwurl\\.to|(taobao|alipay|cnzz)\\.net|tdd\\.la|yao\\.95095\\.com|(tmall|alipay)\\.hk|ahd\\.so|atb\\.so|mshare\\.cc|juhs\\.me|xianyu\\.mobi)([\\?|#|/].*)?)$";
    public static final int SHOW_FAILURE = -1;
    public static final int SHOW_H5 = 1;
    public static final int SHOW_NATIVE = 0;
    private static AlibcShowParams a;
    private static AlibcTaokeParams b;
    private static Map c;
    private static Map d;
    private static String e = AlibcTrade.class.getName();

    private static void a(AlibcBasePage alibcBasePage) {
        if (alibcBasePage == null) {
            a.a(e, "sendUsabilityFailure", "tradePage is null!");
            AlibcLogger.e(e, "tradePage is null");
            return;
        }
        UserTrackerCompoment.sendUseabilitySuccess(alibcBasePage.getUsabilityPageType() + (alibcBasePage.isNativeOpen(a) ? UserTrackerConstants.U_NATIVE : UserTrackerConstants.U_H5));
    }

    private static void a(AlibcBasePage alibcBasePage, String str) {
        if (alibcBasePage == null) {
            a.a(e, "sendUsabilityFailure", "tradePage is null!");
            AlibcLogger.e(e, "tradePage is null");
            return;
        }
        UserTrackerCompoment.sendUseabilityFailure(alibcBasePage.getUsabilityPageType() + (alibcBasePage.isNativeOpen(a) ? UserTrackerConstants.U_NATIVE : UserTrackerConstants.U_H5), str);
    }

    private static void a(AlibcBasePage alibcBasePage, Map map) {
        if (alibcBasePage.getApi() != null) {
            Map hashMap = new HashMap();
            hashMap.putAll(map);
            if (!TextUtils.isEmpty(AlibcContext.getAppKey())) {
                hashMap.put("appkey", AlibcContext.getAppKey());
            }
            hashMap.put(UserTrackerConstants.TAOKEPARAMS, b == null ? "{}" : b.toString());
            AlibcUserTracker.getInstance().sendCustomHit(alibcBasePage.getApi(), "", hashMap);
        }
    }

    private static void a(Map map) {
        if (map == null || map.get(AlibcConstants.ISV_CODE) == null) {
            AlibcContext.isvCode = null;
        } else {
            AlibcContext.isvCode = (String) map.get(AlibcConstants.ISV_CODE);
        }
    }

    private static void b(Activity activity, WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient, String str, AlibcBasePage alibcBasePage, com.alibaba.baichuan.android.trade.b.a aVar, boolean z) {
        Map hashMap = new HashMap();
        hashMap.put(AlibcConstants.TAOKE_PARAM, b);
        hashMap.put("ui_contextParams", d);
        hashMap.put(AlibcConstants.SHOW_BY_H5, String.valueOf(!alibcBasePage.isNativeOpen(a)));
        hashMap.put(AlibcConstants.BACK_LOGIN_FAIL, String.valueOf(alibcBasePage.isBackWhenLoginFail()));
        hashMap.put(UserTrackerConstants.U_LABEL, alibcBasePage.getUsabilityPageType() + UserTrackerConstants.U_H5);
        AlibcLogger.d(e, "h5打开，上下文参数为params=" + hashMap);
        aVar.a(UserTrackerConstants.PM_URL_LOAD_TIME);
        if (webView == null || AlibcContext.executorService == null) {
            AlibcLogger.i(e, "使用Trade的webview打开");
            AlibcComponent.INSTANCE.show(activity, str, (Serializable) hashMap, aVar);
            return;
        }
        AlibcLogger.i(e, "使用第三方webview打开");
        AlibcComponent.INSTANCE.show(activity, str, webView, webViewClient, webChromeClient, hashMap, aVar, alibcBasePage.getAdditionalHttpHeaders(), z);
    }

    public static boolean isAliUrl(String str) {
        return (TextUtils.isEmpty(ALI_URL) || TextUtils.isEmpty(str) || !str.matches(ALI_URL)) ? false : true;
    }

    public static int show(Activity activity, WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient, AlibcBasePage alibcBasePage, AlibcShowParams alibcShowParams, AlibcTaokeParams alibcTaokeParams, Map map, AlibcTradeCallback alibcTradeCallback) {
        AlibcLogger.i(e, "AlibcTrade.show(): AlibcPage: " + (alibcBasePage == null ? "null" : alibcBasePage.toString()) + " taoke.pid: " + (alibcTaokeParams == null ? "null" : alibcTaokeParams.pid) + " activity:" + (activity == null ? "null" : activity.toString()));
        InitResult initResult = AlibcTradeSDK.initResult;
        if (initResult != null && !initResult.isSuccess()) {
            d.a(alibcTradeCallback, 1001, "初始化失败, " + initResult.errorMessage);
            return -1;
        } else if (AlibcTradeSDK.initState.isInitializing()) {
            d.a(alibcTradeCallback, 1002, "初始化未完成,请稍后!");
            return -1;
        } else {
            a = alibcShowParams;
            if (a == null) {
                a = new AlibcShowParams();
            }
            com.alibaba.baichuan.android.trade.b.a aVar = new com.alibaba.baichuan.android.trade.b.a(alibcBasePage, a);
            aVar.a(UserTrackerConstants.PM_ALL_TIME);
            aVar.a(UserTrackerConstants.PM_ANALYSIS_TIME);
            if (AlibcTradeHelper.checkParams(alibcBasePage, activity, aVar, alibcTradeCallback)) {
                AlibcContext.isShowTitleBar = alibcShowParams.isShowTitleBar();
                a(map);
                c = map;
                d = AlibcTradeHelper.createUrlParams(c);
                AlibcLogger.d(e, "url 参数为 mUrlParams=" + d);
                aVar.e = alibcTradeCallback;
                b = alibcTaokeParams;
                if (b == null && AlibcConfig.getInstance().getNBTTradeTaokeParams() != null) {
                    b = AlibcConfig.getInstance().getTaokeParams();
                    AlibcLogger.d(e, "使用全局淘客参数,taokeParams=" + alibcTaokeParams);
                }
                boolean isNativeOpen = alibcBasePage.isNativeOpen(a);
                AlibcApplink.getInstance();
                if (!AlibcApplink.isApplinkSupported(a.getClientType())) {
                    AlibcLogger.i(e, "不支持applink，通过H5打开");
                    isNativeOpen = false;
                }
                aVar.b(UserTrackerConstants.PM_ANALYSIS_TIME);
                if (isNativeOpen) {
                    AlibcLogger.d(e, "通过native打开，打开参数为taokeparamers=" + b + " showparamers=" + a + " urlParamers=" + d);
                    aVar.a(UserTrackerConstants.PM_GO_TAOBAO_TIME);
                    if (alibcBasePage.tryNativeJump(b, a, d, activity)) {
                        AlibcLogger.i(e, "通过applink打开手淘成功");
                        aVar.b(UserTrackerConstants.PM_GO_TAOBAO_TIME);
                        aVar.b(UserTrackerConstants.PM_ALL_TIME);
                        UserTrackerCompoment.sendPerfomancePoint(aVar.b);
                        a(alibcBasePage, d);
                        a(alibcBasePage);
                        return 0;
                    }
                    a(alibcBasePage, UserTrackerConstants.EM_APPLINK_REQUEST_ERROR);
                    aVar.a(alibcBasePage);
                }
                if (alibcBasePage.needTaoke(b)) {
                    AlibcLogger.i(e, "进行淘客打点");
                    alibcBasePage.taokeTraceAndGenUrl(b, aVar, new a(alibcBasePage, activity, webView, webViewClient, webChromeClient, aVar, alibcShowParams));
                } else {
                    b(activity, webView, webViewClient, webChromeClient, alibcBasePage.getAddParamsUrl(d, aVar), alibcBasePage, aVar, alibcShowParams.isProxyWebview());
                }
                a(alibcBasePage, d);
                return 1;
            }
            AlibcLogger.e(e, "page检测参数出错，流程中止");
            if (alibcBasePage == null) {
                alibcBasePage = new AlibcBasePage();
            }
            a(alibcBasePage, UserTrackerConstants.EM_PARAM_ERROR);
            return -1;
        }
    }

    public static int show(Activity activity, AlibcBasePage alibcBasePage, AlibcShowParams alibcShowParams, AlibcTaokeParams alibcTaokeParams, Map map, AlibcTradeCallback alibcTradeCallback) {
        return show(activity, null, null, null, alibcBasePage, alibcShowParams, alibcTaokeParams, map, alibcTradeCallback);
    }
}
