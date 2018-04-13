package qsbk.app.model;

import com.alipay.sdk.util.h;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Vote {
    public String action;
    public String session;
    public String state;
    public String target;
    public String type;

    public Vote(String str, String str2, String str3, String str4) {
        this.session = str + MqttTopic.TOPIC_LEVEL_SEPARATOR + (System.currentTimeMillis() / 1000);
        this.type = str2;
        this.target = str3;
        this.action = str4;
    }

    public Vote(String str, String str2, String str3, String str4, String str5) {
        this.session = str + MqttTopic.TOPIC_LEVEL_SEPARATOR + (System.currentTimeMillis() / 1000);
        this.type = str2;
        this.target = str3;
        this.action = str4;
        this.state = str5;
    }

    public static Vote createFromDB(String str, String str2, String str3, String str4) {
        Vote vote = new Vote(str, str2, str3, str4);
        vote.session = str;
        return vote;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"session\":\"" + this.session + "\"");
        stringBuffer.append(",\"type\":\"" + this.type + "\"");
        stringBuffer.append(",\"target\":\"" + this.target + "\"");
        stringBuffer.append(",\"action\":" + Integer.valueOf(this.action));
        stringBuffer.append(h.d);
        return stringBuffer.toString();
    }
}
