package cn.v6.sixrooms.bean;

import java.io.Serializable;
import java.util.List;

public class ChangzhanStatusBean extends MessageBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String favrank;
    private String favscore;
    private List<String> honor;
    private List<ChangzhanStatusMsgBean> msg;
    private String rank;
    private String schedule;
    private String schtag;
    private String tscore;
    private String vbegtm;
    private String vendtm;

    public List<String> getHonor() {
        return this.honor;
    }

    public void setHonor(List<String> list) {
        this.honor = list;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(String str) {
        this.schedule = str;
    }

    public String getSchtag() {
        return this.schtag;
    }

    public void setSchtag(String str) {
        this.schtag = str;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String str) {
        this.rank = str;
    }

    public String getTscore() {
        return this.tscore;
    }

    public void setTscore(String str) {
        this.tscore = str;
    }

    public String getVbegtm() {
        return this.vbegtm;
    }

    public void setVbegtm(String str) {
        this.vbegtm = str;
    }

    public String getVendtm() {
        return this.vendtm;
    }

    public void setVendtm(String str) {
        this.vendtm = str;
    }

    public String getFavscore() {
        return this.favscore;
    }

    public void setFavscore(String str) {
        this.favscore = str;
    }

    public String getFavrank() {
        return this.favrank;
    }

    public void setFavrank(String str) {
        this.favrank = str;
    }

    public List<ChangzhanStatusMsgBean> getMsg() {
        return this.msg;
    }

    public void setMsg(List<ChangzhanStatusMsgBean> list) {
        this.msg = list;
    }

    public String toString() {
        return "ChangzhanStatusBean [schedule=" + this.schedule + ", schtag=" + this.schtag + ", favscore=" + this.favscore + ", favrank=" + this.favrank + ", rank=" + this.rank + ", tscore=" + this.tscore + ", msg=" + this.msg + ", vbegtm=" + this.vbegtm + ", vendtm=" + this.vendtm + "]";
    }
}
