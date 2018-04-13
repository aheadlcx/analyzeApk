package org.eclipse.paho.client.mqttv3;

import android.support.v4.internal.view.SupportMenu;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.util.Strings;

public class MqttTopic {
    public static final String MULTI_LEVEL_WILDCARD = "#";
    public static final String MULTI_LEVEL_WILDCARD_PATTERN = "/#";
    public static final String SINGLE_LEVEL_WILDCARD = "+";
    public static final String TOPIC_LEVEL_SEPARATOR = "/";
    public static final String TOPIC_WILDCARDS = "#+";
    private ClientComms a;
    private String b;

    public MqttTopic(String str, ClientComms clientComms) {
        this.a = clientComms;
        this.b = str;
    }

    public MqttDeliveryToken publish(byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(mqttMessage);
    }

    public MqttDeliveryToken publish(MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        MqttToken mqttDeliveryToken = new MqttDeliveryToken(this.a.getClient().getClientId());
        mqttDeliveryToken.a(mqttMessage);
        this.a.sendNoWait(a(mqttMessage), mqttDeliveryToken);
        mqttDeliveryToken.internalTok.waitUntilSent();
        return mqttDeliveryToken;
    }

    public String getName() {
        return this.b;
    }

    private MqttPublish a(MqttMessage mqttMessage) {
        return new MqttPublish(getName(), mqttMessage);
    }

    public String toString() {
        return getName();
    }

    public static void validate(String str, boolean z) throws IllegalStateException, IllegalArgumentException {
        try {
            int length = str.getBytes("UTF-8").length;
            if (length < 1 || length > SupportMenu.USER_MASK) {
                throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", new Object[]{new Integer(1), new Integer(SupportMenu.USER_MASK)}));
            } else if (z) {
                if (!Strings.equalsAny(str, new String[]{MULTI_LEVEL_WILDCARD, SINGLE_LEVEL_WILDCARD})) {
                    if (Strings.countMatches(str, MULTI_LEVEL_WILDCARD) > 1 || (str.contains(MULTI_LEVEL_WILDCARD) && !str.endsWith(MULTI_LEVEL_WILDCARD_PATTERN))) {
                        throw new IllegalArgumentException(new StringBuffer("Invalid usage of multi-level wildcard in topic string: ").append(str).toString());
                    }
                    a(str);
                }
            } else if (Strings.containsAny((CharSequence) str, TOPIC_WILDCARDS)) {
                throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)");
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private static void a(String str) {
        char charAt = SINGLE_LEVEL_WILDCARD.charAt(0);
        char charAt2 = TOPIC_LEVEL_SEPARATOR.charAt(0);
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        int i = 0;
        while (i < length) {
            char c = i + -1 >= 0 ? toCharArray[i - 1] : '\u0000';
            char c2;
            if (i + 1 < length) {
                c2 = toCharArray[i + 1];
            } else {
                c2 = '\u0000';
            }
            if (toCharArray[i] != charAt || ((c == charAt2 || c == '\u0000') && (r0 == charAt2 || r0 == '\u0000'))) {
                i++;
            } else {
                throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", new Object[]{str}));
            }
        }
    }

    public static boolean isMatched(String str, String str2) throws IllegalStateException, IllegalArgumentException {
        int length = str2.length();
        int length2 = str.length();
        validate(str, true);
        validate(str2, false);
        if (str.equals(str2)) {
            return true;
        }
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            if (i2 < length) {
                if ((str2.charAt(i2) == '/' && str.charAt(i) != '/') || (str.charAt(i) != '+' && str.charAt(i) != '#' && str.charAt(i) != str2.charAt(i2))) {
                    break;
                }
                if (str.charAt(i) == '+') {
                    int i3 = i2 + 1;
                    while (i3 < length && str2.charAt(i3) != '/') {
                        i3 = i2 + 1;
                        i2 = i3;
                        i3++;
                    }
                } else if (str.charAt(i) == '#') {
                    i2 = length - 1;
                }
                i2++;
                i++;
            } else {
                break;
            }
        }
        if (i2 == length && i == length2) {
            return true;
        }
        return false;
    }
}
