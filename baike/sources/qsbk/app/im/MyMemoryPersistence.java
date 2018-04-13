package qsbk.app.im;

import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import qsbk.app.utils.LogUtil;

public class MyMemoryPersistence extends MemoryPersistence {
    private MsgDeliveryObserver a = null;

    public MyMemoryPersistence(MsgDeliveryObserver msgDeliveryObserver) {
        this.a = msgDeliveryObserver;
    }

    public void remove(String str) throws MqttPersistenceException {
        super.remove(str);
        if (str.startsWith("s-")) {
            try {
                a(Integer.parseInt(str.substring(2)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(int i) {
        LogUtil.d("on msg complete:" + i);
        if (this.a != null) {
            this.a.onMessageSended(i);
        }
    }
}
