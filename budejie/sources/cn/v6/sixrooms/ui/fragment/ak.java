package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.ShopItemBean$Item$ItemDetails;
import cn.v6.sixrooms.utils.DialogUtils;

final class ak implements OnItemClickListener {
    final /* synthetic */ ShopVipFragment a;

    ak(ShopVipFragment shopVipFragment) {
        this.a = shopVipFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ShopItemBean$Item$ItemDetails shopItemBean$Item$ItemDetails = (ShopItemBean$Item$ItemDetails) adapterView.getItemAtPosition(i);
        DialogUtils dialogUtils = new DialogUtils(ShopVipFragment.a(this.a));
        String string = this.a.getResources().getString(R.string.shop_dialog_title);
        String format = String.format(this.a.getResources().getString(R.string.shop_dialog_content), new Object[]{ShopVipFragment.b(this.a), shopItemBean$Item$ItemDetails.getD(), shopItemBean$Item$ItemDetails.getC()});
        String string2 = this.a.getResources().getString(R.string.shop_dialog_ok);
        ShopVipFragment.a(this.a, dialogUtils.createConfirmDialog(1, string, format, this.a.getResources().getString(R.string.shop_dialog_cancel), string2, new al(this, shopItemBean$Item$ItemDetails.getTid())));
        ShopVipFragment.d(this.a).show();
    }
}
