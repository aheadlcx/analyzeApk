package android.support.v7.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class cg implements OnEditorActionListener {
    final /* synthetic */ SearchView a;

    cg(SearchView searchView) {
        this.a = searchView;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.a.c();
        return true;
    }
}
