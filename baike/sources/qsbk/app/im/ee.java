package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class ee implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ee(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        this.a.d.smoothScrollToPosition(this.a.g.getCount());
    }
}
