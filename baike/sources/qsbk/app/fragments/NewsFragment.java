package qsbk.app.fragments;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.android.phone.mrpc.core.Headers;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;
import qsbk.app.adapter.NewsAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.model.News;
import qsbk.app.model.NewsAdControl;
import qsbk.app.model.NewsEmpty;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

@Deprecated
public class NewsFragment extends Fragment implements OnScrollListener, IFeedsAdLoaded, IArticleList, IPageableFragment, PtrListener {
    protected static final Handler a = new Handler(Looper.getMainLooper());
    public static boolean canShowRefreshTips = false;
    private static NewsAdControl e = null;
    protected boolean b = false;
    protected AsyncDataLoader c = null;
    protected NewsEmpty d = new NewsEmpty();
    private ArrayList<Object> f = new ArrayList();
    private PtrLayout g;
    private ListView h;
    private NewsAdapter i;
    private int j = 1;
    private int k = 0;
    private Activity l;
    private View m;
    private boolean n = true;
    private String o;
    private boolean p;
    private boolean q = false;
    private String r = QYQShareInfo.TYPE_NEWS;
    private String s = Constants.NEWS_LIST;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private int w = 0;

    public static NewsFragment newInstance(ArticleListConfig articleListConfig) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    private void a() {
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase("http://m2.qiushibaike.com/")) {
            this.s = Constants.NEWS_LIST;
            this.r = "qiuwen";
            this.p = false;
        } else {
            this.s = articleListConfig.mUrl;
            this.r = articleListConfig.mUniqueName;
            this.p = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug("NewsFragment", "mUrl:" + this.s + " mUniqueName:" + this.r + " mIsShuffle:" + this.p);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.l = activity;
        if (this.l == null) {
            MainActivity.mInstance.reload();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        a();
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, null);
        a(inflate);
        if (UIHelper.isNightTheme()) {
            this.h.setDivider(new ColorDrawable(-15855598));
            this.h.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.h.setDivider(new ColorDrawable(-1381654));
            this.h.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        }
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.f.size() == 0) {
            for (int i = 0; i < 8; i++) {
                this.f.add(this.d);
            }
            this.i.notifyDataSetChanged();
            initHistoryData();
        }
    }

    protected void a(View view) {
        int i;
        int i2 = -14013903;
        this.g = (PtrLayout) view.findViewById(R.id.ptr);
        this.h = (ListView) view.findViewById(R.id.listview);
        this.i = new NewsAdapter(this.l, this.h, this.f);
        this.m = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.m.findViewById(R.id.foot_lin);
        View findViewById = this.m.findViewById(R.id.foot_left_line);
        View findViewById2 = this.m.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.m.findViewById(R.id.foot_remind);
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
        textView.setText("没内容了哦，去糗友圈看看吧  >");
        this.h.addFooterView(this.m);
        this.m.setOnClickListener(new hn(this));
        this.m.setVisibility(this.b ? 0 : 8);
        this.h.setAdapter(this.i);
        this.g.setRefreshEnable(true);
        this.g.setLoadMoreEnable(true);
        this.g.setPtrListener(this);
        this.g.setOnScrollListener(this);
    }

    public void onStart() {
        super.onStart();
        this.q = false;
    }

    public void onStop() {
        super.onStop();
        if (this.m != null) {
            this.b = this.m.getVisibility() == 0;
        }
    }

    public void onResume() {
        super.onResume();
    }

    private AsyncDataLoader b() {
        return new AsyncDataLoader(new NewsFragment$a(this, this.o), "news_list");
    }

    public void onFeedsAdLoaded() {
        c();
        this.i.notifyDataSetChanged();
    }

    public void onRefresh() {
        this.o = Headers.REFRESH;
        this.j = 1;
        this.c = b();
        this.c.execute(new Void[0]);
    }

    public void onLoadMore() {
        if (!this.q) {
            this.o = "load_more";
            this.c = b();
            this.c.execute(new Void[0]);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.v = i2;
        this.w = i3;
        if (this.u != i && this.u < i) {
            this.u = i;
        }
        if ((i + i2) + 5 >= i3 && this.g != null && this.n) {
            this.g.loadMore();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 25 || this.u + this.v < this.w - 2) {
            return false;
        }
        onLoadMore();
        return true;
    }

    public void scrollToTop() {
        if (this.h != null && this.f.size() > 0) {
            this.h.setSelection(0);
        }
    }

    public void refresh() {
        if (this.g != null) {
            this.g.refresh();
        }
    }

    public void doResume() {
        if (this.l instanceof QiushiListFragment$QiushiCallback) {
            ((QiushiListFragment$QiushiCallback) this.l).onResume(this);
        }
    }

    public void doPause() {
    }

    public void doStop() {
    }

    public boolean hasNewArticle() {
        return false;
    }

    private void c() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            d();
            this.i.notifyDataSetChanged();
        }
    }

    private void d() {
        if (e == null) {
            try {
                e = new NewsAdControl(new JSONObject(SharePreferenceUtils.getSharePreferencesValue("config")).optJSONObject("news_ads"));
            } catch (JSONException e) {
                e = new NewsAdControl();
            }
        }
        if (this.h != null && e.isShow) {
            FeedsAd.getInstance().insertNewsFeedAd(this.k, this.f, e);
        }
    }

    public void initHistoryData() {
        this.o = "load_history";
        this.q = true;
        TaskExecutor.getInstance().addTask(new ho(this));
    }

    private String e() {
        return FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), this.r);
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject;
            if (this.o.equals(Headers.REFRESH) || this.f.contains(this.d)) {
                this.f.clear();
                if (this.i != null) {
                    this.i.notifyDataSetChanged();
                }
            }
            this.k = this.f.size();
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject != null) {
                int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
                if (optInt == 0) {
                    News parseFromJsonObject;
                    if (!jSONObject.isNull("has_more")) {
                        this.n = jSONObject.optBoolean("has_more");
                    }
                    if (jSONObject.isNull("total")) {
                        this.t = 0;
                    } else {
                        this.t = jSONObject.optInt("total");
                    }
                    if (!jSONObject.isNull("data")) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("data");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            for (optInt = 0; optInt < optJSONArray.length(); optInt++) {
                                try {
                                    parseFromJsonObject = News.parseFromJsonObject(optJSONArray.getJSONObject(optInt));
                                    parseFromJsonObject.isHot = false;
                                    this.f.add(parseFromJsonObject);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }
                    if (!jSONObject.isNull("banners")) {
                        JSONArray optJSONArray2 = jSONObject.optJSONArray("banners");
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            try {
                                parseFromJsonObject = News.parseFromJsonObject(optJSONArray2.getJSONObject(0));
                                parseFromJsonObject.isHot = true;
                                if (!parseFromJsonObject.isTextNews()) {
                                    if (this.f.size() > 0 && ((News) this.f.get(0)).isHotNews()) {
                                        this.f.remove(0);
                                    }
                                    this.f.add(0, parseFromJsonObject);
                                }
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                } else if (optInt != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() || QsbkApp.currentUser == null) {
                    Object optString = jSONObject.optString("err_msg");
                    if (!TextUtils.isEmpty(optString)) {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                        return;
                    }
                    return;
                } else {
                    QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
                    return;
                }
            }
            c();
            this.i.notifyDataSetChanged();
            if (this.o.equals(Headers.REFRESH)) {
                this.g.refreshDone(true);
            } else if (this.o.equals("load_more")) {
                this.g.loadMoreDone(true);
            }
            if (this.j == 1 && this.o.equals(Headers.REFRESH) && canShowRefreshTips) {
                if (this.t > 0) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("为您刷新了%s条糗闻", new Object[]{Integer.valueOf(this.t)}), Integer.valueOf(0)).show();
                } else {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, "没有最新的糗闻啦~", Integer.valueOf(0)).show();
                }
            }
            if (this.n) {
                this.j = !this.o.equals("load_history") ? this.j + 1 : this.j;
                this.g.setLoadMoreEnable(true);
                this.b = false;
                this.m.setVisibility(8);
                return;
            }
            this.g.setLoadMoreEnable(false);
            this.b = true;
            this.m.setVisibility(0);
        }
    }
}
