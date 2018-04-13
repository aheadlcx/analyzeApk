package qsbk.app.service;

import android.text.TextUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.report.ReportUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

class b extends Thread {
    final /* synthetic */ ConfigService a;

    b(ConfigService configService, String str) {
        this.a = configService;
        super(str);
    }

    public void run() {
        try {
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(Constants.CONFIG));
            JSONObject jSONObject2 = jSONObject.getJSONObject("ol");
            SharePreferenceUtils.getSharePreferencesValue("version");
            SharePreferenceUtils.setSharePreferencesValue(ConfigService.b, String.valueOf(jSONObject2.getInt("duration")));
            String optString = jSONObject.optString("qiushi_pic_domain");
            if (TextUtils.isEmpty(optString)) {
                optString = jSONObject.optString("image-domain");
            }
            if (!TextUtils.isEmpty(optString)) {
                if (!optString.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                    optString = optString + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                }
                SharePreferenceUtils.setSharePreferencesValue("image-domain", optString);
                Constants.updateImageDomains();
            }
            if (jSONObject.isNull("image-reportable")) {
                SharePreferenceUtils.setSharePreferencesValue("image-reportable", "0");
            } else {
                optString = jSONObject.getString("image-reportable");
                SharePreferenceUtils.setSharePreferencesValue("image-reportable", optString);
                QsbkApp.reportable = optString.equals("1");
            }
            if (!jSONObject.isNull("report-article")) {
                optString = jSONObject.getString("report-article");
                SharePreferenceUtils.setSharePreferencesValue("report-article", optString);
                ReportUtils.reset(optString);
            }
            if (!jSONObject.isNull("auto_disconnect_time")) {
                QsbkApp.getInstance().setAutoDisconnectTime(jSONObject.getInt("auto_disconnect_time") * 1000);
            }
            if (!jSONObject.isNull("promote_inform")) {
                SharePreferenceUtils.setSharePreferencesValue("promote_inform", jSONObject.getJSONArray("promote_inform").toString());
            }
            SharePreferenceUtils.setSharePreferencesValue("config", jSONObject.toString());
            SharePreferenceUtils.setSharePreferencesValue(ConfigService.a, (System.currentTimeMillis() / 1000) + "");
        } catch (QiushibaikeException e) {
        } catch (JSONException e2) {
        } finally {
            this.a.d.obtainMessage().sendToTarget();
        }
    }
}
