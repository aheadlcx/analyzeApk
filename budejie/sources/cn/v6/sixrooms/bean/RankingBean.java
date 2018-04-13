package cn.v6.sixrooms.bean;

public class RankingBean {
    private String cid;
    private String coin6rank;
    private String cvalue;
    private String pic;
    private String rid;
    private String username;
    private String wealthrank;

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public String getWealthrank() {
        return this.wealthrank;
    }

    public void setWealthrank(String str) {
        this.wealthrank = str;
    }

    public String getCvalue() {
        return this.cvalue;
    }

    public void setCvalue(String str) {
        this.cvalue = str;
    }

    public String getCoin6rank() {
        return this.coin6rank;
    }

    public void setCoin6rank(String str) {
        this.coin6rank = str;
    }
}
