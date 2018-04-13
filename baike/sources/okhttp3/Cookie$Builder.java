package okhttp3;

import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class Cookie$Builder {
    String a;
    String b;
    long c = HttpDate.MAX_DATE;
    String d;
    String e = MqttTopic.TOPIC_LEVEL_SEPARATOR;
    boolean f;
    boolean g;
    boolean h;
    boolean i;

    public Cookie$Builder name(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.trim().equals(str)) {
            this.a = str;
            return this;
        } else {
            throw new IllegalArgumentException("name is not trimmed");
        }
    }

    public Cookie$Builder value(String str) {
        if (str == null) {
            throw new NullPointerException("value == null");
        } else if (str.trim().equals(str)) {
            this.b = str;
            return this;
        } else {
            throw new IllegalArgumentException("value is not trimmed");
        }
    }

    public Cookie$Builder expiresAt(long j) {
        long j2;
        long j3 = HttpDate.MAX_DATE;
        if (j <= 0) {
            j2 = Long.MIN_VALUE;
        } else {
            j2 = j;
        }
        if (j2 <= HttpDate.MAX_DATE) {
            j3 = j2;
        }
        this.c = j3;
        this.h = true;
        return this;
    }

    public Cookie$Builder domain(String str) {
        return a(str, false);
    }

    public Cookie$Builder hostOnlyDomain(String str) {
        return a(str, true);
    }

    private Cookie$Builder a(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("domain == null");
        }
        String canonicalizeHost = Util.canonicalizeHost(str);
        if (canonicalizeHost == null) {
            throw new IllegalArgumentException("unexpected domain: " + str);
        }
        this.d = canonicalizeHost;
        this.i = z;
        return this;
    }

    public Cookie$Builder path(String str) {
        if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            this.e = str;
            return this;
        }
        throw new IllegalArgumentException("path must start with '/'");
    }

    public Cookie$Builder secure() {
        this.f = true;
        return this;
    }

    public Cookie$Builder httpOnly() {
        this.g = true;
        return this;
    }

    public Cookie build() {
        return new Cookie(this);
    }
}
