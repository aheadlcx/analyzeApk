package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.Constants;

class adf implements OnClickListener {
    final /* synthetic */ SwitchTestDomainActivity a;

    adf(SwitchTestDomainActivity switchTestDomainActivity) {
        this.a = switchTestDomainActivity;
    }

    public void onClick(View view) {
        this.a.i();
        Constants.init();
        MainActivity.mInstance.reload();
        this.a.finish();
    }
}
