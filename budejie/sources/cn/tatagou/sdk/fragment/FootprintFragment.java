package cn.tatagou.sdk.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.R$dimen;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.adapter.c;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.ResultPojo;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.util.g;
import cn.tatagou.sdk.util.j;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView$a;
import cn.tatagou.sdk.view.pullview.AutoPullToRefreshLayout;
import cn.tatagou.sdk.view.pullview.a;
import cn.v6.sixrooms.socket.common.SocketUtil;
import java.util.List;
import okhttp3.ab;
import retrofit2.b;

public class FootprintFragment extends BaseFragment {
    private static final String c = FootprintFragment.class.getSimpleName();
    public LinearLayout a;
    a b = new FootprintFragment$1(this);
    private AutoPullAbleListView d;
    private AutoPullToRefreshLayout e;
    private RelativeLayout f;
    private TextView g;
    private TextView h;
    private TextView i;
    private String j;
    private c k;
    private int l;
    private b<ab> m;
    private b<ab> n;
    private AutoPullAbleListView$a o = new FootprintFragment$2(this);
    private cn.tatagou.sdk.a.a<CommListPojo<Item>> p = new FootprintFragment$4(this);
    private cn.tatagou.sdk.a.a<ResultPojo> q = new FootprintFragment$7(this);

    public static FootprintFragment newInstance() {
        Bundle bundle = new Bundle();
        FootprintFragment footprintFragment = new FootprintFragment();
        footprintFragment.setArguments(bundle);
        return footprintFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_footprint_fragment, viewGroup, false);
        }
        return this.mView;
    }

    public void initView(View view) {
        super.initView(view);
        initFailHintLayout();
        initLoading();
        showLoading();
        cn.tatagou.sdk.e.a.reportEvent(new cn.tatagou.sdk.e.a.a("FP"));
        b(view);
        this.d = (AutoPullAbleListView) view.findViewById(R.id.lv_footprint);
        this.e = (AutoPullToRefreshLayout) view.findViewById(R.id.auto_pull_footprint);
        this.g = (TextView) view.findViewById(R.id.ttg_tv_num);
        this.h = (TextView) view.findViewById(R.id.ttg_tv_sum_num);
        this.i = (TextView) view.findViewById(R.id.ttg_icon_back_top);
        this.a = (LinearLayout) view.findViewById(R.id.ttg_ly_icon);
        this.f = (RelativeLayout) view.findViewById(R.id.ttg_rl_num);
        this.d.setOnLoadListener(this.o);
        this.d.setOnLoadScrollListener(this.b);
        this.d.setPullDownFlag(false);
        this.i.setOnClickListener(this);
        a(this.a);
    }

    private void a(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.bottomMargin = (int) TtgSDK.getContext().getResources().getDimension(R$dimen.ttg_footprint_bottom);
        view.setLayoutParams(layoutParams);
    }

    private void b(View view) {
        TitleBar titleBar = new TitleBar();
        titleBar.setLeftIconShow(true);
        titleBar.setTitle(getString(R.string.my_footprint));
        titleBar.setRightIconShow(true);
        titleBar.setTvRightIconfontCode(getString(R.string.ttg_icon_del));
        setBarTitle(view, titleBar);
    }

    private void a(String str) {
        this.m = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).getMyPath(str, Integer.valueOf(j.a), cn.tatagou.sdk.util.a.getTaoBaoUserId());
        cn.tatagou.sdk.a.b.onCommRequestApi(this.p, this.m, new FootprintFragment$3(this).getType());
    }

    private void b() {
        this.mTvRightIcon.setTextColor(Color.parseColor("#CFCFCF"));
        this.mTvRightIcon.setEnabled(false);
        this.mTtgTvFirstTitle.setText(R.string.footprint_no_data);
        this.mTtgLyFailLayout.setVisibility(0);
        this.mTtgTvSecondTitle.setVisibility(8);
        this.mTtgTvTryAgain.setVisibility(8);
    }

    private void a(List<Item> list, String str, String str2) {
        if ((SocketUtil.FLAG_ON_FAST.equals(str2) || list == null || list.size() == 0) && p.isEmpty(this.j)) {
            b();
        } else if (!"200".equals(str2) && !"304".equals(str2)) {
            l.showToastCenter(getActivity(), p.isEmpty(str) ? getString(R.string.unkonw_error) : str2 + " - " + str);
        } else if (list == null || list.size() <= 0) {
            a(getString(R.string.ttg_icon_pull_data), false);
        } else {
            this.d.finishLoading(0);
            this.j = ((Item) list.get(list.size() - 1)).getAccessTime();
            if (this.k == null) {
                this.k = new c(getActivity(), list, this);
                this.d.setAdapter(this.k);
                return;
            }
            this.k.addItems(list);
        }
    }

    private void a(String str, boolean z) {
        this.d.setFinishText(str);
        this.d.finishLoading(2);
        this.d.setLoadDataFlag(z);
    }

    public void initData() {
        super.initData();
        a(this.j);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.ttg_icon_back_top) {
            this.d.setSelection(0);
        }
    }

    public void onRetryClick() {
        super.onRetryClick();
        a(null);
    }

    protected void onTitleBarRightIconClick() {
        super.onTitleBarRightIconClick();
        Context activity = getActivity();
        int i = R.layout.ttg_pop_dialog;
        String string = getString(R.string.confirm_empty_footprint);
        String string2 = getString(R.string.empty);
        String string3 = getString(R.string.cancel);
        g gVar = new g();
        gVar.getClass();
        g.showDialog(activity, i, string, string2, string3, new FootprintFragment$5(this, gVar));
    }

    private void c() {
        cn.tatagou.sdk.a.b.onCommRequestApi(this.q, ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).delMyPath(cn.tatagou.sdk.util.a.getTaoBaoUserId()), new FootprintFragment$6(this).getType());
    }

    protected void onTitleBarClick() {
        super.onTitleBarClick();
        if (this.d != null) {
            this.d.setSelection(0);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.k != null) {
            this.k.notifyDataSetChanged();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.m != null) {
            this.m.b();
        }
        if (this.n != null) {
            this.n.b();
        }
    }
}
