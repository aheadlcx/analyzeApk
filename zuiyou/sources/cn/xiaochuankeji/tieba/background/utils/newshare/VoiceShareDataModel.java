package cn.xiaochuankeji.tieba.background.utils.newshare;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rx.d;
import rx.d$a;
import rx.j;

public class VoiceShareDataModel extends ShareDataModel {
    private String audioUrl;
    private String mDescribe;
    private PostDataBean mPostDataBean;
    private int mSharePlatformFlag;
    private String mTitle;
    private String targetUrl;
    private String thumbFilePath;

    public VoiceShareDataModel(PostDataBean postDataBean, int i) {
        this.mPostDataBean = postDataBean;
        this.mSharePlatformFlag = i;
    }

    public String getTitleBy() {
        if (this.mPostDataBean != null && TextUtils.isEmpty(this.mPostDataBean.content)) {
            return "最右-声控福利社";
        }
        if (this.mPostDataBean.content == null || this.mPostDataBean.content.length() >= 20) {
            return this.mPostDataBean.content;
        }
        Matcher matcher = Pattern.compile("^[^,。，？！；：.?;:'\n]+").matcher(this.mPostDataBean.content);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return this.mPostDataBean.content;
    }

    public String getDescriptionBy() {
        if (this.mPostDataBean != null) {
            this.mDescribe = this.mPostDataBean.member.getNickName();
        }
        return this.mDescribe;
    }

    public String getTargetUrl() {
        this.targetUrl = a.d(this.mPostDataBean.getId());
        return this.targetUrl;
    }

    public String getThumbPath() {
        return this.thumbFilePath;
    }

    public int getSharePlatformFlag() {
        return this.mSharePlatformFlag;
    }

    public void setSharePlatformFlag(int i) {
        this.mSharePlatformFlag = i;
    }

    public String getABTestId() {
        return null;
    }

    public long getReportShareId() {
        return 0;
    }

    public void prepareData(final ShareDataModel.a aVar) {
        super.prepareData(aVar);
        d.b(new d$a<File>(this) {
            final /* synthetic */ VoiceShareDataModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super File> jVar) {
                jVar.onNext(cn.xiaochuankeji.tieba.common.c.a.a(ImageRequest.a(b.a(((ServerImageBean) this.a.mPostDataBean.images.get(0)).id).b())));
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.f.a.c()).b(new j<File>(this) {
            final /* synthetic */ VoiceShareDataModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((File) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(File file) {
                this.b.thumbFilePath = file.getAbsolutePath();
                aVar.a();
            }
        });
    }
}
