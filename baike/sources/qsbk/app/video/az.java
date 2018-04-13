package qsbk.app.video;

import android.support.v4.app.NotificationCompat;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.Logger;

class az extends AsyncTask<String, Void, String> {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ VideoLoopStatistics b;

    az(VideoLoopStatistics videoLoopStatistics, JSONObject jSONObject) {
        this.b = videoLoopStatistics;
        this.a = jSONObject;
    }

    protected String a(String... strArr) {
        String jSONObject = this.a.toString();
        Logger.getInstance().debug(VideoLoopStatistics.class.getSimpleName(), "doInBackground", "Batch post params " + jSONObject);
        try {
            return HttpClient.getIntentce().post(strArr[0], jSONObject);
        } catch (QiushibaikeException e) {
            return null;
        }
    }

    protected void a(String str) {
        Logger.getInstance().debug(VideoLoopStatistics.class.getSimpleName(), "onPostExecute", "Batch post result " + str);
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
