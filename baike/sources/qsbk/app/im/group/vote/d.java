package qsbk.app.im.group.vote;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ GroupManagerVoteActivity a;

    d(GroupManagerVoteActivity groupManagerVoteActivity) {
        this.a = groupManagerVoteActivity;
    }

    public void onClick(View view) {
        this.a.a.cancel();
    }
}
