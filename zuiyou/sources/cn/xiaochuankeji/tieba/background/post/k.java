package cn.xiaochuankeji.tieba.background.post;

import android.app.Activity;
import android.content.Context;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a.a;
import cn.xiaochuankeji.tieba.background.b.b;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.my.MyPostActivity;
import cn.xiaochuankeji.tieba.ui.post.PostAllegeActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.ReportedPostActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.json.JSONObject;
import rx.j;

public class k extends a {
    Post c;
    private long d;
    private long e = 0;
    private long f = 0;
    private int g;

    public k(Post post, long j, long j2, long j3, Context context) {
        super(context);
        this.c = post;
        this.e = j;
        this.f = j2;
        this.d = j3;
    }

    public void a(int i) {
        new b(this.f, this.e, "post", i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("举报成功");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    public void a() {
        if (!(this.b instanceof MyPostActivity) && !(this.b instanceof PostDetailActivity)) {
            c();
        } else if (this.d == cn.xiaochuankeji.tieba.background.a.i().q().getId()) {
            PostAllegeActivity.a(this.b, this.e, this.f);
        } else {
            c();
        }
    }

    private void c() {
        LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m.size() != 0) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.b, new SDCheckSheet.a(this) {
                final /* synthetic */ k a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == -123) {
                        CustomReportReasonActivity.a(this.a.b, this.a.f, this.a.e, this.a.g, "post");
                    } else {
                        this.a.a(i);
                    }
                }
            });
            int i = 0;
            for (Entry entry : m.entrySet()) {
                int i2;
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                int parseInt = Integer.parseInt(str);
                int i3 = i + 1;
                String trim = str2.trim();
                if (trim.equals("其他")) {
                    this.g = parseInt;
                    i2 = -123;
                } else {
                    i2 = parseInt;
                }
                if (i3 == m.size()) {
                    sDCheckSheet.a(trim, i2, true);
                } else {
                    sDCheckSheet.a(trim, i2, false);
                }
                i = i3;
            }
            sDCheckSheet.b();
        }
    }

    public void b() {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.b, new SDBottomSheet.b(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 1) {
                    this.a.a(true);
                } else if (i == 2) {
                    this.a.d();
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(R.drawable.toast_against, "违规", 1));
        arrayList.add(new c(R.drawable.toast_support, "正常", 2));
        sDBottomSheet.a(arrayList, null);
        sDBottomSheet.b();
    }

    public void a(final boolean z) {
        LinkedHashMap r = cn.xiaochuankeji.tieba.background.utils.c.a.c().r();
        if (r.size() == 0) {
            c(0);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.b, new SDCheckSheet.a(this) {
            final /* synthetic */ k b;

            public void a(int i) {
                if (z) {
                    this.b.c(i);
                } else {
                    this.b.d(i);
                }
            }
        });
        int i = 0;
        for (Entry entry : r.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            int parseInt = Integer.parseInt(str);
            int i2 = i + 1;
            if (i2 == r.size()) {
                sDCheckSheet.a(str2, parseInt, true);
            } else {
                sDCheckSheet.a(str2, parseInt, false);
            }
            i = i2;
        }
        sDCheckSheet.b();
    }

    private void c(int i) {
        cn.xiaochuankeji.tieba.api.topic.b bVar = new cn.xiaochuankeji.tieba.api.topic.b();
        String str = "";
        if (this.b instanceof ReportedPostActivity) {
            str = "report";
        }
        bVar.a(this.e, i, str).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                } else {
                    g.a("网络出错");
                }
                th.printStackTrace();
            }

            public void a(EmptyJson emptyJson) {
                if (this.a.b instanceof ReportedPostActivity) {
                    g.a("处理成功");
                } else {
                    g.a("帖子已被移除");
                }
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                messageEvent.setData(this.a.c);
                org.greenrobot.eventbus.c.a().d(messageEvent);
            }
        });
    }

    private void d() {
        new cn.xiaochuankeji.tieba.api.topic.b().d(this.e, this.f).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                } else {
                    g.a("网络出错");
                }
                th.printStackTrace();
            }

            public void a(EmptyJson emptyJson) {
                g.a("处理成功");
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                messageEvent.setData(this.a.c);
                org.greenrobot.eventbus.c.a().d(messageEvent);
            }
        });
    }

    private void d(int i) {
        new cn.xiaochuankeji.tieba.api.topic.b().a(this.d, this.f, i).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                } else {
                    g.a("网络错误");
                }
            }

            public void a(EmptyJson emptyJson) {
                g.a("该用户3日内不能再话题中发帖");
            }
        });
    }

    public void b(final int i) {
        f.a("提示", "确定删除帖子吗?", (Activity) this.b, new f.a(this) {
            final /* synthetic */ k b;

            public void a(boolean z) {
                if (z) {
                    this.b.e(i);
                }
            }
        });
    }

    private void e(int i) {
        new i(this.e, i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("删除成功");
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                messageEvent.setData(this.a.c);
                org.greenrobot.eventbus.c.a().d(messageEvent);
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }
}
