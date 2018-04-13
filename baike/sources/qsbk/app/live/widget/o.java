package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class o implements OnClickListener {
    final /* synthetic */ DiscountDialog a;

    o(DiscountDialog discountDialog) {
        this.a = discountDialog;
    }

    public void onClick(View view) {
        if (this.a.r != null) {
            this.a.r.onClick(view);
        }
    }
}
