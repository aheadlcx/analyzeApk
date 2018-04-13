package qsbk.app.open.auth;

import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpClient;

public abstract class CommHttpAsyncTask extends AsyncTask<String, Void, Pair<Integer, String>> {
    private JSONObject a;

    public abstract String getJSONResp() throws Exception;

    public static Pair<Integer, String> getLocalError() {
        return new Pair(Integer.valueOf(9999), HttpClient.getLocalErrorStr());
    }

    public JSONObject getJSONObj() {
        return this.a;
    }

    public void run() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            this.a = new JSONObject(getJSONResp());
            return new Pair(Integer.valueOf(this.a.getInt(NotificationCompat.CATEGORY_ERROR)), this.a.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return getLocalError();
        }
    }
}
