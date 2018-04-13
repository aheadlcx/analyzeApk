package com.budejie.www.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import android.util.Log;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alipay.SignUtils;
import com.alipay.VipPayActivity;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.AppWallActivity;
import com.budejie.www.activity.BaijiajieWithAdvActivity;
import com.budejie.www.activity.CollectActivity;
import com.budejie.www.activity.ConversationActivity;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.MyInfoActivity;
import com.budejie.www.activity.MyPostsActivity;
import com.budejie.www.activity.MySquareMoreIcon;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.RankingListActivity;
import com.budejie.www.activity.auditpost.AuditPostsActivity;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.htmlpage.HtmlSkipParams;
import com.budejie.www.activity.htmlpage.HtmlSkipParams$HtmlKey;
import com.budejie.www.activity.htmlpage.HtmlSkipParams$HtmlMyCate;
import com.budejie.www.activity.htmlpage.HtmlSkipParams$HtmlTypeCate;
import com.budejie.www.activity.label.ActivitiesTopicActivity;
import com.budejie.www.activity.label.h;
import com.budejie.www.activity.mycomment.MyCommentActivity;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.activity.recommend.SuggestedFollowsActivity;
import com.budejie.www.activity.search.SearchMainActivity;
import com.budejie.www.wallpaper.WallPaperListActivity;
import com.facebook.common.util.UriUtil;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.a;
import com.zxt.download2.DownloadListActivity;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.mtop.antiattack.CheckCodeDO;

@SuppressLint({"NewApi"})
public class w {
    private static final String a = w.class.getSimpleName();
    private final int b = 1;
    private final int c = 2;
    private final int d = 3;
    private final int e = 4;
    private final int f = 5;
    private final int g = 6;
    private final int h = 7;
    private final int i = 8;
    private final int j = 9;
    private final int k = 16;
    private final int l = 17;
    private final int m = 18;
    private final int n = 19;
    private final int o = 20;
    private Activity p;
    private boolean q;

    public static w a(Activity activity, boolean z) {
        return new w(activity, z);
    }

    public w(Activity activity, boolean z) {
        this.p = activity;
        this.q = z;
    }

    public void a(String str) {
        Map c = c(str);
        if (c != null) {
            for (Entry a : c.entrySet()) {
                a(a);
            }
        } else if (this.q) {
            this.p.finish();
        }
    }

