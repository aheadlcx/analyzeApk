package org.eclipse.paho.client.mqttv3;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class TimerPingSender implements MqttPingSender {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private ClientComms d;
    private Timer e;

    private class a extends TimerTask {
        final TimerPingSender a;

        private a(TimerPingSender timerPingSender) {
            this.a = timerPingSender;
        }

        a(TimerPingSender timerPingSender, a aVar) {
            this(timerPingSender);
        }

        public void run() {
            TimerPingSender.a().fine(TimerPingSender.b(), "PingTask.run", "660", new Object[]{new Long(System.currentTimeMillis())});
            TimerPingSender.a(this.a).checkForActivity();
        }
    }

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.TimerPingSender");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    static String b() {
        return b;
    }

    static Logger a() {
        return c;
    }

    static ClientComms a(TimerPingSender timerPingSender) {
        return timerPingSender.d;
    }

    public void init(ClientComms clientComms) {
        if (clientComms == null) {
            throw new IllegalArgumentException("ClientComms cannot be null.");
        }
        this.d = clientComms;
    }

    public void start() {
        c.fine(b, "start", "659", new Object[]{this.d.getClient().getClientId()});
        this.e = new Timer(new StringBuffer("MQTT Ping: ").append(r0).toString());
        this.e.schedule(new a(this, null), this.d.getKeepAlive());
    }

    public void stop() {
        c.fine(b, "stop", "661", null);
        if (this.e != null) {
            this.e.cancel();
        }
    }

    public void schedule(long j) {
        this.e.schedule(new a(this, null), j);
    }
}
