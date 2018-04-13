package cn.xiaochuankeji.tieba.background.data.post;

import cn.xiaochuankeji.tieba.background.topic.Topic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendedTopics extends AbstractPost implements Serializable {
    private static final long serialVersionUID = 2880458787735602223L;
    public ArrayList<Topic> topics = new ArrayList();

    public RecommendedTopics(JSONObject jSONObject) {
        parseBaseInfo(jSONObject);
    }

    public int classType() {
        return 1;
    }

    public long getMemberId() {
        return 0;
    }

    public void setFollowStatus(int i) {
    }

    public void parseBaseInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            super.parseBaseInfo(jSONObject);
            JSONArray optJSONArray = jSONObject.optJSONArray("topics");
            this.topics.clear();
            int i = 0;
            while (optJSONArray != null && i < optJSONArray.length()) {
                this.topics.add(new Topic(optJSONArray.optJSONObject(i)));
                i++;
            }
        }
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject serializeTo = super.serializeTo();
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.topics.iterator();
        while (it.hasNext()) {
            jSONArray.put(((Topic) it.next()).serializeTo());
        }
        serializeTo.put("topics", jSONArray);
        return serializeTo;
    }
}
