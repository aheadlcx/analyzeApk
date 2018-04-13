package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.a;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicHistoryRecordManager extends a<Topic> {
    private static TopicHistoryRecordManager searchInstance;
    private static TopicHistoryRecordManager selectInstance;
    private ArrayList<Topic> _items = new ArrayList();
    Type _type;
    private final String kCacheFilePrefix = "data_topic_history_record.";
    private final int kMaxItemCount = 10;
    private final String kNewCacheFilePrefix = "data_topic_history_record_new.";

    public enum Type {
        kSearch,
        kSelect
    }

    private TopicHistoryRecordManager(Type type) {
        this._type = type;
        loadFromCache();
    }

    public static TopicHistoryRecordManager getInstance(Type type) {
        if (Type.kSearch == type) {
            if (searchInstance == null) {
                searchInstance = new TopicHistoryRecordManager(type);
            }
            return searchInstance;
        } else if (Type.kSelect != type) {
            return null;
        } else {
            if (selectInstance == null) {
                selectInstance = new TopicHistoryRecordManager(type);
            }
            return selectInstance;
        }
    }

    private void loadFromCache() {
        JSONArray optJSONArray;
        JSONObject a = b.a(getCacheFile(), AppController.kDataCacheCharset);
        JSONObject a2 = b.a(getNewCacheFile(), AppController.kDataCacheCharsetUTF8.name());
        if (a2 != null) {
            optJSONArray = a2.optJSONArray("list");
        } else if (a != null) {
            optJSONArray = a.optJSONArray("list");
        } else {
            return;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            this._items.add(new Topic(optJSONArray.optJSONObject(i)));
        }
    }

    public void saveCache() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator it = this._items.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Topic) it.next()).serializeTo());
            }
            jSONObject.put("list", jSONArray);
            b.a(jSONObject, getNewCacheFile(), AppController.kDataCacheCharsetUTF8.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private File getCacheFile() {
        return new File(cn.xiaochuankeji.tieba.background.a.e().r() + "data_topic_history_record." + this._type.name());
    }

    private File getNewCacheFile() {
        return new File(cn.xiaochuankeji.tieba.background.a.e().r() + "data_topic_history_record_new." + this._type.name());
    }

    public int itemCount() {
        return this._items.size();
    }

    public Topic itemAt(int i) {
        if (i >= this._items.size()) {
            return null;
        }
        return (Topic) this._items.get(i);
    }

    public void insert(Topic topic) {
        Iterator it = this._items.iterator();
        while (it.hasNext()) {
            Topic topic2 = (Topic) it.next();
            if (topic2._topicID == topic._topicID) {
                this._items.remove(topic2);
                break;
            }
        }
        this._items.add(0, topic);
        if (itemCount() >= 10) {
            for (int i = 0; i < (itemCount() - 10) + 1; i++) {
                this._items.remove(itemCount() - 1);
            }
        }
        notifyListUpdate();
        saveCache();
    }

    public ArrayList<Topic> getTopics() {
        return this._items;
    }

    public void clear() {
        this._items.clear();
        notifyListUpdate();
        saveCache();
    }
}
