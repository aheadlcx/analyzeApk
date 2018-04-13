package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class u implements OnClickListener {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    u(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void onClick(View view) {
        this.a.b.setVisibility(4);
        PaymentChangeActivity.launch(this.a, this.a.w, this.a.x, 101, 0);
    }
}
