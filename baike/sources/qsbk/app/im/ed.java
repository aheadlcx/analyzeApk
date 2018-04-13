package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class ed implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ed(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(View view) {
        if (this.a.t()) {
            if (this.a.Z) {
                this.a.hideEmojiAfterClickItem();
            } else {
                this.a.m();
            }
        }
        this.a.G.requestFocus();
    }
}
