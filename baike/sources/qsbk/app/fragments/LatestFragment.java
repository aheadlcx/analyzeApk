package qsbk.app.fragments;

import qsbk.app.Constants;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.utils.DebugUtil;

@Deprecated
public class LatestFragment extends BaseArticleListViewFragment {
    private static final String Q = LatestFragment.class.getSimpleName();

    public LatestFragment() {
        this(null);
    }

    public LatestFragment(ArticleListConfig articleListConfig) {
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase("http://m2.qiushibaike.com/")) {
            this.u = Constants.LATEST;
            this.v = "latest";
            this.w = false;
        } else {
            this.u = articleListConfig.mUrl;
            this.v = articleListConfig.mUniqueName;
            this.w = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug(Q, "mUrl:" + this.u + " mUniqueName:" + this.v + " mIsShuffle:" + this.w);
    }
}
