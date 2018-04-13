package qsbk.app.model;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.database.QsbkDatabase;

public class QiushiTopicBanner extends QbBean {
    public static final int TYPE_AD = 3;
    public static final int TYPE_QIUSHI_DETAIL = 2;
    public static final int TYPE_QIUSHI_TOPIC = 1;
    public String id;
    public String imageUrl;
    public String target;
    public String title;
    public int type;

    public static QiushiTopicBanner paserJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        QiushiTopicBanner qiushiTopicBanner = new QiushiTopicBanner();
        qiushiTopicBanner.id = jSONObject.optString("id");
        qiushiTopicBanner.type = jSONObject.optInt("type");
        qiushiTopicBanner.target = jSONObject.optString(QsbkDatabase.TARGET);
        qiushiTopicBanner.title = jSONObject.optString("title");
        qiushiTopicBanner.imageUrl = jSONObject.optString("pic_url");
        return qiushiTopicBanner;
    }

    public void jumpTo(Context context) {
        switch (this.type) {
            case 1:
                try {
                    QiushiTopicActivity.Launch(context, new QiushiTopic(Integer.valueOf(this.target).intValue()));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                SingleArticle.launch(context, this.target);
                return;
            case 3:
                SimpleWebActivity.launch(context, this.target);
                return;
            default:
                return;
        }
    }

    public static List<QiushiTopicBanner> parseJsonArray(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        List<QiushiTopicBanner> arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    arrayList.add(paserJson(jSONObject));
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return arrayList;
            }
        }
        return arrayList;
    }
}
