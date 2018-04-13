package cn.xiaochuankeji.tieba.ui.my.history;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class MyHistoryModel extends o {
    private List<c> a = new LinkedList();
    private a b;

    interface a {
        void a();
    }

    interface b {
        void a();

        void b();
    }

    void a(a aVar) {
        aVar.a(this.a);
        this.b = aVar;
    }

    void a(final b bVar) {
        d.b(new d$a<List<PostDataBean>>(this) {
            final /* synthetic */ MyHistoryModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super List<PostDataBean>> jVar) {
                List<String> c = cn.xiaochuankeji.tieba.a.d.c();
                List arrayList = new ArrayList(c.size());
                try {
                    for (String jSONObject : c) {
                        arrayList.add(PostDataBean.getPostDataBeanFromJson(new JSONObject(jSONObject)));
                    }
                    jVar.onNext(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    jVar.onError(new Throwable("数据获取失败"));
                }
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<List<PostDataBean>>(this) {
            final /* synthetic */ MyHistoryModel b;

            public /* synthetic */ void call(Object obj) {
                a((List) obj);
            }

            public void a(List<PostDataBean> list) {
                if (list == null || list.isEmpty()) {
                    bVar.b();
                    return;
                }
                this.b.a.clear();
                this.b.a.addAll(list);
                this.b.b.notifyDataSetChanged();
                bVar.a();
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyHistoryModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                bVar.b();
            }
        });
    }

    void a(final a aVar) {
        cn.xiaochuankeji.tieba.a.d.a(new rx.b.a(this) {
            final /* synthetic */ MyHistoryModel b;

            public void call() {
                this.b.a.clear();
                this.b.b.notifyDataSetChanged();
                aVar.a();
            }
        });
    }
}
