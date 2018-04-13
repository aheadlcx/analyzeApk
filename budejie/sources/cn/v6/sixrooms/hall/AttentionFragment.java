package cn.v6.sixrooms.hall;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.hall.decoration.SpaceDecoration;
import cn.v6.sixrooms.mvp.interfaces.V6Viewable;
import cn.v6.sixrooms.ui.fragment.BaseFragment;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase$OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AttentionFragment extends BaseFragment implements V6Viewable, PullToRefreshBase$OnRefreshListener<RecyclerView> {
    private PullToRefreshRecyclerView a;
    private HostsAdapter b;
    private HostsPresenter c;
    private List<LiveItemBean> d = new ArrayList();
    private BroadcastReceiver e = new c(this);
    private BroadcastReceiver f = new d(this);

    public static AttentionFragment newInstance() {
        return new AttentionFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.phone_fragment_attention, viewGroup, false);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getView() != null) {
            this.a = (PullToRefreshRecyclerView) getView().findViewById(R.id.pullToRefreshRecyclerView);
            this.a.setId(SixRoomsUtils.parseTypeId(CommonStrs.TYPE_FOLLOW));
            RecyclerView recyclerView = (RecyclerView) this.a.getRefreshableView();
            ItemDecoration spaceDecoration = new SpaceDecoration(DensityUtil.dip2px(5.0f));
            spaceDecoration.setPaddingEdgeSide(true);
            spaceDecoration.setPaddingStart(true);
            this.a.setOffset(spaceDecoration.getHalfSpace() * 2);
            spaceDecoration.setPaddingHeaderFooter(false);
            recyclerView.addItemDecoration(spaceDecoration);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            this.a.setMode(Mode.PULL_FROM_START);
            this.b = new HostsAdapter(AttentionFragment.class.getSimpleName(), getActivity(), this.d, new a(this));
            recyclerView.setAdapter(this.b);
            this.a.setOnRefreshListener(this);
            this.c = new HostsPresenter(this);
            String str = null;
            Drawable drawable = null;
            initDefaultTitleBar(null, getResources().getDrawable(R.drawable.phone_ic_back), str, drawable, "关注", new b(this), null, V6Coop.getInstance().isShowBack());
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.e, new IntentFilter(CustomBroadcast.COOPLOGIN_LOGIN));
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.e, new IntentFilter(CustomBroadcast.COOPLOGIN_LOGOUT));
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.f, new IntentFilter(CustomBroadcast.ATTENTION_STATUS_CHANAGED));
        }
    }

    protected void onVisible(boolean z) {
        super.onVisible(z);
        if (z) {
            a(true);
        }
    }

    public void getData(Object obj) {
        this.a.onRefreshComplete();
        b();
        c();
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() == 0) {
                View emptyView = this.a.setEmptyView(R.layout.phone_empty_layout);
                emptyView.setOnClickListener(new f(this));
                ((TextView) emptyView.findViewById(R.id.emptyTv)).setText("您暂时没有关注主播哦，快去关注吧!");
            }
            if (this.d.size() > 0) {
                this.d.clear();
            }
            this.d.addAll(list);
            this.b.notifyDataSetChanged();
        }
    }

    private void a(boolean z) {
        this.c.getHosts(CommonStrs.TYPE_FOLLOW, 1, z);
    }

    private void a() {
        this.a.setEmptyView(R.layout.global_network_error).setOnClickListener(new g(this));
    }

    public void onRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
        a(false);
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.e);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.f);
    }

    public void handleError(int i) {
        this.a.onRefreshComplete();
        b();
        c();
        HandleErrorUtils.showErrorToast(i);
        if (this.d.size() == 0) {
            a();
        }
    }

    public void handleErrorInfo(String str, String str2) {
        this.a.onRefreshComplete();
        b();
        c();
        HandleErrorUtils.handleErrorResult(str, str2, getActivity());
        if (this.d.size() == 0) {
            a();
        }
    }

    public void showLoading() {
        this.a.setEmptyView(R.layout.phone_custom_loading);
    }

    public void hideLoading() {
        this.a.hideView(R.layout.phone_custom_loading);
    }

    private void b() {
        this.a.hideView(R.layout.global_network_error);
    }

    private void c() {
        this.a.hideView(R.layout.phone_empty_layout);
    }
}
