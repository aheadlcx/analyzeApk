package qsbk.app.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.im.datastore.DatabaseHelper;

public class CircleTopic extends QbBean {
    public static final int BACKGROUD = 1;
    public static final String BLACK_ROOM_ID = "3726";
    public static final int CAROUSEL = 0;
    public static final String CHICKEN_TOPIC_ID = "14635";
    public static final int CLOCKED_STATUS = 6;
    public static final String CREATE_ID = "0";
    public static final int ONE_IMAGE = 2;
    public static final String REMIX_ID = "4592";
    public static final int THREE_IMAGE = 3;
    public int articleCount;
    public List<BaseUserInfo> clockedRankingUsers;
    public int color;
    public String content;
    public int createAt;
    public String extraLink;
    public PicUrl icon;
    public String id;
    public String intro;
    public boolean isAnonymous;
    public boolean isPunch = false;
    public int master_id;
    public ArrayList<PicUrl> picUrls = new ArrayList();
    public int rank;
    public ArrayList<CircleArticle> recomendCircleArticles = new ArrayList();
    public String recommendContent;
    public int status;
    public int todayCount;
    public int type;
    public BaseUserInfo user;

    public CircleTopic(String str) {
        this.id = str;
        this.content = "";
        this.intro = "";
        this.recommendContent = "";
    }

