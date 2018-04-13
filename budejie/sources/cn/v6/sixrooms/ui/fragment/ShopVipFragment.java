package cn.v6.sixrooms.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.adapter.ShopVipAdapter;
import cn.v6.sixrooms.bean.ShopItemBean.Item;
import cn.v6.sixrooms.engine.CommodityInfoEngine;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import java.util.ArrayList;
import java.util.List;

public class ShopVipFragment extends BaseFragment {
    private ShopActivity a;
    private CommodityInfoEngine b;
    private String c;
    private View d;
    private GridView e;
    private Dialog f;
    private Handler g = new aj(this);
    private Item h;
    private String i;
    private String j;
    private TextView k;
    private List<TextView> l;
    private String m;

    public static ShopVipFragment newInstance() {
        return new ShopVipFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = layoutInflater.inflate(R.layout.phone_fragment_shop_vip, null);
        return this.d;
    }

    public void onActivityCreated(Bundle bundle) {
        this.a = (ShopActivity) getActivity();
        this.c = getArguments().getString("type");
        ImageView imageView = (ImageView) this.d.findViewById(R.id.itemLogo);
        this.e = (GridView) this.d.findViewById(R.id.gridView);
        this.k = (TextView) this.d.findViewById(R.id.descTitle);
        this.l = new ArrayList();
        this.l.add((TextView) this.d.findViewById(R.id.desc1));
        this.l.add((TextView) this.d.findViewById(R.id.desc2));
        this.l.add((TextView) this.d.findViewById(R.id.desc3));
        this.l.add((TextView) this.d.findViewById(R.id.desc4));
        this.l.add((TextView) this.d.findViewById(R.id.desc5));
        this.l.add((TextView) this.d.findViewById(R.id.desc6));
        this.l.add((TextView) this.d.findViewById(R.id.desc7));
        if (ShopActivity.SHOP_ITEM_TYPE_PURPLE_VIP.equals(this.c)) {
            imageView.setImageResource(R.drawable.rooms_third_shop_vip_purple_logo);
        } else if (ShopActivity.SHOP_ITEM_TYPE_GOLDEN_VIP.equals(this.c)) {
            imageView.setImageResource(R.drawable.rooms_third_shop_vip_logo);
        } else if (ShopActivity.SHOP_ITEM_TYPE_GCARD.equals(this.c)) {
            imageView.setImageResource(R.drawable.rooms_third_shop_vip_green_logo);
            this.e.setNumColumns(1);
        }
        if (ShopActivity.SHOP_ITEM_TYPE_PURPLE_VIP.equals(this.c)) {
            this.i = getResources().getString(R.string.shop_dialog_type_purpleVIP);
            this.j = "vip";
            if (this.a.vipBean.getZ() == null || this.a.vipBean.getZ().getList() == null || this.a.vipBean.getZ().getList().size() == 0) {
                this.a.showLoadingScreen();
                b();
            } else {
                this.h = this.a.vipBean.getZ();
                a();
            }
        } else if (ShopActivity.SHOP_ITEM_TYPE_GOLDEN_VIP.equals(this.c)) {
            this.i = getResources().getString(R.string.shop_dialog_type_goldenVIP);
            this.j = "vip";
            if (this.a.vipBean.getH() == null || this.a.vipBean.getH().getList() == null || this.a.vipBean.getH().getList().size() == 0) {
                this.a.showLoadingScreen();
                b();
            } else {
                this.h = this.a.vipBean.getH();
                a();
            }
        } else if (ShopActivity.SHOP_ITEM_TYPE_GCARD.equals(this.c)) {
            this.i = getResources().getString(R.string.shop_dialog_type_greenCard);
            this.j = ShopActivity.SHOP_ITEM_TYPE_GCARD;
            if (this.a.vipBean.getG() == null || this.a.vipBean.getG().getList() == null || this.a.vipBean.getG().getList().size() == 0) {
                this.a.showLoadingScreen();
                b();
            } else {
                this.h = this.a.vipBean.getG();
                a();
            }
        }
        this.e.setOnItemClickListener(new ak(this));
        super.onActivityCreated(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    private void a() {
        if (getActivity() != null) {
            if (ShopActivity.SHOP_ITEM_TYPE_PURPLE_VIP.equals(this.c)) {
                this.m = this.a.vipBean.getZ().getProp();
                this.k.setTextColor(getResources().getColor(R.color.shop_purple));
                this.k.setText(getResources().getString(R.string.shop_vip_description_purpleVIP));
                this.k.setVisibility(0);
            } else if (ShopActivity.SHOP_ITEM_TYPE_GOLDEN_VIP.equals(this.c)) {
                this.m = this.a.vipBean.getH().getProp();
                this.k.setTextColor(getResources().getColor(R.color.shop_golden));
                this.k.setText(getResources().getString(R.string.shop_vip_description_goldenVIP));
                this.k.setVisibility(0);
            } else if (ShopActivity.SHOP_ITEM_TYPE_GCARD.equals(this.c)) {
                this.m = this.a.vipBean.getG().getProp();
                this.k.setTextColor(getResources().getColor(R.color.shop_green));
                this.k.setText(getResources().getString(R.string.shop_vip_description_greenCard));
                this.k.setVisibility(0);
            }
            this.e.setAdapter(new ShopVipAdapter(this.h.getList(), this.c));
            String[] split = this.h.getDesc().split("\n ");
            for (int i = 0; i < split.length; i++) {
                ((TextView) this.l.get(i)).setText(split[i]);
                ((TextView) this.l.get(i)).setVisibility(0);
            }
        }
    }

    private void b() {
        if (this.b == null) {
            this.b = new CommodityInfoEngine(new am(this));
        }
        this.g.postDelayed(new an(this), 300);
    }
}
