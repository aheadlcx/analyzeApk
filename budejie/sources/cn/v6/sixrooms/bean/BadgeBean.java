package cn.v6.sixrooms.bean;

public class BadgeBean {
    private int anchorLevel;
    private String badge;
    private String familyNum;
    private String pngcar;
    private String priv;
    private String prop;
    private int proxyState;
    private String rid;
    private int speakState;
    private int userIdentity;
    private int wealthLevel;

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

    public String getPngcar() {
        return this.pngcar;
    }

    public void setPngcar(String str) {
        this.pngcar = str;
    }

    public String getPriv() {
        return this.priv;
    }

    public void setPriv(String str) {
        this.priv = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public int getUserIdentity() {
        return this.userIdentity;
    }

    public void setUserIdentity(int i) {
        this.userIdentity = i;
    }

    public int getAnchorLevel() {
        return this.anchorLevel;
    }

    public void setAnchorLevel(int i) {
        this.anchorLevel = i;
    }

    public int getSpeakState() {
        return this.speakState;
    }

    public void setSpeakState(int i) {
        this.speakState = i;
    }

    public String getFamilyNum() {
        return this.familyNum;
    }

    public void setFamilyNum(String str) {
        this.familyNum = str;
    }

    public int getProxyState() {
        return this.proxyState;
    }

    public void setProxyState(int i) {
        this.proxyState = i;
    }

    public int getWealthLevel() {
        return this.wealthLevel;
    }

    public void setWealthLevel(int i) {
        this.wealthLevel = i;
    }

    public String getBadge() {
        return this.badge;
    }

    public void setBadge(String str) {
        this.badge = str;
    }

    public String getProp() {
        return this.prop;
    }

    public void setProp(String str) {
        this.prop = str;
    }

    public String toString() {
        return "BadgeBean [pngcar=" + this.pngcar + ", priv=" + this.priv + ", rid=" + this.rid + ", prop=" + this.prop + ", userIdentity=" + this.userIdentity + ", anchorLevel=" + this.anchorLevel + ", speakState=" + this.speakState + ", familyNum=" + this.familyNum + ", proxyState=" + this.proxyState + ", wealthLevel=" + this.wealthLevel + ", badge=" + this.badge + "]";
    }
}
