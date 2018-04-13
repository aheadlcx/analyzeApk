package cn.tatagou.sdk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.activity.TtgMainTabActivity;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.util.a;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.IUpdateViewManager;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartFragment extends BaseFragment implements OnClickListener {
    private static final String a = ShoppingCartFragment.class.getSimpleName();
    private RelativeLayout b;
    private TextView c;
    private int d = -1;

    public static ShoppingCartFragment newInstance() {
        Bundle bundle = new Bundle();
        ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
        shoppingCartFragment.setArguments(bundle);
        return shoppingCartFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_car_shop_fragment, null);
        }
        return this.mView;
    }

    public void initView(View view) {
        super.initView(view);
        a(view);
        this.b = (RelativeLayout) view.findViewById(R.id.rl_car);
        this.c = (TextView) view.findViewById(R.id.tv_tb_login);
        q.onResetShapeThemeColor(this.c, 0, 0, TtgConfig.getInstance().getThemeColor());
        this.c.setOnClickListener(this);
    }

    private void a(View view) {
        TitleBar titleBar = new TitleBar();
        titleBar.setLeftIconShow(TtgTitleBar.getInstance().isTabBackShown());
        titleBar.setTitle(getString(R.string.ttjx_shopping_cart));
        titleBar.setRightIconShow(false);
        setBarTitle(view, titleBar);
    }

    public void onClickListener(int i, boolean z) {
        super.onClickListener(i, z);
        this.d = i;
        if (a.checkTaobaoLogin()) {
            this.b.setVisibility(8);
            Map hashMap = new HashMap();
            hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "Tab");
            TtgInterface.openBcCarts(getActivity(), hashMap);
            TtgMainTabActivity.setTabLayoutIndex(i);
            return;
        }
        b.cartStatEvent("Tab");
        this.b.setVisibility(0);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_tb_login) {
            Map hashMap = new HashMap();
            hashMap.put(TtgConfigKey.TTG_EVENT_FROM, "Tab");
            TtgInterface.openBcCarts(getActivity(), hashMap);
            TtgMainTabActivity.setTabLayoutIndex(this.d);
        }
    }

    protected void onTitleBarLeftIconClick() {
        IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(true));
    }

    public void onTitleBack() {
        IUpdateViewManager.getInstance().notifyIUpdateView("ttgGoBackTabHome", Boolean.valueOf(true));
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
