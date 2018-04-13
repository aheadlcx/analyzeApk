package qsbk.app.model;

import android.text.TextUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Medal extends QbBean {
    public int current;
    public String describe;
    public String icon;
    public String name;
    public int total;

    public Medal(String str, String str2, String str3, int i, int i2) {
        this.name = str;
        this.icon = str2;
        this.describe = str3;
        this.current = i;
        this.total = i2;
    }

    public String getMedalTitle() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Medal)) {
            return false;
        }
        return TextUtils.equals(this.name, ((Medal) obj).name);
    }

    public String getProgressText() {
        return this.current + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.total;
    }
}
