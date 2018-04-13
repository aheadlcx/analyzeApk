package qsbk.app.im;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class cb implements OnTouchListener {
    final /* synthetic */ ConversationActivity a;

    cb(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.a.t()) {
            if (this.a.Z) {
                this.a.hideEmojiAfterClickItem();
            } else {
                this.a.m();
            }
        }
        return false;
    }
}
