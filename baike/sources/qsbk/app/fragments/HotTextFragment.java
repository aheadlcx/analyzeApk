package qsbk.app.fragments;

import android.os.Bundle;
import qsbk.app.Constants;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.utils.DebugUtil;

public class HotTextFragment extends BaseArticleListViewFragment {
    private static final String Q = HotTextFragment.class.getSimpleName();

    public static HotTextFragment newInstance(ArticleListConfig articleListConfig) {
        HotTextFragment hotTextFragment = new HotTextFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        hotTextFragment.setArguments(bundle);
        return hotTextFragment;
    }

    protected void a() {
        super.a();
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase(Constants.QIUBAI_DOMAINS)) {
            this.u = Constants.TEXT;
            this.v = "text";
            this.w = false;
        } else {
            this.u = articleListConfig.mUrl;
            this.v = articleListConfig.mUniqueName;
            this.w = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug(Q, "mUrl:" + this.u + " mUniqueName:" + this.v + " mIsShuffle:" + this.w);
    }

    protected boolean o() {
        return false;
    }

    protected boolean u() {
        return false;
    }
}
