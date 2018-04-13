package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.GroupInfoActivity;

class er implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    er(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        if (this.a.at) {
            this.a.finish();
        } else {
            GroupInfoActivity.launch(this.a, Integer.parseInt(this.a.getToId()), this.a.getToNick(), true);
        }
    }
}
