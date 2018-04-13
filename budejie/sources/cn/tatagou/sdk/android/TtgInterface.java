package cn.tatagou.sdk.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.tatagou.sdk.activity.TtgMainActivity;
import cn.tatagou.sdk.b.c;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.util.a;
import cn.tatagou.sdk.util.m;
import java.util.HashMap;
import java.util.Map;

public class TtgInterface {
    public static final String COMMODITYD_DETAILS = "commoditydDetails";
    public static final int TBLOGIN = 1;
    public static final int TBLOGOOUT = -1;
    public static final String TB_AUTHORIZE = "tbAuthorize";

    public static void initView(Context context, int i) {
        TtgConfig.getInstance().setPid(i);
        b.ttgStatEvent(String.valueOf(i));
        b.cateStatEvent("1");
    }

    public static void openTtgMain(Context context, String str, int i) {
        m.openTtgMain(context, str, i);
    }

    public static void openTabTtgMain(Context context, String str, int i) {
        m.openTabTtgMain(context, str, i);
    }

    public static void recoverTtgMain(Context context, String str, int i) {
        m.recoverTtgMain(context, str, i);
    }

    public static void openTtgMainView(String str, int i) {
        m.openTtgMainView(str, i);
    }

    public static Map<String, String> getSdkInfo(Context context) {
        return m.getSdkInfo(context);
    }

    public static void apConfig(TtgCallback ttgCallback) {
        cn.tatagou.sdk.util.b.apConfig(ttgCallback);
    }

    public static void checkTaobaoLogin() {
        a.checkoutTaobaoLogin();
    }

    public static void cancelLazyLoad() {
    }

    public static void openTsqItem(Context context, String str, String str2, int i) {
        TtgConfig.getInstance().setPid(i);
        m.openH5(context, str, 8, str2);
    }

    public static void openBcCarts(Context context) {
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "SOURCE");
        m.openBcCartOrOrder(context, null, 2, hashMap);
    }

    public static void openBcCarts(Context context, Map<String, String> map) {
        m.openBcCartOrOrder(context, null, 2, map);
    }

    public static void openBcOrders(Context context) {
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "SOURCE");
        m.openBcCartOrOrder(context, null, 3, hashMap);
    }

    public static void openBcOrders(Context context, Map<String, String> map) {
        m.openBcCartOrOrder(context, null, 3, map);
    }

    public static void openTtgFeedBack(Context context) {
        m.openTtgFeedBack(context, null);
    }

    public static void openTtgFeedBack(Context context, Map<String, String> map) {
        m.openTtgFeedBack(context, map);
    }

    public static void countUnreadFeedback(TtgCallback ttgCallback) {
        m.countUnreadFeedback(ttgCallback);
    }

    public static void openPubFeedBack(Context context, Map<String, String> map) {
        m.openPubFeedBack(context, map);
    }

    public static void openTtgMine(Context context) {
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "SOURCE");
        m.openTtgMine(context, hashMap);
    }

    public static void openTtgMine(Context context, Map<String, String> map) {
        m.openTtgMine(context, map);
    }

    public static void listAllCates(String str, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void listTop10Items(String str, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void listAllItems(String str, int i, int i2, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void listAllItems2(String str, int i, int i2, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void listCateItems(String str, String str2, int i, int i2, String str3, String str4, TtgCallback ttgCallback) {
    }

    public static void listCateItems2(String str, String str2, int i, int i2, String str3, String str4, TtgCallback ttgCallback) {
    }

    public static void getTopItem(String str, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void listAllSps(String str, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void getTopSpecial(String str, String str2, String str3, TtgCallback ttgCallback) {
    }

    public static void apHint(String str, String str2, TtgCallback ttgCallback) {
    }

    public static String getSdkVersion() {
        return "2.4.4.6";
    }

    public static boolean isTtgMainAct() {
        return TtgMainActivity.sIsActShow;
    }

    public static void reportLaunch() {
        b.launchStatEvent();
    }

    public static void testAPI() {
        cn.tatagou.sdk.util.b.openReportLaunch();
    }

    public static void clearCache() {
    }

    public static void clearDBCache() {
        cn.tatagou.sdk.b.a.clearDate();
        AppHomeData.getInstance().clearHistory();
    }

    public static void clearSqlLiteData(Context context) {
        SQLiteDatabase openDatabase = c.getInstance(context).openDatabase();
        c.getInstance(context);
        c.a.onUpgrade(openDatabase, 0, 0);
    }

    public static void setStartSerViceSource(Context context, String str) {
        cn.tatagou.sdk.e.a.init(context).setSource(str).setAppSource(str);
    }
}
