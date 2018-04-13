package cn.xiaochuankeji.tieba.ui.homepage.feed.model;

import android.arch.lifecycle.o;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.post.FeedService;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.json.feed.FeedListJson;
import cn.xiaochuankeji.tieba.json.feed.FeedMemberListJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.b;
import rx.b.g;
import rx.d;
import rx.e;

public class FeedViewModel extends o {
    public static boolean a = true;
    private static int b = 0;
    private a c;
    private long d = 0;
    private long e = 0;
    private AtomicBoolean f = new AtomicBoolean(false);
    private NavigatorTag g;

    public void a(a aVar) {
        this.c = aVar;
    }

    private File g() {
        Object obj = a.e().r() + "post_feed.dat";
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        File file = new File(obj);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    @MainThread
    public void b() {
        d.a(Boolean.valueOf(true)).d(new g<Boolean, FeedListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public FeedListJson a(Boolean bool) {
                FeedListJson feedListJson = null;
                File b = this.a.g();
                if (b != null && b.exists()) {
                    try {
                        feedListJson = (FeedListJson) JSON.parseObject(b.a(b, AppController.kDataCacheCharsetUTF8), FeedListJson.class);
                        if (!(feedListJson.list == null || feedListJson.list.isEmpty())) {
                            for (RecPostDataBean recPostDataBean : feedListJson.list) {
                                recPostDataBean.oldPostData = RecPostDataBean.getOriginPostData(recPostDataBean);
                            }
                        }
                        this.a.d = Math.max(feedListJson.downOffset, 0);
                        this.a.e = Math.max(feedListJson.upOffset, 0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return feedListJson;
            }
        }).b(rx.f.a.c()).a(rx.f.a.c()).a(new e<FeedListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FeedListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.c != null) {
                    this.a.c.a(null);
                }
            }

            public void a(FeedListJson feedListJson) {
                if (this.a.c != null) {
                    this.a.c.a(feedListJson);
                }
            }
        });
    }

