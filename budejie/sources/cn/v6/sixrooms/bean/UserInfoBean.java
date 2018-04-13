package cn.v6.sixrooms.bean;

import android.text.TextUtils;
import cn.v6.sixrooms.constants.UrlStrs;
import java.io.Serializable;

public class UserInfoBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private int anchorLevel;
    private String badge;
    private String cardLevel;
    private long coin6late;
    private long coinstep;
    private String familyNum;
    private String flag;
    private boolean isAdmin;
    private boolean isFriend;
    private boolean isGag;
    private int isGodPic;
    private boolean isManager;
    private boolean isRoomManager;
    private String isfollow;
    private int msgflag;
    private String priv;
    private String privnum;
    private String prop;
    private int proxyState;
    private String rid;
    private String ruid;
    private int speakState;
    private String uid;
    private String uname;
    private String urid;
    private int userIdentity;
    private String userpic;
    private String vipLevel;
    private int wealthLevel;
    private long wealthstep;
    private long wealtlate;

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getProp() {
        return this.prop;
    }

    public void setProp(String str) {
        this.prop = str;
    }

    public String getPrivnum() {
        return this.privnum;
    }

    public void setPrivnum(String str) {
        this.privnum = str;
    }

    public String getCardLevel() {
        return this.cardLevel;
    }

    public void setCardLevel(String str) {
        this.cardLevel = str;
    }

    public String getVipLevel() {
        return this.vipLevel;
    }

    public void setVipLevel(String str) {
        this.vipLevel = str;
    }

    public long getCoinstep() {
        return this.coinstep;
    }

    public void setCoinstep(long j) {
        this.coinstep = j;
    }

    public long getWealthstep() {
        return this.wealthstep;
    }

    public void setWealthstep(long j) {
        this.wealthstep = j;
    }

    public long getWealtlate() {
        return this.wealtlate;
    }

    public void setWealtlate(long j) {
        this.wealtlate = j;
    }

    public long getCoin6late() {
        return this.coin6late;
    }

    public void setCoin6late(long j) {
        this.coin6late = j;
    }

    public boolean isFriend() {
        return this.isFriend;
    }

    public void setFriend(boolean z) {
        this.isFriend = z;
    }

    public String getRuid() {
        return this.ruid;
    }

    public void setRuid(String str) {
        this.ruid = str;
    }

    public boolean isRoomManager() {
        return this.isRoomManager;
    }

    public void setRoomManager(boolean z) {
        this.isRoomManager = z;
    }

    public void setAnchorLevel(int i) {
        this.anchorLevel = i;
    }

    public void setWealthLevel(int i) {
        this.wealthLevel = i;
    }

    public int getIsGodPic() {
        return this.isGodPic;
    }

    public void setIsGodPic(int i) {
        this.isGodPic = i;
    }

    public void setIsfollow(String str) {
        this.isfollow = str;
    }

    public String getIsfollow() {
        return this.isfollow;
    }

    public String getUserpic() {
        return this.userpic;
    }

    public void setUserpic(String str) {
        this.userpic = str;
    }

    public void analyze() {
        if (this.priv != null) {
            String[] split = this.priv.split("\\|");
            if (split.length >= 10) {
                this.userIdentity = parseInt(split[0]);
                this.anchorLevel = parseInt(split[1]);
                this.speakState = parseInt(split[2]);
                this.familyNum = split[3];
                this.proxyState = parseInt(split[5]);
                this.badge = split[6];
                this.wealthLevel = parseInt(split[7]);
            }
        }
    }

    public int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getUrid() {
        return this.urid;
    }

    public void setUrid(String str) {
        this.urid = str;
    }

    public String getPriv() {
        return this.priv;
    }

    public void setPriv(String str) {
        this.priv = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public int getUserIdentity() {
        return this.userIdentity;
    }

    public int getAnchorLevel() {
        return this.anchorLevel;
    }

    public int getSpeakState() {
        return this.speakState;
    }

    public void setSpeakState(int i) {
        this.speakState = i;
    }

    public void setUserIdentity(int i) {
        this.userIdentity = i;
    }

    public String getFamilyNum() {
        if (TextUtils.isEmpty(this.familyNum)) {
            return this.familyNum;
        }
        return UrlStrs.FAMILY_URL + this.familyNum + ".png";
    }

    public int getProxyState() {
        return this.proxyState;
    }

    public int getWealthLevel() {
        return this.wealthLevel;
    }

    public String getBadge() {
        return this.badge;
    }

    public String toString() {
        return "UserInfoBean [uid=" + this.uid + ", uname=" + this.uname + ", urid=" + this.urid + ", priv=" + this.priv + ", flag=" + this.flag + ", userIdentity=" + this.userIdentity + ", anchorLevel=" + this.anchorLevel + ", speakState=" + this.speakState + ", familyNum=" + this.familyNum + ", proxyState=" + this.proxyState + ", wealthLevel=" + this.wealthLevel + "]";
    }

    public boolean isGag() {
        return this.isGag;
    }

    public void setGag(boolean z) {
        this.isGag = z;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean z) {
        this.isAdmin = z;
    }

    public boolean isManager() {
        return this.isManager;
    }

    public void setManager(boolean z) {
        this.isManager = z;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof UserInfoBean)) {
            return false;
        }
        return getUid().equals(((UserInfoBean) obj).getUid());
    }
}
