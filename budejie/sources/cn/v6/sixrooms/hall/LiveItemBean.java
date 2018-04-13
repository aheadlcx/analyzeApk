package cn.v6.sixrooms.hall;

import android.text.TextUtils;
import java.io.Serializable;

public class LiveItemBean implements Serializable, Comparable<LiveItemBean> {
    private static final long serialVersionUID = 1;
    private String alias;
    private String anchor;
    private String banpic;
    private String count;
    private int drawableId;
    private String flvtitle;
    private boolean isRect;
    private String isspe;
    private String largepic;
    private String pic;
    private String picuser;
    private String pospic;
    private String realstarttime;
    private String recid;
    private String red;
    private String rid;
    private String rtype;
    private String score;
    private String secflvtitle;
    private String starttime;
    private String tagid;
    private String tagname;
    private String title;
    private String type;
    private int typeId;
    private String uid;
    private String username;
    private String videotype;
    private String wealthrank;
    private String wealtlate;

    public String getRecid() {
        return this.recid;
    }

    public void setRecid(String str) {
        this.recid = str;
    }

    public String getLargepic() {
        return this.largepic;
    }

    public void setLargepic(String str) {
        if ("null".equals(str)) {
            str = null;
        }
        this.largepic = str;
    }

    public boolean isRect() {
        return this.isRect;
    }

    public void setRect(boolean z) {
        this.isRect = z;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int i) {
        this.typeId = i;
    }

    public int getDrawableId() {
        return this.drawableId;
    }

    public void setDrawableId(int i) {
        this.drawableId = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getVideotype() {
        return this.videotype;
    }

    public void setVideotype(String str) {
        this.videotype = str;
    }

    public String getTagid() {
        return this.tagid;
    }

    public void setTagid(String str) {
        this.tagid = str;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public String getBanpic() {
        return this.banpic;
    }

    public void setBanpic(String str) {
        this.banpic = str;
    }

    public String getTagname() {
        return this.tagname;
    }

    public void setTagname(String str) {
        this.tagname = str;
    }

    public String getIsspe() {
        return this.isspe;
    }

    public void setIsspe(String str) {
        this.isspe = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getWealthrank() {
        return this.wealthrank;
    }

    public void setWealthrank(String str) {
        this.wealthrank = str;
    }

    public String getWealtlate() {
        return this.wealtlate;
    }

    public void setWealtlate(String str) {
        this.wealtlate = str;
    }

    public String getUsername() {
        if (TextUtils.isEmpty(this.alias)) {
            return this.username;
        }
        return this.alias;
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
        if ("null".equals(this.pic)) {
            this.pic = null;
        }
        if (!TextUtils.isEmpty(this.pic)) {
            this.pic = this.pic.replace("_s.", "_b.");
        }
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        if ("null".equals(str)) {
            str = null;
        }
        this.picuser = str;
    }

    public String getRed() {
        return this.red;
    }

    public void setRed(String str) {
        this.red = str;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String str) {
        this.starttime = str;
    }

    public String getRtype() {
        return this.rtype;
    }

    public void setRtype(String str) {
        this.rtype = str;
    }

    public String getRealstarttime() {
        return this.realstarttime;
    }

    public void setRealstarttime(String str) {
        this.realstarttime = str;
    }

    public String getPospic() {
        if ("null".equals(this.pospic)) {
            this.pospic = null;
        }
        if (!TextUtils.isEmpty(this.pospic)) {
            this.pospic = this.pospic.replace("_s.", "_b.");
        }
        return this.pospic;
    }

    public void setPospic(String str) {
        this.pospic = str;
    }

    public String getAnchor() {
        return this.anchor;
    }

    public void setAnchor(String str) {
        this.anchor = str;
    }

    public String getFlvtitle() {
        return this.flvtitle;
    }

    public void setFlvtitle(String str) {
        this.flvtitle = str;
    }

    public String getSecflvtitle() {
        return this.secflvtitle;
    }

    public void setSecflvtitle(String str) {
        this.secflvtitle = str;
    }

    public String toString() {
        return "LiveItemBean{uid='" + this.uid + '\'' + ", wealthrank='" + this.wealthrank + '\'' + ", wealtlate='" + this.wealtlate + '\'' + ", username='" + this.username + '\'' + ", alias='" + this.alias + '\'' + ", rid='" + this.rid + '\'' + ", pic='" + this.pic + '\'' + ", picuser='" + this.picuser + '\'' + ", red='" + this.red + '\'' + ", count='" + this.count + '\'' + ", starttime='" + this.starttime + '\'' + ", rtype='" + this.rtype + '\'' + ", realstarttime='" + this.realstarttime + '\'' + ", pospic='" + this.pospic + '\'' + ", anchor='" + this.anchor + '\'' + ", isspe='" + this.isspe + '\'' + ", tagname='" + this.tagname + '\'' + ", tagid='" + this.tagid + '\'' + ", banpic='" + this.banpic + '\'' + ", score='" + this.score + '\'' + ", videotype='" + this.videotype + '\'' + ", largepic='" + this.largepic + '\'' + ", flvtitle='" + this.flvtitle + '\'' + ", secflvtitle='" + this.secflvtitle + '\'' + ", recid='" + this.recid + '\'' + ", typeId=" + this.typeId + ", drawableId=" + this.drawableId + ", type='" + this.type + '\'' + ", title='" + this.title + '\'' + ", isRect=" + this.isRect + '}';
    }

    public int compareTo(LiveItemBean liveItemBean) {
        return Integer.parseInt(liveItemBean.getCount()) - Integer.parseInt(this.count);
    }
}
