package com.budejie.www.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.util.j;
import com.budejie.www.bean.CmtMyTieziItem;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.CommentVoiceToWordsItem;
import com.budejie.www.bean.DingOrCaiCommentNewsItem;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.FansList;
import com.budejie.www.bean.HeadPortraitData;
import com.budejie.www.bean.HeadPortraitItem;
import com.budejie.www.bean.HotComment;
import com.budejie.www.bean.ImageBean;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.MentionedItem;
import com.budejie.www.bean.MyCollectItem;
import com.budejie.www.bean.MyMsgItem;
import com.budejie.www.bean.MyMsgListData;
import com.budejie.www.bean.MyNewsData;
import com.budejie.www.bean.MyNewsItem;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.bean.RecommendUser;
import com.budejie.www.bean.ReplyNewsItem;
import com.budejie.www.bean.ReportItem;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SyncCollectItem;
import com.budejie.www.bean.SystemNewsItem;
import com.budejie.www.bean.Topics;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.a;
import com.umeng.analytics.pro.d.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class z {
    static String a = "JsonUtils";

    public static ArrayList<CommentItem> a(String str, String str2, Context context) {
        List arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || "[]".equals(str) || (!str.contains("[") && !str.contains("]"))) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null) {
                String string;
                CommentItem commentItem;
                String str3 = "";
                if (jSONObject.has("author_uid")) {
                    string = jSONObject.getString("author_uid");
                } else {
                    string = str3;
                }
                if (jSONObject.has(StatisticCodeTable.HOT)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(StatisticCodeTable.HOT);
                    if (jSONObject2.has("list")) {
                        Collection a = a(jSONObject2.getJSONArray("list"), string);
                        if (!(a == null || a.isEmpty() || !str2.equals("0"))) {
                            commentItem = (CommentItem) a.get(0);
                            commentItem.setIshot(true);
                            commentItem.setTagIsShow(true);
                            commentItem.setHotNum(a.size());
                        }
                        arrayList.addAll(a);
                    }
                    if (jSONObject2.has("info")) {
                        String string2 = jSONObject2.getJSONObject("info").getString("np");
                        if (!(string2 == null || string2.equals("null"))) {
                            ((CommentItem) arrayList.get(arrayList.size() - 1)).setHotNp(string2);
                        }
                    }
                }
                if (jSONObject.has("normal")) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject("normal");
                    if (jSONObject3.has("list")) {
                        Collection a2 = a(jSONObject3.getJSONArray("list"), string);
                        if (!(a2 == null || a2.isEmpty() || !str2.equals("0"))) {
                            commentItem = (CommentItem) a2.get(0);
                            commentItem.setIsnew(true);
                            commentItem.setTagIsShow(true);
                        }
                        arrayList.addAll(a2);
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseComments " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseComments " + e2.toString());
        }
        ax.b(arrayList, context);
        return arrayList;
    }

    public static CommentItem a(String str) {
        CommentItem commentItem = new CommentItem();
        if (!(TextUtils.isEmpty(str) || "[]".equals(str) || (!str.contains("[") && !str.contains("]")))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null && jSONObject.has("data")) {
                    commentItem = b(jSONObject.getJSONObject("data"));
                }
            } catch (JSONException e) {
                Log.e(a, "parseComments " + e.toString());
            } catch (Exception e2) {
                Log.e(a, "parseComments " + e2.toString());
            }
        }
        return commentItem;
    }

    public static CommentVoiceToWordsItem b(String str) {
        CommentVoiceToWordsItem commentVoiceToWordsItem = new CommentVoiceToWordsItem();
        if (!(TextUtils.isEmpty(str) || "[]".equals(str))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null && jSONObject.has("data")) {
                    jSONObject = jSONObject.getJSONObject("data");
                    if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                        commentVoiceToWordsItem.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
                    }
                    if (jSONObject.has("info")) {
                        commentVoiceToWordsItem.setInfo(jSONObject.getString("info"));
                    }
                    if (jSONObject.has("msg")) {
                        commentVoiceToWordsItem.setMsg(jSONObject.getString("msg"));
                    }
                }
            } catch (JSONException e) {
            } catch (Exception e2) {
            }
        }
        return commentVoiceToWordsItem;
    }

    public static ArrayList<CommentItem> a(String str, boolean z) {
        ArrayList<CommentItem> arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || "[]".equals(str) || (!str.contains("[") && !str.contains("]"))) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null) {
                String string;
                CommentItem commentItem;
                String str2 = "";
                if (jSONObject.has("author_uid")) {
                    string = jSONObject.getString("author_uid");
                } else {
                    string = str2;
                }
                if (jSONObject.has(StatisticCodeTable.HOT)) {
                    Collection a = a(jSONObject.getJSONArray(StatisticCodeTable.HOT), string);
                    if (!(a == null || a.isEmpty())) {
                        commentItem = (CommentItem) a.get(0);
                        commentItem.setIshot(true);
                        commentItem.setTagIsShow(true);
                        commentItem.setHotNum(a.size());
                    }
                    arrayList.addAll(a);
                }
                if (jSONObject.has("data")) {
                    Collection a2 = a(jSONObject.getJSONArray("data"), string);
                    if (!(!z || a2 == null || a2.isEmpty())) {
                        commentItem = (CommentItem) a2.get(0);
                        commentItem.setIsnew(true);
                        commentItem.setTagIsShow(true);
                    }
                    arrayList.addAll(a2);
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseComments " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseComments " + e2.toString());
        }
        return arrayList;
    }

    private static ArrayList<CommentItem> a(JSONArray jSONArray, String str) {
        ArrayList<CommentItem> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                CommentItem b = b(jSONArray.getJSONObject(i));
                b.setAuthorUid(str);
                arrayList.add(b);
                ArrayList author_replay = b.getAuthor_replay();
                if (author_replay != null) {
                    for (int i2 = 0; i2 < author_replay.size(); i2++) {
                        b = (CommentItem) author_replay.get(i2);
                        b.setHotAuthorReplay(true);
                        if (i2 == 0) {
                            b.setHotAuthorReplayFirst(true);
                        }
                        arrayList.add(b);
                    }
                }
            } catch (JSONException e) {
                Log.e(a, "parseCommends " + e.toString());
            } catch (Exception e2) {
                Log.e(a, "parseCommends " + e2.toString());
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.budejie.www.bean.CommentItem b(org.json.JSONObject r11) {
        /*
        r1 = 0;
        r2 = 0;
        if (r11 != 0) goto L_0x0006;
    L_0x0004:
        r0 = r1;
    L_0x0005:
        return r0;
    L_0x0006:
        r0 = new com.budejie.www.bean.CommentItem;	 Catch:{ JSONException -> 0x0545, Exception -> 0x053f }
        r0.<init>();	 Catch:{ JSONException -> 0x0545, Exception -> 0x053f }
        r3 = "lapped";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x004c;
    L_0x0013:
        r1 = "lapped";
        r1 = r11.getString(r1);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setLapped(r1);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r1 = "cid";
        r1 = r11.has(r1);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r1 == 0) goto L_0x0005;
    L_0x0024:
        r1 = "cid";
        r1 = r11.getString(r1);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setId(r1);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        goto L_0x0005;
    L_0x002e:
        r1 = move-exception;
    L_0x002f:
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "parseCommends ";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x0005;
    L_0x004c:
        r3 = "type";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x005d;
    L_0x0054:
        r3 = "type";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setType(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x005d:
        r3 = "status";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x006e;
    L_0x0065:
        r3 = "status";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setStatus(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x006e:
        r3 = "id";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x007f;
    L_0x0076:
        r3 = "id";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setId(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x007f:
        r3 = "content";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0090;
    L_0x0087:
        r3 = "content";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setContent(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0090:
        r3 = "ctime";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = "ctime";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setTime(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x00a1:
        r3 = "like_count";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x00b2;
    L_0x00a9:
        r3 = "like_count";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setDingCount(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x00b2:
        r3 = "hate_count";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x00c3;
    L_0x00ba:
        r3 = "hate_count";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setCaiCount(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x00c3:
        r3 = "voiceuri";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x00d4;
    L_0x00cb:
        r3 = "voiceuri";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setVoiceuri(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x00d4:
        r3 = "voicetime";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x00e5;
    L_0x00dc:
        r3 = "voicetime";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setVoicetime(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x00e5:
        r3 = "user";
        r4 = r11.getJSONObject(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 != 0) goto L_0x00f0;
    L_0x00ed:
        r0 = r1;
        goto L_0x0005;
    L_0x00f0:
        r3 = "precmt";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0139;
    L_0x00f8:
        r3 = "precmt";
        r3 = r11.getJSONObject(r3);	 Catch:{ Exception -> 0x0387, JSONException -> 0x002e }
    L_0x00fe:
        if (r3 == 0) goto L_0x0139;
    L_0x0100:
        r5 = "content";
        r5 = r3.has(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 == 0) goto L_0x0111;
    L_0x0108:
        r5 = "content";
        r5 = r3.getString(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setMpreContent(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0111:
        r5 = "user";
        r3 = r3.getJSONObject(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = "username";
        r5 = r3.has(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 == 0) goto L_0x0128;
    L_0x011f:
        r5 = "username";
        r5 = r3.getString(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setMpreName(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0128:
        r5 = "content";
        r5 = r3.has(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 == 0) goto L_0x0139;
    L_0x0130:
        r5 = "content";
        r3 = r3.getString(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setMpreContent(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0139:
        r3 = "precmts";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0152;
    L_0x0141:
        r3 = "precmts";
        r3 = r11.getJSONArray(r3);	 Catch:{ Exception -> 0x038b, JSONException -> 0x002e }
    L_0x0147:
        if (r3 == 0) goto L_0x0152;
    L_0x0149:
        r5 = "";
        r3 = a(r3, r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setReplyList(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0152:
        r3 = "floor";
        r3 = r11.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0163;
    L_0x015a:
        r3 = "floor";
        r3 = r11.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setFloorNum(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0163:
        r3 = "profile_image";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0197;
    L_0x016b:
        r3 = "profile_image";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x038f;
    L_0x0177:
        r6 = java.lang.Math.random();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r8 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r6 = r6 * r8;
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = "default";
        r3 = r3.append(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r3.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setProfile(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0197:
        r3 = "username";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x01a8;
    L_0x019f:
        r3 = "username";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setUname(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x01a8:
        r3 = "qq_uid";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x01c7;
    L_0x01b0:
        r3 = "qq_uid";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 != 0) goto L_0x01c7;
    L_0x01bc:
        r5 = "null";
        r5 = r5.equals(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 != 0) goto L_0x01c7;
    L_0x01c4:
        r0.setQqUid(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x01c7:
        r3 = "weibo_uid";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x01e6;
    L_0x01cf:
        r3 = "weibo_uid";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 != 0) goto L_0x01e6;
    L_0x01db:
        r5 = "null";
        r5 = r5.equals(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 != 0) goto L_0x01e6;
    L_0x01e3:
        r0.setSinaUid(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x01e6:
        r3 = "qzone_uid";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0205;
    L_0x01ee:
        r3 = "qzone_uid";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 != 0) goto L_0x0205;
    L_0x01fa:
        r5 = "null";
        r5 = r5.equals(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 != 0) goto L_0x0205;
    L_0x0202:
        r0.setQzoneUid(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0205:
        r3 = "sex";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0216;
    L_0x020d:
        r3 = "sex";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setSex(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0216:
        r3 = "id";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0227;
    L_0x021e:
        r3 = "id";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setUid(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0227:
        r3 = "is_vip";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0238;
    L_0x022f:
        r3 = "is_vip";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setIs_vip(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0238:
        r3 = "total_cmt_like_count";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0249;
    L_0x0240:
        r3 = "total_cmt_like_count";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setCmtLikeCount(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0249:
        r3 = "video";
        r3 = r11.getJSONObject(r3);	 Catch:{ Exception -> 0x03b9, JSONException -> 0x002e }
    L_0x024f:
        if (r3 == 0) goto L_0x0310;
    L_0x0251:
        r4 = "download";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x027a;
    L_0x0259:
        r4 = "download";
        r4 = r3.getJSONArray(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r5.append(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setVideoDownLoadUrl(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x027a:
        r4 = "thumbnail";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x02a3;
    L_0x0282:
        r4 = "thumbnail";
        r4 = r3.getJSONArray(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r5.append(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setVideoThumbnail(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x02a3:
        r4 = "video";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x02cc;
    L_0x02ab:
        r4 = "video";
        r4 = r3.getJSONArray(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r5.append(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setVideoPlayUrl(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x02cc:
        r4 = "width";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x02dd;
    L_0x02d4:
        r4 = "width";
        r4 = r3.optInt(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setImageWidth(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x02dd:
        r4 = "height";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x02ee;
    L_0x02e5:
        r4 = "height";
        r4 = r3.optInt(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setImageHeight(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x02ee:
        r4 = "duration";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x02ff;
    L_0x02f6:
        r4 = "duration";
        r4 = r3.optString(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setmVideoTime(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x02ff:
        r4 = "avmixed";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x0310;
    L_0x0307:
        r4 = "avmixed";
        r3 = r3.optBoolean(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setPub(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0310:
        r3 = "audio";
        r3 = r11.getJSONObject(r3);	 Catch:{ Exception -> 0x03bd, JSONException -> 0x002e }
    L_0x0316:
        if (r3 == 0) goto L_0x0352;
    L_0x0318:
        r4 = "audio";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x0341;
    L_0x0320:
        r4 = "audio";
        r4 = r3.getJSONArray(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = 0;
        r4 = r4.get(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r5.append(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setAudioUrl(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0341:
        r4 = "duration";
        r4 = r3.has(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r4 == 0) goto L_0x0352;
    L_0x0349:
        r4 = "duration";
        r3 = r3.optInt(r4);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setAudioDuration(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0352:
        r3 = "image";
        r3 = r11.getJSONObject(r3);	 Catch:{ Exception -> 0x03c1, JSONException -> 0x002e }
        r4 = r3;
    L_0x0359:
        if (r4 == 0) goto L_0x043b;
    L_0x035b:
        r3 = "download";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x03c7;
    L_0x0363:
        r3 = "download";
        r5 = r4.getJSONArray(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 == 0) goto L_0x03c7;
    L_0x036b:
        r3 = r5.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 <= 0) goto L_0x03c7;
    L_0x0371:
        r3 = r5.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r2;
    L_0x0378:
        r7 = r5.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 >= r7) goto L_0x03c4;
    L_0x037e:
        r7 = r5.optString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6[r3] = r7;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r3 + 1;
        goto L_0x0378;
    L_0x0387:
        r3 = move-exception;
        r3 = r1;
        goto L_0x00fe;
    L_0x038b:
        r3 = move-exception;
        r3 = r1;
        goto L_0x0147;
    L_0x038f:
        r3 = "profile_image";
        r3 = r4.getString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setProfile(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        goto L_0x0197;
    L_0x039a:
        r1 = move-exception;
    L_0x039b:
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "parseCommends ";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x0005;
    L_0x03b9:
        r3 = move-exception;
        r3 = r1;
        goto L_0x024f;
    L_0x03bd:
        r3 = move-exception;
        r3 = r1;
        goto L_0x0316;
    L_0x03c1:
        r3 = move-exception;
        r4 = r1;
        goto L_0x0359;
    L_0x03c4:
        r0.setImageDownloadUrls(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x03c7:
        r3 = "images";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x03f0;
    L_0x03cf:
        r3 = "images";
        r3 = r4.getJSONArray(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = 0;
        r3 = r3.get(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r5.append(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setImageShowUrl(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x03f0:
        r3 = "thumbnail";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0419;
    L_0x03f8:
        r3 = "thumbnail";
        r3 = r4.getJSONArray(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = 0;
        r3 = r3.get(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r5.append(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setImageThumbUrl(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0419:
        r3 = "width";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x042a;
    L_0x0421:
        r3 = "width";
        r3 = r4.optInt(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setImageWidth(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x042a:
        r3 = "height";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x043b;
    L_0x0432:
        r3 = "height";
        r3 = r4.optInt(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setImageHeight(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x043b:
        r3 = "gif";
        r3 = r11.getJSONObject(r3);	 Catch:{ Exception -> 0x0470, JSONException -> 0x002e }
        r4 = r3;
    L_0x0442:
        if (r4 == 0) goto L_0x0502;
    L_0x0444:
        r3 = "download";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x0476;
    L_0x044c:
        r3 = "download";
        r5 = r4.getJSONArray(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 == 0) goto L_0x0476;
    L_0x0454:
        r3 = r5.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 <= 0) goto L_0x0476;
    L_0x045a:
        r3 = r5.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r2;
    L_0x0461:
        r7 = r5.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 >= r7) goto L_0x0473;
    L_0x0467:
        r7 = r5.optString(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6[r3] = r7;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = r3 + 1;
        goto L_0x0461;
    L_0x0470:
        r3 = move-exception;
        r4 = r1;
        goto L_0x0442;
    L_0x0473:
        r0.setGifDownLoadUrls(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0476:
        r3 = "images";
        r3 = r4.has(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x04b7;
    L_0x047e:
        r3 = "images";
        r3 = r4.getJSONArray(r3);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r3 == 0) goto L_0x04b7;
    L_0x0486:
        r5 = r3.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r5 <= 0) goto L_0x04b7;
    L_0x048c:
        r5 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0491:
        r6 = r3.length();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 >= r6) goto L_0x04b4;
    L_0x0497:
        r6 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r7 = "";
        r6 = r6.append(r7);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r7 = r3.get(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = r6.append(r7);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r6 = r6.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5.add(r6);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r2 = r2 + 1;
        goto L_0x0491;
    L_0x04b4:
        r0.setGifShowUrl(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x04b7:
        r2 = "thumbnail";
        r2 = r4.has(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 == 0) goto L_0x04e0;
    L_0x04bf:
        r2 = "thumbnail";
        r2 = r4.getJSONArray(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r3.<init>();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = "";
        r3 = r3.append(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r5 = 0;
        r2 = r2.get(r5);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r2 = r3.append(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setGifThumbUrl(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x04e0:
        r2 = "width";
        r2 = r4.has(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 == 0) goto L_0x04f1;
    L_0x04e8:
        r2 = "width";
        r2 = r4.optInt(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setGifWidth(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x04f1:
        r2 = "height";
        r2 = r4.has(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 == 0) goto L_0x0502;
    L_0x04f9:
        r2 = "height";
        r2 = r4.optInt(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setGifHeight(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0502:
        r2 = "vote";
        r2 = r11.has(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 == 0) goto L_0x0513;
    L_0x050a:
        r2 = "vote";
        r2 = r11.optString(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setVoteDataJson(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0513:
        r2 = "author_uid";
        r2 = r11.has(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 == 0) goto L_0x0524;
    L_0x051b:
        r2 = "author_uid";
        r2 = r11.optString(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setAuthorUid(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
    L_0x0524:
        r2 = "author_replay";
        r2 = r11.has(r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        if (r2 == 0) goto L_0x0005;
    L_0x052c:
        r2 = "author_replay";
        r1 = r11.getJSONArray(r2);	 Catch:{ Exception -> 0x054b, JSONException -> 0x002e }
    L_0x0532:
        if (r1 == 0) goto L_0x0005;
    L_0x0534:
        r2 = "";
        r1 = a(r1, r2);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        r0.setAuthor_replay(r1);	 Catch:{ JSONException -> 0x002e, Exception -> 0x039a }
        goto L_0x0005;
    L_0x053f:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x039b;
    L_0x0545:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x002f;
    L_0x054b:
        r2 = move-exception;
        goto L_0x0532;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.z.b(org.json.JSONObject):com.budejie.www.bean.CommentItem");
    }

    public static HashMap<String, String> c(String str) {
        HashMap<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(j.c)) {
                hashMap.put(j.c, jSONObject.getString(j.c));
            }
            if (jSONObject.has("result_msg")) {
                hashMap.put("result_msg", jSONObject.getString("result_msg"));
            }
            if (jSONObject.has("info")) {
                jSONObject = jSONObject.getJSONObject("info");
                if (jSONObject.has("id")) {
                    hashMap.put("id", jSONObject.getString("id"));
                }
                if (jSONObject.has(HistoryOpenHelper.COLUMN_USERNAME)) {
                    hashMap.put(HistoryOpenHelper.COLUMN_USERNAME, jSONObject.getString(HistoryOpenHelper.COLUMN_USERNAME));
                }
                if (jSONObject.has("sex")) {
                    hashMap.put("sex", jSONObject.getString("sex"));
                }
                if (jSONObject.has("profile_image")) {
                    hashMap.put(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, jSONObject.getString("profile_image"));
                }
                if (jSONObject.has("description")) {
                    hashMap.put("description", jSONObject.getString("description"));
                }
                if (jSONObject.has("grade")) {
                    hashMap.put("grade", jSONObject.getString("grade"));
                }
                if (jSONObject.has("email")) {
                    hashMap.put("email", jSONObject.getString("email"));
                }
                if (jSONObject.has("phone")) {
                    hashMap.put("phone", jSONObject.getString("phone"));
                }
                if (jSONObject.has("star_sign")) {
                    hashMap.put("astro", jSONObject.getString("star_sign"));
                }
                if (jSONObject.has("birthday")) {
                    hashMap.put("birthday", jSONObject.getString("birthday"));
                }
                if (jSONObject.has("qq")) {
                    hashMap.put("qq", jSONObject.getString("qq"));
                }
                if (jSONObject.has("degree")) {
                    hashMap.put("degree", jSONObject.getString("degree"));
                }
                if (jSONObject.has("introduction")) {
                    hashMap.put("introduction", jSONObject.getString("introduction"));
                }
                if (jSONObject.has("background_image")) {
                    hashMap.put("background_image", jSONObject.getString("background_image"));
                }
                if (jSONObject.has("follow_count")) {
                    hashMap.put("follow_count", jSONObject.getString("follow_count"));
                }
                if (jSONObject.has("fans_count")) {
                    hashMap.put("fans_count", jSONObject.getString("fans_count"));
                }
                if (jSONObject.has("weibo_uid")) {
                    hashMap.put("weibo_uid", jSONObject.getString("weibo_uid"));
                }
                if (jSONObject.has("weibo_token")) {
                    hashMap.put("weibo_token", jSONObject.getString("weibo_token"));
                }
                if (jSONObject.has("qq_uid")) {
                    hashMap.put("qq_uid", jSONObject.getString("qq_uid"));
                }
                if (jSONObject.has("qq_token")) {
                    hashMap.put("qq_token", jSONObject.getString("qq_token"));
                }
                if (jSONObject.has("qzone_uid")) {
                    hashMap.put("qzone_uid", jSONObject.getString("qzone_uid"));
                }
                if (jSONObject.has("qzone_token")) {
                    hashMap.put("qzone_token", jSONObject.getString("qzone_token"));
                }
                if (jSONObject.has("new_user")) {
                    hashMap.put("new_user", jSONObject.getString("new_user"));
                }
                if (jSONObject.has(HistoryOpenHelper.COLUMN_LEVEL)) {
                    hashMap.put(HistoryOpenHelper.COLUMN_LEVEL, jSONObject.getString(HistoryOpenHelper.COLUMN_LEVEL));
                }
                if (jSONObject.has("weixin_uid")) {
                    hashMap.put("weixin_uid", jSONObject.getString("weixin_uid"));
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseUser " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseUser " + e2.toString());
        }
        return hashMap;
    }

    public static HashMap<String, String> d(String str) {
        HashMap<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                hashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
            }
            if (jSONObject.has("msg")) {
                hashMap.put("msg", jSONObject.getString("msg"));
            }
            if (jSONObject.has("info")) {
                jSONObject = jSONObject.getJSONObject("info");
                if (jSONObject.has("id")) {
                    hashMap.put("id", jSONObject.getString("id"));
                }
                if (jSONObject.has(HistoryOpenHelper.COLUMN_USERNAME)) {
                    hashMap.put(HistoryOpenHelper.COLUMN_USERNAME, jSONObject.getString(HistoryOpenHelper.COLUMN_USERNAME));
                }
                if (jSONObject.has("sex")) {
                    hashMap.put("sex", jSONObject.getString("sex"));
                }
                if (jSONObject.has("profile_image")) {
                    hashMap.put(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, jSONObject.getString("profile_image"));
                }
                if (jSONObject.has("description")) {
                    hashMap.put("description", jSONObject.getString("description"));
                }
                if (jSONObject.has("grade")) {
                    hashMap.put("grade", jSONObject.getString("grade"));
                }
                if (jSONObject.has("email")) {
                    hashMap.put("email", jSONObject.getString("email"));
                }
                if (jSONObject.has("phone")) {
                    hashMap.put("phone", jSONObject.getString("phone"));
                }
                if (jSONObject.has("star_sign")) {
                    hashMap.put("astro", jSONObject.getString("star_sign"));
                }
                if (jSONObject.has("birthday")) {
                    hashMap.put("birthday", jSONObject.getString("birthday"));
                }
                if (jSONObject.has("introduction")) {
                    hashMap.put("introduction", jSONObject.getString("introduction"));
                }
                if (jSONObject.has("background_image")) {
                    hashMap.put("background_image", jSONObject.getString("background_image"));
                }
                if (jSONObject.has("follow_count")) {
                    hashMap.put("follow_count", jSONObject.getString("follow_count"));
                }
                if (jSONObject.has("fans_count")) {
                    hashMap.put("fans_count", jSONObject.getString("fans_count"));
                }
                if (jSONObject.has("weibo_uid")) {
                    hashMap.put("weibo_uid", jSONObject.getString("weibo_uid"));
                }
                if (jSONObject.has("weibo_token")) {
                    hashMap.put("weibo_token", jSONObject.getString("weibo_token"));
                }
                if (jSONObject.has("qq_uid")) {
                    hashMap.put("qq_uid", jSONObject.getString("qq_uid"));
                }
                if (jSONObject.has("qq_token")) {
                    hashMap.put("qq_token", jSONObject.getString("qq_token"));
                }
                if (jSONObject.has("qzone_uid")) {
                    hashMap.put("qzone_uid", jSONObject.getString("qzone_uid"));
                }
                if (jSONObject.has("qzone_token")) {
                    hashMap.put("qzone_token", jSONObject.getString("qzone_token"));
                }
                if (jSONObject.has("Is_black_user")) {
                    hashMap.put("Is_black_user", jSONObject.getString("Is_black_user"));
                }
                if (jSONObject.has("Is_black_device")) {
                    hashMap.put("Is_black_device", jSONObject.getString("Is_black_device"));
                }
                if (jSONObject.has("degree")) {
                    hashMap.put("degree", jSONObject.getString("degree"));
                }
                if (jSONObject.has(HistoryOpenHelper.COLUMN_LEVEL)) {
                    hashMap.put(HistoryOpenHelper.COLUMN_LEVEL, jSONObject.getString(HistoryOpenHelper.COLUMN_LEVEL));
                }
                if (jSONObject.has("weixin_uid")) {
                    hashMap.put("weixin_uid", jSONObject.getString("weixin_uid"));
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseUser " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseUser " + e2.toString());
        }
        return hashMap;
    }

    public static HashMap<String, String> a(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                if (jSONObject.has("openid")) {
                    hashMap.put("qzone_uid", jSONObject.getString("openid"));
                }
                if (jSONObject.has(Constants.PARAM_ACCESS_TOKEN)) {
                    hashMap.put("qzone_token", jSONObject.getString(Constants.PARAM_ACCESS_TOKEN));
                }
                if (jSONObject.has(Constants.PARAM_EXPIRES_IN)) {
                    hashMap.put(Constants.PARAM_EXPIRES_IN, jSONObject.getString(Constants.PARAM_EXPIRES_IN));
                }
            } catch (JSONException e) {
                Log.e(a, "parseQqToken " + e.toString());
            } catch (Exception e2) {
                Log.e(a, "parseQqToken " + e2.toString());
            }
        }
        return hashMap;
    }

    public static FansList e(String str) {
        FansList fansList = new FansList();
        ArrayList arrayList = new ArrayList();
        fansList.setData(arrayList);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                fansList.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
            }
            if (jSONObject.has("msg")) {
                fansList.setMsg(jSONObject.getString("msg"));
            }
            if (jSONObject.has("data")) {
                jSONObject = jSONObject.getJSONObject("data");
                if (jSONObject.has("info")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("info");
                    if (jSONObject2.has("count")) {
                        fansList.setCount(jSONObject2.getString("count"));
                    }
                    if (jSONObject2.has("has_data")) {
                        fansList.setHas_data(jSONObject2.getString("has_data").equals(com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE));
                    }
                    if (jSONObject2.has("follow_id")) {
                        fansList.setFollow_id(jSONObject2.getString("follow_id"));
                    }
                }
                if (jSONObject.has("list")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("list");
                    if (jSONArray != null) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                            Fans fans = new Fans();
                            if (jSONObject3.has("id")) {
                                fans.setId(jSONObject3.getString("id"));
                            }
                            if (jSONObject3.has(HistoryOpenHelper.COLUMN_USERNAME)) {
                                fans.setUsername(jSONObject3.getString(HistoryOpenHelper.COLUMN_USERNAME));
                            }
                            if (jSONObject3.has("sex")) {
                                fans.setSex(jSONObject3.getString("sex"));
                            }
                            if (jSONObject3.has("introduction")) {
                                fans.setIntroduction(jSONObject3.getString("introduction"));
                            }
                            if (jSONObject3.has("profile_image")) {
                                fans.setUserPic(jSONObject3.getString("profile_image"));
                            }
                            if (jSONObject3.has("relationship")) {
                                fans.setRelationship(jSONObject3.getString("relationship"));
                            }
                            if (jSONObject3.has("is_vip")) {
                                fans.is_vip = jSONObject3.getBoolean("is_vip");
                            }
                            arrayList.add(fans);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseFansData " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseFansData " + e2.toString());
        }
        return fansList;
    }

    public static FansList f(String str) {
        FansList fansList = new FansList();
        ArrayList arrayList = new ArrayList();
        fansList.setData(arrayList);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                fansList.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
            }
            if (jSONObject.has("msg")) {
                fansList.setMsg(jSONObject.getString("msg"));
            }
            if (jSONObject.has("data")) {
                jSONObject = jSONObject.getJSONObject("data");
                if (jSONObject.has("info")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("info");
                    if (jSONObject2.has("count")) {
                        fansList.setCount(jSONObject2.getString("count"));
                    }
                    if (jSONObject2.has("has_data")) {
                        fansList.setHas_data(jSONObject2.getString("has_data").equals(com.ali.auth.third.core.model.Constants.SERVICE_SCOPE_FLAG_VALUE));
                    }
                    if (jSONObject2.has("follow_id")) {
                        fansList.setFollow_id(jSONObject2.getString("follow_id"));
                    }
                }
                if (jSONObject.has("names")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("names");
                    if (jSONArray != null) {
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                            Fans fans = new Fans();
                            if (jSONObject3.has("id")) {
                                fans.setId(jSONObject3.getString("id"));
                            }
                            if (jSONObject3.has("name")) {
                                fans.setUsername(jSONObject3.getString("name"));
                            }
                            if (jSONObject3.has("sex")) {
                                fans.setSex(jSONObject3.getString("sex"));
                            }
                            if (jSONObject3.has("introduction")) {
                                fans.setIntroduction(jSONObject3.getString("introduction"));
                            }
                            if (jSONObject3.has(a.A)) {
                                fans.setUserPic(jSONObject3.getString(a.A));
                            }
                            if (jSONObject3.has("relationship")) {
                                fans.setRelationship(jSONObject3.getString("relationship"));
                            }
                            arrayList.add(fans);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseFansData " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseFansData " + e2.toString());
        }
        return fansList;
    }

    public static ArrayList<RecommendSubscription> g(String str) {
        int i = 0;
        ArrayList<RecommendSubscription> arrayList = new ArrayList();
        try {
            int i2;
            JSONObject jSONObject;
            RecommendSubscription recommendSubscription;
            JSONObject jSONObject2 = new JSONObject(str);
            JSONArray jSONArray = jSONObject2.getJSONArray("rec_tags");
            if (jSONArray != null) {
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    jSONObject = jSONArray.getJSONObject(i2);
                    recommendSubscription = new RecommendSubscription();
                    if (jSONObject.has("theme_id")) {
                        recommendSubscription.setTheme_id(jSONObject.getString("theme_id"));
                    }
                    if (jSONObject.has("theme_name")) {
                        recommendSubscription.setTheme_name(jSONObject.getString("theme_name"));
                    }
                    if (jSONObject.has("image_list")) {
                        recommendSubscription.setImage_list(jSONObject.getString("image_list"));
                    }
                    if (jSONObject.has("sub_number")) {
                        recommendSubscription.setSub_number(jSONObject.getString("sub_number"));
                    }
                    if (jSONObject.has("post_num")) {
                        recommendSubscription.setPost_num(jSONObject.getString("post_num"));
                    }
                    if (jSONObject.has("is_sub")) {
                        recommendSubscription.setIs_sub(jSONObject.getString("is_sub"));
                    }
                    if (jSONObject.has("is_default")) {
                        recommendSubscription.setIs_default(jSONObject.getString("is_default"));
                    }
                    recommendSubscription.setType("r");
                    arrayList.add(recommendSubscription);
                }
            }
            if (jSONObject2.has("def_tags")) {
                jSONArray = jSONObject2.getJSONArray("def_tags");
                if (jSONArray != null) {
                    for (i2 = 0; i2 < jSONArray.length(); i2++) {
                        jSONObject = jSONArray.getJSONObject(i2);
                        recommendSubscription = new RecommendSubscription();
                        if (jSONObject.has("theme_id")) {
                            recommendSubscription.setTheme_id(jSONObject.getString("theme_id"));
                        }
                        if (jSONObject.has("theme_name")) {
                            recommendSubscription.setTheme_name(jSONObject.getString("theme_name"));
                        }
                        if (jSONObject.has("image_list")) {
                            recommendSubscription.setImage_list(jSONObject.getString("image_list"));
                        }
                        if (jSONObject.has("sub_number")) {
                            recommendSubscription.setSub_number(jSONObject.getString("sub_number"));
                        }
                        if (jSONObject.has("post_num")) {
                            recommendSubscription.setPost_num(jSONObject.getString("post_num"));
                        }
                        if (jSONObject.has("is_sub")) {
                            recommendSubscription.setIs_sub(jSONObject.getString("is_sub"));
                        }
                        if (jSONObject.has("is_default")) {
                            recommendSubscription.setIs_default(jSONObject.getString("is_default"));
                        }
                        recommendSubscription.setType("d");
                        arrayList.add(recommendSubscription);
                    }
                }
            }
            JSONArray jSONArray2 = jSONObject2.getJSONArray("sub_tags");
            if (jSONArray2 != null) {
                while (i < jSONArray2.length()) {
                    jSONObject2 = jSONArray2.getJSONObject(i);
                    RecommendSubscription recommendSubscription2 = new RecommendSubscription();
                    if (jSONObject2.has("theme_id")) {
                        recommendSubscription2.setTheme_id(jSONObject2.getString("theme_id"));
                    }
                    if (jSONObject2.has("theme_name")) {
                        recommendSubscription2.setTheme_name(jSONObject2.getString("theme_name"));
                    }
                    if (jSONObject2.has("image_list")) {
                        recommendSubscription2.setImage_list(jSONObject2.getString("image_list"));
                    }
                    if (jSONObject2.has("sub_number")) {
                        recommendSubscription2.setSub_number(jSONObject2.getString("sub_number"));
                    }
                    if (jSONObject2.has("post_num")) {
                        recommendSubscription2.setPost_num(jSONObject2.getString("post_num"));
                    }
                    if (jSONObject2.has("is_sub")) {
                        recommendSubscription2.setIs_sub(jSONObject2.getString("is_sub"));
                    }
                    if (jSONObject2.has("is_default")) {
                        recommendSubscription2.setIs_default(jSONObject2.getString("is_default"));
                    }
                    recommendSubscription2.setType("s");
                    arrayList.add(recommendSubscription2);
                    i++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList<RecommendSubscription> h(String str) {
        ArrayList<RecommendSubscription> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray == null) {
                return null;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                RecommendSubscription recommendSubscription = new RecommendSubscription();
                if (jSONObject.has("theme_id")) {
                    recommendSubscription.setTheme_id(jSONObject.getString("theme_id"));
                }
                if (jSONObject.has("theme_name")) {
                    recommendSubscription.setTheme_name(jSONObject.getString("theme_name"));
                }
                if (jSONObject.has("image_list")) {
                    recommendSubscription.setImage_list(jSONObject.getString("image_list"));
                }
                if (jSONObject.has("sub_number")) {
                    recommendSubscription.setSub_number(jSONObject.getString("sub_number"));
                }
                if (jSONObject.has("post_num")) {
                    recommendSubscription.setPost_num(jSONObject.getString("post_num"));
                }
                if (jSONObject.has("is_sub")) {
                    recommendSubscription.setIs_sub(jSONObject.getString("is_sub"));
                }
                if (jSONObject.has("is_default")) {
                    recommendSubscription.setIs_default(jSONObject.getString("is_default"));
                }
                if (jSONObject.has("post_number")) {
                    recommendSubscription.setPost_number(jSONObject.getString("post_number"));
                }
                recommendSubscription.setType("r");
                arrayList.add(recommendSubscription);
            }
            return arrayList;
        } catch (JSONException e) {
            return arrayList;
        } catch (Exception e2) {
            return arrayList;
        }
    }

    public static ArrayList<RecommendUser> i(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray == null) {
                return null;
            }
            ArrayList<RecommendUser> arrayList = new ArrayList();
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    RecommendUser recommendUser = new RecommendUser();
                    JSONObject jSONObject = jSONArray.getJSONObject(i).getJSONObject("userinfo");
                    recommendUser.setId(jSONObject.getString("id"));
                    recommendUser.setUserid(jSONObject.getString("userid"));
                    recommendUser.setProfile_image(jSONObject.getString("profile_image"));
                    recommendUser.setUsername(jSONObject.getString(HistoryOpenHelper.COLUMN_USERNAME));
                    recommendUser.setIntroduction(jSONObject.getString("introduction"));
                    JSONArray jSONArray2 = jSONObject.getJSONArray("topics");
                    List arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                        Topics topics = new Topics();
                        topics.setPid(jSONObject2.getString("pid"));
                        topics.setBody(jSONObject2.getString(a.z));
                        topics.setImage(jSONObject2.getString(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY));
                        topics.setType(jSONObject2.getString("type"));
                        arrayList2.add(topics);
                    }
                    recommendUser.setTopics(arrayList2);
                    arrayList.add(recommendUser);
                    i++;
                } catch (Exception e) {
                    return arrayList;
                }
            }
            return arrayList;
        } catch (Exception e2) {
            return null;
        }
    }

    public static List<ReportItem> j(String str) {
        List<ReportItem> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                ReportItem reportItem = new ReportItem();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("value")) {
                    reportItem.setReportID(jSONObject.getString("value"));
                }
                if (jSONObject.has("label")) {
                    reportItem.setReportContent(jSONObject.getString("label"));
                }
                arrayList.add(reportItem);
            }
        } catch (JSONException e) {
            Log.e(a, "parseRepotData " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseRepotData " + e2.toString());
        }
        return arrayList;
    }

    public static HashMap<String, String> k(String str) {
        HashMap<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(j.c)) {
                hashMap.put(j.c, jSONObject.getString(j.c));
            }
            if (jSONObject.has("result_desc")) {
                hashMap.put("result_desc", jSONObject.getString("result_desc"));
            }
            if (jSONObject.has("head_info")) {
                jSONObject = jSONObject.getJSONObject("head_info");
                if (jSONObject.has("uri")) {
                    hashMap.put("url", jSONObject.getString("uri"));
                }
            }
        } catch (JSONException e) {
            Log.e(a, "parseProfileInfo " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseProfileInfo " + e2.toString());
        }
        return hashMap;
    }

    public static HashMap<String, String> l(String str) {
        HashMap<String, String> hashMap = new HashMap();
        if (!"[]".equals(hashMap)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(j.c)) {
                    String string = jSONObject.getString(j.c);
                    String string2 = jSONObject.getString("result_msg");
                    hashMap.put(j.c, string);
                    hashMap.put("msg", string2);
                }
            } catch (JSONException e) {
                Log.e(a, "checkBindWeibo " + e.toString());
            } catch (Exception e2) {
                Log.e(a, "checkBindWeibo " + e2.toString());
            }
        }
        return hashMap;
    }

    public static int a(String str, String str2) {
        int i = 0;
        try {
            if (!(TextUtils.isEmpty(str2) || "".equals(str2.trim()))) {
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.has(str)) {
                    i = jSONObject.getJSONObject(str).getInt("size");
                }
            }
        } catch (JSONException e) {
            Log.e(a, "getListItemContentTextSize " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "getListItemContentTextSize " + e2.toString());
        }
        return i;
    }

    public static MyCollectItem m(String str) {
        MyCollectItem myCollectItem = new MyCollectItem();
        if (!"[]".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(GameAppOperation.QQFAV_DATALINE_VERSION)) {
                    myCollectItem.setVersion(jSONObject.getString(GameAppOperation.QQFAV_DATALINE_VERSION));
                }
                if (jSONObject.has("add_list")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("add_list");
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        ListItemObject listItemObject = new ListItemObject();
                        listItemObject.setWid(jSONObject2.getString("id"));
                        listItemObject.setAddtime(jSONObject2.getString(AppLinkConstants.TIME));
                        arrayList.add(listItemObject);
                    }
                    myCollectItem.setAddList(arrayList);
                }
            } catch (Exception e) {
            }
        }
        return myCollectItem;
    }

    public static SyncCollectItem n(String str) {
        SyncCollectItem syncCollectItem = new SyncCollectItem();
        if (!"[]".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(j.c)) {
                    syncCollectItem.setResult(jSONObject.getString(j.c));
                }
                if (jSONObject.has(HistoryOpenHelper.COLUMN_UID)) {
                    syncCollectItem.setUid(jSONObject.getString(HistoryOpenHelper.COLUMN_UID));
                }
                if (jSONObject.has("success_ids")) {
                    ArrayList arrayList = new ArrayList();
                    JSONArray jSONArray = jSONObject.getJSONArray("success_ids");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                    syncCollectItem.setSuccessIds(arrayList);
                }
                if (jSONObject.has("reason")) {
                    syncCollectItem.setReason(jSONObject.getString("reason"));
                }
            } catch (JSONException e) {
                Log.e(a, "parseSynchronousCollect " + e.toString());
            } catch (Exception e2) {
                Log.e(a, "parseSynchronousCollect " + e2.toString());
            }
        }
        return syncCollectItem;
    }

    public static MyNewsData a(Context context, String str) {
        JSONObject jSONObject;
        ListItemObject a;
        MyNewsData myNewsData = new MyNewsData();
        if ("[]".equals(str)) {
            return myNewsData;
        }
        JSONArray jSONArray;
        ArrayList arrayList;
        int i;
        MyNewsItem myNewsItem;
        String string;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            try {
                JSONArray jSONArray2;
                if (jSONObject2.has("info")) {
                    myNewsData.info = (ListInfo) a(jSONObject2.getString("info"), ListInfo.class);
                }
                if (jSONObject2.has("list")) {
                    jSONArray2 = jSONObject2.getJSONArray("list");
                } else {
                    jSONArray2 = null;
                }
                JSONArray jSONArray3 = jSONArray2;
                jSONObject = jSONObject2;
                jSONArray = jSONArray3;
            } catch (JSONException e) {
                jSONObject = jSONObject2;
                jSONArray = null;
                if (jSONObject != null) {
                    return myNewsData;
                }
                arrayList = new ArrayList();
                while (i < jSONArray.length()) {
                    try {
                        myNewsItem = new MyNewsItem();
                        jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject.has("pinfo")) {
                            a = com.budejie.www.j.a.a(jSONObject.getJSONObject("pinfo"));
                            if (a != null) {
                                myNewsItem.setListItemObject(a);
                            }
                        }
                        if (jSONObject.has("ctime")) {
                            myNewsItem.ctime = jSONObject.getString("ctime");
                        }
                        if (!jSONObject.has("type")) {
                            string = jSONObject.getString("type");
                            myNewsItem.setType(string);
                            if (!"post".equals(string)) {
                            }
                            myNewsItem.setCmtMyTieziItem((CmtMyTieziItem) a(jSONArray.getString(i), CmtMyTieziItem.class));
                            arrayList.add(myNewsItem);
                        }
                    } catch (JSONException e2) {
                        Log.e(a, "parseMyNewsData " + e2.toString());
                    } catch (Exception e3) {
                        Log.e(a, "parseMyNewsData " + e3.toString());
                    }
                    myNewsData.setList(arrayList);
                }
                return myNewsData;
            }
        } catch (JSONException e4) {
            jSONObject = null;
            jSONArray = null;
            if (jSONObject != null) {
                return myNewsData;
            }
            arrayList = new ArrayList();
            while (i < jSONArray.length()) {
                myNewsItem = new MyNewsItem();
                jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("pinfo")) {
                    a = com.budejie.www.j.a.a(jSONObject.getJSONObject("pinfo"));
                    if (a != null) {
                        myNewsItem.setListItemObject(a);
                    }
                }
                if (jSONObject.has("ctime")) {
                    myNewsItem.ctime = jSONObject.getString("ctime");
                }
                if (!jSONObject.has("type")) {
                    string = jSONObject.getString("type");
                    myNewsItem.setType(string);
                    if ("post".equals(string)) {
                    }
                    myNewsItem.setCmtMyTieziItem((CmtMyTieziItem) a(jSONArray.getString(i), CmtMyTieziItem.class));
                    arrayList.add(myNewsItem);
                }
                myNewsData.setList(arrayList);
            }
            return myNewsData;
        }
        if (jSONObject != null) {
            return myNewsData;
        }
        arrayList = new ArrayList();
        for (i = 0; i < jSONArray.length(); i++) {
            myNewsItem = new MyNewsItem();
            jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.has("pinfo")) {
                a = com.budejie.www.j.a.a(jSONObject.getJSONObject("pinfo"));
                if (a != null) {
                    myNewsItem.setListItemObject(a);
                }
            }
            if (jSONObject.has("ctime")) {
                myNewsItem.ctime = jSONObject.getString("ctime");
            }
            if (!jSONObject.has("type")) {
                string = jSONObject.getString("type");
                myNewsItem.setType(string);
                if ("post".equals(string) || "voice_post".equals(string) || "video_post".equals(string)) {
                    myNewsItem.setCmtMyTieziItem((CmtMyTieziItem) a(jSONArray.getString(i), CmtMyTieziItem.class));
                    arrayList.add(myNewsItem);
                } else if ("cding".equals(string) || "voice_cding".equals(string) || "video_cding".equals(string)) {
                    myNewsItem.setDingOrCaiCommentNewsItem((DingOrCaiCommentNewsItem) a(jSONArray.getString(i), DingOrCaiCommentNewsItem.class));
                    arrayList.add(myNewsItem);
                } else if ("ccai".equals(string)) {
                    DingOrCaiCommentNewsItem dingOrCaiCommentNewsItem = (DingOrCaiCommentNewsItem) a(jSONArray.getString(i), DingOrCaiCommentNewsItem.class);
                    dingOrCaiCommentNewsItem.isCai = true;
                    myNewsItem.setDingOrCaiCommentNewsItem(dingOrCaiCommentNewsItem);
                    arrayList.add(myNewsItem);
                } else if ("replay".equals(string) || "voice_replay".equals(string) || "video_reply".equals(string)) {
                    myNewsItem.setReplyNewsItem((ReplyNewsItem) a(jSONArray.getString(i), ReplyNewsItem.class));
                    arrayList.add(myNewsItem);
                } else if ("hot_comment".equals(string)) {
                    myNewsItem.setHotCommentItem((HotComment) a(jSONArray.getString(i), HotComment.class));
                    arrayList.add(myNewsItem);
                } else if ("ugc".equals(string) || "voice_ugc".equals(string) || "video_ugc".equals(string)) {
                    r6 = new SystemNewsItem();
                    if (jSONObject.has("ctime")) {
                        r6.setTime(jSONObject.getString("ctime"));
                    }
                    if (jSONObject.has("title")) {
                        r6.setTitle(jSONObject.getString("title"));
                    }
                    if (jSONObject.has("link")) {
                        r6.setLink(jSONObject.getString("link"));
                    }
                    if (jSONObject.has(a.z)) {
                        r6.setBody(jSONObject.getString(a.z));
                    }
                    if (jSONObject.has(b.c)) {
                        r6.setTid(jSONObject.getString(b.c));
                    }
                    if (jSONObject.has("id")) {
                        r6.setId(jSONObject.getString("id"));
                    }
                    if (jSONObject.has("reserve")) {
                        r6.setReserve(jSONObject.getString("reserve"));
                    }
                    if (jSONObject.has("download")) {
                        r6.setDownload(jSONObject.getString("download"));
                    }
                    myNewsItem.setSystemNewsItem(r6);
                    arrayList.add(myNewsItem);
                } else if (c.a.equals(string) || "forum_jing_topic".equals(string) || "forum_top_topic".equals(string)) {
                    r6 = new SystemNewsItem();
                    if (jSONObject.has("title")) {
                        r6.setTitle(jSONObject.getString("title"));
                    }
                    if (jSONObject.has(a.z)) {
                        r6.setBody(jSONObject.getString(a.z));
                    }
                    if (jSONObject.has("link")) {
                        r6.setLink(jSONObject.getString("link"));
                    }
                    if (jSONObject.has("link_type")) {
                        r6.setLinkType(jSONObject.getString("link_type"));
                    }
                    if (jSONObject.has("ctime")) {
                        r6.setTime(jSONObject.getString("ctime"));
                    }
                    if (jSONObject.has("id")) {
                        r6.setId(jSONObject.getString("id"));
                    }
                    if (jSONObject.has("reserve")) {
                        r6.setReserve(jSONObject.getString("reserve"));
                    }
                    if (jSONObject.has("download")) {
                        r6.setDownload(jSONObject.getString("download"));
                    }
                    if (jSONObject.has("tag_id")) {
                        r6.setTagId(jSONObject.getString("tag_id"));
                    }
                    if (jSONObject.has("pid")) {
                        r6.setPostId(jSONObject.getString("pid"));
                    }
                    myNewsItem.setSystemNewsItem(r6);
                    arrayList.add(myNewsItem);
                } else if ("mentioned".equals(string)) {
                    MentionedItem mentionedItem = new MentionedItem();
                    if (jSONObject.has("title")) {
                        mentionedItem.setTitle(jSONObject.getString("title"));
                    }
                    if (jSONObject.has("ctime")) {
                        mentionedItem.setTime(jSONObject.getString("ctime"));
                    }
                    if (jSONObject.has("id")) {
                        mentionedItem.setId(jSONObject.getString("id"));
                    }
                    if (jSONObject.has("reserve")) {
                        mentionedItem.setReserve(jSONObject.getString("reserve"));
                    }
                    if (jSONObject.has("download")) {
                        mentionedItem.setDownload(jSONObject.getString("download"));
                    }
                    myNewsItem.setMentionedItem(mentionedItem);
                    arrayList.add(myNewsItem);
                } else if ("url_topic".equals(string)) {
                    r6 = new SystemNewsItem();
                    if (jSONObject.has("title")) {
                        r6.setTitle(jSONObject.getString("title"));
                    }
                    if (jSONObject.has(a.z)) {
                        r6.setBody(jSONObject.getString(a.z));
                    }
                    if (jSONObject.has("link")) {
                        r6.setLink(jSONObject.getString("link"));
                    }
                    if (jSONObject.has("link_type")) {
                        r6.setLinkType(jSONObject.getString("link_type"));
                    }
                    if (jSONObject.has("ctime")) {
                        r6.setTime(jSONObject.getString("ctime"));
                    }
                    if (jSONObject.has("id")) {
                        r6.setId(jSONObject.getString("id"));
                    }
                    if (jSONObject.has("reserve")) {
                        r6.setReserve(jSONObject.getString("reserve"));
                    }
                    if (jSONObject.has("download")) {
                        r6.setDownload(jSONObject.getString("download"));
                    }
                    myNewsItem.setSystemNewsItem(r6);
                    arrayList.add(myNewsItem);
                } else {
                    r6 = new SystemNewsItem();
                    if (jSONObject.has(a.A)) {
                        r6.setHeader(jSONObject.getString(a.A));
                    }
                    if (jSONObject.has("ctime")) {
                        r6.setTime(jSONObject.getString("ctime"));
                    }
                    if (jSONObject.has("title")) {
                        r6.setTitle(jSONObject.getString("title"));
                    }
                    if (jSONObject.has("reserve")) {
                        r6.setReserve(jSONObject.getString("reserve"));
                    }
                    if (jSONObject.has("download")) {
                        r6.setDownload(jSONObject.getString("download"));
                    }
                    myNewsItem.setSystemNewsItem(r6);
                    myNewsItem.setType("unknow");
                    arrayList.add(myNewsItem);
                }
            }
            myNewsData.setList(arrayList);
        }
        return myNewsData;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.budejie.www.bean.DingMeData o(java.lang.String r7) {
        /*
        r0 = new com.budejie.www.bean.DingMeData;
        r0.<init>();
        r1 = "[]";
        r1 = r1.equals(r7);
        if (r1 == 0) goto L_0x000e;
    L_0x000d:
        return r0;
    L_0x000e:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r2.<init>(r7);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r1 = "info";
        r1 = r2.has(r1);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r1 == 0) goto L_0x0039;
    L_0x001b:
        r3 = new com.budejie.www.bean.ListInfo;	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r3.<init>();	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r1 = "info";
        r1 = r2.getJSONObject(r1);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r4 = "np";
        r4 = r1.has(r4);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r4 == 0) goto L_0x0036;
    L_0x002e:
        r4 = "np";
        r4 = r1.getLong(r4);	 Catch:{ JSONException -> 0x010a, Exception -> 0x0138 }
        r3.np = r4;	 Catch:{ JSONException -> 0x010a, Exception -> 0x0138 }
    L_0x0036:
        r0.setInfo(r3);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x0039:
        r1 = "data";
        r1 = r2.has(r1);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r1 == 0) goto L_0x000d;
    L_0x0041:
        r1 = "data";
        r2 = r2.getJSONArray(r1);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r3 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r3.<init>();	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r1 = 0;
    L_0x004d:
        r4 = r2.length();	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r1 >= r4) goto L_0x0133;
    L_0x0053:
        r4 = r2.getJSONObject(r1);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5 = new com.budejie.www.bean.DingNewsItem;	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.<init>();	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r6 = "comment";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x006d;
    L_0x0064:
        r6 = "comment";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setBody(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x006d:
        r6 = "ctime";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x007e;
    L_0x0075:
        r6 = "ctime";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setTime(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x007e:
        r6 = "suffix";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x008f;
    L_0x0086:
        r6 = "suffix";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setTitle(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x008f:
        r6 = "voiceuri";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x00a0;
    L_0x0097:
        r6 = "voiceuri";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setVoiceuri(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x00a0:
        r6 = "voicetime";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x00b1;
    L_0x00a8:
        r6 = "voicetime";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setVoicetime(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x00b1:
        r6 = "pid";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x00c2;
    L_0x00b9:
        r6 = "pid";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setWid(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x00c2:
        r6 = "user";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x0103;
    L_0x00ca:
        r6 = "user";
        r4 = r4.getJSONObject(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r6 = "name";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x00e1;
    L_0x00d8:
        r6 = "name";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setName(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x00e1:
        r6 = "header";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x00f2;
    L_0x00e9:
        r6 = "header";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setHeader(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x00f2:
        r6 = "uid";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        if (r6 == 0) goto L_0x0103;
    L_0x00fa:
        r6 = "uid";
        r4 = r4.getString(r6);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r5.setUid(r4);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
    L_0x0103:
        r3.add(r5);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r1 = r1 + 1;
        goto L_0x004d;
    L_0x010a:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        r4 = 0;
        r3.np = r4;	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        goto L_0x0036;
    L_0x0114:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "parseDingMeData ";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x000d;
    L_0x0133:
        r0.setList(r3);	 Catch:{ JSONException -> 0x0114, Exception -> 0x0138 }
        goto L_0x000d;
    L_0x0138:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "parseDingMeData ";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.z.o(java.lang.String):com.budejie.www.bean.DingMeData");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.budejie.www.bean.DingMeData p(java.lang.String r7) {
        /*
        r0 = new com.budejie.www.bean.DingMeData;
        r0.<init>();
        r1 = "[]";
        r1 = r1.equals(r7);
        if (r1 == 0) goto L_0x000e;
    L_0x000d:
        return r0;
    L_0x000e:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r2.<init>(r7);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r1 = "info";
        r1 = r2.has(r1);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r1 == 0) goto L_0x0039;
    L_0x001b:
        r3 = new com.budejie.www.bean.ListInfo;	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r3.<init>();	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r1 = "info";
        r1 = r2.getJSONObject(r1);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r4 = "np";
        r4 = r1.has(r4);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r4 == 0) goto L_0x0036;
    L_0x002e:
        r4 = "np";
        r4 = r1.getLong(r4);	 Catch:{ JSONException -> 0x00ab, Exception -> 0x00d8 }
        r3.np = r4;	 Catch:{ JSONException -> 0x00ab, Exception -> 0x00d8 }
    L_0x0036:
        r0.setInfo(r3);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
    L_0x0039:
        r1 = "list";
        r1 = r2.has(r1);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r1 == 0) goto L_0x000d;
    L_0x0041:
        r1 = "list";
        r2 = r2.getJSONArray(r1);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r3 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r3.<init>();	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r1 = 0;
    L_0x004d:
        r4 = r2.length();	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r1 >= r4) goto L_0x00d3;
    L_0x0053:
        r4 = r2.getJSONObject(r1);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r5 = new com.budejie.www.bean.DingNewsItem;	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r5.<init>();	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r6 = "username";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r6 == 0) goto L_0x006d;
    L_0x0064:
        r6 = "username";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r5.setName(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
    L_0x006d:
        r6 = "profile_image";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r6 == 0) goto L_0x007e;
    L_0x0075:
        r6 = "profile_image";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r5.setHeader(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
    L_0x007e:
        r6 = "userid";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r6 == 0) goto L_0x008f;
    L_0x0086:
        r6 = "userid";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r5.setUid(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
    L_0x008f:
        r6 = "rate_time";
        r6 = r4.has(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        if (r6 == 0) goto L_0x00a0;
    L_0x0097:
        r6 = "rate_time";
        r4 = r4.getString(r6);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r5.setTime(r4);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
    L_0x00a0:
        r4 = "";
        r5.setTitle(r4);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r3.add(r5);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r1 = r1 + 1;
        goto L_0x004d;
    L_0x00ab:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        r4 = 0;
        r3.np = r4;	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        goto L_0x0036;
    L_0x00b4:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "parseVoteListData ";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x000d;
    L_0x00d3:
        r0.setList(r3);	 Catch:{ JSONException -> 0x00b4, Exception -> 0x00d8 }
        goto L_0x000d;
    L_0x00d8:
        r1 = move-exception;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "parseVoteListData ";
        r3 = r3.append(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.util.z.p(java.lang.String):com.budejie.www.bean.DingMeData");
    }

    public static Map<String, String> q(String str) {
        Map<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(j.c)) {
                hashMap.put(j.c, jSONObject.getString(j.c));
            }
            if (jSONObject.has("result_desc")) {
                hashMap.put("result_desc", jSONObject.getString("result_desc"));
            }
        } catch (JSONException e) {
            Log.e(a, "parseDeleteTougaoData " + e.toString());
        } catch (Exception e2) {
            Log.e(a, "parseDeleteTougaoData " + e2.toString());
        }
        return hashMap;
    }

    public static Map<String, Object> r(String str) {
        Map<String, Object> hashMap = new HashMap();
        if ("".equals(str) || "{}".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return hashMap;
            }
            jSONObject = jSONObject.getJSONObject("data");
            if (jSONObject.has("notice")) {
                JSONObject jSONObject2;
                JSONObject jSONObject3 = jSONObject.getJSONObject("notice");
                if (jSONObject3.has("msg_center")) {
                    hashMap.put("msgCenter", Integer.valueOf(jSONObject3.getInt("msg_center")));
                }
                if (jSONObject3.has("friend_info")) {
                    jSONObject2 = jSONObject3.getJSONObject("friend_info");
                    if (jSONObject2.has("num")) {
                        hashMap.put("friendInfoNum", Integer.valueOf(jSONObject2.getInt("num")));
                    }
                    if (jSONObject2.has(a.A)) {
                        hashMap.put("friendInfoHeader", jSONObject2.getString(a.A));
                    }
                }
                if (jSONObject3.has("fans_add")) {
                    hashMap.put("fansAdd", Integer.valueOf(jSONObject3.getInt("fans_add")));
                }
                if (jSONObject3.has("friend_recommend")) {
                    jSONObject2 = jSONObject3.getJSONObject("friend_recommend");
                    if (jSONObject2.has("sina")) {
                        hashMap.put("friendRecommendSina", Integer.valueOf(jSONObject2.getInt("sina")));
                    }
                    if (jSONObject2.has("sina")) {
                        hashMap.put("friendRecommendQQ", Integer.valueOf(jSONObject2.getInt("qq")));
                    }
                }
                if (jSONObject3.has("new_redpack")) {
                    hashMap.put("new_redpack", Integer.valueOf(jSONObject3.getInt("new_redpack")));
                }
            }
            if (!jSONObject.has("list")) {
                return hashMap;
            }
            jSONObject = jSONObject.getJSONObject("list");
            if (jSONObject.has(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                hashMap.put("imgNewCount", Integer.valueOf(jSONObject.getInt(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)));
            }
            if (jSONObject.has("29")) {
                hashMap.put("dzNewCount", Integer.valueOf(jSONObject.getInt("29")));
            }
            if (jSONObject.has("31")) {
                hashMap.put("soundNewCount", Integer.valueOf(jSONObject.getInt("31")));
            }
            if (!jSONObject.has("41")) {
                return hashMap;
            }
            hashMap.put("videoNewCount", Integer.valueOf(jSONObject.getInt("41")));
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return hashMap;
        }
    }

    public static ResultBean s(String str) {
        ResultBean resultBean = new ResultBean();
        if ("".equals(str) || "{}".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                resultBean.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
            }
            if (!jSONObject.has("msg")) {
                return resultBean;
            }
            resultBean.setMsg(jSONObject.getString("msg"));
            return resultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(a, "parseInformUser " + e.toString());
            return resultBean;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(a, "parseInformUser " + e2.toString());
            return resultBean;
        }
    }

    public static ResultBean t(String str) {
        ResultBean resultBean = new ResultBean();
        if ("".equals(str) || "{}".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                resultBean.setCode(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
            }
            if (jSONObject.has("msg")) {
                resultBean.setMsg(jSONObject.getString("msg"));
            }
            if (!jSONObject.has("data")) {
                return resultBean;
            }
            jSONObject = jSONObject.getJSONObject("data");
            ImageBean imageBean = new ImageBean();
            if (jSONObject.has("uri")) {
                imageBean.setUrl(jSONObject.getString("uri"));
            }
            if (jSONObject.has("id")) {
                imageBean.setUid(jSONObject.getString("id"));
            }
            resultBean.setImageBean(imageBean);
            return resultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(a, "parseInformUser " + e.toString());
            return resultBean;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(a, "parseInformUser " + e2.toString());
            return resultBean;
        }
    }

    public static Map<String, String> u(String str) {
        Map<String, String> hashMap = new HashMap();
        if ("".equals(str) || "{}".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return hashMap;
            }
            JSONObject jSONObject2 = new JSONObject(jSONObject.getString("data"));
            if (jSONObject2.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                hashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, jSONObject2.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY));
            }
            if (!jSONObject2.has("info")) {
                return hashMap;
            }
            hashMap.put(SocialConstants.PARAM_APP_DESC, jSONObject2.getString("info"));
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(a, "parseInformUser " + e.toString());
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(a, "parseInformUser " + e2.toString());
            return hashMap;
        }
    }

    public static HeadPortraitData v(String str) {
        HeadPortraitData headPortraitData = new HeadPortraitData();
        if ("[]".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("info")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("info");
                if (jSONObject2.has("count")) {
                    headPortraitData.setCount(jSONObject2.getInt("count"));
                    if (headPortraitData.getCount() <= 0) {
                        return headPortraitData;
                    }
                }
                if (jSONObject2.has("pid")) {
                    headPortraitData.setPid(jSONObject2.getString("pid"));
                }
            }
            if (!jSONObject.has("list")) {
                return headPortraitData;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("list");
            List arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                HeadPortraitItem headPortraitItem = new HeadPortraitItem();
                if (jSONObject3.has("sex")) {
                    headPortraitItem.setSex(jSONObject3.getString("sex"));
                }
                if (jSONObject3.has("profile_image")) {
                    headPortraitItem.setProfile_image(jSONObject3.getString("profile_image"));
                }
                if (jSONObject3.has("userid")) {
                    headPortraitItem.setUserid(jSONObject3.getString("userid"));
                }
                if (jSONObject3.has(HistoryOpenHelper.COLUMN_USERNAME)) {
                    headPortraitItem.setUsername(jSONObject3.getString(HistoryOpenHelper.COLUMN_USERNAME));
                }
                if (jSONObject3.has("praise_time")) {
                    headPortraitItem.setPraise_time(jSONObject3.getString("praise_time"));
                }
                if (jSONObject3.has("introduction")) {
                    headPortraitItem.setIntroduce(jSONObject3.getString("introduction"));
                }
                if (jSONObject3.has("fans_count")) {
                    headPortraitItem.setFans_count(jSONObject3.getString("fans_count"));
                }
                if (jSONObject3.has("tiezi_count")) {
                    headPortraitItem.setTiezi_count(jSONObject3.getString("tiezi_count"));
                }
                if (jSONObject3.has("is_follow")) {
                    headPortraitItem.setIs_follow(jSONObject3.getString("is_follow"));
                }
                if (jSONObject3.has("is_vip")) {
                    headPortraitItem.is_vip = jSONObject3.getBoolean("is_vip");
                }
                arrayList.add(headPortraitItem);
            }
            headPortraitData.setLists(arrayList);
            return headPortraitData;
        } catch (JSONException e) {
            Log.e(a, "parseHeadPortraitData " + e.toString());
            return headPortraitData;
        } catch (Exception e2) {
            Log.e(a, "parseHeadPortraitData " + e2.toString());
            return headPortraitData;
        }
    }

    public static MyMsgListData w(String str) {
        MyMsgListData myMsgListData = new MyMsgListData();
        if ("[]".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("info")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("info");
                if (jSONObject2.has("maxtime")) {
                    myMsgListData.setMaxtime(jSONObject2.getInt("maxtime"));
                }
                if (jSONObject2.has("count")) {
                    myMsgListData.setCount(jSONObject2.getInt("count"));
                    if (myMsgListData.getCount() <= 0) {
                        return myMsgListData;
                    }
                }
            }
            if (!jSONObject.has("list")) {
                return myMsgListData;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("list");
            List arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                MyMsgItem myMsgItem = new MyMsgItem();
                if (jSONObject3.has("sex")) {
                    myMsgItem.setSex(jSONObject3.getString("sex"));
                }
                if (jSONObject3.has("profile_image")) {
                    myMsgItem.setProfile_image(jSONObject3.getString("profile_image"));
                }
                if (jSONObject3.has("userid")) {
                    myMsgItem.setUserid(jSONObject3.getInt("userid"));
                }
                if (jSONObject3.has(HistoryOpenHelper.COLUMN_USERNAME)) {
                    myMsgItem.setUsername(jSONObject3.getString(HistoryOpenHelper.COLUMN_USERNAME));
                }
                if (jSONObject3.has("praise_time")) {
                    myMsgItem.setPraise_time(jSONObject3.getString("praise_time"));
                }
                if (jSONObject3.has("introduction")) {
                    myMsgItem.setIntroduce(jSONObject3.getString("introduction"));
                }
                if (jSONObject3.has("fans_count")) {
                    myMsgItem.setFans_count(jSONObject3.getString("fans_count"));
                }
                if (jSONObject3.has("tiezi_count")) {
                    myMsgItem.setTiezi_count(jSONObject3.getString("tiezi_count"));
                }
                if (jSONObject3.has("is_follow")) {
                    myMsgItem.setIs_follow(jSONObject3.getString("is_follow"));
                }
                arrayList.add(myMsgItem);
            }
            myMsgListData.setLists(arrayList);
            return myMsgListData;
        } catch (JSONException e) {
            Log.e(a, "MyMsgListData " + e.toString());
            return myMsgListData;
        } catch (Exception e2) {
            Log.e(a, "MyMsgListData " + e2.toString());
            return myMsgListData;
        }
    }

    public static ResultBean x(String str) {
        ResultBean resultBean = new ResultBean();
        if ("".equals(str) || "{}".equals(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(j.c)) {
                resultBean.setResult(jSONObject.getString(j.c));
            }
            if (!jSONObject.has("result_msg")) {
                return resultBean;
            }
            resultBean.setResult_msg(jSONObject.getString("result_msg"));
            return resultBean;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(a, "parseResultAndMsg " + e.toString());
            return resultBean;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e(a, "parseResultAndMsg " + e2.toString());
            return resultBean;
        }
    }

    public static <T> T a(String str, Class<T> cls) {
        try {
            return new Gson().fromJson(str, (Class) cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> ArrayList<T> b(String str, Class<T> cls) {
        ArrayList arrayList = (ArrayList) new Gson().fromJson(str, new z$1().getType());
        ArrayList<T> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new Gson().fromJson((JsonObject) it.next(), (Class) cls));
        }
        return arrayList2;
    }
}
