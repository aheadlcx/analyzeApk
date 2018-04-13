package qsbk.app.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class pf implements OnTouchListener {
    final /* synthetic */ ImagesPickerActivity a;

    pf(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
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
