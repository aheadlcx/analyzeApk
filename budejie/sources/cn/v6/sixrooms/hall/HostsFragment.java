package cn.v6.sixrooms.hall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.hall.decoration.SpaceDecoration;
import cn.v6.sixrooms.mvp.interfaces.V6Viewable;
import cn.v6.sixrooms.ui.fragment.BaseFragment;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase$OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.view.SixRoomPullToRefreshRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HostsFragment extends BaseFragment implements V6Viewable, PullToRefreshBase$OnRefreshListener2<RecyclerView> {
    private View a;
    private SixRoomPullToRefreshRecyclerView b;
    private String c;
    private HostsAdapter d;
    private int e;
    private HostsPresenter f;
    private List<LiveItemBean> g = new ArrayList();

    public static HostsFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        HostsFragment hostsFragment = new HostsFragment();
        bundle.putString("TYPE_HOSTS", str);
        hostsFragment.setArguments(bundle);
        return hostsFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.a == null) {
            this.a = LayoutInflater.from(getActivity()).inflate(R.layout.phone_pulltorefresh_recyclerview, viewGroup, false);
            Bundle arguments = getArguments();
            if (!(arguments == null || this.a == null)) {
                if (this.f == null) {
                    this.f = new HostsPresenter(this);
                }
                this.c = arguments.getString("TYPE_HOSTS");
                this.b = (SixRoomPullToRefreshRecyclerView) this.a.findViewById(R.id.pullToRefreshRecyclerView);
                this.b.setMode(Mode.PULL_FROM_START);
                this.b.setOnFooterFuncListener(new o(this));
                RecyclerView recyclerView = (RecyclerView) this.b.getRefreshableView();
                ItemDecoration spaceDecoration = new SpaceDecoration(DensityUtil.dip2px(5.0f));
                spaceDecoration.setPaddingEdgeSide(true);
                spaceDecoration.setPaddingStart(true);
                this.b.setOffset(spaceDecoration.getHalfSpace() * 2);
                spaceDecoration.setPaddingHeaderFooter(false);
                recyclerView.addItemDecoration(spaceDecoration);
                LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager.setSpanSizeLookup(new p(this));
                recyclerView.setLayoutManager(gridLayoutManager);
                this.d = new HostsAdapter(HostsFragment.class.getSimpleName(), getActivity(), this.g, new q(this));
                recyclerView.setAdapter(this.d);
                this.b.setOnRefreshListener(this);
                this.b.setAutoLoadMoreEnabled(true);
            }
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.a.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.a);
            }
        }
        return this.a;
    }

    protected void onVisible(boolean z) {
        super.onVisible(z);
        if (z) {
            a(true, true);
        }
    }

    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
        a(true, false);
    }

    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
        a(false, false);
    }

    private void a(boolean z, boolean z2) {
        if (z) {
            this.e = 1;
        }
        if (this.f == null) {
            this.f = new HostsPresenter(this);
        }
        this.f.getHosts(this.c, this.e, z2);
    }

    public void getData(Object obj) {
        this.b.onRefreshComplete();
        c();
        b();
        if (obj instanceof List) {
            int size = this.g.size();
            List list = (List) obj;
            if (this.e == 1) {
                if (list.size() > 0) {
                    if (this.g.size() > 0) {
                        this.g.clear();
                    }
                    this.g.addAll(list);
                    this.b.onLoadReset();
                    this.d.notifyDataSetChanged();
                } else {
                    View emptyView = this.b.setEmptyView(R.layout.phone_empty_layout);
                    emptyView.setOnClickListener(new r(this));
                    ((TextView) emptyView.findViewById(R.id.emptyTv)).setText("暂时没有主播开播哦,换个地吧!");
                }
            } else if (list.size() > 0) {
                this.g.addAll(list);
                this.b.onLoadReset();
                this.d.notifyItemRangeInserted(size, list.size());
            } else {
                this.b.onLoadEnd();
            }
            this.e++;
        }
    }

    private void a() {
        this.b.setEmptyView(R.layout.global_network_error).setOnClickListener(new s(this));
    }

    private void b() {
        this.b.hideView(R.layout.global_network_error);
    }

    private void c() {
        this.b.hideView(R.layout.phone_empty_layout);
    }

    public void handleError(int i) {
        this.b.onRefreshComplete();
        c();
        b();
        HandleErrorUtils.showErrorToast(i);
        this.b.onLoadError();
        if (this.e == 1 && this.g.size() == 0) {
            a();
        }
    }

    public void handleErrorInfo(String str, String str2) {
        this.b.onRefreshComplete();
        c();
        b();
        HandleErrorUtils.handleErrorResult(str, str2, getActivity());
        if (this.e == 1 && this.g.size() == 0) {
            a();
        }
    }

    public void showLoading() {
        this.b.setEmptyView(R.layout.phone_custom_loading);
    }

    public void hideLoading() {
        this.b.hideView(R.layout.phone_custom_loading);
    }
}
