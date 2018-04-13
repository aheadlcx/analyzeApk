package qsbk.app.push;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

final class a extends SimpleTask {
    final /* synthetic */ String a;
    final /* synthetic */ String b;

    a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public Object proccess() throws QiushibaikeException {
        int i = -1;
        if (HttpUtils.netIsAvailable()) {
            Map hashMap = new HashMap();
            hashMap.put("action", "bind");
            hashMap.put("token", this.a);
            hashMap.put("sdk", this.b);
            try {
                i = new JSONObject(HttpClient.getIntentce().post(Constants.PUSH_DOMAINS_TEST, hashMap)).getInt(NotificationCompat.CATEGORY_ERROR);
            } catch (JSONException e) {
            }
        }
        if (i != 0) {
            new Timer().schedule(new b(this, this), 3000);
        }
        return Integer.valueOf(i);
    }

    public void success(Object obj) {
    }
}
