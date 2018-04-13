package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.Payment;

class bn implements OnClickListener {
    final /* synthetic */ Payment a;
    final /* synthetic */ a b;

    bn(a aVar, Payment payment) {
        this.b = aVar;
        this.a = payment;
    }

    public void onClick(View view) {
        this.b.a.setResult(this.a);
    }
}
