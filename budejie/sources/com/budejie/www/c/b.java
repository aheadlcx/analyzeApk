package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.budejie.www.activity.mycomment.MyCommentInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.bean.RecommendUser;
import com.budejie.www.bean.RichObject;
import com.budejie.www.bean.ShenHeItem;
import com.budejie.www.bean.Topics;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.bean.User;
import com.budejie.www.util.an;
import com.budejie.www.util.ax;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.tencent.stat.DeviceInfo;
import com.umeng.analytics.a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.apache.commons.httpclient.HttpState;

public class b {
    Context a;
    SQLiteDatabase b;
    c c;

    public b(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void d() {
        this.b = this.c.getWritableDatabase();
    }

    private void e() {
        this.b.close();
    }

    public void a(String str, String[] strArr) {
        synchronized (c.a) {
            d();
            this.b.delete("newCacheTable", str, strArr);
            e();
        }
    }

    public void a(String str, String str2) {
        synchronized (c.a) {
            d();
            if (str.equals("cacheTable")) {
                this.b.delete(str, null, null);
            } else {
                Cursor query = this.b.query(str, new String[]{CheckCodeDO.CHECKCODE_IMAGE_URL_KEY}, " wid= " + str2, null, null, null, null);
                Object obj = "";
                if (query.moveToFirst()) {
                    obj = query.getString(query.getColumnIndex(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY));
                }
                this.b.delete(str, "wid=" + str2, null);
                if (!TextUtils.isEmpty(obj)) {
                    new File(obj).delete();
                }
            }
            e();
        }
    }

    public void a(ShenHeItem shenHeItem) {
        String lastId = shenHeItem.getLastId();
        List arrayList = new ArrayList();
        ArrayList dataList = shenHeItem.getDataList();
        if (dataList != null) {
            Iterator it = dataList.iterator();
            while (it.hasNext()) {
                arrayList.add(an.a((TouGaoItem) it.next()));
            }
            if (arrayList.size() > 0) {
                a(arrayList, lastId, false, "1");
            }
        }
    }

    public void a(List<ListItemObject> list, String str, boolean z, String str2) {
        a(list, str, z, str2, "");
    }

    public void a(List<ListItemObject> list, String str, boolean z, String str2, String str3) {
        ListItemObject listItemObject;
        int i = 0;
        List arrayList = new ArrayList();
        for (ListItemObject listItemObject2 : list) {
            if (listItemObject2.getOriginal_topic() != null) {
                arrayList.add(listItemObject2.getOriginal_topic());
            }
        }
        if (arrayList != null && arrayList.size() > 0) {
            a(arrayList, "", false, "3", "");
        }
        synchronized (c.a) {
            d();
            this.b.beginTransaction();
            while (i < list.size()) {
                listItemObject2 = (ListItemObject) list.get(i);
                if (!listItemObject2.isIs_ad()) {
                    ContentValues contentValues = new ContentValues();
                    try {
                        contentValues.put("wid", listItemObject2.getWid());
                        contentValues.put("screen_name", listItemObject2.getName());
                        contentValues.put(DeviceInfo.TAG_MID, listItemObject2.getMid());
                        contentValues.put("created_at", listItemObject2.getAddtime());
                        contentValues.put("passtime", listItemObject2.getPasstime());
                        contentValues.put("text", listItemObject2.getContent());
                        contentValues.put("type", listItemObject2.getType());
                        contentValues.put(UserTrackerConstants.USER_ID, listItemObject2.getUid());
                        contentValues.put(IndexEntry.COLUMN_NAME_WIDTH, Integer.valueOf(listItemObject2.getWidth()));
                        contentValues.put(IndexEntry.COLUMN_NAME_HEIGHT, Integer.valueOf(listItemObject2.getHeight()));
                        contentValues.put("profile_image", listItemObject2.getProfile());
                        contentValues.put("image1", listItemObject2.getImgUrl());
                        contentValues.put("weixinurl", listItemObject2.getWeixin_url());
                        contentValues.put("is_gif", listItemObject2.getIs_gif());
                        contentValues.put("love", Integer.valueOf(listItemObject2.getLove()));
                        contentValues.put("hate", Integer.valueOf(listItemObject2.getCai()));
                        contentValues.put("comment", listItemObject2.getComment());
                        contentValues.put("forward", listItemObject2.getRepost());
                        contentValues.put("bookmark", Integer.valueOf(listItemObject2.getFavorite()));
                        contentValues.put("gifFistFrame", listItemObject2.getGifFistFrame());
                        contentValues.put("voiceuri", listItemObject2.getVoiceUri());
                        contentValues.put("voicetime", listItemObject2.getVoicetime());
                        contentValues.put("videouri", listItemObject2.getVideouri());
                        contentValues.put("videotime", listItemObject2.getVideotime());
                        contentValues.put("playcount", listItemObject2.getPlaycount());
                        contentValues.put("playfcount", listItemObject2.getPlayfcount());
                        contentValues.put("readid", str);
                        contentValues.put("hasdata", z ? Constants.SERVICE_SCOPE_FLAG_VALUE : HttpState.PREEMPTIVE_DEFAULT);
                        contentValues.put("cacheTypeNew", str2);
                        contentValues.put("statusText", listItemObject2.getStatus_text());
                        contentValues.put("voiceBgId", Integer.valueOf(listItemObject2.getVoiceBgId()));
                        contentValues.put("cnd_img", listItemObject2.getCnd_img());
                        contentValues.put("theme_id", Integer.valueOf(listItemObject2.getTheme_id()));
                        contentValues.put("theme_type", Integer.valueOf(listItemObject2.getTheme_type()));
                        contentValues.put("theme_name", listItemObject2.getTheme_name());
                        contentValues.put("tags", listItemObject2.getPlateDatasJson());
                        if (listItemObject2.getHotcmt() != null) {
                            contentValues.put("hot_cmt_username", listItemObject2.getHotcmt().user.username);
                            contentValues.put("hot_cmt_content", listItemObject2.getHotcmt().content);
                            contentValues.put("hot_cmt_voice_comment_url", listItemObject2.getHotcmt().voiceuri);
                            contentValues.put("hot_cmt_voice_comment_time", listItemObject2.getHotcmt().voicetime);
                        }
                        if (listItemObject2.getPrecmt() != null) {
                            contentValues.put("hot_cmt_reply_username", listItemObject2.getPrecmt().user.username);
                            contentValues.put("hot_cmt_reply_content", listItemObject2.getPrecmt().content);
                            contentValues.put("hot_cmt_reply_voice_comment_url", listItemObject2.getPrecmt().voiceuri);
                            contentValues.put("hot_cmt_reply_voice_comment_time", listItemObject2.getPrecmt().voicetime);
                        }
                        contentValues.put("topic_tag_type", str3);
                        if (listItemObject2.getRichObject() != null) {
                            contentValues.put("rich_desc", listItemObject2.getRichObject().getDesc());
                            contentValues.put("rich_img_url", listItemObject2.getRichObject().getImgUrl());
                            contentValues.put("rich_source_rl", listItemObject2.getRichObject().getSourceUrl());
                            contentValues.put("rich_title", listItemObject2.getRichObject().getTitle());
                        }
                        if (listItemObject2.getOriginal_topic() != null) {
                            contentValues.put("original_pid", listItemObject2.getOriginal_topic().getWid());
                        }
                        if (listItemObject2.getDownloadVideoUris() != null) {
                            contentValues.put("downloadVideoUris", TextUtils.join(",", listItemObject2.getDownloadVideoUris()));
                        }
                        if (listItemObject2.getDownloadImageUris() != null) {
                            contentValues.put("downloadImageUris", TextUtils.join(",", listItemObject2.getDownloadImageUris()));
                        }
                        contentValues.put("videouri_back", listItemObject2.getVideouriBackup());
                        contentValues.put("sina_v", listItemObject2.getSina_v());
                        contentValues.put("is_vip", listItemObject2.getIs_vip());
                        contentValues.put("status", Integer.valueOf(listItemObject2.getStatus()));
                        contentValues.put("top_comments", listItemObject2.getHotCommentsJson());
                        contentValues.put("votes", listItemObject2.getVoteDataJson());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.b.insert("newCacheTable", null, contentValues);
                }
                i++;
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            e();
        }
    }

    public void a(List<RecommendSubscription> list) {
        synchronized (c.a) {
            d();
            this.b.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                RecommendSubscription recommendSubscription = (RecommendSubscription) list.get(i);
                Log.i("theme_name", recommendSubscription.getTheme_name());
                ContentValues contentValues = new ContentValues();
                try {
                    contentValues.put("theme_id", recommendSubscription.getTheme_id());
                    contentValues.put("theme_name", recommendSubscription.getTheme_name());
                    contentValues.put("image_list", recommendSubscription.getImage_list());
                    contentValues.put("sub_number", recommendSubscription.getSub_number());
                    contentValues.put("is_sub", recommendSubscription.getIs_sub());
                    contentValues.put("is_default", recommendSubscription.getIs_default());
                    contentValues.put("type", recommendSubscription.getType());
                    contentValues.put("post_num", recommendSubscription.getPost_num());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.b.insert("subscribe_Label", null, contentValues);
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            e();
        }
    }

    public void b(List<RecommendSubscription> list) {
        synchronized (c.a) {
            d();
            this.b.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                RecommendSubscription recommendSubscription = (RecommendSubscription) list.get(i);
                ContentValues contentValues = new ContentValues();
                try {
                    contentValues.put("theme_id", recommendSubscription.getTheme_id());
                    contentValues.put("theme_name", recommendSubscription.getTheme_name());
                    contentValues.put("image_list", recommendSubscription.getImage_list());
                    contentValues.put("sub_number", recommendSubscription.getSub_number());
                    contentValues.put("is_sub", recommendSubscription.getIs_sub());
                    contentValues.put("is_default", recommendSubscription.getIs_default());
                    contentValues.put("type", recommendSubscription.getType());
                    contentValues.put("post_num", recommendSubscription.getPost_num());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.b.insert("recommend_Label", null, contentValues);
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            e();
        }
    }

    public void c(List<RecommendUser> list) {
        synchronized (c.a) {
            d();
            this.b.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                ContentValues contentValues = new ContentValues();
                int i2 = 0;
                while (i2 < ((RecommendUser) list.get(i)).getTopics().size()) {
                    try {
                        Topics topics = (Topics) ((RecommendUser) list.get(i)).getTopics().get(i2);
                        contentValues.put("pid", topics.getPid());
                        contentValues.put(a.z, topics.getBody());
                        contentValues.put(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, topics.getImage());
                        contentValues.put("type", topics.getType());
                        RecommendUser recommendUser = (RecommendUser) list.get(i);
                        contentValues.put("id", recommendUser.getId());
                        contentValues.put("userid", recommendUser.getUserid());
                        contentValues.put(HistoryOpenHelper.COLUMN_USERNAME, recommendUser.getUsername());
                        contentValues.put("profile_image", recommendUser.getProfile_image());
                        contentValues.put("introduction", recommendUser.getIntroduction());
                        this.b.insert("recommend_user", null, contentValues);
                        i2++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            e();
        }
    }

    public boolean a(String str) {
        synchronized (c.a) {
            d();
            this.b.delete("subscribe_Label", null, null);
            e();
        }
        return false;
    }

    public boolean b(String str) {
        synchronized (c.a) {
            d();
            this.b.delete(str, null, null);
            e();
        }
        return false;
    }

    public ArrayList<ListItemObject> c(String str) {
        return a(str, null, "");
    }

    public ArrayList<ListItemObject> b(String str, String str2) {
        return a(str, str2, "");
    }

    public ArrayList<ListItemObject> a(String str, String str2, String str3) {
        List arrayList;
        synchronized (c.a) {
            ListItemObject listItemObject;
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", "wid", "screen_name", DeviceInfo.TAG_MID, "created_at", "text", "type", UserTrackerConstants.USER_ID, IndexEntry.COLUMN_NAME_WIDTH, IndexEntry.COLUMN_NAME_HEIGHT, "profile_image", "image1", "weixinurl", "is_gif", "love", "hate", "comment", "bookmark", "forward", "gifFistFrame", "voiceuri", "voicetime", "playcount", "playfcount", "readid", "hasdata", "passtime", "statusText", "voiceBgId", "videouri", "videotime", "cnd_img", "theme_id", "theme_type", "theme_name", "hot_cmt_username", "hot_cmt_content", "hot_cmt_voice_comment_url", "hot_cmt_voice_comment_time", "hot_cmt_reply_username", "hot_cmt_reply_content", "hot_cmt_reply_voice_comment_url", "hot_cmt_reply_voice_comment_time", "rich_desc", "rich_img_url", "rich_source_rl", "rich_title", "original_pid", "downloadVideoUris", "videouri_back", "downloadImageUris", "sina_v", "is_vip", "status", "top_comments", "votes", "tags"};
            d();
            Cursor query = this.b.query("newCacheTable", strArr, "cacheTypeNew = ? and topic_tag_type = ?", new String[]{str + "", str3}, null, null, str2);
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    listItemObject = new ListItemObject();
                    try {
                        listItemObject.setId(query.getInt(0));
                        listItemObject.setWid(query.getString(1));
                        listItemObject.setName(query.getString(2));
                        listItemObject.setMid(query.getString(3));
                        listItemObject.setAddtime(query.getString(4));
                        listItemObject.setContent(query.getString(5));
                        listItemObject.setType(query.getString(6));
                        listItemObject.setUid(query.getString(7));
                        listItemObject.setWidth(query.getInt(8));
                        listItemObject.setHeight(query.getInt(9));
                        listItemObject.setProfile(query.getString(10));
                        listItemObject.setImgUrl(query.getString(11));
                        listItemObject.setWeixin_url(query.getString(12));
                        listItemObject.setIs_gif(query.getString(13));
                        listItemObject.setLove(query.getInt(14));
                        listItemObject.setCai(query.getInt(15));
                        listItemObject.setComment(query.getString(16));
                        listItemObject.setFavorite(query.getInt(17));
                        listItemObject.setRepost(query.getString(18));
                        listItemObject.setGifFistFrame(query.getString(19));
                        listItemObject.setVoiceUri(query.getString(20));
                        listItemObject.setVoicetime(query.getString(21));
                        listItemObject.setPlaycount(query.getString(22));
                        listItemObject.setPlayfcount(query.getString(23));
                        listItemObject.setReadid(query.getString(24));
                        listItemObject.setHasData(query.getString(25));
                        listItemObject.setPasstime(query.getString(26));
                        listItemObject.setStatus_text(query.getString(27));
                        listItemObject.setVoiceBgId(query.getInt(28));
                        listItemObject.setVideouri(query.getString(29));
                        listItemObject.setVideotime(query.getString(30));
                        listItemObject.setCnd_img(query.getString(query.getColumnIndex("cnd_img")));
                        listItemObject.setTheme_id(query.getInt(32));
                        listItemObject.setTheme_type(query.getInt(33));
                        listItemObject.setTheme_name(query.getString(34));
                        if (!TextUtils.isEmpty(query.getString(35))) {
                            listItemObject.setHotcmt(new MyCommentInfo(query.getString(36), query.getString(37), query.getString(38), new User(query.getString(35))));
                        }
                        if (!TextUtils.isEmpty(query.getString(39))) {
                            listItemObject.setPrecmt(new MyCommentInfo(query.getString(40), query.getString(41), query.getString(42), new User(query.getString(39))));
                        }
                        if (!TextUtils.isEmpty(query.getString(45))) {
                            RichObject richObject = new RichObject();
                            richObject.setDesc(query.getString(43));
                            richObject.setImgUrl(query.getString(44));
                            richObject.setSourceUrl(query.getString(45));
                            richObject.setTitle(query.getString(46));
                            listItemObject.setRichObject(richObject);
                        }
                        if (!TextUtils.isEmpty(query.getString(47))) {
                            listItemObject.setOriginal_pid(query.getString(47));
                        }
                        Object string = query.getString(query.getColumnIndex("downloadVideoUris"));
                        if (!TextUtils.isEmpty(string)) {
                            listItemObject.setDownloadVideoUris(TextUtils.split(string, ","));
                        }
                        string = query.getString(query.getColumnIndex("downloadImageUris"));
                        if (!TextUtils.isEmpty(string)) {
                            listItemObject.setDownloadImageUris(TextUtils.split(string, ","));
                        }
                        listItemObject.setVideouriBackup(query.getString(query.getColumnIndex("videouri_back")));
                        listItemObject.setSina_v(query.getString(query.getColumnIndex("sina_v")));
                        listItemObject.setIs_vip(query.getString(query.getColumnIndex("is_vip")));
                        listItemObject.setStatus(query.getInt(query.getColumnIndex("status")));
                        listItemObject.setHotCommentsJson(query.getString(query.getColumnIndex("top_comments")));
                        listItemObject.setVoteDataJson(query.getString(query.getColumnIndex("votes")));
                        listItemObject.setPlateDatasJson(query.getString(query.getColumnIndex("tags")));
                        arrayList.add(listItemObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            query.close();
            e();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                listItemObject = (ListItemObject) it.next();
                if (!TextUtils.isEmpty(listItemObject.getOriginal_pid())) {
                    listItemObject.setOriginal_topic(c(listItemObject.getOriginal_pid(), "3"));
                }
            }
            ax.a(arrayList, this.a);
        }
        return arrayList;
    }

    public ListItemObject c(String str, String str2) {
        ListItemObject listItemObject;
        synchronized (c.a) {
            listItemObject = new ListItemObject();
            String[] strArr = new String[]{"id", "wid", "screen_name", DeviceInfo.TAG_MID, "created_at", "text", "type", UserTrackerConstants.USER_ID, IndexEntry.COLUMN_NAME_WIDTH, IndexEntry.COLUMN_NAME_HEIGHT, "profile_image", "image1", "weixinurl", "is_gif", "love", "hate", "comment", "bookmark", "forward", "gifFistFrame", "voiceuri", "voicetime", "playcount", "playfcount", "readid", "hasdata", "passtime", "statusText", "voiceBgId", "videouri", "videotime", "cnd_img", "theme_id", "theme_type", "theme_name", "hot_cmt_username", "hot_cmt_content", "hot_cmt_voice_comment_url", "hot_cmt_voice_comment_time", "hot_cmt_reply_username", "hot_cmt_reply_content", "hot_cmt_reply_voice_comment_url", "hot_cmt_reply_voice_comment_time", "rich_desc", "rich_img_url", "rich_source_rl", "rich_title", "downloadVideoUris", "videouri_back", "downloadImageUris", "sina_v", "is_vip", "status", "top_comments", "votes", "tags"};
            d();
            Cursor query = this.b.query("newCacheTable", strArr, "cacheTypeNew = ? and wid = ?", new String[]{str2 + "", str}, null, null, null);
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    try {
                        listItemObject.setId(query.getInt(0));
                        listItemObject.setWid(query.getString(1));
                        listItemObject.setName(query.getString(2));
                        listItemObject.setMid(query.getString(3));
                        listItemObject.setAddtime(query.getString(4));
                        listItemObject.setContent(query.getString(5));
                        listItemObject.setType(query.getString(6));
                        listItemObject.setUid(query.getString(7));
                        listItemObject.setWidth(query.getInt(8));
                        listItemObject.setHeight(query.getInt(9));
                        listItemObject.setProfile(query.getString(10));
                        listItemObject.setImgUrl(query.getString(11));
                        listItemObject.setWeixin_url(query.getString(12));
                        listItemObject.setIs_gif(query.getString(13));
                        listItemObject.setLove(query.getInt(14));
                        listItemObject.setCai(query.getInt(15));
                        listItemObject.setComment(query.getString(16));
                        listItemObject.setFavorite(query.getInt(17));
                        listItemObject.setRepost(query.getString(18));
                        listItemObject.setGifFistFrame(query.getString(19));
                        listItemObject.setVoiceUri(query.getString(20));
                        listItemObject.setVoicetime(query.getString(21));
                        listItemObject.setPlaycount(query.getString(22));
                        listItemObject.setPlayfcount(query.getString(23));
                        listItemObject.setReadid(query.getString(24));
                        listItemObject.setHasData(query.getString(25));
                        listItemObject.setPasstime(query.getString(26));
                        listItemObject.setStatus_text(query.getString(27));
                        listItemObject.setVoiceBgId(query.getInt(28));
                        listItemObject.setVideouri(query.getString(29));
                        listItemObject.setVideotime(query.getString(30));
                        listItemObject.setCnd_img(query.getString(query.getColumnIndex("cnd_img")));
                        listItemObject.setTheme_id(query.getInt(32));
                        listItemObject.setTheme_type(query.getInt(33));
                        listItemObject.setTheme_name(query.getString(34));
                        if (!TextUtils.isEmpty(query.getString(35))) {
                            listItemObject.setHotcmt(new MyCommentInfo(query.getString(36), query.getString(37), query.getString(38), new User(query.getString(35))));
                        }
                        if (!TextUtils.isEmpty(query.getString(39))) {
                            listItemObject.setPrecmt(new MyCommentInfo(query.getString(40), query.getString(41), query.getString(42), new User(query.getString(39))));
                        }
                        if (!TextUtils.isEmpty(query.getString(45))) {
                            RichObject richObject = new RichObject();
                            richObject.setDesc(query.getString(43));
                            richObject.setImgUrl(query.getString(44));
                            richObject.setSourceUrl(query.getString(45));
                            richObject.setTitle(query.getString(46));
                            listItemObject.setRichObject(richObject);
                        }
                        Object string = query.getString(query.getColumnIndex("downloadVideoUris"));
                        if (!TextUtils.isEmpty(string)) {
                            listItemObject.setDownloadVideoUris(TextUtils.split(string, ","));
                        }
                        string = query.getString(query.getColumnIndex("downloadImageUris"));
                        if (!TextUtils.isEmpty(string)) {
                            listItemObject.setDownloadImageUris(TextUtils.split(string, ","));
                        }
                        listItemObject.setVideouriBackup(query.getString(query.getColumnIndex("videouri_back")));
                        listItemObject.setSina_v(query.getString(query.getColumnIndex("sina_v")));
                        listItemObject.setIs_vip(query.getString(query.getColumnIndex("is_vip")));
                        listItemObject.setStatus(query.getInt(query.getColumnIndex("status")));
                        listItemObject.setHotCommentsJson(query.getString(query.getColumnIndex("top_comments")));
                        listItemObject.setVoteDataJson(query.getString(query.getColumnIndex("votes")));
                        listItemObject.setPlateDatasJson(query.getString(query.getColumnIndex("tags")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            query.close();
            e();
        }
        return listItemObject;
    }

    public void d(String str) {
        d(str, "");
    }

    public void d(String str, String str2) {
        synchronized (c.a) {
            d();
            this.b.delete("newCacheTable", "cacheTypeNew = ? and topic_tag_type = ?", new String[]{str + "", str2});
            e();
        }
    }

    public List<RecommendSubscription> a() {
        List arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"theme_id", "theme_name", "image_list", "sub_number", "is_sub", "is_default", "type", "post_num"};
            d();
            Cursor query = this.b.query("subscribe_Label", strArr, null, null, null, null, "id desc");
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    RecommendSubscription recommendSubscription = new RecommendSubscription();
                    try {
                        recommendSubscription.setTheme_id(query.getInt(0) + "");
                        recommendSubscription.setTheme_name(query.getString(1));
                        recommendSubscription.setImage_list(query.getString(2));
                        recommendSubscription.setSub_number(query.getString(3));
                        recommendSubscription.setIs_sub(query.getString(4));
                        recommendSubscription.setIs_default(query.getString(5));
                        recommendSubscription.setType(query.getString(6));
                        recommendSubscription.setPost_num(query.getString(7));
                        arrayList.add(recommendSubscription);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            query.close();
            e();
        }
        return arrayList;
    }

    public List<RecommendUser> b() {
        List arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", "userid", HistoryOpenHelper.COLUMN_USERNAME, "profile_image", "introduction", "pid", a.z, CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, "type"};
            d();
            Cursor query = this.b.query("recommend_user", strArr, null, null, null, null, null);
            if (query.getCount() != 0) {
                Map hashMap = new HashMap();
                while (query.moveToNext()) {
                    try {
                        RecommendUser recommendUser;
                        String string = query.getString(0);
                        if (hashMap.containsKey(string)) {
                            recommendUser = (RecommendUser) hashMap.get(string);
                        } else {
                            recommendUser = new RecommendUser();
                            recommendUser.setTopics(new ArrayList());
                            hashMap.put(string, recommendUser);
                            recommendUser.setId(string);
                            recommendUser.setUserid(query.getString(1));
                            recommendUser.setUsername(query.getString(2));
                            recommendUser.setProfile_image(query.getString(3));
                            recommendUser.setIntroduction(query.getString(4));
                        }
                        Topics topics = new Topics();
                        topics.setPid(query.getString(5));
                        topics.setBody(query.getString(6));
                        topics.setImage(query.getString(7));
                        topics.setType(query.getString(8));
                        recommendUser.getTopics().add(topics);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (String str : hashMap.keySet()) {
                    arrayList.add(hashMap.get(str));
                }
            }
            query.close();
            e();
        }
        return arrayList;
    }

    public List<RecommendSubscription> c() {
        List arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"theme_id", "theme_name", "image_list", "sub_number", "is_default", "is_sub", "type", "post_num"};
            d();
            Cursor query = this.b.query("recommend_Label", strArr, null, null, null, null, null);
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    RecommendSubscription recommendSubscription = new RecommendSubscription();
                    try {
                        recommendSubscription.setTheme_id(query.getInt(0) + "");
                        recommendSubscription.setTheme_name(query.getString(1));
                        recommendSubscription.setImage_list(query.getString(2));
                        recommendSubscription.setSub_number(query.getString(3));
                        recommendSubscription.setIs_default(query.getString(4));
                        recommendSubscription.setIs_sub(query.getString(5));
                        recommendSubscription.setType(query.getString(6));
                        recommendSubscription.setPost_num(query.getString(7));
                        arrayList.add(recommendSubscription);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            query.close();
            e();
        }
        return arrayList;
    }

    public List<RecommendSubscription> e(String str) {
        List arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            d();
            Cursor query = this.b.query("recommend_Label", new String[]{"theme_id", "theme_name", "image_list", "sub_number", "is_default", "is_sub", "type", "post_num"}, "theme_name like ?", new String[]{"%" + str + "%"}, null, null, null);
            if (query.getCount() != 0) {
                while (query.moveToNext()) {
                    RecommendSubscription recommendSubscription = new RecommendSubscription();
                    try {
                        recommendSubscription.setTheme_id(query.getInt(0) + "");
                        recommendSubscription.setTheme_name(query.getString(1));
                        recommendSubscription.setImage_list(query.getString(2));
                        recommendSubscription.setSub_number(query.getString(3));
                        recommendSubscription.setIs_default(query.getString(4));
                        recommendSubscription.setIs_sub(query.getString(5));
                        recommendSubscription.setType(query.getString(6));
                        recommendSubscription.setPost_num(query.getString(7));
                        arrayList.add(recommendSubscription);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            query.close();
            e();
        }
        return arrayList;
    }

    public void a(String str, String str2, String str3, String str4) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_sub", str4);
            contentValues.put("sub_number", str3);
            d();
            this.b.update(str, contentValues, "theme_id = " + str2, null);
            e();
        }
    }

    public void e(String str, String str2) {
        synchronized (c.a) {
            String[] strArr = new String[]{str2};
            d();
            this.b.delete(str, "theme_id = ?", strArr);
            e();
        }
    }

    public void a(String str, RecommendSubscription recommendSubscription) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("theme_id", recommendSubscription.getTheme_id());
            contentValues.put("theme_name", recommendSubscription.getTheme_name());
            contentValues.put("image_list", recommendSubscription.getImage_list());
            contentValues.put("sub_number", recommendSubscription.getSub_number());
            contentValues.put("is_sub", recommendSubscription.getIs_sub());
            contentValues.put("is_default", recommendSubscription.getIs_default());
            contentValues.put("type", recommendSubscription.getType());
            contentValues.put("post_num", recommendSubscription.getPost_num());
            d();
            this.b.insert(str, null, contentValues);
            e();
        }
    }
}
