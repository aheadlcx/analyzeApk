package qsbk.app.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class bv implements OnTouchListener {
    final /* synthetic */ ManageMyContentsAdapter a;

    bv(ManageMyContentsAdapter manageMyContentsAdapter) {
        this.a = manageMyContentsAdapter;
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
