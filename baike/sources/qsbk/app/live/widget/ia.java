package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveRedEnvelopes;

class ia implements OnClickListener {
    final /* synthetic */ LiveRedEnvelopes a;
    final /* synthetic */ RedEnvelopesDialog b;

    ia(RedEnvelopesDialog redEnvelopesDialog, LiveRedEnvelopes liveRedEnvelopes) {
        this.b = redEnvelopesDialog;
        this.a = liveRedEnvelopes;
    }

    public void onClick(View view) {
        if (this.b.i != null && this.b.i.onSend(this.a.pwd, this.a.id)) {
            this.b.dismiss();
        }
    }
}
