package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ui implements OnClickListener {
    final /* synthetic */ MyAuditListActivity a;

    ui(MyAuditListActivity myAuditListActivity) {
        this.a = myAuditListActivity;
    }

    public void onClick(View view) {
        this.a.b.cancel();
    }
}
