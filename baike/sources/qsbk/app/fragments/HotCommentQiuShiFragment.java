package qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.Headers;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.OthersQiuShiActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.HotCommentAdapter;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.HotCommentArticle;
import qsbk.app.model.UserInfo;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;

public class HotCommentQiuShiFragment extends PureArticleListFragment {
    private UserInfo Q;

    public static HotCommentQiuShiFragment newInstance(@NonNull UserInfo userInfo) {
        HotCommentQiuShiFragment hotCommentQiuShiFragment = new HotCommentQiuShiFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userInfo);
        hotCommentQiuShiFragment.setArguments(bundle);
        return hotCommentQiuShiFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.Q = (UserInfo) arguments.getSerializable("user");
            if (this.Q != null) {
                this.u = String.format(Constants.QIUSHI_HOT_COMMENT, new Object[]{this.Q.userId});
            } else {
                return;
            }
        }
        this.v = "HotCommentQiuShiFragment";
        this.w = false;
    }

    protected boolean y() {
        return true;
    }

    protected BaseImageAdapter b() {
        return new HotCommentAdapter(this.B, this.m, this.j, getVotePoint(), this.v);
    }

    protected boolean a(String str) {
        try {
            if (this.j.contains(this.K)) {
                this.j.clear();
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0 || !isSelected()) {
                boolean optBoolean = jSONObject.optBoolean("has_more");
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.p = jSONObject.optInt("total");
                if (this.p == 0 && (optJSONArray == null || (optJSONArray != null && optJSONArray.length() == 0))) {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(8);
                    return false;
                } else if (this.p <= 0 || (optJSONArray != null && (optJSONArray == null || optJSONArray.length() != 0))) {
                    if (optBoolean) {
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
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                Article hotCommentArticle = new HotCommentArticle(optJSONObject);
                                if (this.e.equals("top_refresh") && i == 0 && !(getActivity() instanceof OthersQiuShiActivity) && !(getActivity() instanceof ManageQiuShiActivity)) {
                                    this.h = false;
                                    if (this.f != null) {
                                        this.g = this.f;
                                    }
                                    this.f = hotCommentArticle;
                                }
                                if (!(this.j.contains(hotCommentArticle) || ReportArticle.mReportArtcicle.keySet().contains(hotCommentArticle.id))) {
                                    this.j.add(hotCommentArticle);
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

    protected boolean u() {
        return false;
    }

    protected boolean r() {
        return false;
    }

    protected boolean o() {
        return false;
    }

    protected boolean s() {
        return false;
    }

    protected void d() {
        this.l.refresh();
    }

    protected void k() {
        this.m.setOnItemClickListener(new cz(this));
    }
}
