package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class px implements OnClickListener {
    final /* synthetic */ InviteFriendActivity a;

    px(InviteFriendActivity inviteFriendActivity) {
        this.a = inviteFriendActivity;
    }

    public void onClick(View view) {
        this.a.a(this.a.d.getCheckedIds());
    }
}
