package cn.htjyb.netlib;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.htjyb.netlib.b.b;
import java.util.Collection;
import org.json.JSONObject;

public class g extends d {
    private a a;
    private int i = 10;
    private Handler j;
    private final Collection<b> k;

    public interface a {
        void a(int i, int i2);
    }

    public g(String str, b bVar, Collection<b> collection, JSONObject jSONObject, cn.htjyb.netlib.d.a aVar) {
        super(str, bVar, jSONObject, aVar);
        this.k = collection;
    }

    @SuppressLint({"HandlerLeak"})
    public void a(a aVar) {
        this.a = aVar;
        if (aVar != null && this.j == null) {
            this.j = new Handler(this, Looper.getMainLooper()) {
                final /* synthetic */ g a;

                public void handleMessage(Message message) {
                    if (this.a.j != null && this.a.a != null) {
                        this.a.a.a(message.arg1, message.arg2);
                    }
                }
            };
        }
    }

    public void a(int i) {
        this.i = i;
    }

    protected void a(AsyncTask asyncTask) {
        a anonymousClass2;
        if (this.j != null) {
            anonymousClass2 = new a(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void a(int i, int i2) {
                    this.a.j.obtainMessage(0, i, i2).sendToTarget();
                }
            };
        } else {
            anonymousClass2 = null;
        }
        if (anonymousClass2 == null) {
            this.c = this.f.a(this, this.e, this.k, this.g);
        } else {
            this.c = this.f.a(this, this.e, this.k, this.g, anonymousClass2, this.i);
        }
        this.j = null;
    }
}
