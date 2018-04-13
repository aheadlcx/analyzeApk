package com.budejie.www.http;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.ui.phone.EventActivity;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.packet.d;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.sendCommendActivity;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.as.a;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.pro.x;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.b;

public class j extends b {
    private BudejieApplication c;

    public j() {
        this.c = BudejieApplication.b();
    }

    public j(Context context) {
        d("ver", "6.9.2");
        d("client", AlibcConstants.PF_ANDROID);
        d("market", "xiaomi");
        d("udid", an.e(context));
        d("mac", an.h(context));
        d("os", an.a());
        d("appname", "baisibudejie");
        d("visiting", an.f(context));
    }

    public Map<String, String> a(Context context, Map<String, String> map) {
        map.put("ver", "6.9.2");
        map.put("client", AlibcConstants.PF_ANDROID);
        map.put("market", "xiaomi");
        map.put("t", "" + (System.currentTimeMillis() / 1000));
        map.put("udid", an.e(context));
        map.put("mac", an.h(context));
        map.put("os", an.a());
        map.put("appname", "baisibudejie");
        map.put("visiting", an.f(context));
        return map;
    }

    public b a(Context context, String str) {
        b jVar = new j(context);
        jVar.d("url", str);
        return jVar;
    }

    public b a(Context context) {
        b jVar = new j(context);
        jVar.d("app", "1");
        return jVar;
    }

    public b a(Context context, String str, String str2, String str3, String str4, String str5) {
        b jVar = new j(context);
        jVar.d("url", str);
        jVar.d("title", str2);
        jVar.d("theme_id", str3);
        jVar.d("theme_name", str4);
        jVar.d("vote", str5);
        return jVar;
    }

