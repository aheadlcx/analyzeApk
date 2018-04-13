package qsbk.app.fragments;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import com.alipay.android.phone.mrpc.core.Headers;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.OthersQiuShiActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class MyCollectionsFragment extends PureArticleListFragment {
    public MyCollectionsFragment() {
        this.u = Constants.COLLECT_LIST;
        StringBuilder append = new StringBuilder().append("mycollection");
        QsbkApp.getInstance();
        this.v = append.append(QsbkApp.currentUser.userId).toString();
        this.w = false;
    }

    protected boolean u() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onRefresh() {
        setForceLoad(true);
        super.onRefresh();
    }

    protected boolean a(String str) {
        try {
            if (this.j.contains(this.K)) {
                this.j.clear();
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0 || !isSelected()) {
                int i;
                int length;
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.p = jSONObject.optInt("total");
                if (jSONObject.optBoolean("has_more")) {
                    this.l.setLoadMoreEnable(true);
                } else {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    View view = this.footView;
                    i = (optJSONArray == null || optJSONArray.length() != 0) ? 0 : 8;
                    view.setVisibility(i);
                }
                if (jSONObject.optInt(Headers.REFRESH) > 0 && this.r && isSelected() && this.e.equals("top_refresh")) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("为您刷新了%s条糗事", new Object[]{Integer.valueOf(i)}), Integer.valueOf(0)).show();
                }
                if (optJSONArray != null) {
                    length = optJSONArray.length();
                } else {
                    boolean z = false;
                }
                if (length == 0) {
                    this.q = true;
                }
                if (this.e.equals("top_refresh") || (this.o == 1 && !x())) {
                    this.j.clear();
                    this.D = 0;
                }
                this.j.size();
                int i2 = 0;
                while (i2 < length) {
                    try {
                        if (optJSONArray.optJSONObject(i2) != null) {
                            Article rssArticle;
                            if (y()) {
                                rssArticle = new RssArticle(optJSONArray.optJSONObject(i2));
                            } else {
                                rssArticle = new Article(optJSONArray.optJSONObject(i2));
                            }
                            if (this.e.equals("top_refresh") && i2 == 0 && !(getActivity() instanceof OthersQiuShiActivity) && !(getActivity() instanceof ManageQiuShiActivity)) {
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
                    i2++;
                }
                if (this.o == 1 && this.w) {
                    sort(this.j);
                }
                if (r() && this.o == 1 && this.C != null && this.j.size() > 15) {
                    this.j.add(15, this.C);
                }
                if (o() && !jSONObject.isNull("ads")) {
                    a(jSONObject.getJSONArray("ads"));
                }
                this.j.remove(UserLoginGuideCard.instance);
                if (s() && UserLoginGuideCard.isNeedToShow() && UserLoginGuideCard.getPosition() < this.j.size()) {
                    this.j.add(UserLoginGuideCard.getPosition(), UserLoginGuideCard.getInstance(getActivity()));
                }
                if (!this.q) {
                    this.o++;
                }
                SharePreferenceUtils.setCollectionsByArticle(this.j);
                SharePreferenceUtils.getCollections();
                return true;
            }
            Object optString = jSONObject.optString("err_msg");
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    protected void a(Article article) {
        super.a(article);
        this.j.remove(article);
        this.i.notifyDataSetChanged();
        new fx(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        SharePreferenceUtils.setCollectionsByArticle(this.j);
        SharePreferenceUtils.getCollections();
    }
}
