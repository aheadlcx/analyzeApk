package qsbk.app.model;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;

public class QiushiTopic extends QbBean {
    public static final QiushiTopic EMPTY = new QiushiTopic();
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_SYSTEM = 2;
    public int articleCount;
    public String background = "";
    public String content = "";
    public EventWindow eventWindow;
    public String icon = "";
    public int id;
    public String introduction;
    public boolean isSubscribed;
    public int readCount;
    public int subcribeCount;
    public int type;

    public QiushiTopic(String str, String str2, int i, int i2, boolean z) {
        this.content = str;
        this.icon = str2;
        this.articleCount = i;
        this.readCount = i2;
        this.isSubscribed = z;
    }

    public QiushiTopic(String str, String str2, String str3, int i, int i2, int i3, boolean z) {
        this.content = str;
        this.icon = str2;
        this.background = str3;
        this.articleCount = i;
        this.readCount = i2;
        this.subcribeCount = i3;
        this.isSubscribed = z;
    }

    public QiushiTopic(int i) {
        this.id = i;
    }

    public QiushiTopic(int i, String str) {
        this.id = i;
        this.content = str;
    }

    public boolean isSystem() {
        return this.type == 2;
    }

    public static ArrayList<QiushiTopic> jsonToArray(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList<QiushiTopic> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            QiushiTopic qiushiTopic = new QiushiTopic();
            try {
                qiushiTopic.constructJson(jSONArray.getJSONObject(i));
                arrayList.add(qiushiTopic);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void constructJson(JSONObject jSONObject) {
        this.content = jSONObject.optString("content");
        this.icon = jSONObject.optString("avatar");
        this.background = jSONObject.optString("background");
        this.id = jSONObject.optInt("id");
        this.isSubscribed = jSONObject.optBoolean("is_sub");
        this.type = jSONObject.optInt("type");
        JSONObject optJSONObject = jSONObject.optJSONObject("counter");
        if (optJSONObject != null) {
            this.articleCount = optJSONObject.optInt("article_count");
            this.readCount = optJSONObject.optInt("view_count");
            this.subcribeCount = optJSONObject.optInt("sub_count");
        }
        this.introduction = jSONObject.optString("introduction");
        if (jSONObject.has("activity")) {
            optJSONObject = jSONObject.optJSONObject("activity");
            if (optJSONObject != null) {
                this.eventWindow = EventWindow.parseJson(optJSONObject.optJSONObject("window"));
            }
        }
    }

    public String getWebUrl() {
        return String.format(Constants.QIUSHI_TOPIC_WEB_SHARE, new Object[]{this.id + ""});
    }

    public boolean hasEvent() {
        return this.eventWindow != null;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", this.content);
            jSONObject.put("pic_url", this.icon);
            jSONObject.put("background", this.background);
            jSONObject.put("id", this.id);
            jSONObject.put("is_sub", this.isSubscribed);
            jSONObject.put("introduction", this.introduction);
            jSONObject.put("type", this.type);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("article_count", this.articleCount);
            jSONObject2.put("view_count", this.readCount);
            jSONObject2.put("sub_count", this.subcribeCount);
            jSONObject.put("counter", jSONObject2);
            if (this.eventWindow != null) {
                jSONObject2 = new JSONObject();
                jSONObject2.put("window", this.eventWindow);
                jSONObject.put("activity", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.content) && this.id == 0;
    }

    public String toImDataJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("push_detail", this.content);
            jSONObject.put("title", "[糗百爆社]" + this.content);
            jSONObject.put("push_image", this.icon);
            jSONObject.put("msg_id", this.id + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void fromImJson(JSONObject jSONObject) {
        this.content = jSONObject.optString("push_detail");
        this.icon = jSONObject.optString("push_image");
        try {
            this.id = Integer.parseInt(jSONObject.optString("msg_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.id != ((QiushiTopic) obj).id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.id;
    }

    public void cloneFrom(QiushiTopic qiushiTopic) {
        this.id = qiushiTopic.id;
        this.content = qiushiTopic.content;
        this.isSubscribed = qiushiTopic.isSubscribed;
        this.icon = qiushiTopic.icon;
        this.articleCount = qiushiTopic.articleCount;
        this.readCount = qiushiTopic.readCount;
        this.subcribeCount = qiushiTopic.subcribeCount;
        this.background = qiushiTopic.background;
        this.introduction = qiushiTopic.introduction;
    }
}
