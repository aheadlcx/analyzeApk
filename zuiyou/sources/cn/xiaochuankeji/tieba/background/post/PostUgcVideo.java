package cn.xiaochuankeji.tieba.background.post;

import android.util.Pair;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.upload.f;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.json.UgcPostJson;
import cn.xiaochuankeji.tieba.json.UgcPostJsonForPost;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.StickerTrace;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import rx.d;
import rx.d$a;
import rx.e;

public class PostUgcVideo implements cn.xiaochuankeji.tieba.background.upload.b, f {
    private String[] a;
    private String b;
    private long c;
    private String d;
    private boolean e;
    private a f;
    private b g;
    private long h;
    private String i;
    private UgcVideoMusicJson j;
    private List<StickerTrace> k;
    private LocalMedia l;
    private LocalMedia m;
    private String n;
    private cn.xiaochuankeji.tieba.background.upload.b o;
    private f p;
    private j q;

    public static class UGCVideoInfo {
        @JSONField(name = "id")
        public Long id;
        @JSONField(name = "uri")
        public String uri;
    }

    public static class UgcImageInfo {
        @JSONField(name = "fmt")
        public String format;
        @JSONField(name = "h")
        public int height;
        @JSONField(name = "id")
        public Long id;
        @JSONField(name = "w")
        public int width;
    }

    public static class UgcTextInfo {
        @JSONField(name = "content")
        public String content;
    }

    public interface a {
        void a(boolean z, String str, UgcVideoInfoBean ugcVideoInfoBean);
    }

    public interface b {
        void a(boolean z, String str, Moment moment);
    }

    public PostUgcVideo(String str, String str2, String[] strArr, long j, UgcVideoMusicJson ugcVideoMusicJson, List<StickerTrace> list, String str3, j jVar) {
        this.b = str;
        this.a = strArr;
        this.h = j;
        this.i = str2;
        this.j = ugcVideoMusicJson;
        this.k = list;
        this.n = str3;
        this.q = jVar;
    }

    public PostUgcVideo(long j, String str, String str2, String str3, String[] strArr, UgcVideoMusicJson ugcVideoMusicJson, List<StickerTrace> list, j jVar) {
        this.b = str2;
        this.c = j;
        this.d = str;
        this.a = strArr;
        this.i = str3;
        this.j = ugcVideoMusicJson;
        this.k = list;
        this.q = jVar;
    }

    public void a(a aVar, cn.xiaochuankeji.tieba.background.upload.b bVar, f fVar) {
        this.e = false;
        this.f = aVar;
        this.o = bVar;
        this.p = fVar;
        a();
    }

    public void a(b bVar, cn.xiaochuankeji.tieba.background.upload.b bVar2, f fVar) {
        this.e = true;
        this.g = bVar;
        this.o = bVar2;
        this.p = fVar;
        a();
    }

