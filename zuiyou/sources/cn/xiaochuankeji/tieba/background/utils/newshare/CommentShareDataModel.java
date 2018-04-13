package cn.xiaochuankeji.tieba.background.utils.newshare;

import android.text.TextUtils;
import cn.htjyb.c.d;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.utils.c.b;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.json.ABTestingJson;
import com.tencent.connect.common.Constants;
import java.io.File;
import rx.j;

public class CommentShareDataModel extends ShareDataModel {
    private String mABTestingId;
    private Comment mComment;
    private String mDescription;
    private long mPid;
    private Post mPost;
    private int mSharePlatformFlag;
    private String mTargetUrl;
    private String mThumbPath;
    private String mTitle;

    private CommentShareDataModel(Comment comment, Post post) {
        this.mABTestingId = null;
        this.mPost = post;
        this.mComment = comment;
        this.mTargetUrl = a.a(this.mPost._ID, this.mComment._id);
        this.mThumbPath = cn.xiaochuankeji.tieba.background.review.a.a(this.mComment);
    }

    public CommentShareDataModel(Comment comment, Post post, int i) {
        this(comment, post);
        setSharePlatformFlag(i);
    }

    public CommentShareDataModel(Comment comment, long j, int i) {
        this.mABTestingId = null;
        this.mPid = j;
        this.mComment = comment;
        this.mTargetUrl = a.a(this.mPid, this.mComment._id);
        this.mThumbPath = cn.xiaochuankeji.tieba.background.review.a.a(this.mComment);
        setSharePlatformFlag(i);
    }

    public void setSharePlatformFlag(int i) {
        this.mSharePlatformFlag = i;
        b e = cn.xiaochuankeji.tieba.background.utils.c.a.c().e();
        if (4 == i) {
            this.mTitle = e.q;
            this.mDescription = e.r;
        } else if (2 == i) {
            this.mTitle = e.o;
            this.mDescription = e.p;
        } else if (5 == i) {
            this.mTitle = e.m;
            this.mDescription = e.n;
        } else if (1 == i) {
            this.mTitle = e.k;
            this.mDescription = e.l;
        } else if (3 == i) {
            this.mTitle = e.s;
            this.mDescription = e.t;
        }
        if (TextUtils.isEmpty(this.mTitle)) {
            this.mTitle = "来自最右的搞笑图片";
        }
        if (TextUtils.isEmpty(this.mDescription)) {
            this.mDescription = this.mComment._commentContent;
        }
    }

    public void prepareData(ShareDataModel.a aVar) {
        super.prepareData(aVar);
        if (cn.xiaochuankeji.tieba.background.utils.c.a.c().e().b()) {
            new cn.xiaochuankeji.tieba.api.config.b().a(this.mPost.campaignId).a(rx.a.b.a.a()).b(new j<ABTestingJson>(this) {
                final /* synthetic */ CommentShareDataModel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((ABTestingJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.prepareFinish();
                }

                public void a(ABTestingJson aBTestingJson) {
                    this.a.mABTestingId = aBTestingJson.abtestingId;
                    if (!TextUtils.isEmpty(this.a.mABTestingId)) {
                        this.a.mTargetUrl = this.a.mTargetUrl + this.a.b(this.a.mABTestingId);
                    }
                    Object shareReviewTitle = aBTestingJson.getShareReviewTitle(this.a.mSharePlatformFlag);
                    if (!TextUtils.isEmpty(shareReviewTitle)) {
                        this.a.mTitle = shareReviewTitle;
                    }
                    shareReviewTitle = aBTestingJson.getShareReviewDescription(this.a.mSharePlatformFlag);
                    if (!TextUtils.isEmpty(shareReviewTitle)) {
                        this.a.mDescription = shareReviewTitle;
                    }
                    shareReviewTitle = aBTestingJson.getShareReviewIconUrl(this.a.mSharePlatformFlag);
                    if (TextUtils.isEmpty(shareReviewTitle)) {
                        this.a.prepareFinish();
                    } else {
                        this.a.a(shareReviewTitle);
                    }
                }
            });
        } else {
            prepareFinish();
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
            final /* synthetic */ CommentShareDataModel b;

            public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                if (dVar.c.a) {
                    this.b.mThumbPath = str2;
                }
                this.b.prepareFinish();
            }
        }).b();
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
            str2 = "";
            if (!(this.mPost == null || TextUtils.isEmpty(this.mPost._postContent))) {
                str2 = this.mPost._postContent;
            }
            str = str.replaceAll(postContentReplacedArg, str2);
        }
        if (str.contains(topicNameArg)) {
            str2 = "";
            if (!(this.mPost == null || this.mPost._topic == null)) {
                str2 = this.mPost._topic._topicName;
            }
            str = str.replaceAll(topicNameReplacedArg, str2);
        }
        if (str.contains(postLikeCountArg)) {
            if (this.mPost == null || this.mPost._likeCount < 10) {
                str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            } else {
                str2 = cn.xiaochuankeji.tieba.ui.utils.d.b(this.mPost._likeCount);
            }
            str = str.replaceAll(postLikeCountReplacedArg, str2);
        }
        if (str.contains(postReviewCountArg)) {
            str2 = "";
            if (this.mPost != null && this.mPost._commentCount > 0) {
                str2 = this.mPost._commentCount + "";
            }
            str = str.replaceAll(postReviewCountReplacedArg, str2);
        }
        if (str.contains(reviewContentArg)) {
            str = str.replaceAll(reviewContentReplacedArg, this.mComment._commentContent);
        }
        if (str.contains(shareUserArg)) {
            str2 = cn.xiaochuankeji.tieba.background.a.g().q().getName();
            if (TextUtils.isEmpty(str2) || str2.contains("游客")) {
                str2 = "右友";
            }
            str = str.replaceAll(shareUserReplacedArg, str2);
        }
        if (str.contains(reviewUserArg)) {
            str = str.replaceAll(reviewUserReplacedArg, this.mComment.getWriterMember().getName());
        }
        if (!str.contains(postUserArg)) {
            return str;
        }
        str2 = "";
        if (!(this.mPost == null || this.mPost._member == null)) {
            str2 = this.mPost._member.getName();
        }
        return str.replaceAll(postUserReplacedArg, str2);
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
        return this.mComment._id;
    }
}
