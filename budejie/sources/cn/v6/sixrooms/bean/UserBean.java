package cn.v6.sixrooms.bean;

import android.text.TextUtils;
import cn.v6.sixrooms.constants.UrlStrs;
import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private String audioall;
    private String backpic;
    private List<String> badge;
    private String coin6;
    private String coin6all;
    private String coin6late;
    private String coin6rank;
    private String coinstep;
    private String fansnum;
    private String fid;
    private String follownum;
    private String id;
    private int isGodPic;
    private String isLive;
    private String isfollow;
    private String isfriend;
    private String livetype;
    private String login_last;
    private String ltime;
    private String photoall;
    private String picuser;
    private String props;
    private String remark;
    private String rid;
    private String userfrom;
    private String utype;
    private String videoall;
    private String wealth;
    private String wealthall;
    private String wealthrank;
    private String wealthstep;
    private String wealtlate;
    private String weiboall;

    public List<String> getBadge() {
        return this.badge;
    }

    public void setBadge(List<String> list) {
        this.badge = list;
    }

    public int getIsGodPic() {
        return this.isGodPic;
    }

    public void setIsGodPic(int i) {
        this.isGodPic = i;
    }

    public String getIsfriend() {
        return this.isfriend;
    }

    public void setIsfriend(String str) {
        this.isfriend = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getUserfrom() {
        return this.userfrom;
    }

    public void setUserfrom(String str) {
        this.userfrom = str;
    }

    public String getFollownum() {
        return this.follownum;
    }

    public void setFollownum(String str) {
        this.follownum = str;
    }

    public String getWeiboall() {
        return this.weiboall;
    }

    public void setWeiboall(String str) {
        this.weiboall = str;
    }

    public String getAudioall() {
        return this.audioall;
    }

    public void setAudioall(String str) {
        this.audioall = str;
    }

    public String getPhotoall() {
        return this.photoall;
    }

    public void setPhotoall(String str) {
        this.photoall = str;
    }

    public String getVideoall() {
        return this.videoall;
    }

    public void setVideoall(String str) {
        this.videoall = str;
    }

    public String getCoinstep() {
        return this.coinstep;
    }

    public void setCoinstep(String str) {
        this.coinstep = str;
    }

    public String getWealthstep() {
        return this.wealthstep;
    }

    public void setWealthstep(String str) {
        this.wealthstep = str;
    }

    public String getFid() {
        if (TextUtils.isEmpty(this.fid) || this.fid.equals("0")) {
            return "";
        }
        return UrlStrs.FAMILY_URL + this.fid + ".png";
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getFansnum() {
        return this.fansnum;
    }

    public void setFansnum(String str) {
        this.fansnum = str;
    }

    public String getLtime() {
        return this.ltime;
    }

    public void setLtime(String str) {
        this.ltime = str;
    }

    public String getIsfollow() {
        return this.isfollow;
    }

    public void setIsfollow(String str) {
        this.isfollow = str;
    }

    public String getBackpic() {
        return this.backpic;
    }

    public void setBackpic(String str) {
        this.backpic = str;
    }

    public static long getSerialversionuid() {
        return 1;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        this.picuser = str;
    }

    public String getLogin_last() {
        return this.login_last;
    }

    public void setLogin_last(String str) {
        this.login_last = str;
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

    public String getUtype() {
        return this.utype;
    }

    public void setUtype(String str) {
        this.utype = str;
    }

    public String getProps() {
        return this.props;
    }

    public void setProps(String str) {
        this.props = str;
    }

    public String getIsLive() {
        return this.isLive;
    }

    public void setIsLive(String str) {
        this.isLive = str;
    }

    public String getLivetype() {
        return this.livetype;
    }

    public void setLivetype(String str) {
        this.livetype = str;
    }

    public String toString() {
        return "UserBean{id='" + this.id + '\'' + ", fid='" + this.fid + '\'' + ", fansnum='" + this.fansnum + '\'' + ", ltime='" + this.ltime + '\'' + ", isfollow='" + this.isfollow + '\'' + ", backpic='" + this.backpic + '\'' + ", wealth='" + this.wealth + '\'' + ", coin6='" + this.coin6 + '\'' + ", coin6all='" + this.coin6all + '\'' + ", wealthall='" + this.wealthall + '\'' + ", picuser='" + this.picuser + '\'' + ", login_last='" + this.login_last + '\'' + ", alias='" + this.alias + '\'' + ", rid='" + this.rid + '\'' + ", coin6late='" + this.coin6late + '\'' + ", wealtlate='" + this.wealtlate + '\'' + ", coin6rank='" + this.coin6rank + '\'' + ", wealthrank='" + this.wealthrank + '\'' + ", props='" + this.props + '\'' + ", utype='" + this.utype + '\'' + ", follownum='" + this.follownum + '\'' + ", weiboall='" + this.weiboall + '\'' + ", audioall='" + this.audioall + '\'' + ", photoall='" + this.photoall + '\'' + ", videoall='" + this.videoall + '\'' + ", coinstep='" + this.coinstep + '\'' + ", wealthstep='" + this.wealthstep + '\'' + ", userfrom='" + this.userfrom + '\'' + ", isLive='" + this.isLive + '\'' + ", isGodPic=" + this.isGodPic + ", livetype='" + this.livetype + '\'' + ", isfriend='" + this.isfriend + '\'' + ", remark='" + this.remark + '\'' + ", badge=" + this.badge + '}';
    }
}
