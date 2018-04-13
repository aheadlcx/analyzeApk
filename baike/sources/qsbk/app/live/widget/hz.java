package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class hz implements OnClickListener {
    final /* synthetic */ RedEnvelopesDialog a;

    hz(RedEnvelopesDialog redEnvelopesDialog) {
        this.a = redEnvelopesDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
