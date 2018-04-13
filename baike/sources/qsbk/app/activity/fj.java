package qsbk.app.activity;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class fj implements OnEditorActionListener {
    final /* synthetic */ CircleArticleActivity a;

    fj(CircleArticleActivity circleArticleActivity) {
        this.a = circleArticleActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        this.a.i.performClick();
        return true;
    }
}
