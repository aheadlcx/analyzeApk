package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aef implements OnClickListener {
    final /* synthetic */ UserRevokeActivity a;

    aef(UserRevokeActivity userRevokeActivity) {
        this.a = userRevokeActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
