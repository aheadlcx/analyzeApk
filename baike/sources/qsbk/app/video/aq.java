package qsbk.app.video;

import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class aq implements OnTouchListener {
    final /* synthetic */ VideoEditPlayView a;

    aq(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Message message = new Message();
            message.what = 0;
            message.obj = view;
            this.a.s.sendMessage(message);
        }
        return false;
    }
}
