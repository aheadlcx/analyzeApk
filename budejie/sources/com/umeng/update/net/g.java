package com.umeng.update.net;

import android.os.AsyncTask;
import u.upd.d;
import u.upd.e;
import u.upd.f;

public class g extends e {
    private static final String a = g.class.getName();

    public interface a {
        void a();

        void a(u.upd.f.a aVar);
    }

    private class b extends AsyncTask<Integer, Integer, u.upd.f.a> {
        final /* synthetic */ g a;
        private d b;
        private a c;

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return a((Integer[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((u.upd.f.a) obj);
        }

        public b(g gVar, d dVar, a aVar) {
            this.a = gVar;
            this.b = dVar;
            this.c = aVar;
        }

        protected void onPreExecute() {
            if (this.c != null) {
                this.c.a();
            }
        }

        protected void a(u.upd.f.a aVar) {
            if (this.c != null) {
                this.c.a(aVar);
            }
        }

        protected u.upd.f.a a(Integer... numArr) {
            return this.a.a(this.b);
        }
    }

    public u.upd.f.a a(d dVar) {
        f fVar = (f) a(dVar, f.class);
        return fVar == null ? u.upd.f.a.b : fVar.a;
    }

    public void a(d dVar, a aVar) {
        try {
            new b(this, dVar, aVar).execute(new Integer[0]);
        } catch (Exception e) {
            u.upd.b.b(a, "", e);
            if (aVar != null) {
                aVar.a(u.upd.f.a.b);
            }
        }
    }
}
