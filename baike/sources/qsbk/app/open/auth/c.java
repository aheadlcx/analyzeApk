package qsbk.app.open.auth;

import android.content.Intent;
import qsbk.app.utils.ResultActivityListener;

class c implements ResultActivityListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == 0) {
            this.a.a.h = false;
            this.a.a.i = false;
            this.a.a.a(1);
            return;
        }
        this.a.a.g();
    }
}
