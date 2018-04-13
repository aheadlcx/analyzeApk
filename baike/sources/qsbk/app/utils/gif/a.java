package qsbk.app.utils.gif;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

class a extends Handler {
    final /* synthetic */ GifView a;

    a(GifView gifView) {
        this.a = gifView;
    }

    public void handleMessage(Message message) {
        try {
            if (this.a.h != null) {
                this.a.h.setBackgroundDrawable(new BitmapDrawable(this.a.b));
            } else {
                this.a.b();
            }
        } catch (Exception e) {
            Log.e("GifView", e.toString());
        }
    }
}
