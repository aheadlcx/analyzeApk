package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.HighLightQiushiActivity;
import qsbk.app.model.Qsjx;

class aq implements OnClickListener {
    final /* synthetic */ Qsjx a;
    final /* synthetic */ ah b;

    aq(ah ahVar, Qsjx qsjx) {
        this.b = ahVar;
        this.a = qsjx;
    }

    public void onClick(View view) {
        HighLightQiushiActivity.luanchActivity(this.b.a.h, this.a.msgId);
    }
}
