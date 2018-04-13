package cn.v6.sixrooms.room.statistic;

import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StatisticValue {
    public static final String HOMEPAGE = "index";
    private static final String a = StatisticValue.class.getSimpleName();
    public static volatile StatisticValue instance = null;
    public static String recid = "";
    private static List<String> t = Arrays.asList(new String[]{CommonStrs.TYPE_LIANMAI, CommonStrs.TYPE_VRECOM, CommonStrs.TYPE_HEADLINE, CommonStrs.TYPE_FOLLOW, CommonStrs.TYPE_FOLLOW_FOCUS, CommonStrs.TYPE_FOLLOW_COMMON, CommonStrs.TYPE_SPECIAL});
    public static int title_index;
    private static List<String> u = Arrays.asList(new String[]{CommonStrs.TYPE_VRECOM, CommonStrs.TYPE_HEADLINE, CommonStrs.TYPE_SPECIAL});
    private String b = "HallActivity";
    private HashMap<String, HashMap<String, String>> c = new HashMap();
    private HashMap<String, String> d = new HashMap();
    private String e;
    private String f = "null";
    private String g = "null";
    private String h = "null";
    private String i = "null";
    private String j = "null";
    private String k = "null";
    private String l = "null";
    private String m = "null";
    private String n = "null";
    private String o = "null";
    private String p = "";
    private String q = "";
    private String r = "";
    private String s = "";
    private long v = 0;

    private StatisticValue() {
    }

    public static StatisticValue getInstance() {
        if (instance == null) {
            synchronized (StatisticManager.class) {
                if (instance == null) {
                    instance = new StatisticValue();
                }
            }
        }
        return instance;
    }

    public void setShowHomePage(String str) {
        this.b = str;
    }

    public String getShowHomePage() {
        return this.b;
    }

    public void clearReChargePageModule() {
        this.r = "";
        this.s = "";
        this.j = "null";
        this.k = "null";
    }

    public void clearRegisterPageModule() {
        this.p = "";
        this.q = "";
        this.h = "null";
        this.i = "null";
    }

    public void clearFromAttentionPageModule() {
        this.n = "null";
        this.o = "null";
    }

    public void setHomeFromRoomPageModule(String str, String str2) {
        this.f = getInstance().getHomeTypePage(str);
        this.g = str2;
    }

    public void setFromRoomPageModule(String str, String str2) {
        this.f = getInstance().getHomeTypePage(str);
        this.g = str2;
    }

    public String getFromFoomPageModule() {
        return this.f + "-" + this.g;
    }

    public String getFromRoomPage() {
        return this.f;
    }

    public String getFromRoomModule() {
        return this.g;
    }

    public void setFromRegisterPageModule(String str, String str2) {
        this.h = str;
        this.i = str2;
    }

    public void setHomeFromRegisterPageModule(String str, String str2) {
        this.h = getHomeTypePage(str);
        this.i = str2;
    }

    public void setRegisterPageModule(String str, String str2) {
        this.p = str;
        this.q = str2;
    }

    public String getFromRegisterPageModule() {
        return this.h + "-" + this.i;
    }

    public String getRegisterPage() {
        return this.p;
    }

    public String getRegisterModule() {
        return this.q;
    }

    public void setFromRechargePageModule(String str, String str2) {
        this.j = str;
        this.k = str2;
    }

    public String getFromRechargePageModule() {
        return this.j + "-" + this.k;
    }

    public void setRechargePageModule(String str, String str2) {
        this.r = str;
        this.s = str2;
    }

    public String getRechargePage() {
        return this.r;
    }

    public String getRechargeModule() {
        return this.s;
    }

    public void setTypeRecid(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put(str2, str3);
        this.c.put(str, hashMap);
    }

    public String getTypeRecid(String str, String str2) {
        if (this.c.get(str) == null) {
            return "";
        }
        if (((HashMap) this.c.get(str)).get(str2) == null) {
            return "";
        }
        return (String) ((HashMap) this.c.get(str)).get(str2);
    }

    public String getFromSpeakGiftPageModule() {
        return this.l + "-" + this.m;
    }

    public void setFromSpeakGiftGage(String str, String str2) {
        this.l = str;
        this.m = str2;
    }

    public String getFromAttentionPageModule() {
        return this.n + "-" + this.o;
    }

    public void setFromAttentionPageModule(String str, String str2) {
        this.n = str;
        this.o = str2;
    }

    public String getHomePageOnActivity(String str) {
        return str;
    }

    public void setPageType(String str) {
        this.e = str;
    }

    public void setPageTypePosition(int i) {
        switch (i) {
            case 0:
                getInstance().setPageType(CommonStrs.TYPE_ALL_ROOMLIST);
                return;
            case 1:
                getInstance().setPageType(CommonStrs.TYPE_MLIVE);
                return;
            case 2:
                getInstance().setPageType(CommonStrs.TYPE_LOCATION);
                return;
            case 3:
                getInstance().setPageType(CommonStrs.TYPE_MUSIC);
                return;
            case 4:
                getInstance().setPageType(CommonStrs.TYPE_DANCE);
                return;
            case 5:
                getInstance().setPageType(CommonStrs.TYPE_MC);
                return;
            case 6:
                getInstance().setPageType(CommonStrs.TYPE_TALK);
                return;
            case 7:
                getInstance().setPageType(CommonStrs.TYPE_MALE);
                return;
            default:
                return;
        }
    }

    public String getPageType() {
        return this.e;
    }

    public void setHomeTypePage(String str, String str2) {
        if (this.d != null) {
            this.d.put(str, str2);
        }
    }

    public String getHomeTypePage(String str) {
        if (this.d == null || this.d.get(this.e) == null) {
            return str;
        }
        return (String) this.d.get(this.e);
    }

    public String getHomeTypePage(String str, String str2) {
        if (this.d == null || this.d.get(str) == null) {
            return str2;
        }
        return (String) this.d.get(str);
    }

    public String getFormPageModule(String str) {
        return "index-" + str;
    }

    public String getHomeModule(String str) {
        if (CommonStrs.TYPE_ALL_ROOMLIST.equals(str) || "".equals(str)) {
            return StatisticCodeTable.HOT;
        }
        if (CommonStrs.TYPE_FOLLOW.equals(str)) {
            return "follow";
        }
        if (CommonStrs.TYPE_FOLLOW_FOCUS.equals(str)) {
            return "follow";
        }
        if (CommonStrs.TYPE_FOLLOW_COMMON.equals(str)) {
            return "follow";
        }
        return u.contains(str) ? str : str;
    }

    public String getDefaultPage(String str, String str2) {
        if (t.contains(str)) {
            return "index";
        }
        return str2;
    }

    public void setWatchid() {
        this.v = System.currentTimeMillis();
    }

    public long getWatchid() {
        return getWatchid("");
    }

    public long getWatchid(String str) {
        if (str.equals(StatisticCodeTable.ROOM) && this.v == 0) {
            this.v = System.currentTimeMillis();
            LogUtils.e(a, "重新生成watchid...");
        }
        return this.v;
    }

    public void clearWatchid() {
        this.v = 0;
    }
}
