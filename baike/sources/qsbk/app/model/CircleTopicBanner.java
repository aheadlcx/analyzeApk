package qsbk.app.model;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.CircleTopicWeeklyActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.database.QsbkDatabase;

public class CircleTopicBanner extends QbBean {
    public static final int TYPE_AD = 5;
    public static final int TYPE_BLACK_LIST = 1;
    public static final int TYPE_CIRCLE_ARTICLE = 3;
    public static final int TYPE_CIRCLE_RECOMMEND = 4;
    public static final int TYPE_TOPIC = 2;
    public String bigPicUrl;
    public String id;
    public String picUrl;
    public String subTitle;
    public String target;
    public String title;
    public int type;

    public static CircleTopicBanner parseJson(JSONObject jSONObject) {
        CircleTopicBanner circleTopicBanner = new CircleTopicBanner();
        circleTopicBanner.id = jSONObject.optString("id");
        circleTopicBanner.type = jSONObject.optInt("type");
        circleTopicBanner.picUrl = jSONObject.optString("pic_url");
        circleTopicBanner.bigPicUrl = jSONObject.optString("rpic_url");
        circleTopicBanner.target = jSONObject.optString(QsbkDatabase.TARGET);
        circleTopicBanner.title = jSONObject.optString("title");
        circleTopicBanner.subTitle = jSONObject.optString("sub_title");
        return circleTopicBanner;
    }

    public static List<CircleTopicBanner> parseJsonArray(JSONArray jSONArray) {
        List<CircleTopicBanner> arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        CircleTopicBanner parseJson = parseJson(jSONObject);
                        if (parseJson != null) {
                            arrayList.add(parseJson);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("type", this.type);
            jSONObject.put("pic_url", this.picUrl);
            jSONObject.put("rpic_url", this.bigPicUrl);
            jSONObject.put(QsbkDatabase.TARGET, this.target);
            jSONObject.put("title", this.title);
            jSONObject.put("sub_title", this.subTitle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void jumpTo(Context context) {
        switch (this.type) {
            case 1:
                CircleTopicActivity.launch(context, CircleTopic.BLACK_ROOM_ID);
                return;
            case 2:
                CircleTopicActivity.launch(context, this.target);
                return;
            case 3:
                CircleArticleActivity.launch(context, this.target, false);
                return;
            case 4:
                CircleTopicWeeklyActivity.launch(context, this.target);
                return;
            default:
                SimpleWebActivity.launch(context, this.target);
                return;
        }
    }
}
