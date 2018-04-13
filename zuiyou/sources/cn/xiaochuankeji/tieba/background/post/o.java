package cn.xiaochuankeji.tieba.background.post;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.ugcvideo.a;
import cn.xiaochuankeji.tieba.background.b.b;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.c.c;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.share.UgcVideoShareStruct;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;
import rx.d;
import rx.j;

public class o {
    private String a;
    private a b;
    private Context c;
    private long d = 0;
    private long e = 0;
    private Moment f;

    public o(Moment moment, String str, Context context) {
        this.c = context;
        this.f = moment;
        this.d = moment.id;
        if (!(moment.ugcVideos == null || moment.ugcVideos.isEmpty())) {
            this.e = ((UgcVideoInfoBean) moment.ugcVideos.get(0)).pid;
        }
        this.a = str;
        this.b = new a();
    }

    public void a() {
        final ArrayList s = cn.xiaochuankeji.tieba.background.utils.c.a.c().s();
        if (s.size() != 0) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.c, new SDCheckSheet.a(this) {
                final /* synthetic */ o b;

                public void a(int i) {
                    this.b.a((c) s.get(i));
                }
            });
            for (int i = 0; i < s.size(); i++) {
                c cVar = (c) s.get(i);
                if (i == s.size() - 1) {
                    sDCheckSheet.a(cVar.b, i, true);
                } else {
                    sDCheckSheet.a(cVar.b, i, false);
                }
            }
            sDCheckSheet.b();
        }
    }

    private void a(c cVar) {
        if (cVar.b.equals("其他")) {
            CustomReportReasonActivity.a(this.c, this.e, this.d, cVar.a, "ugcvideo");
        } else {
            new b(this.e, this.d, "ugcvideo", cVar.a, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ o a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    g.b("举报成功");
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ o a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.b(xCError.getMessage());
                }
            }).execute();
        }
    }

    public void b() {
        f.a("提示", "确定删除跟拍帖子吗?", (Activity) this.c, new f.a(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    this.a.d();
                }
            }
        });
    }

    private void d() {
        d d;
        if (this.e == 0) {
            d = this.b.d(this.f.id);
        } else {
            d = this.b.e(this.f.id);
        }
        d.a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                g.b(th.getMessage());
            }

            public void a(EmptyJson emptyJson) {
                if (emptyJson != null) {
                    g.b("删帖成功");
                    MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                    messageEvent.setData(this.a.f);
                    org.greenrobot.eventbus.c.a().d(messageEvent);
                }
            }
        });
    }

    public void c() {
        cn.xiaochuankeji.tieba.ui.utils.d.a("#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.b(this.e, this.d));
        g.a("复制成功");
    }

    public void a(final int i) {
        if (this.f.ugcVideos != null && !this.f.ugcVideos.isEmpty()) {
            d b;
            if (((UgcVideoInfoBean) this.f.ugcVideos.get(0)).pid == 0) {
                b = this.b.b(this.f.id, this.a);
            } else {
                b = this.b.c(this.f.id, this.a);
            }
            b.a(rx.a.b.a.a()).b(new j<UgcVideoShareJson>(this) {
                final /* synthetic */ o b;

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcVideoShareJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(UgcVideoShareJson ugcVideoShareJson) {
                    if (ugcVideoShareJson.shareTxt != null) {
                        Object obj = ugcVideoShareJson.shareTxt.title;
                        Object obj2 = ugcVideoShareJson.shareTxt.desp;
                        long j = (long) ((UgcVideoInfoBean) this.b.f.ugcVideos.get(0)).img.id;
                        if (!TextUtils.isEmpty(obj) || !TextUtils.isEmpty(obj2)) {
                            String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, null);
                            String b = cn.xiaochuankeji.tieba.background.utils.d.a.b(this.b.f.id, 0);
                            File a2 = cn.xiaochuankeji.tieba.common.c.a.a(ImageRequest.a(a));
                            if (a2 != null && a2.exists() && a2.isFile()) {
                                a = a2.getAbsolutePath();
                            } else {
                                a = null;
                            }
                            cn.xiaochuankeji.tieba.background.utils.share.b.a().a(i, (Activity) this.b.c, new UgcVideoShareStruct(obj, obj2, a, b));
                        }
                    }
                }
            });
        }
    }

    public void a(boolean z) {
        this.b.a(this.d, z, this.a).a(rx.a.b.a.a()).b(new j<EmptyJson>(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((EmptyJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
            }

            public void a(EmptyJson emptyJson) {
            }
        });
    }
}
