package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeGetActivity;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.utils.Util;

class ab implements OnClickListener {
    final /* synthetic */ LaiseeImInfo a;
    final /* synthetic */ o b;

    ab(o oVar, LaiseeImInfo laiseeImInfo) {
        this.b = oVar;
        this.a = laiseeImInfo;
    }

    public void onClick(View view) {
        this.b.a.setEnabled(false);
        LaiseeGetActivity.launch(Util.getActivityOrContext(view), this.b.g.j, new Laisee(this.a.laiseeId, this.a.secret));
        this.b.a.postDelayed(new ac(this), 300);
    }
}
