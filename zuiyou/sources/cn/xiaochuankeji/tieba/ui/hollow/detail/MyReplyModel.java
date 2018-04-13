package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.app.Activity;
import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.hollow.MyReplyListJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.alibaba.fastjson.JSON;

public class MyReplyModel extends o {
    private b a;
    private cn.xiaochuankeji.tieba.api.hollow.a b = new cn.xiaochuankeji.tieba.api.hollow.a();

    interface a {
        void a(Throwable th);

        void a(boolean z, String str);
    }

    interface b {
        void a(boolean z);
    }

    void a(b bVar) {
        this.a = bVar;
    }

    void a(final a aVar, final b bVar) {
        this.b.b("").b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<MyReplyListJson>(this) {
            final /* synthetic */ MyReplyModel c;

            public /* synthetic */ void call(Object obj) {
                a((MyReplyListJson) obj);
            }

            public void a(MyReplyListJson myReplyListJson) {
                boolean z;
                boolean z2 = true;
                com.izuiyou.a.a.b.c(JSON.toJSONString(myReplyListJson));
                a aVar = aVar;
                if (myReplyListJson.more == 1) {
                    z = true;
                } else {
                    z = false;
                }
                aVar.a(z, myReplyListJson.nextCb);
                this.c.a.a(myReplyListJson.msgDataList);
                b bVar = bVar;
                if (this.c.a.getItemCount() != 0) {
                    z2 = false;
                }
                bVar.a(z2);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyReplyModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                if (th instanceof ClientErrorException) {
                    aVar.a(th);
                } else {
                    aVar.a(new Throwable("网络错误"));
                }
                com.izuiyou.a.a.b.e(th);
            }
        });
    }

    void a(String str, final a aVar) {
        this.b.b(str).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<MyReplyListJson>(this) {
            final /* synthetic */ MyReplyModel b;

            public /* synthetic */ void call(Object obj) {
                a((MyReplyListJson) obj);
            }

            public void a(MyReplyListJson myReplyListJson) {
                boolean z = true;
                com.izuiyou.a.a.b.c(JSON.toJSONString(myReplyListJson));
                a aVar = aVar;
                if (myReplyListJson.more != 1) {
                    z = false;
                }
                aVar.a(z, myReplyListJson.nextCb);
                this.b.a.b(myReplyListJson.msgDataList);
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyReplyModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                if (th instanceof ClientErrorException) {
                    aVar.a(th);
                } else {
                    aVar.a(new Throwable("网络错误"));
                }
                com.izuiyou.a.a.b.e(th);
            }
        });
    }

    void a(final Activity activity, long j, b bVar) {
        g.a(activity);
        final Activity activity2 = activity;
        final long j2 = j;
        final b bVar2 = bVar;
        this.b.b(j).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ MyReplyModel d;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.c(activity2);
                com.izuiyou.a.a.b.c("评论删除成功");
                this.d.a.a(j2, new d(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        bVar2.a(z);
                    }
                });
                cn.xiaochuankeji.tieba.background.utils.g.a("删除成功");
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ MyReplyModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                g.c(activity);
                if (th instanceof ClientErrorException) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("删除失败" + th.getMessage());
                } else {
                    cn.xiaochuankeji.tieba.background.utils.g.a("操作失败，请重试");
                }
                com.izuiyou.a.a.b.c("评论删除失败：" + th.getMessage());
            }
        });
    }
}
