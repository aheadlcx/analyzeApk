package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class es implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    es(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        if (this.a.aq.getVisibility() == 0) {
            this.a.W();
        } else {
            this.a.V();
        }
    }
}
