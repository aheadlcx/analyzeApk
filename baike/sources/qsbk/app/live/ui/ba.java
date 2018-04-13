package qsbk.app.live.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class ba implements OnKeyListener {
    final /* synthetic */ LiveBaseActivity a;

    ba(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 67 && this.a.aa != null && this.a.Y.getText().toString().equals(this.a.aa.trim())) {
            this.a.aa = "";
            this.a.Y.setText("");
        }
        return false;
    }
}
