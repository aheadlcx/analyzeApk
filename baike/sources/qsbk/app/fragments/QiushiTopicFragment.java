package qsbk.app.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import com.alipay.android.phone.mrpc.core.Headers;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.RssArticle;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;

public class QiushiTopicFragment extends BaseArticleListViewFragment {
    private int Q = 0;
    private QiushiTopic R;

    public static QiushiTopicFragment newInstance(int i) {
        QiushiTopicFragment qiushiTopicFragment = new QiushiTopicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TAG", i);
        qiushiTopicFragment.setArguments(bundle);
        return qiushiTopicFragment;
    }

    public static QiushiTopicFragment newInstance(int i, QiushiTopic qiushiTopic) {
        QiushiTopicFragment qiushiTopicFragment = new QiushiTopicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TAG", i);
        bundle.putSerializable("topic", qiushiTopic);
        qiushiTopicFragment.setArguments(bundle);
        return qiushiTopicFragment;
    }

    protected void a() {
        this.Q = getArguments().getInt("TAG");
        if (this.Q == 0) {
            this.u = String.format(Constants.QIUSHI_TOPIC_DEFAULT, new Object[]{Integer.valueOf(this.R.id)});
        } else if (this.Q == 1) {
            this.u = String.format(Constants.QIUSHI_TOPIC_NEW, new Object[]{Integer.valueOf(this.R.id)});
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.R = (QiushiTopic) getArguments().getSerializable("topic");
        if (this.R == null) {
            getActivity().finish();
            return null;
        }
        a();
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_qiushi_topic_listview, null);
        a(inflate);
        if (UIHelper.isNightTheme()) {
            this.m.setDivider(new ColorDrawable(-16777216));
            this.m.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.m.setDivider(null);
            this.m.setDividerHeight(0);
        }
        this.F = new iy(this, this.m);
        this.F.setEnable(false);
        if (isSelected()) {
            this.F.autoPlay();
            return inflate;
        }
        this.F.stopAll();
        return inflate;
    }

    protected void a(View view) {
        this.l = (PtrLayout) view.findViewById(R.id.ptr);
        this.m = (ListView) view.findViewById(R.id.id_qiushi_topic_listview);
        this.l.setLoadMoreEnable(false);
        this.l.setPtrListener(this);
        this.l.setOnScrollListener(this);
        this.i = b();
        this.m.setAdapter(this.i);
    }

    protected boolean x() {
        return false;
    }

    protected boolean o() {
        return false;
    }

    protected BaseImageAdapter b() {
        return super.b();
    }

    protected void d() {
        this.r = true;
        onRefresh();
    }

    public void onRefresh() {
        super.onRefresh();
    }

    protected boolean r() {
        return false;
    }

    protected boolean p() {
        return false;
    }

    protected boolean u() {
        return false;
    }

    protected boolean s() {
        return false;
    }

    public void onLoadMore() {
        super.onLoadMore();
    }

    public void onResume() {
        super.onResume();
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        super.onScroll(absListView, i, i2, i3);
    }

    protected boolean a(String str) {
        if (this.j.contains(this.K)) {
            this.j.clear();
        }
        JSONObject jSONObject = new JSONObject(str);
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
        if (optInt == 0 || !isSelected()) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                this.p = jSONObject.optInt("total");
                boolean optBoolean = jSONObject.optBoolean("has_more");
                if (jSONObject.has("topic")) {
                    QiushiTopic qiushiTopic = new QiushiTopic();
                    qiushiTopic.constructJson(jSONObject.optJSONObject("topic"));
                    QiushiTopicBus.updateQiushiTopic(qiushiTopic, Integer.valueOf(this.Q));
                }
                if (optBoolean) {
                    this.l.setLoadMoreEnable(true);
                    this.q = false;
                } else {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                }
                if (jSONObject.optInt(Headers.REFRESH) > 0 && this.r && isSelected() && this.e.equals("top_refresh")) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("为您刷新了%s条糗事", new Object[]{Integer.valueOf(r2)}), Integer.valueOf(0)).show();
                }
                int length = optJSONArray.length();
                if (this.o == 1) {
                    this.j.clear();
                }
                this.k = this.j.size();
                for (optInt = 0; optInt < length; optInt++) {
                    try {
                        if (optJSONArray.optJSONObject(optInt) != null) {
                            Article rssArticle = y() ? new RssArticle(optJSONArray.optJSONObject(optInt)) : new Article(optJSONArray.optJSONObject(optInt));
                            if (!(this.j.contains(rssArticle) || ReportArticle.mReportArtcicle.keySet().contains(rssArticle.id))) {
                                this.j.add(rssArticle);
                            }
                        }
                    } catch (QiushibaikeException e) {
                        e.printStackTrace();
                    }
                }
                if (this.o == 1 && this.w) {
                    sort(this.j);
                }
                if (!this.q) {
                    this.o++;
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } else if (optInt != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() || QsbkApp.currentUser == null) {
            Object optString = jSONObject.optString("err_msg");
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
            return false;
        } else {
            QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            return false;
        }
    }
}
