package qsbk.app.model;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.cafe.plugin.OthersPlugin;
import qsbk.app.database.QsbkDatabase;

public class Laisee implements Serializable {
    public static final String SP_POP_LAISEE = "laisee_pop";
    public static final String SUB_TYPE_VOICE = "voice";
    public static final String TYPE_ACTIVITY = "qb_topic";
    public static final int TYPE_CHARGE = 3;
    public static final int TYPE_GROUP_LUCK = 2;
    public static final int TYPE_GROUP_NORMAL = 1;
    public static final String TYPE_P2P = "p2p";
    public static final int TYPE_P2P_NORMAL = 0;
    public static final String TYPE_TRIBE = "tribe";
    public String bottom;
    public String circleArticleCnt;
    public String circleArticleDesc;
    public String circleArticleId;
    public String circleTopicDesc;
    public String circleTopicId;
    public String content;
    public String desc;
    public String detail;
    public int empty;
    public int expired;
    public int got;
    public int gotCount;
    public double gotMoney;
    public String id;
    public String lastDesc;
    public String myMoney;
    public boolean pop;
    public String secret;
    public BaseUser sendUser;
    public String subtype;
    public String title;
    public QiushiTopic topic;
    public int totalCount;
    public double totalMoney;
    public String type;
    public String webDesc;
    public String webUrl;

    public Laisee(String str, String str2) {
        this.id = str;
        this.secret = str2;
    }

    public Laisee(String str, String str2, boolean z) {
        this.id = str;
        this.secret = str2;
        this.pop = z;
    }

    public Laisee(LaiseeImInfo laiseeImInfo) {
        this.id = laiseeImInfo.laiseeId;
        this.secret = laiseeImInfo.secret;
    }

    public static Laisee createLaiseeFromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Laisee laiseeVoice;
        if (TextUtils.equals(jSONObject.optString("sub_type"), SUB_TYPE_VOICE)) {
            laiseeVoice = new LaiseeVoice();
        } else {
            laiseeVoice = new Laisee();
        }
        laiseeVoice.parse(jSONObject);
        return laiseeVoice;
    }

    public boolean isActivityLaisee() {
        return "qb_topic".equals(this.type);
    }

    public boolean isP2P() {
        return TYPE_P2P.equals(this.type);
    }

    public boolean isGot() {
        return this.got == 1;
    }

    public boolean isExpired() {
        return this.expired == 1;
    }

    public boolean isEmpty() {
        return this.empty == 1;
    }

    public boolean isMine() {
        return (this.sendUser == null || QsbkApp.currentUser == null || !TextUtils.equals(this.sendUser.getUid(), QsbkApp.currentUser.userId)) ? false : true;
    }

    public void parse(String str) {
        try {
            parse(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.id = jSONObject.optString("id");
            Object optString = jSONObject.optString("secret");
            if (!TextUtils.isEmpty(optString)) {
                this.secret = optString;
            }
            this.type = jSONObject.optString("type");
            this.expired = jSONObject.optInt("expired");
            this.empty = jSONObject.optInt("empty");
            this.got = jSONObject.optInt("got");
            this.desc = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
            this.title = jSONObject.optString("title");
            this.detail = jSONObject.optString("detail");
            this.content = jSONObject.optString("content");
            this.bottom = jSONObject.optString("bottom");
            this.lastDesc = jSONObject.optString("last_desc");
            this.pop = jSONObject.optBoolean(OthersPlugin.ACTION_POP, false);
            this.circleArticleId = jSONObject.optString("circle_article_id");
            this.circleArticleDesc = jSONObject.optString("cirlce_article_desc");
            this.circleArticleCnt = jSONObject.optString("circle_article_cnt");
            this.circleTopicDesc = jSONObject.optString("circle_topic_desc");
            this.circleTopicId = jSONObject.optString("circle_topic_id");
            this.webUrl = jSONObject.optString("web_url");
            this.webDesc = jSONObject.optString("web_desc");
            this.subtype = jSONObject.optString("sub_type");
            JSONObject optJSONObject = jSONObject.optJSONObject("user");
            if (optJSONObject != null) {
                this.sendUser = new BaseUser(optJSONObject.optString("id"), optJSONObject.optString(QsbkDatabase.LOGIN), optJSONObject.optString("icon"));
            }
        }
    }

    public String toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            jSONObject.put("secret", this.secret);
            jSONObject.put("type", this.type);
            jSONObject.put("expired", this.expired);
            jSONObject.put("empty", this.empty);
            jSONObject.put("got", this.got);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, this.desc);
            jSONObject.put("title", this.title);
            jSONObject.put("detail", this.detail);
            jSONObject.put("content", this.content);
            jSONObject.put("bottom", this.bottom);
            jSONObject.put("last_desc", this.lastDesc);
            jSONObject.put(OthersPlugin.ACTION_POP, this.pop);
            jSONObject.put("circle_article_id", this.circleArticleId);
            jSONObject.put("cirlce_article_desc", this.circleArticleDesc);
            jSONObject.put("circle_article_cnt", this.circleArticleCnt);
            jSONObject.put("circle_topic_desc", this.circleTopicDesc);
            jSONObject.put("circle_topic_id", this.circleTopicId);
            jSONObject.put("web_url", this.webUrl);
            jSONObject.put("web_desc", this.webDesc);
            jSONObject.put("sub_type", this.subtype);
            if (this.sendUser != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(QsbkDatabase.LOGIN, this.sendUser.getLogin());
                jSONObject2.put("id", this.sendUser.getUid());
                jSONObject2.put("icon", this.sendUser.getIcon());
                jSONObject.put("user", jSONObject2);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
