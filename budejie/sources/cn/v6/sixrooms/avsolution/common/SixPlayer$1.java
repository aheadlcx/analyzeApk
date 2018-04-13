package cn.v6.sixrooms.avsolution.common;

import android.os.Handler;
import android.os.Message;
import java.util.Iterator;

class SixPlayer$1 extends Handler {
    final /* synthetic */ SixPlayer this$0;

    SixPlayer$1(SixPlayer sixPlayer) {
        this.this$0 = sixPlayer;
    }

    public void handleMessage(Message message) {
        if (!SixPlayer.access$000(this.this$0)) {
            Iterator it;
            switch (message.what) {
                case 1:
                    Iterator it2 = SixPlayer.access$100(this.this$0).iterator();
                    while (it2.hasNext()) {
                        ((PlayerCallBack) it2.next()).onError(message.arg1);
                    }
                    return;
                case 2:
                    synchronized (SixPlayer.access$200(this.this$0)) {
                        it = SixPlayer.access$100(this.this$0).iterator();
                        while (it.hasNext()) {
                            ((PlayerCallBack) it.next()).onBufferLoad();
                        }
                    }
                    return;
                case 3:
                    synchronized (SixPlayer.access$200(this.this$0)) {
                        it = SixPlayer.access$100(this.this$0).iterator();
                        while (it.hasNext()) {
                            ((PlayerCallBack) it.next()).onBufferEmpty();
                        }
                    }
                    return;
                case 4:
                    return;
                case 5:
                    SixPlayer.access$300();
                    synchronized (SixPlayer.access$200(this.this$0)) {
                        it = SixPlayer.access$100(this.this$0).iterator();
                        while (it.hasNext()) {
                            ((PlayerCallBack) it.next()).onVideoEnd();
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
