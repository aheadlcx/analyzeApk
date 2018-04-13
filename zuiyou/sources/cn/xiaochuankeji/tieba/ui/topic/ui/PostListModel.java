package cn.xiaochuankeji.tieba.ui.topic.ui;

import android.arch.lifecycle.o;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.topic.b;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.topic.TopicPostListJson;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.ui.PostListFragment.FragmentType;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PostListModel extends o {
    private LinkedList<c> a = new LinkedList();
    private a b;
    private b c = new b();
    private String d = "";
    private String e = "";
    private int f = 1;

    interface a {
        void a();

        void a(boolean z, int i);
    }

    void a(a aVar) {
        aVar.a(this.a);
        this.b = aVar;
    }

    void a(List<c> list, String str, int i) {
        if (list != null) {
            this.a.clear();
            this.a.addAll(list);
            this.b.notifyDataSetChanged();
            this.d = str;
            this.f = i;
        }
    }

    void a(long j, FragmentType fragmentType, a aVar) {
        switch (fragmentType) {
            case HOT:
                a(j, aVar);
                return;
            case NEW:
                b(j, aVar);
                return;
            default:
                return;
        }
    }

    private void a(long j, final a aVar) {
        this.c.a(j, this.d, "hot").b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<TopicPostListJson>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((TopicPostListJson) obj);
            }

            public void a(TopicPostListJson topicPostListJson) {
                int i = 0;
                if (topicPostListJson == null) {
                    g.a("数据获取失败");
                    aVar.a();
                    return;
                }
                List postVisitableList = topicPostListJson.postVisitableList();
                if (postVisitableList.isEmpty()) {
                    aVar.a();
                    return;
                }
                boolean z;
                if (this.b.f == 1) {
                    this.b.a.clear();
                    this.b.a.addAll(postVisitableList);
                } else {
                    this.b.a.addAll(0, this.b.a(postVisitableList));
                }
                this.b.b.notifyDataSetChanged();
                a aVar = aVar;
                if (topicPostListJson.more == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (this.b.f != 1) {
                    i = topicPostListJson.jsonArray.size();
                }
                aVar.a(z, i);
                this.b.e = topicPostListJson.nextCb;
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }

    private List<c> a(List<c> list) {
        Collection linkedList = new LinkedList();
        for (c cVar : list) {
            if (this.a.contains(cVar)) {
                linkedList.add(cVar);
            }
        }
        list.removeAll(linkedList);
        return list;
    }

    private void b(long j, final a aVar) {
        this.c.a(j, "", "new").b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<TopicPostListJson>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((TopicPostListJson) obj);
            }

            public void a(TopicPostListJson topicPostListJson) {
                boolean z = true;
                if (topicPostListJson == null) {
                    g.a("数据获取失败");
                    aVar.a();
                    return;
                }
                Collection postVisitableList = topicPostListJson.postVisitableList();
                if (postVisitableList.isEmpty()) {
                    aVar.a();
                    return;
                }
                this.b.a.clear();
                this.b.a.addAll(postVisitableList);
                this.b.b.notifyDataSetChanged();
                a aVar = aVar;
                if (topicPostListJson.more != 1) {
                    z = false;
                }
                aVar.a(z, 0);
                this.b.e = topicPostListJson.nextCb;
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }

    void b(long j, FragmentType fragmentType, a aVar) {
        switch (fragmentType) {
            case HOT:
                c(j, aVar);
                return;
            case NEW:
                d(j, aVar);
                return;
            default:
                return;
        }
    }

    private void c(long j, final a aVar) {
        this.c.a(j, TextUtils.isEmpty(this.e) ? this.d : this.e, "hot").b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<TopicPostListJson>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((TopicPostListJson) obj);
            }

            public void a(TopicPostListJson topicPostListJson) {
                boolean z = true;
                if (topicPostListJson != null) {
                    int size = this.b.a.size();
                    this.b.a.addAll(topicPostListJson.postVisitableList());
                    this.b.b.notifyItemRangeInserted(size, topicPostListJson.postVisitableList().size());
                    a aVar = aVar;
                    if (topicPostListJson.more != 1) {
                        z = false;
                    }
                    aVar.a(z, topicPostListJson.jsonArray.size());
                    this.b.e = topicPostListJson.nextCb;
                    return;
                }
                g.a("数据获取失败");
                aVar.a();
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a();
            }
        });
    }

    private void d(long j, final a aVar) {
        this.c.a(j, TextUtils.isEmpty(this.e) ? this.d : this.e, "new").b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<TopicPostListJson>(this) {
            final /* synthetic */ PostListModel b;

            public /* synthetic */ void call(Object obj) {
                a((TopicPostListJson) obj);
            }

            public void a(TopicPostListJson topicPostListJson) {
                boolean z = true;
                if (topicPostListJson != null) {
                    int size = this.b.a.size();
                    this.b.a.addAll(topicPostListJson.postVisitableList());
                    this.b.b.notifyItemRangeInserted(size, topicPostListJson.postVisitableList().size());
                    a aVar = aVar;
                    if (topicPostListJson.more != 1) {
                        z = false;
                    }
                    aVar.a(z, topicPostListJson.jsonArray.size());
                    this.b.e = topicPostListJson.nextCb;
                    return;
                }
                g.a("数据获取失败");
                aVar.a();
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ PostListModel b;

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
