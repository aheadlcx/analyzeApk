package qsbk.app.core.model;

import android.support.v4.util.Pair;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.io.Serializable;
import java.util.Map;

public class CustomButton implements Serializable {
    public static final String EVENT_TYPE_CHARGE = "charge";
    public static final String EVENT_TYPE_GIFT = "gift";
    public static final String EVENT_TYPE_INTERFACE = "interface";
    public static final String EVENT_TYPE_LOVE = "love";
    public static final String EVENT_TYPE_USER = "user";
    public static final String EVENT_TYPE_WEB = "web";
    public static final String POSITION_BOTTOM = "b";
    public static final String POSITION_RIGHT = "r";
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "post";
    public String button_url;
    public EventParam event_param;
    public String event_type;
    public String position;
    public String ratio;

    public class EventParam implements Serializable {
        final /* synthetic */ CustomButton a;
        public long gift_id;
        public String method;
        public Map<String, String> param;
        public long platform_id;
        public long source;
        public long source_id;
        public String url;

        public EventParam(CustomButton customButton) {
            this.a = customButton;
        }
    }

    public Pair<Integer, Integer> getRatio(int i) {
        int i2;
        NumberFormatException e;
        try {
            if (TextUtils.isEmpty(this.ratio) || !this.ratio.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
                i2 = i;
                return new Pair(Integer.valueOf(i2), Integer.valueOf(i));
            }
            String[] split = this.ratio.split(Config.TRACE_TODAY_VISIT_SPLIT);
            i2 = (int) (Float.parseFloat(split[0]) * ((float) i));
            try {
                i = (int) (Float.parseFloat(split[1]) * ((float) i));
            } catch (NumberFormatException e2) {
                e = e2;
                e.printStackTrace();
                return new Pair(Integer.valueOf(i2), Integer.valueOf(i));
            }
            return new Pair(Integer.valueOf(i2), Integer.valueOf(i));
        } catch (NumberFormatException e3) {
            e = e3;
            i2 = i;
            e.printStackTrace();
            return new Pair(Integer.valueOf(i2), Integer.valueOf(i));
        }
    }
}
