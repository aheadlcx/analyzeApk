package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.alipay.android.phone.mrpc.core.Headers;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.base.BaseQiuyouquanFragment;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.adapter.QiuYouCircleAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.fragments.QiuyouCircleFragment.IPageFocus;
import qsbk.app.http.HttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.LivePackage;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LiveRecommendManager;
import qsbk.app.utils.QiuyouCircleFragmentNotificationViewHelper;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.ptr.AlphaAnimOffsetListener;
import qsbk.app.widget.qiuyoucircle.NormalCell;

public class CircleVideoFragment extends BaseQiuyouquanFragment implements OnScrollListener, ILoadingState, IArticleList, IPageFocus, OnArticleUpdateListener, PtrListener {
    private static final String a = CircleVideoFragment.class.getSimpleName();
    private HttpTask b;
    private int c = 1;
    private ArrayList<Object> d;
    private int e = 0;
    private PtrLayout f;
    private ListView g;
    private VideoInListHelper h;
    private TipsHelper i;
    private QiuYouCircleAdapter j;
    private String k;
    private QiuyouCircleFragmentNotificationViewHelper l;
    private boolean m = false;
    private boolean n = false;
    private WeakReference<NativeMediaADData> o = null;
    private boolean p = false;
    private final BroadcastReceiver q = new bt(this);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, viewGroup, false);
        this.f = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.g = (ListView) inflate.findViewById(R.id.listview);
        this.f.setRefreshEnable(true);
        this.f.setLoadMoreEnable(false);
        this.f.setPtrListener(this);
        if (UIHelper.isNightTheme()) {
            this.g.setDivider(new ColorDrawable(-16777216));
            this.g.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.g.setDivider(null);
            this.g.setDividerHeight(0);
        }
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.j = new QiuYouCircleAdapter(this.d, getActivity(), (ShareUtils$OnCircleShareStartListener) this, "video");
        this.g.setAdapter(this.j);
        this.g.setOnItemClickListener(new bu(this));
        this.g.setOnItemLongClickListener(new bv(this));
        this.h = new bw(this, this.g);
        this.h.setEnable(false);
        this.f.setOnScrollListener(this);
        ReadCircle.trackListView(this.f);
        this.i = new TipsHelper(inflate.findViewById(R.id.tips));
        this.l = QiuyouCircleFragmentNotificationViewHelper.newInstance4ChildFragment(this.f, this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.q, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            this.f.setOnScrollOffsetListener(new AlphaAnimOffsetListener(((QiuyouCircleFragment) parentFragment).getActivityNotification()));
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.l != null) {
            this.l.release();
        }
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.q, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
    }

    public void setSelected(boolean z) {
        this.p = z;
        b();
    }

    public void onResume() {
        super.onResume();
        this.n = true;
        Object obj = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        if (!TextUtils.equals(this.k, obj) || this.d.size() == 0) {
            this.k = obj;
            this.c = 1;
            this.d.clear();
            this.j.notifyDataSetChanged();
            loadArticles();
        }
        b();
    }

    private void b() {
        if (this.h == null) {
            return;
        }
        if (this.n && this.p) {
            this.f.post(new bx(this));
        } else {
            this.h.stopAll();
        }
    }

    public void notifyAdapterDataChanged() {
        this.j.notifyDataSetChanged();
        if (this.n) {
            b();
        }
    }

    public void onPause() {
        super.onPause();
        this.n = false;
        this.h.stopAll();
    }

    private void c() {
    }

    private void d() {
    }

    public void refresh() {
        if (QsbkApp.currentUser != null && this.g != null) {
            this.g.setSelection(0);
            this.f.refresh();
        }
    }

    private void a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
        if (this.c == 1) {
            if (jSONArray.length() > 0) {
                this.d.clear();
            }
            this.f.refreshDone();
        } else {
            this.f.loadMoreDone(true);
        }
        this.e = this.d.size();
        int length = jSONArray.length();
        if (this.c == 1 && length == 0 && getUserVisibleHint()) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "暂时没有新动态，请稍后再试", Integer.valueOf(0)).show();
            return;
        }
        for (int i = 0; i < length; i++) {
            Object parseJson = CircleArticle.parseJson(jSONArray.optJSONObject(i));
            if (!(parseJson == null || this.d.contains(parseJson))) {
                this.d.add(parseJson);
            }
        }
        if (LiveRecommendManager.LIVE_RECOMMEND != null) {
            Pair liveRecommendPackages = LivePackage.getLiveRecommendPackages(LiveRecommendManager.LIVE_RECOMMEND, false);
            if (this.c == 1 && this.d.size() > 2 && liveRecommendPackages != null && liveRecommendPackages.first != null) {
                this.d.add(2, liveRecommendPackages.first);
            }
        }
        e();
        this.c++;
        notifyAdapterDataChanged();
        if (jSONObject.optInt(Headers.REFRESH) > 0 && getUserVisibleHint() && this.m && getActivity() != null) {
            ToastAndDialog.makePositiveToast(getActivity(), String.format("为您刷新了%s条新动态", new Object[]{Integer.valueOf(i)}), Integer.valueOf(0)).show();
        }
        if (z) {
            this.f.setLoadMoreEnable(true);
        } else {
            this.f.setLoadMoreEnable(false);
        }
    }

    public void loadArticles() {
        c();
        LocationHelper.loadCache();
        String format = String.format(Constants.CIRCLE_VIDEO_LIST, new Object[]{Integer.valueOf(this.c), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())});
        new LocationHelper(getActivity()).startLocate(null);
        this.i.hide();
        if (this.m) {
            this.b = new HttpTask(format, format, new by(this));
            this.b.execute(new Void[0]);
            return;
        }
        initHistoryData();
    }

    public void initHistoryData() {
        boolean z = false;
        Object contentFromDisk = FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), a);
        if (!TextUtils.isEmpty(contentFromDisk)) {
            try {
                a(new JSONObject(contentFromDisk));
                z = true;
            } catch (JSONException e) {
            }
        }
        this.m = true;
        if (!z) {
            loadArticles();
        }
    }

    public void onRefresh() {
        if (this.b != null) {
            this.b.cancel(true);
            this.b = null;
        }
        c();
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) parentFragment).showSmallTipsOnMainActitivty(false);
            ((QiuyouCircleFragment) parentFragment).setHeadVisible();
        }
        this.c = 1;
        GroupRecommend.refresh();
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        loadArticles();
    }

    public void onLoadMore() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        loadArticles();
    }

    public boolean isLoading() {
        return this.b != null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CircleArticleBus.register(this);
    }

    public void onDestroy() {
        CircleArticleBus.unregister(this);
        if (this.j != null) {
            this.j.clearImageCache();
        }
        super.onDestroy();
    }

    public void onArticleCreate(CircleArticle circleArticle) {
        if (this.d != null && this.d.size() != 0 && !circleArticle.user.isAnonymous() && circleArticle.type == 10 && circleArticle.auditStatus == 1) {
            this.d.add(0, circleArticle);
            notifyAdapterDataChanged();
        }
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.d != null && this.d.size() != 0) {
            for (int i = 0; i < this.d.size(); i++) {
                Object obj = this.d.get(i);
                if (obj instanceof CircleArticle) {
                    CircleArticle circleArticle2 = (CircleArticle) obj;
                    if (TextUtils.equals(circleArticle2.id, circleArticle.id)) {
                        circleArticle2.updateWith(circleArticle);
                        break;
                    }
                }
            }
            notifyAdapterDataChanged();
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
        if (this.d != null && this.d.size() != 0 && this.d.contains(circleArticle)) {
            this.d.remove(circleArticle);
            notifyAdapterDataChanged();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void scrollToTop() {
        if (this.g != null) {
            this.g.setSelection(0);
        }
    }

    public boolean hasNewArticle() {
        return false;
    }

    private void e() {
        if (this.g != null && this.d != null) {
            int i = this.e > 0 ? this.e : (this.c - 1) * 30;
            if (HttpUtils.isWifi(getActivity())) {
                FeedsAd.getQiuyouCircleInstance().insertCircleFeedAd(i, this.d, getUserVisibleHint());
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.h != null) {
            this.h.onScroll(absListView, i, i2, i3);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.h != null) {
            this.h.onScrollStateChanged(absListView, i);
        }
    }

    public void downloadArticle(CircleArticle circleArticle) {
        if (this.d.contains(circleArticle) && circleArticle.isVideoArticle()) {
            View childAt = this.g.getChildAt((this.d.indexOf(circleArticle) - this.g.getFirstVisiblePosition()) + this.g.getHeaderViewsCount());
            if (childAt != null && (childAt.getTag() instanceof NormalCell)) {
                NormalCell normalCell = (NormalCell) childAt.getTag();
                if (QsbkApp.getInstance().isAutoPlayVideo()) {
                    normalCell.playerView.download();
                } else {
                    normalCell.playerView.downloadWithoutPlay();
                }
            }
        }
    }
}
