package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.GroupInfoActivity;
import qsbk.app.im.ChatListAdapter.InviteShowerOther;
import qsbk.app.model.GroupInfo;

class v implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ InviteShowerOther b;

    v(InviteShowerOther inviteShowerOther, GroupInfo groupInfo) {
        this.b = inviteShowerOther;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        GroupInfoActivity.launch(view.getContext(), this.a);
    }
}
