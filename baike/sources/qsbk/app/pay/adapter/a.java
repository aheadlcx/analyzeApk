package qsbk.app.pay.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.Diamond;

class a implements OnClickListener {
    final /* synthetic */ Diamond a;
    final /* synthetic */ DiamondAdapter b;

    a(DiamondAdapter diamondAdapter, Diamond diamond) {
        this.b = diamondAdapter;
        this.a = diamond;
    }

    public void onClick(View view) {
        this.b.a(this.a.pr, Float.parseFloat(this.a.mv));
    }
}
