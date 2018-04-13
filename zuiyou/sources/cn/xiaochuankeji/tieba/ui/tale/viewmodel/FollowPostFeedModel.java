package cn.xiaochuankeji.tieba.ui.tale.viewmodel;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.json.tale.FollowPostFeedJson;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.tale.FollowPostAdapter;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class FollowPostFeedModel extends o {
    private FollowPostAdapter a;
    private cn.xiaochuankeji.tieba.api.tale.a b = new cn.xiaochuankeji.tieba.api.tale.a();
    private b c;
    private a d;
    private String e = "";

    public interface a {
        void b();

        void c();
    }

    public void a(b bVar, a aVar) {
        this.c = bVar;
        this.d = aVar;
    }

    public void a(FollowPostAdapter followPostAdapter) {
        this.a = followPostAdapter;
    }

    public void b() {
        d.b(new d$a<FollowPostFeedJson>(this) {
            final /* synthetic */ FollowPostFeedModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super FollowPostFeedJson> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.a.e()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    if (this.a.d != null) {
                        this.a.d.c();
                    }
                    this.a.c();
                    return;
                }
                FollowPostFeedJson followPostFeedJson = (FollowPostFeedJson) JSON.parseObject(a.toString(), FollowPostFeedJson.class);
                if (followPostFeedJson == null || followPostFeedJson.list == null) {
                    if (this.a.d != null) {
                        this.a.d.c();
                    }
                    this.a.c();
                    return;
                }
                this.a.e = followPostFeedJson.cursor;
                jVar.onNext(followPostFeedJson);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<FollowPostFeedJson>(this) {
            final /* synthetic */ FollowPostFeedModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FollowPostFeedJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(FollowPostFeedJson followPostFeedJson) {
                this.a.a.a(followPostFeedJson.list);
                if (this.a.d != null) {
                    this.a.d.b();
                }
            }
        });
    }

    private void a(final FollowPostFeedJson followPostFeedJson) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ FollowPostFeedModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.b.e()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                FollowPostFeedJson followPostFeedJson = (FollowPostFeedJson) JSON.parseObject(a.toString(), FollowPostFeedJson.class);
                if (followPostFeedJson == null) {
                    followPostFeedJson = new FollowPostFeedJson();
                }
                if (followPostFeedJson.list == null) {
                    followPostFeedJson.list = new ArrayList();
                }
                followPostFeedJson.list.addAll(followPostFeedJson.list);
                if (followPostFeedJson.list.size() > 40) {
                    followPostFeedJson.list = followPostFeedJson.list.subList(followPostFeedJson.list.size() - 40, followPostFeedJson.list.size());
                }
                followPostFeedJson.cursor = this.b.e;
                try {
                    cn.htjyb.c.a.b.a(new JSONObject(JSON.toJSONString(followPostFeedJson)), new File(this.b.e()), AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    private void b(final FollowPostFeedJson followPostFeedJson) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ FollowPostFeedModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.b.e()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                FollowPostFeedJson followPostFeedJson = (FollowPostFeedJson) JSON.parseObject(a.toString(), FollowPostFeedJson.class);
                if (followPostFeedJson == null) {
                    followPostFeedJson = new FollowPostFeedJson();
                }
                if (followPostFeedJson.list == null) {
                    followPostFeedJson.list = new ArrayList();
                }
                followPostFeedJson.list.clear();
                followPostFeedJson.list.addAll(followPostFeedJson.list);
                followPostFeedJson.cursor = this.b.e;
                try {
                    cn.htjyb.c.a.b.a(new JSONObject(JSON.toJSONString(followPostFeedJson)), new File(this.b.e()), AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    public void c() {
        this.b.a("index", 20, this.e).a(rx.a.b.a.a()).b(new j<FollowPostFeedJson>(this) {
            final /* synthetic */ FollowPostFeedModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FollowPostFeedJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.c != null) {
                    this.a.c.a(false, "网络不给力哦~", 0, true);
                }
            }

            public void a(FollowPostFeedJson followPostFeedJson) {
                if (followPostFeedJson != null && followPostFeedJson.list != null) {
                    if (followPostFeedJson.list.size() != 0) {
                        this.a.a.a(followPostFeedJson.list);
                        this.a.b(followPostFeedJson);
                    }
                    this.a.e = followPostFeedJson.cursor;
                    if (this.a.c != null) {
                        this.a.c.a(true, "", followPostFeedJson.list.size(), followPostFeedJson.more);
                    }
                }
            }
        });
    }

    public void d() {
        this.b.a("index", 20, this.e).a(rx.a.b.a.a()).b(new j<FollowPostFeedJson>(this) {
            final /* synthetic */ FollowPostFeedModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FollowPostFeedJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.c != null) {
                    this.a.c.a(false, "网络不给力哦~", true);
                }
            }

            public void a(FollowPostFeedJson followPostFeedJson) {
                if (followPostFeedJson != null && followPostFeedJson.list != null) {
                    this.a.e = followPostFeedJson.cursor;
                    this.a.a.b(followPostFeedJson.list);
                    if (this.a.c != null) {
                        this.a.c.a(true, "", followPostFeedJson.more);
                    }
                    this.a.a(followPostFeedJson);
                }
            }
        });
    }

    private String e() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "post_recommend_list_tale.dat";
    }
}
