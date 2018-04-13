package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.ui.LiveBaseActivity;

class bm implements OnClickListener {
    final /* synthetic */ FollowTipsDialog a;

    bm(FollowTipsDialog followTipsDialog) {
        this.a = followTipsDialog;
    }

    public void onClick(View view) {
        if (this.a.a instanceof LiveBaseActivity) {
            ((LiveBaseActivity) this.a.a).doFollowAnchor(false);
        }
        this.a.dismiss();
    }
}
