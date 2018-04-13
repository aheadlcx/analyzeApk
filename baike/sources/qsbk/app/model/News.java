package qsbk.app.model;

import android.text.TextUtils;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

@Deprecated
public class News extends QbBean {
    public static final int ONE_IMAGE = 1;
    public static final int TEXT = 0;
    public static final int THREE_IMAGE = 2;
    private int a;
    public int commentCount;
    public String content;
    public String[] covers;
    public String createdAt;
    public String description;
    public int downCount;
    public int id;
    public String[] imgs;
    public boolean isHot;
    public int participateCount;
    public String shareLink;
    public String source;
    public int status;
    public String title;
    public int upCount;
    public int viewCount;
    public int voteType = -1;
    public String webLink;

    private News() {
    }

    public static News parseFromJsonObject(JSONObject jSONObject) {
        JSONArray optJSONArray;
        int i;
        News news = new News();
        if (!jSONObject.isNull("id")) {
            news.id = jSONObject.optInt("id", -1);
        }
        if (!jSONObject.isNull("type")) {
            news.a = jSONObject.optInt("type", -1);
        }
        if (!jSONObject.isNull("title")) {
            news.title = jSONObject.optString("title");
        }
        if (!jSONObject.isNull("source")) {
            news.source = jSONObject.optString("source");
        }
        if (!jSONObject.isNull("content")) {
            news.content = jSONObject.optString("content");
        }
        if (!jSONObject.isNull("description")) {
            news.description = jSONObject.optString("description");
        }
        if (!jSONObject.isNull(QsbkDatabase.CREATE_AT)) {
            news.createdAt = jSONObject.optString(QsbkDatabase.CREATE_AT);
        }
        if (!jSONObject.isNull("covers")) {
            optJSONArray = jSONObject.optJSONArray("covers");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                news.covers = new String[optJSONArray.length()];
                for (i = 0; i < optJSONArray.length(); i++) {
                    try {
                        news.covers[i] = optJSONArray.getString(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!jSONObject.isNull("imgs")) {
            optJSONArray = jSONObject.optJSONArray("imgs");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                news.imgs = new String[optJSONArray.length()];
                for (i = 0; i < optJSONArray.length(); i++) {
                    try {
                        news.imgs[i] = optJSONArray.getString(i);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        if (!jSONObject.isNull("vote_type")) {
            news.voteType = jSONObject.optInt("vote_type", -1);
        }
        if (!jSONObject.isNull("comment_count")) {
            news.commentCount = jSONObject.optInt("comment_count", 0);
        }
        if (!jSONObject.isNull("up_count")) {
            news.upCount = jSONObject.optInt("up_count", 0);
        }
        if (!jSONObject.isNull("down_count")) {
            news.downCount = jSONObject.optInt("down_count", -1);
        }
        if (!jSONObject.isNull("view_count")) {
            news.viewCount = jSONObject.optInt("view_count", -1);
        }
        if (!jSONObject.isNull("web_link")) {
            news.webLink = jSONObject.optString("web_link");
        }
        if (!jSONObject.isNull("share_link")) {
            news.shareLink = jSONObject.optString("share_link");
        }
        if (!jSONObject.isNull("participate_count")) {
            news.participateCount = jSONObject.optInt("participate_count", 0);
        }
        return news;
    }

    public JSONObject encodeToJsonObject() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.id > 0) {
                jSONObject.put("id", this.id);
            }
            if (this.a != -1) {
                jSONObject.put("type", this.a);
            }
            if (!TextUtils.isEmpty(this.title)) {
                jSONObject.put("title", this.title);
            }
            if (!TextUtils.isEmpty(this.source)) {
                jSONObject.put("source", this.source);
            }
            if (!TextUtils.isEmpty(this.content)) {
                jSONObject.put("content", this.content);
            }
            if (!TextUtils.isEmpty(this.description)) {
                jSONObject.put("description", this.description);
            }
            if (!TextUtils.isEmpty(this.createdAt)) {
                jSONObject.put(QsbkDatabase.CREATE_AT, this.createdAt);
            }
            if (this.covers != null && this.covers.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (Object put : this.covers) {
                    jSONArray.put(put);
                }
                jSONObject.put("covers", jSONArray);
            }
            if (this.imgs != null && this.imgs.length > 0) {
                JSONArray jSONArray2 = new JSONArray();
                String[] strArr = this.imgs;
                int length = strArr.length;
                while (i < length) {
                    jSONArray2.put(strArr[i]);
                    i++;
                }
                jSONObject.put("imags", jSONArray2);
            }
            if (this.voteType != -1) {
                jSONObject.put("vote_type", this.voteType);
            }
            jSONObject.put("comment_count", this.commentCount);
            jSONObject.put("up_count", this.upCount);
            jSONObject.put("down_count", this.downCount);
            jSONObject.put("view_count", this.viewCount);
            if (!TextUtils.isEmpty(this.webLink)) {
                jSONObject.put("web_link", this.webLink);
            }
            if (!TextUtils.isEmpty(this.shareLink)) {
                jSONObject.put("share_link", this.shareLink);
            }
            jSONObject.put("participate_count", this.participateCount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof News)) {
            return false;
        }
        News news = (News) obj;
        if (this.id == news.id && TextUtils.equals(this.title, news.title) && TextUtils.equals(this.content, news.content) && TextUtils.equals(this.source, news.source) && this.createdAt.equals(news.createdAt)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.id + 527;
        if (!TextUtils.isEmpty(this.title)) {
            i = (i * 31) + this.title.hashCode();
        }
        if (!TextUtils.isEmpty(this.content)) {
            i = (i * 31) + this.content.hashCode();
        }
        if (!TextUtils.isEmpty(this.source)) {
            i = (i * 31) + this.source.hashCode();
        }
        return (i * 31) + this.createdAt.hashCode();
    }

    public String toString() {
        return encodeToJsonObject().toString();
    }

    public boolean isHotNews() {
        return this.isHot;
    }

    public boolean isTextNews() {
        return this.a == 0;
    }

    public boolean isOneImageNews() {
        return this.a == 1;
    }

    public boolean isThreeImageNews() {
        return this.a == 2;
    }

    public boolean canAddToRecommend() {
        return this.a == 1 || this.a == 2;
    }

    public String[] getImageUrl(int i) {
        if (this.covers == null) {
            return null;
        }
        int min = Math.min(this.covers.length, i);
        if (min > 0) {
            return (String[]) Arrays.copyOf(this.covers, min);
        }
        return null;
    }
}
