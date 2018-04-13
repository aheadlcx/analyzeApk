package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.room.statistic.StatisticValue;

final class bh implements OnClickListener {
    final /* synthetic */ MyPropActivity a;

    bh(MyPropActivity myPropActivity) {
        this.a = myPropActivity;
    }

    public final void onClick(View view) {
        StatisticValue.getInstance().setFromRechargePageModule(MyPropActivity.class.getSimpleName(), MyPropActivity.class.getSimpleName());
        this.a.startActivityForResult(new Intent(this.a, ShopActivity.class), 0);
    }
}
