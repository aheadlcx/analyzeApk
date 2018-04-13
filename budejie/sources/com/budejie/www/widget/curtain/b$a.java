package com.budejie.www.widget.curtain;

import android.os.AsyncTask;
import com.budejie.www.activity.video.barrage.a;

class b$a extends AsyncTask {
    a a;
    final /* synthetic */ b b;

    b$a(b bVar, a aVar) {
        this.b = bVar;
        this.a = aVar;
    }

    protected Object doInBackground(Object[] objArr) {
        if (this.a.g) {
            b.a(this.b, this.a);
        } else {
            b.b(this.b, this.a);
        }
        return null;
    }
}
