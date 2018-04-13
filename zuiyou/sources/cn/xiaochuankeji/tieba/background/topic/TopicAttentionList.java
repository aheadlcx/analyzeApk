package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.topic.TopicQueryList.TopicType;
import java.io.File;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicAttentionList extends TopicQueryList {
    private static final String kCacheFileName = "TopicAttentionList.dat";
    private static final String kNewCacheFileName = "TopicAttentionList_new.dat";

    public TopicAttentionList() {
        super(TopicType.kAttention);
        loadCache();
    }

    private File getCacheFile() {
        return new File(a.e().r() + kCacheFileName);
    }

    private File getNewCacheFile() {
        return new File(a.e().r() + kNewCacheFileName);
    }

    private void loadCache() {
        JSONObject a = b.a(getCacheFile(), AppController.kDataCacheCharset);
        JSONObject a2 = b.a(getNewCacheFile(), AppController.kDataCacheCharsetUTF8.name());
        if (a2 != null) {
            super.handleQuerySuccResult(a2);
        } else if (a != null) {
            super.handleQuerySuccResult(a);
        }
    }

    public void saveCache() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("total", this._total);
            JSONArray jSONArray = new JSONArray();
            Iterator it = this._items.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Topic) it.next()).serializeTo());
            }
            jSONObject.put("list", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONObject == null) {
            getCacheFile().delete();
        } else {
            b.a(jSONObject, getNewCacheFile(), AppController.kDataCacheCharsetUTF8.name());
        }
    }

    public void clear() {
        super.clear();
        saveCache();
    }

    public void remove(Topic topic) {
        this._items.remove(topic);
        notifyListUpdate();
        saveCache();
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        super.handleQuerySuccResult(jSONObject);
        saveCache();
    }
}
