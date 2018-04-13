package qsbk.app;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;

class k implements Runnable {
    final /* synthetic */ QsbkApp a;

    k(QsbkApp qsbkApp) {
        this.a = qsbkApp;
    }

    public void run() {
        try {
            TimeDelta timeDelta = new TimeDelta();
            MqttAsyncClient mqttAsyncClient = new MqttAsyncClient("tcp://127.0.0.1", "test", null);
            LogUtil.d("init mqtt client delta:" + timeDelta.getDelta());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
