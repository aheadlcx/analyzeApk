package qsbk.app.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.model.QiushiEmpty;
import qsbk.app.model.Qsjx;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;

public class EssenceFragment extends BaseArticleListViewFragment {
    private static final String Q = EssenceFragment.class.getSimpleName();
    public static final String QSJX_DATA = "qsjx_data";

    public static EssenceFragment newInstance(ArticleListConfig articleListConfig) {
        EssenceFragment essenceFragment = new EssenceFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        essenceFragment.setArguments(bundle);
        return essenceFragment;
    }

    protected void a() {
        super.a();
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase(Constants.QIUBAI_DOMAINS)) {
            this.u = Constants.DAY;
            this.v = "day";
            this.w = false;
        } else {
            this.u = articleListConfig.mUrl;
            this.v = articleListConfig.mUniqueName;
            this.w = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug(Q, "mUrl:" + this.u + " mUniqueName:" + this.v + " mIsShuffle:" + this.w);
    }

    protected boolean a(String str) {
        boolean a = super.a(str);
        if (!(this.j == null || this.j.size() <= 0 || (this.j.get(0) instanceof QiushiEmpty) || (this.j.get(0) instanceof Qsjx) || (this.j.get(0) instanceof QbAdItem))) {
            Qsjx c = c();
            if (c != null) {
                this.j.add(0, c);
                b().notifyDataSetChanged();
                this.A = true;
            }
        }
        return a;
    }

    private Qsjx c() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("qsjx_articles");
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            try {
                Qsjx qsjx = new Qsjx();
                qsjx.fromJsonObject(new JSONObject(sharePreferencesValue));
                return qsjx;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected boolean p() {
        return true;
    }
}
