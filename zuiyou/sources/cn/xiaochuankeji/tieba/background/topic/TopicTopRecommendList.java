package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.topic.TopicQueryList.TopicType;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicTopRecommendList extends TopicQueryList {
    private static final String kCacheFileName = "TopicTopRecommendList.dat";

    public TopicTopRecommendList() {
        super(TopicType.kRecommend);
        loadCache();
    }

    private File getCacheFile() {
        return new File(a.e().r() + kCacheFileName);
    }

    private void loadCache() {
        JSONObject a = b.a(getCacheFile(), AppController.kDataCacheCharsetUTF8.name());
        if (a != null) {
            super.handleQuerySuccResult(a);
        }
    }

    public void saveCache(JSONObject jSONObject) {
        b.a(jSONObject, getCacheFile(), AppController.kDataCacheCharsetUTF8.name());
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        jSONObject.put("onlytop", 1);
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        saveCache(jSONObject);
    }
}
