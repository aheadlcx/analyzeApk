package android.support.v4.media;

import android.content.Intent;
import android.util.Log;

class j implements Runnable {
    final /* synthetic */ f a;

    j(f fVar) {
        this.a = fVar;
    }

    public void run() {
        if (this.a.f != 0) {
            this.a.f = 2;
            if (MediaBrowserCompat.a && this.a.g != null) {
                throw new RuntimeException("mServiceConnection should be null. Instead it is " + this.a.g);
            } else if (this.a.h != null) {
                throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.a.h);
            } else if (this.a.i != null) {
                throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.a.i);
            } else {
                Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
                intent.setComponent(this.a.b);
                this.a.g = new a(this.a);
                boolean z = false;
                try {
                    z = this.a.a.bindService(intent, this.a.g, 1);
                } catch (Exception e) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + this.a.b);
                }
                if (!z) {
                    this.a.a();
                    this.a.c.onConnectionFailed();
                }
                if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "connect...");
                    this.a.b();
                }
            }
        }
    }
}
