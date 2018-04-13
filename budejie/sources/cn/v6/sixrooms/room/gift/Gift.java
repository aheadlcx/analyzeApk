package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.GiftBean;

public class Gift extends GiftBean implements Cloneable {
    private String anigift;
    private String animType;
    private String anipic;
    private Gift$Pic bpic;
    private String gtype;
    private String id;
    private String intro;
    private boolean isSelected;
    private boolean isShowCoolEffect;
    private int level;
    private String localResPath;
    private Gift$Pic mpic;
    private String msgflag = "0";
    private String price;
    public long randomNum;
    private int rank;
    private Gift$Pic spic;
    private String title;
    private String type;
    private String uids;

    public String getLocalResPath() {
        return this.localResPath;
    }

    public void setLocalResPath(String str) {
        this.localResPath = str;
    }

    public String getGtype() {
        return this.gtype == null ? "" : this.gtype;
    }

    public void setGtype(String str) {
        this.gtype = str;
    }

    public String getAnipic() {
        return this.anipic;
    }

    public void setAnipic(String str) {
        this.anipic = str;
    }

    public String getAnigift() {
        return this.anigift;
    }

    public void setAnigift(String str) {
        this.anigift = str;
    }

    public String getMsgflag() {
        return this.msgflag;
    }

    public void setMsgflag(String str) {
        this.msgflag = str;
    }

    public boolean isShowCoolEffect() {
        return this.isShowCoolEffect;
    }

    public void setShowCoolEffect(boolean z) {
        this.isShowCoolEffect = z;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public String getId() {
        return this.id;
    }

    public String getPrice() {
        return this.price;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public Gift$Pic getSpic() {
        return this.spic;
    }

    public Gift$Pic getMpic() {
        return this.mpic;
    }

    public Gift$Pic getBpic() {
        return this.bpic;
    }

    public String getUids() {
        return this.uids;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setAnimType(String str) {
        this.animType = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setSpic(Gift$Pic gift$Pic) {
        this.spic = gift$Pic;
    }

    public void setMpic(Gift$Pic gift$Pic) {
        this.mpic = gift$Pic;
    }

    public void setBpic(Gift$Pic gift$Pic) {
        this.bpic = gift$Pic;
    }

    public void setUids(String str) {
        this.uids = str;
    }

    public String getAnimType() {
        return this.animType;
    }

    public String getIntro() {
        return this.intro.replace("\\n", "");
    }

    public void setIntro(String str) {
        this.intro = str;
    }

    public Gift clone() throws CloneNotSupportedException {
        Gift gift = (Gift) super.clone();
        if (gift != null) {
            Gift$Pic clone;
            Gift$Pic gift$Pic = new Gift$Pic(this);
            if (gift.getMpic() != null) {
                clone = gift.getMpic().clone();
            } else {
                clone = gift$Pic;
            }
            gift.setMpic(clone);
            if (gift.getSpic() != null) {
                clone = gift.getSpic().clone();
            } else {
                clone = gift$Pic;
            }
            gift.setSpic(clone);
            if (gift.getBpic() != null) {
                gift$Pic = gift.getBpic().clone();
            }
            gift.setBpic(gift$Pic);
        }
        return gift;
    }

    public String toString() {
        return "Gift{id='" + this.id + '\'' + ", animType='" + this.animType + '\'' + ", price='" + this.price + '\'' + ", title='" + this.title + '\'' + ", type='" + this.type + '\'' + ", spic=" + this.spic + ", mpic=" + this.mpic + ", bpic=" + this.bpic + ", uids='" + this.uids + '\'' + ", intro='" + this.intro + '\'' + ", msgflag='" + this.msgflag + '\'' + ", gtype='" + this.gtype + '\'' + ", anipic='" + this.anipic + '\'' + ", anigift='" + this.anigift + '\'' + ", localResPath='" + this.localResPath + '\'' + ", randomNum=" + this.randomNum + ", rank=" + this.rank + ", level=" + this.level + ", isSelected=" + this.isSelected + ", isShowCoolEffect=" + this.isShowCoolEffect + '}';
    }
}
