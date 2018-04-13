package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class f {
    public static String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str2.contains(SpeechConstant.NET_TIMEOUT)) {
            stringBuilder.append("server:" + str + "   timeout");
        } else if (str2.contains("unknown")) {
            stringBuilder.append("ping: cannot resolve " + str + ": Unknown host");
        } else {
            a(str2, stringBuilder);
        }
        return stringBuilder.toString();
    }

    public static void a(String str, StringBuilder stringBuilder) {
        String c = c(str);
        List b = b(str);
        List a = a(str);
        int size = a.size();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append("server:" + c + "   ttl=" + ((String) b.get(i)) + "   time=" + ((String) a.get(i)) + "ms\n");
        }
        if (b.size() != 0) {
            stringBuilder.append("server:" + c + "   ttl=" + ((String) b.get(size - 1)) + "   time=" + ((String) a.get(size - 1)) + Parameters.MESSAGE_SEQ);
        }
    }

    private static List<String> a(String str) {
        List<String> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("(?<==)([\\.0-9\\s]+)(?=ms)").matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group().toString().trim());
        }
        return arrayList;
    }

    private static List<String> b(String str) {
        List<String> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("(?<=ttl=)([0-9]+)(?=\\s)").matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group().toString().trim());
        }
        return arrayList;
    }

    private static String c(String str) {
        String str2 = null;
        Matcher matcher = Pattern.compile("(?<=\\()([\\d]+\\.)+[\\d]+(?=\\))").matcher(str);
        while (matcher.find()) {
            str2 = matcher.group().toString().trim();
        }
        return str2;
    }
}
