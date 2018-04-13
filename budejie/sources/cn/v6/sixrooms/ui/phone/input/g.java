package cn.v6.sixrooms.ui.phone.input;

import android.content.Intent;
import cn.v6.sixrooms.ui.phone.ShopActivity;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class g implements DialogListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public final void positive(int i) {
        Intent intent = new Intent(this.a.a.mActivity, ShopActivity.class);
        intent.putExtra("which", ShopActivity.SHOP_ITEM_TYPE_GOLDEN_VIP);
        this.a.a.mActivity.startActivity(intent);
    }

    public final void negative(int i) {
    }
}
