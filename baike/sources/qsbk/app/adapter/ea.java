package qsbk.app.adapter;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class ea implements OnTouchListener {
    final /* synthetic */ VideoImmersionCell a;

    ea(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
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
