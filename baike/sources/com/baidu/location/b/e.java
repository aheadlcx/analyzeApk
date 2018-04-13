package com.baidu.location.b;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.f;

class e extends Handler {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void handleMessage(Message message) {
        if (f.isServing) {
            switch (message.what) {
                case 1:
                    this.a.e((Location) message.obj);
                    return;
                case 2:
                    if (this.a.j != null) {
                        this.a.j.a((String) message.obj);
                        return;
                    }
                    return;
                case 3:
                    this.a.a("&og=1", (Location) message.obj);
                    return;
                case 4:
                    this.a.a("&og=2", (Location) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
