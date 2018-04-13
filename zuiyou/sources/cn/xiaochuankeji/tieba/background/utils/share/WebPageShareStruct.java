package cn.xiaochuankeji.tieba.background.utils.share;

import java.io.Serializable;

public abstract class WebPageShareStruct implements Serializable {
    public static String assetFilePrefix = "asset:";
    static String pgcNameArg = "${pgc_name}";
    static String pgcNameReplacedArg = "\\$\\{pgc_name\\}";
    static String plistNameArg = "${plist_name}";
    static String plistNameReplacedArg = "\\$\\{plist_name\\}";
    static String postContentArg = "${post_content}";
    static String postContentReplacedArg = "\\$\\{post_content\\}";
    static String postLikeCountArg = "${like_count}";
    static String postLikeCountReplacedArg = "\\$\\{like_count\\}";
    static String postReviewCountArg = "${review_count}";
    static String postReviewCountReplacedArg = "\\$\\{review_count\\}";
    static String topicNameArg = "${topic_name}";
    static String topicNameReplacedArg = "\\$\\{topic_name\\}";
    public String suffix = null;
    protected String targetUrl;
    protected String thumbPath;

    abstract String getDescBy(int i);

    abstract String getTitleBy(int i);

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public String getThumbPath() {
        if (this.thumbPath == null) {
            return a.a();
        }
        return this.thumbPath;
    }
}
