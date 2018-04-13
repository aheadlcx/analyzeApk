package qsbk.app.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class at implements OnKeyListener {
    final /* synthetic */ AddQiuYouActivity a;

    at(AddQiuYouActivity addQiuYouActivity) {
        this.a = addQiuYouActivity;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 66 && keyEvent.getAction() == 1) {
            this.a.d();
        }
        return false;
    }
}
