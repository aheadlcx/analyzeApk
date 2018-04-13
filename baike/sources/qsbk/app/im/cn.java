package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class cn implements OnClickListener {
    final /* synthetic */ ConversationActivity a;

    cn(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onClick(View view) {
        if (!this.a.t()) {
            return;
        }
        if (this.a.Z) {
            this.a.hideEmojiAfterClickItem();
        } else {
            this.a.m();
        }
    }
}
