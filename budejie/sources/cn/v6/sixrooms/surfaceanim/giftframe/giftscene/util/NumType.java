package cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util;

public enum NumType {
    WHITE("num_white_"),
    BLUE("num_blue_"),
    GOLD("num_gold_"),
    PINK("num_pink_");
    
    private String a;

    private NumType(String str) {
        this.a = str;
    }

    public final String getValue() {
        return this.a;
    }
}
