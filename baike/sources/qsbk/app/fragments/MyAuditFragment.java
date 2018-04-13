package qsbk.app.fragments;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import com.alipay.android.phone.mrpc.core.Headers;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.OthersQiuShiActivity;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class MyAuditFragment extends PureArticleListFragment {
    public static MyAuditFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(IndexEntry.COLUMN_NAME_DATE, str);
        MyAuditFragment myAuditFragment = new MyAuditFragment();
        myAuditFragment.setArguments(bundle);
        return myAuditFragment;
    }

    public MyAuditFragment() {
        StringBuilder append = new StringBuilder().append("myAudit");
        QsbkApp.getInstance();
        this.v = append.append(QsbkApp.currentUser.userId).toString();
        this.w = false;
    }

    protected boolean u() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CharSequence charSequence = null;
        Bundle arguments = getArguments();
        if (arguments != null) {
            charSequence = arguments.getString(IndexEntry.COLUMN_NAME_DATE);
        }
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        }
        this.u = String.format(Constants.MY_AUDIT, new Object[]{charSequence});
    }

    public void onRefresh() {
        setForceLoad(true);
        super.onRefresh();
    }

    protected boolean a(String str) {
        int i = 8;
        try {
            if (this.j.contains(this.K)) {
                this.j.clear();
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0 || !isSelected()) {
                int length;
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.p = jSONObject.optInt("total");
                if (jSONObject.optBoolean("has_more")) {
                    this.l.setLoadMoreEnable(true);
                } else {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    View view = this.footView;
                    if (optJSONArray == null || optJSONArray.length() != 0) {
                        i = 0;
                    }
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
                if (this.o == 1 && this.j.size() == 0) {
                    this.l.setLoadMoreState(0, "");
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(8);
                } else {
                    this.footView.setVisibility(0);
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
}
