package com.budejie.www.widget.curtain;

import android.os.AsyncTask;
import com.budejie.www.activity.video.barrage.b;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class b$4 extends a<String> {
    final /* synthetic */ b a;

    b$4(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        new AsyncTask<String, String, b>(this) {
            final /* synthetic */ b$4 a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((b) obj);
            }

            protected b a(String... strArr) {
                try {
                    return (b) z.a(strArr[0], b.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            protected void a(b bVar) {
                b.a(this.a.a, bVar);
            }
        }.execute(new String[]{str});
    }
}
