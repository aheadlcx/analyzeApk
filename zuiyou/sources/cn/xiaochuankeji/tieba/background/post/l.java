package cn.xiaochuankeji.tieba.background.post;

import android.text.TextUtils;
import android.util.Log;
import cn.htjyb.netlib.d;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.upload.b;
import cn.xiaochuankeji.tieba.background.upload.f;
import cn.xiaochuankeji.tieba.background.upload.g.c;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l {
    public long a;
    private String b;
    private String c = "other";
    private ArrayList<LocalMedia> d;
    private ArrayList<String> e;
    private String f;
    private String g;
    private int h;
    private String i;
    private JSONObject j;
    private a k;
    private d l;
    private final ArrayList<c> m = new ArrayList();
    private b n;
    private j o;

    public interface a {
        void a(boolean z, String str, Post post);
    }

    public void a(String str, ArrayList<LocalMedia> arrayList, ArrayList<String> arrayList2, Topic topic, String str2, String str3, int i, JSONObject jSONObject, String str4, a aVar, b bVar, j jVar) {
        if (aVar == null) {
            com.izuiyou.a.a.b.e("参数错误");
            return;
        }
        this.o = jVar;
        this.c = str4;
        this.h = i;
        this.j = jSONObject;
        this.b = str;
        if (arrayList == null || arrayList.size() < 0) {
            Log.i("PublishPostActivity", "没有选择图片");
        } else {
            this.d = (ArrayList) arrayList.clone();
        }
        this.e = arrayList2;
        this.f = str3;
        this.a = topic._topicID;
        this.g = str2;
        this.k = aVar;
        this.n = bVar;
        b();
    }

    public void a() {
        if (this.l != null) {
            this.l.c();
            this.l = null;
        }
        if (this.o != null) {
            this.o.a();
        }
    }

    private void b() {
        if (this.d == null || this.d.size() == 0) {
            c();
        } else {
            this.o.a(this.d, "", new b(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void a(long j, long j2, int i) {
                    if (this.a.n != null) {
                        this.a.n.a(j, j2, i);
                    }
                }
            }, new f(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    JSONArray jSONArray = new JSONArray();
                    JSONArray jSONArray2 = new JSONArray();
                    for (Long longValue : list) {
                        jSONArray2.put(longValue.longValue());
                    }
                    for (Long longValue2 : list2) {
                        jSONArray.put(longValue2.longValue());
                    }
                    this.a.a(jSONArray, jSONArray2);
                }

                public void a(String str) {
                    if (TextUtils.isEmpty(str) || !str.contains("上传视频不能超过15分钟")) {
                        this.a.k.a(false, "上传失败", null);
                    } else {
                        this.a.k.a(false, "上传视频不能超过15分钟", null);
                    }
                }
            });
        }
    }

    private void c() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("tid", this.a);
            jSONObject.put("content", this.b);
            jSONObject.put("from", this.c);
            jSONObject.put("localid", this.g);
            if (-1 != this.h) {
                jSONObject.put("pageres", this.j);
            }
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            Iterator it = this.m.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                jSONArray.put(cVar.b);
                if (1 == cVar.c) {
                    jSONArray2.put(cVar.b);
                }
            }
            jSONObject.put("imgs", jSONArray);
            if (jSONArray2.length() != 0) {
                jSONObject.put("videos", jSONArray2);
            }
            if (!(this.e == null || this.e.isEmpty())) {
                jSONArray = new JSONArray();
                Iterator it2 = this.e.iterator();
                while (it2.hasNext()) {
                    jSONArray.put((String) it2.next());
                }
                jSONObject.put("vote", jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.l = new cn.htjyb.netlib.f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/create"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                this.a.l = null;
                if (dVar.c.a) {
                    this.a.k.a(true, null, new Post(dVar.c.c.optJSONObject("post")));
                    return;
                }
                this.a.k.a(false, dVar.c.c(), null);
            }
        }).b();
    }

    private void a(JSONArray jSONArray, JSONArray jSONArray2) {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("tid", this.a);
            jSONObject.put("content", this.b);
            jSONObject.put("from", this.c);
            jSONObject.put("localid", this.g);
            if (-1 != this.h) {
                jSONObject.put("pageres", this.j);
            }
            if (jSONArray.length() != 0) {
                jSONObject.put("imgs", jSONArray);
            }
            if (jSONArray2.length() != 0) {
                jSONObject.put("videos", jSONArray2);
            }
            jSONObject.put("campaign_id", this.i);
            if (!(this.e == null || this.e.isEmpty())) {
                JSONArray jSONArray3 = new JSONArray();
                Iterator it = this.e.iterator();
                while (it.hasNext()) {
                    jSONArray3.put((String) it.next());
                }
                jSONObject.put("vote", jSONArray3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.l = new cn.htjyb.netlib.f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/create"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                this.a.l = null;
                if (dVar.c.a) {
                    this.a.k.a(true, null, new Post(dVar.c.c.optJSONObject("post")));
                    return;
                }
                this.a.k.a(false, dVar.c.c(), null);
            }
        }).b();
    }
}
