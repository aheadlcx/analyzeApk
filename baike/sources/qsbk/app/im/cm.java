package qsbk.app.im;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import qsbk.app.utils.LogUtil;

class cm implements OnEditorActionListener {
    final /* synthetic */ ConversationActivity a;

    cm(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 4) {
            this.a.f.performClick();
            return true;
        }
        LogUtil.d("sending in editor action");
        this.a.sendTypingStatus();
        return false;
    }
}
