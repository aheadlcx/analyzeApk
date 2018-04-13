package qsbk.app.fragments;

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
import qsbk.app.adapter.ParticipateAdapter;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.model.ParticipateArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;

public class MyParticipatesFragment extends PureArticleListFragment {
    public MyParticipatesFragment() {
        this.u = Constants.PARTICIPATE;
        this.v = "myparticipate";
        this.w = false;
    }

    protected boolean u() {
        return false;
    }

    protected BaseImageAdapter b() {
        return new ParticipateAdapter(this.B, this.m, this.j, getVotePoint(), this.v);
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
                                Article participateArticle = new ParticipateArticle(optJSONArray.optJSONObject(i));
                                if (this.e.equals("top_refresh") && i == 0 && !(getActivity() instanceof OthersQiuShiActivity) && !(getActivity() instanceof ManageQiuShiActivity)) {
                                    this.h = false;
                                    if (this.f != null) {
                                        this.g = this.f;
                                    }
                                    this.f = participateArticle;
                                }
                                if (!(this.j.contains(participateArticle) || ReportArticle.mReportArtcicle.keySet().contains(participateArticle.id))) {
                                    this.j.add(participateArticle);
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
