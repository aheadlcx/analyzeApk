package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.android.phone.mrpc.core.Headers;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.OthersQiuShiActivity;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.TipsHelper;

public class PureArticleListFragment extends BaseArticleListViewFragment {
    protected boolean o() {
        return false;
    }

    public void onResume() {
        super.onResume();
        doResume();
    }

    public void onPause() {
        super.onPause();
        doPause();
    }

    protected void a(View view) {
        int i;
        int i2 = -14013903;
        setForceLoad(false);
        this.l = (PtrLayout) view.findViewById(R.id.ptr);
        this.m = (ListView) view.findViewById(R.id.listview);
        this.l.setLoadMoreEnable(false);
        this.l.setPtrListener(this);
        this.l.setOnScrollListener(this);
        this.l.setLoadMoreState(2, null);
        this.footView = LayoutInflater.from(getActivity()).inflate(R.layout.my_dynamic_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.footView.findViewById(R.id.foot_lin);
        View findViewById = this.footView.findViewById(R.id.foot_left_line);
        View findViewById2 = this.footView.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.footView.findViewById(R.id.foot_remind);
        linearLayout.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
        if (UIHelper.isNightTheme()) {
            i = -14013903;
        } else {
            i = -2236961;
        }
        findViewById.setBackgroundColor(i);
        if (!UIHelper.isNightTheme()) {
            i2 = -2236961;
        }
        findViewById2.setBackgroundColor(i2);
        textView.setTextColor(UIHelper.isNightTheme() ? -12829625 : -5197644);
        textView.setText("到底啦~");
        this.m.addFooterView(this.footView);
        this.footView.setVisibility(8);
        this.i = b();
        this.m.setAdapter(this.i);
        this.n = new TipsHelper(view.findViewById(R.id.tips));
    }

    protected boolean x() {
        return false;
    }

    protected boolean a(String str) {
        try {
            if (this.j.contains(this.K)) {
                this.j.clear();
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0 || !isSelected()) {
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.p = jSONObject.optInt("total");
                if (this.p == 0 && (optJSONArray == null || (optJSONArray != null && optJSONArray.length() == 0))) {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(8);
                    return false;
                } else if (this.p <= 0 || (optJSONArray != null && (optJSONArray == null || optJSONArray.length() != 0))) {
                    if (this.p > ((this.o - 1) * 30) + optJSONArray.length()) {
                        this.l.setLoadMoreEnable(true);
                        this.footView.setVisibility(8);
                    } else {
                        this.l.setLoadMoreEnable(false);
                        this.footView.setVisibility(0);
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
                    }
                    this.j.size();
                    int i = 0;
                    while (i < length) {
                        try {
                            if (optJSONArray.optJSONObject(i) != null) {
                                Article rssArticle;
                                if (y()) {
                                    rssArticle = new RssArticle(optJSONArray.optJSONObject(i));
                                } else {
                                    rssArticle = new Article(optJSONArray.optJSONObject(i));
                                }
                                if (this.e.equals("top_refresh") && i == 0 && !(getActivity() instanceof OthersQiuShiActivity) && !(getActivity() instanceof ManageQiuShiActivity)) {
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
                    return true;
                } else {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(0);
                    return false;
                }
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
