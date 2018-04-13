package qsbk.app.activity;

import android.util.Log;
import com.qq.e.comm.constants.Constants.KEYS;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class cq implements Task {
    final /* synthetic */ Article a;
    final /* synthetic */ int b;
    final /* synthetic */ AuditNativeActivity2 c;

    cq(AuditNativeActivity2 auditNativeActivity2, Article article, int i) {
        this.c = auditNativeActivity2;
        this.a = article;
        this.b = i;
    }

    public void success(Object obj) {
        if (obj != null && (obj instanceof String)) {
            try {
                JSONObject jSONObject = new JSONObject((String) obj);
                if (this.c.a(jSONObject)) {
                    this.c.runOnUiThread(new cr(this, jSONObject));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public Object proccess() throws QiushibaikeException {
        Map hashMap = new HashMap();
        hashMap.put("id", this.a.id);
        hashMap.put(KEYS.RET, this.b + "");
        if (AuditNativeActivity2.b) {
            Log.e(AuditNativeActivity2.a, String.format("Post url %1s, post action %2s , article id %3s", new Object[]{AuditNativeActivity2.d, Integer.valueOf(this.b), hashMap.get("id").toString()}));
        }
        return HttpClient.getIntentce().post(AuditNativeActivity2.d, hashMap);
    }

    public void fail(Throwable th) {
        if (AuditNativeActivity2.b) {
            Log.e(AuditNativeActivity2.a, String.format("Exception happen when posting action %1s", new Object[]{th.toString()}));
        }
    }
}
