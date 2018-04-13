package cn.xiaochuankeji.tieba.ui.homepage.feed.newfeed;

import android.arch.lifecycle.o;
import android.text.TextUtils;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.feed.FeedMemberListJson;
import cn.xiaochuankeji.tieba.json.feed.FeedPostListJson;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import rx.b.g;
import rx.d;
import rx.d$a;
import rx.j;

public class FeedMainModel extends o {
    private List<c> a = new LinkedList();
    private List<MemberInfoBean> b = new LinkedList();
    private a c;
    private b d;
    private NavigatorTag e;
    private cn.xiaochuankeji.tieba.api.post.a f = new cn.xiaochuankeji.tieba.api.post.a();
    private long g = 0;
    private long h = 0;
    private long i = 0;

    interface a {
        void a();

        void a(int i);

        void a(LoadResultType loadResultType, boolean z);
    }

    enum LoadResultType {
        POST,
        MEMBER
    }

    void a(a aVar, b bVar) {
        bVar.a(this.a);
        aVar.a(this.b);
        this.c = aVar;
        this.d = bVar;
    }

    void a(NavigatorTag navigatorTag) {
        this.e = navigatorTag;
    }

    void a(final a aVar) {
        d.b(new d$a<FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super FeedPostListJson> jVar) {
                File c = this.b.b();
                if (c == null || !c.exists()) {
                    this.b.c(aVar);
                    return;
                }
                JSONObject a = b.a(c, AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    this.b.c(aVar);
                    return;
                }
                FeedPostListJson feedPostListJson = (FeedPostListJson) JSON.parseObject(a.toString(), FeedPostListJson.class);
                if (feedPostListJson == null || feedPostListJson.jsonArray == null || feedPostListJson.jsonArray.isEmpty()) {
                    this.b.c(aVar);
                } else {
                    jVar.onNext(feedPostListJson);
                }
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((FeedPostListJson) obj);
            }

            public void a(FeedPostListJson feedPostListJson) {
                this.b.a.clear();
                this.b.a.addAll(feedPostListJson.postVisitableList());
                this.b.d.notifyDataSetChanged();
                this.b.h = feedPostListJson.downOffset;
                this.b.i = feedPostListJson.upOffset;
                aVar.a(LoadResultType.POST, true);
            }
        });
    }

    private File b() {
        Object obj = cn.xiaochuankeji.tieba.background.a.e().r() + "post_feed.dat";
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        return new File(obj);
    }

    private void c(final a aVar) {
        d.b(new d$a<FeedMemberListJson>(this) {
            final /* synthetic */ FeedMainModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super FeedMemberListJson> jVar) {
                File f = this.a.c();
                if (f == null || !f.exists()) {
                    jVar.onError(new Throwable("加载本地数据失败"));
                    return;
                }
                JSONObject a = b.a(f, AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    jVar.onError(new Throwable("加载本地数据失败"));
                    return;
                }
                FeedMemberListJson feedMemberListJson = (FeedMemberListJson) JSON.parseObject(a.toString(), FeedMemberListJson.class);
                if (feedMemberListJson == null || feedMemberListJson.list == null || feedMemberListJson.list.isEmpty()) {
                    jVar.onError(new Throwable("加载本地数据失败"));
                } else {
                    jVar.onNext(feedMemberListJson);
                }
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<FeedMemberListJson>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((FeedMemberListJson) obj);
            }

            public void a(FeedMemberListJson feedMemberListJson) {
                this.b.g = feedMemberListJson.offset;
                this.b.b.clear();
                this.b.b.addAll(feedMemberListJson.list);
                this.b.c.notifyDataSetChanged();
                aVar.a(LoadResultType.MEMBER, false);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                aVar.a();
            }
        });
    }

    private File c() {
        Object obj = cn.xiaochuankeji.tieba.background.a.e().r() + "post_feed_member.dat";
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        return new File(obj);
    }

    private void a(final FeedPostListJson feedPostListJson, final boolean z) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ FeedMainModel c;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                FeedPostListJson feedPostListJson;
                long j;
                File c = this.c.b();
                JSONObject a = b.a(c, AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                FeedPostListJson feedPostListJson2 = (FeedPostListJson) JSON.parseObject(a.toString(), FeedPostListJson.class);
                if (feedPostListJson2 == null) {
                    feedPostListJson = new FeedPostListJson();
                } else {
                    feedPostListJson = feedPostListJson2;
                }
                if (feedPostListJson.jsonArray == null) {
                    feedPostListJson.jsonArray = new JSONArray();
                }
                if (z) {
                    feedPostListJson.jsonArray.addAll(0, feedPostListJson.jsonArray);
                    feedPostListJson.upOffset = feedPostListJson.upOffset;
                } else {
                    feedPostListJson.jsonArray.addAll(feedPostListJson.jsonArray);
                }
                if (feedPostListJson.jsonArray.size() > 200) {
                    feedPostListJson.jsonArray.subList(0, 200);
                }
                if (feedPostListJson.jsonArray.isEmpty()) {
                    j = 0;
                } else {
                    j = ((c) feedPostListJson.postVisitableList().get(feedPostListJson.jsonArray.size() - 1)).getCreateTime();
                }
                feedPostListJson.downOffset = j;
                feedPostListJson.more = 1;
                try {
                    b.a(new JSONObject(JSON.toJSONString(feedPostListJson)), c, AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    private void a(final FeedMemberListJson feedMemberListJson) {
        d.b(new d$a<Object>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Object> jVar) {
                File f = this.b.c();
                JSONObject a = b.a(f, AppController.kDataCacheCharsetUTF8.name());
                if (a == null) {
                    a = new JSONObject();
                }
                FeedMemberListJson feedMemberListJson = (FeedMemberListJson) JSON.parseObject(a.toString(), FeedMemberListJson.class);
                if (feedMemberListJson == null) {
                    feedMemberListJson = new FeedMemberListJson();
                }
                if (feedMemberListJson.list == null) {
                    feedMemberListJson.list = new ArrayList();
                }
                feedMemberListJson.list.clear();
                feedMemberListJson.list.addAll(feedMemberListJson.list);
                feedMemberListJson.offset = feedMemberListJson.offset;
                try {
                    b.a(new JSONObject(JSON.toJSONString(feedMemberListJson)), f, AppController.kDataCacheCharsetUTF8.name());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).b(rx.f.a.c()).g();
    }

    private void d() {
        File b = b();
        if (b != null && b.exists()) {
            try {
                org.apache.commons.io.b.d(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void a(String str, boolean z, final a aVar) {
        this.f.a(z ? 1 : 0, str, this.h, this.i).d(new g<FeedPostListJson, FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ Object call(Object obj) {
                return a((FeedPostListJson) obj);
            }

            public FeedPostListJson a(FeedPostListJson feedPostListJson) {
                if (feedPostListJson == null || feedPostListJson.jsonArray == null || feedPostListJson.goSuggestPage != 0) {
                    this.b.h = 0;
                    this.b.i = 0;
                    this.b.d();
                    this.b.a(false, aVar);
                    return null;
                }
                a.a = true;
                return feedPostListJson;
            }
        }).d(new g<FeedPostListJson, FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((FeedPostListJson) obj);
            }

            public FeedPostListJson a(FeedPostListJson feedPostListJson) {
                if (feedPostListJson == null) {
                    return null;
                }
                if (this.a.e != null) {
                    cn.xiaochuankeji.tieba.background.a.q().g(this.a.e);
                    cn.xiaochuankeji.tieba.background.a.q().f(this.a.e);
                    cn.xiaochuankeji.tieba.background.a.q().m();
                }
                if (feedPostListJson.jsonArray.isEmpty()) {
                    return feedPostListJson;
                }
                if (feedPostListJson.more == 0) {
                    this.a.a(feedPostListJson, true);
                    this.a.i = feedPostListJson.upOffset;
                    return feedPostListJson;
                }
                this.a.d();
                this.a.a(feedPostListJson, true);
                this.a.h = feedPostListJson.downOffset;
                this.a.i = feedPostListJson.upOffset;
                return feedPostListJson;
            }
        }).a(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((FeedPostListJson) obj);
            }

            public void a(FeedPostListJson feedPostListJson) {
                boolean z = true;
                if (feedPostListJson != null) {
                    if (feedPostListJson.more == 0) {
                        this.b.a.addAll(0, feedPostListJson.postVisitableList());
                    } else {
                        this.b.a.clear();
                        this.b.a.addAll(feedPostListJson.postVisitableList());
                    }
                    this.b.d.notifyDataSetChanged();
                    aVar.a(feedPostListJson.jsonArray.size());
                    a aVar = aVar;
                    LoadResultType loadResultType = LoadResultType.POST;
                    if (feedPostListJson.more != 1) {
                        z = false;
                    }
                    aVar.a(loadResultType, z);
                }
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }

    void b(final a aVar) {
        this.f.a(0, "up", this.h, this.i).d(new g<FeedPostListJson, FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((FeedPostListJson) obj);
            }

            public FeedPostListJson a(FeedPostListJson feedPostListJson) {
                if (feedPostListJson == null || feedPostListJson.jsonArray == null) {
                    return null;
                }
                this.a.a(feedPostListJson, false);
                if (feedPostListJson.jsonArray.isEmpty()) {
                    return feedPostListJson;
                }
                this.a.h = feedPostListJson.downOffset;
                return feedPostListJson;
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<FeedPostListJson>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((FeedPostListJson) obj);
            }

            public void a(FeedPostListJson feedPostListJson) {
                boolean z = true;
                if (feedPostListJson == null) {
                    aVar.a();
                    return;
                }
                this.b.a.addAll(feedPostListJson.postVisitableList());
                this.b.d.notifyDataSetChanged();
                a aVar = aVar;
                LoadResultType loadResultType = LoadResultType.POST;
                if (feedPostListJson.more != 1) {
                    z = false;
                }
                aVar.a(loadResultType, z);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }

    void a(final boolean z, final a aVar) {
        this.f.a(this.g).d(new g<FeedMemberListJson, FeedMemberListJson>(this) {
            final /* synthetic */ FeedMainModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((FeedMemberListJson) obj);
            }

            public FeedMemberListJson a(FeedMemberListJson feedMemberListJson) {
                if (feedMemberListJson == null || feedMemberListJson.list == null || feedMemberListJson.list.isEmpty()) {
                    return null;
                }
                this.a.g = this.a.g + ((long) feedMemberListJson.list.size());
                feedMemberListJson.offset = this.a.g;
                this.a.a(feedMemberListJson);
                return feedMemberListJson;
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<FeedMemberListJson>(this) {
            final /* synthetic */ FeedMainModel c;

            public /* synthetic */ void call(Object obj) {
                a((FeedMemberListJson) obj);
            }

            public void a(FeedMemberListJson feedMemberListJson) {
                if (feedMemberListJson == null) {
                    if (!z) {
                        this.c.a.clear();
                        this.c.d.notifyDataSetChanged();
                    }
                    aVar.a();
                    return;
                }
                this.c.b.clear();
                this.c.b.addAll(feedMemberListJson.list);
                this.c.c.notifyDataSetChanged();
                aVar.a(LoadResultType.MEMBER, false);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ FeedMainModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }
}
