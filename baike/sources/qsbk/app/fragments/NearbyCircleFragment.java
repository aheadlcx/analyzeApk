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
import android.widget.ListView;
import com.alipay.android.phone.mrpc.core.Headers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;
import qsbk.app.adapter.QiuYouCircleAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.fragments.QiuyouCircleFragment.IPageFocus;
import qsbk.app.http.HttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.GroupRecommend.GroupRecommendObserver;
import qsbk.app.model.LivePackage;
import qsbk.app.model.RssArticle.Type;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
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

public class NearbyCircleFragment extends BaseNearbyFragment implements ILoadingState, IFeedsAdLoaded, IArticleList, IPageFocus, GroupRecommendObserver, LocationCallBack, OnArticleUpdateListener, PtrListener {
    private static final String a = NearbyCircleFragment.class.getSimpleName();
    private GroupRecommend b;
    private HttpTask c;
    private int d = 1;
    private ArrayList<Object> e;
    private int f = 0;
    private PtrLayout g;
    private ListView o;
    private VideoInListHelper p;
    private TipsHelper q;
    private QiuYouCircleAdapter r;
    private QiuyouCircleFragmentNotificationViewHelper s;
    private String t;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private final BroadcastReceiver x = new hc(this);

    protected String a() {
        return getString(R.string.view_nearby_circle);
    }

    protected boolean d() {
        return false;
    }

    public void onFillContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater.inflate(R.layout.layout_ptr_listview, viewGroup);
        this.g = (PtrLayout) viewGroup.findViewById(R.id.ptr);
        this.o = (ListView) viewGroup.findViewById(R.id.listview);
        this.g.setRefreshEnable(true);
        this.g.setLoadMoreEnable(true);
        this.g.setPtrListener(this);
        if (UIHelper.isNightTheme()) {
            this.o.setDivider(new ColorDrawable(-16777216));
            this.o.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.o.setDivider(null);
            this.o.setDividerHeight(0);
        }
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.r = new QiuYouCircleAdapter(this.e, getActivity(), (ShareUtils$OnCircleShareStartListener) this, Type.NEARBY);
        this.o.setAdapter(this.r);
        this.o.setOnItemClickListener(new hd(this));
        this.o.setOnItemLongClickListener(new he(this));
        this.p = new hf(this, this.o);
        this.p.setEnable(false);
        this.g.setOnScrollListener(this.p);
        ReadCircle.trackListView(this.g);
        this.q = new TipsHelper(viewGroup.findViewById(R.id.tips));
        this.s = QiuyouCircleFragmentNotificationViewHelper.newInstance4ChildFragment(this.g, this);
        FeedsAd.getQiuyouCircleInstance().registerListener(this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.x, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
    }

    public void init() {
    }

    public void setSelected(boolean z) {
        this.w = z;
        f();
    }

    private void f() {
        if (this.v && this.w) {
            this.g.post(new hg(this));
        } else if (this.p != null) {
            this.p.stopAll();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            this.g.setOnScrollOffsetListener(new AlphaAnimOffsetListener(((QiuyouCircleFragment) parentFragment).getActivityNotification()));
        }
    }

    public void onResume() {
        super.onResume();
        this.v = true;
        Object obj = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        if (!TextUtils.equals(this.t, obj) || this.e.size() == 0) {
            this.t = obj;
            this.d = 1;
            this.e.clear();
            this.r.notifyDataSetChanged();
            startLocate();
        }
        f();
    }

    public void notifyAdapterDataChanged() {
        this.r.notifyDataSetChanged();
        if (this.v) {
            f();
        }
    }

    public void onPause() {
        super.onPause();
        this.v = false;
        this.p.stopAll();
    }

