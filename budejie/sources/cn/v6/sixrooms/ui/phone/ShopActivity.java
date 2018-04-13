package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ShopItemBean;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.engine.BuyItemInShopEngine;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.ui.fragment.ShopCarFragment;
import cn.v6.sixrooms.ui.fragment.ShopVipFragment;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

public class ShopActivity extends SlidingActivity implements OnClickListener {
    public static final String SHOP_ITEM_TYPE_CAR = "car";
    public static final String SHOP_ITEM_TYPE_GCARD = "gcard";
    public static final String SHOP_ITEM_TYPE_GOLDEN_VIP = "gvip";
    public static final String SHOP_ITEM_TYPE_PURPLE_VIP = "pvip";
    public static final String TYPE_STR = "type";
    private RelativeLayout a;
    private ShopVipFragment b;
    private ShopVipFragment c;
    public ShopItemCarBean carBean = new ShopItemCarBean();
    private ShopVipFragment d;
    private ShopCarFragment e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private BuyItemInShopEngine j;
    private UserInfoEngine k;
    private int l = 0;
    public ShopItemBean vipBean = new ShopItemBean();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_shop);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, null, getResources().getString(R.string.shop_title), new cw(this), null);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rootView);
        Animation translateAnimation = new TranslateAnimation((float) getWindowManager().getDefaultDisplay().getWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        relativeLayout.startAnimation(translateAnimation);
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new da(this));
        this.a = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.a.setOnClickListener(this);
        initView();
        initData();
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        CharSequence stringExtra = getIntent().getStringExtra("which");
        if (TextUtils.isEmpty(stringExtra)) {
            removeBackground();
            this.f.setTextColor(-1);
            this.f.setBackgroundResource(R.drawable.rooms_third_tab_left);
            if (this.b == null) {
                this.b = a(SHOP_ITEM_TYPE_PURPLE_VIP);
            }
            changeFragment(this.b);
        } else if (SHOP_ITEM_TYPE_PURPLE_VIP.equals(stringExtra)) {
            removeBackground();
            this.f.setTextColor(-1);
            this.f.setBackgroundResource(R.drawable.rooms_third_tab_left);
            if (this.b == null) {
                this.b = a(SHOP_ITEM_TYPE_PURPLE_VIP);
            }
            changeFragment(this.b);
        } else if (SHOP_ITEM_TYPE_GOLDEN_VIP.equals(stringExtra)) {
            removeBackground();
            this.g.setTextColor(-1);
            this.g.setBackgroundColor(getResources().getColor(R.color.shop_red));
            if (this.c == null) {
                this.c = a(SHOP_ITEM_TYPE_GOLDEN_VIP);
            }
            changeFragment(this.c);
        } else if (SHOP_ITEM_TYPE_GCARD.equals(stringExtra)) {
            removeBackground();
            this.h.setTextColor(-1);
            this.h.setBackgroundColor(getResources().getColor(R.color.shop_red));
            if (this.d == null) {
                this.d = a(SHOP_ITEM_TYPE_GCARD);
            }
            changeFragment(this.d);
        } else if (SHOP_ITEM_TYPE_CAR.equals(stringExtra)) {
            removeBackground();
            this.i.setTextColor(-1);
            this.i.setBackgroundResource(R.drawable.rooms_third_tab_right);
            if (this.e == null) {
                this.e = a();
            }
            changeFragment(this.e);
        } else {
            removeBackground();
            this.f.setTextColor(-1);
            this.f.setBackgroundResource(R.drawable.rooms_third_tab_left);
            if (this.b == null) {
                this.b = a(SHOP_ITEM_TYPE_PURPLE_VIP);
            }
            changeFragment(this.b);
        }
    }

    private static ShopCarFragment a() {
        ShopCarFragment newInstance = ShopCarFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("type", SHOP_ITEM_TYPE_CAR);
        newInstance.setArguments(bundle);
        return newInstance;
    }

    private static ShopVipFragment a(String str) {
        ShopVipFragment newInstance = ShopVipFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("type", str);
        newInstance.setArguments(bundle);
        return newInstance;
    }

    public void buyItem(String str, String str2) {
        this.j.sendRequest(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), GlobleValue.getUserBean().getId(), "", str, str2, "");
    }

    protected void initView() {
        this.f = (TextView) findViewById(R.id.tvGoPurpleVip);
        this.g = (TextView) findViewById(R.id.tvGoGoldenVip);
        this.h = (TextView) findViewById(R.id.tvGoGreedCard);
        this.i = (TextView) findViewById(R.id.tvGo4S);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvGoPurpleVip) {
            removeBackground();
            this.f.setTextColor(-1);
            this.f.setBackgroundResource(R.drawable.rooms_third_tab_left);
            if (this.b == null) {
                this.b = a(SHOP_ITEM_TYPE_PURPLE_VIP);
            }
            changeFragment(this.b);
        } else if (id == R.id.tvGoGoldenVip) {
            removeBackground();
            this.g.setTextColor(-1);
            this.g.setBackgroundColor(getResources().getColor(R.color.shop_red));
            if (this.c == null) {
                this.c = a(SHOP_ITEM_TYPE_GOLDEN_VIP);
            }
            changeFragment(this.c);
        } else if (id == R.id.tvGoGreedCard) {
            removeBackground();
            this.h.setTextColor(-1);
            this.h.setBackgroundColor(getResources().getColor(R.color.shop_red));
            if (this.d == null) {
                this.d = a(SHOP_ITEM_TYPE_GCARD);
            }
            changeFragment(this.d);
        } else if (id == R.id.tvGo4S) {
            removeBackground();
            this.i.setTextColor(-1);
            this.i.setBackgroundResource(R.drawable.rooms_third_tab_right);
            if (this.e == null) {
                this.e = a();
            }
            changeFragment(this.e);
        }
    }

    public void removeBackground() {
        this.f.setTextColor(SupportMenu.CATEGORY_MASK);
        this.f.setBackgroundResource(17170445);
        this.g.setTextColor(SupportMenu.CATEGORY_MASK);
        this.g.setBackgroundColor(0);
        this.h.setTextColor(SupportMenu.CATEGORY_MASK);
        this.h.setBackgroundColor(0);
        this.i.setTextColor(SupportMenu.CATEGORY_MASK);
        this.i.setBackgroundResource(17170445);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        getSlidingMenu().a();
        return true;
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.scrollView, fragment).commitAllowingStateLoss();
    }

    public void showLoadingScreen() {
        this.a.setVisibility(0);
    }

    public void hideLoadingScreen() {
        this.a.setVisibility(8);
    }

    public String getErrorCodeString(int i) {
        if (i == 1007) {
            return getString(R.string.tip_json_parse_error_title);
        }
        return getString(R.string.tip_network_error_title);
    }

    protected void initData() {
        this.j = new BuyItemInShopEngine(new cx(this));
        this.k = new UserInfoEngine(new cz(this));
    }

    static /* synthetic */ void b(ShopActivity shopActivity) {
        Intent intent = new Intent();
        intent.putExtra("hasBoughtSomething", true);
        shopActivity.setResult(-1, intent);
    }
}
