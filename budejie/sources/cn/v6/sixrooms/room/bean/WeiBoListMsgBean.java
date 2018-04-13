package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class WeiBoListMsgBean implements Serializable, Comparable<WeiBoListMsgBean> {
    private static final long serialVersionUID = 1;
    private String alias = "";
    private String commnum = "";
    private WeiBoListMsgBean forWardBean;
    private String forwardnum = "";
    private String id = "";
    private boolean isPlayerLoding = false;
    private boolean isPlayingAudio = false;
    private WeiBoDetailsBean msgBean;
    private String rid = "";
    private String tm = "";
    private String type = "";
    private String uid = "";
    private String userpic = "";

    public boolean isPlayerLoding() {
        return this.isPlayerLoding;
    }

    public void setPlayerLoding(boolean z) {
        this.isPlayerLoding = z;
    }

    public boolean isPlayingAudio() {
        return this.isPlayingAudio;
    }

    public synchronized void setPlayingAudio(boolean z) {
        this.isPlayingAudio = z;
    }

    public WeiBoListMsgBean getForWardBean() {
        return this.forWardBean;
    }

    public void setForWardBean(WeiBoListMsgBean weiBoListMsgBean) {
        this.forWardBean = weiBoListMsgBean;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCommnum() {
        return this.commnum;
    }

    public void setCommnum(String str) {
        this.commnum = str;
    }

    public String getForwardnum() {
        return this.forwardnum;
    }

    public void setForwardnum(String str) {
        this.forwardnum = str;
    }

    public String getTm() {
        return this.tm;
    }

    public void setTm(String str) {
        this.tm = str;
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

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getUserpic() {
        return this.userpic;
    }

    public void setUserpic(String str) {
        this.userpic = str;
    }

    public WeiBoDetailsBean getMsgBean() {
        return this.msgBean;
    }

    public void setMsgBean(WeiBoDetailsBean weiBoDetailsBean) {
        this.msgBean = weiBoDetailsBean;
    }

    public String toString() {
        return "WeiBoListMsgBean [type=" + this.type + ", id=" + this.id + ", commnum=" + this.commnum + ", forwardnum=" + this.forwardnum + ", tm=" + this.tm + ", uid=" + this.uid + ", rid=" + this.rid + ", alias=" + this.alias + ", userpic=" + this.userpic + ", msgBean=" + this.msgBean + ", forWardBean=" + this.forWardBean + "]";
    }

    public int compareTo(WeiBoListMsgBean weiBoListMsgBean) {
        return -((int) (Long.parseLong(this.tm) - Long.parseLong(weiBoListMsgBean.tm)));
    }
}
