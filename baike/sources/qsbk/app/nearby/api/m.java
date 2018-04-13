package qsbk.app.nearby.api;

import com.baidu.mobstat.Config;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Article;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class m implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    m(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        int i = 3;
        int i2 = 0;
        try {
            int optInt;
            int optInt2;
            int optInt3;
            int optInt4;
            int i3;
            ArrayList arrayList;
            JSONArray jSONArray;
            if (jSONObject.has("pronum")) {
                optInt = jSONObject.optInt("pronum");
            } else {
                optInt = 0;
            }
            if (jSONObject.has("smile")) {
                optInt2 = jSONObject.optInt("smile");
            } else {
                optInt2 = 0;
            }
            if (jSONObject.has("hot_comment_count")) {
                optInt3 = jSONObject.optInt("hot_comment_count");
            } else {
                optInt3 = 0;
            }
            if (jSONObject.has(Config.SIGN)) {
                JSONObject optJSONObject = jSONObject.optJSONObject(Config.SIGN);
                if (optJSONObject != null && optJSONObject.has("days")) {
                    optInt4 = optJSONObject.optInt("days");
                    i3 = jSONObject.getInt("total");
                    arrayList = new ArrayList();
                    jSONArray = jSONObject.getJSONArray("items");
                    if (jSONArray.length() < 3) {
                        if (jSONArray.length() > 0 || jSONArray.length() >= 3) {
                            i = 0;
                        } else {
                            i = jSONArray.length();
                        }
                    }
                    while (i2 < i) {
                        arrayList.add(new Article(jSONArray.getJSONObject(i2)));
                        i2++;
                    }
                    this.a.e.getPersonalQiushiSucc(arrayList, i3, optInt, optInt2, optInt3, optInt4);
                }
            }
            optInt4 = 0;
            i3 = jSONObject.getInt("total");
            arrayList = new ArrayList();
            jSONArray = jSONObject.getJSONArray("items");
            if (jSONArray.length() < 3) {
                if (jSONArray.length() > 0) {
                }
                i = 0;
            }
            while (i2 < i) {
                arrayList.add(new Article(jSONArray.getJSONObject(i2)));
                i2++;
            }
            this.a.e.getPersonalQiushiSucc(arrayList, i3, optInt, optInt2, optInt3, optInt4);
        } catch (JSONException e) {
            e.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, "数据解析出错", Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.e.getPersonalQiushiFailed();
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.e.getPersonalQiushiFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.e.getPersonalQiushiFailed();
        if (!this.a.l) {
            this.a.l = true;
            if (i != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue()) {
                ToastAndDialog.makeNegativeToast(this.a.k, str, Integer.valueOf(1)).show();
            } else if (QsbkApp.currentUser != null) {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            }
        }
    }
}
