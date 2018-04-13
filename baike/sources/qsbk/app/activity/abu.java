package qsbk.app.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import qsbk.app.utils.UIHelper;

class abu implements OnTouchListener {
    final /* synthetic */ SearchQiuYouActivity a;

    abu(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        UIHelper.hideKeyboard(this.a);
        return false;
    }
}
