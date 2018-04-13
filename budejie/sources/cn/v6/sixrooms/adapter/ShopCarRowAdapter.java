package cn.v6.sixrooms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ShopItemCarBean$Item$ItemDetails;
import cn.v6.sixrooms.bean.ShopItemCarBean.Item;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import java.util.List;

public class ShopCarRowAdapter extends BaseAdapter {
    private List<Item> a;
    private Context b;
    private int c;

    private static class a {
        ImageView a;
        ImageView b;
        TextView c;
        TextView d;

        private a() {
        }
    }

    public ShopCarRowAdapter(List<Item> list, Context context) {
        this.c = 4;
        this.a = list;
        this.b = context;
    }

    public ShopCarRowAdapter(List<Item> list, ShopActivity shopActivity, int i) {
        this(list, shopActivity);
        this.c = i;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        Item item = (Item) this.a.get(i);
        if (view == null || !(view instanceof RelativeLayout)) {
            View inflate;
            a aVar2 = new a();
            if (this.c == 2) {
                inflate = View.inflate(V6Coop.getInstance().getContext(), R.layout.phone_fragment_shop_item_car_private, null);
            } else {
                inflate = View.inflate(V6Coop.getInstance().getContext(), R.layout.phone_fragment_shop_item_car, null);
            }
            aVar2.a = (ImageView) inflate.findViewById(R.id.carImage);
            aVar2.b = (ImageView) inflate.findViewById(R.id.carLogo);
            aVar2.c = (TextView) inflate.findViewById(R.id.carName);
            aVar2.d = (TextView) inflate.findViewById(R.id.carPrice);
            view = inflate;
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (item.getCar() != null) {
            if (this.c == 2) {
                aVar.a.setImageResource(item.getCar().getCarLarge());
                aVar.b.setImageResource(item.getCar().getCarLogoLarge());
            } else {
                aVar.a.setImageResource(item.getCar().getCar());
                aVar.b.setImageResource(item.getCar().getCarLogo());
            }
            aVar.c.setText(item.getCar().getCarName());
            aVar.d.setText(((ShopItemCarBean$Item$ItemDetails) item.getList().get(0)).getC() + "六币/年");
            view.setTag(aVar);
            return view;
        }
        this.a.remove(item);
        notifyDataSetChanged();
        view = new View(this.b);
        view.setVisibility(8);
        return view;
    }
}
