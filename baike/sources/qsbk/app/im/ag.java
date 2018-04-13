package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.LaiseeGetActivity;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeImInfo;
import qsbk.app.utils.Util;

class ag implements OnClickListener {
    final /* synthetic */ LaiseeImInfo a;
    final /* synthetic */ s b;

    ag(s sVar, LaiseeImInfo laiseeImInfo) {
        this.b = sVar;
        this.a = laiseeImInfo;
    }

    public void onClick(View view) {
        this.b.a.setEnabled(false);
        LaiseeGetActivity.launch(Util.getActivityOrContext(view), this.b.g.j, new Laisee(this.a.laiseeId, this.a.secret));
        this.b.a.postDelayed(new ah(this), 300);
    }
}
