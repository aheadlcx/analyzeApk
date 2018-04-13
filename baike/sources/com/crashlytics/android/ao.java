package com.crashlytics.android;

import android.os.AsyncTask;

final class ao extends AsyncTask<Void, Void, Void> {
    private /* synthetic */ long a;
    private /* synthetic */ CrashTest b;

    ao(CrashTest crashTest, long j) {
        this.b = crashTest;
        this.a = j;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    private Void a() {
        try {
            Thread.sleep(this.a);
        } catch (InterruptedException e) {
        }
        this.b.throwRuntimeException("Background thread crash");
        return null;
    }
}
