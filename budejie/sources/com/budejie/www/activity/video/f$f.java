package com.budejie.www.activity.video;

import android.os.AsyncTask;
import android.util.Log;
import com.budejie.www.util.ac;

class f$f extends AsyncTask {
    final /* synthetic */ f a;

    private f$f(f fVar) {
        this.a = fVar;
    }

    protected Object doInBackground(Object[] objArr) {
        try {
            Log.e("MicroMediaController", "updateProgressTask doInBackground");
            if (f.d(this.a) == null || f.s(this.a)) {
                return Integer.valueOf(0);
            }
            f.b(this.a, f.d(this.a).getCurrentPosition());
            f.c(this.a, f.d(this.a).getDuration());
            Log.e("MicroMediaController", "mPosition =" + f.F(this.a));
            Log.e("MicroMediaController", "duration=" + f.G(this.a));
            f.u(this.a).obtainMessage(5, ac.a((long) f.F(this.a))).sendToTarget();
            if (f.C(this.a) != null) {
                if (f.G(this.a) > 0) {
                    f.u(this.a).obtainMessage(6, f.G(this.a), f.F(this.a)).sendToTarget();
                    if (f.l(this.a) != null) {
                        f.l(this.a).a(f.F(this.a));
                    }
                }
                f.u(this.a).obtainMessage(7, (f.d(this.a).getBufferPercentage() * f.G(this.a)) / 100, 0).sendToTarget();
            }
            return Integer.valueOf(f.F(this.a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
