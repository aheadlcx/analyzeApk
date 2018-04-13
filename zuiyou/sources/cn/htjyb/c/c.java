package cn.htjyb.c;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.izuiyou.a.a.b;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class c extends Thread {
    private Looper a;
    private Handler b;
    private ArrayList<Message> c = new ArrayList();

    @SuppressLint({"HandlerLeak"})
    private class a extends Handler {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void handleMessage(Message message) {
            this.a.b(message);
        }
    }

    protected abstract void b(Message message);

    public void a() {
        if (this.a != null) {
            this.a.quit();
            this.a = null;
            this.b = null;
        }
    }

    public void a(Message message) {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.c.add(message);
                    return;
                }
            }
        }
        this.b.sendMessage(message);
    }

    public void run() {
        b.a((Object) "enter");
        Looper.prepare();
        this.a = Looper.myLooper();
        Handler aVar = new a();
        synchronized (this) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                aVar.sendMessage((Message) it.next());
            }
            this.b = aVar;
            this.c = null;
        }
        Looper.loop();
        b.a((Object) "exit");
    }
}
