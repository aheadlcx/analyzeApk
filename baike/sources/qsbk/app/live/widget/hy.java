package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.live.model.LiveRedEnvelopes;

class hy implements OnClickListener {
    final /* synthetic */ LiveRedEnvelopes a;
    final /* synthetic */ RedEnvelopesDialog b;

    hy(RedEnvelopesDialog redEnvelopesDialog, LiveRedEnvelopes liveRedEnvelopes) {
        this.b = redEnvelopesDialog;
        this.a = liveRedEnvelopes;
    }

    public void onClick(View view) {
        User user = new User();
        user.id = this.a.userId;
        user.origin = this.a.source;
        user.origin_id = this.a.userId;
        if (this.b.i != null) {
            this.b.i.onAvartarClick(user);
        }
    }
}
