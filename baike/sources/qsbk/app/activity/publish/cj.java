package qsbk.app.activity.publish;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

class cj extends AsyncTask<Object, Void, String> {
    final /* synthetic */ QiushiPublishTask a;

    cj(QiushiPublishTask qiushiPublishTask) {
        this.a = qiushiPublishTask;
    }

    protected /* synthetic */ Object a(Object[] objArr) {
        return c(objArr);
    }

    protected String c(Object... objArr) {
        String str = null;
        try {
            Map hashMap = new HashMap();
            hashMap.put("json", this.a.getPostParams());
            str = HttpClient.getIntentce().submitForm(Constants.ARTICLE_CREATE, hashMap);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
        return str;
    }

    protected void a(String str) {
        super.a(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                this.a.a(i + "", jSONObject);
                return;
            }
            String optString = jSONObject.optString("err_msg");
            if (!TextUtils.isEmpty(optString)) {
                this.a.a(-1, optString);
                return;
            }
            this.a.a(-1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
