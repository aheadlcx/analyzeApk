package qsbk.app.activity;

import android.content.Intent;
import qsbk.app.core.AsyncTask;

class jz extends AsyncTask<Void, Void, Intent> {
    final /* synthetic */ jy a;

    jz(jy jyVar) {
        this.a = jyVar;
    }

    protected Intent a(Void... voidArr) {
        return this.a.a.d();
    }

    protected void a(Intent intent) {
        super.a(intent);
        if (intent != null) {
            this.a.a.setResult(-1, intent);
        } else {
            this.a.a.setResult(0);
        }
        this.a.a.i.dismiss();
        this.a.a.finish();
    }
}
