package cn.v6.sixrooms.bean;

import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.gift.BoxingBean;
import cn.v6.sixrooms.room.gift.InitTopGiftBean;

public class AuthKeyBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private int anchorLevel;
    private String authKey;
    private BoxingBean boxingBean;
    private int eventDefend;
    private String familyNum;
    private InitTopGiftBean initTopGift;
    private MiniGameBean miniGameBean;
    private String priv;
    private int proxyState;
    private int speakState;
    private int userIdentity;
    private int wealthLevel;

    public MiniGameBean getMiniGameBean() {
        return this.miniGameBean;
    }

    public void setMiniGameBean(MiniGameBean miniGameBean) {
        this.miniGameBean = miniGameBean;
    }

    public InitTopGiftBean getInitTopGift() {
        return this.initTopGift;
    }

    public void setInitTopGift(InitTopGiftBean initTopGiftBean) {
        this.initTopGift = initTopGiftBean;
    }

    public int getEventDefend() {
        return this.eventDefend;
    }

    public void setEventDefend(int i) {
        this.eventDefend = i;
    }

    public BoxingBean getBoxingBean() {
        return this.boxingBean;
    }

    public void setBoxingBean(BoxingBean boxingBean) {
        this.boxingBean = boxingBean;
    }

    public String getPriv() {
        return this.priv;
    }

    public void setPriv(String str) {
        this.priv = str;
    }

    public String getAuthKey() {
        return this.authKey;
    }

    public void setAuthKey(String str) {
        this.authKey = str;
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

    public String getFamilyURL() {
        if (this.familyNum == null || "".equals(this.familyNum)) {
            return null;
        }
        return "http://vi0.6.cn/live/family/" + this.familyNum + ".png";
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
}