    public b b(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "logout");
        jVar.d(d.n, str);
        return jVar;
    }

    public b c(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "modify_extra_info");
        jVar.d("introduction", str);
        return jVar;
    }

    public b a(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "fans_list");
        jVar.d("userid", str);
        jVar.d("follow_id", str2);
        return jVar;
    }

    public b d(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "follow");
        jVar.d("userid", str);
        return jVar;
    }

    public b e(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "unfollow");
        jVar.d("userid", str);
        return jVar;
    }

    public b b(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "follow_list");
        jVar.d("userid", str);
        jVar.d("follow_id", str2);
        return jVar;
    }

    public static String a(String str) {
        return new h("http://d.api.budejie.com/user/interest").b(str).a().toString();
    }

    public static String a() {
        return new h("http://d.api.budejie.com/user/interest/read").toString();
    }

    public b f(Context context, String str) {
        b jVar = new j(context);
        jVar.d("f_uid", str);
        return jVar;
    }

    public b g(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "follow_ids");
        jVar.d("userid", str);
        jVar.d("need_name", "1");
        return jVar;
    }

    public b c(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "follow");
        jVar.d("userid", str);
        jVar.d(UserTrackerConstants.FROM, str2);
        return jVar;
    }

    public b d(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "guide");
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        jVar.d("pre", str2);
        return jVar;
    }

    public b h(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "report_user");
        jVar.d("userid", str);
        return jVar;
    }

    public b i(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "modifybg");
        try {
            jVar.a("bgimage", new File(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jVar;
    }

    public b e(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", StatisticCodeTable.SEARCH);
        jVar.d("uname", str);
        jVar.d("pre", "50");
        jVar.d("page", str2);
        return jVar;
    }

    public b f(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "tag_search");
        jVar.d("limit", "50");
        jVar.d("type", str);
        jVar.d("kw", str2);
        return jVar;
    }

    public b j(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "user_login_report");
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        return jVar;
    }

    public b k(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "themes");
        jVar.d("page", str);
        jVar.d("pagesize", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        return jVar;
    }

    public b g(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "theme_info");
        jVar.d("theme_id", str);
        jVar.d("theme_name", str2);
        return jVar;
    }

    public b a(Context context, String str, boolean z) {
        b jVar = new j(context);
        jVar.d("c", "subscribe");
        jVar.d("a", "tags");
        jVar.d("theme_id", str);
        jVar.d("status", z ? RoomActivity.VIDEOTYPE_UNKNOWN : "1");
        return jVar;
    }

    public b l(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "theme_list");
        jVar.d("data_type", str);
        jVar.d("theme_type", "1");
        return jVar;
    }

    public b b(Context context) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "tag_recommend");
        jVar.d("action", "sub");
        jVar.d("type", "0");
        return jVar;
    }

    public b c(Context context) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "recommend");
        return jVar;
    }

    public b m(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "tag_search");
        jVar.d("limit", "50");
        jVar.d("kw", str);
        return jVar;
    }

    public b h(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "comment");
        jVar.d("a", "delete");
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        jVar.d(IXAdRequestInfo.CELL_ID, str2);
        return jVar;
    }

    public b n(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "changeaccount");
        jVar.d("req", str);
        return jVar;
    }

    public b a(Context context, String str, String str2, a aVar) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "social_list");
        jVar.d("pre", str2);
        jVar.d("last_flag", aVar.a());
        jVar.d("last_coord", aVar.b());
        jVar.d("platform", str);
        return jVar;
    }

    public b a(Context context, String str, String str2, String str3, String str4) {
        b jVar = new j(context);
        jVar.d("c", "weibo");
        jVar.d("a", "invite");
        jVar.d("type", str);
        jVar.d(INoCaptchaComponent.token, str2);
        if (str3 != null) {
            jVar.d("openid", str3);
        }
        jVar.d("userlist", str4);
        jVar.d("format", "json");
        return jVar;
    }

    public b a(Context context, String str, a aVar) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "friend_recommend");
        jVar.d("pre", "50");
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        jVar.d("last_flag", aVar.a());
        jVar.d("last_coord", aVar.b());
        return jVar;
    }

    public b i(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", com.alipay.sdk.sys.a.j);
        jVar.d(str, str2);
        return jVar;
    }

    public b a(Context context, String str, String str2, String str3) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", EventActivity.GOV_REPORT_EVENT);
        jVar.d(c.c, str);
        jVar.d(com.alipay.sdk.cons.b.c, str3);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str2);
        return jVar;
    }

    public b a(Activity activity, String str, String str2, String str3) {
        Map a = a((Context) activity, new HashMap());
        a.put("c", "topic");
        a.put("a", "rate");
        a.put("action", str3);
        a.put(com.alipay.sdk.cons.b.c, str2);
        a.put(HistoryOpenHelper.COLUMN_UID, str);
        return e.a("/api/api_open.php", a);
    }

    public b a(Activity activity, String str, String str2) {
        b jVar = new j(activity);
        jVar.d("c", "topic");
        jVar.d("a", "deltopic");
        jVar.d(com.alipay.sdk.cons.b.c, str);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str2);
        return jVar;
    }

    public b a(Context context, com.sina.weibo.sdk.auth.b bVar, String str) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "binding");
        jVar.d("type", "sina");
        jVar.d("app", "8");
        jVar.d("account", "");
        jVar.d("format", "json");
        jVar.d(INoCaptchaComponent.token, bVar.c() + "##");
        if (!TextUtils.isEmpty(str)) {
            jVar.d("id", str);
        }
        return jVar;
    }

    public b a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "modify");
        jVar.d("id", str);
        if (!TextUtils.isEmpty(str2)) {
            jVar.d("gender", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            jVar.d("name", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            jVar.d("age_group", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            jVar.d("degree", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            jVar.d("birthday", str6);
        }
        if (!TextUtils.isEmpty(str7)) {
            jVar.d("qq", str7);
        }
        return jVar;
    }

    public b b(Context context, String str, String str2, String str3) {
        b jVar = new j(context);
        jVar.d("c", "bookmark");
        jVar.d("a", str);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str2);
        jVar.d("ids", str3);
        return jVar;
    }

    public b j(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "bookmark");
        jVar.d("a", "list");
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        jVar.d("version_bm", str2);
        return jVar;
    }

    public b b(Context context, String str, String str2, String str3, String str4) {
        b jVar = new j(context);
        jVar.d("c", "comment");
        jVar.d("a", "dataList");
        jVar.d("data_id", str);
        jVar.d("userID", str2);
        jVar.d("per", str3);
        jVar.d("page", "1");
        jVar.d(StatisticCodeTable.HOT, "1");
        jVar.d("lastcid", str4);
        return jVar;
    }

    public b c(Context context, String str, String str2, String str3) {
        b jVar = new j(context);
        jVar.d("c", "comment");
        jVar.d("a", EventActivity.GOV_REPORT_EVENT);
        jVar.d("pid", str3);
        jVar.d(IXAdRequestInfo.CELL_ID, str);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str2);
        return jVar;
    }

    public b o(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "data");
        jVar.d("a", "istotal");
        jVar.d(UserTrackerConstants.FROM, AlibcConstants.PF_ANDROID);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        return jVar;
    }

    public b c(Context context, String str, String str2, String str3, String str4) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "binding");
        jVar.d("account", str2);
        jVar.d("type", "qq");
        jVar.d("app", "8");
        jVar.d(INoCaptchaComponent.token, str3);
        jVar.d("openid", str4);
        jVar.d("format", "json");
        if (!TextUtils.isEmpty(str)) {
            jVar.d("id", str);
        }
        jVar.d("shareType", "qq");
        return jVar;
    }

    public b d(Context context, String str, String str2, String str3) {
        b jVar = new j(context);
        jVar.d("c", "user");
        jVar.d("a", "binding");
        jVar.d("account", str3);
        jVar.d("type", Constants.SOURCE_QZONE);
        jVar.d("app", "0");
        jVar.d("key", str2);
        jVar.d(x.c, str3);
        jVar.d("format", "json");
        if (!TextUtils.isEmpty(str)) {
            jVar.d("id", str);
        }
        return jVar;
    }

    public b a(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        b jVar = new j(context);
        jVar.d("c", "weibo");
        jVar.d("a", UpdateConfig.a);
        if ("sina".equals(str6)) {
            jVar.d("type", "sina");
            jVar.d(INoCaptchaComponent.token, str);
        } else if ("qq".equals(str6)) {
            jVar.d("type", "qq");
            jVar.d(INoCaptchaComponent.token, str);
            jVar.d("openid", Util.getSharePersistent(context, "OPEN_ID"));
        } else if (Constants.SOURCE_QZONE.equals(str6)) {
            jVar.d("type", Constants.SOURCE_QZONE);
            jVar.d("key", str);
            jVar.d(x.c, str2);
        }
        jVar.d("app", "8");
        jVar.d("data_type", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        jVar.d("pic_path", str3);
        jVar.d("data_id", str4);
        jVar.d("content", str5);
        return jVar;
    }

    public b a(Context context, HuodongBean huodongBean, String str) {
        String type = huodongBean.getType();
        b jVar = new j(context);
        jVar.d("c", "weibo");
        jVar.d("a", "huodong");
        if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_SINA.equals(type)) {
            jVar.d(INoCaptchaComponent.token, str);
        } else if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_TENCENT.equals(type)) {
            jVar.d(INoCaptchaComponent.token, str);
            jVar.d("openid", Util.getSharePersistent(context, "OPEN_ID"));
        } else if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_QZONE.equals(type)) {
            jVar.d("key", str);
            jVar.d(x.c, huodongBean.getUid());
        }
        jVar.d("type", type);
        jVar.d("app", "8");
        jVar.d("huodong_id", huodongBean.getHuodongId());
        jVar.d("title", huodongBean.getTitle());
        jVar.d("content", huodongBean.getContent());
        jVar.d("share_url", huodongBean.getShareUrl());
        jVar.d("pic_path", huodongBean.getPicUrl());
        jVar.d("voice_path", huodongBean.getVoiceUrl());
        jVar.d("video_path", huodongBean.getVideoUrl());
        jVar.d("reserve", huodongBean.getReserve());
        return jVar;
    }

    public b a(Activity activity, String str, ListItemObject listItemObject, String str2) {
        String type = listItemObject.getType();
        b jVar = new j(activity);
        jVar.d("c", "weibo");
        jVar.d("a", SocialConstants.PARAM_SHARE_URL);
        if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_SINA.equals(type)) {
            jVar.d(INoCaptchaComponent.token, str);
        } else if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_TENCENT.equals(type)) {
            jVar.d(INoCaptchaComponent.token, str);
            jVar.d("openid", Util.getSharePersistent(activity, "OPEN_ID"));
        } else if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_QZONE.equals(type)) {
            jVar.d("key", str);
            jVar.d(x.c, listItemObject.getUid());
        }
        jVar.d("type", type);
        jVar.d("sharetype", str2);
        jVar.d("app", "8");
        jVar.d("title", listItemObject.getTitle());
        jVar.d("content", listItemObject.getContent());
        jVar.d("share_url", listItemObject.getWeixin_url());
        type = listItemObject.getImgUrl();
        if (Scheme.ofUri(type) == Scheme.FILE) {
            type = listItemObject.getCnd_img();
        }
        jVar.d("pic_path", type);
        jVar.d("voice_path", listItemObject.getVoiceUri());
        jVar.d("video_path", listItemObject.getVideouri());
        return jVar;
    }

    public b a(Activity activity, ListItemObject listItemObject) {
        b jVar = new j(activity);
        jVar.d("c", "data");
        jVar.d("a", "favourite");
        jVar.d("id", listItemObject.getWid());
        if (an.j(activity) && !listItemObject.isForwardAndCollect()) {
            n nVar = new n(activity);
            String str = "";
            if (nVar.a((Context) activity)) {
                str = str + "sina,";
            }
            if (nVar.b((Context) activity)) {
                str = str + "qq,";
            }
            if (nVar.c(activity)) {
                str = str + Constants.SOURCE_QZONE;
            }
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
            }
            if (!str.equals("")) {
                String string = activity.getSharedPreferences("weiboprefer", 0).getString("id", "");
                if (!string.equals("")) {
                    jVar.d("shareType", str);
                    jVar.d("app", "8");
                    jVar.d("userID", string);
                }
            }
            aa.a("ListItemTools", "收藏并转发");
        }
        if (listItemObject.isForwardAndCollect()) {
            aa.a("ListItemTools", "转发并收藏");
        }
        jVar.d("tj_from", sendCommendActivity.a(listItemObject));
        return jVar;
    }

    public b a(Activity activity, ListItemObject listItemObject, String str) {
        Map a = a((Context) activity, new HashMap());
        a.put("c", "data");
        if ("ding".equals(str)) {
            a.put("a", "love");
            a.put("dong", "love");
        } else if ("cai".equals(str)) {
            a.put("a", "cai");
            a.put("dong", "cai");
        }
        a.put("id", listItemObject.getWid());
        a.put("tj_from", sendCommendActivity.a(listItemObject));
        return e.a("/api/api_open.php", a);
    }

    public b k(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "superuser");
        jVar.d("a", "delcomment");
        jVar.d(IXAdRequestInfo.CELL_ID, str2);
        jVar.d("black", str);
        return jVar;
    }

    public b p(Context context, String str) {
        b jVar = new j(context);
        jVar.d("c", "comment");
        jVar.d("a", "delete");
        jVar.d(IXAdRequestInfo.CELL_ID, str);
        return jVar;
    }

    public b l(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "superuser");
        jVar.d("a", "deltopic");
        jVar.d(com.alipay.sdk.cons.b.c, str2);
        jVar.d("black", str);
        return jVar;
    }

    public b m(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("c", "topic");
        jVar.d("a", "deltopic");
        jVar.d(com.alipay.sdk.cons.b.c, str);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str2);
        return jVar;
    }

    public b a(Activity activity, String str, String str2, boolean z) {
        b jVar = new j(activity);
        jVar.d("c", "topic");
        if (z) {
            jVar.d("a", "top_mytopic");
        } else {
            jVar.d("a", "untop_mytopic");
        }
        jVar.d("id", str);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str2);
        return jVar;
    }

    public b a(Activity activity, String str, HuodongBean huodongBean) {
        String type = huodongBean.getType();
        b jVar = new j(activity);
        jVar.d("c", "weibo");
        jVar.d("a", "theme");
        if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_SINA.equals(type)) {
            jVar.d(INoCaptchaComponent.token, str);
        } else if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_TENCENT.equals(type)) {
            jVar.d(INoCaptchaComponent.token, str);
            jVar.d("openid", Util.getSharePersistent(activity, "OPEN_ID"));
        } else if (com.budejie.www.activity.htmlpage.c.SHARE_PLATFORM_QZONE.equals(type)) {
            jVar.d("key", str);
            jVar.d(x.c, huodongBean.getUid());
        }
        jVar.d("type", type);
        jVar.d("app", "8");
        jVar.d("huodong_id", huodongBean.getHuodongId());
        jVar.d("title", huodongBean.getTitle());
        jVar.d("content", huodongBean.getContent());
        jVar.d("share_url", huodongBean.getShareUrl());
        jVar.d("pic_path", huodongBean.getPicUrl());
        jVar.d("voice_path", huodongBean.getVoiceUrl());
        jVar.d("video_path", huodongBean.getVideoUrl());
        jVar.d("reserve", huodongBean.getReserve());
        jVar.d("theme_id", huodongBean.getTheme_id() + "");
        return jVar;
    }

    public b a(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        b jVar = new j(context);
        jVar.d("c", "comment");
        jVar.d("a", "dataCreate");
        jVar.d("shareType", str);
        jVar.d("data_type", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        jVar.d("initiator_id", str5);
        jVar.d("data_id", str2);
        jVar.d("content", str3);
        jVar.d("app", "8");
        jVar.d("precid", str4);
        jVar.d("return_type", "2");
        jVar.d("format", "json");
        if (z) {
            jVar.d("tj_type", "voice");
        } else {
            jVar.d("tj_type", "text");
        }
        jVar.d("tj_from", str6);
        return jVar;
    }

    public b b(Activity activity, String str, String str2) {
        b jVar = new j(activity);
        jVar.d("c", "user");
        jVar.d("a", "unbind");
        jVar.d("type", str);
        jVar.d("client", AlibcConstants.PF_ANDROID);
        jVar.d("id", str2);
        return jVar;
    }

    public b b(Context context, String str, String str2, String str3, String str4, String str5) {
        b jVar = new j(context);
        jVar.d("c", "push");
        jVar.d("a", "upclient");
        jVar.d(HistoryOpenHelper.COLUMN_UID, str);
        jVar.d("channel_id", str2);
        jVar.d("cloud_uid", str3);
        if (!TextUtils.isEmpty(str4)) {
            str4 = Base64.encodeToString(str4.getBytes(), 0);
        }
        jVar.d("push_id", str4);
        jVar.d("plantform_flag", str5);
        return jVar;
    }

    public b a(Activity activity, String str, int i, int i2) {
        b jVar = new j(activity);
        jVar.d("a", "list");
        jVar.d("c", "praise");
        jVar.d("userid", str);
        jVar.d("maxtime", String.valueOf(i));
        jVar.d("per", String.valueOf(i2));
        return jVar;
    }

    public static b a(Context context, ListItemObject listItemObject) {
        b jVar = new j();
        jVar.d(IXAdRequestInfo.V, "1");
        jVar.d(com.alipay.sdk.cons.b.c, "UA-41454169-7");
        jVar.d(IXAdRequestInfo.CELL_ID, an.e(context));
        jVar.d(com.alipay.sdk.sys.a.i, "bsbdjapp");
        jVar.d(com.alipay.sdk.sys.a.k, "6.9.2");
        jVar.d("t", "event");
        jVar.d("ec", context.getString(R.string.reprint_sccess));
        String a = a(listItemObject);
        jVar.d("ea", a);
        String b = b(context, listItemObject);
        jVar.d("el", b);
        i.a(context.getString(R.string.track_event_share_post), a, b);
        return jVar;
    }

    public static String a(ListItemObject listItemObject) {
        if (listItemObject == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        String addtime = listItemObject.getAddtime();
        if (!TextUtils.isEmpty(addtime)) {
            if (addtime.contains("-")) {
                stringBuilder.append(addtime.length() > 10 ? addtime.substring(0, 10) : addtime);
                StringBuilder append = stringBuilder.append("|");
                if (addtime.length() > 16) {
                    addtime = addtime.substring(11, 16);
                }
                append.append(addtime);
            } else if (addtime.length() >= 12) {
                stringBuilder.append(addtime.substring(0, 4)).append("-").append(addtime.substring(4, 6)).append("-").append(addtime.substring(6, 8)).append("|").append(addtime.substring(8, 10)).append(":").append(addtime.substring(10, 12));
            }
        }
        StringBuilder append2 = stringBuilder.append("|");
        String content = !TextUtils.isEmpty(listItemObject.getContent()) ? listItemObject.getContent() : listItemObject.getRichObject() != null ? listItemObject.getRichObject().getTitle() : "";
        append2.append(content).append("|").append(listItemObject.getWid());
        return stringBuilder.toString();
    }

    public static String b(Context context, ListItemObject listItemObject) {
        if (listItemObject == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        if ("61".equals(listItemObject.getType())) {
            stringBuilder.append(context.getString(R.string.reship));
        } else if (!TextUtils.isEmpty(listItemObject.getVoiceUri())) {
            stringBuilder.append(context.getString(R.string.main_sound));
            i = Integer.parseInt(listItemObject.getVoicetime());
        } else if (!TextUtils.isEmpty(listItemObject.getVideouri())) {
            stringBuilder.append(context.getString(R.string.main_video));
            i = Integer.parseInt(TextUtils.isEmpty(listItemObject.getVideotime()) ? "0" : listItemObject.getVideotime());
        } else if ("1".equals(listItemObject.getIs_gif()) || !TextUtils.isEmpty(listItemObject.getImgUrl())) {
            stringBuilder.append(context.getString(R.string.main_new));
        } else if ("51".equals(listItemObject.getType())) {
            stringBuilder.append(context.getString(R.string.track_event_rich_text));
        } else {
            stringBuilder.append(context.getString(R.string.main_duanzi));
        }
        if (i != 0) {
            stringBuilder.append("|");
            float f = (float) (i / 60);
            if (i % 60 > 30) {
                stringBuilder.append((int) (1.0f + f));
            } else {
                stringBuilder.append((float) (((double) f) + 0.5d));
            }
            stringBuilder.append(context.getString(R.string.time_minute_unit));
        }
        return stringBuilder.toString();
    }

    public static String b(String str) {
        h hVar = new h("http://d.api.budejie.com/topic/friends-topic");
        hVar.b(str).a();
        return hVar.toString();
    }

    public static String a(String str, String str2, String str3, String str4) {
        return new h("http://s.budejie.com/topic/top-topic").d(str).d(str2).d(str3).b(str4).a().toString();
    }

    public static String b() {
        return new h("http://d.api.budejie.com/topic/recommend/").b("0", "30").a().toString();
    }

    public static String b(String str, String str2, String str3, String str4) {
        return new h("http://s.budejie.com/topic/tag-topic").d(str).d(str4).a(str2, str3).a().toString();
    }

    public static String c(String str) {
        h hVar = new h("http://s.budejie.com/danmu/list");
        hVar.d(str).b().a();
        return hVar.toString();
    }

    public static String c() {
        return new h("http://d.api.budejie.com/spider/videourl").toString();
    }

    public static String d() {
        return new h("http://d.api.budejie.com/topic/push_url_topic").toString();
    }

    public static String a(String str, String str2, boolean z) {
        h hVar = new h();
        if (z) {
            hVar.a("http://d.api.budejie.com/comment/my-comment");
        } else {
            hVar.a("http://s.budejie.com/comment/user-comment").d(str);
        }
        hVar.b(str2).a();
        return hVar.toString();
    }

    public static String d(String str) {
        return new h("/comment/create-cmt").b().d(str).toString();
    }

    public static String a(String str, String str2) {
        return new h("http://d.api.budejie.com/comment").d(str).d(str2).a("/").toString();
    }

    public static String a(long j) {
        return new h("http://d.api.budejie.com/message/list").a(j).a().toString();
    }

    public static j d(Context context) {
        Map hashMap = new HashMap();
        hashMap.put(x.u, an.e(context));
        hashMap.put("t", "" + System.currentTimeMillis());
        hashMap.put("idfv", "");
        hashMap.put("imei", an.e(context));
        hashMap.put("screen", com.budejie.www.adapter.b.a.a + "x" + com.budejie.www.adapter.b.a.b);
        hashMap.put("model", Build.MODEL);
        hashMap.put("os", AlibcConstants.PF_ANDROID);
        hashMap.put(x.q, an.a());
        hashMap.put("app_id", context.getPackageName());
        hashMap.put("app_version", "6.9.2");
        hashMap.put("market", "xiaomi");
        hashMap.put(x.G, Locale.getDefault().getCountry());
        hashMap.put("lang", Locale.getDefault().getLanguage());
        hashMap.put(com.alipay.sdk.app.statistic.c.a, "1".equals(an.b(context)) ? IXAdSystemUtils.NT_WIFI : "mobile");
        hashMap.put(INoCaptchaComponent.token, an.B(context));
        return e.a("/device/activate/", hashMap);
    }

    public static j a(Context context, int i, String str) {
        Map hashMap = new HashMap();
        hashMap.put("t", "" + System.currentTimeMillis());
        hashMap.put("cate", "ad");
        hashMap.put("adposition", "0");
        hashMap.put("adid", String.valueOf(i));
        hashMap.put(AppLinkConstants.TIME, String.valueOf(System.currentTimeMillis()));
        hashMap.put("type", str);
        return e.a("/device/report/", hashMap);
    }

    public b a(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, String str7) {
        b jVar = new j(context);
        jVar.d("url", str);
        jVar.d("dns-used", str2);
        jVar.d("dns", str3);
        jVar.d("first-response", str4);
        jVar.d("finish-response", str5);
        jVar.d("status", str6);
        jVar.d("bytes", i == 0 ? "" : i + "");
        jVar.d(com.baidu.mobads.openad.c.b.EVENT_MESSAGE, str7);
        jVar.d("nt", an.d(context));
        return jVar;
    }

    public static b e(Context context, String str, String str2, String str3) {
        b jVar = new j(context);
        jVar.d("pid", str3);
        jVar.d(AppLinkConstants.TIME, str2);
        jVar.d("content", str);
        return jVar;
    }

    public static b q(Context context, String str) {
        b jVar = new j(context);
        jVar.d("did", str);
        return jVar;
    }

    public static b d(Context context, String str, String str2, String str3, String str4) {
        b jVar = new j(context);
        jVar.d("product_id", str);
        jVar.d("pay_user_id", str2);
        jVar.d("to_user_id", str3);
        jVar.d("channel", str4);
        return jVar;
    }

    public static b f(Context context, String str, String str2, String str3) {
        b jVar = new j(context);
        jVar.d("offset", str);
        jVar.d("limit", str2);
        jVar.d(HistoryOpenHelper.COLUMN_UID, str3);
        return jVar;
    }

    public static String e(String str) {
        h hVar = new h("http://d.api.budejie.com/vip_user");
        hVar.d(str).e();
        return hVar.toString();
    }

    public static String f(String str) {
        return new h("http://d.api.budejie.com/topic/search").b(str).a().toString();
    }

    public static String g(String str) {
        return new h("http://d.api.budejie.com/topic/hotsearch").b(str).a().toString();
    }

    public static b r(Context context, String str) {
        b jVar = new j(context);
        jVar.d("tname", str);
        return jVar;
    }

    public static String h(String str) {
        return new h("http://d.api.budejie.com/topic/list/pvmixed/100").b(str).a().toString();
    }

    public static b e(Context context) {
        return new j(context);
    }

    public static String i(String str) {
        return new h("http://d.api.budejie.com/topic/praise").d(str).b("0").a().toString();
    }

    public static String e() {
        return new h("http://d.api.budejie.com/user/info").toString();
    }

    public static b s(Context context, String str) {
        b jVar = new j(context);
        jVar.d("id", str);
        return jVar;
    }

    public static String f() {
        return new h("http://d.api.budejie.com/user/profile").toString();
    }

    public static b n(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d("userid", str);
        jVar.d("name", str2);
        return jVar;
    }

    public static String g() {
        h hVar = new h("http://s.budejie.com/user/new_ruler");
        hVar.b().c().c("0", "20").a();
        return hVar.toString();
    }

    public static String h() {
        h hVar = new h("http://s.budejie.com/topic/new_reporttype");
        hVar.b().c().c("0", "20").a();
        return hVar.toString();
    }

    public static String i() {
        return new h("http://d.api.budejie.com/subscribe/category").b("0").a().toString();
    }

    public static b g(Context context, String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("ver", "6.9.2");
        hashMap.put("client", AlibcConstants.PF_ANDROID);
        hashMap.put("market", "xiaomi");
        hashMap.put("t", "" + (System.currentTimeMillis() / 1000));
        hashMap.put("udid", an.e(context));
        hashMap.put("mac", an.h(context));
        hashMap.put("os", an.a());
        hashMap.put("appname", "baisibudejie");
        hashMap.put("visiting", an.f(context));
        return e.a(b(str, str2, str3), hashMap);
    }

    public static String a(String str, String str2, String str3) {
        h hVar = new h("http://d.api.budejie.com/topic/share");
        hVar.a("/" + str).a("/" + str2).a("/" + str3);
        return hVar.toString();
    }

    public static String b(String str, String str2, String str3) {
        h hVar = new h("/topic/share");
        hVar.a("/" + str).a("/" + str2).a("/" + str3);
        return hVar.toString();
    }

    public static String a(int i, String str) {
        return new h("http://d.api.budejie.com/subscribe/user").d(str).d("0").a((long) i).a().toString();
    }

    public b a(Context context, String str, String str2, String str3, String str4, String str5, int i, File file, File file2, File file3, String str6, int i2) {
        Map a = a(context, new HashMap());
        a.put(HistoryOpenHelper.COLUMN_UID, str2);
        if (!TextUtils.isEmpty(str3)) {
            a.put("precid", str3);
        }
        a.put("cmt_type", str5);
        if (!TextUtils.isEmpty(str4)) {
            a.put("content", str4);
        }
        if (i > 0) {
            a.put("voicetime", "" + i);
        }
        if (!TextUtils.isEmpty(str6)) {
            a.put("cvote", "" + str6);
        }
        if (i2 > 0) {
            a.put("videotime", "" + i2);
        }
        return e.a(d(str), a, file, file2, file3);
    }

    public static String j() {
        return new h("https://d.api.budejie.com/user/api/login").toString();
    }

    public b h(Context context, String str, String str2, String str3) {
        Map a = a(context, new HashMap());
        a.put("phonenum", str);
        a.put("password", com.budejie.www.activity.phonenumber.a.a(str2));
        a.put("countrycode", str3);
        return e.a("/user/api/login", a);
    }

    public static String k() {
        return new h("https://d.api.budejie.com/user/api/register").toString();
    }

    public b i(Context context, String str, String str2, String str3) {
        Map a = a(context, new HashMap());
        a.put(HistoryOpenHelper.COLUMN_USERNAME, str);
        a.put("password", com.budejie.www.activity.phonenumber.a.a(str2));
        a.put("req", str3);
        return e.a("/user/api/register", a);
    }

    public static String l() {
        return new h("https://d.api.budejie.com/user/api/get_verify").toString();
    }

    public b j(Context context, String str, String str2, String str3) {
        Map a = a(context, new HashMap());
        a.put("phonenum", str);
        a.put("countrycode", str2);
        a.put("verifytype", str3);
        String e = an.e(context);
        a.put(x.u, e);
        long currentTimeMillis = System.currentTimeMillis();
        a.put(AppLinkConstants.TIME, Long.toString(currentTimeMillis));
        a.put("sec", com.budejie.www.activity.phonenumber.a.a(e, str, currentTimeMillis));
        return e.a("/user/api/get_verify", a);
    }

    public static String m() {
        return new h("https://d.api.budejie.com/user/api/verify_code").toString();
    }

    public b e(Context context, String str, String str2, String str3, String str4) {
        Map a = a(context, new HashMap());
        a.put("phonenum", str);
        a.put("countrycode", str2);
        a.put("verifycode", str3);
        a.put("seq", str4);
        return e.a("/user/api/verify_code", a);
    }

    public static String n() {
        return new h("https://d.api.budejie.com/user/api/init_password").toString();
    }

    public b k(Context context, String str, String str2, String str3) {
        Map a = a(context, new HashMap());
        String a2 = com.budejie.www.activity.phonenumber.a.a(str);
        String a3 = com.budejie.www.activity.phonenumber.a.a(str2);
        a.put("newpwd", a2);
        a.put("repeatpwd", a3);
        a.put("req", str3);
        return e.a("/user/api/init_password", a);
    }

    public static String c(String str, String str2, String str3) {
        return new h("http://c.api.budejie.com/topic/comment_list").d(str2).d(str3).b(str).a().toString();
    }

    public static String d(String str, String str2, String str3) {
        return new h("http://d.api.budejie.com/topic/comment_list").d(str2).d(str3).b(str).a().toString();
    }

    public static String j(String str) {
        return new h("http://c.api.budejie.com/comment/voice-words").d(str).b("0").a().toString();
    }

    public static String b(String str, String str2) {
        return new h("http://c.api.budejie.com/topic/comment_lapped").d(str).d(str2).b("0").a().toString();
    }

    public static String o() {
        return new h("http://d.api.budejie.com/user/binding").toString();
    }

    public static b o(Context context, String str, String str2) {
        b jVar = new j(context);
        jVar.d(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str);
        jVar.d("type", str2);
        jVar.d("appid", "wx592fdc48acfbe290");
        return jVar;
    }

    public static String p() {
        return new h("http://d.api.budejie.com/user/unbind").toString();
    }

    public static b t(Context context, String str) {
        b jVar = new j(context);
        jVar.d("type", str);
        return jVar;
    }

    public static String a(String str, long j) {
        return new h(str).b("" + j, "8").a().toString();
    }

    public static String e(String str, String str2, String str3) {
        return new h("http://d.api.budejie.com/comment/act_list").d(str).d(str2).b().c(str3, "20").a().toString();
    }

    public static String f(String str, String str2, String str3) {
        String str4 = "";
        str4 = "";
        if (TextUtils.isEmpty(str3)) {
            str4 = "http://d.api.budejie.com/topic/vote";
        } else {
            str4 = "http://d.api.budejie.com/comment/vote";
            str = str3;
        }
        h hVar = new h(str4);
        hVar.d(str).d(str2).e();
        return hVar.toString();
    }

    public static String c(String str, String str2, String str3, String str4) {
        String str5 = "";
        str5 = "";
        if (TextUtils.isEmpty(str3)) {
            str5 = "http://d.api.budejie.com/topic/vote/list";
        } else {
            str5 = "http://d.api.budejie.com/comment/vote/list";
            str = str3;
        }
        return new h(str5).d(str).d(str2).b().c(str4, "20").a().toString();
    }

    public static String q() {
        h hVar = new h("http://d.api.budejie.com/user/vote/info");
        hVar.e();
        return hVar.toString();
    }

    public static String r() {
        h hVar = new h("http://d.api.budejie.com/user/cvote/info");
        hVar.e();
        return hVar.toString();
    }

    public static String k(String str) {
        return new h("http://d.api.budejie.com/xuantu/list/user").d(str).a(0).a().toString();
    }

    public static String s() {
        return new h("http://d.api.budejie.com/forum/subscribe").b().a().toString();
    }

    public static String a(String str, String str2, String str3, String str4, String str5) {
        return new h("http://d.api.budejie.com/topic/forum").d(str).d(str2).d(str3).b().c(str4, str5).a().toString();
    }

    public static String l(String str) {
        return a(str, 1);
    }

    public static String a(int i) {
        return new h("http://d.api.budejie.com/tag/recommend/topic").a(i).b().a().toString();
    }

    public static String a(String str, int i) {
        String str2 = "";
        try {
            str2 = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new h("http://d.api.budejie.com/tag/search").d(str2).a(i).b().c("0", "50").a().toString();
    }

    public static String m(String str) {
        return new h("http://s.budejie.com/topic/by-id").d(str).b().a().toString();
    }

    public static String c(String str, String str2) {
        return new h("http://d.api.budejie.com/topic/bookmark").d(str).b().c(str2, "20").a().toString();
    }

    public static String n(String str) {
        return new h("http://d.api.budejie.com/forum/user_list").d(str).b().c("0", "20").a().toString();
    }

    public static String o(String str) {
        return new h("http://d.api.budejie.com/forum/ban_user_list").d(str).b().c("0", "20").a().toString();
    }
}
