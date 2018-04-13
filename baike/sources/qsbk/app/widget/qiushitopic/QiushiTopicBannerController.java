package qsbk.app.widget.qiushitopic;

import android.support.v4.app.NotificationCompat;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.cache.FileCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.utils.HttpClient;

public class QiushiTopicBannerController {
    public static final int TYPE_LOAD_CACHE = 2;
    public static final int TYPE_LOAD_NET = 1;
    public static final String UNIQUE_NAME = "qiushi_topic_banners";

    public interface OnTopicBannerLoadListener {
        void onTopicBannerLoad(List<QiushiTopicBanner> list);
    }

    public static void load(OnTopicBannerLoadListener onTopicBannerLoadListener, int i) {
        load(onTopicBannerLoadListener, i, Constants.QIUSHI_TOPIC_BANNER);
    }

    public static void load(OnTopicBannerLoadListener onTopicBannerLoadListener, int i, String str) {
        new a(i, str, onTopicBannerLoadListener).execute(new Void[0]);
    }

    public static List<QiushiTopicBanner> loadFromCache() {
        try {
            return QiushiTopicBanner.parseJsonArray(new JSONArray(FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), UNIQUE_NAME)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<QiushiTopicBanner> loadFromApi(String str) {
        try {
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(str));
            if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("banners");
                FileCache.cacheTextToDiskSync(QsbkApp.getInstance(), UNIQUE_NAME, optJSONArray.toString());
                return QiushiTopicBanner.parseJsonArray(optJSONArray);
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }
}
