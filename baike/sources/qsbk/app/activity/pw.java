package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class pw implements OnClickListener {
    final /* synthetic */ InviteFriendActivity a;

    pw(InviteFriendActivity inviteFriendActivity) {
        this.a = inviteFriendActivity;
    }

    public void onClick(View view) {
        this.a.l.setText("");
    }
}
