package android.support.v4.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class MimeTypeFilter {
    private MimeTypeFilter() {
    }

    private static boolean a(@NonNull String[] strArr, @NonNull String[] strArr2) {
        if (strArr2.length != 2) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
        } else if (strArr2[0].isEmpty() || strArr2[1].isEmpty()) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
        } else if (strArr.length != 2) {
            return false;
        } else {
            if (!"*".equals(strArr2[0]) && !strArr2[0].equals(strArr[0])) {
                return false;
            }
            if ("*".equals(strArr2[1]) || strArr2[1].equals(strArr[1])) {
                return true;
            }
            return false;
        }
    }

    public static boolean matches(@Nullable String str, @NonNull String str2) {
        if (str == null) {
            return false;
        }
        return a(str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR), str2.split(MqttTopic.TOPIC_LEVEL_SEPARATOR));
    }

    @Nullable
    public static String matches(@Nullable String str, @NonNull String[] strArr) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        for (String str2 : strArr) {
            if (a(split, str2.split(MqttTopic.TOPIC_LEVEL_SEPARATOR))) {
                return str2;
            }
        }
        return null;
    }

    @Nullable
    public static String matches(@Nullable String[] strArr, @NonNull String str) {
        if (strArr == null) {
            return null;
        }
        String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        for (String str2 : strArr) {
            if (a(str2.split(MqttTopic.TOPIC_LEVEL_SEPARATOR), split)) {
                return str2;
            }
        }
        return null;
    }

    @NonNull
    public static String[] matchesMany(@Nullable String[] strArr, @NonNull String str) {
        int i = 0;
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        int length = strArr.length;
        while (i < length) {
            String str2 = strArr[i];
            if (a(str2.split(MqttTopic.TOPIC_LEVEL_SEPARATOR), split)) {
                arrayList.add(str2);
            }
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
