package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.app.Activity;
import android.arch.lifecycle.o;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.api.post.b;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.PostShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.VoiceShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.share.UgcVideoShareStruct;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.json.topic.TopicReportTediumJson;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.UgcDataBean;
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.util.List;
import org.greenrobot.eventbus.c;
import rx.d;
import rx.f.a;

public class HolderOperator extends o {
    private b a = new b();

    public void a(final long j) {
        this.a.a(j, 0).b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator b;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.b("删除成功");
                c.a().d(new cn.xiaochuankeji.tieba.ui.my.mypost.a(j));
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    public d<EmptyJson> b(long j) {
        return this.a.a(System.currentTimeMillis(), j).b(a.a()).a(rx.a.b.a.a());
    }

    public void a(long j, int i, String str) {
        this.a.a(j, i, str).b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.a("举报成功");
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    void a(Activity activity, Post post, int i, String str) {
        final ShareDataModel postShareDataModel = new PostShareDataModel(post, post.comments.size() > 0 ? (Comment) post.comments.get(0) : null, i);
        final Activity activity2 = activity;
        final Post post2 = post;
        final String str2 = str;
        final int i2 = i;
        postShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ HolderOperator f;

            public void a() {
                activity2.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass17 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        cn.xiaochuankeji.tieba.background.utils.share.b.a().a(activity2, postShareDataModel);
                    }
                });
                cn.xiaochuankeji.tieba.background.i.a.a(post2._ID, str2, (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i2)), postShareDataModel.getABTestId());
            }
        });
    }

    public void a(Activity activity, PostDataBean postDataBean, int i, String str) {
        final ShareDataModel voiceShareDataModel = new VoiceShareDataModel(postDataBean, i);
        final Activity activity2 = activity;
        final int i2 = i;
        final PostDataBean postDataBean2 = postDataBean;
        final String str2 = str;
        voiceShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ HolderOperator f;

            public void a() {
                activity2.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass18 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (i2 == 4 || i2 == 2) {
                            cn.xiaochuankeji.tieba.background.utils.share.c.a().a(i2 == 4, voiceShareDataModel.getTargetUrl(), postDataBean2.audio.url, voiceShareDataModel.getTitleBy(), voiceShareDataModel.getDescriptionBy(), voiceShareDataModel.getThumbPath());
                        } else {
                            voiceShareDataModel.prepareData(new ShareDataModel.a(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a() {
                                    cn.xiaochuankeji.tieba.background.utils.share.b.a().a(activity2, voiceShareDataModel);
                                }
                            });
                        }
                    }
                });
                cn.xiaochuankeji.tieba.background.i.a.a(postDataBean2.getId(), str2, (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i2)), voiceShareDataModel.getABTestId());
            }
        });
    }

    void a(final long j, int i) {
        this.a.b(j, i, "").b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator b;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.a("帖子已被移除");
                c.a().d(new cn.xiaochuankeji.tieba.ui.my.mypost.a(j));
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    void c(long j) {
        this.a.a(j).b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    void a(long j, long j2, int i) {
        this.a.a(j, j2, i).b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.a("该用户3日内不能在话题中发帖");
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    void a(Activity activity, long j, long j2, String str, List<String> list, String str2) {
        final Activity activity2 = activity;
        final long j3 = j2;
        final String str3 = str;
        final long j4 = j;
        this.a.a(j, j2, list, str2, 1).b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<TopicReportTediumJson>(this) {
            final /* synthetic */ HolderOperator e;

            public /* synthetic */ void call(Object obj) {
                a((TopicReportTediumJson) obj);
            }

            public void a(TopicReportTediumJson topicReportTediumJson) {
                g.a("将减少类似内容推荐");
                if (topicReportTediumJson != null && topicReportTediumJson.block == 1) {
                    this.e.a(activity2, j3, str3);
                }
                c.a().d(new cn.xiaochuankeji.tieba.ui.my.mypost.a(j4));
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    private void a(Activity activity, final long j, String str) {
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(activity, "提示", "小右看你总是删除" + str + "中的帖子，需不需要屏蔽该话题呀？").b("", new OnClickListener(this) {
            final /* synthetic */ HolderOperator b;

            public void onClick(View view) {
                this.b.c(j);
            }
        }).a("", null).a();
    }

    void d(final long j) {
        this.a.b(j).b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator b;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.b("删除成功");
                c.a().d(new cn.xiaochuankeji.tieba.ui.my.mypost.a(j));
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    void a(final Activity activity, final UgcDataBean ugcDataBean, final int i) {
        this.a.a(ugcDataBean.getId(), "index").b(a.a()).a(rx.a.b.a.a()).a(new rx.b.b<UgcVideoShareJson>(this) {
            final /* synthetic */ HolderOperator d;

            public /* synthetic */ void call(Object obj) {
                a((UgcVideoShareJson) obj);
            }

            public void a(UgcVideoShareJson ugcVideoShareJson) {
                if (ugcVideoShareJson != null && ugcVideoShareJson.shareTxt != null) {
                    Object obj = ugcVideoShareJson.shareTxt.desp;
                    Object obj2 = ugcVideoShareJson.shareTxt.title;
                    if (!TextUtils.isEmpty(obj) && !TextUtils.isEmpty(obj2)) {
                        cn.xiaochuankeji.tieba.background.utils.share.b.a().a(i, activity, new UgcVideoShareStruct(obj2, obj, this.d.e((long) ((UgcVideoInfoBean) ugcDataBean.ugcVideos.get(0)).img.id), cn.xiaochuankeji.tieba.background.utils.d.a.b(ugcDataBean.getId(), 0)));
                    }
                }
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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

    private String e(long j) {
        File a = cn.xiaochuankeji.tieba.common.c.a.a(ImageRequest.a(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, null)));
        if (a != null && a.exists() && a.isFile()) {
            return a.getAbsolutePath();
        }
        return null;
    }

    void a(long j, boolean z, String str) {
        this.a.a(j, z, str).b(a.c()).a(rx.a.b.a.a()).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ HolderOperator a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ HolderOperator a;

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
}
