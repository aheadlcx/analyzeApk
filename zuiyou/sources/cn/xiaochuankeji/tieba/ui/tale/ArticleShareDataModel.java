package cn.xiaochuankeji.tieba.ui.tale;

import android.net.Uri;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.tale.FollowPostArticleJson;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.g;
import com.facebook.drawee.a.a.c;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.UUID;
import rx.d;
import rx.d$a;
import rx.j;

public class ArticleShareDataModel extends ShareDataModel {
    private long mCoverId;
    private String mDescription;
    private int mSharePlatformFlag;
    private String mTargetUrl;
    private String mThumbPath;
    private String mTitle;

    public ArticleShareDataModel(TaleDetail taleDetail, int i) {
        this.mTitle = taleDetail.theme.title;
        this.mTargetUrl = a.c(taleDetail.id);
        this.mSharePlatformFlag = i;
        if (TextUtils.isEmpty(taleDetail.abstractX)) {
            this.mDescription = "[图片]";
        } else {
            this.mDescription = taleDetail.abstractX;
        }
        this.mCoverId = taleDetail.cover;
    }

    public ArticleShareDataModel(FollowPostArticleJson followPostArticleJson, int i) {
        this.mTitle = followPostArticleJson.theme.title;
        this.mTargetUrl = a.c(followPostArticleJson.id);
        this.mSharePlatformFlag = i;
        if (TextUtils.isEmpty(followPostArticleJson.summary)) {
            this.mDescription = "[图片]";
        } else {
            this.mDescription = followPostArticleJson.summary;
        }
        this.mCoverId = followPostArticleJson.coverId;
    }

    public void prepareData(ShareDataModel.a aVar) {
        super.prepareData(aVar);
        a(this.mCoverId);
    }

    private void a(long j) {
        if (j == 0) {
            prepareFinish();
            return;
        }
        a(b.a(j).b(), cn.xiaochuankeji.tieba.background.a.e().B() + UUID.randomUUID() + "tale_share.jpg", new j<File>(this) {
            final /* synthetic */ ArticleShareDataModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((File) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.prepareFinish();
            }

            public void a(File file) {
                this.a.mThumbPath = file.getPath();
                this.a.prepareFinish();
            }
        });
    }

    private void a(final String str, final String str2, final j<? super File> jVar) {
        d.b(new d$a<File>(this) {
            final /* synthetic */ ArticleShareDataModel d;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(final j<? super File> jVar) {
                c.c().b(ImageRequestBuilder.a(Uri.parse(str)).a(true).n(), Boolean.valueOf(true)).a(new g(this, Uri.parse(str)) {
                    final /* synthetic */ AnonymousClass2 b;

                    protected void a(int i) {
                    }

                    protected void a(File file) {
                        File file2 = new File(str2);
                        cn.htjyb.c.a.b.a(file, file2);
                        jVar.onNext(file2);
                        jVar.onCompleted();
                    }

                    protected void a(Throwable th) {
                        jVar.onError(th);
                        jVar.onCompleted();
                    }
                }, cn.xiaochuankeji.tieba.background.a.p().d());
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b((j) jVar);
    }

    public String getTitleBy() {
        return this.mTitle;
    }

    public String getDescriptionBy() {
        return this.mDescription;
    }

    public String getTargetUrl() {
        return this.mTargetUrl;
    }

    public String getThumbPath() {
        return TextUtils.isEmpty(this.mThumbPath) ? cn.xiaochuankeji.tieba.background.utils.share.a.a() : this.mThumbPath;
    }

    public int getSharePlatformFlag() {
        return this.mSharePlatformFlag;
    }

    public void setSharePlatformFlag(int i) {
    }

    public String getABTestId() {
        return null;
    }

    public long getReportShareId() {
        return 0;
    }
}
