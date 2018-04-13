package qsbk.app.core.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class FormatUtils {
    public static String formatCoupon(long j) {
        if (j < 10000) {
            return String.valueOf(j);
        }
        DecimalFormat decimalFormat;
        double d = ((double) j) / 10000.0d;
        if (j >= 100000) {
            decimalFormat = new DecimalFormat(MqttTopic.MULTI_LEVEL_WILDCARD);
        } else {
            decimalFormat = new DecimalFormat("#.0");
        }
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(d) + "万";
    }

    public static String formatBalance(long j) {
        if (j < 1000000) {
            return String.valueOf(j);
        }
        double d = ((double) j) / 10000.0d;
        DecimalFormat decimalFormat = new DecimalFormat(MqttTopic.MULTI_LEVEL_WILDCARD);
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(d) + "万";
    }
}
