package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class qa implements OnClickListener {
    final /* synthetic */ InviteQiuYouActivity a;

    qa(InviteQiuYouActivity inviteQiuYouActivity) {
        this.a = inviteQiuYouActivity;
    }

    public void onClick(View view) {
        this.a.a(this.a.c.getCheckedIds());
    }
}
