package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aci implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    aci(SecurityBindActivity securityBindActivity) {
        this.a = securityBindActivity;
    }

    public void onClick(View view) {
        SocialBindActivity.launch(this.a);
    }
}
