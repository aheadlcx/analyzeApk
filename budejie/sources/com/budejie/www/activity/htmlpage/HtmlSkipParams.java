package com.budejie.www.activity.htmlpage;

import java.util.HashMap;
import java.util.Map;

public class HtmlSkipParams {
    public static Map<String, HtmlSkipParams$HtmlKey> a = new HashMap();
    public static Map<String, String> b = new HashMap();
    public static Map<String, HtmlSkipParams$HtmlTypeCate> c = new HashMap();
    public static Map<String, HtmlTypeTougao> d = new HashMap();
    public static Map<String, HtmlSkipParams$HtmlMyCate> e = new HashMap();

    public enum HtmlTypeTougao {
        SEND_PICTURE,
        SEND_SHORTJOKE,
        SEND_VOICE
    }

    static {
        a.put("App_To_AliPay", HtmlSkipParams$HtmlKey.App_To_AliPay);
        a.put("App_To_GetGameInfo", HtmlSkipParams$HtmlKey.App_To_GetGameInfo);
        a.put("App_To_Login", HtmlSkipParams$HtmlKey.App_To_Login);
        a.put("App_To_NewTopic", HtmlSkipParams$HtmlKey.App_To_NewTopic);
        a.put("App_To_H5", HtmlSkipParams$HtmlKey.App_To_H5);
        a.put("App_To_Shopping", HtmlSkipParams$HtmlKey.App_To_Shopping);
        a.put("BDJ_To_Mall", HtmlSkipParams$HtmlKey.BDJ_To_Mall);
        a.put("BDJ_Pop_Note", HtmlSkipParams$HtmlKey.BDJ_Pop_Note);
        a.put("BDJ_To_Cate", HtmlSkipParams$HtmlKey.BDJ_To_Cate);
        a.put("BDJ_To_Check", HtmlSkipParams$HtmlKey.BDJ_To_Check);
        a.put("BDJ_To_Mine", HtmlSkipParams$HtmlKey.BDJ_To_Mine);
        a.put("BDJ_To_Friends", HtmlSkipParams$HtmlKey.BDJ_To_Friends);
        a.put("BDJ_To_Home", HtmlSkipParams$HtmlKey.BDJ_To_Home);
        a.put("BDJ_To_MyForm", HtmlSkipParams$HtmlKey.BDJ_To_MyForm);
        a.put("BDJ_To_H5", HtmlSkipParams$HtmlKey.BDJ_To_H5);
        a.put("BDJ_To_Recommend", HtmlSkipParams$HtmlKey.BDJ_To_Recommend);
        a.put("BDJ_To_RecentHot", HtmlSkipParams$HtmlKey.BDJ_To_RecentHot);
        a.put("App_To_NearBy", HtmlSkipParams$HtmlKey.App_To_NearBy);
        a.put("App_To_MoreIcon", HtmlSkipParams$HtmlKey.App_To_MoreIcon);
        a.put("App_To_Activity", HtmlSkipParams$HtmlKey.App_To_Activity);
        a.put("App_To_ActivityDetail", HtmlSkipParams$HtmlKey.App_To_ActivityDetail);
        a.put("App_To_FeedBack", HtmlSkipParams$HtmlKey.App_To_FeedBack);
        a.put("BDJ_To_LaunchLiveShow", HtmlSkipParams$HtmlKey.BDJ_To_LaunchLiveShow);
        a.put("App_To_SearchUser", HtmlSkipParams$HtmlKey.App_To_SearchUser);
        a.put("App_To_Appwall", HtmlSkipParams$HtmlKey.App_To_Appwall);
        a.put("App_To_HTTP", HtmlSkipParams$HtmlKey.App_To_HTTP);
        a.put("App_To_MyVideo", HtmlSkipParams$HtmlKey.App_To_MyVideo);
        a.put("App_To_CreateShortcut", HtmlSkipParams$HtmlKey.App_To_CreateShortcut);
        a.put("BDJ_To_RankingList", HtmlSkipParams$HtmlKey.BDJ_To_RankingList);
        a.put("BDJ_To_FreeTraffic", HtmlSkipParams$HtmlKey.BDJ_To_FreeTraffic);
        a.put("BDJ_To_VipPay", HtmlSkipParams$HtmlKey.BDJ_To_VipPay);
        a.put("BDJ_To_SixRooms", HtmlSkipParams$HtmlKey.BDJ_To_SixRooms);
        a.put("BDJ_To_SRLiveRoom", HtmlSkipParams$HtmlKey.BDJ_To_SRLiveRoom);
        a.put("BDJ_To_ToTalk", HtmlSkipParams$HtmlKey.BDJ_To_ToTalk);
        a.put("BDJ_To_WallPaper", HtmlSkipParams$HtmlKey.BDJ_To_WallPaper);
        a.put("BDJ_To_HuaXiReader", HtmlSkipParams$HtmlKey.BDJ_To_HuaXiReader);
        c.put("1", HtmlSkipParams$HtmlTypeCate.HANDPICK);
        c.put("2", HtmlSkipParams$HtmlTypeCate.NEWEST);
        c.put("3", HtmlSkipParams$HtmlTypeCate.RANDOM);
        c.put("4", HtmlSkipParams$HtmlTypeCate.NEARBY);
        d.put("1", HtmlTypeTougao.SEND_PICTURE);
        d.put("2", HtmlTypeTougao.SEND_SHORTJOKE);
        d.put("3", HtmlTypeTougao.SEND_VOICE);
        e.put("1", HtmlSkipParams$HtmlMyCate.STICK);
        e.put("2", HtmlSkipParams$HtmlMyCate.COLLECT);
        e.put("3", HtmlSkipParams$HtmlMyCate.COMMENT);
    }
}
