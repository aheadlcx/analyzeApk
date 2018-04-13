package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.Serializable;
import java.util.Comparator;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@Immutable
public class CookiePathComparator implements Serializable, Comparator<Cookie> {
    public static final CookiePathComparator INSTANCE = new CookiePathComparator();

    private String a(Cookie cookie) {
        String path = cookie.getPath();
        if (path == null) {
            path = MqttTopic.TOPIC_LEVEL_SEPARATOR;
        }
        if (path.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            return path;
        }
        return path + '/';
    }

    public int compare(Cookie cookie, Cookie cookie2) {
        String a = a(cookie);
        String a2 = a(cookie2);
        if (a.equals(a2)) {
            return 0;
        }
        if (a.startsWith(a2)) {
            return -1;
        }
        if (a2.startsWith(a)) {
            return 1;
        }
        return 0;
    }
}
