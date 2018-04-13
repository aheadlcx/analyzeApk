package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class cp implements Task {
    final /* synthetic */ boolean a;
    final /* synthetic */ AuditNativeActivity2 b;

    cp(AuditNativeActivity2 auditNativeActivity2, boolean z) {
        this.b = auditNativeActivity2;
        this.a = z;
    }

    public void success(Object obj) {
        if (obj != null && !TextUtils.isEmpty((String) obj)) {
            try {
                JSONObject jSONObject = new JSONObject((String) obj);
                String string = jSONObject.getString(NotificationCompat.CATEGORY_ERROR);
                if (!this.b.a(jSONObject)) {
                    return;
                }
                if ("0".equalsIgnoreCase(string)) {
                    if (!jSONObject.isNull("articles")) {
                        JSONArray jSONArray = new JSONArray(jSONObject.getString("articles"));
                        int length = jSONArray.length();
                        if (length > 0) {
                            for (int i = 0; i < length; i++) {
                                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                                if (AuditNativeActivity2.b) {
                                    Log.e(AuditNativeActivity2.a, String.format("the %1s article is %2s", new Object[]{Integer.valueOf(i), optJSONObject.toString()}));
                                }
                                Article article = new Article(optJSONObject);
                                if (this.a) {
                                    this.b.O.add(article);
                                } else {
                                    this.b.K.add(article);
                                }
                            }
                            if (!jSONObject.isNull("sid")) {
                                this.b.I = jSONObject.getString("sid");
                            }
                            if (!this.a) {
                                this.b.n();
                            }
                        } else if (!this.a) {
                            this.b.b("server return empty article");
                        }
                    } else if (!this.a) {
                        this.b.b("server return empty article");
                    }
                } else if (!TextUtils.isEmpty(string)) {
                    this.b.a(string, jSONObject.optString("err_msg"));
                } else if (!this.a) {
                    this.b.b("unknow error, perhaps param auth failed");
                }
            } catch (JSONException e) {
                if (!this.a) {
                    this.b.b(e.toString());
                }
            } catch (QiushibaikeException e2) {
                if (!this.a) {
                    this.b.b(e2.toString());
                }
            }
        } else if (!this.a) {
            this.b.b("network no response");
        }
    }

    public Object proccess() throws QiushibaikeException {
        if (!this.a) {
            this.b.m();
        }
        String str = null;
        if (TextUtils.isEmpty(this.b.I)) {
            str = Constants.AUDIT.substring(0, Constants.AUDIT.indexOf("?"));
        } else if (TextUtils.isDigitsOnly(this.b.I)) {
            str = String.format(Constants.AUDIT, new Object[]{Integer.valueOf(Integer.parseInt(this.b.I))});
        }
        str = HttpClient.getIntentce().get(str);
        if (AuditNativeActivity2.b) {
            Log.e(AuditNativeActivity2.a, "get articles " + str);
        }
        return str;
    }

    public void fail(Throwable th) {
        if (!this.a) {
            this.b.b(th.toString());
        }
    }
}
