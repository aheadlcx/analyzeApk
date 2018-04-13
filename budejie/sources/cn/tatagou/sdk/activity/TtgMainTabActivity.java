package cn.tatagou.sdk.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.fragment.MineFragment;
import cn.tatagou.sdk.fragment.ShoppingCartFragment;
import cn.tatagou.sdk.fragment.TtgMainFragment;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.HomeTabTitle;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.pojo.TtgUrl;
import cn.tatagou.sdk.util.a;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.view.IUpdateView;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.IconTextView;
import cn.tatagou.sdk.view.UpdateView;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

public class TtgMainTabActivity extends FragmentActivity implements OnClickListener {
    public static boolean a = false;
    public static int b = 0;
    private static final String c = TtgMainTabActivity.class.getSimpleName();
    private static Integer p;
    private IconTextView d;
    private TextView e;
    private IconTextView f;
    private TextView g;
    private IconTextView h;
    private TextView i;
    private TtgMainFragment j;
    private ShoppingCartFragment k;
    private MineFragment l;
    private FragmentTransaction m;
    private FragmentManager n;
    private int o = 0;
    private boolean q = false;

    public static void setTabLayoutIndex(int i) {
        p = Integer.valueOf(i);
        b = p.intValue();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ttg_maintab_activity);
        d();
        this.o = 0;
        a(bundle);
        a = true;
        a();
        b();
        a((TtgUrl) getIntent().getParcelableExtra(TtgConfigKey.TTG_URl));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            TtgUrl ttgUrl = (TtgUrl) intent.getParcelableExtra(TtgConfigKey.TTG_URl);
            b(ttgUrl);
            a(ttgUrl);
        }
    }

    private void a() {
        IUpdateViewManager.getInstance().registIUpdateView(new UpdateView("TTG_TAB_HOME", new IUpdateView<TtgUrl>(this) {
            final /* synthetic */ TtgMainTabActivity a;

            {
                this.a = r1;
            }

            public void updateView(TtgUrl ttgUrl) {
                this.a.b(ttgUrl);
                this.a.a(ttgUrl);
            }
        }));
    }

    private void a(TtgUrl ttgUrl) {
        if (ttgUrl != null) {
            Uri parseUrl = ttgUrl.parseUrl();
            if (!p.isEmpty(ttgUrl.getHost()) && parseUrl != null) {
                String queryParameter = parseUrl.getQueryParameter("notify");
                Log.d(c, "onTtgUrlParse: " + queryParameter);
                this.q = "notify".equals(queryParameter);
            }
        }
    }

    private void b() {
        IUpdateViewManager.getInstance().registIUpdateView(new UpdateView("ttgGoBackTabHome", new IUpdateView<Boolean>(this) {
            final /* synthetic */ TtgMainTabActivity a;

            {
                this.a = r1;
            }

            public void updateView(Boolean bool) {
                if (bool.booleanValue()) {
                    this.a.f();
                } else if (this.a.q) {
                    IUpdateViewManager.getInstance().notifyIUpdateView(TtgConfigKey.KEY_TTGMAIN_COLSE_NOTIFY, Boolean.valueOf(true));
                }
            }
        }));
    }

    private void b(TtgUrl ttgUrl) {
        if (this.o == 1 || this.o == 2) {
            f();
        }
        IUpdateViewManager.getInstance().notifyIUpdateView("TtgHome", ttgUrl);
    }

    private void c() {
        if (!p.isEmpty(Config.getInstance().getTabTitle())) {
            a((HomeTabTitle) JSON.parseObject(Config.getInstance().getTabTitle(), HomeTabTitle.class));
        }
    }

    private void a(HomeTabTitle homeTabTitle) {
        if (homeTabTitle != null) {
            if (!(this.e == null || homeTabTitle.getHome() == null || p.isEmpty(homeTabTitle.getHome().getTitle()))) {
                this.e.setText(homeTabTitle.getHome().getTitle());
            }
            if (!(this.g == null || homeTabTitle.getCart() == null || p.isEmpty(homeTabTitle.getCart().getTitle()))) {
                this.g.setText(homeTabTitle.getCart().getTitle());
            }
            if (this.i != null && homeTabTitle.getMine() != null && !p.isEmpty(homeTabTitle.getMine().getTitle())) {
                this.i.setText(homeTabTitle.getMine().getTitle());
            }
        }
    }

    private void d() {
        this.d = (IconTextView) findViewById(R.id.ttg_icon_tab_main);
        this.e = (TextView) findViewById(R.id.ttg_tv_tab_main_title);
        this.f = (IconTextView) findViewById(R.id.ttg_icon_tab_cart);
        this.g = (TextView) findViewById(R.id.ttg_tv_tab_mine_cart);
        this.h = (IconTextView) findViewById(R.id.ttg_icon_tab_mine);
        this.i = (TextView) findViewById(R.id.ttg_tv_tab_mine_title);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ttg_linear_tab2);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.ttg_linear_tab3);
        ((LinearLayout) findViewById(R.id.ttg_linear_tab1)).setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
    }

    private void a(Bundle bundle) {
        c();
        TtgTitleBar.getInstance().setBackIconShown(true);
        TtgTitleBar.getInstance().setCartIconShown(false);
        TtgTitleBar.getInstance().setMyShoppingIconShown(false);
        this.n = getSupportFragmentManager();
        changeTabTextView(this.e, this.g, this.i);
        changeTabIconView(this.d, this.f, this.h);
        TtgInterface.initView(getApplicationContext(), TtgConfig.getInstance().getPid());
        if (bundle != null) {
            boolean z;
            this.n.popBackStackImmediate(null, 1);
            this.j = (TtgMainFragment) getSupportFragmentManager().findFragmentByTag("mTtgMainFragment");
            this.k = (ShoppingCartFragment) getSupportFragmentManager().findFragmentByTag("mShoppingCartFragment");
            this.l = (MineFragment) getSupportFragmentManager().findFragmentByTag("mMineFragment");
            if (this.j == null) {
                z = true;
            } else {
                z = false;
            }
            this.m = this.n.beginTransaction();
            e();
            if (z) {
                this.m.add(R.id.ttg_framelayout, this.j, "mTtgMainFragment").add(R.id.ttg_framelayout, this.k, "mShoppingCartFragment").add(R.id.ttg_framelayout, this.l, "mMineFragment");
            }
            this.m.show(this.j).hide(this.k).hide(this.l);
        } else {
            e();
            this.m = this.n.beginTransaction();
            this.m.add(R.id.ttg_framelayout, this.j, "mTtgMainFragment").add(R.id.ttg_framelayout, this.k, "mShoppingCartFragment").add(R.id.ttg_framelayout, this.l, "mMineFragment");
            this.m.show(this.j).hide(this.k).hide(this.l);
        }
        this.m.commitAllowingStateLoss();
    }

    private void e() {
        if (this.j == null) {
            this.j = TtgMainFragment.newInstance(false);
        }
        if (this.k == null) {
            this.k = new ShoppingCartFragment();
        }
        if (this.l == null) {
            this.l = MineFragment.newInstance(TtgTitleBar.getInstance().isTabBackShown());
        }
    }

    private void f() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.show(this.j).hide(this.k).hide(this.l);
        beginTransaction.commitAllowingStateLoss();
        changeTabTextView(this.e, this.g, this.i);
        changeTabIconView(this.d, this.f, this.h);
        this.o = 0;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    protected void onResume() {
        Log.d(c, "TABonResume mIndex: " + p);
        if (p != null && (p.intValue() == 1 || p.intValue() == 0)) {
            p = Integer.valueOf(b);
            f();
        }
        if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        }
        a = true;
        super.onResume();
    }

    protected void onPause() {
        a = false;
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        IUpdateViewManager.getInstance().unRegistIUpdateView("TTG_TAB_HOME");
        IUpdateViewManager.getInstance().unRegistIUpdateView("ttgGoBackTabHome");
    }

    protected void onStop() {
        super.onStop();
    }

    private void a(int i) {
        if (this.n == null) {
            this.n = getSupportFragmentManager();
        }
        this.m = this.n.beginTransaction();
        if (i == R.id.ttg_linear_tab1 || i == R.id.ttg_icon_tab_main) {
            this.m.show(this.j).hide(this.k).hide(this.l);
            changeTabTextView(this.e, this.g, this.i);
            changeTabIconView(this.d, this.f, this.h);
            if (this.o == 0) {
                this.j.onClickListener(0, false);
            } else {
                TtgInterface.initView(getApplicationContext(), TtgConfig.getInstance().getPid());
            }
            this.o = 0;
        } else if (i == R.id.ttg_linear_tab2 || i == R.id.ttg_icon_tab_cart) {
            g();
            this.o = 1;
        } else if (i == R.id.ttg_linear_tab3 || i == R.id.ttg_icon_tab_mine) {
            this.m.show(this.l).hide(this.k).hide(this.j);
            changeTabTextView(this.i, this.e, this.g);
            changeTabIconView(this.h, this.d, this.f);
            if (this.o != 2) {
                b.mineStatEvent("Tab");
                this.l.onClickListener(2, true);
            }
            this.o = 2;
        }
        this.m.commitAllowingStateLoss();
    }

    private void g() {
        if (a.checkTaobaoLogin()) {
            Map hashMap = new HashMap();
            hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "Tab");
            TtgInterface.openBcCarts(this, hashMap);
            return;
        }
        this.m.show(this.k).hide(this.j).hide(this.l);
        changeTabTextView(this.g, this.e, this.i);
        changeTabIconView(this.f, this.d, this.h);
        this.k.onClickListener(1, true);
    }

    public void onClick(View view) {
        a(view.getId());
    }

    public void onBackPressed() {
        if (this.o != 0) {
            IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(true));
        } else if (this.j == null || this.j.mAppCate == null || this.j.mScrollCatViewPage == null || this.j.mAppCate.getPosition() == 0) {
            if (this.q) {
                IUpdateViewManager.getInstance().notifyIUpdateView(TtgConfigKey.KEY_TTGMAIN_COLSE_NOTIFY, Boolean.valueOf(true));
            }
            super.onBackPressed();
        } else {
            this.j.mScrollCatViewPage.setCurrentItem(0);
            this.j.mAppCate.setPosition(0);
        }
    }

    public void changeTabTextView(TextView textView, TextView textView2, TextView textView3) {
        textView.setTextColor(TtgConfig.getInstance().getThemeColor());
        textView2.setTextColor(getResources().getColor(R.color.ttg_font_gray));
        textView3.setTextColor(getResources().getColor(R.color.ttg_font_gray));
    }

    public void changeTabIconView(TextView textView, TextView textView2, TextView textView3) {
        textView.setTextColor(TtgConfig.getInstance().getThemeColor());
        textView2.setTextColor(getResources().getColor(R.color.ttg_font_gray));
        textView3.setTextColor(getResources().getColor(R.color.ttg_font_gray));
    }
}
