package cn.tatagou.sdk.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.activity.FootprintListActivity;
import cn.tatagou.sdk.activity.TtgMainTabActivity;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.ResultPojo;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.pojo.UnReadFeedback;
import cn.tatagou.sdk.util.b;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.g;
import cn.tatagou.sdk.util.m;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.BadgeView;
import cn.tatagou.sdk.view.IUpdateViewManager;
import java.util.HashMap;
import java.util.Map;

public class MineFragment extends BaseFragment {
    private static final String a = MineFragment.class.getSimpleName();
    private TextView b;
    private TextView c;
    private BadgeView d;
    private boolean e = false;
    private int f = 0;
    private long g;
    private TextView h;
    private LinearLayout i;
    private String j;
    private boolean k = false;
    private c l = new MineFragment$1(this);
    private a<CommPojo<UnReadFeedback>> m = new MineFragment$3(this);
    private a<ResultPojo> n = new MineFragment$7(this);
    private OnClickListener o = new MineFragment$8(this);

    static /* synthetic */ int i(MineFragment mineFragment) {
        int i = mineFragment.f + 1;
        mineFragment.f = i;
        return i;
    }

    protected void onViewInVisible() {
        super.onViewInVisible();
        f();
    }

    public static MineFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        bundle.putBoolean("isBackIconShow", z);
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_mine_fragment, viewGroup, false);
        }
        return this.mView;
    }

    protected void initView(View view) {
        super.initView(view);
        a(view);
        onBcSuccFlag();
        c();
        Config.getInstance().setSex(b.getSex());
        this.b = (TextView) view.findViewById(R.id.tv_ttg_taobao_login);
        this.c = (TextView) view.findViewById(R.id.tv_ttg_login_mark);
        this.h = (TextView) view.findViewById(R.id.ttg_tv_shengfeng);
        this.i = (LinearLayout) view.findViewById(R.id.ttg_linear_indentity);
        view.findViewById(R.id.rl_order).setOnClickListener(this);
        view.findViewById(R.id.rl_shenfeng).setOnClickListener(this);
        view.findViewById(R.id.rl_my_cart).setOnClickListener(this);
        view.findViewById(R.id.rl_footprint).setOnClickListener(this);
        view.findViewById(R.id.rl_feedback).setOnClickListener(this);
        view.findViewById(R.id.rl_login).setOnClickListener(this);
        view.findViewById(R.id.ttg_rl_bc_register).setOnClickListener(this);
        b();
    }

    private void b() {
        if (isAdded() && this.h != null) {
            String sexList = Config.getInstance().getSexList();
            CharSequence sex = b.getSex();
            Config.getInstance().setSex(sex);
            if (p.isEmpty(sex) && !TextUtils.isEmpty(sexList)) {
                String[] split = sexList.split(",");
                if (sexList.length() == 1) {
                    sex = split[0];
                    cn.tatagou.sdk.b.a.saveStr("ttgSex", sex);
                }
            }
            TextView textView = this.h;
            sex = TextUtils.isEmpty(sex) ? "" : "F".equals(sex) ? getString(R.string.ttg_beauty) : "M".equals(sex) ? getString(R.string.ttg_handsome_guy) : getString(R.string.ttg_freaky);
            textView.setText(sex);
        }
    }

    private void a(View view) {
        TitleBar titleBar = new TitleBar();
        titleBar.setLeftIconShow(getArguments().getBoolean("isBackIconShow"));
        titleBar.setTitle(getString(R.string.ttg_mine_shopping));
        titleBar.setRightIconShow(false);
        setBarTitle(view, titleBar);
    }

    private void c() {
        this.d = new BadgeView(getActivity(), (TextView) this.mView.findViewById(R.id.tv_msg_ponit));
        this.d.setGravity(17);
        this.d.setTextColor(-1);
        this.d.setTextSize(12.0f);
        this.d.setBadgePosition(2);
        this.d.setBadgeBackgroundColor(SupportMenu.CATEGORY_MASK);
    }

    protected void initPageData() {
        super.initPageData();
        d();
        g();
    }

    private void d() {
        cn.tatagou.sdk.a.b.onCommRequestApi(this.m, ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).countUnreadFeedback(1, p.phoneImei(getActivity())), new MineFragment$2(this).getType());
    }

    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "HOME");
        if (id == R.id.rl_order) {
            TtgInterface.openBcOrders(getActivity(), hashMap);
        } else if (id == R.id.rl_my_cart) {
            TtgInterface.openBcCarts(getActivity(), hashMap);
        } else if (id == R.id.ttg_rl_bc_register) {
            m.openH5(getActivity(), getString(R.string.ttg_bc_register_link), 7, getString(R.string.ttg_bc_register));
        } else if (id == R.id.rl_footprint) {
            startActivity(new Intent(getActivity(), FootprintListActivity.class));
        } else if (id == R.id.rl_feedback) {
            if (this.d.isShown()) {
                this.d.hide();
            }
            this.e = true;
            TtgInterface.openTtgFeedBack(getActivity());
        } else if (id == R.id.rl_login) {
            if (cn.tatagou.sdk.util.a.checkTaobaoLogin()) {
                Context activity = getActivity();
                int i = R.layout.ttg_pop_dialog;
                String string = getString(R.string.is_taobao_cancel_login);
                String string2 = getString(R.string.confirm);
                String string3 = getString(R.string.cancel);
                g gVar = new g();
                gVar.getClass();
                g.showDialog(activity, i, string, string2, string3, new MineFragment$4(this, gVar));
                return;
            }
            cn.tatagou.sdk.util.a.showLogin(getActivity(), this.l);
        } else if (id == R.id.rl_shenfeng) {
            e();
        }
    }

    private void e() {
        Context activity = getActivity();
        g gVar = new g();
        gVar.getClass();
        g.showSexDialog(activity, new MineFragment$5(this, gVar));
    }

    private void a(String str) {
        cn.tatagou.sdk.a.b.onCommRequestApi(this.n, ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).saveUserInfo(this.j, str), new MineFragment$6(this).getType());
    }

    private synchronized void f() {
        if (this.k) {
            this.k = false;
            IUpdateViewManager.getInstance().notifyIUpdateView("changeSex", Boolean.valueOf(true));
        }
    }

    public void onBackPressed() {
        f();
    }

    private void g() {
        if (this.mView != null) {
            LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.ttg_ly_mine);
            if (linearLayout != null) {
                linearLayout.setOnClickListener(this.o);
            }
        }
        if (this.mRylToolBar != null) {
            this.mRylToolBar.setOnClickListener(this.o);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private void h() {
        if (isAdded() && this.b != null && this.c != null) {
            if (cn.tatagou.sdk.util.a.checkTaobaoLogin()) {
                this.b.setText(getString(R.string.taobao_login));
                this.c.setText(getString(R.string.taobao_cancel_login));
                this.c.setTextColor(getActivity().getResources().getColor(R.color.ttg_logout_font));
                return;
            }
            this.b.setText(getString(R.string.taobao_logout));
            this.c.setText(getString(R.string.taobao_go_login));
            this.c.setTextColor(TtgConfig.getInstance().getThemeColor());
        }
    }

    public void onResume() {
        super.onResume();
        h();
        d();
    }

    public void onClickListener(int i, boolean z) {
        super.onClickListener(i, z);
        TtgMainTabActivity.setTabLayoutIndex(i);
        b();
        h();
        d();
    }

    public void onTitleBack() {
        if (!TtgMainTabActivity.a) {
            super.onTitleBack();
        }
        f();
        IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(true));
    }

    protected void onTitleBarLeftIconClick() {
        if (!TtgMainTabActivity.a) {
            super.onTitleBarLeftIconClick();
        }
        f();
        IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(true));
    }

    public void onDestroy() {
        super.onDestroy();
        f();
    }
}
