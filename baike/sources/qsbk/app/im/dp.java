package qsbk.app.im;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class dp implements OnTouchListener {
    final /* synthetic */ GroupConversationActivity a;

    dp(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.aM = 0;
            this.a.aK = 0;
        }
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
