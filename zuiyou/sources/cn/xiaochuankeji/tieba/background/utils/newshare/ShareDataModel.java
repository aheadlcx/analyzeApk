package cn.xiaochuankeji.tieba.background.utils.newshare;

import java.io.Serializable;

public abstract class ShareDataModel implements Serializable {
    protected static String postContentArg = "${post_content}";
    protected static String postContentReplacedArg = "\\$\\{post_content\\}";
    protected static String postLikeCountArg = "${like_count}";
    protected static String postLikeCountReplacedArg = "\\$\\{like_count\\}";
    protected static String postReviewCountArg = "${review_count}";
    protected static String postReviewCountReplacedArg = "\\$\\{review_count\\}";
    protected static String postUserArg = "${post_user}";
    protected static String postUserReplacedArg = "\\$\\{post_user\\}";
    protected static String reviewContentArg = "${review_content}";
    protected static String reviewContentReplacedArg = "\\$\\{review_content\\}";
    protected static String reviewUserArg = "${review_user}";
    protected static String reviewUserReplacedArg = "\\$\\{review_user\\}";
    protected static String shareUserArg = "${share_user}";
    protected static String shareUserReplacedArg = "\\$\\{share_user\\}";
    protected static String topicNameArg = "${topic_name}";
    protected static String topicNameReplacedArg = "\\$\\{topic_name\\}";
    private a mCallBack;

    public interface a {
        void a();
    }

    public abstract String getABTestId();

    public abstract String getDescriptionBy();

    public abstract long getReportShareId();

    public abstract int getSharePlatformFlag();

    public abstract String getTargetUrl();

    public abstract String getThumbPath();

    public abstract String getTitleBy();

    public abstract void setSharePlatformFlag(int i);

    public void prepareData(a aVar) {
        this.mCallBack = aVar;
    }

    protected void prepareFinish() {
        if (this.mCallBack != null) {
            this.mCallBack.a();
        }
    }
}
