package com.alibaba.baichuan.android.trade;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.adapter.security.AlibcSecurityGuard;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.utils.g;
import com.ut.device.UTDevice;
import java.util.Arrays;
import java.util.List;

public class AlibcContext {
    public static String ETICKET_URL;
    public static String HOME_URL = "https://h5.m.taobao.com/";
    public static String MINI_SHOUTAO_ITEM_DETAIL_URL;
    public static String MY_ORDERS_URL;
    public static String PROMOTION_URL;
    public static String SHOUTAO_ITEM_DETAIL_URL;
    public static String SHOW_CART_URL;
    public static String TB_ITEM_DETAIL_URL;
    public static String TB_SHOP_URL;
    public static String TMALL_ITEM_DETAIL_URL;
    public static String[] TRADE_APLIPAY_URLS = new String[]{"maliprod.alipay.com/w/trade_pay.do", "maliprod.alipay.com/batch_payment.do", "mclient.alipay.com/w/trade_pay.do", "mclient.alipay.com/batch_payment.do"};
    private static String a;
    private static String b;
    public static volatile Context context;
    public static String[] detailPatterns = new String[]{"^http(s)?://h5\\.(.*)\\.taobao.com/cm/snap/index\\.html(.*)", "^http(s)?://h5\\.(.*)\\.taobao\\.com/awp/core/detail\\.htm(.*)", "^http(s)?://detail\\.(.*)\\.tmall\\.(com|hk)/item\\.htm(.*)"};
    public static volatile Environment environment = Environment.ONLINE;
    public static final g executorService = g.a();
    public static List firstLevelKeys = Arrays.asList(new String[]{AlibcConstants.ISV_CODE, AlibcConstants.SCM, AlibcConstants.PVID});
    public static boolean isDebugMode = false;
    public static volatile boolean isShowTitleBar = true;
    public static boolean isVip = false;
    public static volatile String isvCode;
    public static String[] nativeOpenPatterns = new String[]{"^http(s)?://h5\\.(.*)\\.taobao.com/cm/snap/index\\.html(.*)", "^http(s)?://h5\\.(.*)\\.taobao\\.com/awp/core/detail\\.htm(.*)", "^http(s)?://detail\\.(.*)\\.tmall\\.(com|hk)/item\\.htm(.*)", "^http(s)?://shop\\.m\\.taobao\\.com/shop/shop_index\\.htm(.*)", "^http(s)?://s\\.taobao\\.com/(.*)", "^http(s)?://s\\.click\\.taobao\\.com/(.*)"};
    public static String sclickPattern = "^http(s)?://s\\.click\\.taobao\\.com/(.*)";
    public static String sdkVersion = "3.1.1.9";
    public static boolean showErrorInReleaseMode = false;

    public enum Environment {
        TEST,
        ONLINE,
        PRE,
        SANDBOX
    }

    public static String getAppKey() {
        if (a == null) {
            a = AlibcSecurityGuard.getInstance().getAppKey();
        }
        return a;
    }

    public static String getUtdid() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        b = UTDevice.getUtdid(context);
        return b;
    }

    public static void initData() {
        updateUrl();
    }

    public static boolean isDebuggable() {
        return isDebugMode;
    }

    public static void setShowErrorInReleaseMode(boolean z) {
        showErrorInReleaseMode = z;
    }

    public static void updateUrl() {
        TMALL_ITEM_DETAIL_URL = AlibcUrlCenter.instance.getValue("trade.tmallTradeItemUrlHost", environment);
        TB_ITEM_DETAIL_URL = AlibcUrlCenter.instance.getValue("trade.taobaoTradeItemUrlHost", environment);
        SHOUTAO_ITEM_DETAIL_URL = AlibcUrlCenter.instance.getValue("trade.taobaoMobileTradeItemUrlHost", environment);
        MY_ORDERS_URL = AlibcUrlCenter.instance.getValue("trade.myOrdersUrl", environment);
        ETICKET_URL = AlibcUrlCenter.instance.getValue("trade.eTicketDetailUrl", environment);
        PROMOTION_URL = AlibcUrlCenter.instance.getValue("trade.promotionsUrl", environment);
        TB_SHOP_URL = AlibcUrlCenter.instance.getValue("trade.shopUrlHost", environment);
        SHOW_CART_URL = AlibcUrlCenter.instance.getValue("trade.cartUrl", environment);
        MINI_SHOUTAO_ITEM_DETAIL_URL = AlibcUrlCenter.instance.getValue("trade.miniTaobaoItemUrlHost", environment);
    }
}
