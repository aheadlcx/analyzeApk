package qsbk.app.slide;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class u implements OnEditorActionListener {
    final /* synthetic */ SingleArticleFragment a;

    u(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        this.a.u.performClick();
        return true;
    }
}
