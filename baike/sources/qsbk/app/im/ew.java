package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.RunForOwnerActivity;

class ew implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ew(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        RunForOwnerActivity.launch(this.a, this.a.au.id, this.a.aw);
    }
}
