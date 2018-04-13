package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.alipay.android.phone.mrpc.core.Headers;
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
import qsbk.app.adapter.QiuYouCircleAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.fragments.QiuyouCircleFragment.IPageFocus;
import qsbk.app.http.HttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.FollowWelcomCard;
import qsbk.app.model.GroupRecommend;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
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

public class FollowCircleFragment extends BaseQiuyouquanFragment implements ILoadingState, IArticleList, IPageFocus, OnArticleUpdateListener, PtrListener {
    private static final String a = FollowCircleFragment.class.getSimpleName();
    private static String b = (a + (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId));
    private HttpTask c;
    private int d = 1;
    private ArrayList<Object> e;
    private PtrLayout f;
    private ListView g;
    private VideoInListHelper h;
    private TipsHelper i;
    private QiuYouCircleAdapter j;
    private String k;
    private QiuyouCircleFragmentNotificationViewHelper l;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private FollowWelcomCard p = new FollowWelcomCard();
    private final BroadcastReceiver q = new ci(this);
    private final BroadcastReceiver r = new cj(this);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_circle_follow, viewGroup, false);
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
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.j = new QiuYouCircleAdapter(this.e, getActivity(), this);
        this.g.setAdapter(this.j);
        this.g.setOnItemClickListener(new ck(this));
        this.g.setOnItemLongClickListener(new cl(this));
        this.h = new cm(this, this.g);
        this.h.setEnable(false);
        this.f.setOnScrollListener(this.h);
        ReadCircle.trackListView(this.f);
        this.i = new TipsHelper(inflate.findViewById(R.id.tips));
        this.l = QiuyouCircleFragmentNotificationViewHelper.newInstance4ChildFragment(this.f, this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.q, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.r, new IntentFilter("QIU_YOU_RELATION_CHANGED"));
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
        if (this.l != null) {
            this.l.release();
        }
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.q);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.r);
        super.onDestroyView();
    }

    public void setSelected(boolean z) {
        this.o = z;
        b();
    }

    private void b() {
        if (this.h != null) {
            if (this.n && this.o) {
                this.f.post(new cn(this));
            } else {
                this.h.stopAll();
            }
        }
    }

    public void onResume() {
        super.onResume();
        this.n = true;
        Object obj = QsbkApp.currentUser == null ? null : QsbkApp.currentUser.userId;
        if (!TextUtils.equals(this.k, obj) || this.e.size() == 0) {
            this.k = obj;
            this.d = 1;
            this.e.clear();
            this.j.notifyDataSetChanged();
            loadArticles();
        }
        b();
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
        b = a + (QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId);
        if (this.g != null) {
            this.g.setSelection(0);
            this.f.refresh();
        }
    }

    public boolean hasNewArticle() {
        return false;
    }

    private void a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
        boolean optBoolean = jSONObject.optBoolean("no_fans");
        if (this.d == 1) {
            this.e.clear();
            if (jSONArray.length() <= 0) {
                this.i.set(UIHelper.getEmptyImg(), "暂时没有动态，稍后刷新试试");
                this.i.show();
            } else if (optBoolean) {
                if (this.e.contains(this.p)) {
                    this.e.remove(this.p);
                }
                this.e.add(0, this.p);
            }
            this.f.refreshDone();
        } else {
            this.f.loadMoreDone(true);
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object parseJson = CircleArticle.parseJson(jSONArray.optJSONObject(i));
            if (!(parseJson == null || this.e.contains(parseJson))) {
                this.e.add(parseJson);
            }
        }
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
        String format = String.format(Constants.CIRCLE_FOLLOW_LIST, new Object[]{Integer.valueOf(this.d), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())});
        new LocationHelper(getActivity()).startLocate(null);
        this.i.hide();
        if (this.m) {
            this.c = new HttpTask(format, format, new co(this));
            this.c.execute(new Void[0]);
            return;
        }
        initHistoryData();
    }

    public void initHistoryData() {
        boolean z = false;
        Object contentFromDisk = FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), b);
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
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        c();
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiuyouCircleFragment)) {
            ((QiuyouCircleFragment) parentFragment).showSmallTipsOnMainActitivty(false);
            ((QiuyouCircleFragment) parentFragment).setHeadVisible();
        }
        this.d = 1;
        GroupRecommend.refresh();
        loadArticles();
    }

    public void onLoadMore() {
        loadArticles();
    }

    public boolean isLoading() {
        return this.c != null;
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
        if (this.e != null && circleArticle != null && circleArticle.user != null && !circleArticle.isVideoArticle() && !circleArticle.user.isAnonymous()) {
            this.e.add(0, circleArticle);
            if (this.e.contains(this.p)) {
                this.e.remove(this.p);
                this.e.add(0, this.p);
            }
            notifyAdapterDataChanged();
            this.i.hide();
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

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void scrollToTop() {
        if (this.g != null) {
            this.g.setSelection(0);
        }
    }

    public void downloadArticle(CircleArticle circleArticle) {
        if (this.e.contains(circleArticle) && circleArticle.isVideoArticle()) {
            View childAt = this.g.getChildAt((this.e.indexOf(circleArticle) - this.g.getFirstVisiblePosition()) + this.g.getHeaderViewsCount());
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
