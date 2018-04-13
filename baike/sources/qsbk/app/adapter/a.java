package qsbk.app.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class a implements OnTouchListener {
    final /* synthetic */ ArticleAdapter a;

    a(ArticleAdapter articleAdapter) {
        this.a = articleAdapter;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                view.setSelected(true);
                break;
            default:
                view.setSelected(false);
                break;
        }
        return false;
    }
}
