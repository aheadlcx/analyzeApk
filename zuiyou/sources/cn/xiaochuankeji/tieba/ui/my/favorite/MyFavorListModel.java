package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.MyFavorListJson;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MyFavorListModel extends o {
    private static final long a = cn.xiaochuankeji.tieba.background.a.g().c();
    private cn.xiaochuankeji.tieba.api.my.a b = new cn.xiaochuankeji.tieba.api.my.a();
    private long c = 0;
    private e d;
    private List<Favorite> e = new LinkedList();

    interface a {
        void a(boolean z);
    }

    interface b {
        void a();

        void a(boolean z);
    }

    void a(e eVar) {
        eVar.a(this.e);
        this.d = eVar;
    }

    void a(final b bVar) {
        this.b.a(0, a).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<MyFavorListJson>(this) {
            final /* synthetic */ MyFavorListModel b;

            public /* synthetic */ void call(Object obj) {
                a((MyFavorListJson) obj);
            }

            public void a(MyFavorListJson myFavorListJson) {
                boolean z = true;
                Collection a = this.b.a(myFavorListJson);
                if (a == null || a.isEmpty()) {
                    bVar.a();
                    return;
                }
                this.b.e.clear();
                this.b.e.addAll(a);
                this.b.d.notifyDataSetChanged();
                this.b.c = myFavorListJson.lastTime;
                b bVar = bVar;
                if (myFavorListJson.more != 1) {
                    z = false;
                }
                bVar.a(z);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyFavorListModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                bVar.a();
            }
        });
    }

    private List<Favorite> a(MyFavorListJson myFavorListJson) {
        if (myFavorListJson == null) {
            return null;
        }
        List<FavoriteBean> list = myFavorListJson.favorList;
        List<Favorite> linkedList = new LinkedList();
        for (FavoriteBean favoriteFromFavoriteBean : list) {
            linkedList.add(FavoriteBean.getFavoriteFromFavoriteBean(favoriteFromFavoriteBean));
        }
        return linkedList;
    }

    void b(final b bVar) {
        this.b.a(this.c, a).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<MyFavorListJson>(this) {
            final /* synthetic */ MyFavorListModel b;

            public /* synthetic */ void call(Object obj) {
                a((MyFavorListJson) obj);
            }

            public void a(MyFavorListJson myFavorListJson) {
                boolean z = true;
                Collection a = this.b.a(myFavorListJson);
                if (a == null || a.isEmpty()) {
                    bVar.a();
                    return;
                }
                this.b.e.addAll(a);
                this.b.d.notifyDataSetChanged();
                this.b.c = myFavorListJson.lastTime;
                b bVar = bVar;
                if (myFavorListJson.more != 1) {
                    z = false;
                }
                bVar.a(z);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyFavorListModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
            }
        });
    }

    void a(final long j, final a aVar) {
        this.b.a(j).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ MyFavorListModel c;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                this.c.b(j, aVar);
                g.a("删除成功");
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyFavorListModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                e.a(th);
                aVar.a(this.b.e.size() == 0);
            }
        });
    }

    void b(long j, a aVar) {
        for (Favorite favorite : this.e) {
            if (favorite.getId() == j) {
                this.e.remove(favorite);
                break;
            }
        }
        this.d.notifyDataSetChanged();
        aVar.a(this.e.size() == 0);
    }
}
