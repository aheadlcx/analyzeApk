package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.HighLightQiushiActivity;
import qsbk.app.model.Qsjx;

class ar implements OnClickListener {
    final /* synthetic */ Qsjx a;
    final /* synthetic */ ai b;

    ar(ai aiVar, Qsjx qsjx) {
        this.b = aiVar;
        this.a = qsjx;
    }

    public void onClick(View view) {
        HighLightQiushiActivity.luanchActivity(this.b.a.h, this.a.msgId);
    }
}
