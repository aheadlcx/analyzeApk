package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class ad implements OnClickListener {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    ad(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
