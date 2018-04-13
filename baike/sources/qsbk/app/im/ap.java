package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.RedirectActivity;
import qsbk.app.model.DeeplinkInfo;

class ap implements OnClickListener {
    final /* synthetic */ DeeplinkInfo a;
    final /* synthetic */ ag b;

    ap(ag agVar, DeeplinkInfo deeplinkInfo) {
        this.b = agVar;
        this.a = deeplinkInfo;
    }

    public void onClick(View view) {
        RedirectActivity.navigateToActivity(this.b.a.h, this.a.deepLink);
    }
}
