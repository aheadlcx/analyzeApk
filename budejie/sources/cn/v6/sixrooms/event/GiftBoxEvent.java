package cn.v6.sixrooms.event;

public class GiftBoxEvent {
    public static final String UPDATE_CHAT_LIST = "1";
    public static final String UPDATE_RED = "2";
    public static final String WANT_TO_OPEN_THE_GIFTBOX = "0";
    private String a;
    private String b;
    public boolean isUpdateChatList;
    public boolean isUpdateRed;

    public String getUid() {
        return this.a;
    }

    public void setUid(String str) {
        this.a = str;
    }

    public String getUname() {
        return this.b;
    }

    public void setUname(String str) {
        this.b = str;
    }
}
