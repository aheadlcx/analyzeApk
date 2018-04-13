package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class acl implements OnClickListener {
    final /* synthetic */ SecuritySafeActivity a;

    acl(SecuritySafeActivity securitySafeActivity) {
        this.a = securitySafeActivity;
    }

    public void onClick(View view) {
        this.a.g();
    }
}
