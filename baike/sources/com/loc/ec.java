package com.loc;

import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

final class ec extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ Editor a;

    ec(Editor editor) {
        this.a = editor;
    }

    private Void a() {
        try {
            if (this.a != null) {
                this.a.commit();
            }
        } catch (Throwable th) {
            cw.a(th, "SPUtil", "commit");
        }
        return null;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }
}
