package qsbk.app.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class abw implements OnKeyListener {
    final /* synthetic */ SearchQiuYouActivity a;

    abw(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 66 && keyEvent.getAction() == 1) {
            this.a.a(this.a.e.getText().toString(), true);
        }
        return false;
    }
}
