package cn.v6.sixrooms.hall;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.constants.CommonStrs;
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

public class HostsNearbyFragment extends BaseFragment implements OnClickListener, V6Viewable, PullToRefreshBase$OnRefreshListener2<RecyclerView> {
    private SixRoomPullToRefreshRecyclerView a;
    private HostsAdapter b;
    private int c;
    private v d;
    private List<ProvinceNumBean> e;
    private String f;
    private TextView g;
    private ImageView h;
    private RelativeLayout i;
    private View j;
    private am k;
    private List<LiveItemBean> l = new ArrayList();

    public static HostsNearbyFragment newInstance() {
        return new HostsNearbyFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.j == null) {
            this.j = LayoutInflater.from(getActivity()).inflate(R.layout.phone_fragment_nearby, viewGroup, false);
            if (this.j != null) {
                if (this.d == null) {
                    this.d = new v(this);
                }
                this.a = (SixRoomPullToRefreshRecyclerView) this.j.findViewById(R.id.pullToRefreshRecyclerView);
                this.a.setMode(Mode.PULL_FROM_START);
                this.a.setOnFooterFuncListener(new x(this));
                this.a.setId(SixRoomsUtils.parseTypeId(CommonStrs.TYPE_LOCATION));
                this.g = (TextView) this.j.findViewById(R.id.locationTv);
                this.h = (ImageView) this.j.findViewById(R.id.arrowIv);
                this.i = (RelativeLayout) this.j.findViewById(R.id.locationRl);
                RecyclerView recyclerView = (RecyclerView) this.a.getRefreshableView();
                ItemDecoration spaceDecoration = new SpaceDecoration(DensityUtil.dip2px(5.0f));
                spaceDecoration.setPaddingEdgeSide(true);
                spaceDecoration.setPaddingStart(false);
                spaceDecoration.setPaddingHeaderFooter(false);
                recyclerView.addItemDecoration(spaceDecoration);
                LayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager.setSpanSizeLookup(new y(this));
                recyclerView.setLayoutManager(gridLayoutManager);
                this.b = new HostsAdapter(HostsNearbyFragment.class.getSimpleName(), getActivity(), this.l, new z(this));
                recyclerView.setAdapter(this.b);
                this.a.setOnRefreshListener((PullToRefreshBase$OnRefreshListener2) this);
                this.i.setOnClickListener(this);
                this.a.setAutoLoadMoreEnabled(true);
            }
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.j.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.j);
            }
        }
        return this.j;
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
            this.c = 1;
        }
        if (this.d == null) {
            this.d = new v(this);
        }
        this.d.a(this.f, this.c, z2);
    }

    public void getData(Object obj) {
        this.a.onRefreshComplete();
        c();
        b();
        if (obj instanceof HostsLocationBean) {
            HostsLocationBean hostsLocationBean = (HostsLocationBean) obj;
            int size = this.l.size();
            Object roomList = hostsLocationBean.getRoomList();
            this.e = hostsLocationBean.getProvinceNumAry();
            this.g.setText(hostsLocationBean.getPtitle());
            this.f = hostsLocationBean.getPid();
            if (this.c == 1) {
                if (roomList.size() == 0) {
                    View emptyView = this.a.setEmptyView(R.layout.phone_empty_layout);
                    emptyView.setOnClickListener(new aa(this));
                    ((TextView) emptyView.findViewById(R.id.emptyTv)).setText("该地没有直播的主播哦，换个地吧!");
                } else {
                    if (this.l.size() > 0) {
                        this.l.clear();
                    }
                    this.l.addAll(roomList);
                    this.a.onLoadReset();
                    this.b.notifyDataSetChanged();
                }
            } else if (roomList.size() > 0) {
                this.l.addAll(roomList);
                this.a.onLoadReset();
                this.b.notifyItemRangeInserted(size, roomList.size());
            } else {
                this.a.onLoadEnd();
            }
            this.c++;
        }
    }

    private void a() {
        this.a.setEmptyView(R.layout.global_network_error).setOnClickListener(new ab(this));
    }

    private void b() {
        this.a.hideView(R.layout.global_network_error);
    }

    private void c() {
        this.a.hideView(R.layout.phone_empty_layout);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.locationRl && this.e != null) {
            if (this.k == null) {
                this.k = new am(getActivity(), this.e, new ac(this));
                this.k.setOnDismissListener(new ad(this));
            }
            am amVar = this.k;
            View view2 = this.i;
            if (VERSION.SDK_INT < 24) {
                amVar.showAsDropDown(view2);
            } else {
                int[] iArr = new int[2];
                view2.getLocationOnScreen(iArr);
                amVar.showAtLocation(view2, 0, 0, iArr[1] + view2.getHeight());
            }
            this.h.setImageResource(R.drawable.phone_location_pull_up);
        }
    }

    public void handleError(int i) {
        this.a.onRefreshComplete();
        c();
        b();
        HandleErrorUtils.showErrorToast(i);
        this.a.onLoadError();
        if (this.c == 1 && this.l.size() == 0) {
            a();
        }
    }

    public void handleErrorInfo(String str, String str2) {
        this.a.onRefreshComplete();
        c();
        b();
        HandleErrorUtils.handleErrorResult(str, str2, getActivity());
        if (this.c == 1 && this.l.size() == 0) {
            a();
        }
    }

    public void showLoading() {
        this.a.setEmptyView(R.layout.phone_custom_loading);
    }

    public void hideLoading() {
        this.a.hideView(R.layout.phone_custom_loading);
    }
}
