package qsbk.app.slide;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class bc implements OnTouchListener {
    final /* synthetic */ SingleArticleFragment a;
    final /* synthetic */ a b;

    bc(a aVar, SingleArticleFragment singleArticleFragment) {
        this.b = aVar;
        this.a = singleArticleFragment;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.b.a();
        return false;
    }
}
