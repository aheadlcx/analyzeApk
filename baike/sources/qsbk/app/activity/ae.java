package qsbk.app.activity;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class ae implements OnEditorActionListener {
    final /* synthetic */ ActionBarLoginRegisterBaseActivity a;

    ae(ActionBarLoginRegisterBaseActivity actionBarLoginRegisterBaseActivity) {
        this.a = actionBarLoginRegisterBaseActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 5 && (keyEvent == null || keyEvent.getKeyCode() != 66)) {
            return false;
        }
        this.a.e.requestFocus();
        return true;
    }
}
