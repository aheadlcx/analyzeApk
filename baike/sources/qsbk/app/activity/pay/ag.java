package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class ag implements OnClickListener {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    ag(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void onClick(View view) {
        this.a.n.setFocusable(true);
        this.a.n.setFocusableInTouchMode(true);
        this.a.n.setVisibility(0);
    }
}
