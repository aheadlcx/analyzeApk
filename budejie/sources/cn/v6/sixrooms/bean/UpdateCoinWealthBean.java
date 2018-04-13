package cn.v6.sixrooms.bean;

public class UpdateCoinWealthBean extends MessageBean {
    private String coin6;
    private String gid;
    private String wealth;

    public UpdateCoinWealthBean(String str, String str2, String str3) {
        this.wealth = str;
        this.coin6 = str2;
        this.gid = str3;
    }

    public String getWealth() {
        return this.wealth;
    }

    public void setWealth(String str) {
        this.wealth = str;
    }

    public String getCoin6() {
        return this.coin6;
    }

    public void setCoin6(String str) {
        this.coin6 = str;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public String toString() {
        return "UpdateGiftNumBean [wealth=" + this.wealth + ", coin6=" + this.coin6 + ", gid=" + this.gid + "]";
    }
}
