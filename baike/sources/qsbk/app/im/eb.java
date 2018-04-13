package qsbk.app.im;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class eb implements OnEditorActionListener {
    final /* synthetic */ GroupConversationActivity a;

    eb(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        this.a.f.performClick();
        return true;
    }
}