    @WorkerThread
    private void h() {
        File g = g();
        if (g != null && g.exists()) {
            try {
                b.d(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @UiThread
    public void a(final List<RecPostDataBean> list) {
        a.p().b().execute(new Runnable(this) {
            final /* synthetic */ FeedViewModel b;

            public void run() {
                if (list != null && !list.isEmpty()) {
                    File b = this.b.g();
                    if (b == null) {
                        b = new File(a.e().r() + "post_feed.dat");
                    }
                    int min = Math.min(list.size(), 200);
                    FeedListJson feedListJson = new FeedListJson();
                    feedListJson.downOffset = this.b.d;
                    feedListJson.upOffset = this.b.e;
                    feedListJson.list = list.subList(0, min);
                    try {
                        b.a(b, JSON.toJSONString(feedListJson), AppController.kDataCacheCharsetUTF8);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void a(@Nullable final String str, final boolean z) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(a.g().c()));
        jSONObject.put("direction", str);
        jSONObject.put("up_offset", Long.valueOf(this.e));
        jSONObject.put("down_offset", Long.valueOf(this.d));
        jSONObject.put("auto", Integer.valueOf(z ? 1 : 0));
        JSONArray jSONArray = new JSONArray();
        jSONArray.add(Integer.valueOf(1));
        jSONObject.put("c_types", jSONArray);
        ((FeedService) c.b(FeedService.class)).feedList(jSONObject).d(new g<FeedListJson, FeedListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((FeedListJson) obj);
            }

            public FeedListJson a(FeedListJson feedListJson) {
                if (!(feedListJson.list == null || feedListJson.list.isEmpty())) {
                    Iterator it = feedListJson.list.iterator();
                    while (it.hasNext()) {
                        RecPostDataBean recPostDataBean = (RecPostDataBean) it.next();
                        if (recPostDataBean.c_type != 1) {
                            it.remove();
                        }
                        recPostDataBean.oldPostData = RecPostDataBean.getOriginPostData(recPostDataBean);
                    }
                }
                return feedListJson;
            }
        }).d(new g<FeedListJson, FeedListJson>(this) {
            final /* synthetic */ FeedViewModel b;

            public /* synthetic */ Object call(Object obj) {
                return a((FeedListJson) obj);
            }

            public FeedListJson a(FeedListJson feedListJson) {
                if ((feedListJson.more == 1 && !"up".equalsIgnoreCase(str)) || feedListJson.goSuggestPage == 1) {
                    this.b.h();
                }
                return feedListJson;
            }
        }).d(new g<FeedListJson, FeedListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((FeedListJson) obj);
            }

            public FeedListJson a(FeedListJson feedListJson) {
                if (this.a.g != null) {
                    a.q().g(this.a.g);
                    if (!(feedListJson.list == null || feedListJson.list.isEmpty())) {
                        a.q().f(this.a.g);
                        a.q().m();
                    }
                }
                return feedListJson;
            }
        }).a(rx.a.b.a.a()).a(new e<FeedListJson>(this) {
            final /* synthetic */ FeedViewModel c;

            public /* synthetic */ void onNext(Object obj) {
                a((FeedListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                if (this.c.c != null) {
                    this.c.c.a(th.getMessage());
                }
            }

            public void a(FeedListJson feedListJson) {
                boolean z = true;
                com.izuiyou.a.a.b.c(feedListJson);
                if (this.c.c != null) {
                    boolean z2;
                    if (feedListJson.goSuggestPage == 1) {
                        this.c.e = 0;
                        this.c.d = 0;
                    } else if ("up".equalsIgnoreCase(str)) {
                        this.c.d = feedListJson.downOffset > 0 ? feedListJson.downOffset : this.c.d;
                    } else if (feedListJson.more == 1) {
                        this.c.e = feedListJson.upOffset > 0 ? feedListJson.upOffset : this.c.e;
                        this.c.d = feedListJson.downOffset > 0 ? feedListJson.downOffset : this.c.d;
                    } else {
                        this.c.e = feedListJson.upOffset > 0 ? feedListJson.upOffset : this.c.e;
                    }
                    a a = this.c.c;
                    String str = str;
                    if (feedListJson.goSuggestPage == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (feedListJson.more != 1) {
                        z = false;
                    }
                    a.a(str, z2, z, z, feedListJson.list);
                }
            }
        });
    }

    public void c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(a.g().c()));
        jSONObject.put("direction", "up");
        jSONObject.put("up_offset", Long.valueOf(this.e));
        jSONObject.put("down_offset", Long.valueOf(this.d));
        ((FeedService) c.b(FeedService.class)).feedList(jSONObject).d(new g<FeedListJson, FeedListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((FeedListJson) obj);
            }

            public FeedListJson a(FeedListJson feedListJson) {
                if (!(feedListJson == null || feedListJson.list == null)) {
                    for (RecPostDataBean recPostDataBean : feedListJson.list) {
                        recPostDataBean.oldPostData = RecPostDataBean.getOriginPostData(recPostDataBean);
                    }
                }
                return feedListJson;
            }
        }).a(rx.a.b.a.a()).a(new e<FeedListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FeedListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                if (this.a.c != null) {
                    this.a.c.a(false, true, null);
                }
            }

            public void a(FeedListJson feedListJson) {
                boolean z = false;
                if (feedListJson == null) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("没有更多内容了");
                    if (this.a.c != null) {
                        this.a.c.a(false, true, null);
                    }
                } else if (this.a.c != null) {
                    this.a.d = Math.max(feedListJson.downOffset, 0);
                    a a = this.a.c;
                    if (feedListJson.more == 1) {
                        z = true;
                    }
                    a.a(true, z, feedListJson.list);
                }
            }
        });
    }

    public void d() {
        this.f.set(true);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(a.g().c()));
        jSONObject.put("offset", Integer.valueOf(b));
        ((FeedService) c.b(FeedService.class)).suggestMembers(jSONObject).a(rx.a.b.a.a()).a(new e<FeedMemberListJson>(this) {
            final /* synthetic */ FeedViewModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((FeedMemberListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                this.a.f.set(false);
                if (this.a.c != null) {
                    this.a.c.a(null, null);
                }
            }

            public void a(FeedMemberListJson feedMemberListJson) {
                this.a.f.set(false);
                String str = null;
                if (feedMemberListJson.list == null || feedMemberListJson.list.isEmpty()) {
                    str = "没有更多推荐用户了";
                } else {
                    FeedViewModel.b = FeedViewModel.b + feedMemberListJson.list.size();
                }
                if (this.a.c != null) {
                    this.a.c.a(feedMemberListJson.list, str);
                }
            }
        });
    }

    public boolean e() {
        return this.f.get();
    }

    public void a(NavigatorTag navigatorTag) {
        this.g = navigatorTag;
    }
}
