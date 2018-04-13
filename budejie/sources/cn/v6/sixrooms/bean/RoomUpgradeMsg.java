package cn.v6.sixrooms.bean;

public class RoomUpgradeMsg extends MessageBean {
    public static final String TYPE_COIN = "coin";
    public static final String TYPE_WEALTH = "wealth";
    private String msgid;
    private String name;
    private int rank;
    private String rid;
    private String type;
    private String uid;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getMsgid() {
        return this.msgid;
    }

    public void setMsgid(String str) {
        this.msgid = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
