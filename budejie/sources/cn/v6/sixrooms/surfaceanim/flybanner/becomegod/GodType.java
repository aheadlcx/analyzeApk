package cn.v6.sixrooms.surfaceanim.flybanner.becomegod;

public enum GodType {
    GOD("神"),
    GOD_OF_GODS("众神之神"),
    CREATOR_GOD("创世神");
    
    private String a;

    private GodType(String str) {
        this.a = str;
    }

    public final String getValue() {
        return this.a;
    }
}
