package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class av implements OnClickListener {
    final /* synthetic */ PayCardActivity a;

    av(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void onClick(View view) {
        this.a.showMenu();
    }
}
