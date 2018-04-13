package qsbk.app.video;

import android.support.v4.app.NotificationCompat;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

class i extends AsyncTask<String, Void, String> {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ QiuyouCircleVideoLoopStatistics b;

    i(QiuyouCircleVideoLoopStatistics qiuyouCircleVideoLoopStatistics, JSONObject jSONObject) {
        this.b = qiuyouCircleVideoLoopStatistics;
        this.a = jSONObject;
    }

    protected String a(String... strArr) {
        try {
            return HttpClient.getIntentce().post(strArr[0], this.a.toString());
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void a(String str) {
        if (str != null) {
            try {
                if (new JSONObject(str).optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                    this.b.b();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        synchronized (this.b.b) {
            this.b.d = false;
        }
    }
}
