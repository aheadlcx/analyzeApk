package qsbk.app.im;

import android.text.style.ClickableSpan;
import android.view.View;
import qsbk.app.activity.LaiseeDetailActivity;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeImLog;
import qsbk.app.utils.Util;

class y extends ClickableSpan {
    final /* synthetic */ LaiseeImLog a;
    final /* synthetic */ k b;

    y(k kVar, LaiseeImLog laiseeImLog) {
        this.b = kVar;
        this.a = laiseeImLog;
    }

    public void onClick(View view) {
        LaiseeDetailActivity.launch(Util.getActivityOrContext(view), new Laisee(this.a.laiseeId, this.a.secret));
    }
}
