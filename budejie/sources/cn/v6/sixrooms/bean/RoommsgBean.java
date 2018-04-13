package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.gift.Gift;
import java.io.Serializable;
import java.util.List;

public class RoommsgBean implements Serializable {
    private static final long serialVersionUID = 1;
    private BadgeBean badgeBean;
    private String chatMode;
    private int chatStyle = -1;
    private String content;
    private String customRuid;
    private int driver;
    private String eid;
    private String fPic;
    private String fid;
    private String frid;
    private String from;
    private Gift giftItemBean;
    private OnHeadlineBeans headlineBeans;
    private boolean isDefinedWealth;
    private boolean isFirstFans;
    private boolean isPropParsedImgUrl;
    private boolean isPropParsedRes;
    private int position;
    private List<String> priv;
    private List<String> prop;
    private List<String> propImgUrl;
    private List<Integer> propResId;
    private int rank;
    private boolean rankFlag;
    private String tPic;
    private String tm;
    private String to;
    private String toid;
    private String torid;
    private String typeID;
    private int wealth;

    public String getEid() {
        return this.eid;
    }

    public void setEid(String str) {
        this.eid = str;
    }

    public List<String> getPropImgUrl() {
        return this.propImgUrl;
    }

    public void setPropImgUrl(List<String> list) {
        this.propImgUrl = list;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public int getChatStyle() {
        return this.chatStyle;
    }

    public void setChatStyle(int i) {
        this.chatStyle = i;
    }

    public void setPropParsedRes(boolean z) {
        this.isPropParsedRes = z;
    }

    public boolean isPropParsedRes() {
        return this.isPropParsedRes;
    }

    public void setPropParsedImgUrl(boolean z) {
        this.isPropParsedImgUrl = z;
    }

    public boolean isPropParsedImgUrl() {
        return this.isPropParsedImgUrl;
    }

    public void setPropResId(List<Integer> list) {
        this.propResId = list;
    }

    public List<Integer> getPropResId() {
        return this.propResId;
    }

    public void setFirstFans(boolean z) {
        this.isFirstFans = z;
    }

    public boolean isFirstFans() {
        return this.isFirstFans;
    }

    public void setDefinedWealth(boolean z) {
        this.isDefinedWealth = z;
    }

    public boolean isDefinedWealth() {
        return this.isDefinedWealth;
    }

    public void setWealth(int i) {
        this.wealth = i;
    }

    public int getWealth() {
        return this.wealth;
    }

    public void settPic(String str) {
        this.tPic = str;
    }

    public String gettPic() {
        return this.tPic;
    }

    public void setfPic(String str) {
        this.fPic = str;
    }

    public String getfPic() {
        return this.fPic;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public boolean isRankFlag() {
        return this.rankFlag;
    }

    public void setRankFlag(boolean z) {
        this.rankFlag = z;
    }

    public String getCustomRuid() {
        return this.customRuid;
    }

    public void setCustomRuid(String str) {
        this.customRuid = str;
    }

    public int getDriver() {
        return this.driver;
    }

    public void setDriver(int i) {
        this.driver = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getTypeID() {
        return this.typeID;
    }

    public void setTypeID(String str) {
        this.typeID = str;
    }

    public String getTm() {
        return this.tm;
    }

    public void setTm(String str) {
        this.tm = str;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getFrid() {
        return this.frid;
    }

    public void setFrid(String str) {
        this.frid = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getToid() {
        return this.toid;
    }

    public void setToid(String str) {
        this.toid = str;
    }

    public String getTorid() {
        return this.torid;
    }

    public void setTorid(String str) {
        this.torid = str;
    }

    public Gift getGiftItemBean() {
        return this.giftItemBean;
    }

    public void setGiftItemBean(Gift gift) {
        this.giftItemBean = gift;
    }

    public String getChatMode() {
        return this.chatMode;
    }

    public void setChatMode(String str) {
        this.chatMode = str;
    }

    public BadgeBean getBadgeBean() {
        return this.badgeBean;
    }

    public void setBadgeBean(BadgeBean badgeBean) {
        this.badgeBean = badgeBean;
    }

    public List<String> getProp() {
        return this.prop;
    }

    public void setProp(List<String> list) {
        this.prop = list;
    }

    public List<String> getPriv() {
        return this.priv;
    }

    public void setPriv(List<String> list) {
        this.priv = list;
    }

    public OnHeadlineBeans getHeadlineBeans() {
        return this.headlineBeans;
    }

    public void setHeadlineBeans(OnHeadlineBeans onHeadlineBeans) {
        this.headlineBeans = onHeadlineBeans;
    }

    public String toString() {
        return "RoommsgBean [content=" + this.content + ", typeID=" + this.typeID + ", tm=" + this.tm + ", fid=" + this.fid + ", frid=" + this.frid + ", from=" + this.from + ", to=" + this.to + ", toid=" + this.toid + ", torid=" + this.torid + ", chatMode=" + this.chatMode + ", giftItemBean=" + this.giftItemBean + ", badgeBean=" + this.badgeBean + ", prop=" + this.prop + ", priv=" + this.priv + "]";
    }
}
