package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.o;
import cn.xiaochuankeji.tieba.background.utils.f;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.hollow.EmotionListJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowJson;
import cn.xiaochuankeji.tieba.json.hollow.NickNameListJson;
import cn.xiaochuankeji.tieba.json.hollow.NickNameListJson.NickName;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.TreeHoleViewModel;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.List;
import org.greenrobot.eventbus.c;
import rx.d;
import rx.d$a;
import rx.e;
import rx.j;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;

public class AudioPublishModel extends o {
    private a a;
    private cn.xiaochuankeji.tieba.api.hollow.a b = new cn.xiaochuankeji.tieba.api.hollow.a();
    @SuppressLint({"StaticFieldLeak"})
    private Activity c;
    private List<NickName> d;
    private int e = -1;

    interface a {
        void a();

        void b();
    }

    interface b {
        void a(NickName nickName);
    }

    void a(a aVar, Activity activity) {
        this.a = aVar;
        this.c = activity;
    }

    void a(final a aVar) {
        this.b.a().b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<EmotionListJson>(this) {
            final /* synthetic */ AudioPublishModel b;

            public /* synthetic */ void call(Object obj) {
                a((EmotionListJson) obj);
            }

            public void a(EmotionListJson emotionListJson) {
                this.b.a.a(emotionListJson.emotionList);
                aVar.a();
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ AudioPublishModel b;

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                g.a("获取表情列表失败");
                com.izuiyou.a.a.b.e(th);
                aVar.b();
            }
        });
    }

    void a(String str, final String str2, long j, long j2, long j3, String str3) {
        cn.xiaochuankeji.tieba.ui.widget.g.a(this.c, "正在发表树洞");
        final String str4 = str;
        final long j4 = j;
        final long j5 = j2;
        final long j6 = j3;
        final String str5 = str3;
        d.b(new d$a<String>(this) {
            final /* synthetic */ AudioPublishModel b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super String> jVar) {
                File file = new File(cn.xiaochuankeji.tieba.background.a.e().v(), "audio");
                if (!file.exists()) {
                    file.mkdirs();
                }
                String absolutePath = new File(file, "final.aac").getAbsolutePath();
                FFmpegMainCaller.wavToAac(str2, absolutePath);
                jVar.onNext(absolutePath);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.b.b<String>(this) {
            final /* synthetic */ AudioPublishModel f;

            public /* synthetic */ void call(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                this.f.b(str4, str, j4, j5, j6, str5);
            }
        });
    }

    private void b(String str, String str2, long j, long j2, long j3, String str3) {
        final long j4 = j2;
        final String str4 = str;
        final long j5 = j;
        final long j6 = j3;
        final String str5 = str3;
        new f(str2, "aac", new cn.xiaochuankeji.tieba.background.utils.f.a(this) {
            final /* synthetic */ AudioPublishModel f;

            public void a(boolean z, String str, String str2) {
                long j = 90;
                if (z) {
                    com.izuiyou.a.a.b.c("soundUri : " + str);
                    AudioDataBean audioDataBean = new AudioDataBean();
                    if (j4 <= 90) {
                        j = j4;
                    }
                    audioDataBean.dur = j;
                    audioDataBean.uri = str;
                    this.f.a(System.currentTimeMillis(), str4, audioDataBean, j5, j6, str5);
                    return;
                }
                com.izuiyou.a.a.b.e(str2);
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.f.c);
                g.a("发布失败，请重试");
            }
        }).a();
    }

    private void a(long j, String str, AudioDataBean audioDataBean, long j2, long j3, String str2) {
        this.b.a(j, str, audioDataBean, j2, j3, str2).b(rx.f.a.a()).a(rx.a.b.a.a()).a(new rx.b.b<HollowJson>(this) {
            final /* synthetic */ AudioPublishModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((HollowJson) obj);
            }

            public void a(HollowJson hollowJson) {
                com.izuiyou.a.a.b.c("HollowPublishTest -> " + JSON.toJSONString(hollowJson));
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.c);
                g.a("小秘密埋藏成功");
                HollowRecommendItemBean a = HollowRecommendItemBean.a(hollowJson.room);
                TreeHoleViewModel.a(a);
                c.a().d(new cn.xiaochuankeji.tieba.ui.hollow.recommend.f(a));
                if (this.a.c != null) {
                    this.a.c.setResult(-1);
                    this.a.c.finish();
                }
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ AudioPublishModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                com.izuiyou.a.a.b.e(th);
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.c);
                if (th instanceof ClientErrorException) {
                    g.a("发表失败：" + ((ClientErrorException) th).errMessage());
                } else {
                    g.a("发布失败，请重试");
                }
            }
        });
    }

    void a(String str, long j, long j2) {
        cn.xiaochuankeji.tieba.ui.widget.g.a(this.c, "正在发表树洞");
        a(System.currentTimeMillis(), str, null, j, j2, null);
    }

    void a(final b bVar) {
        this.b.b().a(rx.a.b.a.a()).a(new e<NickNameListJson>(this) {
            final /* synthetic */ AudioPublishModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((NickNameListJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(NickNameListJson nickNameListJson) {
                if (nickNameListJson != null && nickNameListJson.nickNameList != null) {
                    this.b.e = 0;
                    this.b.d = nickNameListJson.nickNameList;
                    if (bVar != null) {
                        bVar.a(this.b.b());
                    }
                }
            }
        });
    }

    protected NickName b() {
        if (this.e == -1) {
            return null;
        }
        if (this.e >= this.d.size()) {
            this.e %= this.d.size();
        }
        List list = this.d;
        int i = this.e;
        this.e = i + 1;
        return (NickName) list.get(i);
    }
}
