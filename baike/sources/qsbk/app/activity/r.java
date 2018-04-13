package qsbk.app.activity;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class r implements OnEditorActionListener {
    final /* synthetic */ ActionBarLoginActivity a;

    r(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (keyEvent == null || keyEvent.getKeyCode() != 66) {
            return false;
        }
        ActionBarLoginActivity.o(this.a);
        return true;
    }
}
