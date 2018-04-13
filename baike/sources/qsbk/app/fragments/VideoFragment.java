package qsbk.app.fragments;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import com.alipay.android.phone.mrpc.core.Headers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.SubscribeAdapter;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.model.LivePackage;
import qsbk.app.model.RssArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.utils.LiveRecommendManager;
import qsbk.app.utils.QiushiCircleVideoManager;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.qbnews.recommend.NewsRecommendManager;

public class VideoFragment extends BaseArticleListViewFragment {
    private static final String Q = VideoFragment.class.getSimpleName();

    public static VideoFragment newInstance(ArticleListConfig articleListConfig) {
        VideoFragment videoFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        videoFragment.setArguments(bundle);
        return videoFragment;
    }

    protected void a() {
        super.a();
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase(Constants.QIUBAI_DOMAINS)) {
            this.u = Constants.VIDEO;
            this.v = "video";
            this.w = false;
            return;
        }
        this.u = articleListConfig.mUrl;
        this.v = articleListConfig.mUniqueName;
        this.w = articleListConfig.mIsShuffle;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    protected void a(int i, boolean z) {
        super.a(i, z);
    }

    protected boolean p() {
        return false;
    }

    public void onRefresh() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        QiushiCircleVideoManager instance2 = QiushiCircleVideoManager.getInstance();
        if (instance2 != null) {
            instance2.load();
        }
        super.onRefresh();
    }

    public void onLoadMore() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        QiushiCircleVideoManager instance2 = QiushiCircleVideoManager.getInstance();
        if (instance2 != null) {
            instance2.load();
        }
        super.onLoadMore();
    }

    protected BaseImageAdapter b() {
        return new SubscribeAdapter(this.B, this.m, this.j, getVotePoint(), this.v, this);
    }

    protected boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("items");
            this.p = jSONObject.optInt("total");
            if (this.p == 0 && (optJSONArray == null || (optJSONArray != null && optJSONArray.length() == 0))) {
                this.q = true;
                this.l.setLoadMoreEnable(false);
                this.footView.setVisibility(8);
                return false;
            } else if (this.p <= 0 || (optJSONArray != null && (optJSONArray == null || optJSONArray.length() != 0))) {
                if (this.p > ((this.o - 1) * 30) + optJSONArray.length()) {
                    this.footView.setVisibility(8);
                    this.l.setLoadMoreEnable(true);
                    this.q = false;
                } else {
                    this.footView.setVisibility(0);
                    this.l.setLoadMoreEnable(false);
                    this.q = true;
                }
                if (jSONObject.optInt(Headers.REFRESH) > 0 && this.r && isSelected() && this.e.equals("top_refresh")) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("为您刷新了%s条糗事", new Object[]{Integer.valueOf(r2)}), Integer.valueOf(0)).show();
                }
                int length = optJSONArray.length();
                if (length == 0) {
                    this.q = true;
                }
                if (this.e.equals("top_refresh") || (this.o == 1 && !x())) {
                    this.j.clear();
                    this.D = 0;
                    NewsRecommendManager.setLastInsertPosition(this.v, -1);
                }
                this.k = this.j.size();
                int i = 0;
                while (i < length) {
                    try {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            Article rssArticle = new RssArticle(optJSONObject);
                            if (this.e.equals("top_refresh") && i == 0 && (this.B instanceof MainActivity)) {
                                this.h = false;
                                if (this.f != null) {
                                    this.g = this.f;
                                }
                                this.f = rssArticle;
                            }
                            if (!(this.j.contains(rssArticle) || ReportArticle.mReportArtcicle.keySet().contains(rssArticle.id))) {
                                this.j.add(rssArticle);
                            }
                        }
                    } catch (QiushibaikeException e) {
                    }
                    i++;
                }
                if (LiveRecommendManager.LIVE_RECOMMEND != null) {
                    Pair liveRecommendPackages = LivePackage.getLiveRecommendPackages(LiveRecommendManager.LIVE_RECOMMEND, false);
                    if (this.o == 1 && liveRecommendPackages != null && liveRecommendPackages.first != null && this.j.size() > 2) {
                        this.j.add(2, liveRecommendPackages.first);
                    }
                }
                i = this.j.size();
                if (QiushiListFragment.showCircleVideo) {
                    i -= this.k;
                    int size = QiushiCircleVideoManager.circleVideos.size();
                    int i2;
                    if (NetworkUtils.getInstance().isWifiAvailable() && i > 0) {
                        length = i / QiushiListFragment.circleWifiPosition;
                        i2 = 0;
                        while (true) {
                            if (i2 >= (length > size ? size : length)) {
                                break;
                            }
                            i = ((i2 + 1) * QiushiListFragment.circleWifiPosition) + this.k;
                            if (i > 1 && size > i2) {
                                this.j.add(i - 1, QiushiCircleVideoManager.circleVideos.get(i2));
                            }
                            i2++;
                        }
                    } else {
                        length = i / QiushiListFragment.circle4GPosition;
                        i2 = 0;
                        while (true) {
                            if (i2 >= (length > size ? size : length)) {
                                break;
                            }
                            i = ((i2 + 1) * QiushiListFragment.circle4GPosition) + this.k;
                            if (i > 1 && size > i2) {
                                this.j.add(i - 1, QiushiCircleVideoManager.circleVideos.get(i2));
                            }
                            i2++;
                        }
                    }
                }
                if (p()) {
                    q();
                }
                v();
                if (!jSONObject.isNull("ads")) {
                    a(jSONObject.getJSONArray("ads"));
                }
                this.j.remove(UserLoginGuideCard.instance);
                if (UserLoginGuideCard.isNeedToShow() && UserLoginGuideCard.getPosition() < this.j.size()) {
                    this.j.add(UserLoginGuideCard.getPosition(), UserLoginGuideCard.getInstance(getActivity()));
                }
                if (!this.q) {
                    this.o++;
                }
                return true;
            } else {
                this.q = true;
                this.l.setLoadMoreEnable(false);
                this.footView.setVisibility(0);
                return false;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }
}
