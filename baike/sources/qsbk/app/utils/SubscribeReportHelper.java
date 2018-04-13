package qsbk.app.utils;

import android.content.Context;
import android.util.Pair;
import com.qiushibaike.statsdk.StatSDK;
import java.util.HashMap;
import java.util.Map;

public class SubscribeReportHelper {
    public static final int LOGIN_CARD_KEY = -1;
    public static final String TYPE_GROUP = "group";
    public static final String TYPE_LIVE = "live";
    public static final String TYPE_LOGIN_CARD = "login_card";
    public static final String TYPE_TOPIC = "topic";
    private static Map<Integer, Pair<String, Integer>> a = new HashMap();

    private SubscribeReportHelper() {
    }

    public static void clear() {
        a.clear();
    }

    public static void addRecord(int i, String str, int i2) {
        a.put(Integer.valueOf(i), new Pair(str, Integer.valueOf(i2)));
    }

    public static void report(Context context, int i) {
        if (context != null && a.containsKey(Integer.valueOf(i))) {
            Pair pair = (Pair) a.get(Integer.valueOf(i));
            String str = (String) pair.first;
            String num = ((Integer) pair.second).toString();
            StatSDK.onEvent(context, "recommend_click", str, num, null, null);
        }
    }
}
