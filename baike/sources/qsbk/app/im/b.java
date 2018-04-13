package qsbk.app.im;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.image.issue.Logger;

class b implements IMqttActionListener {
    final /* synthetic */ ChatClientManager a;

    b(ChatClientManager chatClientManager) {
        this.a = chatClientManager;
    }

    public void onSuccess(IMqttToken iMqttToken) {
        LogUtil.d("asyncActionToken:" + iMqttToken.getMessageId());
        LogUtil.d("conListener:" + this.a.d.toString());
        LogUtil.d("connect success");
        ChatClientManager.a(this.a, true);
        try {
            ChatClientManager.b(this.a, 2);
            ChatClientManager.c(this.a, 0);
        } catch (Throwable th) {
            Logger.getInstance().debug(ChatClientManager.a(), "onSuccess", String.format("连接成功后，客户端报错误：%s", new Object[]{th}));
        }
        this.a.a.removeCallbacks(ChatClientManager.b(this.a));
        Logger.getInstance().debug(ChatClientManager.a(), "onSuccess", String.format("异步连接成功[connectActionId:%s, messageId:%ss", new Object[]{Integer.valueOf(ChatClientManager.c(this.a)), Integer.valueOf(iMqttToken.getMessageId())}));
    }

    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        LogUtil.d("connect failure:" + iMqttToken.getMessageId());
        String str = "";
        for (Object obj : th.getStackTrace()) {
            str = str + obj + "\n";
        }
        LogUtil.d("connect failure:" + str);
        Logger.getInstance().debug(ChatClientManager.a(), "onFailure", "stackTrace:\n" + str);
        th.printStackTrace();
        ChatClientManager.a(this.a, false);
        ChatClientManager.d(this.a);
        if (th instanceof MqttException) {
            MqttException mqttException = (MqttException) th;
            if (mqttException.getReasonCode() == 32100) {
                ChatClientManager.a(this.a, true);
                ChatClientManager.b(this.a, 2);
                return;
            } else if (mqttException.getReasonCode() == 4) {
                ChatClientManager.b(this.a, 5);
                return;
            }
        }
        this.a.connectLater();
        ChatClientManager.b(this.a, 3);
        Logger.getInstance().debug(ChatClientManager.a(), "onFailure", String.format("异步连接失败[connectActionId:%s, messageId:%s, exception:%s]", new Object[]{Integer.valueOf(ChatClientManager.c(this.a)), Integer.valueOf(iMqttToken.getMessageId()), th}));
    }
}
