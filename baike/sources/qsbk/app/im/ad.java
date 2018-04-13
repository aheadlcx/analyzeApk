package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeGetActivity;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.utils.Util;

class ad implements OnClickListener {
    final /* synthetic */ j a;
    final /* synthetic */ LaiseeImInfo b;
    final /* synthetic */ p c;

    ad(p pVar, j jVar, LaiseeImInfo laiseeImInfo) {
        this.c = pVar;
        this.a = jVar;
        this.b = laiseeImInfo;
    }

    public void onClick(View view) {
        this.a.a.setEnabled(false);
        LaiseeGetActivity.launch(Util.getActivityOrContext(view), this.c.a.j, new Laisee(this.b.laiseeId, this.b.secret));
        this.a.a.postDelayed(new ae(this), 200);
    }
}
