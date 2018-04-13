package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.netlib.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.c.e;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendTopicInitModel {
    private static RecommendTopicInitModel instance;
    private static String nextListCb;
    private long currentCategoryId;
    private f mPostTask;
    private e mRecommendTopicCache = e.a();
    private int more;
    private long offset;
    private int showCount;
    private int showType;
    private ArrayList<Category> topicCategorys = new ArrayList();
    private ArrayList<Topic> topicList = new ArrayList();

    public interface CallBack {
        void queryFinish(boolean z, String str);
    }

    private RecommendTopicInitModel() {
    }

    public static RecommendTopicInitModel getInstance() {
        if (instance == null) {
            instance = new RecommendTopicInitModel();
        }
        return instance;
    }

    public boolean hasData() {
        return this.topicCategorys.size() > 0 && this.topicList.size() > 0;
    }

    public void query(final CallBack callBack) {
        if (this.mPostTask == null) {
            this.topicCategorys.clear();
            this.topicList.clear();
            String b = a.b("/topic/recommend_home");
            JSONObject jSONObject = new JSONObject();
            a.a(jSONObject);
            this.mPostTask = new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new d.a() {
                public void onTaskFinish(d dVar) {
                    int i = 0;
                    RecommendTopicInitModel.this.mPostTask = null;
                    b.a aVar = dVar.c;
                    if (aVar.a) {
                        try {
                            RecommendTopicInitModel.this.currentCategoryId = aVar.c.optLong("cid");
                            RecommendTopicInitModel.this.more = aVar.c.optInt("more");
                            RecommendTopicInitModel.this.offset = aVar.c.optLong("offset");
                            RecommendTopicInitModel.this.showCount = aVar.c.optInt("show_count");
                            RecommendTopicInitModel.this.showType = aVar.c.optInt("show_type");
                            RecommendTopicInitModel.nextListCb = aVar.c.optString("next_list_cb", "");
                            JSONArray optJSONArray = aVar.c.optJSONArray("categorie_list");
                            int i2 = 0;
                            while (optJSONArray != null && i2 < optJSONArray.length()) {
                                JSONObject jSONObject = optJSONArray.getJSONObject(i2);
                                if (jSONObject != null) {
                                    RecommendTopicInitModel.this.topicCategorys.add(new Category(jSONObject.optLong("cid"), jSONObject.optString("name")));
                                }
                                i2++;
                            }
                            JSONArray optJSONArray2 = aVar.c.optJSONArray("list");
                            while (optJSONArray2 != null && i < optJSONArray2.length()) {
                                JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                                if (optJSONObject != null) {
                                    Topic topic = new Topic(optJSONObject);
                                    if (topic != null) {
                                        RecommendTopicInitModel.this.topicList.add(topic);
                                    }
                                }
                                i++;
                            }
                            RecommendTopicInitModel.this.saveTopicToCache();
                            RecommendTopicInitModel.this.topicCategorys.add(0, new Category(-1, "关注"));
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

    private void saveTopicToCache() {
        boolean z = true;
        e eVar = this.mRecommendTopicCache;
        long j = this.currentCategoryId;
        ArrayList arrayList = (ArrayList) this.topicList.clone();
        if (this.more != 1) {
            z = false;
        }
        eVar.a(j, arrayList, z, this.offset);
    }

    public long getCurrentCategoryId() {
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

    public int getShowCount() {
        return this.showCount;
    }

    public int getShowType() {
        return this.showType;
    }

    public static String getNextListCb() {
        return nextListCb;
    }
}
