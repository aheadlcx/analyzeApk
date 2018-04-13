package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class acj implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    acj(SecurityBindActivity securityBindActivity) {
        this.a = securityBindActivity;
    }

    public void onClick(View view) {
        ModifyPwdActivity.launch(this.a);
    }
}
