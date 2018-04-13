package qsbk.app.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class abl implements OnEditorActionListener {
    final /* synthetic */ SearchGroupActivity a;

    abl(SearchGroupActivity searchGroupActivity) {
        this.a = searchGroupActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        String obj = this.a.a.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            this.a.a(obj);
        }
        return true;
    }
}
