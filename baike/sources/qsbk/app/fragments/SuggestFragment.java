package qsbk.app.fragments;

import qsbk.app.Constants;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.utils.DebugUtil;

public class SuggestFragment extends BaseArticleListViewFragment {
    private static final String Q = SuggestFragment.class.getSimpleName();

    public SuggestFragment() {
        this(null);
    }

    public SuggestFragment(ArticleListConfig articleListConfig) {
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase("http://m2.qiushibaike.com/")) {
            this.u = Constants.SUGGEST;
            this.v = "suggest";
            this.w = false;
        } else {
            this.u = articleListConfig.mUrl;
            this.v = articleListConfig.mUniqueName;
            this.w = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug(Q, "mUrl:" + this.u + " mUniqueName:" + this.v + " mIsShuffle:" + this.w);
    }
}
