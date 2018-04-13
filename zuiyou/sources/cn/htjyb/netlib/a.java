package cn.htjyb.netlib;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import org.json.JSONObject;

public class a extends d {
    public final String a;
    private HashMap<String, String> i;
    private boolean j;
    private boolean k;
    private a l;
    private int m;
    private Handler n;

    public interface a {
        void a(int i, int i2);
    }

    public a(String str, b bVar, String str2, cn.htjyb.netlib.d.a aVar) {
        this(str, null, bVar, str2, aVar);
    }

    public a(String str, HashMap<String, String> hashMap, b bVar, String str2, cn.htjyb.netlib.d.a aVar) {
        this(str, hashMap, bVar, str2, aVar, null);
    }

    public a(String str, HashMap<String, String> hashMap, b bVar, String str2, cn.htjyb.netlib.d.a aVar, JSONObject jSONObject) {
        super(str, bVar, jSONObject, aVar);
        this.m = 200;
        this.i = hashMap;
        this.d = false;
        this.a = str2;
    }

    @SuppressLint({"HandlerLeak"})
    public void a(a aVar) {
        this.l = aVar;
        synchronized (this) {
            if (aVar != null) {
                if (this.n == null) {
                    this.n = new Handler(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void handleMessage(Message message) {
                            if (this.a.n != null && this.a.l != null) {
                                this.a.l.a(message.arg1, message.arg2);
                            }
                        }
                    };
                }
            }
        }
    }

    protected void a(AsyncTask asyncTask) {
        a aVar = null;
        synchronized (this) {
            if (this.n != null) {
                aVar = new a(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a(int i, int i2) {
                        this.a.n.obtainMessage(0, i, i2).sendToTarget();
                    }
                };
            }
        }
        this.c = this.f.a(this, this.e, this.i, this.a, this.g, this.j, this.k, aVar, this.m);
    }
}