    private void a(Entry<String, Map<String, String>> entry) {
        int i = 1;
        Log.i(a, "skipParams-->" + entry);
        HtmlSkipParams$HtmlKey htmlSkipParams$HtmlKey = (HtmlSkipParams$HtmlKey) HtmlSkipParams.a.get(entry.getKey());
        Log.i(a, "htmlKey-->" + htmlSkipParams$HtmlKey);
        if (htmlSkipParams$HtmlKey != null) {
            Map map = (Map) entry.getValue();
            Log.i(a, "paramMap-->" + map);
            Intent intent = new Intent();
            String str = "";
            String str2;
            switch (w$2.b[htmlSkipParams$HtmlKey.ordinal()]) {
                case 1:
                    if (map != null) {
                        a((String) map.get("title"), (String) map.get(a.z), (String) map.get("price"), (String) map.get("orderid"), (String) map.get("url"));
                        return;
                    }
                    return;
                case 2:
                    if (map != null) {
                        ((HtmlFeatureActivity) this.p).a((String) map.get("url"), (String) map.get("iconurl"), (String) map.get("title"), (String) map.get(SocialConstants.PARAM_APP_DESC), i);
                        return;
                    }
                    return;
                case 3:
                    a(9);
                    return;
                case 4:
                    a(16);
                    return;
                case 5:
                    a(18);
                    return;
                case 6:
                    str2 = (String) map.get("type");
                    str = (String) map.get("param");
                    if (str2 != null) {
                        intent.setClass(this.p, HomeGroup.class);
                        intent.addFlags(268435456);
                        intent.setAction("com.budejie.www.activity.MyInfoActivity");
                        Bundle bundle = new Bundle();
                        bundle.putString("param", str);
                        bundle.putSerializable("tougao", (Serializable) HtmlSkipParams.d.get(str2));
                        intent.putExtra("bundle", bundle);
                        this.p.startActivity(intent);
                    }
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 7:
                case 8:
                    str2 = (String) map.get("url");
                    if (str2 != null && str2.startsWith(UriUtil.HTTP_SCHEME)) {
                        intent.setClass(this.p, HtmlFeatureActivity.class);
                        intent.setData(Uri.parse(str2));
                        this.p.startActivity(intent);
                    }
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 9:
                    this.p.startActivity(new Intent(this.p, BaijiajieWithAdvActivity.class));
                    return;
                case 10:
                    str2 = (String) map.get(TtgConfigKey.TTG_URl);
                    str = (String) map.get("pid");
                    if (!(TextUtils.isEmpty(str2) || TextUtils.isEmpty(str))) {
                        try {
                            TtgInterface.openTabTtgMain(this.p, str2, Integer.parseInt(str));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 11:
                    String str3 = (String) map.get("id");
                    String str4 = (String) map.get("url");
                    if (!TextUtils.isEmpty(str3)) {
                        a.a(this.p, null, str3, false, false, str4);
                    }
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 12:
                    str2 = (String) map.get("type");
                    str = (String) map.get("cate");
                    if (!TextUtils.isEmpty(str)) {
                        if (TextUtils.isEmpty(str2)) {
                            a(intent, "0", str);
                        } else {
                            a(intent, str2, str);
                        }
                    }
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 13:
                    a(i);
                    return;
                case 14:
                    str2 = (String) map.get("dest");
                    if (str2 != null) {
                        HtmlSkipParams$HtmlMyCate htmlSkipParams$HtmlMyCate = (HtmlSkipParams$HtmlMyCate) HtmlSkipParams.e.get(str2);
                        if (htmlSkipParams$HtmlMyCate != null) {
                            switch (w$2.a[htmlSkipParams$HtmlMyCate.ordinal()]) {
                                case 1:
                                    a(4);
                                    return;
                                case 2:
                                    a(5);
                                    return;
                                case 3:
                                    a(6);
                                    return;
                                default:
                                    return;
                            }
                        } else if (this.q) {
                            this.p.finish();
                            return;
                        } else {
                            return;
                        }
                    } else if (this.q) {
                        this.p.finish();
                        return;
                    } else {
                        return;
                    }
                case 15:
                    a(2, map);
                    return;
                case 16:
                    a(3, map);
                    return;
                case 17:
                    intent.setClass(this.p, HomeGroup.class);
                    intent.addFlags(268435456);
                    intent.setAction("com.budejie.www.activity.MyInfoActivity");
                    this.p.startActivity(intent);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 18:
                    str2 = (String) map.get("url");
                    Log.i(a, "h5url-->" + str2);
                    if (str2 != null) {
                        intent.setClass(this.p, MyInfoActivity.class);
                        intent.addFlags(268435456);
                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable("htmlUrl", str2);
                        intent.putExtra("bundle", bundle2);
                        this.p.startActivity(intent);
                    }
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 19:
                    a(7);
                    return;
                case 20:
                    a(8);
                    return;
                case 21:
                    a(17);
                    return;
                case 22:
                    this.p.startActivity(new Intent(this.p, SearchMainActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 23:
                    a(20);
                    return;
                case 24:
                    str2 = (String) map.get("id");
                    str = (String) map.get("display_num");
                    if (!TextUtils.isEmpty(str2)) {
                        if (!TextUtils.isEmpty(str)) {
                            try {
                                i = Integer.parseInt(str);
                            } catch (NumberFormatException e2) {
                            }
                        }
                        h.a(this.p, str2, "", i);
                        if (this.q) {
                            this.p.finish();
                            return;
                        }
                        return;
                    }
                    return;
                case 25:
                    intent.setClass(this.p, ConversationActivity.class);
                    this.p.startActivity(intent);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 26:
                    an.a(this.p, false);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 27:
                    Intent intent2 = new Intent(this.p, DownloadListActivity.class);
                    intent2.putExtra("isDownloaded", i);
                    this.p.startActivity(intent2);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 28:
                    this.p.startActivity(new Intent(this.p, RankingListActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 29:
                    com.lt.a.a.a.a(this.p, com.lt.a.a(this.p).g());
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 30:
                    this.p.startActivity(new Intent(this.p, VipPayActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 31:
                    com.d.a.a(this.p);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 32:
                    com.d.a.a(this.p, (String) map.get("rid"), (String) map.get(HistoryOpenHelper.COLUMN_UID));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 33:
                    a.a(this.p, WallPaperListActivity.class);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 34:
                    com.b.a.b(this.p);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (this.q) {
            this.p.finish();
        }
    }

    private void a(Intent intent, String str, String str2) {
        HtmlSkipParams$HtmlTypeCate htmlSkipParams$HtmlTypeCate = (HtmlSkipParams$HtmlTypeCate) HtmlSkipParams.c.get(str2);
        if (htmlSkipParams$HtmlTypeCate != null) {
            intent.setClass(this.p, HomeGroup.class);
            intent.addFlags(268435456);
            switch (w$2.c[htmlSkipParams$HtmlTypeCate.ordinal()]) {
                case 1:
                    intent.putExtra("tag_all", "tag_essence");
                    this.p.startActivity(intent);
                    return;
                case 2:
                    intent.putExtra("tag_all", "tag_new");
                    this.p.startActivity(intent);
                    return;
                case 3:
                    this.p.getSharedPreferences("weiboprefer", 0);
                    Intent intent2 = new Intent(this.p, PostsActivity.class);
                    intent2.putExtra("navigation_key", (Serializable) HomeGroup.z.menus.get(2));
                    intent2.putExtra("tag_all", "tag_suiji");
                    this.p.startActivity(intent2);
                    return;
                case 4:
                    Intent intent3 = new Intent(this.p, PostsActivity.class);
                    intent3.putExtra("tag_all", "tag_nearby");
                    this.p.startActivity(intent3);
                    return;
                default:
                    return;
            }
        }
    }

    private Map<String, Map<String, String>> c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map hashMap;
        if (str.startsWith("mod://")) {
            hashMap = new HashMap();
            a(str.replace("mod://", ""), hashMap);
            return hashMap;
        } else if (str.startsWith(UriUtil.HTTP_SCHEME)) {
            Map<String, Map<String, String>> hashMap2 = new HashMap();
            Map hashMap3 = new HashMap();
            hashMap3.put("url", str);
            hashMap2.put("App_To_HTTP", hashMap3);
            return hashMap2;
        } else if (!str.startsWith("open=")) {
            return null;
        } else {
            hashMap = new HashMap();
            a(str.replace("open=", ""), hashMap);
            return hashMap;
        }
    }

    private void a(String str, Map<String, Map<String, String>> map) {
        Log.i(a, "analy-->" + str);
        for (String str2 : str.split("\\|")) {
            Map hashMap = new HashMap();
            String[] split = str2.split("@");
            if (split.length >= 2) {
                Log.i(a, "split[1].trim()-->" + split[1].trim());
                for (String str3 : split[1].trim().split("#")) {
                    Log.i(a, "param-->" + str3);
                    String[] split2 = str3.split(LoginConstants.EQUAL, 2);
                    if (split2.length == 2 && split2[0].trim().length() > 0 && split2[1].trim().length() > 0) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
                Log.i(a, "split[0].trim()-->" + split[0].trim());
                Log.i(a, "connMap-->" + hashMap);
                map.put(split[0].trim(), hashMap);
            } else {
                map.put(split[0].trim(), hashMap);
            }
        }
    }

    private void a(int i) {
        a(i, null);
    }

    private void a(int i, Map<String, String> map) {
        b(i);
        SharedPreferences sharedPreferences = this.p.getSharedPreferences("weiboprefer", 0);
        if (i == 5) {
            if (an.a(sharedPreferences)) {
                if (!sharedPreferences.getBoolean("collectUpdate", false)) {
                    sharedPreferences.edit().putBoolean("collectUpdate", true).commit();
                }
                this.p.startActivity(new Intent(this.p, CollectActivity.class));
                if (this.q) {
                    this.p.finish();
                    return;
                }
                return;
            }
            an.a(this.p, 0, null, null, 0);
            this.p.finish();
        } else if (i == 8) {
            r1 = new Intent(this.p, PostsActivity.class);
            r1.putExtra("navigation_key", (Serializable) HomeGroup.z.menus.get(3));
            r1.putExtra("tag_all", "tag_ranking");
            this.p.startActivity(r1);
            if (this.q) {
                this.p.finish();
            }
        } else if (i == 18) {
            this.p.startActivity(new Intent(this.p, MySquareMoreIcon.class));
            if (this.q) {
                this.p.finish();
            }
        } else if (i == 16) {
            this.p.startActivity(new Intent(this.p, ActivitiesTopicActivity.class));
            if (this.q) {
                this.p.finish();
            }
        } else if (i == 20) {
            this.p.startActivity(new Intent(this.p, AppWallActivity.class));
            if (this.q) {
                this.p.finish();
            }
        } else if (i == 3) {
            if (map != null) {
                String str = (String) map.get(UserTrackerConstants.USERID);
                if (!TextUtils.isEmpty(str)) {
                    r1 = new Intent(this.p, PersonalProfileActivity.class);
                    r1.putExtra(PersonalProfileActivity.c, str);
                    this.p.startActivity(r1);
                    if (this.q) {
                        this.p.finish();
                    }
                }
            }
        } else if (!an.a(this.p)) {
            an.a(this.p, this.p.getString(R.string.nonet), -1).show();
            if (this.q) {
                this.p.finish();
            }
        } else if (an.a(sharedPreferences)) {
            switch (i) {
                case 1:
                    this.p.startActivity(new Intent(this.p, AuditPostsActivity.class));
                    if (this.q) {
                        this.p.finish();
                    }
                    if (!sharedPreferences.getBoolean("shenheUpdate", false)) {
                        sharedPreferences.edit().putBoolean("shenheUpdate", true).commit();
                    }
                    if (!MyInfoActivity.c.isShown()) {
                        this.p.sendBroadcast(new Intent("android.hide.sister.my.NOTIFYTIPS"));
                        return;
                    }
                    return;
                case 2:
                    r0 = new Intent();
                    r0.setClass(this.p, HomeGroup.class);
                    r0.addFlags(268435456);
                    r0.putExtra("tag_all", "myinfo_type");
                    r0.putExtra("jump_type", "jump_type_follow");
                    this.p.startActivity(r0);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 4:
                    this.p.startActivity(new Intent(this.p, MyPostsActivity.class));
                    if (this.q) {
                        this.p.finish();
                    }
                    if (!sharedPreferences.getBoolean("mytougaoUpdate", false)) {
                        sharedPreferences.edit().putBoolean("mytougaoUpdate", true).commit();
                        return;
                    }
                    return;
                case 6:
                    this.p.startActivity(new Intent(this.p, MyCommentActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 7:
                    this.p.startActivity(new Intent(this.p, SuggestedFollowsActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 9:
                    return;
                case 17:
                    r0 = new Intent(this.p, PostsActivity.class);
                    r0.putExtra("tag_all", "tag_nearby");
                    this.p.startActivity(r0);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 19:
                    this.p.startActivity(new Intent(this.p, SearchMainActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            switch (i) {
                case 1:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "shenhe", 125);
                    return;
                case 2:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "newsfeed", (int) R$styleable.Theme_Custom_new_item_login_phone_bg);
                    return;
                case 3:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "personalprofile", 134);
                    return;
                case 4:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "mytougao", 124);
                    return;
                case 6:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "mycomment", 129);
                    return;
                case 7:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "suggestedfollows", 135);
                    return;
                case 9:
                    an.a(this.p, 1, StatisticCodeTable.MORE, "login", (int) R$styleable.Theme_Custom_new_item_shareFriend_text_color);
                    return;
                case 17:
                    r0 = new Intent(this.p, PostsActivity.class);
                    r0.putExtra("tag_all", "tag_nearby");
                    an.a(this.p, r0);
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                case 19:
                    an.a(this.p, new Intent(this.p, SearchMainActivity.class));
                    if (this.q) {
                        this.p.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void b(int i) {
        switch (i) {
            case 1:
                MobclickAgent.onEvent(this.p, "sheheTiezi");
                return;
            case 2:
                MobclickAgent.onEvent(this.p, "newsFeed");
                return;
            case 3:
                MobclickAgent.onEvent(this.p, "personalProfile");
                return;
            case 4:
                MobclickAgent.onEvent(this.p, "mytougao");
                return;
            case 5:
                MobclickAgent.onEvent(this.p, "collect");
                return;
            case 6:
                MobclickAgent.onEvent(this.p, "我的评论");
                return;
            case 7:
                MobclickAgent.onEvent(this.p, "E05-A08", "推荐关注");
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r5, int r6, android.content.Intent r7) {
        /*
        r4 = this;
        r3 = 0;
        if (r7 != 0) goto L_0x000d;
    L_0x0003:
        r0 = r4.q;
        if (r0 == 0) goto L_0x000c;
    L_0x0007:
        r0 = r4.p;
        r0.finish();
    L_0x000c:
        return;
    L_0x000d:
        r0 = 142; // 0x8e float:1.99E-43 double:7.0E-322;
        if (r5 != r0) goto L_0x0052;
    L_0x0011:
        if (r7 == 0) goto L_0x0046;
    L_0x0013:
        r0 = r7.getData();
        r1 = new android.content.Intent;
        r1.<init>();
        r2 = "android.intent.action.VIEW";
        r1.setAction(r2);
        r2 = r0.toString();
        r3 = "content://";
        r2 = r2.startsWith(r3);
        if (r2 == 0) goto L_0x004c;
    L_0x002d:
        r2 = new java.io.File;
        r3 = r4.p;
        r0 = a(r3, r0);
        r2.<init>(r0);
        r0 = android.net.Uri.fromFile(r2);
        r2 = "video/*";
        r1.setDataAndType(r0, r2);
    L_0x0041:
        r0 = r4.p;
        r0.startActivity(r1);
    L_0x0046:
        r0 = r4.p;
        r0.finish();
        goto L_0x000c;
    L_0x004c:
        r2 = "video/*";
        r1.setDataAndType(r0, r2);
        goto L_0x0041;
    L_0x0052:
        r0 = "success";
        r0 = r7.getBooleanExtra(r0, r3);
        if (r0 != 0) goto L_0x0064;
    L_0x005a:
        r0 = r4.q;
        if (r0 == 0) goto L_0x000c;
    L_0x005e:
        r0 = r4.p;
        r0.finish();
        goto L_0x000c;
    L_0x0064:
        r1 = 0;
        switch(r6) {
            case 124: goto L_0x00d1;
            case 125: goto L_0x009d;
            case 126: goto L_0x0068;
            case 127: goto L_0x0068;
            case 128: goto L_0x0068;
            case 129: goto L_0x00db;
            case 130: goto L_0x0068;
            case 131: goto L_0x0068;
            case 132: goto L_0x0068;
            case 133: goto L_0x00a7;
            case 134: goto L_0x00bc;
            case 135: goto L_0x00e5;
            case 136: goto L_0x0080;
            default: goto L_0x0068;
        };
    L_0x0068:
        r0 = r1;
    L_0x0069:
        if (r0 == 0) goto L_0x0076;
    L_0x006b:
        r1 = r0.getClass();
        if (r1 == 0) goto L_0x0076;
    L_0x0071:
        r1 = r4.p;
        r1.startActivity(r0);
    L_0x0076:
        r0 = r4.q;
        if (r0 == 0) goto L_0x000c;
    L_0x007a:
        r0 = r4.p;
        r0.finish();
        goto L_0x000c;
    L_0x0080:
        r0 = r4.p;
        r0 = r0 instanceof com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
        if (r0 == 0) goto L_0x0068;
    L_0x0086:
        r0 = r4.p;
        r2 = "j";
        r2 = com.budejie.www.http.NetWorkUtil.a(r0, r2);
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 != 0) goto L_0x009b;
    L_0x0094:
        r0 = r4.p;
        r0 = (com.budejie.www.activity.htmlpage.HtmlFeatureActivity) r0;
        r0.a(r2);
    L_0x009b:
        r0 = r1;
        goto L_0x0069;
    L_0x009d:
        r0 = new android.content.Intent;
        r1 = r4.p;
        r2 = com.budejie.www.activity.auditpost.AuditPostsActivity.class;
        r0.<init>(r1, r2);
        goto L_0x0069;
    L_0x00a7:
        r0 = new android.content.Intent;
        r1 = r4.p;
        r2 = com.budejie.www.activity.NewsFeedActivity.class;
        r0.<init>(r1, r2);
        r1 = com.budejie.www.activity.HomeGroup.j;
        if (r1 <= 0) goto L_0x0069;
    L_0x00b4:
        r1 = "isLoadCache";
        r0.putExtra(r1, r3);
        com.budejie.www.activity.HomeGroup.j = r3;
        goto L_0x0069;
    L_0x00bc:
        r0 = new android.content.Intent;
        r1 = r4.p;
        r2 = com.budejie.www.activity.PersonalProfileActivity.class;
        r0.<init>(r1, r2);
        r1 = com.budejie.www.activity.PersonalProfileActivity.c;
        r2 = r4.p;
        r2 = com.budejie.www.util.ai.b(r2);
        r0.putExtra(r1, r2);
        goto L_0x0069;
    L_0x00d1:
        r0 = new android.content.Intent;
        r1 = r4.p;
        r2 = com.budejie.www.activity.MyPostsActivity.class;
        r0.<init>(r1, r2);
        goto L_0x0069;
    L_0x00db:
        r0 = new android.content.Intent;
        r1 = r4.p;
        r2 = com.budejie.www.activity.mycomment.MyCommentActivity.class;
        r0.<init>(r1, r2);
        goto L_0x0069;
    L_0x00e5:
        r0 = new android.content.Intent;
        r1 = r4.p;
        r2 = com.budejie.www.activity.recommend.SuggestedFollowsActivity.class;
        r0.<init>(r1, r2);
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.w.a(int, int, android.content.Intent):void");
    }

    public static String a(Context context, Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : 0) == 0 || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return a(context, uri, null, null);
            }
            if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        } else if (a(uri)) {
            r1 = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(r1[0])) {
                return Environment.getExternalStorageDirectory() + "/" + r1[1];
            }
            return null;
        } else if (b(uri)) {
            return a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (!c(uri)) {
            return null;
        } else {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if (CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String str = "_id=?";
            return a(context, uri2, "_id=?", new String[]{r1[1]});
        }
    }

    public static String a(Context context, Uri uri, String str, String[] strArr) {
        Throwable th;
        Cursor cursor = null;
        String str2 = "_data";
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query == null) {
                            return str2;
                        }
                        query.close();
                        return str2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean a(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean b(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        String b = b(str, str2, str3, str4, str5);
        String b2 = b(b);
        try {
            b2 = URLEncoder.encode(b2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new Thread(new w$1(this, b + "&sign=\"" + b2 + com.alipay.sdk.sys.a.a + a())).start();
    }

    public String b(String str) {
        return SignUtils.sign(str, "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMF/nSpqUZ2pemJWeOXau8Dda3SA7skhmVdXn+FPbaPAw/mCkVCi1Funz54HPbzlWZc2UZDNYeGUGFYD+LD3N/h6cBJSUcgH+MGLxxOttKOnWhBFSDXkooscqvEPUXvxkGrXBmE2uZBZ75eaNthFTOGTw7QwpugjOvyEiQ8xGMTnAgMBAAECgYEAlFM7abEYIRAyBUGd6SxTshLI9QSEhl8gCUjdALx/IgB4+UFyHOrEeAHZ75xDYhI5K+VyftpBBsccJItkzlqjZptGS9oafJ2bcNloHPpH/6k9UQspVP1BCOC3AGo7WSdvUU3i7l7QeX3XyfS5+NX7N2OOQ0WgO+cNcxwzor+5oHECQQDim5qearIvZTBr6qGn2xWiAZdeQS+AtPG3w2sOOzM8E1muw/LWm43jx5NEFDmgyjb+d4kn2U8002hRHIrTH9cPAkEA2pihq4s0HWaQKs6jiDY9AYN9biMEAz/myogNV/eiuRqsM9IeDlhQka1z4xJHkKbRyWTmLL8jRZZvM6Ojmk90qQJADhm8n0roV4amYrCw6m75g6ExuA26VPntaI/iY3pPj9dsZzGONMhtJdVPVpcjltu+XEs336DoCtN01EdAZC7BBQJAcObucYSHWTwU8BckPYEOB08bpJvvQaJqmGamxa2AXSyajnVS0sPocSVuOnTBg8O1jNhRTgE2vYVSUBCPum6ZaQJBAI91OnOkrugRqkYdNEJm8RT6Sdmm8AwkrwN827y49uNDUtGKhcjKrCR3uJKqL+feCxX9OkFwt0bE+DSiLogoC1I=");
    }

    public String a() {
        return "sign_type=\"RSA\"";
    }

    public String b(String str, String str2, String str3, String str4, String str5) {
        return (((((((((("partner=\"2088021259674192\"" + "&seller_id=\"2088021259674192\"") + "&out_trade_no=\"" + str4 + "\"") + "&subject=\"" + str + "\"") + "&body=\"" + str2 + "\"") + "&total_fee=\"" + str3 + "\"") + "&notify_url=\"" + str5 + "\"") + "&service=\"mobile.securitypay.pay\"") + "&payment_type=\"1\"") + "&_input_charset=\"utf-8\"") + "&it_b_pay=\"30m\"") + "&return_url=\"m.alipay.com\"";
    }
}
