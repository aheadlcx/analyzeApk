package com.umeng.commonsdk;

import android.content.Context;
import android.util.Base64;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.a;
import com.umeng.commonsdk.stateless.f;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

final class b implements Runnable {
    final /* synthetic */ Context a;

    b(Context context) {
        this.a = context;
    }

    public void run() {
        try {
            JSONObject b = e.b(this.a);
            if (b != null && b.length() > 0) {
                f.a(this.a, this.a.getFilesDir() + MqttTopic.TOPIC_LEVEL_SEPARATOR + a.e + MqttTopic.TOPIC_LEVEL_SEPARATOR + Base64.encodeToString(com.umeng.commonsdk.internal.a.n.getBytes(), 0), 10);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lbs", b);
                b = new JSONObject();
                b.put(IXAdRequestInfo.PHONE_TYPE, jSONObject);
                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                uMSLEnvelopeBuild.buildSLEnvelope(this.a, uMSLEnvelopeBuild.buildSLBaseHeader(this.a), b, com.umeng.commonsdk.internal.a.n);
            }
        } catch (Throwable e) {
            com.umeng.commonsdk.proguard.b.a(this.a, e);
        }
    }
}
