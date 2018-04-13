package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.netlib.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendTopicInitDataInPublishModel {
    private static RecommendTopicInitDataInPublishModel instance;
    private long currentCategoryId;
    private f mPostTask;
    private int more;
    private long offset;
    private ArrayList<Category> topicCategorys = new ArrayList();
    private ArrayList<Topic> topicList = new ArrayList();

    public interface CallBack {
        void queryFinish(boolean z, String str);
    }

    private RecommendTopicInitDataInPublishModel() {
    }

    public static RecommendTopicInitDataInPublishModel getInstance() {
        if (instance == null) {
            instance = new RecommendTopicInitDataInPublishModel();
        }
        return instance;
    }

    public void query(final CallBack callBack) {
        if (this.mPostTask == null) {
            this.topicCategorys.clear();
            this.topicList.clear();
            String b = a.b("/topic/get_mcategories");
            JSONObject jSONObject = new JSONObject();
            a.a(jSONObject);
            this.mPostTask = new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new d.a() {
                public void onTaskFinish(d dVar) {
                    int i = 0;
                    RecommendTopicInitDataInPublishModel.this.mPostTask = null;
                    b.a aVar = dVar.c;
                    if (aVar.a) {
                        try {
                            RecommendTopicInitDataInPublishModel.this.currentCategoryId = aVar.c.optLong("mcid");
                            RecommendTopicInitDataInPublishModel.this.more = aVar.c.optInt("more");
                            RecommendTopicInitDataInPublishModel.this.offset = aVar.c.optLong("offset");
                            JSONArray optJSONArray = aVar.c.optJSONArray("categorie_list");
                            int i2 = 0;
                            while (optJSONArray != null && i2 < optJSONArray.length()) {
                                JSONObject jSONObject = optJSONArray.getJSONObject(i2);
                                if (jSONObject != null) {
                                    RecommendTopicInitDataInPublishModel.this.topicCategorys.add(new Category(jSONObject.optLong("mcid"), jSONObject.optString("name")));
                                }
                                i2++;
                            }
                            JSONArray optJSONArray2 = aVar.c.optJSONArray("list");
                            while (optJSONArray2 != null && i < optJSONArray2.length()) {
                                JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                                if (optJSONObject != null) {
                                    Topic topic = new Topic(optJSONObject);
                                    if (topic != null) {
                                        RecommendTopicInitDataInPublishModel.this.topicList.add(topic);
                                    }
                                }
                                i++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.queryFinish(aVar.a, dVar.c.c());
                }
            });
            this.mPostTask.b();
        }
    }

    public long getCurrentCId() {
        return this.currentCategoryId;
    }

    public int getMore() {
        return this.more;
    }

    public long getOffset() {
        return this.offset;
    }

    public ArrayList<Category> getTopicCategorys() {
        return this.topicCategorys;
    }

    public ArrayList<Topic> getTopicList() {
        return this.topicList;
    }
}
