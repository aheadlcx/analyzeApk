package qsbk.app.activity;

import android.util.Log;
import com.qq.e.comm.constants.Constants.KEYS;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.AuditArticle;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class br implements Task {
    final /* synthetic */ AuditArticle a;
    final /* synthetic */ int b;
    final /* synthetic */ AuditNativeActivity c;

    br(AuditNativeActivity auditNativeActivity, AuditArticle auditArticle, int i) {
        this.c = auditNativeActivity;
        this.a = auditArticle;
        this.b = i;
    }

    public void success(Object obj) {
        if (obj != null && (obj instanceof String)) {
            try {
                AuditNativeActivity.a(this.c, new JSONObject((String) obj));
            } catch (Throwable th) {
            }
        }
    }

    public Object proccess() throws QiushibaikeException {
        Map hashMap = new HashMap();
        hashMap.put("id", this.a.id);
        hashMap.put(KEYS.RET, this.b + "");
        if (AuditNativeActivity.e()) {
            Log.e(AuditNativeActivity.f(), String.format("Post url %1s, post action %2s , article id %3s", new Object[]{AuditNativeActivity.g(), Integer.valueOf(this.b), hashMap.get("id").toString()}));
        }
        return HttpClient.getIntentce().post(AuditNativeActivity.g(), hashMap);
    }

    public void fail(Throwable th) {
        if (AuditNativeActivity.e()) {
            Log.e(AuditNativeActivity.f(), String.format("Exception happen when posting action %1s", new Object[]{th.toString()}));
        }
    }
}
