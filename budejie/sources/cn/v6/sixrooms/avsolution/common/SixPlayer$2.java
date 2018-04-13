package cn.v6.sixrooms.avsolution.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;

class SixPlayer$2 extends Thread {
    final /* synthetic */ SixPlayer this$0;

    SixPlayer$2(SixPlayer sixPlayer) {
        this.this$0 = sixPlayer;
    }

    public void run() {
        Looper.prepare();
        SixPlayer.access$402(this.this$0, new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        Surface surface = SixPlayer$2.this.this$0.getHolder().getSurface();
                        if (surface != null) {
                            SixPlayer.access$002(SixPlayer$2.this.this$0, false);
                            if (SixPlayer.access$500(SixPlayer$2.this.this$0, surface, (String) message.obj) < 0) {
                                SixPlayer.access$600(SixPlayer$2.this.this$0, 2);
                                return;
                            }
                            return;
                        }
                        return;
                    case 2:
                    case 3:
                        SixPlayer.access$002(SixPlayer$2.this.this$0, true);
                        SixPlayer.access$700(SixPlayer$2.this.this$0).removeCallbacks(null);
                        SixPlayer.access$300();
                        if (message.what == 3) {
                            Looper.myLooper().quit();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        synchronized (SixPlayer.access$200(this.this$0)) {
            SixPlayer.access$200(this.this$0).notify();
        }
        Looper.loop();
    }
}
