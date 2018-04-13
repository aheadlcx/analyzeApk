package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttToken;

public class ConnectActionListener implements IMqttActionListener {
    private MqttClientPersistence a;
    private MqttAsyncClient b;
    private ClientComms c;
    private MqttConnectOptions d;
    private MqttToken e;
    private Object f;
    private IMqttActionListener g;
    private int h;
    private MqttCallbackExtended i;
    private boolean j;

    public ConnectActionListener(MqttAsyncClient mqttAsyncClient, MqttClientPersistence mqttClientPersistence, ClientComms clientComms, MqttConnectOptions mqttConnectOptions, MqttToken mqttToken, Object obj, IMqttActionListener iMqttActionListener, boolean z) {
        this.a = mqttClientPersistence;
        this.b = mqttAsyncClient;
        this.c = clientComms;
        this.d = mqttConnectOptions;
        this.e = mqttToken;
        this.f = obj;
        this.g = iMqttActionListener;
        this.h = mqttConnectOptions.getMqttVersion();
        this.j = z;
    }

    public void onSuccess(IMqttToken iMqttToken) {
        if (this.h == 0) {
            this.d.setMqttVersion(0);
        }
        this.e.internalTok.a(iMqttToken.getResponse(), null);
        this.e.internalTok.c();
        this.e.internalTok.a(this.b);
        if (this.j) {
            this.c.notifyReconnect();
        }
        if (this.g != null) {
            this.e.setUserContext(this.f);
            this.g.onSuccess(this.e);
        }
        if (this.i != null) {
            this.i.connectComplete(this.j, this.c.getNetworkModules()[this.c.getNetworkModuleIndex()].getServerURI());
        }
    }

    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        int length = this.c.getNetworkModules().length;
        int networkModuleIndex = this.c.getNetworkModuleIndex();
        if (networkModuleIndex + 1 < length || (this.h == 0 && this.d.getMqttVersion() == 4)) {
            if (this.h != 0) {
                this.c.setNetworkModuleIndex(networkModuleIndex + 1);
            } else if (this.d.getMqttVersion() == 4) {
                this.d.setMqttVersion(3);
            } else {
                this.d.setMqttVersion(4);
                this.c.setNetworkModuleIndex(networkModuleIndex + 1);
            }
            try {
                connect();
                return;
            } catch (Throwable e) {
                onFailure(iMqttToken, e);
                return;
            }
        }
        MqttException mqttException;
        if (this.h == 0) {
            this.d.setMqttVersion(0);
        }
        if (th instanceof MqttException) {
            mqttException = (MqttException) th;
        } else {
            mqttException = new MqttException(th);
        }
        this.e.internalTok.a(null, mqttException);
        this.e.internalTok.c();
        this.e.internalTok.a(this.b);
        if (this.g != null) {
            this.e.setUserContext(this.f);
            this.g.onFailure(this.e, th);
        }
    }

    public void connect() throws MqttPersistenceException {
        IMqttToken mqttToken = new MqttToken(this.b.getClientId());
        mqttToken.setActionCallback(this);
        mqttToken.setUserContext(this);
        this.a.open(this.b.getClientId(), this.b.getServerURI());
        if (this.d.isCleanSession()) {
            this.a.clear();
        }
        if (this.d.getMqttVersion() == 0) {
            this.d.setMqttVersion(4);
        }
        try {
            this.c.connect(this.d, mqttToken);
        } catch (Throwable e) {
            onFailure(mqttToken, e);
        }
    }

    public void setMqttCallbackExtended(MqttCallbackExtended mqttCallbackExtended) {
        this.i = mqttCallbackExtended;
    }
}
