package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class adh implements OnClickListener {
    final /* synthetic */ SwitchTestDomainActivity a;

    adh(SwitchTestDomainActivity switchTestDomainActivity) {
        this.a = switchTestDomainActivity;
    }

    public void onClick(View view) {
        this.a.startActivity(new Intent(this.a, RemixConfigActivity.class));
    }
}
