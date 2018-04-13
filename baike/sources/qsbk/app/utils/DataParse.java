package qsbk.app.utils;

public class DataParse {

    public enum StampContent {
        NONE,
        POLITICS,
        VULGAR,
        OLD,
        UNGELIEVEABLE,
        FUNNY,
        SKIP
    }

    public static int result(StampContent stampContent) {
        switch (k.a[stampContent.ordinal()]) {
            case 1:
                return -2;
            case 2:
                return -6;
            case 3:
                return -5;
            case 4:
                return -7;
            case 5:
                return 1;
            default:
                return 0;
        }
    }
}
