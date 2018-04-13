package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.base.BaseQiuyouquanFragment;
import qsbk.app.adapter.CircleHotCommentAdapter;
import qsbk.app.adapter.QiuYouCircleAdapter;
import qsbk.app.fragments.QiuyouCircleFragment.IPageFocus;
import qsbk.app.http.HttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.UserInfo;
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
import qsbk.app.widget.qiuyoucircle.NormalCell;

public class HotCommentCircleFragment extends BaseQiuyouquanFragment implements ILoadingState, IArticleList, IPageFocus, OnArticleUpdateListener, PtrListener {
    private static final String a = FollowCircleFragment.class.getSimpleName();
    private UserInfo b;
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
    private View m;
    private View n;
    private boolean o = false;
    private boolean p = false;
    private final BroadcastReceiver q = new cs(this);

    public static HotCommentCircleFragment newInstance(@NonNull UserInfo userInfo) {
        HotCommentCircleFragment hotCommentCircleFragment = new HotCommentCircleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userInfo);
        hotCommentCircleFragment.setArguments(bundle);
        return hotCommentCircleFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.activity_circle_follow, viewGroup, false);
        this.f = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.g = (ListView) inflate.findViewById(R.id.listview);
        this.f.setRefreshEnable(true);
        this.f.setLoadMoreEnable(false);
        this.f.setPtrListener(this);
        this.m = inflate.findViewById(R.id.login_layout);
        this.n = inflate.findViewById(R.id.id_btn_login);
        ((TextView) inflate.findViewById(R.id.tips_text_unlogin)).setText(R.string.nologin_when_open_circle);
        if (UIHelper.isNightTheme()) {
            this.g.setDivider(new ColorDrawable(-16777216));
            this.g.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
            this.g.setBackgroundColor(getResources().getColor(R.color.main_bg_night));
        } else {
            this.g.setDivider(null);
            this.g.setDividerHeight(0);
            this.g.setBackgroundColor(getResources().getColor(R.color.main_bg));
        }
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.j = new CircleHotCommentAdapter(this.e, getActivity(), this);
        this.g.setAdapter(this.j);
        this.g.setOnItemClickListener(new ct(this));
        this.g.setOnItemLongClickListener(new cu(this));
        this.h = new cv(this, this.g);
        this.h.setEnable(false);
        this.f.setOnScrollListener(this.h);
        ReadCircle.trackListView(this.f);
        this.i = new TipsHelper(inflate.findViewById(R.id.tips));
        this.l = QiuyouCircleFragmentNotificationViewHelper.newInstance4ChildFragment(this.f, this);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.q, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        return inflate;
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

    private void b() {
        if (this.h != null) {
            if (this.o && this.p) {
                this.f.post(new cw(this));
            } else {
                this.h.stopAll();
            }
        }
    }

    public void onResume() {
        Object obj;
        super.onResume();
        this.o = true;
        if (QsbkApp.currentUser == null) {
            obj = null;
        } else {
            obj = QsbkApp.currentUser.userId;
        }
        if (QsbkApp.currentUser == null) {
            this.m.setVisibility(0);
            this.n.setOnClickListener(new cx(this));
            this.g.setVisibility(8);
        } else {
            this.m.setVisibility(8);
            this.g.setVisibility(0);
        }
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
        if (this.o) {
            b();
        }
    }

    public void onPause() {
        super.onPause();
        this.o = false;
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

    public boolean hasNewArticle() {
        return false;
    }

    private void a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        boolean z = jSONObject.optBoolean("has_more") || jSONObject.optInt("has_more", 0) != 0;
        if (this.d == 1) {
            if (jSONArray.length() > 0) {
                this.e.clear();
            }
            this.f.refreshDone();
        } else {
            this.f.loadMoreDone(true);
        }
        int length = jSONArray.length();
        if (this.d == 1 && length == 0 && getUserVisibleHint()) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "暂时无内容", Integer.valueOf(0)).show();
            return;
        }
        for (int i = 0; i < length; i++) {
            this.e.add(CircleArticle.parseAsHotCommentCircleArticle(jSONArray.optJSONObject(i)));
        }
        this.d++;
        notifyAdapterDataChanged();
        if (jSONObject.optInt(Headers.REFRESH) > 0 && getUserVisibleHint() && getActivity() != null) {
            ToastAndDialog.makePositiveToast(getActivity(), String.format("为您刷新了%s条新动态", new Object[]{Integer.valueOf(i)}), Integer.valueOf(0)).show();
        }
        if (z) {
            this.f.setLoadMoreEnable(true);
        } else {
            this.f.setLoadMoreEnable(false);
        }
    }

    public void loadArticles() {
        if (this.b != null) {
            c();
            LocationHelper.loadCache();
            String format = String.format(Constants.CIRCLE_HOT_COMMENT, new Object[]{this.b.userId, Integer.valueOf(this.d)});
            new LocationHelper(getActivity()).startLocate(null);
            this.i.hide();
            this.c = new HttpTask(format, format, new cy(this));
            this.c.execute(new Void[0]);
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
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.b = (UserInfo) arguments.getSerializable("user");
        }
    }

    public void onDestroy() {
        CircleArticleBus.unregister(this);
        super.onDestroy();
    }

    public void onArticleCreate(CircleArticle circleArticle) {
        if (this.e != null && circleArticle != null && circleArticle.user != null && !circleArticle.user.isAnonymous()) {
            this.e.add(0, circleArticle);
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
