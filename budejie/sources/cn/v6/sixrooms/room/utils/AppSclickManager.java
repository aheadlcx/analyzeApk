package cn.v6.sixrooms.room.utils;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;

public class AppSclickManager {
    public static final String KEY = "IS_NEED_SEND";
    private static AppSclickManager a;

    public enum Area {
        TOP_BANANER("top-bananer"),
        U_PANEL("u-panel"),
        MENU("menu"),
        BANANER(StatisticCodeTable.BANANER),
        CENTER("center"),
        BOTTOM("bottom");
        
        String a;

        private Area(String str) {
            this.a = str;
        }

        public final String getArea() {
            return this.a;
        }
    }

    public enum Mod {
        UPANEL("upanel"),
        STAR("star"),
        TOP_FOLLOW("top_follow"),
        MENU("menu"),
        UINFO("uinfo"),
        RGIFTS("rgifts"),
        HISTORY("history"),
        HEVENT("hevent"),
        GAME("gmae"),
        SHOP(AlibcConstants.SHOP),
        PAY("pay"),
        SETING("seting"),
        ALL("all"),
        R10(CommonStrs.TYPE_HOTSTAR),
        R5(CommonStrs.TYPE_SUPERSTAR),
        R4(CommonStrs.TYPE_BIGSTAR),
        R1(CommonStrs.TYPE_STAR),
        R2(CommonStrs.TYPE_RED),
        U1(CommonStrs.TYPE_DANCE),
        GEQU("gequ"),
        U2(CommonStrs.TYPE_MC),
        U6("u6"),
        U3(CommonStrs.TYPE_TALK),
        INDEX("index"),
        BOTTOM_FOLLOW("follow"),
        TOP("top"),
        SEARCH(StatisticCodeTable.SEARCH),
        VIDEO("video"),
        BANANER(StatisticCodeTable.BANANER),
        LSLIDE("lslide"),
        RSLIDE("rslide"),
        REC("rec");
        
        String a;

        private Mod(String str) {
            this.a = str;
        }

        public final String getMod() {
            return this.a;
        }
    }

    public enum Page {
        INDEX;
        
        String a;

        private Page() {
            this.a = r3;
        }
    }

    public static AppSclickManager getInstance() {
        if (a == null) {
            a = new AppSclickManager();
        }
        return a;
    }

    private AppSclickManager() {
    }

    public void send(Page page, Area area, Mod mod, String str) {
        if (page == null || area == null || mod == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("sorry whatever can not be empty");
        }
        String str2 = "http://sclick.6rooms.com/a.html?uid=" + a() + "&page=" + page.a + "&area=" + area.getArea() + "&mod=" + mod.getMod() + "&target=" + str;
        LogUtils.i("AppSclickManager", "url:" + str2);
        LogUtils.i("AppSclickManager", "mod:" + mod.getMod());
        LogUtils.i("AppSclickManager", "area:" + area.getArea());
        a(str2);
    }

    public void send(Page page, Area area, String str, String str2) {
        if (page == null || area == null || str == null || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("sorry whatever can not be empty");
        }
        String str3 = "http://sclick.6rooms.com/a.html?uid=" + a() + "&page=" + page.a + "&area=" + area.getArea() + "&mod=" + str + "&target=" + str2;
        LogUtils.i("AppSclickManager", "url:" + str3);
        LogUtils.i("AppSclickManager", "mod:" + str);
        LogUtils.i("AppSclickManager", "area:" + area.getArea());
        a(str3);
    }

    public void send(Page page, Area area, String str) {
        if (page == null || area == null || str == null) {
            throw new IllegalArgumentException("sorry whatever can not be empty");
        }
        String str2 = "http://sclick.6rooms.com/a.html?uid=" + a() + "&page=" + page.a + "&area=" + area.getArea() + "&mod=" + str;
        LogUtils.i("AppSclickManager", "url:" + str2);
        LogUtils.i("AppSclickManager", "mod:" + str);
        LogUtils.i("AppSclickManager", "area:" + area.getArea());
        a(str2);
    }

    public void send(Page page, Area area, Mod mod) {
        if (page == null || area == null || mod == null) {
            throw new IllegalArgumentException("sorry whatever can not be empty");
        }
        String str = "http://sclick.6rooms.com/a.html?uid=" + a() + "&page=" + page.a + "&area=" + area.getArea() + "&mod=" + mod.getMod();
        LogUtils.i("AppSclickManager", "url:" + str);
        LogUtils.i("AppSclickManager", "mod:" + mod.getMod());
        LogUtils.i("AppSclickManager", "area:" + area.getArea());
        a(str);
    }

    private void a(String str) {
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), str, "");
    }

    private static String a() {
        if (GlobleValue.getUserBean() != null) {
            return GlobleValue.getUserBean().getId();
        }
        String loginUID = LoginUtils.getLoginUID();
        if (TextUtils.isEmpty(loginUID)) {
            return SaveUserInfoUtils.getVisitorId(V6Coop.getInstance().getContext());
        }
        return loginUID;
    }
}
