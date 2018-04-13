package qsbk.app.video;

import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class r implements OnTouchListener {
    final /* synthetic */ VideoCropView a;

    r(VideoCropView videoCropView) {
        this.a = videoCropView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Message message = new Message();
            message.what = 0;
            message.obj = view;
            this.a.t.sendMessage(message);
        }
        return false;
    }
}
