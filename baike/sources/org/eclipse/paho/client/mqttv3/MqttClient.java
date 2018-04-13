package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttClient implements IMqttClient {
    protected MqttAsyncClient a;
    protected long b;

    public MqttClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this.a = null;
        this.b = -1;
        this.a = new MqttAsyncClient(str, str2, mqttClientPersistence);
    }

    public void connect() throws MqttSecurityException, MqttException {
        connect(new MqttConnectOptions());
    }

    public void connect(MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException {
        this.a.connect(mqttConnectOptions, null, null).waitForCompletion(getTimeToWait());
    }

    public IMqttToken connectWithResult(MqttConnectOptions mqttConnectOptions) throws MqttSecurityException, MqttException {
        IMqttToken connect = this.a.connect(mqttConnectOptions, null, null);
        connect.waitForCompletion(getTimeToWait());
        return connect;
    }

    public void disconnect() throws MqttException {
        this.a.disconnect().waitForCompletion();
    }

    public void disconnect(long j) throws MqttException {
        this.a.disconnect(j, null, null).waitForCompletion();
    }

    public void disconnectForcibly() throws MqttException {
        this.a.disconnectForcibly();
    }

    public void disconnectForcibly(long j) throws MqttException {
        this.a.disconnectForcibly(j);
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.a.disconnectForcibly(j, j2);
    }

    public void subscribe(String str) throws MqttException {
        subscribe(new String[]{str}, new int[]{1});
    }

    public void subscribe(String[] strArr) throws MqttException {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr);
    }

    public void subscribe(String str, int i) throws MqttException {
        subscribe(new String[]{str}, new int[]{i});
    }

    public void subscribe(String[] strArr, int[] iArr) throws MqttException {
        IMqttToken subscribe = this.a.subscribe(strArr, iArr, null, null);
        subscribe.waitForCompletion(getTimeToWait());
        int[] grantedQos = subscribe.getGrantedQos();
        for (int i = 0; i < grantedQos.length; i++) {
            iArr[i] = grantedQos[i];
        }
        if (grantedQos.length == 1 && iArr[0] == 128) {
            throw new MqttException(128);
        }
    }

    public void subscribe(String str, IMqttMessageListener iMqttMessageListener) throws MqttException {
        subscribe(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    public void subscribe(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr, iMqttMessageListenerArr);
    }

    public void subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        subscribe(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    public void subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        subscribe(strArr, iArr);
        for (int i = 0; i < strArr.length; i++) {
            this.a.a.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
    }

    public void unsubscribe(String str) throws MqttException {
        unsubscribe(new String[]{str});
    }

    public void unsubscribe(String[] strArr) throws MqttException {
        this.a.unsubscribe(strArr, null, null).waitForCompletion(getTimeToWait());
    }

    public void publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        publish(str, mqttMessage);
    }

    public void publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        this.a.publish(str, mqttMessage, null, null).waitForCompletion(getTimeToWait());
    }

    public void setTimeToWait(long j) throws IllegalArgumentException {
        if (j < -1) {
            throw new IllegalArgumentException();
        }
        this.b = j;
    }

    public long getTimeToWait() {
        return this.b;
    }

    public void close() throws MqttException {
        this.a.close();
    }

    public String getClientId() {
        return this.a.getClientId();
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.a.getPendingDeliveryTokens();
    }

    public String getServerURI() {
        return this.a.getServerURI();
    }

    public String getCurrentServerURI() {
        return this.a.getCurrentServerURI();
    }

    public MqttTopic getTopic(String str) {
        return this.a.a(str);
    }

    public boolean isConnected() {
        return this.a.isConnected();
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.a.setCallback(mqttCallback);
    }

    public void setManualAcks(boolean z) {
        this.a.setManualAcks(z);
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.a.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return MqttAsyncClient.generateClientId();
    }

    public void reconnect() throws MqttException {
        this.a.reconnect();
    }

    public Debug getDebug() {
        return this.a.getDebug();
    }
}
