package qsbk.app.widget.qiushitopic;

import android.support.v4.app.NotificationCompat;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.cache.FileCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.HttpClient;

public class QiushiTopicRecommendController {
    public static final int TYPE_LOAD_ALL = 2;
    public static final int TYPE_LOAD_CACHE = 3;
    public static final int TYPE_LOAD_NET = 1;
    public static final String UNIQUE_NAME = "qiushi_topic_recommend";

    public interface OnTopicRecommendLoadListener {
        void onTopicsLoad(List<QiushiTopic> list);
    }

    public static void load(OnTopicRecommendLoadListener onTopicRecommendLoadListener, int i) {
        load(onTopicRecommendLoadListener, i, Constants.QIUSHI_TOPIC_RECOMMEND);
    }

    public static void load(OnTopicRecommendLoadListener onTopicRecommendLoadListener, int i, String str) {
        new b(i, str, onTopicRecommendLoadListener).execute(new Void[0]);
    }

    public static List<QiushiTopic> loadFromCache() {
        try {
            JSONArray jSONArray = new JSONArray(FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), UNIQUE_NAME));
            if (jSONArray != null) {
                return QiushiTopic.jsonToArray(jSONArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<QiushiTopic> loadFromApi(String str) {
        try {
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(str));
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                if (optJSONArray != null) {
                    List<QiushiTopic> jsonToArray = QiushiTopic.jsonToArray(optJSONArray);
                    if (jsonToArray == null || jsonToArray.size() <= 0) {
                        return jsonToArray;
                    }
                    FileCache.cacheTextToDiskSync(QsbkApp.getInstance(), UNIQUE_NAME, optJSONArray.toString());
                    return jsonToArray;
                }
            }
            return null;
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
