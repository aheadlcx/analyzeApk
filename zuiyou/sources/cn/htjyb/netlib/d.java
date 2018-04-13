package cn.htjyb.netlib;

import android.os.AsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public abstract class d {
    private static a a;
    private static final HashSet<d> i = new HashSet();
    public int b;
    public cn.htjyb.netlib.b.a c;
    boolean d;
    protected String e;
    protected b f;
    protected JSONObject g;
    protected a h;
    private e j;
    private b k;

    public interface a {
        void onTaskFinish(d dVar);
    }

    private class b extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ d a;

        private b(d dVar) {
            this.a = dVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onCancelled(Object obj) {
            b((Void) obj);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        protected Void a(Void... voidArr) {
            long currentTimeMillis = System.currentTimeMillis();
            this.a.a((AsyncTask) this);
            this.a.b = (int) (System.currentTimeMillis() - currentTimeMillis);
            return null;
        }

        protected void a(Void voidR) {
            if (this == this.a.k) {
                d.i.remove(this.a);
                this.a.k = null;
                if (!this.a.c.a) {
                    com.izuiyou.a.a.b.d("url: " + this.a.e + ", errorCode: " + this.a.c.b + ", errMsg: " + this.a.c.c());
                }
                if (this.a.h != null) {
                    this.a.h.onTaskFinish(this.a);
                }
                if (d.a != null) {
                    d.a.onTaskFinish(this.a);
                }
            }
            if (this.a.j != null) {
                this.a.j.b(this.a);
            }
        }

        protected void b(Void voidR) {
            if (this.a.j != null) {
                this.a.j.b(this.a);
            }
        }
    }

    protected abstract void a(AsyncTask asyncTask);

    public d(String str, b bVar, JSONObject jSONObject, a aVar) {
        this.d = true;
        this.e = str;
        this.f = bVar;
        this.g = jSONObject;
        this.h = aVar;
        i.add(this);
    }

    public d(String str, b bVar, a aVar) {
        this(str, bVar, null, aVar);
    }

    public String a() {
        return this.e;
    }

    public void a(e eVar) {
        this.j = eVar;
    }

    public d b() {
        if (this.k == null) {
            i.add(this);
            this.k = new b();
            this.k.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        return this;
    }

    public void c() {
        a(true);
    }

    public void a(boolean z) {
        if (this.k != null) {
            this.k.cancel(z);
            this.k = null;
        }
        i.remove(this);
        if (this.j != null) {
            this.j.a(this);
        }
    }
}
