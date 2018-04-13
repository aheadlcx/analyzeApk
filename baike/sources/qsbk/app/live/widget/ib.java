package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ib implements OnClickListener {
    final /* synthetic */ RedEnvelopesResultDialog a;

    ib(RedEnvelopesResultDialog redEnvelopesResultDialog) {
        this.a = redEnvelopesResultDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
