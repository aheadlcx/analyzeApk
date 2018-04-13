package cn.tatagou.sdk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.adapter.i;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.util.e;
import cn.tatagou.sdk.util.f;
import cn.tatagou.sdk.util.j;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.a;
import cn.tatagou.sdk.view.pullview.PullToRefreshLayout;
import cn.tatagou.sdk.view.pullview.PullableListView;
import cn.tatagou.sdk.view.pullview.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import okhttp3.ab;
import retrofit2.b;

public class TrackListFragment extends BaseFragment {
    private static final String a = TrackListFragment.class.getSimpleName();
    private PullableListView b;
    private i c;
    private PullToRefreshLayout d;
    private TextView e;
    private LinearLayout f;
    private e g;
    private String h;
    private List<Item> i;
    private List<Item> j;
    private int k = 1;
    private TextView l;
    private b<ab> m;
    private a n = new TrackListFragment$1(this);
    private cn.tatagou.sdk.a.a<CommPojo<Item>> o = new TrackListFragment$3(this);
    private c p = new TrackListFragment$4(this);

    public static TrackListFragment newInstance() {
        Bundle bundle = new Bundle();
        TrackListFragment trackListFragment = new TrackListFragment();
        trackListFragment.setArguments(bundle);
        return trackListFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_tracklist_fragment, viewGroup, false);
        }
        return this.mView;
    }

    public void initView(View view) {
        super.initView(view);
        initLoading();
        showLoading();
        initFailHintLayout();
        a(view);
        this.b = (PullableListView) view.findViewById(R.id.pull_grid);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.ttg_top_session, this.b, false);
        View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.ttg_load_more_auto_bottom, this.b, false);
        this.e = (TextView) inflate.findViewById(R.id.tv_end_time);
        this.f = (LinearLayout) inflate.findViewById(R.id.ly_time);
        this.d = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        this.l = (TextView) inflate2.findViewById(R.id.ttg_loadstate_tv);
        this.d.setOnRefreshListener(this.p);
        this.b.setCanPullUp(false);
        this.b.addHeaderView(inflate);
        this.b.addFooterView(inflate2);
        this.b.setOnScrollListener(this.n);
    }

    private void a(View view) {
        String stringExtra = getActivity().getIntent().getStringExtra("title");
        TitleBar titleBar = new TitleBar();
        titleBar.setLeftIconShow(true);
        if (p.isEmpty(stringExtra)) {
            stringExtra = getString(R.string.my_product_list);
        }
        titleBar.setTitle(stringExtra);
        titleBar.setRightIconShow(false);
        setBarTitle(view, titleBar);
    }

    public void initData() {
        super.initData();
        this.h = getActivity().getIntent().getStringExtra("productId");
        b();
    }

    private void b() {
        if (this.h != null) {
            this.m = ((cn.tatagou.sdk.a.a.a) cn.tatagou.sdk.a.e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).getSpecialItems(this.h);
            cn.tatagou.sdk.a.b.onCommRequestApi(this.o, this.m, new TrackListFragment$2(this).getType());
            return;
        }
        hideLoading();
    }

    private void a(CommPojo<Item> commPojo) {
        if (!"200".equals(commPojo.getCode()) && !"304".equals(commPojo.getCode())) {
            l.showToastCenter(getActivity(), p.isEmpty(commPojo.getCode()) ? getString(R.string.unkonw_error) : commPojo.getCode() + " - " + commPojo.getMessage());
        } else if (commPojo.getData() != null) {
            a((Item) commPojo.getData());
            a(((Item) commPojo.getData()).getSpecial(), commPojo.getTimestamp());
        }
    }

    private void a(Special special, String str) {
        if (special != null && "Y".equals(special.getIsCountDown()) && !p.isEmpty(str)) {
            a(f.unixTS2Time(Long.parseLong(str), Locale.CANADA), special.getOfflineTime());
        }
    }

    private void a(Item item) {
        if (item.getItems() != null) {
            if (this.i == null) {
                this.i = new ArrayList();
            } else if (this.i.size() > 0) {
                this.i.clear();
            }
            this.i = item.getItems();
            if (this.j != null && this.j.size() > 0) {
                this.j.clear();
            }
            this.k = 1;
            c();
        }
    }

    private void c() {
        if (this.i != null && this.i.size() != 0) {
            int allPager = j.allPager(this.i.size());
            if (this.k > 0 && this.k <= allPager) {
                int lastPage = j.lastPage(this.k, this.i.size());
                int i = (this.k - 1) * j.a;
                if (this.j == null) {
                    this.j = new ArrayList();
                }
                for (int i2 = i; i2 < lastPage; i2++) {
                    this.j.add((Item) this.i.get(i2));
                }
                if (this.l != null) {
                    this.l.setText(this.j.size() < this.i.size() ? getString(R.string.more) : getString(R.string.ttg_icon_pull_data));
                }
                if (this.c == null) {
                    this.c = new i(getActivity(), this.h, getActivity().getIntent().getStringExtra("cateid"), this.j, this);
                    this.b.setAdapter(this.c);
                } else {
                    this.c.setItems(this.j);
                }
                this.k = j.next(this.k, allPager);
            }
        } else if (this.c != null) {
            this.c.notifyDataSetChanged();
            this.l.setVisibility(8);
        }
    }

    private void a(String str, String str2) {
        this.f.setVisibility(0);
        if (this.g == null) {
            long subDate = f.subDate(str, str2, null);
            if (subDate < 0) {
                this.f.setVisibility(8);
                return;
            }
            a(subDate);
            this.g = new TrackListFragment$5(this, subDate, 1000);
            this.g.start();
        }
    }

    private void a(long j) {
        if (this.e != null) {
            long j2 = j / com.umeng.analytics.a.i;
            long j3 = (j / com.umeng.analytics.a.j) - (j2 * 24);
            long j4 = (((j / 1000) - (((24 * j2) * 60) * 60)) - ((j3 * 60) * 60)) - ((((j / 60000) - ((j2 * 24) * 60)) - (j3 * 60)) * 60);
            this.e.setText(String.format((j2 == 0 ? "" : j2 + "天 ") + "%02d : %02d : %02d", new Object[]{Long.valueOf(j3), Long.valueOf(((j / 60000) - ((j2 * 24) * 60)) - (j3 * 60)), Long.valueOf(j4)}));
        }
    }

    protected void onTitleBarClick() {
        super.onTitleBarClick();
        if (this.b != null) {
            this.b.setSelection(0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        if (this.m != null) {
            this.m.b();
        }
    }

    public void onRetryClick() {
        super.onRetryClick();
        b();
    }
}
