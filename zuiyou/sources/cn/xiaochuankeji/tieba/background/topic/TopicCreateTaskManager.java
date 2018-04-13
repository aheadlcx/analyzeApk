package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.c.b.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.g;
import cn.xiaochuankeji.tieba.background.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicCreateTaskManager {
    private static final int kTopicAlreadyExist = -40;
    private static TopicCreateTaskManager sInstance;
    private d mCreateTopicTask;
    private OnTopicCreateListener mListener;
    private onTopicModifyListener mModifyListener;
    private d mModifyTopicTask;

    public interface OnTopicCreateListener {
        void onTopicCreateFailure(String str);

        void onTopicCreateSuccess(Topic topic, boolean z);
    }

    public interface onTopicModifyListener {
        void onTopicModifySuccess();

        void onTopidModifyFailure(String str);
    }

    private TopicCreateTaskManager() {
    }

    public static synchronized TopicCreateTaskManager getInstance() {
        TopicCreateTaskManager topicCreateTaskManager;
        synchronized (TopicCreateTaskManager.class) {
            if (sInstance == null) {
                sInstance = new TopicCreateTaskManager();
            }
            topicCreateTaskManager = sInstance;
        }
        return topicCreateTaskManager;
    }

    public void setOnTopicCreateListener(OnTopicCreateListener onTopicCreateListener) {
        this.mListener = onTopicCreateListener;
    }

    public void setOnTopicModifyListener(onTopicModifyListener ontopicmodifylistener) {
        this.mModifyListener = ontopicmodifylistener;
    }

    public void createTopic(String str, String str2, String str3, String str4) {
        if (this.mCreateTopicTask == null) {
            Collection collection = null;
            if (str3 != null) {
                File file = new File(str3);
                if (file.exists()) {
                    File file2 = new File(a.e().q());
                    if (!b.a(file, file2, 80, 1200)) {
                        file2 = file;
                    }
                    collection = new ArrayList();
                    collection.add(new cn.htjyb.netlib.b.b(file2, "cover"));
                } else {
                    this.mListener.onTopicCreateFailure("请先选择话题封面");
                    return;
                }
            }
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put("token", a.g().a());
                jSONObject.put("topic", str);
                jSONObject.put("brief", str2);
                jSONObject.put("atts_title", str4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mCreateTopicTask = new g(cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/create_v2"), a.d(), collection, jSONObject, new d.a() {
                public void onTaskFinish(d dVar) {
                    Topic topic = null;
                    TopicCreateTaskManager.this.mCreateTopicTask = null;
                    if (dVar.c.a) {
                        Topic topic2;
                        try {
                            topic2 = new Topic(dVar.c.c.getJSONObject("topic"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            topic2 = null;
                        }
                        if (TopicCreateTaskManager.this.mListener != null && topic2 != null) {
                            TopicCreateTaskManager.this.mListener.onTopicCreateSuccess(topic2, false);
                        }
                    } else if (TopicCreateTaskManager.this.mListener == null) {
                    } else {
                        if (dVar.c.b == TopicCreateTaskManager.kTopicAlreadyExist) {
                            try {
                                try {
                                    topic = new Topic(new JSONObject(dVar.c.d).getJSONObject("topic"));
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                                cn.xiaochuankeji.tieba.background.utils.g.b(dVar.c.c());
                                if (topic != null) {
                                    TopicCreateTaskManager.this.mListener.onTopicCreateSuccess(topic, true);
                                    return;
                                }
                                return;
                            } catch (JSONException e22) {
                                e22.printStackTrace();
                                return;
                            }
                        }
                        TopicCreateTaskManager.this.mListener.onTopicCreateFailure(dVar.c.c());
                    }
                }
            }).b();
        }
    }

    public void modifyTopic(long j, String str, String str2, String str3) {
        if (this.mModifyTopicTask == null) {
            Collection collection = null;
            if (str2 != null && cn.htjyb.c.a.b.c(str2)) {
                File file = new File(str2);
                File file2 = new File(a.e().q());
                if (!b.a(file, file2, 80, 1200)) {
                    file2 = file;
                }
                collection = new ArrayList();
                collection.add(new cn.htjyb.netlib.b.b(file2, "cover"));
            }
            JSONObject jSONObject = new JSONObject();
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            try {
                jSONObject.put("token", a.g().a());
                jSONObject.put("tid", j);
                jSONObject.put("brief", str);
                jSONObject.put("atts_title", str3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mModifyTopicTask = new g(cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/edit"), a.d(), collection, jSONObject, new d.a() {
                public void onTaskFinish(d dVar) {
                    TopicCreateTaskManager.this.mModifyTopicTask = null;
                    if (dVar.c.a) {
                        if (TopicCreateTaskManager.this.mModifyListener != null) {
                            TopicCreateTaskManager.this.mModifyListener.onTopicModifySuccess();
                        }
                    } else if (TopicCreateTaskManager.this.mModifyListener != null) {
                        TopicCreateTaskManager.this.mModifyListener.onTopidModifyFailure(dVar.c.c());
                    }
                }
            }).b();
        }
    }
}