    public static CircleTopic parseJson(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        try {
            BaseUserInfo baseUserInfo;
            int i2;
            CircleTopic circleTopic = new CircleTopic();
            circleTopic.content = jSONObject.optString("content");
            circleTopic.recommendContent = jSONObject.optString("recommend_content");
            circleTopic.createAt = jSONObject.optInt("create_at");
            circleTopic.id = String.valueOf(jSONObject.optInt("id"));
            circleTopic.color = jSONObject.optInt("color");
            circleTopic.articleCount = jSONObject.optInt("article_count");
            circleTopic.todayCount = jSONObject.optInt("today_article_count");
            circleTopic.isPunch = jSONObject.optBoolean("is_punch");
            JSONObject optJSONObject = jSONObject.optJSONObject("master");
            if (optJSONObject != null) {
                baseUserInfo = new BaseUserInfo();
                baseUserInfo.parseBaseInfo(optJSONObject);
                circleTopic.user = baseUserInfo;
            }
            circleTopic.master_id = jSONObject.optInt("master_id", -1);
            JSONArray optJSONArray = jSONObject.optJSONArray("pic_urls");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                circleTopic.picUrls = new ArrayList();
                int length = optJSONArray != null ? optJSONArray.length() : 0;
                for (i2 = 0; i2 < length; i2++) {
                    PicUrl picUrl = new PicUrl(circleTopic.createAt);
                    picUrl.constructJson(optJSONArray.getJSONObject(i2));
                    circleTopic.picUrls.add(picUrl);
                }
            }
            circleTopic.rank = jSONObject.optInt("rank");
            optJSONArray = jSONObject.optJSONArray("articles");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                circleTopic.recomendCircleArticles = new ArrayList();
                for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                    CircleArticle circleArticle = new CircleArticle();
                    circleTopic.recomendCircleArticles.add((CircleArticle) CircleArticle.parseJson(optJSONArray.getJSONObject(i2)));
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("avatar_urls");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                PicUrl picUrl2 = new PicUrl(circleTopic.createAt);
                picUrl2.constructJson(optJSONArray2.getJSONObject(0));
                circleTopic.icon = picUrl2;
            }
            circleTopic.intro = jSONObject.optString("abstract");
            circleTopic.isAnonymous = jSONObject.optBoolean("is_anonymous");
            circleTopic.extraLink = jSONObject.optString("link");
            circleTopic.status = jSONObject.optInt("status");
            optJSONArray2 = jSONObject.optJSONArray(DatabaseHelper.TABLE_USERS);
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                circleTopic.clockedRankingUsers = new ArrayList();
                while (i < optJSONArray2.length()) {
                    baseUserInfo = new BaseUserInfo();
                    baseUserInfo.parseBaseInfo(optJSONArray2.getJSONObject(i));
                    circleTopic.clockedRankingUsers.add(baseUserInfo);
                    i++;
                }
            }
            return circleTopic;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject toJson(CircleTopic circleTopic) {
        int i = 0;
        if (circleTopic == null) {
            return null;
        }
        try {
            int size;
            JSONArray jSONArray;
            int i2;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", circleTopic.id);
            jSONObject.put("content", circleTopic.content);
            jSONObject.put("color", circleTopic.color);
            jSONObject.put("create_at", circleTopic.createAt);
            jSONObject.put("article_count", circleTopic.articleCount);
            jSONObject.put("today_article_count", circleTopic.todayCount);
            jSONObject.put("is_anonymous", circleTopic.isAnonymous);
            jSONObject.put("recommend_content", circleTopic.recommendContent);
            if (circleTopic.user != null) {
                jSONObject.put("master", circleTopic.user.toJSONObject());
            }
            jSONObject.put("abstract", circleTopic.intro);
            if (circleTopic.icon != null) {
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(circleTopic.icon.toJSONObject());
                jSONObject.put("avatar_urls", jSONArray2);
            }
            if (circleTopic.picUrls != null && circleTopic.picUrls.size() > 0) {
                size = circleTopic.picUrls.size();
                jSONArray = new JSONArray();
                for (i2 = 0; i2 < size; i2++) {
                    jSONArray.put(((PicUrl) circleTopic.picUrls.get(i2)).toJSONObject());
                }
                jSONObject.put("pic_urls", jSONArray);
            }
            jSONObject.put("rank", circleTopic.rank);
            if (circleTopic.recomendCircleArticles != null && circleTopic.recomendCircleArticles.size() > 0) {
                size = circleTopic.recomendCircleArticles.size();
                jSONArray = new JSONArray();
                for (i2 = 0; i2 < size; i2++) {
                    jSONArray.put(CircleArticle.toJSONObject((CircleArticle) circleTopic.recomendCircleArticles.get(i2)));
                }
                jSONObject.put("articles", jSONArray);
            }
            jSONObject.put("link", circleTopic.extraLink);
            jSONObject.put("status", circleTopic.status);
            jSONObject.put("master_id", circleTopic.master_id);
            if (circleTopic.clockedRankingUsers != null && circleTopic.clockedRankingUsers.size() > 0) {
                JSONArray jSONArray3 = new JSONArray();
                while (i < circleTopic.clockedRankingUsers.size()) {
                    jSONArray3.put(((BaseUserInfo) circleTopic.clockedRankingUsers.get(i)).toJSONObject());
                    i++;
                }
                jSONObject.put(DatabaseHelper.TABLE_USERS, jSONArray3.toString());
            }
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateWith(CircleTopic circleTopic) {
        this.content = circleTopic.content;
        this.articleCount = circleTopic.articleCount;
        this.todayCount = circleTopic.todayCount;
        this.user = circleTopic.user;
        this.picUrls = circleTopic.picUrls;
        this.status = circleTopic.status;
        this.master_id = circleTopic.master_id;
        if (circleTopic.rank != 0) {
            this.rank = circleTopic.rank;
        }
        if (circleTopic.extraLink != null) {
            this.extraLink = circleTopic.extraLink;
        }
        this.icon = circleTopic.icon;
        this.intro = circleTopic.intro;
        this.isAnonymous = circleTopic.isAnonymous;
    }

    public int getRandomPicIndex() {
        if (this.picUrls == null || this.picUrls.size() == 0) {
            return (int) (Math.random() * 5.0d);
        }
        return (int) (Math.random() * ((double) this.picUrls.size()));
    }

    public String getPicUrl(int i) {
        if (this.picUrls == null || this.picUrls.size() <= i) {
            return "";
        }
        return ((PicUrl) this.picUrls.get(i)).url;
    }

    public int hashCode() {
        return this.id == null ? 0 : this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CircleTopic)) {
            return false;
        }
        if (this.id != null) {
            return this.id.equals(((CircleTopic) obj).id);
        }
        if (((CircleTopic) obj).id == null) {
            return true;
        }
        return false;
    }

    public boolean isBlackRoom() {
        return BLACK_ROOM_ID.equals(this.id);
    }

    public boolean isRemix() {
        return REMIX_ID.equals(this.id);
    }

    public boolean canPublishArticle() {
        return this.status != 5;
    }

    public boolean isClocked() {
        return this.status == 6;
    }

    public boolean isChicken() {
        return CHICKEN_TOPIC_ID.equals(this.id);
    }
}
