package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.b.a.e;
import cn.htjyb.netlib.b;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONObject;

public class TopicFollowModel extends e<Topic> {
    private static TopicFollowModel sInstance;

    public static TopicFollowModel getInstance() {
        if (sInstance == null) {
            sInstance = new TopicFollowModel();
        }
        return sInstance;
    }

    private TopicFollowModel() {
    }

    protected b getHttpEngine() {
        return a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/attention_list");
    }

    protected Topic parseItem(JSONObject jSONObject) {
        return new Topic(jSONObject);
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
    }

    public void toggleTop(long j) {
        Topic topicBy = getTopicBy(j);
        if (topicBy != null) {
            if (topicBy._topTime > 0) {
                unTopAction(j);
            } else {
                topAction(j);
            }
        }
    }

    private void topAction(final long j) {
        new TopicTopActionRequest(j, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>() {
            public void onResponse(JSONObject jSONObject, Object obj) {
                TopicFollowModel.this.updateTopic(j, jSONObject.optLong("stamp"));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a() {
            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void unTopAction(long j) {
        new TopicUntopActionRequest(j, Long.valueOf(j), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>() {
            public void onResponse(JSONObject jSONObject, Object obj) {
                TopicFollowModel.this.updateTopic(((Long) obj).longValue(), 0);
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a() {
            public void onErrorResponse(XCError xCError, Object obj) {
            }
        }).execute();
    }

    private void updateTopic(long j, long j2) {
        Topic topicBy = getTopicBy(j);
        if (topicBy != null) {
            topicBy._topTime = j2;
            notifyQueryFinish(true, "");
        }
    }

    private Topic getTopicBy(long j) {
        for (int i = 0; i < itemCount(); i++) {
            if (((Topic) itemAt(i))._topicID == j) {
                return (Topic) itemAt(i);
            }
        }
        return null;
    }

    public void unFollow(long j) {
        Topic topicBy = getTopicBy(j);
        this._items.remove(topicBy);
        notifyQueryFinish(true, "");
        TopicUtilityClass.asynchSendFollowRequest(j, false, "topiclist", topicBy == null ? null : topicBy.click_cb);
    }

    public void sort() {
        Object arrayList = new ArrayList();
        Object arrayList2 = new ArrayList();
        for (int i = 0; i < itemCount(); i++) {
            if (((Topic) itemAt(i))._topTime > 0) {
                arrayList2.add(itemAt(i));
            } else {
                arrayList.add(itemAt(i));
            }
        }
        Collections.sort(arrayList2, new Comparator<Topic>() {
            public int compare(Topic topic, Topic topic2) {
                if (topic._topTime > topic2._topTime) {
                    return -1;
                }
                if (topic._topTime < topic2._topTime) {
                    return 1;
                }
                return 0;
            }
        });
        this._items.clear();
        this._items.addAll(arrayList2);
        this._items.addAll(arrayList);
        arrayList2.clear();
        arrayList.clear();
    }
}
