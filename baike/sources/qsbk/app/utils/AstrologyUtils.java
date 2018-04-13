package qsbk.app.utils;

import java.util.Calendar;

public class AstrologyUtils {
    public static final String[] ASTROLOGY = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    public static final int[] ASTROLOGY_DATE_START_EDGE = new int[]{21, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};

    private AstrologyUtils() {
    }

    public static final String date2Astrology(Calendar calendar) {
        int i = calendar.get(2);
        if (calendar.get(5) < ASTROLOGY_DATE_START_EDGE[i]) {
            i--;
        }
        if (i >= 0) {
            return ASTROLOGY[i];
        }
        return ASTROLOGY[11];
    }

    public static final int getAge(Calendar calendar) {
        int i = 1;
        Calendar instance = Calendar.getInstance();
        int max = Math.max(0, instance.get(1) - calendar.get(1));
        if (max <= 0) {
            return max;
        }
        int i2 = instance.get(2);
        int i3 = calendar.get(2);
        if (instance.get(5) + (i2 * 100) < (i3 * 100) + calendar.get(5)) {
            i = 0;
        }
        if (i == 0) {
            return max - 1;
        }
        return max;
    }
}
