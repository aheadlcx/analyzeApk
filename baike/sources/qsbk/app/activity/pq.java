package qsbk.app.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class pq implements OnTouchListener {
    final /* synthetic */ ImagesPickerForCollectActivity a;

    pq(ImagesPickerForCollectActivity imagesPickerForCollectActivity) {
        this.a = imagesPickerForCollectActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a.j();
                break;
        }
        return true;
    }
}
