package cn.xiaochuankeji.tieba.ui.recommend;

import android.arch.lifecycle.o;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import cn.htjyb.c.a.b;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.json.recommend.RecommendJson;
import cn.xiaochuankeji.tieba.json.recommend.RecommendJson.UgcDataWrapper;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecUgcDataBean;
import com.alibaba.fastjson.JSON;
import com.iflytek.cloud.SpeechConstant;
import java.io.File;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rx.b.g;
import rx.d;
import rx.d$a;
import rx.j;

public class RecommendViewModel extends o implements cn.xiaochuankeji.tieba.ui.recommend.holder.a.a {
    private static String b;
    cn.xiaochuankeji.tieba.api.recommend.a a = new cn.xiaochuankeji.tieba.api.recommend.a();
    private a c;
    private b d;
    private NavigatorTag e;

    public interface a {
        void a();

        void b();
    }

    enum ActionEnum {
        Refresh,
        LoadMore,
        LoadCache
    }

    public void a(a aVar) {
        this.c = aVar;
        this.c.registerAdapterDataObserver(new AdapterDataObserver(this) {
            final /* synthetic */ RecommendViewModel a;

            {
                this.a = r1;
            }

            public void onChanged() {
                super.onChanged();
                this.a.c();
            }
        });
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public void a(final a aVar, NavigatorTag navigatorTag) {
        this.e = navigatorTag;
        b = a(navigatorTag);
        if (b.c(b)) {
            d.b(new d$a<RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((j) obj);
                }

                public void a(j<? super RecommendJson> jVar) {
                    try {
                        RecommendJson recommendJson = (RecommendJson) JSON.parseObject(org.apache.commons.io.b.a(new File(RecommendViewModel.b), Charset.forName("utf-8")), RecommendJson.class);
                        Collections.sort(recommendJson.postList, new Comparator<RecPostDataBean>(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ int compare(Object obj, Object obj2) {
                                return a((RecPostDataBean) obj, (RecPostDataBean) obj2);
                            }

                            public int a(RecPostDataBean recPostDataBean, RecPostDataBean recPostDataBean2) {
                                if (recPostDataBean.getIndexInGroup() == recPostDataBean2.getIndexInGroup()) {
                                    return 0;
                                }
                                return recPostDataBean.getIndexInGroup() > recPostDataBean2.getIndexInGroup() ? -1 : 1;
                            }
                        });
                        jVar.onNext(recommendJson);
                        jVar.onCompleted();
                    } catch (Throwable e) {
                        com.izuiyou.a.a.b.d("read cache file error", e);
                        jVar.onError(e);
                    } catch (Throwable e2) {
                        com.izuiyou.a.a.b.d("convert string to json error", e2);
                        jVar.onError(e2);
                    }
                }
            }).b(rx.f.a.c()).d(new g<RecommendJson, RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object call(Object obj) {
                    return a((RecommendJson) obj);
                }

                public RecommendJson a(RecommendJson recommendJson) {
                    if (!(recommendJson == null || recommendJson.postList == null || recommendJson.postList.size() == 0)) {
                        for (RecPostDataBean recPostDataBean : recommendJson.postList) {
                            recPostDataBean.oldPostData = RecPostDataBean.getOriginPostData(recPostDataBean);
                        }
                    }
                    return recommendJson;
                }
            }).a(rx.a.b.a.a()).b(new j<RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel b;

                public /* synthetic */ void onNext(Object obj) {
                    a((RecommendJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    com.izuiyou.a.a.b.e(th);
                    this.b.a(th, ActionEnum.LoadCache);
                }

                public void a(RecommendJson recommendJson) {
                    List a = d.a().a(recommendJson);
                    this.b.c.a(a);
                    if (a == null || a.size() <= 0) {
                        aVar.a();
                    } else {
                        aVar.b();
                    }
                }
            });
        } else {
            aVar.a();
        }
    }

    public void a(boolean z) {
        if (this.e != null) {
            this.a.a(this.e.action_info.filter, z ? 1 : 0, "rec", "down").d(new g<RecommendJson, RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object call(Object obj) {
                    return a((RecommendJson) obj);
                }

                public RecommendJson a(RecommendJson recommendJson) {
                    if (!(recommendJson == null || recommendJson.postList == null || recommendJson.postList.size() == 0)) {
                        for (RecPostDataBean recPostDataBean : recommendJson.postList) {
                            recPostDataBean.oldPostData = RecPostDataBean.getOriginPostData(recPostDataBean);
                        }
                    }
                    return recommendJson;
                }
            }).b(rx.f.a.c()).c(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((RecommendJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    com.izuiyou.a.a.b.e(th);
                    this.a.a(th, ActionEnum.Refresh);
                }

                public void a(RecommendJson recommendJson) {
                    List a = d.a().a(recommendJson);
                    this.a.c.a(a);
                    if (this.a.d != null) {
                        this.a.d.a(true, "", a.size(), true);
                    }
                }
            });
        }
    }

    public void b(boolean z) {
        if (this.e != null) {
            this.a.a(this.e.action_info.filter, z ? 1 : 0, "rec", "up").d(new g<RecommendJson, RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object call(Object obj) {
                    return a((RecommendJson) obj);
                }

                public RecommendJson a(RecommendJson recommendJson) {
                    if (!(recommendJson == null || recommendJson.postList == null || recommendJson.postList.size() == 0)) {
                        for (RecPostDataBean recPostDataBean : recommendJson.postList) {
                            recPostDataBean.oldPostData = RecPostDataBean.getOriginPostData(recPostDataBean);
                        }
                    }
                    return recommendJson;
                }
            }).a(rx.a.b.a.a()).b(new j<RecommendJson>(this) {
                final /* synthetic */ RecommendViewModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((RecommendJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    com.izuiyou.a.a.b.e(th);
                    this.a.a(th, ActionEnum.LoadMore);
                }

                public void a(RecommendJson recommendJson) {
                    this.a.c.b(d.a().a(recommendJson));
                    if (this.a.d != null) {
                        this.a.d.a(true, "", true);
                    }
                }
            });
        }
    }

    public void a(long j) {
        if (j > 0 && this.c != null) {
            this.c.b(j);
        }
    }

    private String a(NavigatorTag navigatorTag) {
        if (navigatorTag != null) {
            String str = navigatorTag.action_info.filter;
            if (SpeechConstant.PLUS_LOCAL_ALL.equalsIgnoreCase(str)) {
                return cn.xiaochuankeji.tieba.background.a.e().r() + "post_recommend_list_new2.dat";
            }
            if ("video".equalsIgnoreCase(str)) {
                return cn.xiaochuankeji.tieba.background.a.e().r() + "video_post_recommend_list_new2.dat";
            }
            if ("imgtxt".equalsIgnoreCase(str)) {
                return cn.xiaochuankeji.tieba.background.a.e().r() + "index_imgtxt_post_list_new2.dat";
            }
        }
        return null;
    }

    private void a(Throwable th, ActionEnum actionEnum) {
        if (this.d != null) {
            String str = "请求出错";
            if (th instanceof ClientErrorException) {
                str = th.getMessage();
            } else if ((th instanceof SocketTimeoutException) || (th instanceof ConnectException)) {
                str = "网络错误";
            }
            switch (actionEnum) {
                case Refresh:
                    this.d.a(false, str, 0, true);
                    return;
                case LoadMore:
                    this.d.a(false, str, true);
                    return;
                default:
                    return;
            }
        }
    }

    private void c() {
        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable(this) {
            final /* synthetic */ RecommendViewModel a;

            {
                this.a = r1;
            }

            public void run() {
                int i = 200;
                int itemCount = this.a.c.getItemCount();
                if (itemCount < 200) {
                    i = itemCount;
                }
                RecommendJson recommendJson = new RecommendJson();
                recommendJson.postList = new ArrayList();
                recommendJson.ugcList = new ArrayList();
                for (int i2 = 0; i2 < i; i2++) {
                    c a = this.a.c.a(i2);
                    if (a != null) {
                        a.setIndexInGroup(i2);
                        if (a instanceof RecPostDataBean) {
                            recommendJson.postList.add((RecPostDataBean) a);
                        } else if (a instanceof RecUgcDataBean) {
                            UgcDataWrapper ugcDataWrapper = new UgcDataWrapper();
                            ugcDataWrapper.position = i2;
                            ugcDataWrapper.ugcDataBean = (RecUgcDataBean) a;
                            recommendJson.ugcList.add(ugcDataWrapper);
                        }
                    }
                }
                try {
                    org.apache.commons.io.b.a(new File(RecommendViewModel.b), JSON.toJSONString(recommendJson), Charset.forName("utf-8"));
                } catch (Exception e) {
                    com.izuiyou.a.a.b.d("save cache error", e);
                }
            }
        });
    }
}