    private void a() {
        d.a(new d$a<Void>(this) {
            final /* synthetic */ PostUgcVideo a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((rx.j) obj);
            }

            public void a(rx.j<? super Void> jVar) {
                if (cn.htjyb.c.a.b.c(this.a.i)) {
                    Pair a = cn.xiaochuankeji.tieba.d.b.a(this.a.i);
                    if (((Integer) a.first).intValue() <= 0 || ((Integer) a.second).intValue() <= 0) {
                        jVar.onError(new IllegalStateException("视频封面异常"));
                        return;
                    }
                    this.a.l = new LocalMedia();
                    this.a.l.width = ((Integer) a.first).intValue();
                    this.a.l.height = ((Integer) a.second).intValue();
                    this.a.l.rotate = 0;
                    this.a.l.type = 1;
                    this.a.l.mediaID = 0;
                    this.a.l.path = this.a.b;
                    this.a.m = new LocalMedia();
                    this.a.m.path = this.a.i;
                    this.a.m.type = 2;
                    this.a.m.width = ((Integer) a.first).intValue();
                    this.a.m.height = ((Integer) a.second).intValue();
                    this.a.m.mediaID = 0;
                    jVar.onNext(null);
                    jVar.onCompleted();
                    return;
                }
                jVar.onError(new NullPointerException("未获取到视频封面"));
            }
        }).a(rx.f.a.c()).b(rx.f.a.c()).a(new e<Void>(this) {
            final /* synthetic */ PostUgcVideo a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.b.e(th);
                if (this.a.f != null) {
                    this.a.f.a(false, th.getMessage(), null);
                }
                if (this.a.g != null) {
                    this.a.g.a(false, th.getMessage(), null);
                }
            }

            public void a(Void voidR) {
                List arrayList = new ArrayList();
                arrayList.add(this.a.l);
                this.a.q.a(this.a.i);
                this.a.q.a(arrayList, "ugcvideo", this.a, this.a);
            }
        });
    }

    private void a(UGCVideoInfo uGCVideoInfo, UgcImageInfo ugcImageInfo, long j, boolean z) {
        d a;
        cn.xiaochuankeji.tieba.api.ugcvideo.a aVar = new cn.xiaochuankeji.tieba.api.ugcvideo.a();
        UgcTextInfo[] a2 = a(this.a);
        long currentTimeMillis = System.currentTimeMillis();
        if (j > 0) {
            a = aVar.a(currentTimeMillis, j, uGCVideoInfo, ugcImageInfo, this.j, a2, this.k, this.n);
        } else {
            a = aVar.a(currentTimeMillis, uGCVideoInfo, ugcImageInfo, this.j, a2, this.k);
        }
        final boolean z2 = z;
        final long j2 = j;
        final UGCVideoInfo uGCVideoInfo2 = uGCVideoInfo;
        final UgcImageInfo ugcImageInfo2 = ugcImageInfo;
        a.b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<UgcPostJson>(this) {
            final /* synthetic */ PostUgcVideo e;

            public /* synthetic */ void onNext(Object obj) {
                a((UgcPostJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.b.e(th);
                this.e.b(th instanceof ClientErrorException ? th.getMessage() : "发布失败，请重试");
            }

            public void a(UgcPostJson ugcPostJson) {
                if (z2 || j2 <= 0 || ugcPostJson == null || !ugcPostJson.gradle) {
                    if (ugcPostJson == null) {
                        return;
                    }
                    if (ugcPostJson.ugcVideoInfoBean != null || ugcPostJson.reviewVideoInfoBean != null) {
                        this.e.a(ugcPostJson.ugcVideoInfoBean == null ? ugcPostJson.reviewVideoInfoBean : ugcPostJson.ugcVideoInfoBean);
                    }
                } else if (0 == ugcPostJson.gradleId) {
                    this.e.a(uGCVideoInfo2, ugcImageInfo2, 0, true);
                } else {
                    this.e.a(uGCVideoInfo2, ugcImageInfo2, ugcPostJson.gradleId, true);
                }
            }
        });
    }

    private void a(UGCVideoInfo uGCVideoInfo, UgcImageInfo ugcImageInfo) {
        UGCVideoInfo uGCVideoInfo2 = uGCVideoInfo;
        UgcImageInfo ugcImageInfo2 = ugcImageInfo;
        new cn.xiaochuankeji.tieba.api.ugcvideo.a().a(System.currentTimeMillis(), this.d, this.c, uGCVideoInfo2, ugcImageInfo2, this.j, a(this.a), this.k).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<UgcPostJsonForPost>(this) {
            final /* synthetic */ PostUgcVideo a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcPostJsonForPost) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.b.e(th);
                this.a.b(th instanceof ClientErrorException ? th.getMessage() : "发布失败，请重试");
            }

            public void a(UgcPostJsonForPost ugcPostJsonForPost) {
                if (this.a.g != null) {
                    this.a.g.a(true, null, ugcPostJsonForPost.post);
                }
            }
        });
    }

    public void a(long j, long j2, int i) {
        if (this.o != null) {
            this.o.a(j, j2, i);
        }
    }

    public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
        UGCVideoInfo uGCVideoInfo = new UGCVideoInfo();
        uGCVideoInfo.uri = this.l.uri;
        UgcImageInfo ugcImageInfo = new UgcImageInfo();
        ugcImageInfo.format = "jpeg";
        ugcImageInfo.width = this.l.width;
        ugcImageInfo.height = this.l.height;
        if (list.size() > 0) {
            uGCVideoInfo.id = (Long) list.get(0);
            ugcImageInfo.id = (Long) list.get(0);
        }
        if (this.e) {
            a(uGCVideoInfo, ugcImageInfo);
        } else {
            a(uGCVideoInfo, ugcImageInfo, this.h, false);
        }
        if (this.p != null) {
            this.p.a(list, list2, hashMap);
        }
    }

    public void a(String str) {
        b("发布失败，请重试");
    }

    private UgcTextInfo[] a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        UgcTextInfo[] ugcTextInfoArr = new UgcTextInfo[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            UgcTextInfo ugcTextInfo = new UgcTextInfo();
            ugcTextInfo.content = strArr[i];
            ugcTextInfoArr[i] = ugcTextInfo;
        }
        return ugcTextInfoArr;
    }

    private void b(String str) {
        if (this.f != null) {
            this.f.a(false, str, null);
        }
        if (this.g != null) {
            this.g.a(false, str, null);
        }
    }

    private void a(UgcVideoInfoBean ugcVideoInfoBean) {
        if (this.f != null) {
            this.f.a(true, null, ugcVideoInfoBean);
        }
    }
}
