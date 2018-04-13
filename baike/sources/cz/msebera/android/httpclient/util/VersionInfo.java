package cz.msebera.android.httpclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class VersionInfo {
    public static final String PROPERTY_MODULE = "info.module";
    public static final String PROPERTY_RELEASE = "info.release";
    public static final String PROPERTY_TIMESTAMP = "info.timestamp";
    public static final String UNAVAILABLE = "UNAVAILABLE";
    public static final String VERSION_PROPERTY_FILE = "version.properties";
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    protected VersionInfo(String str, String str2, String str3, String str4, String str5) {
        Args.notNull(str, "Package identifier");
        this.a = str;
        if (str2 == null) {
            str2 = UNAVAILABLE;
        }
        this.b = str2;
        if (str3 == null) {
            str3 = UNAVAILABLE;
        }
        this.c = str3;
        if (str4 == null) {
            str4 = UNAVAILABLE;
        }
        this.d = str4;
        if (str5 == null) {
            str5 = UNAVAILABLE;
        }
        this.e = str5;
    }

    public final String getPackage() {
        return this.a;
    }

    public final String getModule() {
        return this.b;
    }

    public final String getRelease() {
        return this.c;
    }

    public final String getTimestamp() {
        return this.d;
    }

    public final String getClassloader() {
        return this.e;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(((((this.a.length() + 20) + this.b.length()) + this.c.length()) + this.d.length()) + this.e.length());
        stringBuilder.append("VersionInfo(").append(this.a).append(':').append(this.b);
        if (!UNAVAILABLE.equals(this.c)) {
            stringBuilder.append(':').append(this.c);
        }
        if (!UNAVAILABLE.equals(this.d)) {
            stringBuilder.append(':').append(this.d);
        }
        stringBuilder.append(')');
        if (!UNAVAILABLE.equals(this.e)) {
            stringBuilder.append('@').append(this.e);
        }
        return stringBuilder.toString();
    }

    public static VersionInfo[] loadVersionInfo(String[] strArr, ClassLoader classLoader) {
        Args.notNull(strArr, "Package identifier array");
        List arrayList = new ArrayList(strArr.length);
        for (String loadVersionInfo : strArr) {
            VersionInfo loadVersionInfo2 = loadVersionInfo(loadVersionInfo, classLoader);
            if (loadVersionInfo2 != null) {
                arrayList.add(loadVersionInfo2);
            }
        }
        return (VersionInfo[]) arrayList.toArray(new VersionInfo[arrayList.size()]);
    }

    public static VersionInfo loadVersionInfo(String str, ClassLoader classLoader) {
        Map properties;
        Args.notNull(str, "Package identifier");
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        InputStream resourceAsStream;
        try {
            resourceAsStream = classLoader.getResourceAsStream(str.replace('.', '/') + MqttTopic.TOPIC_LEVEL_SEPARATOR + VERSION_PROPERTY_FILE);
            if (resourceAsStream != null) {
                properties = new Properties();
                properties.load(resourceAsStream);
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                }
            } else {
                properties = null;
            }
        } catch (IOException e2) {
            properties = null;
        } catch (Throwable th) {
            resourceAsStream.close();
        }
        if (properties != null) {
            return a(str, properties, classLoader);
        }
        return null;
    }

    protected static VersionInfo a(String str, Map<?, ?> map, ClassLoader classLoader) {
        String str2;
        String str3;
        String str4;
        String str5 = null;
        Args.notNull(str, "Package identifier");
        if (map != null) {
            String str6;
            String str7 = (String) map.get(PROPERTY_MODULE);
            if (str7 == null || str7.length() >= 1) {
                str6 = str7;
            } else {
                str6 = null;
            }
            str7 = (String) map.get(PROPERTY_RELEASE);
            if (str7 == null || (str7.length() >= 1 && !str7.equals("${pom.version}"))) {
                str2 = str7;
            } else {
                str2 = null;
            }
            str7 = (String) map.get(PROPERTY_TIMESTAMP);
            if (str7 == null || (str7.length() >= 1 && !str7.equals("${mvn.timestamp}"))) {
                str3 = str7;
                str4 = str2;
                str2 = str6;
            } else {
                str3 = null;
                str4 = str2;
                str2 = str6;
            }
        } else {
            str3 = null;
            str4 = null;
            str2 = null;
        }
        if (classLoader != null) {
            str5 = classLoader.toString();
        }
        return new VersionInfo(str, str2, str4, str3, str5);
    }

    public static String getUserAgent(String str, String str2, Class<?> cls) {
        VersionInfo loadVersionInfo = loadVersionInfo(str2, cls.getClassLoader());
        String release = loadVersionInfo != null ? loadVersionInfo.getRelease() : UNAVAILABLE;
        String property = System.getProperty("java.version");
        return String.format("%s/%s (Java/%s)", new Object[]{str, release, property});
    }
}
