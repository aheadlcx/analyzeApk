package qsbk.app.api;

import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.HttpClient;

class b extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ Map a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    b(a aVar, Map map, String str) {
        this.c = aVar;
        this.a = map;
        this.b = str;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            HttpClient intentce = HttpClient.getIntentce();
            String str = Constants.URL_QINIU_KEY;
            Object[] objArr = new Object[1];
            QsbkApp.getInstance();
            objArr[0] = QsbkApp.currentUser.userId;
            JSONObject jSONObject = new JSONObject(intentce.post(String.format(str, objArr), this.a));
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                return new Pair(Integer.valueOf(i), "上传大罩成功。");
            }
            return new Pair(Integer.valueOf(i), jSONObject.getString("err_msg"));
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
            if (this.c.b != null) {
                this.c.b.onSuccess(this.c.c, this.b);
            }
        } else if (this.c.b != null) {
            this.c.b.onFail(((Integer) pair.first).intValue(), (String) pair.second);
        }
    }
}
