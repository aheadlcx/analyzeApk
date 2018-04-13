package qsbk.app.fragments;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

class jr extends AsyncTask<String, Void, String> {
    final /* synthetic */ QiuyouCircleFragment a;

    jr(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    protected String a(String... strArr) {
        try {
            return HttpClient.getIntentce().get(strArr[0]);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void a(String str) {
        if (str != null) {
            try {
                if (new JSONObject(str).optBoolean("has_new", false)) {
                    this.a.showSmallTipsOnMainActitivty(true);
                }
            } catch (JSONException e) {
            }
        }
    }
}
