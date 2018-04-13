package cn.v6.sixrooms.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ShopItemCarBean$Item$ItemDetails;
import cn.v6.sixrooms.bean.ShopItemCarBean.Item;
import cn.v6.sixrooms.utils.DialogUtils;

final class g implements OnItemClickListener {
    final /* synthetic */ ShopCarAdapter a;

    g(ShopCarAdapter shopCarAdapter) {
        this.a = shopCarAdapter;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Item item = (Item) adapterView.getItemAtPosition(i);
        new DialogUtils(this.a.b).createConfirmDialog_shopCarSpecial(1, item.getCar().getCar(), this.a.b.getResources().getString(R.string.shop_dialog_title), String.format(this.a.b.getResources().getString(R.string.shop_dialog_content), new Object[]{item.getCar().getCarName(), ((ShopItemCarBean$Item$ItemDetails) item.getList().get(0)).getD(), ((ShopItemCarBean$Item$ItemDetails) item.getList().get(0)).getC()}), this.a.b.getResources().getString(R.string.shop_dialog_cancel), this.a.b.getResources().getString(R.string.shop_dialog_ok), new h(this, item, ((ShopItemCarBean$Item$ItemDetails) item.getList().get(0)).getTid())).show();
    }
}
