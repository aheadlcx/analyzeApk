package cn.v6.sixrooms.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiftIdStrs {
    public static final String BIG_FIREWORKS = "99";
    public static final String BIRTHDAY_GIFT_ID = "539";
    public static final String CHANGZHANG_GIFT_ID = "379";
    public static final String GIFT_ID_1 = "1";
    public static final int GOLD_GUARD = 7570;
    public static final String ID_FALL_IN_LOVE_AT_FIRST_SIGHT = "833";
    public static final String ID_I_LOVE_YOU = "834";
    public static final String ID_TURN_OVER_A_SIGN = "835";
    public static final String PIG_GIFT_ID = "88";
    public static final int SILVER_GUARD = 7569;
    public static final String SMALL_FIREWORKS = "98";
    public static final String STONE_ID = "7333";
    public static final String SUPER_FIREWORKS = "430";
    public static final String WELCOME_GIFT_ID = "108";
    public static final String YELLOWDUCK_GIFT_ID = "465";
    private static final String[] a;
    private static final String[] b;
    public static final List<String> fireworksIds;
    public static final List<String> nobleIds;

    static {
        String[] strArr = new String[]{SMALL_FIREWORKS, "99", SUPER_FIREWORKS};
        a = strArr;
        fireworksIds = Collections.unmodifiableList(Arrays.asList(strArr));
        strArr = new String[]{ID_FALL_IN_LOVE_AT_FIRST_SIGHT, ID_I_LOVE_YOU, ID_TURN_OVER_A_SIGN};
        b = strArr;
        nobleIds = Collections.unmodifiableList(Arrays.asList(strArr));
    }
}
