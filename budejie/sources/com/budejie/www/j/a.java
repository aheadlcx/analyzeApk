package com.budejie.www.j;

import android.content.Context;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.budejie.www.activity.label.k;
import com.budejie.www.activity.mycomment.MyCommentInfo;
import com.budejie.www.activity.mycomment.d;
import com.budejie.www.activity.mycomment.f;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.bean.HeadPortraitItem;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ListItemObject$ShareInfo;
import com.budejie.www.bean.PayHistoryItem;
import com.budejie.www.bean.RichObject;
import com.budejie.www.bean.ShenHeItem;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.type.TopicList;
import com.budejie.www.util.an;
import com.budejie.www.util.ax;
import com.budejie.www.util.z;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.x;
import com.umeng.update.UpdateConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static ListInfo a(String str) {
        ListInfo listInfo = new ListInfo();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject("info");
                if (optJSONObject != null) {
                    listInfo.count = optJSONObject.optInt("count");
                    listInfo.np = optJSONObject.optLong("np");
                    listInfo.code = optJSONObject.optInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY);
                    optJSONObject = optJSONObject.optJSONObject(UpdateConfig.a);
                    if (optJSONObject != null) {
                        listInfo.updateUrl = optJSONObject.optString(AlibcConstants.PF_ANDROID);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return listInfo;
    }

    public static ArrayList<ListItemObject> a(Context context, String str) {
        JSONArray jSONArray = null;
        ArrayList<ListItemObject> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            MobclickAgent.onEvent(context, "E03-A07", "object = new JSONObject(json)：" + str);
            Object obj = jSONArray;
        }
        if (jSONObject == null || !jSONObject.has("list")) {
            return arrayList;
        }
        try {
            jSONArray = jSONObject.getJSONArray("list");
        } catch (JSONException e2) {
            e2.printStackTrace();
            MobclickAgent.onEvent(context, "E03-A07", "array = object.getJSONArray(\"list\")：" + str);
        }
        return a(context, jSONArray);
    }

    public static ArrayList<ListItemObject> b(Context context, String str) {
        JSONArray jSONArray = null;
        ArrayList<ListItemObject> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            MobclickAgent.onEvent(context, "E03-A07", "object = new JSONObject(json)：" + str);
            Object obj = jSONArray;
        }
        if (jSONObject == null || !jSONObject.has("top_list")) {
            return arrayList;
        }
        try {
            jSONArray = jSONObject.getJSONArray("top_list");
        } catch (JSONException e2) {
            e2.printStackTrace();
            MobclickAgent.onEvent(context, "E03-A07", "array = object.getJSONArray(\"list\")：" + str);
        }
        return a(context, jSONArray);
    }

    public static ArrayList<ListItemObject> a(Context context, JSONArray jSONArray) {
        List arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                ListItemObject a = a(jSONArray.getJSONObject(i));
                if (a != null && (!TextUtils.isEmpty(a.getWid()) || a.isIs_ad())) {
                    arrayList.add(a);
                }
            } catch (JSONException e) {
                MobclickAgent.onEvent(context, "E03-A07", "数据解析异常：ListItemObject：" + null);
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        ax.a(arrayList, context);
        return arrayList;
    }

    public static ListItemObject a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ListItemObject listItemObject = new ListItemObject();
        listItemObject.setWid(jSONObject.optString("id"));
        listItemObject.setMid(jSONObject.optString(DeviceInfo.TAG_MID));
        listItemObject.setContent(jSONObject.optString("text"));
        String optString = jSONObject.optString("passtime");
        listItemObject.setPasstime(optString);
        listItemObject.setAddtime(optString);
        listItemObject.setCmdShowTime(optString);
        listItemObject.setLove(jSONObject.optInt("up"));
        listItemObject.setCai(jSONObject.optInt("down"));
        listItemObject.setRepost(jSONObject.optString("forward"));
        listItemObject.setComment(jSONObject.optString("comment"));
        listItemObject.setFavorite(jSONObject.optInt("favourite"));
        listItemObject.setWeixin_url(jSONObject.optString("share_url"));
        listItemObject.setStatus_text(jSONObject.optString("status_text"));
        listItemObject.setStatus(jSONObject.optInt("status"));
        a(listItemObject, jSONObject.optJSONObject("u"));
        optString = jSONObject.optString("type");
        if ("text".equals(optString)) {
            listItemObject.setType("29");
        } else if (CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(optString)) {
            listItemObject.setType(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            b(listItemObject, jSONObject.getJSONObject(optString));
        } else if ("gif".equals(optString)) {
            listItemObject.setType(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            c(listItemObject, jSONObject.getJSONObject(optString));
        } else if ("audio".equals(optString)) {
            listItemObject.setType("31");
            d(listItemObject, jSONObject.getJSONObject(optString));
        } else if ("video".equals(optString)) {
            listItemObject.setType("41");
            e(listItemObject, jSONObject.getJSONObject(optString));
        } else if ("html".equals(optString)) {
            listItemObject.setType("51");
            f(listItemObject, jSONObject.getJSONObject(optString));
        } else if ("repost".equals(optString)) {
            listItemObject.setType("61");
            listItemObject.setOriginal_topic(a(jSONObject.optJSONObject(optString)));
        } else if ("ad_image".equals(optString)) {
            listItemObject.setType(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            g(listItemObject, jSONObject.getJSONObject(optString));
        } else if ("ad_video".equals(optString)) {
            listItemObject.setType("41");
            g(listItemObject, jSONObject.getJSONObject(optString));
        }
        if (jSONObject.has("top_comment")) {
            try {
                h(listItemObject, jSONObject.getJSONObject("top_comment"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (jSONObject.has("top_comments")) {
            listItemObject.setHotCommentsJson(jSONObject.optString("top_comments"));
        }
        if (jSONObject.has("tags")) {
            i(listItemObject, jSONObject);
        }
        if (jSONObject.has("vote")) {
            listItemObject.setVoteDataJson(jSONObject.optString("vote"));
        }
        j(listItemObject, jSONObject);
        k(listItemObject, jSONObject);
        return listItemObject;
    }

    public static VoteData b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        VoteData voteData = new VoteData();
        voteData.votes = z.b(str, Vote.class);
        return voteData;
    }

    public static ArrayList<PlateBean> c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return z.b(str, PlateBean.class);
    }

    private static void a(ListItemObject listItemObject, JSONObject jSONObject) {
        if (jSONObject != null) {
            listItemObject.setName(jSONObject.optString("name"));
            listItemObject.setUid(jSONObject.optString(HistoryOpenHelper.COLUMN_UID));
            listItemObject.setProfile(jSONObject.optJSONArray(com.umeng.analytics.a.A).optString(0));
            listItemObject.setSina_v(jSONObject.optString("is_v"));
            listItemObject.setIs_vip(jSONObject.optString("is_vip"));
        }
    }

    private static void b(ListItemObject listItemObject, JSONObject jSONObject) {
        int i = 0;
        try {
            JSONArray jSONArray;
            int i2;
            if (jSONObject.has("small")) {
                jSONArray = jSONObject.getJSONArray("small");
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 == 0) {
                        listItemObject.setImage0(jSONArray.optString(i2));
                    } else if (i2 == 1) {
                        listItemObject.setCnd_img(jSONArray.optString(i2));
                    }
                }
            }
            if (jSONObject.has("medium")) {
                jSONArray = jSONObject.getJSONArray("medium");
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 == 0) {
                        listItemObject.setImage1(jSONArray.optString(i2));
                    } else if (i2 == 1) {
                        listItemObject.setCnd_img(jSONArray.optString(i2));
                    }
                }
            }
            if (jSONObject.has("big")) {
                jSONArray = jSONObject.getJSONArray("big");
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 == 0) {
                        listItemObject.setImage2(jSONArray.optString(i2));
                    } else if (i2 == 1) {
                        listItemObject.setCnd_img(jSONArray.optString(i2));
                    }
                }
            }
            if (jSONObject.has("thumbnail_small")) {
                jSONArray = jSONObject.getJSONArray("thumbnail_small");
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 == 0) {
                        listItemObject.setSmallImage(jSONArray.optString(i2));
                    } else if (i2 == 1) {
                        listItemObject.setcnd_SmallImg(jSONArray.optString(i2));
                    }
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("download_url");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                String[] strArr = new String[optJSONArray.length()];
                while (i < optJSONArray.length()) {
                    strArr[i] = optJSONArray.optString(i);
                    i++;
                }
                listItemObject.setDownloadImageUris(strArr);
            }
            listItemObject.setWidth(jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH));
            listItemObject.setHeight(jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void c(ListItemObject listItemObject, JSONObject jSONObject) {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("images");
        int i2 = 0;
        while (optJSONArray != null && i2 < optJSONArray.length()) {
            if (i2 == 0) {
                listItemObject.setImgUrl(optJSONArray.optString(i2));
            } else if (i2 == 1) {
                listItemObject.setCnd_img(optJSONArray.optString(i2));
            }
            i2++;
        }
        listItemObject.setGifFistFrame(jSONObject.optJSONArray("gif_thumbnail").optString(0));
        JSONArray optJSONArray2 = jSONObject.optJSONArray("download_url");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            String[] strArr = new String[optJSONArray2.length()];
            while (i < optJSONArray2.length()) {
                strArr[i] = optJSONArray2.optString(i);
                i++;
            }
            listItemObject.setDownloadImageUris(strArr);
        }
        listItemObject.setWidth(jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH));
        listItemObject.setHeight(jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT));
        listItemObject.setIs_gif("1");
    }

    private static void d(ListItemObject listItemObject, JSONObject jSONObject) {
        int i = 0;
        try {
            if (jSONObject.has("audio")) {
                listItemObject.setVoiceUri(jSONObject.getJSONArray("audio").optString(0));
            }
            if (jSONObject.has("thumbnail")) {
                listItemObject.setImgUrl(jSONObject.getJSONArray("thumbnail").optString(0));
            }
            if (jSONObject.has("thumbnail_small")) {
                JSONArray jSONArray = jSONObject.getJSONArray("thumbnail_small");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (i2 == 0) {
                        listItemObject.setSmallImage(jSONArray.optString(i2));
                    } else if (i2 == 1) {
                        listItemObject.setcnd_SmallImg(jSONArray.optString(i2));
                    }
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("download_url");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                String[] strArr = new String[optJSONArray.length()];
                while (i < optJSONArray.length()) {
                    strArr[i] = optJSONArray.optString(i);
                    i++;
                }
                listItemObject.setDownloadImageUris(strArr);
            }
            listItemObject.setWidth(jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH));
            listItemObject.setHeight(jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT));
            listItemObject.setVoicetime(jSONObject.optString("duration"));
            listItemObject.setPlaycount(jSONObject.optString("playcount"));
            listItemObject.setPlayfcount(jSONObject.optString("playfcount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void e(ListItemObject listItemObject, JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("video");
            if (optJSONArray != null) {
                listItemObject.setVideouri(optJSONArray.optString(0));
                listItemObject.setVideouriBackup(optJSONArray.optString(1));
            }
            if (jSONObject.has("thumbnail")) {
                listItemObject.setImgUrl(jSONObject.getJSONArray("thumbnail").optString(0));
            }
            if (jSONObject.has("thumbnail_small")) {
                optJSONArray = jSONObject.getJSONArray("thumbnail_small");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    if (i == 0) {
                        listItemObject.setSmallImage(optJSONArray.optString(i));
                    } else if (i == 1) {
                        listItemObject.setcnd_SmallImg(optJSONArray.optString(i));
                    }
                }
            }
            if (jSONObject.optJSONArray("download") != null) {
                listItemObject.setDownloadVideoUris(jSONObject.optJSONArray("download").optString(0), jSONObject.optJSONArray("download").optString(1));
            }
            listItemObject.setWidth(jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH));
            listItemObject.setHeight(jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT));
            listItemObject.setVideotime(jSONObject.optString("duration"));
            listItemObject.setPlaycount(jSONObject.optString("playcount"));
            listItemObject.setPlayfcount(jSONObject.optString("playfcount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void f(ListItemObject listItemObject, JSONObject jSONObject) {
        try {
            RichObject richObject = new RichObject();
            richObject.setBody(jSONObject.optString(com.umeng.analytics.a.z));
            richObject.setTitle(jSONObject.optString("title"));
            if (jSONObject.has("thumbnail")) {
                richObject.setImgUrl(jSONObject.getJSONArray("thumbnail").optString(0));
            }
            richObject.setDesc(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            richObject.setSourceUrl(jSONObject.optString("source_url"));
            listItemObject.setRichObject(richObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void g(ListItemObject listItemObject, JSONObject jSONObject) {
        listItemObject.setIs_ad(true);
        listItemObject.setAd_id(jSONObject.optInt("adid"));
        listItemObject.setContent(jSONObject.optString("title"));
        listItemObject.setWidth(jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH));
        listItemObject.setHeight(jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT));
        listItemObject.setImgUrl(jSONObject.optString(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY));
        if (TextUtils.isEmpty(listItemObject.getImgUrl())) {
            listItemObject.setImgUrl(jSONObject.optString("thumbnail"));
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("link");
        if (optJSONObject != null) {
            listItemObject.setAd_url(optJSONObject.optString(AlibcConstants.PF_ANDROID));
        }
        listItemObject.setVideouri(jSONObject.optString("video"));
        listItemObject.setVideotime(jSONObject.optString("duration"));
    }

    private static void h(ListItemObject listItemObject, JSONObject jSONObject) {
        try {
            listItemObject.setHotcmt(c(jSONObject));
            CharSequence optString = jSONObject.optString("precmt");
            if (!TextUtils.isEmpty(optString) && !"[]".equals(optString)) {
                listItemObject.setPrecmt(c(jSONObject.getJSONObject("precmt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static MyCommentInfo b(JSONObject jSONObject) {
        MyCommentInfo c;
        JSONException e;
        try {
            c = c(jSONObject);
            try {
                CharSequence optString = jSONObject.optString("precmt");
                if (!(TextUtils.isEmpty(optString) || "[]".equals(optString))) {
                    c.precmt = c(jSONObject.getJSONObject("precmt"));
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return c;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            c = null;
            e = jSONException;
            e.printStackTrace();
            return c;
        }
        return c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.budejie.www.activity.mycomment.MyCommentInfo c(org.json.JSONObject r9) {
        /*
        r1 = 0;
        r2 = 0;
        if (r9 == 0) goto L_0x0314;
    L_0x0004:
        r0 = new com.budejie.www.activity.mycomment.MyCommentInfo;	 Catch:{ Exception -> 0x030a }
        r0.<init>();	 Catch:{ Exception -> 0x030a }
        r3 = "id";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.id = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "content";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.content = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "precid";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.precid = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "preuid";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.preuid = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "voiceuri";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.voiceuri = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "voicetime";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.voicetime = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "like_count";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.like_count = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "hate_count";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.hate_count = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "passtime";
        r3 = r9.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.ctime = r3;	 Catch:{ Exception -> 0x0312 }
        r3 = "u";
        r3 = r9.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x0093;
    L_0x0059:
        r3 = "u";
        r3 = r9.getJSONObject(r3);	 Catch:{ Exception -> 0x0312 }
        r4 = new com.budejie.www.bean.User;	 Catch:{ Exception -> 0x0312 }
        r4.<init>();	 Catch:{ Exception -> 0x0312 }
        r5 = "uid";
        r5 = r3.optString(r5);	 Catch:{ Exception -> 0x0312 }
        r4.id = r5;	 Catch:{ Exception -> 0x0312 }
        r5 = "name";
        r5 = r3.optString(r5);	 Catch:{ Exception -> 0x0312 }
        r4.username = r5;	 Catch:{ Exception -> 0x0312 }
        r5 = "sex";
        r5 = r3.optString(r5);	 Catch:{ Exception -> 0x0312 }
        r4.sex = r5;	 Catch:{ Exception -> 0x0312 }
        r5 = "header";
        r5 = r3.optJSONArray(r5);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r5 = r5.optString(r6);	 Catch:{ Exception -> 0x0312 }
        r4.profile_image = r5;	 Catch:{ Exception -> 0x0312 }
        r5 = "is_vip";
        r3 = r3.optString(r5);	 Catch:{ Exception -> 0x0312 }
        r4.is_vip = r3;	 Catch:{ Exception -> 0x0312 }
        r0.user = r4;	 Catch:{ Exception -> 0x0312 }
    L_0x0093:
        r3 = "cmt_type";
        r3 = r9.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x00a4;
    L_0x009b:
        r3 = "cmt_type";
        r3 = r9.getString(r3);	 Catch:{ Exception -> 0x0312 }
        r0.setType(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x00a4:
        r3 = "video";
        r3 = r9.getJSONObject(r3);	 Catch:{ Exception -> 0x01c0 }
    L_0x00aa:
        if (r3 == 0) goto L_0x0149;
    L_0x00ac:
        r4 = "download";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x00d5;
    L_0x00b4:
        r4 = "download";
        r4 = r3.getJSONArray(r4);	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x0312 }
        r4 = r5.append(r4);	 Catch:{ Exception -> 0x0312 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setVideoDownLoadUrl(r4);	 Catch:{ Exception -> 0x0312 }
    L_0x00d5:
        r4 = "thumbnail";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x00fe;
    L_0x00dd:
        r4 = "thumbnail";
        r4 = r3.getJSONArray(r4);	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x0312 }
        r4 = r5.append(r4);	 Catch:{ Exception -> 0x0312 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setVideoThumbnail(r4);	 Catch:{ Exception -> 0x0312 }
    L_0x00fe:
        r4 = "video";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x0127;
    L_0x0106:
        r4 = "video";
        r4 = r3.getJSONArray(r4);	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x0312 }
        r4 = r5.append(r4);	 Catch:{ Exception -> 0x0312 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setVideoPlayUrl(r4);	 Catch:{ Exception -> 0x0312 }
    L_0x0127:
        r4 = "width";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x0138;
    L_0x012f:
        r4 = "width";
        r4 = r3.optInt(r4);	 Catch:{ Exception -> 0x0312 }
        r0.setImageWidth(r4);	 Catch:{ Exception -> 0x0312 }
    L_0x0138:
        r4 = "height";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x0149;
    L_0x0140:
        r4 = "height";
        r3 = r3.optInt(r4);	 Catch:{ Exception -> 0x0312 }
        r0.setImageHeight(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x0149:
        r3 = "audio";
        r3 = r9.getJSONObject(r3);	 Catch:{ Exception -> 0x01c4 }
    L_0x014f:
        if (r3 == 0) goto L_0x018b;
    L_0x0151:
        r4 = "audio";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x017a;
    L_0x0159:
        r4 = "audio";
        r4 = r3.getJSONArray(r4);	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ Exception -> 0x0312 }
        r4 = r5.append(r4);	 Catch:{ Exception -> 0x0312 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setAudioUrl(r4);	 Catch:{ Exception -> 0x0312 }
    L_0x017a:
        r4 = "duration";
        r4 = r3.has(r4);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x018b;
    L_0x0182:
        r4 = "duration";
        r3 = r3.optInt(r4);	 Catch:{ Exception -> 0x0312 }
        r0.setAudioDuration(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x018b:
        r3 = "image";
        r3 = r9.getJSONObject(r3);	 Catch:{ Exception -> 0x01c7 }
        r4 = r3;
    L_0x0192:
        if (r4 == 0) goto L_0x0241;
    L_0x0194:
        r3 = "download";
        r3 = r4.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x01cd;
    L_0x019c:
        r3 = "download";
        r5 = r4.getJSONArray(r3);	 Catch:{ Exception -> 0x0312 }
        if (r5 == 0) goto L_0x01cd;
    L_0x01a4:
        r3 = r5.length();	 Catch:{ Exception -> 0x0312 }
        if (r3 <= 0) goto L_0x01cd;
    L_0x01aa:
        r3 = r5.length();	 Catch:{ Exception -> 0x0312 }
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0312 }
        r3 = r2;
    L_0x01b1:
        r7 = r5.length();	 Catch:{ Exception -> 0x0312 }
        if (r3 >= r7) goto L_0x01ca;
    L_0x01b7:
        r7 = r5.optString(r3);	 Catch:{ Exception -> 0x0312 }
        r6[r3] = r7;	 Catch:{ Exception -> 0x0312 }
        r3 = r3 + 1;
        goto L_0x01b1;
    L_0x01c0:
        r3 = move-exception;
        r3 = r1;
        goto L_0x00aa;
    L_0x01c4:
        r3 = move-exception;
        r3 = r1;
        goto L_0x014f;
    L_0x01c7:
        r3 = move-exception;
        r4 = r1;
        goto L_0x0192;
    L_0x01ca:
        r0.setImageDownloadUrls(r6);	 Catch:{ Exception -> 0x0312 }
    L_0x01cd:
        r3 = "images";
        r3 = r4.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x01f6;
    L_0x01d5:
        r3 = "images";
        r3 = r4.getJSONArray(r3);	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r3 = r3.get(r6);	 Catch:{ Exception -> 0x0312 }
        r3 = r5.append(r3);	 Catch:{ Exception -> 0x0312 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setImageShowUrl(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x01f6:
        r3 = "thumbnail";
        r3 = r4.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x021f;
    L_0x01fe:
        r3 = "thumbnail";
        r3 = r4.getJSONArray(r3);	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = 0;
        r3 = r3.get(r6);	 Catch:{ Exception -> 0x0312 }
        r3 = r5.append(r3);	 Catch:{ Exception -> 0x0312 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setImageThumbUrl(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x021f:
        r3 = "width";
        r3 = r4.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x0230;
    L_0x0227:
        r3 = "width";
        r3 = r4.optInt(r3);	 Catch:{ Exception -> 0x0312 }
        r0.setImageWidth(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x0230:
        r3 = "height";
        r3 = r4.has(r3);	 Catch:{ Exception -> 0x0312 }
        if (r3 == 0) goto L_0x0241;
    L_0x0238:
        r3 = "height";
        r3 = r4.optInt(r3);	 Catch:{ Exception -> 0x0312 }
        r0.setImageHeight(r3);	 Catch:{ Exception -> 0x0312 }
    L_0x0241:
        r3 = "gif";
        r1 = r9.getJSONObject(r3);	 Catch:{ Exception -> 0x0276 }
        r3 = r1;
    L_0x0248:
        if (r3 == 0) goto L_0x0309;
    L_0x024a:
        r1 = "download";
        r1 = r3.has(r1);	 Catch:{ Exception -> 0x0312 }
        if (r1 == 0) goto L_0x027c;
    L_0x0252:
        r1 = "download";
        r4 = r3.getJSONArray(r1);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x027c;
    L_0x025a:
        r1 = r4.length();	 Catch:{ Exception -> 0x0312 }
        if (r1 <= 0) goto L_0x027c;
    L_0x0260:
        r1 = r4.length();	 Catch:{ Exception -> 0x0312 }
        r5 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0312 }
        r1 = r2;
    L_0x0267:
        r6 = r4.length();	 Catch:{ Exception -> 0x0312 }
        if (r1 >= r6) goto L_0x0279;
    L_0x026d:
        r6 = r4.optString(r1);	 Catch:{ Exception -> 0x0312 }
        r5[r1] = r6;	 Catch:{ Exception -> 0x0312 }
        r1 = r1 + 1;
        goto L_0x0267;
    L_0x0276:
        r3 = move-exception;
        r3 = r1;
        goto L_0x0248;
    L_0x0279:
        r0.setGifDownLoadUrls(r5);	 Catch:{ Exception -> 0x0312 }
    L_0x027c:
        r1 = "images";
        r1 = r3.has(r1);	 Catch:{ Exception -> 0x0312 }
        if (r1 == 0) goto L_0x02be;
    L_0x0284:
        r1 = "images";
        r4 = r3.getJSONArray(r1);	 Catch:{ Exception -> 0x0312 }
        if (r4 == 0) goto L_0x02be;
    L_0x028c:
        r1 = r4.length();	 Catch:{ Exception -> 0x0312 }
        if (r1 <= 0) goto L_0x02be;
    L_0x0292:
        r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0312 }
        r5.<init>();	 Catch:{ Exception -> 0x0312 }
        r1 = r2;
    L_0x0298:
        r2 = r4.length();	 Catch:{ Exception -> 0x0312 }
        if (r1 >= r2) goto L_0x02bb;
    L_0x029e:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r2.<init>();	 Catch:{ Exception -> 0x0312 }
        r6 = "";
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0312 }
        r6 = r4.get(r1);	 Catch:{ Exception -> 0x0312 }
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0312 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0312 }
        r5.add(r2);	 Catch:{ Exception -> 0x0312 }
        r1 = r1 + 1;
        goto L_0x0298;
    L_0x02bb:
        r0.setGifShowUrl(r5);	 Catch:{ Exception -> 0x0312 }
    L_0x02be:
        r1 = "thumbnail";
        r1 = r3.has(r1);	 Catch:{ Exception -> 0x0312 }
        if (r1 == 0) goto L_0x02e7;
    L_0x02c6:
        r1 = "thumbnail";
        r1 = r3.getJSONArray(r1);	 Catch:{ Exception -> 0x0312 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0312 }
        r2.<init>();	 Catch:{ Exception -> 0x0312 }
        r4 = "";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x0312 }
        r4 = 0;
        r1 = r1.get(r4);	 Catch:{ Exception -> 0x0312 }
        r1 = r2.append(r1);	 Catch:{ Exception -> 0x0312 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0312 }
        r0.setGifThumbUrl(r1);	 Catch:{ Exception -> 0x0312 }
    L_0x02e7:
        r1 = "width";
        r1 = r3.has(r1);	 Catch:{ Exception -> 0x0312 }
        if (r1 == 0) goto L_0x02f8;
    L_0x02ef:
        r1 = "width";
        r1 = r3.optInt(r1);	 Catch:{ Exception -> 0x0312 }
        r0.setGifWidth(r1);	 Catch:{ Exception -> 0x0312 }
    L_0x02f8:
        r1 = "height";
        r1 = r3.has(r1);	 Catch:{ Exception -> 0x0312 }
        if (r1 == 0) goto L_0x0309;
    L_0x0300:
        r1 = "height";
        r1 = r3.optInt(r1);	 Catch:{ Exception -> 0x0312 }
        r0.setGifHeight(r1);	 Catch:{ Exception -> 0x0312 }
    L_0x0309:
        return r0;
    L_0x030a:
        r0 = move-exception;
        r8 = r0;
        r0 = r1;
        r1 = r8;
    L_0x030e:
        r1.printStackTrace();
        goto L_0x0309;
    L_0x0312:
        r1 = move-exception;
        goto L_0x030e;
    L_0x0314:
        r0 = r1;
        goto L_0x0309;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.j.a.c(org.json.JSONObject):com.budejie.www.activity.mycomment.MyCommentInfo");
    }

    private static void i(ListItemObject listItemObject, JSONObject jSONObject) {
        try {
            if (jSONObject.has("tags")) {
                listItemObject.setPlateDatasJson(jSONObject.getString("tags"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void j(ListItemObject listItemObject, JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("share_info");
            if (optJSONObject != null) {
                ListItemObject$ShareInfo listItemObject$ShareInfo = new ListItemObject$ShareInfo();
                listItemObject$ShareInfo.setUid(optJSONObject.optString(HistoryOpenHelper.COLUMN_UID));
                listItemObject$ShareInfo.setName(optJSONObject.optString("name"));
                listItemObject$ShareInfo.setShare_time(optJSONObject.optString("share_time"));
                listItemObject.setShareInfo(listItemObject$ShareInfo);
            }
        }
    }

    private static void k(ListItemObject listItemObject, JSONObject jSONObject) {
        int i = 0;
        if (jSONObject != null && jSONObject.has("praise")) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("praise");
                while (i < optJSONArray.length() && i < 5) {
                    HeadPortraitItem headPortraitItem = new HeadPortraitItem();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    if (jSONObject2.has("sex")) {
                        headPortraitItem.setSex(jSONObject2.getString("sex"));
                    }
                    if (jSONObject2.has(com.umeng.analytics.a.A)) {
                        JSONArray optJSONArray2;
                        JSONObject optJSONObject = jSONObject2.optJSONObject(com.umeng.analytics.a.A);
                        if (optJSONObject.has("big")) {
                            optJSONArray2 = optJSONObject.optJSONArray("big");
                            if (!(optJSONArray2 == null || optJSONArray2.length() <= 0 || TextUtils.isEmpty(optJSONArray2.getString(0)))) {
                                headPortraitItem.setProfile_image(optJSONArray2.getString(0));
                            }
                        }
                        if (optJSONObject.has("medium")) {
                            optJSONArray2 = optJSONObject.optJSONArray("medium");
                            if (!(optJSONArray2 == null || optJSONArray2.length() <= 0 || TextUtils.isEmpty(optJSONArray2.getString(0)))) {
                                headPortraitItem.setProfile_image(optJSONArray2.getString(0));
                            }
                        }
                        if (optJSONObject.has("small")) {
                            JSONArray optJSONArray3 = optJSONObject.optJSONArray("small");
                            if (!(optJSONArray3 == null || optJSONArray3.length() <= 0 || TextUtils.isEmpty(optJSONArray3.getString(0)))) {
                                headPortraitItem.setProfile_image(optJSONArray3.getString(0));
                            }
                        }
                    }
                    if (jSONObject2.has(HistoryOpenHelper.COLUMN_UID)) {
                        headPortraitItem.setUserid(jSONObject2.getString(HistoryOpenHelper.COLUMN_UID));
                    }
                    if (jSONObject2.has("name")) {
                        headPortraitItem.setUsername(jSONObject2.getString("name"));
                    }
                    if (jSONObject2.has("praise_time")) {
                        headPortraitItem.setPraise_time(jSONObject2.getString("praise_time"));
                    }
                    if (jSONObject2.has("introduction")) {
                        headPortraitItem.setIntroduce(jSONObject2.getString("introduction"));
                    }
                    if (jSONObject2.has("fans_count")) {
                        headPortraitItem.setFans_count(jSONObject2.getString("fans_count"));
                    }
                    if (jSONObject2.has("tiezi_count")) {
                        headPortraitItem.setTiezi_count(jSONObject2.getString("tiezi_count"));
                    }
                    if (jSONObject2.has("is_follow")) {
                        headPortraitItem.setIs_follow(jSONObject2.getString("is_follow"));
                    }
                    arrayList.add(headPortraitItem);
                    i++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listItemObject.setHeadPortraitItems(arrayList);
        }
    }

    public static TopicList c(Context context, String str) {
        TopicList topicList = new TopicList();
        topicList.setInfo(a(str));
        topicList.setTopics(a(context, str));
        try {
            topicList.setTop_topic(a(new JSONObject(str).optJSONObject("top_topic")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return topicList;
    }

    public static ShenHeItem d(Context context, String str) {
        ShenHeItem shenHeItem = new ShenHeItem();
        long j = a(str).np;
        shenHeItem.setLastId(j == 0 ? "" : String.valueOf(j));
        ArrayList a = a(context, str);
        ArrayList arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(an.a((ListItemObject) it.next()));
        }
        shenHeItem.setDataList(arrayList);
        return shenHeItem;
    }

    public static k e(Context context, String str) {
        k kVar = new k();
        ListInfo a = a(str);
        kVar.c = String.valueOf(a.np);
        kVar.d = a.np;
        kVar.b = a(context, str);
        kVar.a = b(context, str);
        return kVar;
    }

    public static f f(Context context, String str) {
        f fVar = new f();
        try {
            fVar.a = new ArrayList();
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("list");
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                d dVar = new d();
                dVar.a = c(optJSONObject);
                dVar.b = c(optJSONObject.optJSONObject("precmt"));
                dVar.c = a(optJSONObject.optJSONObject("topic"));
                fVar.a.add(dVar);
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fVar;
    }

    public static ShenHeItem g(Context context, String str) {
        ArrayList a = a(context, str);
        ArrayList arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(an.a((ListItemObject) it.next()));
        }
        ShenHeItem shenHeItem = new ShenHeItem();
        shenHeItem.setDataList(arrayList);
        return shenHeItem;
    }

    public static List<PayHistoryItem> h(Context context, String str) {
        List<PayHistoryItem> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("list");
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                PayHistoryItem payHistoryItem = new PayHistoryItem();
                payHistoryItem.setTitle(optJSONObject.optString("title"));
                payHistoryItem.setEnd_time(optJSONObject.optString(x.X));
                arrayList.add(payHistoryItem);
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
