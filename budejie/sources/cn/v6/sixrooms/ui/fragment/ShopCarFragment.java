package cn.v6.sixrooms.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.ShopCarAdapter;
import cn.v6.sixrooms.engine.CommodityInfoEngine;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;

public class ShopCarFragment extends BaseFragment {
    private ShopActivity a;
    private CommodityInfoEngine b;
    private View c;
    private StickyListHeadersListView d;
    private Handler e = new Handler();
    private String f;

    public static ShopCarFragment newInstance() {
        return new ShopCarFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = layoutInflater.inflate(R.layout.phone_fragment_shop_car, null);
        return this.c;
    }

    public void onActivityCreated(Bundle bundle) {
        this.a = (ShopActivity) getActivity();
        this.f = getArguments().getString("type");
        this.d = (StickyListHeadersListView) this.c.findViewById(R.id.carGridView);
        if (this.a.carBean.getH() == null || this.a.carBean.getP() == null || this.a.carBean.getS() == null || this.a.carBean.getZ() == null) {
            this.a.showLoadingScreen();
            this.b = new CommodityInfoEngine(new ah(this));
            String encpass = SaveUserInfoUtils.getEncpass(this.a);
            if (GlobleValue.getUserBean() == null) {
                this.e.postDelayed(new ai(this, encpass), 500);
            } else {
                this.b.getShopItems(encpass, GlobleValue.getUserBean().getId(), "", this.f);
            }
        } else {
            a();
        }
        super.onActivityCreated(bundle);
    }

    private void a() {
        this.d.setAdapter(new ShopCarAdapter(this.a.carBean, this.a));
    }
}
