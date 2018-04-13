package qsbk.app.activity;

import android.util.Log;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class bs extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ AuditNativeActivity c;

    bs(AuditNativeActivity auditNativeActivity, String str, String str2) {
        this.c = auditNativeActivity;
        this.a = str;
        this.b = str2;
    }

    protected Pair<Integer, String> a(String... strArr) {
        Map hashMap = new HashMap();
        hashMap.put("id", this.a);
        hashMap.put("reason", this.b);
        try {
            String post = HttpClient.getIntentce().post(strArr[0], hashMap);
            if (AuditNativeActivity.e()) {
                Log.e(AuditNativeActivity.f(), "report article result->" + post);
            }
            return new Pair(Integer.valueOf(0), post);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return SimpleHttpTask.getLocalError();
        }
    }

    protected void a(Pair<Integer, String> pair) {
        if (((Integer) pair.first).intValue() != 9999) {
            try {
                if (!AuditNativeActivity.a(this.c, new JSONObject((String) pair.second))) {
                    return;
                }
                return;
            } catch (Throwable th) {
                return;
            }
        }
        ToastAndDialog.makeNeutralToast(this.c, (String) pair.second).show();
    }
}