    private void a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
        if (this.d == 1) {
            if (jSONArray.length() > 0) {
                this.e.clear();
            }
            this.g.refreshDone();
        } else {
            this.g.loadMoreDone(true);
        }
        this.f = this.e.size();
        int length = jSONArray.length();
        if (this.d == 1 && length == 0 && getUserVisibleHint()) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "暂时没有新动态，请稍后再试", Integer.valueOf(0)).show();
            return;
        }
        boolean z2;
        List arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            Object parseJson = CircleArticle.parseJson(jSONArray.optJSONObject(i));
            if (!(parseJson == null || this.e.contains(parseJson))) {
                this.e.add(parseJson);
                arrayList.add(parseJson);
            }
        }
        if (this.u && this.d == 1) {
            ReadCircle.onRefresh(this.e);
            z2 = false;
        } else if (this.d > 1) {
            ReadCircle.onLoadMore(arrayList);
            z2 = true;
        } else {
            z2 = false;
        }
        if (!(this.b == null || this.e.contains(this.b) || this.e.size() < 23)) {
            this.e.add(23, this.b);
        }
        h();
        if (LiveRecommendManager.LIVE_RECOMMEND != null) {
            Pair liveRecommendPackages = LivePackage.getLiveRecommendPackages(LiveRecommendManager.LIVE_RECOMMEND, false);
            if (this.d == 1 && this.e.size() > 11 && liveRecommendPackages != null && liveRecommendPackages.first != null) {
                this.e.add(11, liveRecommendPackages.first);
            }
        }
        this.d++;
        notifyAdapterDataChanged();
        if (this.d - 1 == 1) {
            this.o.setSelection(0);
        }
        int optInt = jSONObject.optInt(Headers.REFRESH);
        if (!z2 && optInt > 0 && getUserVisibleHint() && this.u && getActivity() != null) {
            ToastAndDialog.makePositiveToast(getActivity(), String.format("为您刷新了%s条新动态", new Object[]{Integer.valueOf(optInt)}), Integer.valueOf(0)).show();
        }
        if (z) {
            this.g.setLoadMoreEnable(true);
        } else {
            this.g.setLoadMoreEnable(false);
        }
    }

    private void g() {
        String format = String.format(Constants.CIRCLE_NEARBY_LIST, new Object[]{Integer.valueOf(this.d), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude()), Integer.valueOf(30)});
        this.q.hide();
        if (this.u) {
            this.c = new HttpTask(format, format, new hh(this));
            this.c.execute(new Void[0]);
            return;
        }
        initHistoryData();
    }

    public void onLocateDone() {
        g();
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
        this.u = true;
        if (!z) {
            g();
        }
    }

    public void refresh() {
        if (this.o != null) {
            this.o.setSelection(0);
            this.g.refresh();
        }
    }

    public void onRefresh() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        this.d = 1;
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) parentFragment).showSmallTipsOnMainActitivty(false);
            ((QiuyouCircleFragment) parentFragment).setHeadVisible();
        }
        GroupRecommend.refresh();
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        g();
    }

    public void onLoadMore() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        g();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.s != null) {
            this.s.release();
        }
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.x);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CircleArticleBus.register(this);
        GroupRecommend.register(this);
    }

    public void onDestroy() {
        CircleArticleBus.unregister(this);
        GroupRecommend.unregister(this);
        if (this.r != null) {
            this.r.clearImageCache();
        }
        super.onDestroy();
    }

    public void onArticleCreate(CircleArticle circleArticle) {
        if (this.e != null && this.e.size() != 0 && circleArticle.auditStatus == 1) {
            this.e.add(0, circleArticle);
            notifyAdapterDataChanged();
        }
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.e != null && this.e.size() != 0) {
            for (int i = 0; i < this.e.size(); i++) {
                Object obj = this.e.get(i);
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
        if (this.e != null && this.e.size() != 0 && this.e.contains(circleArticle)) {
            this.e.remove(circleArticle);
            notifyAdapterDataChanged();
        }
    }

    public void onNewGroupRecommend(GroupRecommend groupRecommend) {
        if (!(this.b == null || this.e == null)) {
            this.e.remove(this.b);
        }
        this.b = groupRecommend;
        if (this.e != null && this.e.size() >= 23) {
            this.e.add(23, groupRecommend);
        }
        if (this.r != null) {
            notifyAdapterDataChanged();
        }
    }

    private void h() {
        if (this.o != null && this.e != null) {
            FeedsAd.getQiuyouCircleInstance().insertCircleFeedAd(this.f > 0 ? this.f : (this.d - 1) * 30, this.e, getUserVisibleHint());
        }
    }

    public void onFeedsAdLoaded() {
        h();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void scrollToTop() {
        if (this.o != null) {
            this.o.setSelection(0);
        }
    }

    public boolean hasNewArticle() {
        return false;
    }

    public void downloadArticle(CircleArticle circleArticle) {
        if (this.e.contains(circleArticle) && circleArticle.isVideoArticle()) {
            View childAt = this.o.getChildAt((this.e.indexOf(circleArticle) - this.o.getFirstVisiblePosition()) + this.o.getHeaderViewsCount());
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
