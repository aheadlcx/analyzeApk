package qsbk.app.video;

import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;

class q implements Callback {
    final /* synthetic */ VideoCropView a;

    q(VideoCropView videoCropView) {
        this.a = videoCropView;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                View view = (View) message.obj;
                if (this.a.s != view.getScrollX()) {
                    this.a.s = view.getScrollX();
                    this.a.t.sendMessageDelayed(this.a.t.obtainMessage(0, view), 5);
                    break;
                }
                this.a.b();
                break;
        }
        return false;
    }
}
