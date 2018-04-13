package cn.xiaochuankeji.tieba.background.topic;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectTopicCacheInActivityCycle {
    private static SelectTopicCacheInActivityCycle instance;
    private HashMap<Long, TopicCacheData> topicCacheMap = new HashMap();

    public class TopicCacheData {
        public boolean more;
        public long offset;
        public ArrayList<Topic> topics = new ArrayList();
    }

    private SelectTopicCacheInActivityCycle() {
    }

    public static SelectTopicCacheInActivityCycle getInstance() {
        if (instance == null) {
            instance = new SelectTopicCacheInActivityCycle();
        }
        return instance;
    }

    public void save(long j, ArrayList<Topic> arrayList, boolean z, long j2) {
        if (((TopicCacheData) this.topicCacheMap.get(Long.valueOf(j))) != null) {
            this.topicCacheMap.remove(Long.valueOf(j));
        }
        TopicCacheData topicCacheData = new TopicCacheData();
        topicCacheData.topics.addAll(arrayList);
        topicCacheData.more = z;
        topicCacheData.offset = j2;
        this.topicCacheMap.put(Long.valueOf(j), topicCacheData);
    }

    public TopicCacheData getTopicListBy(long j) {
        return (TopicCacheData) this.topicCacheMap.get(Long.valueOf(j));
    }

    public void clear() {
        this.topicCacheMap.clear();
    }
}
