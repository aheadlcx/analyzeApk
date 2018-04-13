package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class hc implements OnClickListener {
    final /* synthetic */ LivePermissionDialog a;

    hc(LivePermissionDialog livePermissionDialog) {
        this.a = livePermissionDialog;
    }

    public void onClick(View view) {
        this.a.j();
    }
}
