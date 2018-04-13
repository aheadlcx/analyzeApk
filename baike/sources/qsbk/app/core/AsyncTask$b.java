package qsbk.app.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class AsyncTask$b extends Handler {
    public AsyncTask$b() {
        super(Looper.getMainLooper());
    }

    public void handleMessage(Message message) {
        AsyncTask$a asyncTask$a = (AsyncTask$a) message.obj;
        switch (message.what) {
            case 1:
                AsyncTask.c(asyncTask$a.a, asyncTask$a.b[0]);
                return;
            case 2:
                asyncTask$a.a.b(asyncTask$a.b);
                return;
            default:
                return;
        }
    }
}
