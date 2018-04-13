package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.ui.NetworkConfigActivity;

class adg implements OnClickListener {
    final /* synthetic */ SwitchTestDomainActivity a;

    adg(SwitchTestDomainActivity switchTestDomainActivity) {
        this.a = switchTestDomainActivity;
    }

    public void onClick(View view) {
        this.a.startActivity(new Intent(this.a, NetworkConfigActivity.class));
    }
}
