package cn.xiaochuankeji.tieba.ui.hollow.recommend;

import android.arch.lifecycle.o;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.api.hollow.a;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.hollow.HollowListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import cn.xiaochuankeji.tieba.ui.recommend.RecommendViewModel;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class TreeHoleViewModel extends o {
    private a a = new a();
    private String b = "";
    private i c;
    private j d;

    public void a(i iVar, j jVar) {
        this.c = iVar;
        this.d = jVar;
    }

    public void a(final RecommendViewModel.a aVar) {
        d.b(new d$a<HollowListJson>(this) {
            final /* synthetic */ TreeHoleViewModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super HollowListJson> jVar) {
                JSONObject a = b.a(new File(TreeHoleViewModel.d()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    aVar.a();
                    jVar.onCompleted();
                    return;
                }
                HollowListJson hollowListJson = (HollowListJson) JSON.parseObject(a.toString(), HollowListJson.class);
                if (hollowListJson != null && hollowListJson.hollowList != null && hollowListJson.hollowList.size() != 0) {
                    jVar.onNext(hollowListJson);
                } else if (aVar != null) {
                    aVar.a();
                    jVar.onCompleted();
                }
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<HollowListJson>(this) {
            final /* synthetic */ TreeHoleViewModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((HollowListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(HollowListJson hollowListJson) {
                if (hollowListJson.hollowList != null) {
                    this.b.c.a(hollowListJson.hollowList);
                    aVar.b();
                }
            }
        });
    }

    public void b() {
        if (this.a != null) {
            this.a.a("down", this.b).a(rx.a.b.a.a()).b(new j<HollowListJson>(this) {
                final /* synthetic */ TreeHoleViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((HollowListJson) obj);
                }

                public void onCompleted() {
                    this.a.d.i();
                }

                public void onError(Throwable th) {
                    if (th instanceof ClientErrorException) {
                        g.a(th.getMessage());
                    } else {
                        g.a("网络错误");
                    }
                    this.a.d.i();
                }

                public void a(HollowListJson hollowListJson) {
                    this.a.c.c();
                    this.a.e();
                    if (hollowListJson == null || hollowListJson.hollowList == null || hollowListJson.hollowList.size() == 0) {
                        this.a.d.a("暂无推荐，发条树洞呗");
                        return;
                    }
                    this.a.b = hollowListJson.nextCb;
                    if (hollowListJson.hollowList != null) {
                        this.a.c.a(hollowListJson.hollowList);
                        this.a.a(true, hollowListJson);
                        this.a.d.a("快看，有" + hollowListJson.hollowList.size() + "个新树洞");
                    }
                }
            });
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.a("up", this.b).a(rx.a.b.a.a()).b(new j<HollowListJson>(this) {
                final /* synthetic */ TreeHoleViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((HollowListJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if (th instanceof ClientErrorException) {
                        g.a(th.getMessage());
                    } else {
                        g.a("网络错误");
                    }
                    this.a.d.c(true);
                }

                public void a(HollowListJson hollowListJson) {
                    if (hollowListJson == null || hollowListJson.hollowList == null || hollowListJson.hollowList.size() == 0) {
                        this.a.d.c(false);
                        return;
                    }
                    this.a.b = hollowListJson.nextCb;
                    if (hollowListJson.hollowList != null) {
                        this.a.c.b(hollowListJson.hollowList);
                        this.a.a(false, hollowListJson);
                        this.a.d.c(true);
                        return;
                    }
                    this.a.d.c(false);
                }
            });
        }
    }

    private void e() {
        File file = new File(d());
        if (file.exists()) {
            try {
                org.apache.commons.io.b.d(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(final boolean z, final HollowListJson hollowListJson) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ TreeHoleViewModel c;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                this.c.e();
                if (z) {
                    try {
                        b.a(new JSONObject(JSON.toJSONString(hollowListJson)), new File(TreeHoleViewModel.d()), AppController.kDataCacheCharsetUTF8.name());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                HollowListJson hollowListJson = new HollowListJson();
                if (this.c.c != null) {
                    List b = this.c.c.b();
                    if (b.size() > 40) {
                        hollowListJson.hollowList = b.subList(0, 40);
                    } else {
                        hollowListJson.hollowList = b;
                    }
                }
                hollowListJson.nextCb = this.c.b;
                try {
                    b.a(new JSONObject(JSON.toJSONString(hollowListJson)), new File(TreeHoleViewModel.d()), AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    public static String d() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + "post_recommend_list_hollow.dat";
    }

    public static void a(final HollowRecommendItemBean hollowRecommendItemBean) {
        d.b(new d$a<Object>() {
            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                JSONObject a = b.a(new File(TreeHoleViewModel.d()), AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                HollowListJson hollowListJson = (HollowListJson) JSON.parseObject(a.toString(), HollowListJson.class);
                if (hollowListJson == null) {
                    hollowListJson = new HollowListJson();
                }
                if (hollowListJson.hollowList == null) {
                    hollowListJson.hollowList = new LinkedList();
                }
                hollowListJson.hollowList.add(0, hollowRecommendItemBean);
                if (hollowListJson.hollowList.size() > 40) {
                    hollowListJson.hollowList = hollowListJson.hollowList.subList(0, 40);
                }
                try {
                    b.a(new JSONObject(JSON.toJSONString(hollowListJson)), new File(TreeHoleViewModel.d()), AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    public void b(HollowRecommendItemBean hollowRecommendItemBean) {
        if (hollowRecommendItemBean != null && hollowRecommendItemBean.id >= 0) {
            this.c.b(hollowRecommendItemBean);
            a(false, null);
        }
    }
}
