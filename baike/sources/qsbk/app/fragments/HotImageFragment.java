package qsbk.app.fragments;

import android.os.Bundle;
import qsbk.app.Constants;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.utils.DebugUtil;

public class HotImageFragment extends BaseArticleListViewFragment {
    private static final String Q = HotImageFragment.class.getSimpleName();

    public static HotImageFragment newInstance(ArticleListConfig articleListConfig) {
        HotImageFragment hotImageFragment = new HotImageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        hotImageFragment.setArguments(bundle);
        return hotImageFragment;
    }

    protected void a() {
        super.a();
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase(Constants.QIUBAI_DOMAINS)) {
            this.u = Constants.HOT_IMAGES;
            this.v = "imageshot";
            this.w = false;
        } else {
            this.u = articleListConfig.mUrl;
            this.v = articleListConfig.mUniqueName;
            this.w = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug(Q, "mUrl:" + this.u + " mUniqueName:" + this.v + " mIsShuffle:" + this.w);
    }

    protected boolean p() {
        return true;
    }
}
