package qsbk.app.api;

import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.api.BigCoverHelper.UploadListener;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.HttpClient;

class c extends AsyncTask<Void, Void, Pair<Integer, String>> {
    final /* synthetic */ UploadListener a;
    final /* synthetic */ Uri b;
    final /* synthetic */ BigCoverHelper c;

    c(BigCoverHelper bigCoverHelper, UploadListener uploadListener, Uri uri) {
        this.c = bigCoverHelper;
        this.a = uploadListener;
        this.b = uri;
    }

    protected Pair<Integer, String> a(Void... voidArr) {
        try {
            HttpClient intentce = HttpClient.getIntentce();
            String str = Constants.URL_GET_TOKEN;
            Object[] objArr = new Object[1];
            QsbkApp.getInstance();
            objArr[0] = QsbkApp.currentUser.userId;
            JSONObject jSONObject = new JSONObject(intentce.get(String.format(str, objArr)));
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                return new Pair(Integer.valueOf(i), jSONObject.optString("uptoken"));
            }
            return new Pair(Integer.valueOf(i), jSONObject.optString("err_msg"));
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return SimpleHttpTask.getLocalError();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return SimpleHttpTask.getLocalError();
        }
    }

    protected void a(Pair<Integer, String> pair) {
        super.a(pair);
        if (((Integer) pair.first).intValue() == 0) {
            if (this.a != null) {
                this.a.onUploading(0, 0);
            }
            this.c.a((String) pair.second, this.b, this.a);
        } else if (this.a != null) {
            this.a.onFail(((Integer) pair.first).intValue(), (String) pair.second);
        }
    }
}
