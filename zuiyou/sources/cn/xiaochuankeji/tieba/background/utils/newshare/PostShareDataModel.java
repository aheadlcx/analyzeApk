package cn.xiaochuankeji.tieba.background.utils.newshare;

import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.c.b;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.json.ABTestingJson;
import cn.xiaochuankeji.tieba.ui.post.e;
import com.tencent.connect.common.Constants;
import java.io.File;
import rx.j;

public class PostShareDataModel extends ShareDataModel {
    private String mABTestingIconUrl;
    private String mABTestingId;
    private Comment mComment;
    private String mDescription;
    private Post mPost;
    private int mSharePlatformFlag;
    private String mTargetUrl;
    private String mThumbPath;
    private String mTitle;

    public PostShareDataModel(Post post, Comment comment) {
        this.mPost = post;
        this.mComment = comment;
        this.mTargetUrl = a.a(this.mPost);
    }

    public PostShareDataModel(Post post, Comment comment, int i) {
        this(post, comment);
        setSharePlatformFlag(i);
    }

    public void setSharePlatformFlag(int i) {
        this.mSharePlatformFlag = i;
        b e = cn.xiaochuankeji.tieba.background.utils.c.a.c().e();
        if (4 == i) {
            this.mTitle = e.g;
            this.mDescription = e.h;
        } else if (2 == i) {
            this.mTitle = e.e;
            this.mDescription = e.f;
        } else if (5 == i) {
            this.mTitle = e.c;
            this.mDescription = e.d;
        } else if (1 == i) {
            this.mTitle = e.a;
            this.mDescription = e.b;
        } else if (3 == i) {
            this.mTitle = e.i;
            this.mDescription = e.j;
        }
        if (TextUtils.isEmpty(this.mTitle)) {
            this.mTitle = this.mPost._topic._topicName;
        }
        if (TextUtils.isEmpty(this.mDescription)) {
            this.mDescription = this.mPost._postContent;
        }
    }

    protected void setAbtestingData(String str, String str2) {
        this.mABTestingId = str;
        this.mABTestingIconUrl = str2;
        if (!TextUtils.isEmpty(this.mABTestingId)) {
            this.mTargetUrl += b(this.mABTestingId);
        }
    }

