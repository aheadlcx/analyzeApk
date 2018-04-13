package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.AuditArticle;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class bq implements Task {
    final /* synthetic */ boolean a;
    final /* synthetic */ AuditNativeActivity b;

    bq(AuditNativeActivity auditNativeActivity, boolean z) {
        this.b = auditNativeActivity;
        this.a = z;
    }

    public void success(Object obj) {
        if (obj != null && !TextUtils.isEmpty((String) obj)) {
            try {
                JSONObject jSONObject = new JSONObject((String) obj);
                Object string = jSONObject.getString(NotificationCompat.CATEGORY_ERROR);
                if (!AuditNativeActivity.a(this.b, jSONObject)) {
                    return;
                }
                if ("0".equalsIgnoreCase(string)) {
                    if (!jSONObject.isNull("articles")) {
                        JSONArray jSONArray = new JSONArray(jSONObject.getString("articles"));
                        int length = jSONArray.length();
                        if (length > 0) {
                            for (int i = 0; i < length; i++) {
                                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                                if (AuditNativeActivity.e()) {
                                    Log.e(AuditNativeActivity.f(), String.format("the %1s article is %2s", new Object[]{Integer.valueOf(i), optJSONObject.toString()}));
                                }
                                AuditArticle auditArticle = new AuditArticle(optJSONObject);
                                if (this.a) {
                                    AuditNativeActivity.t(this.b).add(auditArticle);
                                } else {
                                    AuditNativeActivity.i(this.b).add(auditArticle);
                                }
                            }
                            if (!jSONObject.isNull("sid")) {
                                AuditNativeActivity.b(this.b, jSONObject.getString("sid"));
                            }
                            if (!this.a) {
                                AuditNativeActivity.u(this.b);
                            }
                        } else if (!this.a) {
                            AuditNativeActivity.c(this.b, "server return empty article");
                        }
                    } else if (!this.a) {
                        AuditNativeActivity.c(this.b, "server return empty article");
                    }
                } else if (!TextUtils.isEmpty(string)) {
                    AuditNativeActivity.a(this.b, string, jSONObject.optString("err_msg"));
                } else if (!this.a) {
                    AuditNativeActivity.c(this.b, "unknow error, perhaps param auth failed");
                }
            } catch (JSONException e) {
                if (!this.a) {
                    AuditNativeActivity.c(this.b, e.toString());
                }
            } catch (QiushibaikeException e2) {
                if (!this.a) {
                    AuditNativeActivity.c(this.b, e2.toString());
                }
            }
        } else if (!this.a) {
            AuditNativeActivity.c(this.b, "network no response");
        }
    }

    public Object proccess() throws QiushibaikeException {
        if (!this.a) {
            AuditNativeActivity.v(this.b);
        }
        String str = null;
        if (TextUtils.isEmpty(AuditNativeActivity.w(this.b))) {
            str = Constants.AUDIT.substring(0, Constants.AUDIT.indexOf("?"));
        } else if (TextUtils.isDigitsOnly(AuditNativeActivity.w(this.b))) {
            str = String.format(Constants.AUDIT, new Object[]{Integer.valueOf(Integer.parseInt(AuditNativeActivity.w(this.b)))});
        }
        str = HttpClient.getIntentce().get(str);
        if (AuditNativeActivity.e()) {
            Log.e(AuditNativeActivity.f(), "get articles " + str);
        }
        return str;
    }

    public void fail(Throwable th) {
        if (!this.a) {
            AuditNativeActivity.c(this.b, th.toString());
        }
    }
}
