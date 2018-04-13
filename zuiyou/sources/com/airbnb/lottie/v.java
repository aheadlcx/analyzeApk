package com.airbnb.lottie;

import android.content.res.Resources;
import com.airbnb.lottie.ai.a;
import java.io.InputStream;

final class v extends s<InputStream> {
    private final Resources a;
    private final am b;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((InputStream[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((ai) obj);
    }

    v(Resources resources, am amVar) {
        this.a = resources;
        this.b = amVar;
    }

    protected ai a(InputStream... inputStreamArr) {
        return a.a(this.a, inputStreamArr[0]);
    }

    protected void a(ai aiVar) {
        this.b.a(aiVar);
    }
}
