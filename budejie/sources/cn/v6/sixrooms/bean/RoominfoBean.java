package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class RoominfoBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private String backpic;
    private String coin6;
    private String coin6all;
    private String coin6late;
    private String coin6rank;
    private String id;
    private String ltime;
    private String max;
    private String rid;
    private String roomkey;
    private long roomkeyTm;
    private int status;
    private UoptionBean uoption;
    private String wealth;
    private String wealthall;
    private String wealthrank;
    private String wealtlate;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getMax() {
        return this.max;
    }

    public void setMax(String str) {
        this.max = str;
    }

    public String getLtime() {
        return this.ltime;
    }

    public void setLtime(String str) {
        this.ltime = str;
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

    public String getBackpic() {
        return this.backpic;
    }

    public void setBackpic(String str) {
        this.backpic = str;
    }

    public String getCoin6all() {
        return this.coin6all;
    }

    public void setCoin6all(String str) {
        this.coin6all = str;
    }

    public String getWealthall() {
        return this.wealthall;
    }

    public void setWealthall(String str) {
        this.wealthall = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getCoin6late() {
        return this.coin6late;
    }

    public void setCoin6late(String str) {
        this.coin6late = str;
    }

    public String getWealtlate() {
        return this.wealtlate;
    }

    public void setWealtlate(String str) {
        this.wealtlate = str;
    }

    public String getCoin6rank() {
        return this.coin6rank;
    }

    public void setCoin6rank(String str) {
        this.coin6rank = str;
    }

    public String getWealthrank() {
        return this.wealthrank;
    }

    public void setWealthrank(String str) {
        this.wealthrank = str;
    }

    public String getRoomkey() {
        return this.roomkey;
    }

    public void setRoomkey(String str) {
        this.roomkey = str;
    }

    public long getRoomkeyTm() {
        return this.roomkeyTm;
    }

    public void setRoomkeyTm(long j) {
        this.roomkeyTm = j;
    }

    public UoptionBean getUoption() {
        return this.uoption;
    }

    public void setUoption(UoptionBean uoptionBean) {
        this.uoption = uoptionBean;
    }

    public String toString() {
        return "RoominfoBean{id='" + this.id + '\'' + ", max='" + this.max + '\'' + ", ltime='" + this.ltime + '\'' + ", wealth='" + this.wealth + '\'' + ", coin6='" + this.coin6 + '\'' + ", backpic='" + this.backpic + '\'' + ", coin6all='" + this.coin6all + '\'' + ", wealthall='" + this.wealthall + '\'' + ", alias='" + this.alias + '\'' + ", rid='" + this.rid + '\'' + ", coin6late='" + this.coin6late + '\'' + ", wealtlate='" + this.wealtlate + '\'' + ", coin6rank='" + this.coin6rank + '\'' + ", wealthrank='" + this.wealthrank + '\'' + ", roomkey='" + this.roomkey + '\'' + ", roomkeyTm=" + this.roomkeyTm + ", status=" + this.status + ", uoption=" + this.uoption + '}';
    }
}
