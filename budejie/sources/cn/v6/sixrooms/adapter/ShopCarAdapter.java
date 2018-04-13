package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ShopItemCarBean;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;

public class ShopCarAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private ShopItemCarBean a;
    private ShopActivity b;

    private static class a {
        TextView a;

        private a() {
        }
    }

    private static class b {
        GridView a;

        private b() {
        }
    }

    public ShopCarAdapter(ShopItemCarBean shopItemCarBean, ShopActivity shopActivity) {
        this.a = shopItemCarBean;
        this.b = shopActivity;
    }

    public int getCount() {
        return 4;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        ListAdapter shopCarRowAdapter;
        if (view != null) {
            bVar = (b) view.getTag();
        } else {
            bVar = new b();
            view = View.inflate(this.b, R.layout.phone_shop_car_list_item, null);
            bVar.a = (GridView) view.findViewById(R.id.gv_gift_list);
        }
        if (i == 0) {
            bVar.a.setNumColumns(2);
            shopCarRowAdapter = new ShopCarRowAdapter(this.a.getP(), this.b, 2);
        } else {
            shopCarRowAdapter = i == 1 ? new ShopCarRowAdapter(this.a.getS(), this.b) : i == 2 ? new ShopCarRowAdapter(this.a.getH(), this.b) : i == 3 ? new ShopCarRowAdapter(this.a.getZ(), this.b) : null;
        }
        bVar.a.setAdapter(shopCarRowAdapter);
        bVar.a.setOnItemClickListener(new g(this));
        view.setTag(bVar);
        return view;
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null || !(view instanceof LinearLayout)) {
            view = View.inflate(V6Coop.getInstance().getContext(), R.layout.phone_shop_car_list_header, null);
            a aVar2 = new a();
            aVar2.a = (TextView) view.findViewById(R.id.tv_gift_tag);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (i == 0) {
            aVar.a.setText(this.b.getResources().getString(R.string.shop_car_type_private));
            aVar.a.setBackgroundResource(R.drawable.rooms_third_giftpage_inventory_item_bg);
        } else if (i == 1) {
            aVar.a.setText(this.b.getResources().getString(R.string.shop_car_type_luxuryCar));
        } else if (i == 2) {
            aVar.a.setText(this.b.getResources().getString(R.string.shop_car_type_niceCar));
        } else if (i == 3) {
            aVar.a.setText(this.b.getResources().getString(R.string.shop_car_type_popularCar));
        }
        return view;
    }

    public long getHeaderId(int i) {
        return (long) i;
    }
}
