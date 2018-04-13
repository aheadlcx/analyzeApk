package qsbk.app.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.HttpClient;

class lf extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ String a;
    final /* synthetic */ FillUserDataActivity b;

    lf(FillUserDataActivity fillUserDataActivity, String str) {
        this.b = fillUserDataActivity;
        this.a = str;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            String str = HttpClient.getIntentce().get(this.a);
            if (str != null) {
                String optString;
                JSONObject jSONObject = new JSONObject(str);
                if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(this.b.j)) {
                    optString = jSONObject.optString("nickname");
                    str = optString;
                    optString = jSONObject.optString("figureurl_qq_2");
                } else if (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equalsIgnoreCase(this.b.j)) {
                    optString = jSONObject.optString("name");
                    str = optString;
                    optString = jSONObject.optString("avatar_large");
                } else {
                    optString = jSONObject.optString("nickname");
                    str = optString;
                    optString = jSONObject.optString("headimgurl");
                }
                if (str.length() >= 8) {
                    str = str.substring(0, 8);
                }
                JSONObject jSONObject2 = new JSONObject(HttpClient.getIntentce().get(Constants.FILLINFO + "?login=" + URLEncoder.encode(str, "UTF-8")));
                Message obtainMessage;
                Bundle bundle;
                if (jSONObject2.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                    obtainMessage = this.b.m.obtainMessage();
                    bundle = new Bundle();
                    bundle.putString("name", str);
                    bundle.putString("avatar", optString);
                    obtainMessage.what = 0;
                    obtainMessage.setData(bundle);
                    obtainMessage.sendToTarget();
                } else if (106 == jSONObject2.getInt(NotificationCompat.CATEGORY_ERROR)) {
                    obtainMessage = this.b.m.obtainMessage();
                    bundle = new Bundle();
                    bundle.putString("name", str + new Random().nextInt(10));
                    bundle.putString("avatar", optString);
                    obtainMessage.what = 0;
                    obtainMessage.setData(bundle);
                    obtainMessage.sendToTarget();
                } else {
                    obtainMessage = this.b.m.obtainMessage();
                    bundle = new Bundle();
                    bundle.putString("name", str);
                    bundle.putString("avatar", optString);
                    obtainMessage.what = 0;
                    obtainMessage.setData(bundle);
                    obtainMessage.sendToTarget();
                }
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    protected void a(Pair<Integer, String> pair) {
        super.a(pair);
    }
}
