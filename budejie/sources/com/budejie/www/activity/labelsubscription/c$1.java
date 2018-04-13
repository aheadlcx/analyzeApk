package com.budejie.www.activity.labelsubscription;

import android.os.AsyncTask;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.util.an;

class c$1 extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    protected Boolean a(Void... voidArr) {
        return Boolean.valueOf(c.a(this.a));
    }

    protected void a(Boolean bool) {
        super.onPostExecute(bool);
        if (bool.booleanValue()) {
            c.b(this.a);
        } else if (an.a(c.c(this.a))) {
            c.d(this.a);
        } else {
            c.e(this.a);
            Toast.makeText(c.c(this.a), this.a.getString(R.string.nonet), 0).show();
        }
    }
}
