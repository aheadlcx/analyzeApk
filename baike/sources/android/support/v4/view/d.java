package android.support.v4.view;

import android.os.Handler.Callback;
import android.os.Message;

class d implements Callback {
    final /* synthetic */ AsyncLayoutInflater a;

    d(AsyncLayoutInflater asyncLayoutInflater) {
        this.a = asyncLayoutInflater;
    }

    public boolean handleMessage(Message message) {
        b bVar = (b) message.obj;
        if (bVar.d == null) {
            bVar.d = this.a.a.inflate(bVar.c, bVar.b, false);
        }
        bVar.e.onInflateFinished(bVar.d, bVar.c, bVar.b);
        this.a.c.releaseRequest(bVar);
        return true;
    }
}
