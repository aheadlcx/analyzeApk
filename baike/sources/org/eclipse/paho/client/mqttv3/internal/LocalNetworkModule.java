package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;

public class LocalNetworkModule implements NetworkModule {
    static Class a;
    private Class b;
    private String c;
    private Object d;

    public LocalNetworkModule(String str) {
        this.c = str;
    }

    public void start() throws IOException, MqttException {
        if (ExceptionHelper.isClassAvailable("com.ibm.mqttdirect.modules.local.bindings.localListener")) {
            try {
                this.b = Class.forName("com.ibm.mqttdirect.modules.local.bindings.localListener");
                Class cls = this.b;
                String str = "connect";
                Class[] clsArr = new Class[1];
                Class cls2 = a;
                if (cls2 == null) {
                    cls2 = Class.forName("java.lang.String");
                    a = cls2;
                }
                clsArr[0] = cls2;
                this.d = cls.getMethod(str, clsArr).invoke(null, new Object[]{this.c});
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            } catch (Exception e2) {
            }
            if (this.d == null) {
                throw ExceptionHelper.createMqttException(32103);
            }
            return;
        }
        throw ExceptionHelper.createMqttException(32103);
    }

    public InputStream getInputStream() throws IOException {
        try {
            return (InputStream) this.b.getMethod("getClientInputStream", new Class[0]).invoke(this.d, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public OutputStream getOutputStream() throws IOException {
        try {
            return (OutputStream) this.b.getMethod("getClientOutputStream", new Class[0]).invoke(this.d, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public void stop() throws IOException {
        if (this.d != null) {
            try {
                this.b.getMethod("close", new Class[0]).invoke(this.d, new Object[0]);
            } catch (Exception e) {
            }
        }
    }

    public String getServerURI() {
        return new StringBuffer("local://").append(this.c).toString();
    }
}
