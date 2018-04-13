package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class cy implements DialogListener {
    final /* synthetic */ cx a;

    cy(cx cxVar) {
        this.a = cxVar;
    }

    public final void positive(int i) {
        String str;
        StatisticValue.getInstance().setRechargePageModule(ShopActivity.class.getSimpleName(), ShopActivity.class.getSimpleName());
        if (V6Coop.getUserCoopPaySDK()) {
            str = CommonStrs.COOPRECHARGEACTIVITY;
        } else {
            str = CommonStrs.V6RECHARGEACTIVITY;
        }
        try {
            this.a.a.startActivity(new Intent(this.a.a, Class.forName(str)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void negative(int i) {
    }
}
