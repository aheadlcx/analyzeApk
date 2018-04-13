package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class qb implements OnClickListener {
    final /* synthetic */ InviteQiuYouActivity a;

    qb(InviteQiuYouActivity inviteQiuYouActivity) {
        this.a = inviteQiuYouActivity;
    }

    public void onClick(View view) {
        InviteFriendActivity.launch(this.a, this.a.f.id);
        this.a.finish();
    }
}
