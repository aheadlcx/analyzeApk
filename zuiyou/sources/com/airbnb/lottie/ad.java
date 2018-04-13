package com.airbnb.lottie;

import android.content.res.Resources;
import com.airbnb.lottie.ai.a;
import org.json.JSONObject;

final class ad extends s<JSONObject> {
    private final Resources a;
    private final am b;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((JSONObject[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((ai) obj);
    }

    ad(Resources resources, am amVar) {
        this.a = resources;
        this.b = amVar;
    }

    protected ai a(JSONObject... jSONObjectArr) {
        return a.a(this.a, jSONObjectArr[0]);
    }

    protected void a(ai aiVar) {
        this.b.a(aiVar);
    }
}
