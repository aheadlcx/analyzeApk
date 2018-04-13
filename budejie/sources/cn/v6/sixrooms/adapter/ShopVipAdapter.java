package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ShopItemBean$Item$ItemDetails;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import java.util.List;

public class ShopVipAdapter extends BaseAdapter {
    List<ShopItemBean$Item$ItemDetails> a;
    String b;

    public ShopVipAdapter(List<ShopItemBean$Item$ItemDetails> list, String str) {
        this.a = list;
        this.b = str;
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
        View inflate = View.inflate(V6Coop.getInstance().getContext(), R.layout.phone_fragment_shop_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.price);
        TextView textView2 = (TextView) inflate.findViewById(R.id.period);
        if (ShopActivity.SHOP_ITEM_TYPE_PURPLE_VIP.equals(this.b)) {
            inflate.setBackgroundResource(R.drawable.rooms_third_shop_vip_purple_button_bg);
            textView.setTextColor(V6Coop.getInstance().getContext().getResources().getColor(R.color.shop_purple));
            textView2.setTextColor(V6Coop.getInstance().getContext().getResources().getColor(R.color.shop_purple));
        } else if (ShopActivity.SHOP_ITEM_TYPE_GOLDEN_VIP.equals(this.b)) {
            inflate.setBackgroundResource(R.drawable.rooms_third_shop_vip_yellow_button_bg);
            textView.setTextColor(V6Coop.getInstance().getContext().getResources().getColor(R.color.shop_golden));
            textView2.setTextColor(V6Coop.getInstance().getContext().getResources().getColor(R.color.shop_golden));
        } else if (ShopActivity.SHOP_ITEM_TYPE_GCARD.equals(this.b)) {
            inflate.setBackgroundResource(R.drawable.rooms_third_shop_vip_green_button_bg);
            textView.setTextColor(V6Coop.getInstance().getContext().getResources().getColor(R.color.shop_green));
            textView2.setTextColor(V6Coop.getInstance().getContext().getResources().getColor(R.color.shop_green));
        }
        ShopItemBean$Item$ItemDetails shopItemBean$Item$ItemDetails = (ShopItemBean$Item$ItemDetails) this.a.get(i);
        textView.setText(shopItemBean$Item$ItemDetails.getC() + "六币");
        textView2.setText((Integer.parseInt(shopItemBean$Item$ItemDetails.getD()) / 30) + "个月");
        return inflate;
    }
}