    public void prepareData(ShareDataModel.a aVar) {
        super.prepareData(aVar);
        boolean a = cn.xiaochuankeji.tieba.background.utils.c.a.c().e().a();
        if (TextUtils.isEmpty(this.mABTestingId) && a) {
            new cn.xiaochuankeji.tieba.api.config.b().a(this.mPost.campaignId).a(rx.a.b.a.a()).b(new j<ABTestingJson>(this) {
                final /* synthetic */ PostShareDataModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((ABTestingJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.a();
                }

                public void a(ABTestingJson aBTestingJson) {
                    this.a.mABTestingId = aBTestingJson.abtestingId;
                    if (!TextUtils.isEmpty(this.a.mABTestingId)) {
                        this.a.mTargetUrl = this.a.mTargetUrl + this.a.b(this.a.mABTestingId);
                    }
                    Object sharePostTitle = aBTestingJson.getSharePostTitle(this.a.mSharePlatformFlag);
                    if (!TextUtils.isEmpty(sharePostTitle)) {
                        this.a.mTitle = sharePostTitle;
                    }
                    sharePostTitle = aBTestingJson.getSharePostDescription(this.a.mSharePlatformFlag);
                    if (!TextUtils.isEmpty(sharePostTitle)) {
                        this.a.mDescription = sharePostTitle;
                    }
                    sharePostTitle = aBTestingJson.getSharePostIconUrl(this.a.mSharePlatformFlag);
                    if (TextUtils.isEmpty(sharePostTitle)) {
                        this.a.a();
                    } else {
                        this.a.a(sharePostTitle);
                    }
                }
            });
        } else if (TextUtils.isEmpty(this.mABTestingIconUrl)) {
            a();
        } else {
            a(this.mABTestingIconUrl);
        }
    }

    private void a(String str) {
        final String str2 = cn.xiaochuankeji.tieba.background.a.e().D() + d.c(str).substring(0, 16);
        if (new File(str2).exists()) {
            this.mThumbPath = str2;
            prepareFinish();
            return;
        }
        new cn.htjyb.netlib.a(str, cn.htjyb.netlib.b.a(), str2, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ PostShareDataModel b;

            public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                if (dVar.c.a) {
                    this.b.mThumbPath = str2;
                    this.b.prepareFinish();
                    return;
                }
                this.b.a();
            }
        }).b();
    }

    private void a() {
        if (3 == this.mSharePlatformFlag) {
            cn.htjyb.b.a a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kShareImg, this.mPost._ID);
            if (a.hasLocalFile()) {
                this.mThumbPath = a.cachePath();
                prepareFinish();
                return;
            }
            a.registerPictureDownloadListener(new cn.htjyb.b.a.a(this) {
                final /* synthetic */ PostShareDataModel a;

                {
                    this.a = r1;
                }

                public void a(cn.htjyb.b.a aVar, boolean z, int i, String str) {
                    if (z) {
                        this.a.mThumbPath = aVar.cachePath();
                    }
                    this.a.prepareFinish();
                }
            });
            a.download(true);
            return;
        }
        this.mThumbPath = e.a(this.mPost);
        prepareFinish();
    }

    private String b(String str) {
        StringBuilder stringBuilder = new StringBuilder("?zy_to=");
        String a = cn.xiaochuankeji.tieba.d.d.a(this.mSharePlatformFlag);
        stringBuilder.append(a);
        stringBuilder.append("&to=");
        stringBuilder.append(a);
        stringBuilder.append("&share_count=1&abtesting=");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public String getTitleBy() {
        return c(this.mTitle);
    }

    public String getDescriptionBy() {
        return c(this.mDescription);
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2;
        if (str.contains(postContentArg)) {
            str = str.replaceAll(postContentReplacedArg, this.mPost._postContent);
        }
        if (str.contains(topicNameArg)) {
            str = str.replaceAll(topicNameReplacedArg, this.mPost._topic._topicName);
        }
        if (str.contains(postLikeCountArg)) {
            if (this.mPost._likeCount < 10) {
                str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            } else {
                str2 = cn.xiaochuankeji.tieba.ui.utils.d.b(this.mPost._likeCount);
            }
            str = str.replaceAll(postLikeCountReplacedArg, str2);
        }
        if (str.contains(postReviewCountArg)) {
            str = str.replaceAll(postReviewCountReplacedArg, this.mPost._commentCount + "");
        }
        if (str.contains(reviewContentArg)) {
            str2 = "";
            if (!(this.mComment == null || TextUtils.isEmpty(this.mComment._commentContent))) {
                str2 = this.mComment._commentContent;
            }
            str = str.replaceAll(reviewContentReplacedArg, str2);
        }
        if (str.contains(shareUserArg)) {
            str2 = cn.xiaochuankeji.tieba.background.a.g().q().getName();
            if (TextUtils.isEmpty(str2) || str2.contains("游客")) {
                str2 = "右友";
            }
            str = str.replaceAll(shareUserReplacedArg, str2);
        }
        if (str.contains(reviewUserArg)) {
            str2 = "";
            if (!(this.mComment == null || TextUtils.isEmpty(this.mComment.getWriterMember().getName()))) {
                str2 = this.mComment.getWriterMember().getName();
            }
            str = str.replaceAll(reviewUserReplacedArg, str2);
        }
        if (str.contains(postUserArg)) {
            return str.replaceAll(postUserReplacedArg, this.mPost._member.getName());
        }
        return str;
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

    public String getABTestId() {
        return this.mABTestingId;
    }

    public long getReportShareId() {
        return this.mPost._ID;
    }
}
